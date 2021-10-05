package com.educapyoficial.educapy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.educapyoficial.educapy.models.EducapyModelUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;


public class RegisterClientActivity extends AppCompatActivity {

    //creo instancias de los objetos
    EditText cajatextInputName1, cajatextInputapellidos1, cajatextInputlollaman1, cajatextInputlugarnacimiento1, cajatextInputEdad1, cajatextInputpeso1,
            cajatextInputestatura1, cajatextInputdomicilio1, cajatextInputtelefono1, cajatextInputtelefono1de2;
    Spinner spinner1sexoT;
    ArrayAdapter<String> mAdapter1;
    private CircleImageView mCircleImageBack, mCircleImageNext;

    String almacenasexo;

    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    EducapyModelUser educapyModelUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);
        mCircleImageBack = findViewById(R.id.circleImageBack1);
        mCircleImageNext = findViewById(R.id.circleImageNext1);
        cajatextInputName1 = findViewById(R.id.textInputName1);
        cajatextInputapellidos1 = findViewById(R.id.textInputapellidos1);
        cajatextInputlollaman1 = findViewById(R.id.textInputlollaman1);
        cajatextInputlugarnacimiento1 = findViewById(R.id.textInputlugarnacimiento1);
        cajatextInputEdad1 = findViewById(R.id.textInputEdad1);
        cajatextInputpeso1 = findViewById(R.id.textInputpeso1);
        cajatextInputestatura1 = findViewById(R.id.textInputestatura1);
        cajatextInputdomicilio1 = findViewById(R.id.textInputdomicilio1);
        cajatextInputtelefono1 = findViewById(R.id.textInputtelefono1);
        cajatextInputtelefono1de2 = findViewById(R.id.textInputtelefono1de2);
        spinner1sexoT = (Spinner) findViewById(R.id.spinnersexoT);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mAdapter1 = new ArrayAdapter<String>(RegisterClientActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.sexoQ));
        spinner1sexoT.setAdapter(mAdapter1);
        mCircleImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent myIntent = new Intent(RegisterClientActivity.this, principal.class);
                //startActivity(myIntent);
                finish();
            }
        });


        spinner1sexoT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ((TextView) spinner1sexoT.getSelectedView()).setTextColor(Color.BLACK);

                String seleccionEdad = spinner1sexoT.getSelectedItem().toString();

                if (seleccionEdad.equals("Seleccione Sexo")) {
                    almacenasexo = "";
                }
                if (seleccionEdad.equals("niño")) {
                    almacenasexo = "niño";
                }
                if (seleccionEdad.equals("niña")) {
                    almacenasexo = "niña";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        mCircleImageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (cajatextInputName1.getText().toString().equals("") || cajatextInputapellidos1.getText().toString().equals("") || cajatextInputlollaman1.getText().toString().equals("") || cajatextInputlugarnacimiento1.getText().toString().equals("") || cajatextInputEdad1.getText().toString().equals("") || cajatextInputpeso1.getText().toString().equals("") || cajatextInputestatura1.getText().toString().equals("") || cajatextInputdomicilio1.getText().toString().equals("") || cajatextInputtelefono1.getText().toString().equals("") || cajatextInputtelefono1de2.getText().toString().equals("") || almacenasexo.equals("")) { // compruebo que no este vacio los campos antes de enviar
                    validacion();
                } else {
                    Intent myIntent = new Intent(RegisterClientActivity.this, RegisterClientActivity2.class);
                    myIntent.putExtra("nombre1T", cajatextInputName1.getText().toString());
                    myIntent.putExtra("apellidos1T", cajatextInputapellidos1.getText().toString());
                    myIntent.putExtra("lollaman1T", cajatextInputlollaman1.getText().toString());
                    myIntent.putExtra("lugarnacimiento11T", cajatextInputlugarnacimiento1.getText().toString());
                    myIntent.putExtra("Edad11T", cajatextInputEdad1.getText().toString());
                    myIntent.putExtra("peso1T", cajatextInputpeso1.getText().toString());
                    myIntent.putExtra("estatura1T", cajatextInputestatura1.getText().toString());
                    myIntent.putExtra("domicilio1T", cajatextInputdomicilio1.getText().toString());
                    myIntent.putExtra("telefono1T", cajatextInputtelefono1.getText().toString());
                    myIntent.putExtra("telefono1de2T", cajatextInputtelefono1de2.getText().toString());
                    myIntent.putExtra("sexo1T", almacenasexo);
                    myIntent.putExtra("educapyModelUser", educapyModelUser);
                    // myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL BORRAR LA ACTIVIDAD COMPLETA Y NO REGRESAR AQUI
                    startActivity(myIntent);
                }


            }
        });

        cargarDatos();
    }


    public void cargarDatos() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            Query query = mDatabase.child("Users").child("Clients").orderByChild("emailR").equalTo(email);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (!dataSnapshot.getChildren().iterator().hasNext()) {

                    } else {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            educapyModelUser = data.getValue(EducapyModelUser.class);
                            if (educapyModelUser != null) {

                                cajatextInputName1.setText(educapyModelUser.getNombre1R());
                                cajatextInputapellidos1.setText(educapyModelUser.getApellidos1R());
                                cajatextInputlollaman1.setText(educapyModelUser.getLollaman1R());
                                cajatextInputlugarnacimiento1.setText(educapyModelUser.getLugarnacimiento1R());
                                cajatextInputEdad1.setText(educapyModelUser.getEdad1R());
                                cajatextInputpeso1.setText(educapyModelUser.getPeso1R());
                                cajatextInputestatura1.setText(educapyModelUser.getEstatura1R());
                                cajatextInputdomicilio1.setText(educapyModelUser.getDomicilio1R());
                                cajatextInputtelefono1.setText(educapyModelUser.getTelefono1R());
                                cajatextInputtelefono1de2.setText(educapyModelUser.getTelefono2R());

                                int spinnerPosition = mAdapter1.getPosition(educapyModelUser.getAlmacenasexo1R());
                                spinner1sexoT.setSelection(spinnerPosition);

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
            Toast.makeText(this, "No Hay datos.", Toast.LENGTH_SHORT).show();
        }


    }


    private void validacion() {
        /*1*/
        String nombre1t = cajatextInputName1.getText().toString(); //utilizo esta forma para obtener el valor de los campos y validar los campos vacios  atravez de la clase validacion
        String apellido1t = cajatextInputapellidos1.getText().toString();
        String lollaman1T = cajatextInputlollaman1.getText().toString();
        String lugarnacimiento1T = cajatextInputlugarnacimiento1.getText().toString();
        String edad1T = cajatextInputEdad1.getText().toString();
        String peso1T = cajatextInputpeso1.getText().toString();
        String estatura1T = cajatextInputestatura1.getText().toString();
        String domicilio1T = cajatextInputdomicilio1.getText().toString();
        String telefono1T = cajatextInputtelefono1.getText().toString();
        String telefono2T = cajatextInputtelefono1de2.getText().toString();


        if (nombre1t.equals("")) {
            cajatextInputName1.setError("Requerido");
        } else if (apellido1t.equals("")) {
            cajatextInputapellidos1.setError("Required");
        } else if (lollaman1T.equals("")) {
            cajatextInputlollaman1.setError("Required");
        } else if (lugarnacimiento1T.equals("")) {
            cajatextInputlugarnacimiento1.setError("Required");
        } else if (edad1T.equals("")) {
            cajatextInputEdad1.setError("Required");
        } else if (peso1T.equals("")) {
            cajatextInputpeso1.setError("Required");
        } else if (estatura1T.equals("")) {
            cajatextInputestatura1.setError("Required");
        } else if (domicilio1T.equals("")) {
            cajatextInputdomicilio1.setError("Required");
        } else if (telefono1T.equals("")) {
            cajatextInputtelefono1.setError("Required");
        } else if (telefono2T.equals("")) {
            cajatextInputtelefono1de2.setError("Required");
        } else if (almacenasexo.equals("")) {
            Toast.makeText(this, "Seleccione alguna opcion de sexo", Toast.LENGTH_SHORT).show();
        }
    }


}

