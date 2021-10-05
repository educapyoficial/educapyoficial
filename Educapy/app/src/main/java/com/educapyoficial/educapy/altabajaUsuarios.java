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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.educapyoficial.educapy.adapters.ListaUsuariosAdapter;
import com.educapyoficial.educapy.includes.MyToolbar;
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

public class altabajaUsuarios extends AppCompatActivity {

    private ArrayList<EducapyModelUser> listUsuarios = new ArrayList<>();
    ArrayAdapter<EducapyModelUser> arrayAdapterEspecialidad;
    Button btnsinImage, btnseleccionar;
    DatabaseReference imgref;
    StorageReference storageReference;
    ProgressDialog cargando;
    public Bitmap thumb_bitmap = null;
    EditText nomP, cajaCorreo, imagenP, cajaNombre, cajagrupo, profilePpick, cajakey, cajapais; //insertar datos
    FirebaseDatabase firebaseDatabase;
    ListView listV_personas; //insertar datos
    DatabaseReference databaseReference, mdatabaseO;
    DatabaseReference reff;
    AlertDialog mDialog;
    AuthProvider mauthProvider;
    EducapyModelUser getFocusSelecteduser;
    //  String obtienekey;
    long maxid = 0;
    ArrayAdapter<String> mAdapter;
    Spinner spinnerCurso;
    ImageView imagen;
    String grupoasignado;
    int almacenapuntos;
    String obtieneuid;
    private CircleImageView mCircleImageBackRR;
    ListaUsuariosAdapter usuariosAdapter;

    Button btnLimpiar;
    Button btnAgregar;

    boolean bandEdit = false;
    EducapyModelUser educapyModelUser;

    TextView textSpinnerCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altabaja_usuarios);

        MyToolbar.show(this, "alta y baja", false);
        mauthProvider = new AuthProvider();
        spinnerCurso = (Spinner) findViewById(R.id.spinnerCurso);
        textSpinnerCurso = findViewById(R.id.textSpinnerCurso);
        imagen = findViewById(R.id.imagen);

        spinnerCurso.setVisibility(View.GONE);
        textSpinnerCurso.setVisibility(View.GONE);
        imagen.setVisibility(View.GONE);

        cargando = new ProgressDialog(this);
        cajaNombre = findViewById(R.id.solucionLinkR);
        cajaCorreo = findViewById(R.id.textInputCorreoR);
        cajakey = findViewById(R.id.solucionkeyR); //CREO SE PUEDE QUITAR
        mdatabaseO = FirebaseDatabase.getInstance().getReference();


        reff = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients");
        mDialog = new SpotsDialog.Builder().setContext(altabajaUsuarios.this).setMessage("Espere Un Momento").build();
        nomP = findViewById(R.id.textInputNameR); //insertar datos
        imagenP = findViewById(R.id.solucionLinkR);
        getFocusSelecteduser = new EducapyModelUser();
        listV_personas = findViewById(R.id.lv_datosPersonasR); //insertar datos
        inicializarFirebase(); //insertar datos
        listarDatos();

        mAdapter = new ArrayAdapter<String>(altabajaUsuarios.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.altabajaQ));


        listV_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getFocusSelecteduser = (EducapyModelUser) parent.getItemAtPosition(position);
                educapyModelUser = (EducapyModelUser) parent.getItemAtPosition(position);
                nomP.setText(getFocusSelecteduser.getNombre());
                cajaCorreo.setText(getFocusSelecteduser.getEmailR());
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
                educapyModelUser = (EducapyModelUser) parent.getItemAtPosition(position);
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
                    if (bandEdit){
                        if (educapyModelUser != null && educapyModelUser.getUid() != null){
                            educapyModelUser.setEmailR(cajaCorreo.getText().toString());
                            educapyModelUser.setNombre(nomP.getText().toString().toUpperCase());
                            mdatabaseO.child("Users").child("Clients").child(educapyModelUser.getUid()).setValue(educapyModelUser);
                            Toast.makeText(altabajaUsuarios.this, "Usuario Actualizado Con Éxito", Toast.LENGTH_SHORT).show();
                            limpiar();
                            listarDatos();
                        }
                    }else{
                        educapyModelUser = new EducapyModelUser();
                        educapyModelUser.setEmailR(cajaCorreo.getText().toString());
                        educapyModelUser.setNombre(nomP.getText().toString().toUpperCase());
                        DatabaseReference usersRef = databaseReference.child("Users").child("Clients");
                        usersRef.push().setValue(educapyModelUser);
                        //usersRef.setValue(educapyModelUserProfesor);
                        Toast.makeText(altabajaUsuarios.this, "Usuario Registrado Con Éxito", Toast.LENGTH_SHORT).show();
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
                altabajaUsuarios.this);


        if (educapyModelUser.getEstado() == null || educapyModelUser.getEstado().equals("I")){
            alert.setTitle("Activar");
            alert.setMessage("Desea activar lo seleccionado de la Lista?");
        }else{
            alert.setTitle("Inactivar");
            alert.setMessage("Desea inactivar lo seleccionado de la Lista?");
        }

        alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (educapyModelUser.getEstado() == null || educapyModelUser.getEstado().equals("I")){
                    educapyModelUser.setEstado("A");
                }else{
                    educapyModelUser.setEstado("I");
                }
                mdatabaseO.child("Users").child("Clients").child(educapyModelUser.getUid()).setValue(educapyModelUser);
                Toast.makeText(altabajaUsuarios.this, "Usuario Actualizado Con Éxito", Toast.LENGTH_SHORT).show();
                limpiar();
                listarDatos();

                //listUsuarios.remove(deletePosition);
                //usuariosAdapter.notifyDataSetChanged();
                //usuariosAdapter.notifyDataSetInvalidated();

            }
        });
        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });

        alert.show();

    }

    public void eliminar(){

    }

    public void limpiar(){
        nomP.setText("");
        cajaCorreo.setText("");
        educapyModelUser= new EducapyModelUser();
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
        databaseReference.child("Users").child("Clients").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listUsuarios.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    maxid = (dataSnapshot.getChildrenCount());
                    EducapyModelUser p = objSnaptshot.getValue(EducapyModelUser.class);
                    p.setUid(objSnaptshot.getKey());
                    listUsuarios.add(p);
                }
                usuariosAdapter = new ListaUsuariosAdapter(altabajaUsuarios.this, listUsuarios);
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
        Intent intent = new Intent(altabajaUsuarios.this, MainActivity.class);
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

