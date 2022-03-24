package com.educapyoficial.educapy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.educapyoficial.educapy.adapters.SpinnerAdapter;
import com.educapyoficial.educapy.models.CursosModel;
import com.educapyoficial.educapy.models.EducapyModelUserProfesor;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegistrarReferenciaActivity extends AppCompatActivity {

    Spinner spinnerRef;
    private ArrayAdapter mAdapterSpinner;
    private String referencia;
    private DatabaseReference mDatabase;
    private ArrayList<String> items;

    private ArrayList<String> listDatos = new ArrayList<>();
    private ListView lv_datosPersonasR;

    ArrayAdapter<String> adapter;
    private TextInputEditText textInput;

    Button btnAgregar;
    Button btnLimpiar;

    int band = 1;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_referencia);

        lv_datosPersonasR = findViewById(R.id.lv_datosPersonasR);
        textInput = findViewById(R.id.textInput);

        spinnerRef = findViewById(R.id.spinnerRef);
        spinnerRef.setSelection(0);
        mAdapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
        spinnerRef.setPrompt("Seleccionar");
        spinnerRef.setAdapter(mAdapterSpinner);
        spinnerRef.setSelection(mAdapterSpinner.getCount());
        spinnerRef.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                try {
                    referencia = spinnerRef
                            .getItemAtPosition(position).toString();
                    cargarDatos();
                } catch (Exception e) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO Auto-generated method stub

            }

        });

        mDatabase = FirebaseDatabase.getInstance().getReference();

        listarDatos();

        btnAgregar = findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, String> objetoMap = new HashMap<>();
                objetoMap.put("descripcion", textInput.getText().toString());

                if (id != null){
                    mDatabase.child("Referencias").child(referencia).child(id).setValue(objetoMap);
                }else{
                    mDatabase.child("Referencias").child(referencia).push().setValue(objetoMap);
                }

                Toast.makeText(RegistrarReferenciaActivity.this, "Proceso Realizado con Éxito!!", Toast.LENGTH_SHORT).show();
                cargarDatos();
                textInput.setText("");
                id = null;
                btnAgregar.setText("Agregar");
            }
        });

        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInput.setText("");
            }
        });

        lv_datosPersonasR.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textInput.setText(listDatos.get(position));
                listarDatoSelect();
                btnAgregar.setText("Actualizar");
            }
        });

    }

    private void listarDatos() {
        mDatabase.child("Referencias").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                items = new ArrayList<>();
                items.add(0, "Seleccionar......");

                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    items.add(objSnaptshot.getKey());
                }

                mAdapterSpinner.addAll(items);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void cargarDatos(){
        listDatos.clear();
        band = 0;
        mDatabase.child("Referencias").child(referencia).orderByChild("descripcion").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    try {
                        JSONObject jsonObject = new JSONObject(objSnaptshot.getValue().toString());
                        listDatos.add(jsonObject.optString("descripcion"));
                        band++;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                adapter = new ArrayAdapter<String>(RegistrarReferenciaActivity.this, android.R.layout.simple_list_item_1, listDatos);

                lv_datosPersonasR.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void listarDatoSelect() {
        mDatabase.child("Referencias").child(referencia).orderByChild("descripcion").equalTo((textInput.getText().toString())).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                   id = objSnaptshot.getKey();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}