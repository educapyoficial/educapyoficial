package com.educapyoficial.educapy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.educapyoficial.educapy.Calendario.CalendarUser;
import com.educapyoficial.educapy.Evaluacion.evaluacionIndividual;
import com.educapyoficial.educapy.SendNotificationPack.Token;
import com.educapyoficial.educapy.listaAsistencia.revisaAsistenciaAlumno;
import com.educapyoficial.educapy.models.ClientM;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class principal extends AppCompatActivity {

    private Button mButtonADMIN;
    CardView tarjeta1, tarjeta2, tarjeta3, tarjeta4,tarjeta5,tarjeta6asistencia;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    ImageView imagePerfil, imagePais;
    private CircleImageView mCircleImageNext;
    String id;
    String getkey0; //identificacion del token de usuario
    SharedPreferences mPref, mPref2, mPref3;
    public int borrar = 1;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String obtienecorreo;
    String revisacesso;
    String obtienegkeR;
    TextView muestranombre;
    String almacenatoken;
    String oNombre;
    String arknombre = "";

    //nuevo fragmento
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref_user = database.getReference("Users").child("Clients").child(user.getUid());

    //fin de fragmento

    String compruebaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_principal);

        mPref = getApplicationContext().getSharedPreferences("idgruput", MODE_PRIVATE);
        mPref2 = getApplicationContext().getSharedPreferences("revisaUser", MODE_PRIVATE);
        mPref3 = getApplicationContext().getSharedPreferences("validadmiRT", MODE_PRIVATE);

        obtienegkeR = mPref3.getString("uid", "");

        final SharedPreferences.Editor editor = mPref.edit();
        muestranombre = findViewById(R.id.txttituloname);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = user.getUid();
        //    UpdateToken();
        obtenerinfoChatCod();
        actualizanodocalificacion();
        tarjeta1 = findViewById(R.id.Card1calificacion);
        tarjeta2 = findViewById(R.id.Card2chat);
        tarjeta3 = findViewById(R.id.Card3fotos);
        tarjeta4 = findViewById(R.id.Card4eventos);
        tarjeta5 = findViewById(R.id.Card5registro);
        tarjeta6asistencia = findViewById(R.id.Card5asistencia);
        Intent i = getIntent();
        arknombre = i.getStringExtra("nombrealumnoT");

        FirebaseMessaging.getInstance().subscribeToTopic("enviartodos").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //     Toast.makeText(Principal.this, "Suscrito a enviar a todos", Toast.LENGTH_SHORT).show();
            }
        });


