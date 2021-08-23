package com.educapyoficial.educapy.listaAsistencia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.educapyoficial.educapy.R;
import com.educapyoficial.educapy.videoUserGalery;

import de.hdodenhof.circleimageview.CircleImageView;

public class selectorAsistencia extends AppCompatActivity {

    CardView tarjeta1, tarjeta2;
    private CircleImageView mCircleImageBackR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector_asistencia);

        mCircleImageBackR = findViewById(R.id.circleImageBackT);
        tarjeta1 = findViewById(R.id.Card1galeria);
        tarjeta2 = findViewById(R.id.Card2video);


        tarjeta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(selectorAsistencia.this, asistencia.class);
             //   intent.putExtra("mandogkeR", jalogkeR);
                //  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
                startActivity(intent);
            }
        });


        tarjeta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(selectorAsistencia.this, revisarAsistencia.class);
             //   myIntent.putExtra("obtengogkeR", jalogkeR);
                //  stopService(new Intent(Principal.this, contructorMusica.class)); //detener musica de fondo
                startActivity(myIntent);

                // Toast.makeText(menuadministrador.this, "Se esta construyendo esta parte", Toast.LENGTH_SHORT).show();
            }
        });
    }
}