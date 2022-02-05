package com.educapyoficial.educapy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.educapyoficial.educapy.Calendario.CalendarAdd;
import com.educapyoficial.educapy.listaAsistencia.selectorAsistencia;
import com.educapyoficial.educapy.Evaluacion.evaluacionVentana;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class menuadministrador extends AppCompatActivity {

    CardView tarjeta1, tarjeta2, tarjeta3, tarjeta4, tarjeta5, tarjeta6, tarjeta7, tarjeta8, tarjeta9, tarjeta10, tarjeta11, tarjeta12, tarjeta13, tarjeta14, tarjeta15, tarjeta16, tarjeta17, tarjeta18;
    TextView texttitulo, text1, text2, text3, text4, text5, text6, text7, text8, text9, text10, text11, text12, text13;
    ImageView imagenTitulo, imagen1, imagen2, imagen3, imagen4, imagen5, imagen6, imagen7, imagen8, imagen9, imagen10, imagen11, imagen12, imagen13, imagen14, imagen15;
    SharedPreferences mPref;
    Thread t = null;
    private CircleImageView mCircleImageBackR;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuadministrador);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mPref = getApplicationContext().getSharedPreferences("typeUser2", MODE_PRIVATE);
        final SharedPreferences.Editor editor = mPref.edit();
        mCircleImageBackR = findViewById(R.id.circleImageBackT);
        imagen1 = findViewById(R.id.img1);
        imagen2 = findViewById(R.id.img3);
        imagen3 = findViewById(R.id.img2);
        imagen4 = findViewById(R.id.img4);
        imagen5 = findViewById(R.id.img5);
        imagen6 = findViewById(R.id.img6);
        imagen7 = findViewById(R.id.img7);
        texttitulo = findViewById(R.id.txttitulo);
        imagenTitulo = findViewById(R.id.imgtitulo);
        tarjeta1 = findViewById(R.id.Card1notifica);
        tarjeta2 = findViewById(R.id.Card2chat);
        tarjeta3 = findViewById(R.id.Card3fotos);
        tarjeta4 = findViewById(R.id.Card4alta);
        tarjeta5 = findViewById(R.id.Card7registro);
        tarjeta6 = findViewById(R.id.Card6calendario);
        tarjeta7 = findViewById(R.id.Card7evaluacion);
        tarjeta8 = findViewById(R.id.Card8altaProfesores);
        tarjeta9 = findViewById(R.id.Card9altaCurso);

        mCircleImageBackR.setOnClickListener(new View.OnClickListener() {
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

                Toast.makeText(menuadministrador.this, "Finalizando...", Toast.LENGTH_SHORT).show();
            }
        });

        tarjeta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuadministrador.this, administradorNotifica.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
                startActivity(intent);
            }
        });


        tarjeta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAccessChat();
               // Toast.makeText(menuadministrador.this, "Se esta construyendo esta parte", Toast.LENGTH_SHORT).show();
            }
        });
        tarjeta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(menuadministrador.this, selectorvisualadmin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
                startActivity(intent);


              //  Toast.makeText(menuadministrador.this, "Se esta construyendo esta parte", Toast.LENGTH_SHORT).show();
            }
        });

        tarjeta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuadministrador.this, altabajaUsuarios.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
                startActivity(intent);
            }
        });

        tarjeta5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuadministrador.this, selectorAsistencia.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
                startActivity(intent);

              //  Toast.makeText(menuadministrador.this, "Se esta construyendo esta parte", Toast.LENGTH_SHORT).show();
            }
        });

        tarjeta6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(menuadministrador.this, CalendarAdd.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
                startActivity(intent);

               /*
                Intent intent = new Intent(menuadministrador.this, administradorNotifica.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
                startActivity(intent);

                 */
            //    Toast.makeText(menuadministrador.this, "Se esta construyendo esta parte", Toast.LENGTH_SHORT).show();
            }
        });

        tarjeta7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(menuadministrador.this, evaluacionVentana.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
                startActivity(intent);
            }
        });

        tarjeta8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(menuadministrador.this, altabajaUsuariosProfesor.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
                startActivity(intent);
            }
        });

        tarjeta9.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuadministrador.this, AltabajaCursos.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
                startActivity(intent);
            }
        });

    }

    private void vamosalogin() {
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        moveTaskToBack(true);

    }

    @SuppressLint("NewApi")
    public void salida() {
        finishAffinity();
    }

    private void showAccessChat() {

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(menuadministrador.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(menuadministrador.this).inflate(
                R.layout.layout_token_dialog,
                (ConstraintLayout) findViewById(R.id.layoutDialogtokenRR)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitleRToken)).setText(getResources().getString(R.string.alert_success));
        // userInput = (EditText) view.findViewById(R.id.txttokenRToken);
       // final EditText caja1 = (EditText) view.findViewById(R.id.txttokenRToken);

//        almacenado = userInput.getText().toString();
        ((Button) view.findViewById(R.id.buttonYesRRoken)).setText(getResources().getString(R.string.activar));
        ((Button) view.findViewById(R.id.buttonNoRRoken)).setText(getResources().getString(R.string.bloquear));
        ((ImageView) view.findViewById(R.id.imageIconRToken)).setImageResource(R.drawable.lapiz_y_regla);
        final androidx.appcompat.app.AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.buttonYesRRoken).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombret = "1";  //utilizo esta forma para obtener el valor de los campos y validar los campos vacios  atravez de la clase validacion

                Map<String, Object> personmap = new HashMap<>();
                // Toast.makeText(administrador.this, "", Toast.LENGTH_SHORT).show();
                personmap.put("codigo", nombret);

                mDatabase.child("codAcess").child("Accesso").updateChildren(personmap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(menuadministrador.this, "Acceso Autorizado", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(menuadministrador.this, "Fallo al Actualizar", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.dismiss();
            }
        });

        view.findViewById(R.id.buttonNoRRoken).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombret = "0";  //utilizo esta forma para obtener el valor de los campos y validar los campos vacios  atravez de la clase validacion

                Map<String, Object> personmap = new HashMap<>();
                // Toast.makeText(administrador.this, "", Toast.LENGTH_SHORT).show();
                personmap.put("codigo", nombret);

                mDatabase.child("codAcess").child("Accesso").updateChildren(personmap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(menuadministrador.this, "Acceso Bloquado", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(menuadministrador.this, "Fallo al Actualizar", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Intent intent = new Intent(menuadministrador.this, principal.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
        //startActivity(intent);
    }

}



