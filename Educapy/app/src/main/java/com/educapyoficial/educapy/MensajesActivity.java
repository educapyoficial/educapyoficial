package com.educapyoficial.educapy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.educapyoficial.educapy.adapters.AdapterChats;
import com.educapyoficial.educapy.pojos.Chats;
import com.educapyoficial.educapy.pojos.Estado;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class MensajesActivity extends AppCompatActivity {

    CircleImageView img_user;
    TextView username;
    ImageView ic_conectado,ic_desconectado;
    SharedPreferences mPref;

    //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref_estado;
    DatabaseReference ref_chat = database.getReference("Chats");

    EditText et_mensaje_txt;
    ImageButton btn_enviar_msj;

    String id_chat_global;
    Boolean amigoonline = false;

    RecyclerView rv_chats;
    AdapterChats adapter;
    ArrayList<Chats> chatslist;
    String envia;
    String recibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPref = getApplicationContext().getSharedPreferences("usuario_sp",MODE_PRIVATE);
        img_user = findViewById(R.id.img_usuario);
        username = findViewById(R.id.tv_user);
        ic_conectado = findViewById(R.id.icon_conectado);
        ic_desconectado =findViewById(R.id.icon_desconectado);
        String usuario = getIntent().getExtras().getString("nombre");
        String foto = getIntent().getExtras().getString("img_user");
        envia = getIntent().getExtras().getString("envia");
        id_chat_global = getIntent().getExtras().getString("id_unico");
        recibe = getIntent().getExtras().getString("recibe");

        ref_estado = database.getReference("Estado").child(envia);

        colocarenvisto();

        et_mensaje_txt = findViewById(R.id.et_txt_mensaje);
        btn_enviar_msj = findViewById(R.id.btn_enviar_msj);

        btn_enviar_msj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msj = et_mensaje_txt.getText().toString();

                if(!msj.isEmpty())
                {

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm");
                    SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
                    String idpush = ref_chat.push().getKey();

                    if(amigoonline)
                    {
                        Chats chatmsj = new Chats(idpush, envia, recibe, msj,"si",dateformat.format(c.getTime()),timeformat.format(c.getTime()));

                        ref_chat.child(id_chat_global).child(idpush).setValue(chatmsj);
                        Toast.makeText(MensajesActivity.this, "Mensaje Enviado", Toast.LENGTH_SHORT).show();
                        et_mensaje_txt.setText("");
                    }else
                    {
                        Chats chatmsj = new Chats(idpush, envia, recibe, msj,"no",dateformat.format(c.getTime()),timeformat.format(c.getTime()));

                        ref_chat.child(id_chat_global).child(idpush).setValue(chatmsj);
                        Toast.makeText(MensajesActivity.this, "Mensaje Enviado", Toast.LENGTH_SHORT).show();
                        et_mensaje_txt.setText("");
                    }

                }
                else
                {
                    Toast.makeText(MensajesActivity.this, "Escribe un Mensaje", Toast.LENGTH_SHORT).show();
                }

            }
        });

        final String id_user_sp = mPref.getString("usuario_sp","");

        username.setText(usuario);
        Glide.with(this).load(foto).into(img_user);

        final DatabaseReference ref = database.getReference("Estado").child(id_user_sp).child("chatcon");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String chatcon = dataSnapshot.getValue(String.class);
                if(dataSnapshot.exists())
                {
                    if(chatcon.equals(envia))
                    {
                        amigoonline = true;
                        ic_conectado.setVisibility(View.VISIBLE);
                        ic_desconectado.setVisibility(View.GONE);
                    }
                    else
                    {
                        amigoonline = false;
                        ic_conectado.setVisibility(View.GONE);
                        ic_desconectado.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //rv--

        rv_chats = findViewById(R.id.rv);
        rv_chats.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        rv_chats.setLayoutManager(linearLayoutManager);

        chatslist = new ArrayList<>();
        adapter = new AdapterChats(chatslist,this, envia);
        rv_chats.setAdapter(adapter);

        LeerMensajes();


    } //fin del oncreate

    private void colocarenvisto() {
        ref_chat.child(id_chat_global).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    Chats chats = snapshot.getValue(Chats.class);
                    if(chats.getRecibe() != null && chats.getRecibe().equals(envia))
                    {
                        ref_chat.child(id_chat_global).child(chats.getId()).child("visto").setValue("si");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void LeerMensajes() {
        ref_chat.child(id_chat_global).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    chatslist.removeAll(chatslist);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren())
                    {
                        Chats chat = snapshot.getValue(Chats.class);
                        Chats chats = snapshot.getValue(Chats.class);
                        chatslist.add(chat);
                        setScroll(); //este metodo sirve para que nos muestre el ultimo mensaje y retorne a la ultima posicion
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setScroll() {
        rv_chats.scrollToPosition(adapter.getItemCount()-1);
    }

    private void estadousuario(final String estado) {

        final String id_user_sp = mPref.getString("usuario_sp","");

        ref_estado.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Estado est = new Estado(estado,"","",id_user_sp);
                ref_estado.setValue(est);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        estadousuario("conectado");
    }

    @Override
    protected void onPause() {
        super.onPause();
        estadousuario("desconectado");
        dameultimafecha();
    }

    private void dameultimafecha() {
        final Calendar c = Calendar.getInstance();
        final SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm");
        final SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");

        ref_estado.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ref_estado.child("fecha").setValue(dateformat.format(c.getTime()));
                ref_estado.child("hora").setValue(timeformat.format(c.getTime()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}