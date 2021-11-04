package com.educapyoficial.educapy.Evaluacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.educapyoficial.educapy.models.EducapyModelUser;
import com.educapyoficial.educapy.R;
import com.educapyoficial.educapy.principal;
import com.educapyoficial.educapy.providers.AuthProvider;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;

public class evaluacionIndividual extends AppCompatActivity {

    private List<EducapyModelUser> listEspecialidad = new ArrayList<EducapyModelUser>();
    ArrayAdapter<EducapyModelUser> arrayAdapterEspecialidad;
    ProgressDialog cargando;
    EditText nomP, cajaCalificacion; //insertar datos
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    AlertDialog mDialog;
    AuthProvider mauthProvider;
    EducapyModelUser getFocusSelecteduser;
    private CircleImageView mCircleImageNext;
    String cajagkeR;
    private DatabaseReference mDatabase;

    TextView caja1,caja2,caja3,caja4,caja5,caja6,caja7,caja8,caja9,caja10,caja11,caja12,caja13,caja14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_individual);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mauthProvider = new AuthProvider();
        mCircleImageNext = findViewById(R.id.circleImageBackRTindivi);
        //  spinnerGrupo = (Spinner) findViewById(R.id.spinnerGrupoT);
        cargando = new ProgressDialog(this);
        cajaCalificacion = findViewById(R.id.textCorreoIndivi);  //bien
        caja1 = (TextView) findViewById(R.id.respuesta1);
        caja2 = (TextView) findViewById(R.id.respuesta2);
        caja3 = (TextView) findViewById(R.id.respuesta3);
        caja4 = (TextView) findViewById(R.id.respuesta4);
        caja5 = (TextView) findViewById(R.id.respuesta5);
        caja6 = (TextView) findViewById(R.id.respuesta6);
        caja7 = (TextView) findViewById(R.id.respuesta7);
        caja8 = (TextView) findViewById(R.id.respuesta8);
        caja9 = (TextView) findViewById(R.id.respuesta9);
        caja10 = (TextView) findViewById(R.id.respuesta10);
        caja11 = (TextView) findViewById(R.id.respuesta11);
        caja12 = (TextView) findViewById(R.id.respuesta12);
        caja13 = (TextView) findViewById(R.id.respuesta13);
        caja14 = (TextView) findViewById(R.id.respuesta14);

        nomP = findViewById(R.id.textInputNameIndivi); //bien
        //cajafiltra = findViewById(R.id.textentrada);
        mDialog = new SpotsDialog.Builder().setContext(evaluacionIndividual.this).setMessage("Espere Un Momento").build();
        getFocusSelecteduser = new EducapyModelUser();
        inicializarFirebase(); //insertar datos

        mCircleImageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(evaluacionIndividual.this, principal.class));

            }
        });

        try {
            Intent i = getIntent();
            cajagkeR = i.getStringExtra("obtengogkeR");
            obtenerinfoUser2();
            obtenerinfoUser3();
         //   Toast.makeText(this, ""+cajagkeR, Toast.LENGTH_SHORT).show();

        }catch (Exception e)
        {
            Toast.makeText(this, "No ha llenado Bitacora", Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(evaluacionIndividual.this, principal.class));
            //  stopService(new Intent(Principal.this, contructorMusica.class)); //detener musica de fondo
            finish();
        }




    }

    private void obtenerinfoUser2() {
       mDatabase.child("Calificaciones").child(cajagkeR).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                    try {
                        nomP.setText(dataSnapshot.child("nombre1R").getValue().toString());
                        cajaCalificacion.setText(dataSnapshot.child("calificacion").getValue().toString());
                    }catch (Exception e)
                    {
                        Toast.makeText(evaluacionIndividual.this, "No tiene calificacion asignada", Toast.LENGTH_SHORT).show();
                        //Intent myIntent = new Intent(evaluacionIndividual.this, principal.class);
                        //  stopService(new Intent(Principal.this, contructorMusica.class)); //detener musica de fondo
                        //startActivity(myIntent);
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void obtenerinfoUser3() {
        mDatabase.child("Users").child("Clients").child(cajagkeR).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

                    try {
                        caja1.setText(dataSnapshot.child("indicador1").getValue().toString());
                        caja2.setText(dataSnapshot.child("indicador2").getValue().toString());
                        caja3.setText(dataSnapshot.child("indicador3").getValue().toString());
                        caja4.setText(dataSnapshot.child("indicador4").getValue().toString());
                        caja5.setText(dataSnapshot.child("indicador5").getValue().toString());
                        caja6.setText(dataSnapshot.child("indicador6").getValue().toString());
                        caja7.setText(dataSnapshot.child("indicador7").getValue().toString());
                        caja8.setText(dataSnapshot.child("indicador8").getValue().toString());
                        caja9.setText(dataSnapshot.child("indicador9").getValue().toString());
                        caja10.setText(dataSnapshot.child("indicador10").getValue().toString());
                        caja11.setText(dataSnapshot.child("indicador11").getValue().toString());
                        caja12.setText(dataSnapshot.child("indicador12").getValue().toString());
                        caja13.setText(dataSnapshot.child("indicador13").getValue().toString());
                        caja14.setText(dataSnapshot.child("indicador14").getValue().toString());

                    }catch (Exception e)
                    {
                        Toast.makeText(evaluacionIndividual.this, "no ahy datos", Toast.LENGTH_SHORT).show();
                    }

                    /*
                    try {

                        /*





                         */

                    /*
                    }catch (Exception e)
                    {
                        Toast.makeText(evaluacionIndividual.this, "No tiene indicadores asignados", Toast.LENGTH_SHORT).show();
                        //    Intent myIntent = new Intent(evaluacionIndividual.this, principal.class);
                        //  stopService(new Intent(Principal.this, contructorMusica.class)); //detener musica de fondo
                        //    startActivity(myIntent);
                    }


                     */

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Intent intent = new Intent(evaluacionIndividual.this, principal.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
        //startActivity(intent);
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}