package com.educapyoficial.educapy.models;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.educapyoficial.educapy.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context, context2;
    ArrayList<constructorCalendario> profiles;
    int incrementa;

    public MyAdapter(Context c, ArrayList<constructorCalendario> p) {
        context = c;
        profiles = p;
        incrementa = 0;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        incrementa = incrementa + 1;

        Log.d("rob", String.valueOf(incrementa));

        holder.nombreEventoAdapter.setText(profiles.get(position).getNombreEvento().toUpperCase()); //toUpperCase convierte a mayuscula
        holder.fechaEventoAdapter.setText(profiles.get(position).getFechaEvento());
      //  holder.puntosTotalesRT.setText("Puntos " + String.valueOf(profiles.get(position).getPuntosTotales()));
        holder.tipodeEventoAdapter.setText(profiles.get(position).getTipodeEvento());
     //   holder.txtcajacount.setText(String.valueOf(incrementa));
     //   holder.pais.setText(profiles.get(position).getPais());
        //  Toast.makeText(context, ""+, Toast.LENGTH_SHORT).show();
        Picasso.with(context).load(profiles.get(position).getFotourl()).into(holder.fotourl);

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombreEventoAdapter, fechaEventoAdapter, tipodeEventoAdapter;
        ImageView fotourl;
        Button btn;

        public MyViewHolder(View itemView) {
            super(itemView);
            context2 = itemView.getContext(); //para poder tener acceso a los parametro
            nombreEventoAdapter = (TextView) itemView.findViewById(R.id.name);
            fechaEventoAdapter = (TextView) itemView.findViewById(R.id.txtfecha);
            tipodeEventoAdapter = (TextView) itemView.findViewById(R.id.tipodeEventoCard);
            fotourl = (ImageView) itemView.findViewById(R.id.profilePicCard);
          //  txtcajacount = (TextView) itemView.findViewById(R.id.txtcountRT);
          //  pais = (TextView) itemView.findViewById(R.id.txtPaisCard);
        }
    }
}
