package com.educapyoficial.educapy;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.educapyoficial.educapy.adapters.ListaCursosAdapter;
import com.educapyoficial.educapy.adapters.ListaUsuariosProfAdapter;
import com.educapyoficial.educapy.adapters.SpinnerAdapter;
import com.educapyoficial.educapy.includes.MyToolbar;
import com.educapyoficial.educapy.models.CursosModel;
import com.educapyoficial.educapy.models.EducapyModelUserProfesor;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;

public class altabajaUsuariosProfesor extends AppCompatActivity {

    private ArrayList<EducapyModelUserProfesor> listUsuarios = new ArrayList<EducapyModelUserProfesor>();
    ArrayAdapter<EducapyModelUserProfesor> arrayAdapterEspecialidad;
    Button btnsinImage, btnseleccionar;
    DatabaseReference imgref;
    StorageReference storageReference;
    ProgressDialog cargando;
    public Bitmap thumb_bitmap = null;
    EditText imagenP, cajaNombre, cajagrupo, profilePpick, cajakey, cajapais; //insertar datos
    TextInputEditText cajaCorreo, nomP;
    FirebaseDatabase firebaseDatabase;
    ListView listV_personas; //insertar datos
    DatabaseReference databaseReference, mdatabaseO;
    DatabaseReference reff;
    AlertDialog mDialog;
    AuthProvider mauthProvider;
    EducapyModelUserProfesor getFocusSelecteduser;
    //  String obtienekey;
    long maxid = 0;
    ArrayAdapter<String> mAdapter;
    Spinner spinnerCurso;
    String grupoasignado;
    int almacenapuntos;
    String obtieneuid;
    private CircleImageView mCircleImageBackRR;

    Button btnLimpiar;
    Button btnAgregar;

    ListaUsuariosProfAdapter usuariosAdapter;

