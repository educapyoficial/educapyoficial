package com.educapyoficial.educapy;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.educapyoficial.educapy.SendNotificationPack.APIService;
import com.educapyoficial.educapy.SendNotificationPack.Client;
import com.educapyoficial.educapy.SendNotificationPack.Token;
import com.educapyoficial.educapy.models.EducapyModelUser;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;

public class administradorNotificaProfesor extends AppCompatActivity {

    //para jalar los datos del listview

    private List<EducapyModelUser> listcolaboradores = new ArrayList<EducapyModelUser>();
    ArrayAdapter<EducapyModelUser> arrayAdapterColaboradores;
    ProgressDialog cargando;
    private CircleImageView mCircleImageBackad;
    EditText nomP, cajafiltra, cajatitulo, cajacuerpo; //insertar datos
    FirebaseDatabase firebaseDatabase;
    ListView listV_personasR; //insertar datos
    DatabaseReference databaseReference;
    DatabaseReference reff;
    AlertDialog mDialog;
    AuthProvider mauthProvider;
    EducapyModelUser educapyModelUser;
    long maxid = 0;
    String tokenFirebase;
    Button btnespecifico, btnatopico;
    private APIService apiService;
    //String obtieneToken;

    SharedPreferences mPref;
    String uidCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
        mauthProvider = new AuthProvider();
        mPref = getApplicationContext().getSharedPreferences("validadmiRT", MODE_PRIVATE);
        //  spinnerGrupo = (Spinner) findViewById(R.id.spinnerGrupoT);
        cargando = new ProgressDialog(this);
        cajatitulo = findViewById(R.id.titulomensaje);  //bien
        cajacuerpo = findViewById(R.id.cuerpomensaje);
        nomP = findViewById(R.id.textInputNamenot); //bien
        //cajafiltra = findViewById(R.id.textentradanoti);
        listV_personasR = findViewById(R.id.lv_datosPersonasRnot); //insertar datos
        reff = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients");
        mDialog = new SpotsDialog.Builder().setContext(administradorNotificaProfesor.this).setMessage("Espere Un Momento").build();
        educapyModelUser = new EducapyModelUser();
        inicializarFirebase(); //insertar datos

        mCircleImageBackad = findViewById(R.id.circleImageBackadmi);

