package com.educapyoficial.educapy.listaAsistencia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.educapyoficial.educapy.R;
import com.educapyoficial.educapy.menuadministrador;
import com.educapyoficial.educapy.models.constructoAsistenciaRevision;
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

public class revisaAsistenciaAlumno extends AppCompatActivity {

    private List<constructoAsistenciaRevision> listEspecialidad = new ArrayList<constructoAsistenciaRevision>();
    ArrayAdapter<constructoAsistenciaRevision> arrayAdapterEspecialidad;
    ProgressDialog cargando;
    EditText nomP, cajaCalificacion,cajaFechaEvento; //insertar datos
    FirebaseDatabase firebaseDatabase;
    ListView listV_personasR; //insertar datos
    DatabaseReference databaseReference;
    DatabaseReference reff;
    AlertDialog mDialog;
    AuthProvider mauthProvider;
    constructoAsistenciaRevision constructoAsistenciaRevision;
    long maxid = 0;
    int almacenapuntos;
    ArrayAdapter<String> mAdapter;
    Spinner spinnerGrupo;
    String grupoasignado;
    private CircleImageView mCircleImageNext;
    String almacenagkeR;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revisa_asistencia_alumno);

        mauthProvider = new AuthProvider();
        mCircleImageNext = findViewById(R.id.circleImageNextCom);
        //  spinnerGrupo = (Spinner) findViewById(R.id.spinnerGrupoT);
        cargando = new ProgressDialog(this);
        cajaFechaEvento = findViewById(R.id.textInputNameComFecha);  //bien
        reff = FirebaseDatabase.getInstance().getReference().child("AsistenciaId");
        mDialog = new SpotsDialog.Builder().setContext(revisaAsistenciaAlumno.this).setMessage("Espere Un Momento").build();
        constructoAsistenciaRevision = new constructoAsistenciaRevision();
        listV_personasR = findViewById(R.id.lv_datosPersonasRcom); //insertar datos
        constructoAsistenciaRevision = new constructoAsistenciaRevision();
        listV_personasR = findViewById(R.id.lv_datosPersonasRcomRevisar); //insertar datos
        inicializarFirebase(); //insertar datos
        listarDatos();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        mCircleImageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(revisaAsistenciaAlumno.this, revisaAsistenciaFinalAlumno.class);
                myIntent.putExtra("obtengoFecha", cajaFechaEvento.getText().toString());
                //  stopService(new Intent(Principal.this, contructorMusica.class)); //detener musica de fondo
                startActivity(myIntent);

            }
        });

        listV_personasR.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                constructoAsistenciaRevision = (constructoAsistenciaRevision) parent.getItemAtPosition(position);
                //   nomP.setText(constructoAsistenciaRevision.getNombre1R());
                cajaFechaEvento.setText(constructoAsistenciaRevision.getFechaId());
                //   almacenagkeR = constructoAsistenciaRevision.getGkeR();
                //  Log.d("kimbo7",almacenagkeR);
                //  almacenapuntos = getFocusSelecteduser.getPuntosTotales();
                // cajapuntos.setText(String.valueOf(almacenapuntos));
            }
        });
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    maxid = (dataSnapshot.getChildrenCount());
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
        Intent intent = new Intent(revisaAsistenciaAlumno.this, principal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
        startActivity(intent);
    }

    private void listarDatos() {
        databaseReference.child("AsistenciaId").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                listEspecialidad.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    int count = 0;
                    maxid = (dataSnapshot.getChildrenCount());
                    constructoAsistenciaRevision p = objSnaptshot.getValue(constructoAsistenciaRevision.class);
                    listEspecialidad.add(p);
                    arrayAdapterEspecialidad = new ArrayAdapter<constructoAsistenciaRevision>(revisaAsistenciaAlumno.this, android.R.layout.simple_list_item_1, listEspecialidad);
                    listV_personasR.setAdapter(arrayAdapterEspecialidad);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}