//        stopService(new Intent(Principal.this, MyServiceSingle.class)); //detener musica de fondo
        //    startService(new Intent(principal.this, contructorMusica.class));
        mButtonADMIN = (Button) findViewById(R.id.btnGoToadmi);
        imagePerfil = findViewById(R.id.fotoP);
       // imagePais = findViewById(R.id.fotoPais);
        mCircleImageNext = findViewById(R.id.circleImageNext);

        userunico(); //para crear en realtime un registro con la autenticacion de Google

        Glide.with(this).load(user.getPhotoUrl()).into(imagePerfil);
        muestranombre.setText(user.getDisplayName());
        obtienecorreo = user.getEmail();

        if (obtienecorreo.equals("profematiaseducapy@gmail.com")||obtienecorreo.equals("letogon@gmail.com")) {
            mButtonADMIN.setVisibility(View.VISIBLE);
        } else {
            Log.d("prueba", "NO es administrador");
        }

        obtenerinfoUser2();

        tarjeta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String codigoOS = "1";
               // if(codigoOS.equals(compruebaUsuario))
                //{
                    Intent myIntent = new Intent(principal.this, evaluacionIndividual.class);
                    myIntent.putExtra("obtengogkeR", obtienegkeR);
                    //  stopService(new Intent(Principal.this, contructorMusica.class)); //detener musica de fondo
                    startActivity(myIntent);
                //}else
                //{
                 //   Toast.makeText(principal.this, "Tiene que llenar bitacora Primero", Toast.LENGTH_SHORT).show();
                //}


            }
        });


        tarjeta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String codigoOS = "1";
                if (codigoOS.equals(almacenatoken)) {
                    //mAuth.signOut();
                    //finish();
                    startActivity(new Intent(principal.this, principalchat.class));
                    //  stopService(new Intent(principal.this, contructorMusica.class)); //detener musica de fondo
                } else {
                    Toast.makeText(principal.this, "Chat Bloqueado el Profesor Activara a la Brevedad", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tarjeta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(principal.this, selectorvisual.class);
                myIntent.putExtra("obtengogkeR", obtienegkeR);
                //  stopService(new Intent(Principal.this, contructorMusica.class)); //detener musica de fondo
                startActivity(myIntent);
            }
        });

        tarjeta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(principal.this, CalendarUser.class));
                //finish();
            }
        });

        tarjeta5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(principal.this, RegisterClientActivity.class));
                //finish();
            }
        });

        tarjeta6asistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(principal.this, revisaAsistenciaAlumno.class));
                //finish();
            }
        });


        mButtonADMIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(principal.this, menuadministrador.class));
            }
        });

        mCircleImageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance()
                        .signOut(getApplicationContext())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                //  finish();
                                //    Toast.makeText(getContext(), "Cerrando seccion", Toast.LENGTH_SHORT).show();

                                vamosalogin();
                                salida();
                            }
                        });

                Toast.makeText(principal.this, "Finalizando...", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void userunico() {

        ref_user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    ClientM uu = new ClientM(
                            user.getUid(),
                            "",
                            user.getEmail(),
                            user.getPhotoUrl().toString(),
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            user.getUid(),
                            user.getDisplayName()

                    );
                    ref_user.setValue(uu);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }



    private void vamosalogin() {
        Intent i = new Intent(this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        moveTaskToBack(true);

    }

    @SuppressLint("NewApi")
    public void salida() {
        finishAffinity();
    }

    private void actualizanodocalificacion() {


        //lo que hago con este fragmento de codigo es pregntar si existe el registro del usuario logueado en calificaciones no cree una nueva setencia, si no existe que cree esa nueva informacion

        id = mAuth.getCurrentUser().getUid(); //aqui obtengo el id del usuario logueado
        mDatabase.child("Calificaciones").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                  //  Toast.makeText(principal.this, "Si existe", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    String calificaciont = "NA";  //utilizo esta forma para obtener el valor de los campos y validar los campos vacios  atravez de la clase validacion

                    Map<String, Object> personmap = new HashMap<>();
                    // Toast.makeText(administrador.this, "", Toast.LENGTH_SHORT).show();
                    personmap.put("calificacion", calificaciont);
                    personmap.put("nombre1R",arknombre);

                    mDatabase.child("Calificaciones").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(personmap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                         //   Toast.makeText(principal.this, "nodo Actualizado", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                          //  Toast.makeText(principal.this, "nodo no Actualizado", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void obtenerinfoChatCod() {
        //   id = mAuth.getCurrentUser().getUid(); //aqui obtengo el id del usuario logueado
        mDatabase.child("codAcess").child("Accesso").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                    almacenatoken = dataSnapshot.child("codigo").getValue().toString();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }



    private void obtenerinfoUser2() {
        mPref = getApplicationContext().getSharedPreferences("idgruput", MODE_PRIVATE);
        final SharedPreferences.Editor editor = mPref.edit();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        Token token = new Token(refreshToken);
        FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);
     //   FirebaseDatabase.getInstance().getReference("Calificaciones").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);

        id = mAuth.getCurrentUser().getUid(); //aqui obtengo el id del usuario logueado
        mDatabase.child("Users").child("Clients").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                        final SharedPreferences.Editor editor2 = mPref2.edit();
                        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                        oNombre = dataSnapshot.child("nombre1R").getValue().toString();
                        //String oPais = dataSnapshot.child("nombrepapa2R").getValue().toString();
                        obtienegkeR = dataSnapshot.child("gkeR").getValue().toString();
                       // obtienecorreo = dataSnapshot.child("email4R").getValue().toString();
                        revisacesso = dataSnapshot.child("idgruR").getValue().toString();
                        //   muestranombre.setText(oNombre);
                        Log.d("kimbo11",obtienegkeR);
                        Log.d("pato", revisacesso);


                        getkey0 = dataSnapshot.getKey();
                        if (!getkey0.equals("")) {
                            Log.d("kimbo6", getkey0);
                            //actualizar parametros
                            Map<String, Object> personMap = new HashMap<>();
                            personMap.put("gkeR", getkey0.toString());
                            rootRef.child("Users").child("Clients").child(id).updateChildren(personMap);
                            Log.d("vacio", "esta vacio");
                        } else {
                            Log.d("lleno", "tiene datos");
                        }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


}