        Intent intent = getIntent();
        uidCurso = intent.getExtras().getString("uidCurso", "");
        listarDatos();
        mCircleImageBackad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });

        btnespecifico = (Button) findViewById(R.id.btn_especifico);
        btnatopico = (Button) findViewById(R.id.btn_atopico);
        apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);
        //     UpdateToken();
        btnespecifico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomp = nomP.getText().toString();
                String titulo = cajatitulo.getText().toString();
                String cuerpo = cajacuerpo.getText().toString();

                if (!nomp.isEmpty() && !titulo.isEmpty() && !cuerpo.isEmpty()) {
                    enviaraespecifico();
                    Toast.makeText(administradorNotificaProfesor.this, "Mensaje Enviado a Estudiante", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(administradorNotificaProfesor.this, "Selecciona un usuario", Toast.LENGTH_SHORT).show();
                }
                //  enviaraespecifico();
            }
        });

        btnatopico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomp = nomP.getText().toString();
                String titulo = cajatitulo.getText().toString();
                String cuerpo = cajacuerpo.getText().toString();

                if (!nomp.isEmpty() && !titulo.isEmpty() && !cuerpo.isEmpty()) {
                    showSucessDialogNOTI();
                    // Toast.makeText(admiNotificaciones.this, "Excelente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(administradorNotificaProfesor.this, "Llena todos los campos", Toast.LENGTH_SHORT).show();
                }
                //  enviarnotiall();

                //  Toast.makeText(admiNotificaciones.this, "boton presionado", Toast.LENGTH_SHORT).show();

            }
        });

     /*   cajafiltra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                arrayAdapterColaboradores.getFilter().filter(s);
                //obtenerinfoToken();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/


        listV_personasR.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                educapyModelUser = (EducapyModelUser) parent.getItemAtPosition(position);

                Intent intent = new Intent(administradorNotificaProfesor.this, ListAnecdotarioActivity.class);
                intent.putExtra("educapyModelUser", educapyModelUser);
                startActivity(intent);


//                nomP.setText(educapyModelUser.getNombre1R());
//                tokenFirebase = educapyModelUser.getTokenFirebase();

               /* databaseReference.child("Tokens").child(obtienegkeR).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                            obtieneToken = dataSnapshot.child("token").getValue().toString();
                            //     Toast.makeText(admiNotificaciones.this, "" + obtieneToken, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });*/



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

    }




    private void validacion() {
        /*1*/
        String nombret = nomP.getText().toString();  //utilizo esta forma para obtener el valor de los campos y validar los campos vacios  atravez de la clase validacion

        if (nombret.equals("")) {
            nomP.setError("Required");
        } else if (tokenFirebase.equals("")) {
            Toast.makeText(this, "Seleccione un Colaborador", Toast.LENGTH_SHORT).show();
        }
    }

    private void listarDatos() {
        databaseReference.child("Users").child("Clients").orderByChild("uidCurso").equalTo(uidCurso).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                listcolaboradores.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    int count = 0;
                    maxid = (dataSnapshot.getChildrenCount());
                    EducapyModelUser p = objSnaptshot.getValue(EducapyModelUser.class);
                    listcolaboradores.add(p);
                }
                arrayAdapterColaboradores = new ArrayAdapter<EducapyModelUser>(administradorNotificaProfesor.this, android.R.layout.simple_list_item_1, listcolaboradores);
                listV_personasR.setAdapter(arrayAdapterColaboradores);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        databaseReference.child("Profesores").child("id").orderByChild("uidCurso").equalTo(uidCurso).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                listcolaboradores.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    int count = 0;
                    maxid = (dataSnapshot.getChildrenCount());
                    EducapyModelUser p = objSnaptshot.getValue(EducapyModelUser.class);
                    listcolaboradores.add(p);
                }
                arrayAdapterColaboradores = new ArrayAdapter<EducapyModelUser>(administradorNotificaProfesor.this, android.R.layout.simple_list_item_1, listcolaboradores);
                listV_personasR.setAdapter(arrayAdapterColaboradores);

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


    private void enviarnotiall() {

        //   Toast.makeText(this, "boton precionado topics", Toast.LENGTH_SHORT).show();
        RequestQueue myrequest = Volley.newRequestQueue(getApplicationContext());
        JSONObject json = new JSONObject();

        try {
            //  String token ="9uzXyBlKfQMv56GzPmPPWUt5PPx2";
            json.put("to", "/topics/" + "enviartodos");
            JSONObject notificacion = new JSONObject();
            notificacion.put("titulo", cajatitulo.getText().toString());
            notificacion.put("detalle", cajacuerpo.getText().toString());
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showSucessDialogNOTI() {

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(administradorNotificaProfesor.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(administradorNotificaProfesor.this).inflate(
                R.layout.layout_warning_dialog,
                (ConstraintLayout) findViewById(R.id.layoutDialogContainerRR)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitleRT)).setText(getResources().getString(R.string.alert_success));
        ((TextView) view.findViewById(R.id.textMessageRT)).setText(R.string.aviso);
        ((Button) view.findViewById(R.id.buttonYesRR)).setText(getResources().getString(R.string.enviartodos));
        ((Button) view.findViewById(R.id.buttonNoRR)).setText(getResources().getString(R.string.cancelar));
        ((ImageView) view.findViewById(R.id.imageIconRT)).setImageResource(R.drawable.notificacion);
        final androidx.appcompat.app.AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.buttonYesRR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(administradorNotificaProfesor.this, "Mensaje enviado a todos los usuarios", Toast.LENGTH_SHORT).show();
                enviarnotiall();
                alertDialog.dismiss();
            }
        });

        view.findViewById(R.id.buttonNoRR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();


    }


    private void enviaraespecifico() {
        //   Toast.makeText(this, "boton precionado", Toast.LENGTH_SHORT).show();
        RequestQueue myrequest = Volley.newRequestQueue(getApplicationContext());
        JSONObject json = new JSONObject();

        try {
//            String token ="e8z05haZTy69IZ8Yc8GrPc:APA91bGxJ1sMzUm6VZIVJCbQ0VoSQNKDn807PwLNG6uyuIpiawL2MoUtmKO1XY65yTTFigDGHgaabGFfjd4t6QS4vsbS-4EqA4PsXULP6nDhRhQW2IHE3X6jO5v9yKth1FXU5xXFhhZg"; //kimbo
            //    String token ="dv4hIv--QWOPL3dxjSgX_n:APA91bEzRlpc6ro41vbRnG-fO9G1sU4qsepQ9GBalCEBFWxm9kwERlTEzJW2C5_8F8GGtEIpzn8I3j68poXrrhXSZY6e_DB_wXNsuNhZvssDacCL1fQaKvUovzRZMQlt7eD7-VVLulFl"; //martha
            //String token = "cZmk1uRCRC27eiPWlDu2MO:APA91bFqScj7PtPtZsjS1eFCLM9BPOHvk1KKmbYs0yD85wOYOk0pTOf4DU9A3NsQRK4eCUKyRVheWQfmPT8hHoi2LgZ9xufBxEk4riO6xfb2T9vHfEm0oQg5yEIUS87FywpfmlkzPecu"; //señorbigotes
            json.put("to", tokenFirebase);
            JSONObject notificacion = new JSONObject();
            notificacion.put("titulo", cajatitulo.getText().toString());
            notificacion.put("detalle", cajacuerpo.getText().toString());
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

