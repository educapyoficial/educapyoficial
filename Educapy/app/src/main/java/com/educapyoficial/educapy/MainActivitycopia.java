package com.educapyoficial.educapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivitycopia extends AppCompatActivity {

    private FirebaseAuth mfirebaseAuth; //autenticacion google
    private FirebaseAuth.AuthStateListener mauthListener; //autenticacion google
    public static final int SIGN_IN =1; //autenticacion google

    List<AuthUI.IdpConfig> providers = Arrays.asList( //autenticacion google
            new AuthUI.IdpConfig.GoogleBuilder().build() //autenticacion google
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //..................................//autenticacion Google adentro del onCreate
        mfirebaseAuth = FirebaseAuth.getInstance();
        mauthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null)
                {
                    vamosahome();
                //    Toast.makeText(MainActivity.this, "Login con Exito", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(providers)
                                    .setIsSmartLockEnabled(false)
                                    .build(),SIGN_IN
                    );
                }
            }
        };
        //..................................//autenticacion Google
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }

    @Override //autenticacion Google
    protected void onResume() {
        super.onResume();
        mfirebaseAuth.addAuthStateListener(mauthListener);
    }

    @Override //autenticacion Google
    protected void onPause() {
        super.onPause();
        mfirebaseAuth.removeAuthStateListener(mauthListener);
    }

    private void vamosahome() { //autenticacion Google
        Intent i = new Intent(this,principal.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}
