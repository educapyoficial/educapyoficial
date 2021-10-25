package com.educapyoficial.educapy.adapters;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.educapyoficial.educapy.R;
import com.educapyoficial.educapy.models.RegistroAnecdotario;


import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AdapterAnecdotario extends RecyclerView.Adapter<AdapterAnecdotario.ViewHolderAdapter> {

    List<RegistroAnecdotario> chatsList;
    Context context;
    public static final int MENSAJE_RIGHT = 1;
    public static final int MENSAJE_LEFT = 0;
    Boolean soloright = false;

    public AdapterAnecdotario(List<RegistroAnecdotario> chatsList, Context context) {
        this.chatsList = chatsList;
        this.context = context;
    }

    @Override
    public ViewHolderAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anecdotario, parent, false);
        return new ViewHolderAdapter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAdapter holder, int position) {

        RegistroAnecdotario registroAnecdotario = chatsList.get(position);

//        if (registroAnecdotario.getNombreAlumno() != null){
//            holder.textNombreNinho.setText(registroAnecdotario.getNombreAlumno());
//        }

        if (registroAnecdotario.getFecha() != null) {
            holder.textFecha.setText(registroAnecdotario.getFecha());
        }

        if (registroAnecdotario.getObservador() != null) {
            holder.textNombreNinho.setText(registroAnecdotario.getObservador());
        }

        if (registroAnecdotario.getActividadRealizada() != null) {
            holder.textActRealizada.setText(registroAnecdotario.getActividadRealizada());
        }

        if (registroAnecdotario.getConducta() != null) {
            holder.textConducta.setText(registroAnecdotario.getConducta());
        }

        if (registroAnecdotario.getComentario() != null) {
            holder.textComentario.setText(registroAnecdotario.getComentario());
        }

        if (registroAnecdotario.getRecomendacion() != null) {
            holder.textRecomendacion.setText(registroAnecdotario.getRecomendacion());
        }

        if (registroAnecdotario.getPeriodo() != null) {
            holder.textPeriodo.setText(registroAnecdotario.getPeriodo());
        }

    }

    @Override
    public int getItemCount() {
        return chatsList.size();
    }

    public class ViewHolderAdapter extends RecyclerView.ViewHolder {

        TextView textNombreNinho;
        TextView textFecha;
        TextView textObservador;
        TextView textActRealizada;
        TextView textConducta;
        TextView textComentario;
        TextView textRecomendacion;
        TextView textPeriodo;

        public ViewHolderAdapter(@NonNull View itemView) {
            super(itemView);
            textNombreNinho = itemView.findViewById(R.id.textNombreNinho);
            textFecha = itemView.findViewById(R.id.textFecha);
            textObservador = itemView.findViewById(R.id.textObservador);
            textActRealizada = itemView.findViewById(R.id.textActRealizada);
            textConducta = itemView.findViewById(R.id.textConducta);
            textComentario = itemView.findViewById(R.id.textComentario);
            textRecomendacion = itemView.findViewById(R.id.textRecomendacion);
            textPeriodo = itemView.findViewById(R.id.textPeriodo);


        }
    }

}
