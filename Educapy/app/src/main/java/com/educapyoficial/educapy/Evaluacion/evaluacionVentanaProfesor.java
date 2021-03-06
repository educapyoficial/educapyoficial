package com.educapyoficial.educapy.Evaluacion;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.educapyoficial.educapy.ListarEvaluacionAlumno;
import com.educapyoficial.educapy.MenuProfesores;
import com.educapyoficial.educapy.R;
import com.educapyoficial.educapy.menuadministrador;
import com.educapyoficial.educapy.models.EducapyCalificaciones;
import com.educapyoficial.educapy.models.EducapyModelUser;
import com.educapyoficial.educapy.providers.AuthProvider;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;

public class evaluacionVentanaProfesor extends AppCompatActivity {

    private List<EducapyModelUser> listAlumnos = new ArrayList<EducapyModelUser>();
    ArrayAdapter<EducapyModelUser> arrayAdapter;
    ProgressDialog cargando;
    EditText nomP, cajaCalificacion; //insertar datos
    FirebaseDatabase firebaseDatabase;
    ListView listV_personasR; //insertar datos
    DatabaseReference databaseReference;
    DatabaseReference reff;
    AlertDialog mDialog;
    AuthProvider mauthProvider;
    EducapyModelUser getFocusSelecteduser;
    long maxid = 0;
    int almacenapuntos;
    ArrayAdapter<String> mAdapter;
    Spinner spinnerGrupo;
    String grupoasignado;
    private CircleImageView mCircleImageNext;
    String almacenagkeR;
    private DatabaseReference mDatabase;
    Button mbtnIndicadores;
    SharedPreferences mPref;
    private String uidCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_ventana);
        mPref = getApplicationContext().getSharedPreferences("validadmiRT", MODE_PRIVATE);
        mauthProvider = new AuthProvider();
        spinnerGrupo = (Spinner) findViewById(R.id.spinnerGrupoCom);
        //mCircleImageNext = findViewById(R.id.circleImageNextCom);
        //  spinnerGrupo = (Spinner) findViewById(R.id.spinnerGrupoT);
        cargando = new ProgressDialog(this);
        cajaCalificacion = findViewById(R.id.textCorreoCom);  //bien
        nomP = findViewById(R.id.textInputNameCom); //bien
        //mbtnIndicadores = (Button) findViewById(R.id.btnIndicadores);
        //cajafiltra = findViewById(R.id.textentrada);
        reff = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients");
        mDialog = new SpotsDialog.Builder().setContext(evaluacionVentanaProfesor.this).setMessage("Espere Un Momento").build();
        getFocusSelecteduser = new EducapyModelUser();
        listV_personasR = findViewById(R.id.lv_datosPersonasRcom); //insertar datos
        inicializarFirebase(); //insertar datos

        Intent intent = getIntent();
        uidCurso = intent.getExtras().getString("uidCurso", "");

        listarDatos();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mAdapter = new ArrayAdapter<String>(evaluacionVentanaProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.calificacionesC));
        spinnerGrupo.setAdapter(mAdapter);
        spinnerGrupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) spinnerGrupo.getSelectedView()).setTextColor(Color.BLACK);
                String seleccionEdad = spinnerGrupo.getSelectedItem().toString();
                if (seleccionEdad.equals("A")) {
                    grupoasignado = "A";
                    cajaCalificacion.setText("A");
                    //    cajafiltra.setText(grupoasignado.toString());
                }
                if (seleccionEdad.equals("B")) {
                    grupoasignado = "B";
                    cajaCalificacion.setText("B");
                    //    cajafiltra.setText(grupoasignado.toString());
                }
                if (seleccionEdad.equals("C")) {
                    grupoasignado = "C";
                    cajaCalificacion.setText("C");
                    //    cajafiltra.setText(grupoasignado.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        listV_personasR.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getFocusSelecteduser = (EducapyModelUser) parent.getItemAtPosition(position);
                nomP.setText(getFocusSelecteduser.getNombre1R());
                //cajaCalificacion.setText(getFocusSelecteduser.getCalificacion());
                almacenagkeR = getFocusSelecteduser.getGkeR();
                Log.d("kimbo7", almacenagkeR);

                //Intent intent = new Intent(evaluacionVentanaProfesor.this, indicadoresActivityProfesor.class);
                Intent intent = new Intent(evaluacionVentanaProfesor.this, ListarEvaluacionAlumno.class);
                intent.putExtra("mandogkeR", almacenagkeR);
                intent.putExtra("mandoNombre", getFocusSelecteduser.getNombre1R());
                intent.putExtra("educapyModelUser", getFocusSelecteduser);
                intent.putExtra("profesor", 1);
                //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                //    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);


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

    public void actualizaCalificacion() { //este metodo actualiza un solo elemento o crea un nuevo elemento sin que se aya creado :)
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference scoreRef = rootRef.child("Calificaciones").child(almacenagkeR).child("calificacion");
        scoreRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                String calificacion = mutableData.getValue(String.class);

                calificacion = cajaCalificacion.getText().toString();
                if (calificacion == null) {
                    return Transaction.success(mutableData);

                }
                mutableData.setValue(calificacion);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
            }

        });
        //  mDatabase.child("Users").child("Clients").child(id).child("puntosTotales").setValue(+5);
        Map<String, Object> personmap = new HashMap<>();
        personmap.put("nombre1R", nomP.getText().toString());
        mDatabase.child("Calificaciones").child(almacenagkeR).updateChildren(personmap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(evaluacionVentanaProfesor.this, "Nombre Actualizado", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(evaluacionVentanaProfesor.this, "Fallo al Actualizar", Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(this, "Calificacion Actualizada", Toast.LENGTH_SHORT).show();
    }

    private void validacion() {
        String nombret = nomP.getText().toString();  //utilizo esta forma para obtener el valor de los campos y validar los campos vacios  atravez de la clase validacion
        String correoT = cajaCalificacion.getText().toString();
        //    String grupoT = cajapuntos.getText().toString();
        if (nombret.equals("")) {
            nomP.setError("Required");
        } else if (correoT.equals("")) {
            cajaCalificacion.setError("Required");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent intent = new Intent(evaluacionVentanaProfesor.this, MenuProfesores.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
//        startActivity(intent);
    }


    private void listarDatos() {
        databaseReference.child("Users").child("Clients").orderByChild("uidCurso").equalTo(uidCurso).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                listAlumnos.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    int count = 0;
                    maxid = (dataSnapshot.getChildrenCount());
                    EducapyModelUser p = objSnaptshot.getValue(EducapyModelUser.class);
                    listAlumnos.add(p);
                    arrayAdapter = new ArrayAdapter<EducapyModelUser>(evaluacionVentanaProfesor.this, android.R.layout.simple_list_item_1, listAlumnos);
                    listV_personasR.setAdapter(arrayAdapter);
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