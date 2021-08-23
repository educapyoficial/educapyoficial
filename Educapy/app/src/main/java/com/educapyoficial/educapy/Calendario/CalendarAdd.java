package com.educapyoficial.educapy.Calendario;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.educapyoficial.educapy.MainActivity;
import com.educapyoficial.educapy.R;
import com.educapyoficial.educapy.includes.MyToolbar;
import com.educapyoficial.educapy.menuadministrador;
import com.educapyoficial.educapy.models.constructorCalendario;
import com.educapyoficial.educapy.providers.AuthProvider;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;

public class CalendarAdd extends AppCompatActivity {


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




    private List<constructorCalendario> listEspecialidad = new ArrayList<constructorCalendario>();
    ArrayAdapter<constructorCalendario> arrayAdapterEspecialidad;
    ProgressDialog cargando;
    EditText nomP, cajaFechaEvento,cajatipoevento; //insertar datos
    FirebaseDatabase firebaseDatabase;
    ListView listV_personasR; //insertar datos
    DatabaseReference databaseReference;
    DatabaseReference reff;
    AlertDialog mDialog;
    AuthProvider mauthProvider;
    constructorCalendario objetoconstructorCalendario;
    long maxid = 0;
    int almacenapuntos;
    ArrayAdapter<String> mAdapter;
    Spinner spinnerGrupo;
    public String grupoasignado = "";
    private CircleImageView mCircleImageNext,mCircleUpdate,mCircleDelete;
    String tipodeEventoR;
    Button btncalendar;
    private String fecha;
    String obtengoUrlFoto;

    DatePickerDialog.OnDateSetListener setListener;

