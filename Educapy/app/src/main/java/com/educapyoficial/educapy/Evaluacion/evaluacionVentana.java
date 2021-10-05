package com.educapyoficial.educapy.Evaluacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
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

import com.educapyoficial.educapy.Galeria.primerActivity;
import com.educapyoficial.educapy.MainActivity;
import com.educapyoficial.educapy.R;
import com.educapyoficial.educapy.RegisterClientActivity4;
import com.educapyoficial.educapy.administradorGaleria;
import com.educapyoficial.educapy.menuadministrador;
import com.educapyoficial.educapy.models.EducapyCalificaciones;
import com.educapyoficial.educapy.principal;
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

public class evaluacionVentana extends AppCompatActivity {

    private List<EducapyCalificaciones> listEspecialidad = new ArrayList<EducapyCalificaciones>();
    ArrayAdapter<EducapyCalificaciones> arrayAdapterEspecialidad;
    ProgressDialog cargando;
    EditText nomP, cajaCalificacion; //insertar datos
    FirebaseDatabase firebaseDatabase;
    ListView listV_personasR; //insertar datos
    DatabaseReference databaseReference;
    DatabaseReference reff;
    AlertDialog mDialog;
    AuthProvider mauthProvider;
    EducapyCalificaciones getFocusSelecteduser;
    long maxid = 0;
    int almacenapuntos;
    ArrayAdapter<String> mAdapter;
    Spinner spinnerGrupo;
    String grupoasignado;
    private CircleImageView mCircleImageNext;
    String almacenagkeR;
    private DatabaseReference mDatabase;
    Button mbtnIndicadores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_ventana);

        mauthProvider = new AuthProvider();
        spinnerGrupo = (Spinner) findViewById(R.id.spinnerGrupoCom);
        mCircleImageNext = findViewById(R.id.circleImageNextCom);
        //  spinnerGrupo = (Spinner) findViewById(R.id.spinnerGrupoT);
        cargando = new ProgressDialog(this);
        cajaCalificacion = findViewById(R.id.textCorreoCom);  //bien
        nomP = findViewById(R.id.textInputNameCom); //bien
        mbtnIndicadores = (Button) findViewById(R.id.btnIndicadores);
        //cajafiltra = findViewById(R.id.textentrada);
        reff = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients");
        mDialog = new SpotsDialog.Builder().setContext(evaluacionVentana.this).setMessage("Espere Un Momento").build();
        getFocusSelecteduser = new EducapyCalificaciones();
        listV_personasR = findViewById(R.id.lv_datosPersonasRcom); //insertar datos
        inicializarFirebase(); //insertar datos
        listarDatos();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mCircleImageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actualizaCalificacion();

            }
        });


        mbtnIndicadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(almacenagkeR==null)
                {
                    Toast.makeText(evaluacionVentana.this, "Seleccione usuario para cargar galeria", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(evaluacionVentana.this, indicadoresActivity.class);
                    intent.putExtra("mandogkeR", almacenagkeR);
                    intent.putExtra("mandoNombre",nomP.getText().toString() );
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    //    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });

        mAdapter = new ArrayAdapter<String>(evaluacionVentana.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.calificacionesC));
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
                getFocusSelecteduser = (EducapyCalificaciones) parent.getItemAtPosition(position);
                nomP.setText(getFocusSelecteduser.getNombre1R());
                cajaCalificacion.setText(getFocusSelecteduser.getCalificacion());
                almacenagkeR = getFocusSelecteduser.getGkeR();
                Log.d("kimbo7",almacenagkeR);
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
                Toast.makeText(evaluacionVentana.this, "Nombre Actualizado", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(evaluacionVentana.this, "Fallo al Actualizar", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(evaluacionVentana.this, menuadministrador.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
        startActivity(intent);
    }


    private void listarDatos() {
        databaseReference.child("Users").child("Clients").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                listEspecialidad.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    int count = 0;
                    maxid = (dataSnapshot.getChildrenCount());
                    EducapyCalificaciones p = objSnaptshot.getValue(EducapyCalificaciones.class);
                    listEspecialidad.add(p);
                    arrayAdapterEspecialidad = new ArrayAdapter<EducapyCalificaciones>(evaluacionVentana.this, android.R.layout.simple_list_item_1, listEspecialidad);
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