package com.educapyoficial.educapy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.educapyoficial.educapy.Calendario.CalendarAdd;
import com.educapyoficial.educapy.Calendario.CalendarAddProfesor;
import com.educapyoficial.educapy.Evaluacion.evaluacionVentana;
import com.educapyoficial.educapy.Evaluacion.evaluacionVentanaProfesor;
import com.educapyoficial.educapy.SendNotificationPack.Token;
import com.educapyoficial.educapy.adapters.SpinnerAdapter;
import com.educapyoficial.educapy.listaAsistencia.selectorAsistenciaProfesor;
import com.educapyoficial.educapy.models.CursosModel;
import com.educapyoficial.educapy.models.EducapyModelUserProfesor;
import com.educapyoficial.educapy.utils.UIHelper;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuProfesores extends AppCompatActivity {

    CardView tarjeta1, tarjeta2, tarjeta3, tarjeta5, tarjeta6, tarjeta7, tarjeta8;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference mDatabase;
    String compruebaUsuario;
    String uid;
    private FirebaseAuth mAuth;
    private CircleImageView mCircleImageBack;

    private Button mButtonADMIN;
    SpinnerAdapter<CursosModel> mAdapterSpinner;
    private Spinner spinnerCursos;
    private String uidCurso;

    EducapyModelUserProfesor educapyModelUserProfesor;
    ArrayList<CursosModel> itemsCursos;

    UIHelper uiHelper = new UIHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_profesores);
        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        if (mAuth.getCurrentUser() != null) {
            uid = mAuth.getCurrentUser().getUid(); //aqui obtengo el id del usuario logueado
        }
        //compruebaRegistroEnRealtime();
        tarjeta1 = findViewById(R.id.Card1notifica);
        tarjeta2 = findViewById(R.id.Card2chat);
        tarjeta3 = findViewById(R.id.Card3fotos);
        tarjeta5 = findViewById(R.id.Card7registro);
        tarjeta6 = findViewById(R.id.Card6calendario);
        tarjeta7 = findViewById(R.id.Card7evaluacion);
        tarjeta8 = findViewById(R.id.Card7PerfilProfesor);
        mCircleImageBack = findViewById(R.id.circleImageBackT);
        mButtonADMIN = (Button) findViewById(R.id.btnGoToadmi);

        String obtienecorreo = user.getEmail();
//        if (obtienecorreo.equals("profematiaseducapy@gmail.com") || obtienecorreo.equals("letogon@gmail.com") || obtienecorreo.equals("aguara123@gmail.com")) {
//            mButtonADMIN.setVisibility(View.VISIBLE);
//        } else {
//            Log.d("prueba", "NO es administrador");
//        }

        mButtonADMIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuProfesores.this, menuadministrador.class));
                //finish();
            }
        });


        mCircleImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                Toast.makeText(MenuProfesores.this, "Finalizando...", Toast.LENGTH_SHORT).show();

            }
        });

        tarjeta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mDatabase.child("Profesores").child("id").orderByChild("uid").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.exists()) {
                //DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                //String oNombre = dataSnapshot.child("es_profesor").getValue().toString();
                Intent intent = new Intent(MenuProfesores.this, administradorNotificaProfesor.class);
                intent.putExtra("uidCurso", uidCurso);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
                startActivity(intent);

//                        } else {
//                            Toast.makeText(MenuProfesores.this, "actualizar primero mi registro", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                    }
//                });


            }
        });

        tarjeta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuProfesores.this, principalchat.class);
                intent.putExtra("uidCurso", educapyModelUserProfesor.getUidCurso());
                startActivity(intent);

//                mDatabase.child("Profesores").child("id").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.exists()) {
//                            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

                //showAccessChat();


