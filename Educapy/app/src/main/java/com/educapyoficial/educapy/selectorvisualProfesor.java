package com.educapyoficial.educapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import de.hdodenhof.circleimageview.CircleImageView;

public class selectorvisualProfesor extends AppCompatActivity {

    CardView tarjeta1, tarjeta2;
    TextView texttitulo, text1, text2;
    ImageView imagenTitulo, imagen1, imagen2;
    private CircleImageView mCircleImageBackR;
 //   String jalogkeR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectorvisual);


        mCircleImageBackR = findViewById(R.id.circleImageBackT);
        imagen1 = findViewById(R.id.img1);
        imagen2 = findViewById(R.id.img3);
        texttitulo = findViewById(R.id.txttitulo);
        imagenTitulo = findViewById(R.id.imgtitulo);
        tarjeta1 = findViewById(R.id.Card1galeria);
        tarjeta2 = findViewById(R.id.Card2video);

        mCircleImageBackR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(selectorvisualProfesor.this, MenuProfesores.class));
                finish();
            }
        });

        tarjeta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(selectorvisualProfesor.this, administradorGaleria.class);
              //  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
                startActivity(intent);
            }
        });


        tarjeta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(selectorvisualProfesor.this, addVideoActivity.class);
           //     myIntent.putExtra("obtengogkeR", jalogkeR);
                //  stopService(new Intent(Principal.this, contructorMusica.class)); //detener musica de fondo
                startActivity(myIntent);

                // Toast.makeText(menuadministrador.this, "Se esta construyendo esta parte", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(selectorvisualProfesor.this, MenuProfesores.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
        startActivity(intent);
    }

}