package com.educapyoficial.educapy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.educapyoficial.educapy.R;
import com.educapyoficial.educapy.pojos.Chats;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AdapterChats extends RecyclerView.Adapter<AdapterChats.ViewHolderAdapter> {

    List<Chats> chatsList;
    Context context;
    public static final int MENSAJE_RIGHT = 1;
    public static final int MENSAJE_LEFT = 0;
    Boolean soloright = false;
    //FirebaseUser fuser;
    String uidConectado;

    public AdapterChats(List<Chats> chatsList, Context context, String uidConectado) {
        this.chatsList = chatsList;
        this.context = context;
        this.uidConectado = uidConectado;
    }

    @NonNull
    @Override
    public ViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == MENSAJE_RIGHT)
        {
           View view = LayoutInflater.from(context).inflate(R.layout.chat_item_righ,parent,false);
            return new AdapterChats.ViewHolderAdapter(view);
        }
        else
        {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_left,parent,false);
            return new AdapterChats.ViewHolderAdapter(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAdapter holder, int position) {

        Chats chats = chatsList.get(position);

        holder.tv_mensaje.setText(chats.getMensaje());

        if(soloright)
        {
            if(chats.getVisto().equals("si"))
            {
                holder.img_entregado.setVisibility(View.GONE);
                holder.img_visto.setVisibility(View.VISIBLE);
            }
            else
            {
                holder.img_entregado.setVisibility(View.VISIBLE);
                holder.img_visto.setVisibility(View.GONE);
            }

            final Calendar c = Calendar.getInstance();
            final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            if(chats.getFecha().equals(dateFormat.format(c.getTime())))
            {
                holder.tv_fecha.setText("hoy "+chats.getHora());
            }else {
                holder.tv_fecha.setText(chats.getFecha()+" "+chats.getHora());
            }
        }
    }

    @Override
    public int getItemCount() {
        return chatsList.size();
    }

    public class ViewHolderAdapter extends RecyclerView.ViewHolder {


        TextView tv_mensaje,tv_fecha;
        ImageView img_entregado,img_visto;

        public ViewHolderAdapter(@NonNull View itemView) {
            super(itemView);
            tv_mensaje = itemView.findViewById(R.id.tv_mensaje);
            tv_fecha = itemView.findViewById(R.id.tv_fecha);
            img_entregado = itemView.findViewById(R.id.img_entregado);
            img_visto = itemView.findViewById(R.id.img_visto);
        }
    }

    @Override
    public int getItemViewType(int position) {
         //fuser = FirebaseAuth.getInstance().getCurrentUser();
         if(chatsList.get(position).getEnvia().equals(uidConectado))
         {
             soloright=true;
             return MENSAJE_RIGHT;
         }
         else
         {
             soloright=false;
             return MENSAJE_LEFT;
         }
    }
}