//                        } else {
//                            Toast.makeText(MenuProfesores.this, "actualizar primero mi registro", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                    }
//                });
//
//                // Toast.makeText(MenuProfesores.this, "Se esta construyendo esta parte", Toast.LENGTH_SHORT).show();
            }
        });
        tarjeta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                mDatabase.child("Profesores").child("id").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.exists()) {
//                            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                //String oNombre = dataSnapshot.child("es_profesor").getValue().toString();

                //if (oNombre.equals("si")) {
                //  compruebaUsuario = "1";
                //   Toast.makeText(MenuProfesores.this, "con acceso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MenuProfesores.this, selectorvisualProfesor.class);
                intent.putExtra("uidCurso", uidCurso);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
                startActivity(intent);

                //  } else {
                //   compruebaUsuario = "0";
                //   Toast.makeText(MenuProfesores.this, "NO TIENES ACCESO CONTACTA AL ADMINISTRADOR", Toast.LENGTH_SHORT).show();
                // }
//                        } else {
//                            Toast.makeText(MenuProfesores.this, "actualizar primero mi registro", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                    }
//                });
//
//                //  Toast.makeText(MenuProfesores.this, "Se esta construyendo esta parte", Toast.LENGTH_SHORT).show();
            }
        });


        tarjeta6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                mDatabase.child("Profesores").child("id").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.exists()) {
                //DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                //String oNombre = dataSnapshot.child("es_profesor").getValue().toString();

                //if (oNombre.equals("si")) {
                //  compruebaUsuario = "1";
                //   Toast.makeText(MenuProfesores.this, "con acceso", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MenuProfesores.this, CalendarAddProfesor.class);
                intent.putExtra("uidCurso", uidCurso);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
                startActivity(intent);

                // } else {
                //   compruebaUsuario = "0";
                //      Toast.makeText(MenuProfesores.this, "NO TIENES ACCESO CONTACTA AL ADMINISTRADOR", Toast.LENGTH_SHORT).show();
                // }
//                        } else {
//                            Toast.makeText(MenuProfesores.this, "actualizar primero mi registro", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                    }
//                });

                //  Toast.makeText(MenuProfesores.this, "Se esta construyendo esta parte", Toast.LENGTH_SHORT).show();
            }
        });

        tarjeta7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mDatabase.child("Profesores").child("id").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.exists()) {
                //DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                //String oNombre = dataSnapshot.child("es_profesor").getValue().toString();

                //if (oNombre.equals("si")) {
                //  compruebaUsuario = "1";
                //   Toast.makeText(MenuProfesores.this, "con acceso", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MenuProfesores.this, evaluacionVentanaProfesor.class);
                intent.putExtra("uidCurso", uidCurso);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
                startActivity(intent);

                //} else {
                //   compruebaUsuario = "0";
                //    Toast.makeText(MenuProfesores.this, "NO TIENES ACCESO CONTACTA AL ADMINISTRADOR", Toast.LENGTH_SHORT).show();
                //}
//                        } else {
//                            Toast.makeText(MenuProfesores.this, "actualizar primero mi registro", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                    }
//                });

            }
        });
