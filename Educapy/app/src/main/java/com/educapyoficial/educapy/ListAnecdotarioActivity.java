package com.educapyoficial.educapy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.educapyoficial.educapy.adapters.AdapterAnecdotario;
import com.educapyoficial.educapy.models.EducapyModelUser;
import com.educapyoficial.educapy.models.RegistroAnecdotario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListAnecdotarioActivity extends AppCompatActivity {

    RecyclerView lv_datosPersonasRnot;
    AdapterAnecdotario adapterAnecdotario;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String uidAlumno;
    List<RegistroAnecdotario> registroAnecdotarioList;
    Button btnAgregarRegistro;
    EducapyModelUser educapyModelUser;
    private boolean isProfesor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_anecdotario);

        Intent intent = getIntent();
        educapyModelUser = (EducapyModelUser) intent.getSerializableExtra("educapyModelUser");

        lv_datosPersonasRnot = findViewById(R.id.lv_datosPersonasRnot);
        lv_datosPersonasRnot.setLayoutManager(new LinearLayoutManager(this));
        lv_datosPersonasRnot.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        lv_datosPersonasRnot.setHasFixedSize(true);

        registroAnecdotarioList = new ArrayList<>();

        isProfesor = intent.getIntExtra("profesor", 0) == 1;
        btnAgregarRegistro = findViewById(R.id.btnAgregarRegistro);

        if (!isProfesor){
            btnAgregarRegistro.setVisibility(View.GONE);
        }

        btnAgregarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ListAnecdotarioActivity.this, RegistrarAnectotarioAlumno.class);
                intent1.putExtra("educapyModelUser", educapyModelUser);
                startActivity(intent1);
            }
        });

        inicializarFirebase();
        listarDatos();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listarDatos();
    }

    private void listarDatos() {
        databaseReference.child("RegistroAnecdotario").child("id").orderByChild("uidAlumno").equalTo(educapyModelUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                registroAnecdotarioList.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    RegistroAnecdotario p = objSnaptshot.getValue(RegistroAnecdotario.class);
                    registroAnecdotarioList.add(p);

                }
                adapterAnecdotario = new AdapterAnecdotario(registroAnecdotarioList, getApplicationContext());
                lv_datosPersonasRnot.setAdapter(adapterAnecdotario);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

}