    boolean bandEdit = false;
    EducapyModelUserProfesor educapyModelUserProfesor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altabaja_usuarios);

        MyToolbar.show(this, "Alta y Baja Profesores", false);
        mauthProvider = new AuthProvider();
        spinnerCurso = (Spinner) findViewById(R.id.spinnerCurso);
        cargando = new ProgressDialog(this);
        cajaNombre = findViewById(R.id.solucionLinkR);
        cajaCorreo = (TextInputEditText) findViewById(R.id.textInputCorreoR);
        cajakey = findViewById(R.id.solucionkeyR); //CREO SE PUEDE QUITAR
        mdatabaseO = FirebaseDatabase.getInstance().getReference();

        reff = FirebaseDatabase.getInstance().getReference().child("Profesores").child("id");
        mDialog = new SpotsDialog.Builder().setContext(altabajaUsuariosProfesor.this).setMessage("Espere Un Momento").build();
        nomP = (TextInputEditText) findViewById(R.id.textInputNameR); //insertar datos
        imagenP = findViewById(R.id.solucionLinkR);
        getFocusSelecteduser = new EducapyModelUserProfesor();
        listV_personas = findViewById(R.id.lv_datosPersonasR); //insertar datos
        inicializarFirebase(); //insertar datos
        listarDatos();

        //mAdapter = new ArrayAdapter<String>(altabajaUsuariosProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.altabajaQ));
        //spinnerCurso.setAdapter(mAdapter);


     listarDatosCurso();




        listV_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getFocusSelecteduser = (EducapyModelUserProfesor) parent.getItemAtPosition(position);
                educapyModelUserProfesor = (EducapyModelUserProfesor) parent.getItemAtPosition(position);
                nomP.setText(getFocusSelecteduser.getNombre());
                cajaCorreo.setText(getFocusSelecteduser.getCorreo());
                //   cajakey.setText(getFocusSelecteduser.getGkeR());
                obtieneuid = getFocusSelecteduser.getUid();
                //   Log.d("nodo", obtieneuid.toString());
                bandEdit = true;
                btnAgregar.setText("Actualizar");
            }
        });

        listV_personas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                educapyModelUserProfesor = (EducapyModelUserProfesor) parent.getItemAtPosition(position);
                removeItemFromList(position);
                return false;
            }
        });


        reff.addValueEventListener(new ValueEventListener() {
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
                    if (bandEdit){
                        if (educapyModelUserProfesor != null && educapyModelUserProfesor.getUid() != null){
                            educapyModelUserProfesor.setCorreo(cajaCorreo.getText().toString());
                            educapyModelUserProfesor.setNombre(nomP.getText().toString().toUpperCase());
                            mdatabaseO.child("Profesores").child("id").child(educapyModelUserProfesor.getUid()).setValue(educapyModelUserProfesor);
                            Toast.makeText(altabajaUsuariosProfesor.this, "Usuario Actualizado Con Éxito", Toast.LENGTH_SHORT).show();
                            limpiar();
                            listarDatos();
                        }
                    }else{
                        educapyModelUserProfesor = new EducapyModelUserProfesor();
                        educapyModelUserProfesor.setEs_profesor("Si");
                        educapyModelUserProfesor.setCorreo(cajaCorreo.getText().toString());
                        educapyModelUserProfesor.setNombre(nomP.getText().toString().toUpperCase());
                        DatabaseReference usersRef = databaseReference.child("Profesores").child("id");
                        usersRef.push().setValue(educapyModelUserProfesor);
                        //usersRef.setValue(educapyModelUserProfesor);
                        Toast.makeText(altabajaUsuariosProfesor.this, "Usuario Registrado Con Éxito", Toast.LENGTH_SHORT).show();
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
                altabajaUsuariosProfesor.this);

        if (educapyModelUserProfesor.getEstado() == null || educapyModelUserProfesor.getEstado().equals("I")){
            alert.setTitle("Activar o Asignar Alumnos");
            alert.setMessage("Desea activar o Asignar Alumnos lo seleccionado de la Lista?");
        }else{
            alert.setTitle("Inactivar  o Asignar Alumnos");
            alert.setMessage("Desea inactivar o Asignar Alumnos lo seleccionado de la Lista?");
        }
        alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (educapyModelUserProfesor.getEstado() == null || educapyModelUserProfesor.getEstado().equals("I")){
                    educapyModelUserProfesor.setEstado("A");
                }else{
                    educapyModelUserProfesor.setEstado("I");
                }
                mdatabaseO.child("Profesores").child("id").child(educapyModelUserProfesor.getUid()).setValue(educapyModelUserProfesor.getEstado());
                Toast.makeText(altabajaUsuariosProfesor.this, "Usuario Borrado Con Éxito", Toast.LENGTH_SHORT).show();
                limpiar();
                listarDatos();

                //listUsuarios.remove(deletePosition);
                //usuariosAdapter.notifyDataSetChanged();
                //usuariosAdapter.notifyDataSetInvalidated();

            }
        });
        alert.setNegativeButton("Asignar Alumnos", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                Intent intent = new Intent(altabajaUsuariosProfesor.this, VincularProfeAlumnoActivity.class);
                intent.putExtra("uidprofesor", educapyModelUserProfesor.getUid());
                startActivity(intent);

            }
        });

        alert.show();

    }

    public void limpiar(){
        nomP.setText("");
        cajaCorreo.setText("");
        educapyModelUserProfesor = new EducapyModelUserProfesor();
        btnAgregar.setText("Agregar");
        bandEdit = false;
    }

    private boolean validar() {
        if (nomP.getText().toString().equals("")) {
            nomP.setError("Requerido");
            Toast.makeText(this, "Debe Ingresar el Nombre del Usuario", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (cajaCorreo.getText().toString().equals("")) {
            cajaCorreo.setError("Requerido");
            Toast.makeText(this, "Debe Ingresar el correo electronico", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!validarEmail(cajaCorreo.getText().toString())) {
            Toast.makeText(this, "Debe un correo electronico valido", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void listarDatos() {
        databaseReference.child("Profesores").child("id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listUsuarios.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    maxid = (dataSnapshot.getChildrenCount());
                    EducapyModelUserProfesor p = objSnaptshot.getValue(EducapyModelUserProfesor.class);
                    p.setUid(objSnaptshot.getKey());
                    listUsuarios.add(p);
                    //arrayAdapterEspecialidad = new ArrayAdapter<EducapyModelUserProfesor>(altabajaUsuariosProfesor.this, android.R.layout.simple_list_item_1, listUsuarios);
                    //listV_personas.setAdapter(arrayAdapterEspecialidad);
                }

                usuariosAdapter = new ListaUsuariosProfAdapter(altabajaUsuariosProfesor.this, listUsuarios);
                listV_personas.setAdapter(usuariosAdapter);
//                usuariosAdapter.setUsuariosList(listUsuarios);
                usuariosAdapter.notifyDataSetChanged();
                usuariosAdapter.notifyDataSetInvalidated();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void listarDatosCurso() {
        databaseReference.child("Cursos").child("id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<CursosModel> items = new ArrayList<>();
                CursosModel p = new CursosModel();
                p.setUid("0");
                p.setCursos("Seleccionar......");
                items.add(p);
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    maxid = (dataSnapshot.getChildrenCount());
                    p = objSnaptshot.getValue(CursosModel.class);
                    p.setUid(objSnaptshot.getKey());
                    items.add(p);
                }
                SpinnerAdapter<CursosModel> mAdapter = new SpinnerAdapter<CursosModel>(
                        altabajaUsuariosProfesor.this, android.R.layout.simple_spinner_item, items);
                spinnerCurso.setPrompt("Seleccionar Curso");
                // mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCurso.setAdapter(mAdapter);
                spinnerCurso.setSelection(mAdapter.getCount());

                spinnerCurso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int position, long id) {


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // TODO Auto-generated method stub

                    }

                });

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
        String correoT = cajaCorreo.getText().toString();
/*
        switch (item.getItemId()) {
            case R.id.icon_update: {

                if (!nomP.getText().equals("")) {

                    if (!grupoasignado.equals("NA")) {
                        try {
                            Map<String, Object> personMap = new HashMap<>();
                            personMap.put("es_profesor", grupoasignado.toString());
                            mdatabaseO.child("Profesores").child("id").child(obtieneuid).updateChildren(personMap);
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
        Intent intent = new Intent(altabajaUsuariosProfesor.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //insertar datos
    private void limpiarCajas() {
        nomP.setText("");
        cajagrupo.setText("");
        cajaCorreo.setText("");
    }

    /*3*/ //insertar datos
    private void validacion() {
        /*1*/
        String nombret = nomP.getText().toString();  //utilizo esta forma para obtener el valor de los campos y validar los campos vacios  atravez de la clase validacion
        /*2*/
        String correoT = cajaCorreo.getText().toString();
        /*3*/
        /*4*/

        if (nombret.equals("")) {
            nomP.setError("Required");
        } else if (correoT.equals("")) {
            cajaCorreo.setError("Required");
        }
    }

    /*3*/ //insertar datos
}

