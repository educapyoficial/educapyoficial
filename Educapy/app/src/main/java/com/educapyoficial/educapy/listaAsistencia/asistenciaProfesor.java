package com.educapyoficial.educapy.listaAsistencia;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.educapyoficial.educapy.R;
import com.educapyoficial.educapy.menuadministrador;
import com.educapyoficial.educapy.models.constructoAsistencia;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;

public class asistenciaProfesor extends AppCompatActivity {


    private EditText etFecha;

    // Guardar el último año, mes y día del mes
    private int ultimoAnio, ultimoMes, ultimoDiaDelMes;

    // Crear un listener del datepicker;
    private DatePickerDialog.OnDateSetListener listenerDeDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int mes, int diaDelMes) {
            // Esto se llama cuando seleccionan una fecha. Nos pasa la vista, pero más importante, nos pasa:
            // El año, el mes y el día del mes. Es lo que necesitamos para saber la fecha completa


            // Refrescamos las globales
            ultimoAnio = year;
            ultimoMes = mes;
            ultimoDiaDelMes = diaDelMes;

            // Y refrescamos la fecha
            refrescarFechaEnEditText();

        }
    };

    public void refrescarFechaEnEditText() {
        // Formateamos la fecha pero podríamos hacer cualquier otra cosa ;)
        String fecha = String.format(Locale.getDefault(), "%02d-%02d-%02d", ultimoDiaDelMes,ultimoMes+1,ultimoAnio);

        // La ponemos en el editText
        etFecha.setText(fecha);
    }


    DatePickerDialog.OnDateSetListener setListener;
    int year,month,day;
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
    constructoAsistencia objetoconstructorCalendario;
    long maxid = 0;
    int almacenapuntos;
    ArrayAdapter<String> mAdapter;
    Spinner spinnerGrupo;
    String grupoasignado;
    private CircleImageView mCircleImageAdd,mCircleRegistroCalendar,mCircleUpdate,mCircleDelete;
    String almacenagkeR;
    private DatabaseReference mDatabase;
    String tipodeEventoR;
    EditText cajatipoevento;
    String gkeyRegister;
    SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia);
        mPref = getApplicationContext().getSharedPreferences("validadmiRT", MODE_PRIVATE);
        etFecha = findViewById(R.id.textfechaRegistro);

        // Poner último año, mes y día a la fecha de hoy
        final Calendar calendario = Calendar.getInstance();
        ultimoAnio = calendario.get(Calendar.YEAR);
        ultimoMes = calendario.get(Calendar.MONTH);
        ultimoDiaDelMes = calendario.get(Calendar.DAY_OF_MONTH);

        // Refrescar la fecha en el EditText
        refrescarFechaEnEditText();

        mauthProvider = new AuthProvider();
        cajatipoevento = findViewById(R.id.solucionkeyR);
        spinnerGrupo = (Spinner) findViewById(R.id.spinnerGrupoRegistro);
        mCircleImageAdd = findViewById(R.id.circleImageNextRegistro);
        mCircleRegistroCalendar = findViewById(R.id.circleImageCalendar);
        mCircleUpdate = findViewById(R.id.circleImageNextComupdate);
        mCircleDelete = findViewById(R.id.circleImageNextComdelete);
        //  spinnerGrupo = (Spinner) findViewById(R.id.spinnerGrupoT);
        cargando = new ProgressDialog(this);
        cajaFechaEvento = findViewById(R.id.textfechaRegistro);  //bien
        nomP = findViewById(R.id.textInputNameComRegistro); //bien
        //cajafiltra = findViewById(R.id.textentrada);
        reff = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients");
        mDialog = new SpotsDialog.Builder().setContext(asistenciaProfesor.this).setMessage("Espere Un Momento").build();
        objetoconstructorCalendario = new constructoAsistencia();
        listV_personasR = findViewById(R.id.lv_datosPersonasRegistro); //insertar datos
        inicializarFirebase(); //insertar datos
        listarDatos();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mCircleDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = nomP.getText().toString();
                String editextfechaevento = cajaFechaEvento.getText().toString();
                String tipodeEventoRR = cajatipoevento.getText().toString();

                if (nombre.isEmpty()||editextfechaevento.isEmpty()){
                    validacion();
                }
                else {
                    constructoAsistencia p = new constructoAsistencia();
                    p.setUid(objetoconstructorCalendario.getUid());
                    databaseReference.child("Asistencia").child(cajaFechaEvento.getText().toString()).child(almacenagkeR).removeValue();
                    Toast.makeText(asistenciaProfesor.this,"Eliminado", Toast.LENGTH_LONG).show();
                    limpiarCajas();
                  //  Intent intent = new Intent(asistencia.this, menuadministrador.class);
                   // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
                  //  startActivity(intent);
                }
            }
        });

        mCircleUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nomP.getText().toString();
                String editextfechaevento = cajaFechaEvento.getText().toString();
                String tipodeEventoRR = cajatipoevento.getText().toString();

                if (nombre.isEmpty()||tipodeEventoRR.isEmpty()||editextfechaevento.isEmpty()){
                    validacion();
                }
                else {
                    constructoAsistencia p = new constructoAsistencia();
                   // p.setUid(constructoAsistencia.getuid());
                    p.setNombre1R(nomP.getText().toString().trim());
                    p.setParametroAsistencia(cajatipoevento.getText().toString().trim());
                    p.setUid(almacenagkeR);
                    p.setFecha(cajaFechaEvento.getText().toString());
                   // p.setFechaEvento(tipodeEventoR.trim());
                  //  p.setFotourl(obtengoUrlFoto);
                    databaseReference.child("Asistencia").child(cajaFechaEvento.getText().toString()).child(almacenagkeR).setValue(p);
                    Toast.makeText(asistenciaProfesor.this,"Actualizado", Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }
            }
        });

        mCircleImageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = nomP.getText().toString();
                String editextfechaevento = cajaFechaEvento.getText().toString();
                String tipodeEventoRR = cajatipoevento.getText().toString();

                if (nombre.isEmpty()||tipodeEventoRR.isEmpty()){
                    validacion();
                    Toast.makeText(asistenciaProfesor.this, "no validado", Toast.LENGTH_SHORT).show();
                }
                else {
                    //   Toast.makeText(CalendarAdd.this, "validado", Toast.LENGTH_SHORT).show();

                    constructoAsistencia p = new constructoAsistencia();
                    p.setUid(UUID.randomUUID().toString());
                    p.setNombre1R(nombre);
                    p.setParametroAsistencia(cajatipoevento.getText().toString());
                  //  p.setUid(almacenagkeR);
                    p.setFecha(cajaFechaEvento.getText().toString());
                 //   p.setTipodeEvento(cajatipoevento.getText().toString());
                    databaseReference.child("Asistencia").child(cajaFechaEvento.getText().toString()).child(almacenagkeR).setValue(p);
                    // Toast.makeText(this, "Agregado", Toast.LENGTH_LONG).show();



                    //**************************






                    /*
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("AsistenciaId").child(cajaFechaEvento.getText().toString());
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot .exists())
                            {
                                Toast.makeText(asistencia.this, "Si existe nodo", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(asistencia.this, "No existe nodo", Toast.LENGTH_SHORT).show();
                            }
                            //existe

                            //no existe

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                     */




                    Map<String, Object> personmap = new HashMap<>();

                    personmap.put("fechaId", cajaFechaEvento.getText().toString());

                    mDatabase.child("AsistenciaId").child(cajaFechaEvento.getText().toString()).updateChildren(personmap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(asistenciaProfesor.this, "Dato Actualizado", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(asistenciaProfesor.this, "Fallo al Actualizar", Toast.LENGTH_SHORT).show();
                        }
                    });


                            //*****************************

                            // Toast.makeText(asistencia.this, "Agregado", Toast.LENGTH_SHORT).show();
                            limpiarCajas();





                }

            }
        });

        mCircleRegistroCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialogoFecha = new DatePickerDialog(asistenciaProfesor.this, listenerDeDatePicker, ultimoAnio, ultimoMes, ultimoDiaDelMes);
                //Mostrar
                dialogoFecha.show();

            }
        });

        mAdapter = new ArrayAdapter<String>(asistenciaProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.AsistenciaC));
        spinnerGrupo.setAdapter(mAdapter);
        spinnerGrupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) spinnerGrupo.getSelectedView()).setTextColor(Color.BLACK);
                String seleccionEdad = spinnerGrupo.getSelectedItem().toString();
                if (seleccionEdad.equals("Si")) {
                    tipodeEventoR = "Si";
                    cajatipoevento.setText("Si");
                    //    cajafiltra.setText(grupoasignado.toString());
                }
                if (seleccionEdad.equals("No")) {
                    tipodeEventoR = "No";
                    cajatipoevento.setText("No");
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
                objetoconstructorCalendario = (constructoAsistencia) parent.getItemAtPosition(position);
                nomP.setText(objetoconstructorCalendario.getNombre1R());
              //  cajaCalificacion.setText(getFocusSelecteduser.getCalificacion());
                almacenagkeR = objetoconstructorCalendario.getGkeR();
                Log.d("kimbo7",almacenagkeR);
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


    private void limpiarCajas() {
        nomP.setText("");
       // cajaFechaEvento.setText("");
        grupoasignado = "";
    }


    public void actualizaCalificacion() { //este metodo actualiza un solo elemento o crea un nuevo elemento sin que se aya creado :)
        constructoAsistencia p = new constructoAsistencia();
        p.setUid(UUID.randomUUID().toString());
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference scoreRef = rootRef.child("Asistencia").child(cajaFechaEvento.getText().toString()).child(p.getUid());
        scoreRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                String calificacion = mutableData.getValue(String.class);

                calificacion = cajatipoevento.getText().toString();
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
                Toast.makeText(asistenciaProfesor.this, "Nombre Actualizado", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(asistenciaProfesor.this, "Fallo al Actualizar", Toast.LENGTH_SHORT).show();
            }
        });


        Toast.makeText(this, "Registro Actualizado", Toast.LENGTH_SHORT).show();
    }

    private void validacion() {
        String nombret = nomP.getText().toString();  //utilizo esta forma para obtener el valor de los campos y validar los campos vacios  atravez de la clase validacion
        String correoT = cajaFechaEvento.getText().toString();
        String tipodeEventoT = cajatipoevento.getText().toString();
        if (nombret.equals("")) {
            nomP.setError("Required");
        } else if (correoT.equals("")) {
            cajaFechaEvento.setError("Required");
        } else if (tipodeEventoT.equals("")) {
            Toast.makeText(this, "Seleccionar Fecha de Asistencia", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(asistenciaProfesor.this, selectorAsistenciaProfesor.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
        startActivity(intent);
    }


    private void listarDatos() {
        databaseReference.child("Users").child("Clients").orderByChild("uidProfesor").equalTo(mPref.getString("uidProfesor", "")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                listEspecialidad.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    int count = 0;
                    maxid = (dataSnapshot.getChildrenCount());
                    constructoAsistencia p = objSnaptshot.getValue(constructoAsistencia.class);
                    listEspecialidad.add(p);
                    arrayAdapterEspecialidad = new ArrayAdapter<constructoAsistencia>(asistenciaProfesor.this, android.R.layout.simple_list_item_1, listEspecialidad);
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