//
        tarjeta5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mDatabase.child("Profesores").child("id").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.exists()) {
                //DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                //String oNombre = dataSnapshot.child("es_profesor").getValue().toString();

                // if (oNombre.equals("si")) {
                //  compruebaUsuario = "1";
                //   Toast.makeText(MenuProfesores.this, "con acceso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MenuProfesores.this, selectorAsistenciaProfesor.class);
                intent.putExtra("uidCurso", uidCurso);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
                startActivity(intent);

                //} else {
                //   compruebaUsuario = "0";
                //    Toast.makeText(MenuProfesores.this, "NO TIENES ACCESO CONTACTA AL ADMINISTRADOR", Toast.LENGTH_SHORT).show();
                //}
//                        } else {
//                            Toast.makeText(MenuProfesores.this, "actualizar primero mi registro", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                    }
//                });
                // Toast.makeText(menuadministrador.this, "Se esta construyendo esta parte", Toast.LENGTH_SHORT).show();
            }
        });

        tarjeta8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mDatabase.child("Profesores").child("id").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.exists()) {
//                            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                //String oNombre = dataSnapshot.child("es_profesor").getValue().toString();

                //if (oNombre.equals("si")) {
                //  compruebaUsuario = "1";
                //   Toast.makeText(MenuProfesores.this, "con acceso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MenuProfesores.this, registroProfesores.class);
                //   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
                startActivity(intent);

                //} else {
                //   compruebaUsuario = "0";
                //     Toast.makeText(MenuProfesores.this, "NO TIENES ACCESO CONTACTA AL ADMINISTRADOR", Toast.LENGTH_SHORT).show();
                // }
//                        } else {
//                            Toast.makeText(MenuProfesores.this, "actualizar primero mi registro", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                    }
//                });
            }
        });

        spinnerCursos = (Spinner) findViewById(R.id.spinnerCursos);
        spinnerCursos.setSelection(0);
        mAdapterSpinner = new SpinnerAdapter<>(
                this, R.layout.item_spinner, new ArrayList<>());
        spinnerCursos.setPrompt("Seleccionar Curso");
        spinnerCursos.setAdapter(mAdapterSpinner);
        spinnerCursos.setSelection(mAdapterSpinner.getCount());
        spinnerCursos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                try {
                    uidCurso = ((CursosModel) spinnerCursos
                            .getItemAtPosition(position)).getUid();
                } catch (Exception e) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO Auto-generated method stub

            }

        });

        listarDatosCurso();
    }


    public void cargarDatosProfesor() {
        uiHelper.showLoadingDialog("Cargando...");
        mDatabase.child("Profesores").child("id").orderByChild("uidfirebase").equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                uiHelper.dismissLoadingDialog();
                if (!snapshot.getChildren().iterator().hasNext()) {

                } else {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        educapyModelUserProfesor = data.getValue(EducapyModelUserProfesor.class);
                        if (educapyModelUserProfesor.getUidCursosList() != null) {
                            for (String e : educapyModelUserProfesor.getUidCursosList()) {
                                for (CursosModel cursosModel : itemsCursos) {
                                    if (cursosModel.getUid().equals(e)) {
                                        mAdapterSpinner.addList(cursosModel);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                uiHelper.dismissLoadingDialog();
            }
        });
    }

    private void listarDatosCurso() {
        //uiHelper.showLoadingDialog("Cargando...");
        mDatabase.child("Cursos").child("id").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                uiHelper.dismissLoadingDialog();
                itemsCursos = new ArrayList<>();
                CursosModel p = new CursosModel();
                p.setUid("0");
                p.setCursos("Seleccionar......");
                itemsCursos.add(p);
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    p = new CursosModel();
                    p = objSnaptshot.getValue(CursosModel.class);
                    p.setUid(objSnaptshot.getKey());
                    itemsCursos.add(p);
                }
                cargarDatosProfesor();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                uiHelper.dismissLoadingDialog();
            }
        });
    }


    private void showAccessChat() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MenuProfesores.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(MenuProfesores.this).inflate(
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
                        Toast.makeText(MenuProfesores.this, "Acceso Autorizado", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MenuProfesores.this, "Fallo al Actualizar", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(MenuProfesores.this, "Acceso Bloquado", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MenuProfesores.this, "Fallo al Actualizar", Toast.LENGTH_SHORT).show();
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


    private void compruebaRegistroEnRealtime() {
        //FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        Token token = new Token(refreshToken);
        FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);
        //   FirebaseDatabase.getInstance().getReference("Calificaciones").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);

        uid = mAuth.getCurrentUser().getUid(); //aqui obtengo el id del usuario logueado
        mDatabase.child("Profesores").child("id").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //lo que hago aqui es comprobar si existe registro en realtime de este usuario logueado
                if (dataSnapshot.exists()) {

                } else {
                    //Toast.makeText(MenuProfesores.this, "actualizar primero mi PERFIL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
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


}