package com.educapyoficial.educapy.listaAsistencia;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.educapyoficial.educapy.R;
import com.educapyoficial.educapy.models.constructoAsistencia;
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

public class revisaAsistenciaFinalProfesor extends AppCompatActivity {

    private List<constructoAsistencia> listEspecialidad = new ArrayList<constructoAsistencia>();
    ArrayAdapter<constructoAsistencia> arrayAdapterEspecialidad;
    ProgressDialog cargando;
    EditText nomP, cajaCalificacion,cajaFechaEvento; //insertar datos
    FirebaseDatabase firebaseDatabase;
    ListView listV_personasR; //insertar datos
    DatabaseReference databaseReference;
    DatabaseReference reff;
    AlertDialog mDialog;
    AuthProvider mauthProvider;
    constructoAsistencia constructoAsistencia;
    long maxid = 0;
    int almacenapuntos;
    ArrayAdapter<String> mAdapter;
    Spinner spinnerGrupo;
    String grupoasignado;
    private CircleImageView mCircleImageNext;
    String almacenagkeR;
    private DatabaseReference mDatabase;
    String jaloFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revisa_asistencia_final);

        Intent i = getIntent();
        jaloFecha = i.getStringExtra("obtengoFecha");
        Log.d("kimbo12",jaloFecha);

        mauthProvider = new AuthProvider();
        mCircleImageNext = findViewById(R.id.circleImageNextCom);
        //  spinnerGrupo = (Spinner) findViewById(R.id.spinnerGrupoT);
        cargando = new ProgressDialog(this);
        cajaFechaEvento = findViewById(R.id.textInputNameComFecha);  //bien
        reff = FirebaseDatabase.getInstance().getReference().child("AsistenciaId");
        mDialog = new SpotsDialog.Builder().setContext(revisaAsistenciaFinalProfesor.this).setMessage("Espere Un Momento").build();
        constructoAsistencia = new constructoAsistencia();
        listV_personasR = findViewById(R.id.lv_datosPersonasRcom); //insertar datos
        constructoAsistencia = new constructoAsistencia();
        listV_personasR = findViewById(R.id.lv_datosPersonasRcomRevisar); //insertar datos
        inicializarFirebase(); //insertar datos
        listarDatos();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        mCircleImageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //    actualizaCalificacion();

            }
        });

        listV_personasR.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                constructoAsistencia = (constructoAsistencia) parent.getItemAtPosition(position);
                //   nomP.setText(constructoAsistencia.getNombre1R());
                cajaFechaEvento.setText(constructoAsistencia.getParametroAsistencia());
                //   almacenagkeR = constructoAsistencia.getGkeR();
                //  Log.d("kimbo7",almacenagkeR);
                //  almacenapuntos = getFocusSelecteduser.getPuntosTotales();
                // cajapuntos.setText(String.valueOf(almacenapuntos));
            }
        });
        reff.addValueEventListener(new ValueEventListener() {
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


    private void listarDatos() {
        databaseReference.child("Asistencia").child(jaloFecha).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                listEspecialidad.clear();
                Toast.makeText(revisaAsistenciaFinalProfesor.this, jaloFecha.toString(), Toast.LENGTH_SHORT).show();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    int count = 0;
                    maxid = (dataSnapshot.getChildrenCount());
                    constructoAsistencia p = objSnaptshot.getValue(constructoAsistencia.class);
                    listEspecialidad.add(p);
                    arrayAdapterEspecialidad = new ArrayAdapter<constructoAsistencia>(revisaAsistenciaFinalProfesor.this, android.R.layout.simple_list_item_1, listEspecialidad);
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