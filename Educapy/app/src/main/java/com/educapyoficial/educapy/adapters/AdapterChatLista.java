package com.educapyoficial.educapy.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.educapyoficial.educapy.MensajesActivity;
import com.educapyoficial.educapy.R;
import com.educapyoficial.educapy.pojos.Users;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AdapterChatLista extends RecyclerView.Adapter<AdapterChatLista.viewHolderAdapterchatlist> {

    List<Users> usersList;
    Context context;

    //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    SharedPreferences mPref;


    String uidUsuarioConectado;

    public AdapterChatLista(List<Users> usersList, Context context, String uidUsuarioConectado) {
        this.usersList = usersList;
        this.context = context;
        this.uidUsuarioConectado = uidUsuarioConectado;
    }

    @NonNull
    @Override
    public viewHolderAdapterchatlist onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chatlista, parent, false);

        viewHolderAdapterchatlist holder = new viewHolderAdapterchatlist(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolderAdapterchatlist holder, int position) {

        final Users userss = usersList.get(position);

        final Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        holder.tv_usuario.setText(userss.getNombre());
        Glide.with(context).load(userss.getFoto()).into(holder.img_user);


       /* DatabaseReference ref_mis_solicitudes = database.getReference("Solicitudes").child(user.getUid());
        if (ref_mis_solicitudes != null) {
            ref_mis_solicitudes.child(userss.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String estado = dataSnapshot.child("estado").getValue(String.class);

                    if (dataSnapshot.exists()) {

                        if (estado.equals("amigos")) {
                            holder.cardView.setVisibility(View.VISIBLE);
                        } else {
                            holder.cardView.setVisibility(View.GONE);
                        }
                    } else {
                        holder.cardView.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }*/

        /*final Calendar c = Calendar.getInstance();
        final SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");

        DatabaseReference ref_Estado = database.getReference("Estado").child(userss.getUid());

        ref_Estado.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String estado = dataSnapshot.child("estado").getValue(String.class);
                String fecha = dataSnapshot.child("fecha").getValue(String.class);
                String hora = dataSnapshot.child("hora").getValue(String.class);

                if (dataSnapshot.exists()) {
                    if (estado.equals("conectado")) {
                        holder.tv_conectado.setVisibility(View.VISIBLE);
                        holder.icon_conectado.setVisibility(View.VISIBLE);
                        holder.tv_desconectado.setVisibility(View.GONE);
                        holder.icon_desconectado.setVisibility(View.GONE);
                    } else {
                        holder.tv_conectado.setVisibility(View.GONE);
                        holder.icon_conectado.setVisibility(View.GONE);
                        holder.tv_desconectado.setVisibility(View.VISIBLE);
                        holder.icon_desconectado.setVisibility(View.VISIBLE);

                        if (fecha.equals(dateformat.format(c.getTime()))) {
                            holder.tv_desconectado.setText("ult.vez hoy a las " + hora);
                        } else {
                            holder.tv_desconectado.setText("ult.vez " + fecha + " a las " + hora);
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                mPref = v.getContext().getSharedPreferences("usuario_sp", Context.MODE_PRIVATE);
                final SharedPreferences.Editor editor = mPref.edit();


                final DatabaseReference ref = database.getReference("Chats").child("idchat").child(uidUsuarioConectado + userss.getUid());

                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //String id_unico = dataSnapshot.getValue(String.class);
                        if (dataSnapshot.exists()) {
                            Intent intent = new Intent(v.getContext(), MensajesActivity.class);
                            intent.putExtra("nombre", userss.getNombre());
                            intent.putExtra("img_user", userss.getFoto());
                            intent.putExtra("id_user", userss.getId());
                            intent.putExtra("id_unico", uidUsuarioConectado + userss.getUid());
                            editor.putString("usuario_sp", userss.getUid());
                            editor.apply();
                            v.getContext().startActivity(intent);
                        } else {

                            DatabaseReference ref2 = database.getReference("Chats").child(userss.getUid() + uidUsuarioConectado);

                            ref2.addListenerForSingleValueEvent(new ValueEventListener() {

                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (dataSnapshot.exists()) {
                                        Intent intent = new Intent(v.getContext(), MensajesActivity.class);
                                        intent.putExtra("nombre", userss.getNombre());
                                        intent.putExtra("img_user", userss.getFoto());
                                        intent.putExtra("id_user", userss.getId());
                                        intent.putExtra("id_unico", userss.getUid() + uidUsuarioConectado);
                                        editor.putString("usuario_sp", userss.getUid());
                                        editor.apply();
                                        v.getContext().startActivity(intent);
                                    } else {
                                        //database.getReference("Chats").push().setValue(userss.getUid() + uidUsuarioConectado);
                                        Intent intent = new Intent(v.getContext(), MensajesActivity.class);
                                        intent.putExtra("nombre", userss.getNombre());
                                        intent.putExtra("img_user", userss.getFoto());
                                        intent.putExtra("id_user", userss.getId());
                                        intent.putExtra("id_unico", userss.getUid() + uidUsuarioConectado);
                                        editor.putString("usuario_sp", userss.getUid());
                                        editor.apply();
                                        v.getContext().startActivity(intent);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });




    } //fin del onbinViewHolder

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class viewHolderAdapterchatlist extends RecyclerView.ViewHolder {

        TextView tv_usuario;
        ImageView img_user;
        CardView cardView;
        TextView tv_conectado, tv_desconectado;
        ImageView icon_conectado, icon_desconectado;

        public viewHolderAdapterchatlist(@NonNull View itemView) {
            super(itemView);

            tv_usuario = itemView.findViewById(R.id.tv_user);
            img_user = itemView.findViewById(R.id.img_user);
            cardView = itemView.findViewById(R.id.cardview);
            tv_conectado = itemView.findViewById(R.id.tv_conectado);
            tv_desconectado = itemView.findViewById(R.id.tv_desconectado);
            icon_conectado = itemView.findViewById(R.id.icon_conectado);
            icon_desconectado = itemView.findViewById(R.id.icon_desconectado);


        }
    }
}
