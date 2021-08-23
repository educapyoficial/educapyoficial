package com.educapyoficial.educapy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.educapyoficial.educapy.includes.MyToolbar;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class registroProfesores extends AppCompatActivity {

    EditText mtextInputName,mtextInputEmail;
    Button mButtonReggister;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_profesores);
        MyToolbar.show(this, "Actualizacion de datos Profesor", true);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mtextInputName = (EditText) findViewById(R.id.textInputName);
        mtextInputEmail = (EditText) findViewById(R.id.textInputEmail);
        mButtonReggister = (Button) findViewById(R.id.btnRegister);
        mButtonReggister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRegister();
            }
        });
    }


    public  void clickRegister() {
        Map<String, Object> personmap = new HashMap<>();

        personmap.put("nombre", mtextInputName.getText().toString());
        personmap.put("correo", mtextInputEmail.getText().toString());
        personmap.put("es_profesor", "no");
        personmap.put("gkeR",user.getUid());

        mDatabase.child("Profesores").child("id").child(user.getUid()).updateChildren(personmap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(registroProfesores.this, "Alumno Actualizado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(registroProfesores.this, principal.class);
             //   intent.putExtra("nombrealumnoT",cajatextInputName1);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CLIENTE NO REGRESE A REGISTRARSE
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(registroProfesores.this, "Fallo al Actualizar", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(registroProfesores.this, MenuProfesores.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
        startActivity(intent);
    }
}