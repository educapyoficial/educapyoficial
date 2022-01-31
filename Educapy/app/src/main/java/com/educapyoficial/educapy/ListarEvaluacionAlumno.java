package com.educapyoficial.educapy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.educapyoficial.educapy.Evaluacion.evaluacionIndividual;
import com.educapyoficial.educapy.Evaluacion.evaluacionVentanaProfesor;
import com.educapyoficial.educapy.Evaluacion.indicadoresActivityProfesor;
import com.educapyoficial.educapy.models.EducapyModelUser;
import com.educapyoficial.educapy.models.EvaluacionIndicadores;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListarEvaluacionAlumno extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    EducapyModelUser educapyModelUser;

    private List<EvaluacionIndicadores> listEvaluaciones = new ArrayList<>();
    ArrayAdapter<EvaluacionIndicadores> arrayAdapter;
    ListView listViewEvaluaciones;

    TextInputEditText textInputNameCom;

    boolean isProfesor = false;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_evaluacion_alumno);

        Intent intent = getIntent();
        educapyModelUser = (EducapyModelUser) intent.getSerializableExtra("educapyModelUser");
        isProfesor = intent.getIntExtra("profesor", 0) == 1;

        listViewEvaluaciones = findViewById(R.id.lv_datosPersonasRcom);
        textInputNameCom = findViewById(R.id.textInputNameCom);
        textInputNameCom.setText(educapyModelUser.getNombre1R());

        inicializarFirebase();

        listarDatos();


        listViewEvaluaciones.setOnItemClickListener((parent, view, position, id) -> {
            EvaluacionIndicadores p = (EvaluacionIndicadores) parent.getItemAtPosition(position);
            Intent intent1 = new Intent(ListarEvaluacionAlumno.this, evaluacionIndividual.class);
            intent1.putExtra("evaluacionIndicadores", p);
            intent1.putExtra("educapyModelUser", educapyModelUser);
            startActivity(intent1);

        });

        btnAgregar = findViewById(R.id.btnAgregar);
        if (!isProfesor) {
            btnAgregar.setVisibility(View.GONE);
        }

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ListarEvaluacionAlumno.this, indicadoresActivityProfesor.class);
                intent2.putExtra("educapyModelUser", educapyModelUser);
                intent2.putExtra("profesor", 1);
                startActivity(intent2);
            }
        });


    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    private void listarDatos() {
        databaseReference.child("EvaluacionIndicadores").child("id").orderByChild("uidAlumno").equalTo(educapyModelUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                listEvaluaciones.clear();
                int band = 1;
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    EvaluacionIndicadores p = objSnaptshot.getValue(EvaluacionIndicadores.class);
                    p.setNroEvaluacion(band);
                    band++;
                    listEvaluaciones.add(p);
                    arrayAdapter = new ArrayAdapter<>(ListarEvaluacionAlumno.this, android.R.layout.simple_list_item_1, listEvaluaciones);
                    listViewEvaluaciones.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


}