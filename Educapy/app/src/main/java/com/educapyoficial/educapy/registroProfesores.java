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
import com.educapyoficial.educapy.models.CursosModel;
import com.educapyoficial.educapy.models.EducapyModelUserProfesor;
import com.educapyoficial.educapy.utils.UIHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class registroProfesores extends AppCompatActivity {

    EditText mtextInputName,mtextInputEmail;
    Button mButtonReggister;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference mDatabase;
    private EducapyModelUserProfesor educapyModelUserProfesor;
    private String uid;
    UIHelper uiHelper = new UIHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_profesores);
        MyToolbar.show(this, "Actualizacion de datos Profesor", true);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
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
        cargarDatosProfesor();
    }

    public void cargarDatosProfesor() {
        uiHelper.showLoadingDialog("Cargando....");
        mDatabase.child("Profesores").child("id").orderByChild("uidfirebase").equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                uiHelper.dismissLoadingDialog();
                if (!snapshot.getChildren().iterator().hasNext()) {
                }else{
                    for (DataSnapshot data : snapshot.getChildren()) {
                        educapyModelUserProfesor = data.getValue(EducapyModelUserProfesor.class);
                        mtextInputEmail.setText(educapyModelUserProfesor.getCorreo());
                        mtextInputName.setText(educapyModelUserProfesor.getNombre());
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                uiHelper.dismissLoadingDialog();
            }
        });

    }


    public  void clickRegister() {
        Map<String, Object> personmap = new HashMap<>();
        personmap.put("nombre", mtextInputName.getText().toString());
        personmap.put("correo", mtextInputEmail.getText().toString());
        educapyModelUserProfesor.setNombre(mtextInputName.getText().toString().toUpperCase());
        educapyModelUserProfesor.setCorreo(mtextInputEmail.getText().toString().toUpperCase());
        //personmap.put("es_profesor", "no");
        //personmap.put("gkeR",user.getUid());
        mDatabase.child("Profesores").child("id").child(educapyModelUserProfesor.getUid()).setValue(educapyModelUserProfesor);
        Toast.makeText(this, "Registro Actualizado", Toast.LENGTH_LONG).show();
        finish();
//        mDatabase.child("Profesores").child("id").orderByChild("uidfirebase").equalTo(user.getUid()).updateChildren(personmap).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Toast.makeText(registroProfesores.this, "Alumno Actualizado", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(registroProfesores.this, principal.class);
//             //   intent.putExtra("nombrealumnoT",cajatextInputName1);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CLIENTE NO REGRESE A REGISTRARSE
//                startActivity(intent);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(registroProfesores.this, "Fallo al Actualizar", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}