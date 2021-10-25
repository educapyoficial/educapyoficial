package com.educapyoficial.educapy;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.educapyoficial.educapy.adapters.ListaCursosAdapter;
import com.educapyoficial.educapy.includes.MyToolbar;
import com.educapyoficial.educapy.models.CursosModel;
import com.educapyoficial.educapy.models.EducapyModelUser;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;

public class AltabajaCursos extends AppCompatActivity {

    private ArrayList<CursosModel> list = new ArrayList<>();
    Button btnsinImage, btnseleccionar;
    DatabaseReference imgref;
    StorageReference storageReference;
    ProgressDialog cargando;
    public Bitmap thumb_bitmap = null;
    EditText nomP, imagenP, cajaNombre, cajagrupo, profilePpick, cajakey, cajapais; //insertar datos
    FirebaseDatabase firebaseDatabase;
    ListView listV_personas; //insertar datos
    DatabaseReference databaseReference, mdatabaseO;
    DatabaseReference reff;
    AlertDialog mDialog;
    AuthProvider mauthProvider;
    //  String obtienekey;
    long maxid = 0;

    String grupoasignado;
    int almacenapuntos;
    String obtieneuid;
    private CircleImageView mCircleImageBackRR;
    ListaCursosAdapter mAdapter;

    Button btnLimpiar;
    Button btnAgregar;

    boolean bandEdit = false;
    CursosModel cursosModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altabaja_curso);

        MyToolbar.show(this, "alta y baja", false);
        mauthProvider = new AuthProvider();
        cargando = new ProgressDialog(this);
        mdatabaseO = FirebaseDatabase.getInstance().getReference();

        reff = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients");
        mDialog = new SpotsDialog.Builder().setContext(AltabajaCursos.this).setMessage("Espere Un Momento").build();
        nomP = findViewById(R.id.textInputNameR); //insertar datos
        imagenP = findViewById(R.id.solucionLinkR);
        listV_personas = findViewById(R.id.lv_datosPersonasR); //insertar datos
        inicializarFirebase(); //insertar datos
        listarDatos();

        listV_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursosModel = (CursosModel) parent.getItemAtPosition(position);
                nomP.setText(cursosModel.getCursos());
                obtieneuid = cursosModel.getUid();
                bandEdit = true;
                btnAgregar.setText("Actualizar");
            }
        });


        listV_personas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                cursosModel = (CursosModel) parent.getItemAtPosition(position);
                removeItemFromList(position);
                return false;
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

        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });

        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validar()) {
                    if (bandEdit) {
                        if (cursosModel != null && cursosModel.getUid() != null) {
                            cursosModel.setCursos(nomP.getText().toString().toUpperCase());
                            mdatabaseO.child("Cursos").child("id").child(cursosModel.getUid()).setValue(cursosModel);
                            Toast.makeText(AltabajaCursos.this, "Curso Actualizado Con Éxito", Toast.LENGTH_SHORT).show();
                            limpiar();
                            listarDatos();
                        }
                    } else {
                        cursosModel = new CursosModel();
                        cursosModel.setCursos(nomP.getText().toString().toUpperCase());
                        DatabaseReference usersRef = databaseReference.child("Cursos").child("id");
                        usersRef.push().setValue(cursosModel);
                        Toast.makeText(AltabajaCursos.this, "Curso Registrado Con Éxito", Toast.LENGTH_SHORT).show();
                        limpiar();
                        listarDatos();
                    }


                }
            }
        });
    }

    protected void removeItemFromList(int position) {
        final int deletePosition = position;

        AlertDialog.Builder alert = new AlertDialog.Builder(
                AltabajaCursos.this);


        if (cursosModel.getEstado() == null || cursosModel.getEstado().equals("I")) {
            alert.setTitle("Activar");
            alert.setMessage("Desea activar lo seleccionado de la Lista?");
        } else {
            alert.setTitle("Inactivar");
            alert.setMessage("Desea inactivar lo seleccionado de la Lista?");
        }

        alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (cursosModel.getEstado() == null || cursosModel.getEstado().equals("I")) {
                    cursosModel.setEstado("A");
                } else {
                    cursosModel.setEstado("I");
                }
                mdatabaseO.child("Cursos").child("id").child(cursosModel.getUid()).setValue(cursosModel);
                Toast.makeText(AltabajaCursos.this, "Curso Actualizado Con Éxito", Toast.LENGTH_SHORT).show();
                limpiar();
                listarDatos();

            }
        });

        alert.setNegativeButton("Asignar Alumnos", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                Intent intent = new Intent(AltabajaCursos.this, VincularProfeAlumnoActivity.class);
                intent.putExtra("uidCurso", cursosModel.getUid());
                startActivity(intent);

            }
        });


        alert.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });

        alert.show();

    }

    public void eliminar() {

    }

    public void limpiar() {
        nomP.setText("");
        cursosModel = new CursosModel();
        btnAgregar.setText("Agregar");
        bandEdit = false;
    }

    private boolean validar() {
        if (nomP.getText().toString().equals("")) {
            nomP.setError("Requerido");
            Toast.makeText(this, "Debe Ingresar el Nombre del Curso", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void listarDatos() {
        databaseReference.child("Cursos").child("id").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    maxid = (dataSnapshot.getChildrenCount());
                    CursosModel p = objSnaptshot.getValue(CursosModel.class);
                    p.setUid(objSnaptshot.getKey());
                    list.add(p);
                }
                mAdapter = new ListaCursosAdapter(AltabajaCursos.this, list);
                listV_personas.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                mAdapter.notifyDataSetInvalidated();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //insertar datos
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String nombret = nomP.getText().toString();  //utilizo esta forma para obtener el valor de los campos y validar los campos vacios  atravez de la clase validacion
        String imagenT = imagenP.getText().toString();
/*
        switch (item.getItemId()) {
            case R.id.icon_update: {

                if (!nomP.getText().equals("")) {

                    if (!grupoasignado.equals("NA")) {
                        try {
                            Map<String, Object> personMap = new HashMap<>();
                            personMap.put("idgruR", grupoasignado.toString());
                            mdatabaseO.child("Users").child("Clients").child(obtieneuid).updateChildren(personMap);
                            Toast.makeText(this, "Actualizado", Toast.LENGTH_SHORT).show();
                            break;
                        } catch (Exception e) {
                            Toast.makeText(this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Selecciona un status de usuario", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(this, "Selecciona un campo", Toast.LENGTH_SHORT).show();
                }


            }


        }

 */
        return true;

    }


    void logout() {
        mauthProvider.logout();
//        Intent intent = new Intent(AltabajaCursos.this, MainActivity.class);
//        startActivity(intent);
        finish();
    }



    /*3*/ //insertar datos
}

