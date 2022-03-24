package com.educapyoficial.educapy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.educapyoficial.educapy.models.EducapyModelUser;
import com.educapyoficial.educapy.models.RegistroAnecdotario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RegistrarAnectotarioAlumno extends AppCompatActivity {

    EditText textInputName1;
    EditText textInputapellidos1;
    Button textInputFecha;
    EditText textInputTiempo;
    EditText textInputLugar;
    EditText textInputPeriodo;
    EditText textInputObservador;
    EditText textInputActividadReal;
    EditText textInputConducta;
    EditText textInputComentario;
    EditText textInputRecomendacion;

    EducapyModelUser educapyModelUser;

    Button btnregistrarAnecdotario;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private int mYear, mMonth, mDay;

    DatePickerDialog datePickerDialog;
    RegistroAnecdotario registroAnecdotario;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    Button textInputHoraDesde;
    Button textInputHoraHasta;

    int mMinute, mHour;
    private Spinner spinnerLugar;
    Spinner spinnerObservador;
    private ArrayAdapter<String> mAdapterSpinner;
    private ArrayAdapter<String> mAdapterSpinnerObs;
    private ArrayAdapter<String> mAdapterSpinnerPeriodo;
    private String lugar;
    String observador;
    String periodo;
    private List<String> listDatos;
    private Spinner spinnerPeriodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_anectotario_alumno);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        String dayString = "";
                        if (dayOfMonth < 10) {
                            dayString = "0" + dayOfMonth;
                        } else {
                            dayString = String.valueOf(dayOfMonth);
                        }

                        textInputFecha.setText(dayString + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);

        textInputName1 = findViewById(R.id.textInputName1);
        textInputapellidos1 = findViewById(R.id.textInputapellidos1);
        textInputFecha = findViewById(R.id.textInputFecha);
        textInputTiempo = findViewById(R.id.textInputTiempo);
        //textInputLugar = findViewById(R.id.textInputLugar);
        //textInputPeriodo = findViewById(R.id.textInputPeriodo);
        //textInputObservador = findViewById(R.id.textInputObservador);
        textInputActividadReal = findViewById(R.id.textInputActividadReal);
        textInputConducta = findViewById(R.id.textInputConducta);
        textInputComentario = findViewById(R.id.textInputComentario);
        textInputRecomendacion = findViewById(R.id.textInputRecomendacion);
        btnregistrarAnecdotario = findViewById(R.id.btnregistrarAnecdotario);
        textInputHoraDesde = findViewById(R.id.textInputHoraDesde);
        textInputHoraHasta = findViewById(R.id.textInputHoraHasta);
        Intent intent = getIntent();
        educapyModelUser = (EducapyModelUser) intent.getSerializableExtra("educapyModelUser");
        registroAnecdotario = (RegistroAnecdotario) intent.getSerializableExtra("registroAnecdotario");


        spinnerLugar = findViewById(R.id.spinnerLugar);
        spinnerLugar.setSelection(0);
        mAdapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
        spinnerLugar.setPrompt("Seleccionar Lugar");
        spinnerLugar.setAdapter(mAdapterSpinner);
        spinnerLugar.setSelection(mAdapterSpinner.getCount());
        spinnerLugar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                try {
                    lugar = spinnerLugar
                            .getItemAtPosition(position).toString();
                } catch (Exception e) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO Auto-generated method stub

            }

        });

        spinnerObservador = findViewById(R.id.spinnerObservador);
        spinnerObservador.setSelection(0);
        mAdapterSpinnerObs = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
        spinnerObservador.setPrompt("Seleccionar Observador");
        spinnerObservador.setAdapter(mAdapterSpinnerObs);
        spinnerObservador.setSelection(mAdapterSpinnerObs.getCount());
        spinnerObservador.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                try {
                    observador = spinnerObservador
                            .getItemAtPosition(position).toString();
                } catch (Exception e) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO Auto-generated method stub

            }

        });

        spinnerPeriodo = findViewById(R.id.spinnerPeriodo);
        spinnerPeriodo.setSelection(0);
        mAdapterSpinnerPeriodo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
        spinnerPeriodo.setPrompt("Seleccionar Periodo");
        spinnerPeriodo.setAdapter(mAdapterSpinnerPeriodo);
        spinnerPeriodo.setSelection(mAdapterSpinnerPeriodo.getCount());
        spinnerPeriodo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                try {
                    periodo = spinnerPeriodo
                            .getItemAtPosition(position).toString();
                } catch (Exception e) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO Auto-generated method stub

            }

        });


        textInputName1.setText(educapyModelUser.getNombre1R());
        textInputapellidos1.setText(educapyModelUser.getApellidos1R());

        btnregistrarAnecdotario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarRegistroAnecdotario();
            }
        });

        textInputFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        inicializarFirebase();

        setUpStartTime();

        cargarDatosLugar();
        cargarDatosObservador();
        cargarDatosPeriodo();


        if (registroAnecdotario != null){
            btnregistrarAnecdotario.setVisibility(View.GONE);
            cargarDatos();
        }

    }

    private void cargarDatosLugar() {
        databaseReference.child("Referencias").child("lugar").orderByChild("descripcion").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listDatos = new ArrayList<>();
                listDatos.add(0, "Seleccionar Lugar");
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    try {


                        JSONObject jsonObject = new JSONObject(objSnaptshot.getValue().toString());
                        listDatos.add(jsonObject.optString("descripcion"));




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                mAdapterSpinner.addAll(listDatos);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void cargarDatosObservador() {
        databaseReference.child("Referencias").child("observador").orderByChild("descripcion").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listDatos = new ArrayList<>();
                listDatos.add(0, "Seleccionar Observador");
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    try {


                        JSONObject jsonObject = new JSONObject(objSnaptshot.getValue().toString());
                        listDatos.add(jsonObject.optString("descripcion"));




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                mAdapterSpinnerObs.addAll(listDatos);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void cargarDatosPeriodo() {
        databaseReference.child("Referencias").child("periodo").orderByChild("descripcion").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listDatos = new ArrayList<>();
                listDatos.add(0, "Seleccionar Periodo");
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    try {


                        JSONObject jsonObject = new JSONObject(objSnaptshot.getValue().toString());
                        listDatos.add(jsonObject.optString("descripcion"));




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                mAdapterSpinnerPeriodo.addAll(listDatos);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setUpStartTime(){
        textInputHoraDesde.setText("Hora Desde");
        textInputHoraDesde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStartTimePicker();
            }
        });

        textInputHoraHasta.setText("Hora Hasta");
        textInputHoraHasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStartTimePicker2();
            }
        });
    }

    private void showStartTimePicker() {
        final Calendar calendar = Calendar.getInstance();
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (minute < 10){
                    textInputHoraDesde.setText(hourOfDay + ":0" + minute);
                }else{
                    textInputHoraDesde.setText(hourOfDay + ":" + minute);
                }

            }
        }, mHour, mMinute, true);

        timePickerDialog.show();

    }


    private void showStartTimePicker2() {
        final Calendar calendar = Calendar.getInstance();
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (minute < 10){
                    textInputHoraHasta.setText(hourOfDay + ":0" + minute);
                }else{
                    textInputHoraHasta.setText(hourOfDay + ":" + minute);
                }

            }
        }, mHour, mMinute, true);

        timePickerDialog.show();

    }

    public void cargarDatos(){
//        textInputName1.setText(registroAnecdotario.getNombreAlumno());
//        textInputapellidos1.setText(registroAnecdotario.getApellidoAlumno());
        textInputFecha.setText(simpleDateFormat.format(registroAnecdotario.getFecha()));
        textInputTiempo.setText(registroAnecdotario.getTiempo());
        //textInputLugar.setText(registroAnecdotario.getTiempo());
        //textInputPeriodo.setText(registroAnecdotario.getPeriodo());
        //textInputObservador.setText(registroAnecdotario.getObservador());
        if (registroAnecdotario.getLugar() != null && !registroAnecdotario.getLugar().contains("Seleccionar")){
            int position = mAdapterSpinner.getPosition(registroAnecdotario.getLugar());
            spinnerLugar.setSelection(position);
        }

        if (registroAnecdotario.getPeriodo() != null && !registroAnecdotario.getPeriodo().contains("Seleccionar")){
            int position = mAdapterSpinnerPeriodo.getPosition(registroAnecdotario.getPeriodo());
            spinnerPeriodo.setSelection(position);
        }

        if (registroAnecdotario.getObservador() != null && !registroAnecdotario.getObservador().contains("Seleccionar")){
            int position = mAdapterSpinnerObs.getPosition(registroAnecdotario.getObservador());
            spinnerObservador.setSelection(position);
        }


        textInputActividadReal.setText(registroAnecdotario.getActividadRealizada());
        textInputConducta.setText(registroAnecdotario.getConducta());
        textInputComentario.setText(registroAnecdotario.getComentario());
        textInputRecomendacion.setText(registroAnecdotario.getRecomendacion());
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void registrarRegistroAnecdotario() {
        registroAnecdotario = new RegistroAnecdotario();
        registroAnecdotario.setPeriodo(periodo);
        registroAnecdotario.setObservador(observador);
        registroAnecdotario.setActividadRealizada(textInputActividadReal.getText().toString());
        registroAnecdotario.setConducta(textInputConducta.getText().toString());
        registroAnecdotario.setComentario(textInputComentario.getText().toString());
        registroAnecdotario.setRecomendacion(textInputRecomendacion.getText().toString());
        registroAnecdotario.setLugar(lugar);
        registroAnecdotario.setUidAlumno(educapyModelUser.getUid());
        registroAnecdotario.setTiempo(textInputTiempo.getText().toString());
        registroAnecdotario.setTiempoDesde(textInputHoraDesde.getText().toString());
        registroAnecdotario.setTiempoHasta(textInputHoraHasta.getText().toString());
        registroAnecdotario.setLugar(textInputLugar.getText().toString());
        try {
            registroAnecdotario.setFecha(simpleDateFormat.parse(textInputFecha.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DatabaseReference usersRef = databaseReference.child("RegistroAnecdotario").child("id");
        usersRef.push().setValue(registroAnecdotario);
        Toast.makeText(this, "Registrado Con Éxito", Toast.LENGTH_SHORT).show();
        enviaraespecifico(registroAnecdotario);

        finish();

    }


    private void enviaraespecifico(RegistroAnecdotario registroAnecdotario) {
        //   Toast.makeText(this, "boton precionado", Toast.LENGTH_SHORT).show();
        RequestQueue myrequest = Volley.newRequestQueue(getApplicationContext());
        JSONObject json = new JSONObject();
        try {
//            String token ="e8z05haZTy69IZ8Yc8GrPc:APA91bGxJ1sMzUm6VZIVJCbQ0VoSQNKDn807PwLNG6uyuIpiawL2MoUtmKO1XY65yTTFigDGHgaabGFfjd4t6QS4vsbS-4EqA4PsXULP6nDhRhQW2IHE3X6jO5v9yKth1FXU5xXFhhZg"; //kimbo
            //    String token ="dv4hIv--QWOPL3dxjSgX_n:APA91bEzRlpc6ro41vbRnG-fO9G1sU4qsepQ9GBalCEBFWxm9kwERlTEzJW2C5_8F8GGtEIpzn8I3j68poXrrhXSZY6e_DB_wXNsuNhZvssDacCL1fQaKvUovzRZMQlt7eD7-VVLulFl"; //martha
            //String token = "cZmk1uRCRC27eiPWlDu2MO:APA91bFqScj7PtPtZsjS1eFCLM9BPOHvk1KKmbYs0yD85wOYOk0pTOf4DU9A3NsQRK4eCUKyRVheWQfmPT8hHoi2LgZ9xufBxEk4riO6xfb2T9vHfEm0oQg5yEIUS87FywpfmlkzPecu"; //señorbigotes
            json.put("to", educapyModelUser.getTokenFirebase());
            JSONObject notificacion = new JSONObject();
            notificacion.put("periodo", registroAnecdotario.getPeriodo());
            notificacion.put("observador", registroAnecdotario.getObservador());
            notificacion.put("actividadRealizada", registroAnecdotario.getActividadRealizada());
            notificacion.put("conducta", registroAnecdotario.getConducta());
            notificacion.put("comentario", registroAnecdotario.getComentario());
            notificacion.put("recomendacion", registroAnecdotario.getRecomendacion());
            notificacion.put("lugar", registroAnecdotario.getLugar());
            notificacion.put("titulo", "Registro Anecdotario");
            notificacion.put("detalle", "Información");


            Gson gson = new GsonBuilder()
                    .setDateFormat("dd/MM/yyyy")
                    .create();

            notificacion.put("registroAnecdotario", new JSONObject(gson.toJson(registroAnecdotario)));
            notificacion.put("educapyModelUser", new JSONObject(gson.toJson(educapyModelUser)));


            json.put("data", notificacion);
            String URL = "https://fcm.googleapis.com/fcm/send";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, json, null, null) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> header = new HashMap<>();
                    header.put("content-type", "application/json");
                    header.put("authorization", "key=AAAANtFpiDk:APA91bEWuP3fTIKEHGJ0uv9H5vI5eF78bet0VICafCJ_NS88fd8-80ny_quPABk962BE1_-S7U4wgCdhHqWHlUMWt20Dz0V0HsTPC_dOm_1m2cDXarX9dYEZt9havZ1hHiSQPXq_M5Au");
                    return header;
                }
            };
            myrequest.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}