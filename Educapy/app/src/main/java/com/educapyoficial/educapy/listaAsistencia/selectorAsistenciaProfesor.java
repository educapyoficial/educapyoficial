package com.educapyoficial.educapy.listaAsistencia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.educapyoficial.educapy.MenuProfesores;
import com.educapyoficial.educapy.R;
import com.educapyoficial.educapy.registroProfesores;

import de.hdodenhof.circleimageview.CircleImageView;

public class selectorAsistenciaProfesor extends AppCompatActivity {

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
                Intent intent = new Intent(selectorAsistenciaProfesor.this, asistenciaProfesor.class);
             //   intent.putExtra("mandogkeR", jalogkeR);
                //  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
                startActivity(intent);
            }
        });


        tarjeta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(selectorAsistenciaProfesor.this, revisarAsistenciaProfesor.class);
             //   myIntent.putExtra("obtengogkeR", jalogkeR);
                //  stopService(new Intent(Principal.this, contructorMusica.class)); //detener musica de fondo
                startActivity(myIntent);

                // Toast.makeText(menuadministrador.this, "Se esta construyendo esta parte", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(selectorAsistenciaProfesor.this, MenuProfesores.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
        startActivity(intent);
    }
}