package com.educapyoficial.educapy;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.educapyoficial.educapy.models.EducapyModelUser;
import com.educapyoficial.educapy.models.EducapyModelUserProfesor;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {

    //creo instancias de los objetos
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    AlertDialog mDialog;
    SharedPreferences mPref;
    //autentication Google

    Button mCircleAutenticationGoogle, mCircleAutenticationGoogleProfesores;
    private GoogleSignInClient mGoogleSignInClient, mGoogleSignClient2;
    private final static int RC_SIGN_IN = 123;
    private final static int RC_SIGN_IN2 = 124;

    String revisaUsuario = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //..................................//autenticacion Google adentro del onCreate
        mPref = getApplicationContext().getSharedPreferences("validadmiRT", MODE_PRIVATE);
        //inicializo los parametros en el oncreate

        mCircleAutenticationGoogle = findViewById(R.id.btnlogin);
        mCircleAutenticationGoogleProfesores = findViewById(R.id.btnRegisterProfesores);
        //   stopService(new Intent(ventanaLogin.this, contructorMusica.class)); //detener musica de fondo

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        createRequest();
        mCircleAutenticationGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
                revisaUsuario = "1";

            }
        });

        mCircleAutenticationGoogleProfesores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInProfesor();
                Toast.makeText(MainActivity.this, "profesores", Toast.LENGTH_SHORT).show();
                revisaUsuario = "2";
            }
        });

        mDialog = new SpotsDialog.Builder().setContext(MainActivity.this).setMessage("Espere Un Momento").build();

        //evaluarUsuario();
        //evaluarUsuarioProfesor();


    }

    //aqui va el codigo para autenticacion por google
    private void createRequest() {

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //GoogleSignInOptions gso2 = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        //         .requestIdToken(getString(R.string.default_web_client_id))
        //         .requestEmail()
        //        .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        //mGoogleSignClient2 = GoogleSignIn.getClient(this, gso2);

    }


    @Override
    public void onStart() {


        super.onStart();
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        signInIntent.putExtra("identificaLogin", "alumnoLogin");
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signInProfesor() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        signInIntent.putExtra("identificaLogin", "profesorLogin");
        startActivityForResult(signInIntent, RC_SIGN_IN2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = (GoogleSignInAccount) task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                // ...
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        if (requestCode == RC_SIGN_IN2) {
            Task task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = (GoogleSignInAccount) task.getResult(ApiException.class);
                firebaseAuthWithGoogleProfesores(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                // ...
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }


    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //preguntamos si el usuario esta registrado como profesor o como padre
                            evaluarUsuario();
                            /*
                            if (revisaUsuario.equals("1")) {
                                //FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(getApplicationContext(), principal.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                //FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(getApplicationContext(), MenuProfesores.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
*/

                        } else {
                            Toast.makeText(MainActivity.this, "Sorry auth failed.", Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

    public void evaluarUsuarioProfesor() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Toast.makeText(MainActivity.this, "Hay ya un usuario logueado.", Toast.LENGTH_SHORT).show();
            //String id = user.getUid(); //aqui obtengo el id del usuario logueado
            String email = user.getEmail();
            Query query = mDatabase.child("Profesores").child("id").orderByChild("correo").equalTo(email.toUpperCase());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (!dataSnapshot.getChildren().iterator().hasNext()) {

                    } else {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            EducapyModelUserProfesor educapyModelUserProfesor = data.getValue(EducapyModelUserProfesor.class);
                            if (educapyModelUserProfesor != null) {
                                if (educapyModelUserProfesor.getEstado() != null && !educapyModelUserProfesor.getEstado().equals("I")) {
                                    //educapyModelUserProfesor.setEstado("A");
                                    educapyModelUserProfesor.setGkeR(user.getUid());
                                    educapyModelUserProfesor.setUid(data.getKey());
                                    educapyModelUserProfesor.setUidfirebase(user.getUid());
                                    mDatabase = FirebaseDatabase.getInstance().getReference();
                                    mDatabase.child("Profesores").child("id").child(educapyModelUserProfesor.getUid()).setValue(educapyModelUserProfesor);
                                    SharedPreferences.Editor editor = mPref.edit();
                                    editor.putString("uidProfesor", educapyModelUserProfesor.getUid());
                                    editor.commit();
                                    Intent intent = new Intent(getApplicationContext(), MenuProfesores.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                } else {
                                    GoogleSignIn.getClient(MainActivity.this, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build())
                                            .signOut();
                                    mAuth.signOut();
                                    Toast.makeText(MainActivity.this, "El usuario " + educapyModelUserProfesor.getCorreo() + " se encuentra inactivado, Consulte con el administrador.", Toast.LENGTH_LONG).show();
                                }


                            }
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d("Error dd", databaseError.getMessage());
                }
            });
        } else {
            GoogleSignIn.getClient(MainActivity.this, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build())
                    .signOut();
            mAuth.signOut();
            Toast.makeText(MainActivity.this, "No Hay usuario logueado.", Toast.LENGTH_SHORT).show();
        }
/*
        mDatabase.child("Profesores").child("id")..get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
 EducapyModelUserProfesor educapyModelUserProfesor = task.getResult().getValue(EducapyModelUserProfesor.class);

                if (educapyModelUserProfesor == null) {
                    Log.d("firebase", "no vino nada entonces no es profe", task.getException());
                    Intent intent = new Intent(getApplicationContext(), principal.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    Intent intent = new Intent(getApplicationContext(), MenuProfesores.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }


            }
        });
 */
    }

    public void evaluarUsuario() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Toast.makeText(MainActivity.this, "Hay ya un usuario logueado.", Toast.LENGTH_SHORT).show();
            //String id = user.getUid(); //aqui obtengo el id del usuario logueado
            String email = user.getEmail();
            Query query = mDatabase.child("Users").child("Clients").orderByChild("emailR").equalTo(email);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (!dataSnapshot.getChildren().iterator().hasNext()) {

                    } else {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            EducapyModelUser educapyModelUser = data.getValue(EducapyModelUser.class);
                            if (educapyModelUser != null) {
                                if (educapyModelUser.getEstado() != null && !educapyModelUser.getEstado().equals("I")) {
                                    educapyModelUser.setGkeR(user.getUid());
                                    educapyModelUser.setUidfirebase(user.getUid());
                                    educapyModelUser.setEstado("A");
                                    mDatabase.child("Users").child("Clients").child(educapyModelUser.getUid()).setValue(educapyModelUser);
                                    SharedPreferences.Editor editor = mPref.edit();
                                    editor.putString("uid", educapyModelUser.getUid());
                                    editor.commit();
                                    Intent intent = new Intent(getApplicationContext(), principal.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                } else {
                                    GoogleSignIn.getClient(MainActivity.this, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build())
                                            .signOut();

                                    mAuth.signOut();
                                    Toast.makeText(MainActivity.this, "El usuario " + educapyModelUser.getEmailR() + " se encuentra inactivado, Consulte con el administrador.", Toast.LENGTH_LONG).show();
                                }

                            }
                        }
                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d("Error dd", databaseError.getMessage());
                }
            });
        } else {
            GoogleSignIn.getClient(MainActivity.this, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build())
                    .signOut();
            mAuth.signOut();
            Toast.makeText(MainActivity.this, "No Hay usuario logueado.", Toast.LENGTH_SHORT).show();
        }

/*
        mDatabase.child("Profesores").child("id")..get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
 EducapyModelUserProfesor educapyModelUserProfesor = task.getResult().getValue(EducapyModelUserProfesor.class);

                if (educapyModelUserProfesor == null) {
                    Log.d("firebase", "no vino nada entonces no es profe", task.getException());
                    Intent intent = new Intent(getApplicationContext(), principal.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    Intent intent = new Intent(getApplicationContext(), MenuProfesores.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }


            }
        });

 */
    }


    private void firebaseAuthWithGoogleProfesores(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            evaluarUsuarioProfesor();
                        } else {
                            Toast.makeText(MainActivity.this, "No se pudo autenticar, por favor intentelo nuevamente.", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }


    //fin del codigo por autenticacion por google



    /*
    //creo el registro con este metodo
    private void Registro() {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);

    }

     */


    //con este metodo realizo el login con lo que recopile en las carpetas que contienen las clases de providers y models
}