    int year,month,day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_add);


        etFecha = findViewById(R.id.textCorreoComE);

        // Poner último año, mes y día a la fecha de hoy
        final Calendar calendario = Calendar.getInstance();
        ultimoAnio = calendario.get(Calendar.YEAR);
        ultimoMes = calendario.get(Calendar.MONTH);
        ultimoDiaDelMes = calendario.get(Calendar.DAY_OF_MONTH);

        // Refrescar la fecha en el EditText
        refrescarFechaEnEditText();

        mauthProvider = new AuthProvider();
        spinnerGrupo = (Spinner) findViewById(R.id.spinnerGrupoCalendar);
        mCircleImageNext = findViewById(R.id.circleImageNextCom);
        mCircleUpdate = findViewById(R.id.circleImageNextComupdate);
        mCircleDelete = findViewById(R.id.circleImageNextComdelete);
        //  spinnerGrupo = (Spinner) findViewById(R.id.spinnerGrupoT);
        cargando = new ProgressDialog(this);
        cajaFechaEvento = findViewById(R.id.textCorreoComE);  //bien
        nomP = findViewById(R.id.textInputNameComE); //bien
        btncalendar = findViewById(R.id.btnGoToCalendar);
        cajatipoevento = findViewById(R.id.solucionkeyR);
        //cajafiltra = findViewById(R.id.textentrada);
        reff = FirebaseDatabase.getInstance().getReference().child("Calendario").child("Eventos");
        mDialog = new SpotsDialog.Builder().setContext(CalendarAdd.this).setMessage("Espere Un Momento").build();
        objetoconstructorCalendario = new constructorCalendario();
        listV_personasR = findViewById(R.id.lv_datosPersonasRcom); //insertar datos
        inicializarFirebase(); //insertar datos
        listarDatos();



        btncalendar.setOnClickListener(new View.OnClickListener() {
          //  @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                DatePickerDialog dialogoFecha = new DatePickerDialog(CalendarAdd.this, listenerDeDatePicker, ultimoAnio, ultimoMes, ultimoDiaDelMes);
                //Mostrar
                dialogoFecha.show();

            }
        });

        mCircleDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = nomP.getText().toString();
                String editextfechaevento = cajaFechaEvento.getText().toString();
              //  String tipodeEventoRR = cajatipoevento.getText().toString();

                if (nombre.isEmpty()||editextfechaevento.isEmpty()){
                    validacion();
                }
                else {
                    constructorCalendario p = new constructorCalendario();
                    p.setUid(objetoconstructorCalendario.getUid());
                    databaseReference.child("Calendario").child("Eventos").child(p.getUid()).removeValue();
                    Toast.makeText(CalendarAdd.this,"Eliminado", Toast.LENGTH_LONG).show();
                    limpiarCajas();
                    Intent intent = new Intent(CalendarAdd.this, menuadministrador.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
                    startActivity(intent);
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
                    constructorCalendario p = new constructorCalendario();
                    p.setUid(objetoconstructorCalendario.getUid());
                    p.setNombreEvento(nomP.getText().toString().trim());
                    p.setFechaEvento(cajaFechaEvento.getText().toString().trim());
                    p.setFechaEvento(tipodeEventoR.trim());
                    p.setFotourl(obtengoUrlFoto);
                    databaseReference.child("Calendario").child("Eventos").child(p.getUid()).setValue(p);
                    Toast.makeText(CalendarAdd.this,"Actualizado", Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }
            }
        });

        mCircleImageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = nomP.getText().toString();
                String editextfechaevento = cajaFechaEvento.getText().toString();
                String tipodeEventoRR = cajatipoevento.getText().toString();

                if (nombre.isEmpty()||tipodeEventoRR.isEmpty()||editextfechaevento.isEmpty()){
                    validacion();
                    Toast.makeText(CalendarAdd.this, "no validado", Toast.LENGTH_SHORT).show();
                }
                else {
                 //   Toast.makeText(CalendarAdd.this, "validado", Toast.LENGTH_SHORT).show();

                    constructorCalendario p = new constructorCalendario();
                    p.setUid(UUID.randomUUID().toString());
                    p.setNombreEvento(nombre);
                    p.setFechaEvento(editextfechaevento);
                    p.setTipodeEvento(cajatipoevento.getText().toString());
                    p.setFotourl(obtengoUrlFoto);
                    databaseReference.child("Calendario").child("Eventos").child(p.getUid()).setValue(p);
                    // Toast.makeText(this, "Agregado", Toast.LENGTH_LONG).show();

                    Toast.makeText(CalendarAdd.this, "Agregado", Toast.LENGTH_SHORT).show();
                    limpiarCajas();


                }

            }
        });

        mAdapter = new ArrayAdapter<String>(CalendarAdd.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.tipoEventoC));
        spinnerGrupo.setAdapter(mAdapter);
        spinnerGrupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) spinnerGrupo.getSelectedView()).setTextColor(Color.BLACK);
                String seleccionEdad = spinnerGrupo.getSelectedItem().toString();
                if (seleccionEdad.equals("Tareas")) {
                    tipodeEventoR = "Tareas";
                    cajatipoevento.setText("Tareas");
                    obtengoUrlFoto = "https://firebasestorage.googleapis.com/v0/b/educapy-5fd5d.appspot.com/o/noborrar%2Fpackard-bell.png?alt=media&token=016173d9-7fa1-4740-ba8d-6fbc76f673ca";
                    //    cajafiltra.setText(grupoasignado.toString());
                }
                if (seleccionEdad.equals("Convivencias")) {
                    tipodeEventoR = "Convivencias";
                    cajatipoevento.setText("Convivencias");
                    obtengoUrlFoto = "https://firebasestorage.googleapis.com/v0/b/educapy-5fd5d.appspot.com/o/noborrar%2Fescoger.png?alt=media&token=0773bd6c-c88e-45ac-bfee-079facc98659";
                    //    cajafiltra.setText(grupoasignado.toString());
                }
                if (seleccionEdad.equals("Eventos")) {
                    tipodeEventoR = "Eventos";
                    cajatipoevento.setText("Eventos");
                    obtengoUrlFoto = "https://firebasestorage.googleapis.com/v0/b/educapy-5fd5d.appspot.com/o/noborrar%2Feventos.png?alt=media&token=f9438495-1e8f-4a2b-a335-fe3ce9ce1a69";
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
                objetoconstructorCalendario = (constructorCalendario) parent.getItemAtPosition(position);
                nomP.setText(objetoconstructorCalendario.getNombreEvento());
                cajaFechaEvento.setText(objetoconstructorCalendario.getFechaEvento());
                tipodeEventoR = objetoconstructorCalendario.getTipodeEvento();
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

    /*
    public void actualizaCalificacion() { //este metodo actualiza un solo elemento o crea un nuevo elemento sin que se aya creado :)
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference scoreRef = rootRef.child("Users").child("Clients").child(tipodeEventoR).child("calificacion1R");
        scoreRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                String calificacion = mutableData.getValue(String.class);

                calificacion = cajaFechaEvento.getText().toString();
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

        Toast.makeText(this, "Calificacion Actualizada", Toast.LENGTH_SHORT).show();
    }

     */

    private void validacion() {
        String nombret = nomP.getText().toString();  //utilizo esta forma para obtener el valor de los campos y validar los campos vacios  atravez de la clase validacion
        String correoT = cajaFechaEvento.getText().toString();
        String tipodeEventoT = cajatipoevento.getText().toString();
        if (nombret.equals("")) {
            nomP.setError("Required");
        } else if (correoT.equals("")) {
            cajaFechaEvento.setError("Required");
        } else if (tipodeEventoT.equals("")) {
            Toast.makeText(this, "Seleccionar tipo de Evento", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CalendarAdd.this, menuadministrador.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
        startActivity(intent);
    }


    private void limpiarCajas() {
        nomP.setText("");
        cajaFechaEvento.setText("");
        grupoasignado = "";
    }



    private void listarDatos() {
        databaseReference.child("Calendario").child("Eventos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                listEspecialidad.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    int count = 0;
                    maxid = (dataSnapshot.getChildrenCount());
                    constructorCalendario p = objSnaptshot.getValue(constructorCalendario.class);
                    listEspecialidad.add(p);
                    arrayAdapterEspecialidad = new ArrayAdapter<constructorCalendario>(CalendarAdd.this, android.R.layout.simple_list_item_1, listEspecialidad);
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
