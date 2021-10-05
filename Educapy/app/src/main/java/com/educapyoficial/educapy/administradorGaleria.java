package com.educapyoficial.educapy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

import com.educapyoficial.educapy.Galeria.primerActivity;
import com.educapyoficial.educapy.includes.MyToolbar;
import com.educapyoficial.educapy.models.EducapyModelUser;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dmax.dialog.SpotsDialog;
import id.zelory.compressor.Compressor;

public class administradorGaleria extends AppCompatActivity {

    private List<EducapyModelUser> listEspecialidad = new ArrayList<EducapyModelUser>();
    ArrayAdapter<EducapyModelUser> arrayAdapterEspecialidad;

    Button btngoGaleria, btnseleccionar;
    ImageView foto;
    DatabaseReference imgref;
    StorageReference storageReference;
    ProgressDialog cargando;
    public Bitmap thumb_bitmap = null;

    EditText nomP, preguntaP1, preguntaP2, preguntaP3, preguntaP4, imagenP, justificacionP, cajaObtiene, profilePpick; //insertar datos
    TextView cajagkeR;
    FirebaseDatabase firebaseDatabase;
    ListView listV_personas; //insertar datos
    DatabaseReference databaseReference;
    DatabaseReference reff;
    AlertDialog mDialog;
    AuthProvider mauthProvider;

    EducapyModelUser MedicaSelected;

    long maxid = 0;

    Spinner spinnerPais;
    String obtienespiner;
    ArrayAdapter<String> mAdapter;
    int cuentapregunta = 0;

    String seleccionUsuario ="";

    EditText tituimagen;

    private String serializalo;
    String guardaNombre;

    SharedPreferences mPref;

  //  String referencia ="Lljx10kmbIYDnXXGZe96ZapjfV52";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador_galeria);

        MyToolbar.show(this, "Admin Galeria", false);
        mauthProvider = new AuthProvider();

        mPref = getApplicationContext().getSharedPreferences("validadmiRT", MODE_PRIVATE);

        foto = findViewById(R.id.img_foto);
        btnseleccionar = findViewById(R.id.btdSeleccionar);
        storageReference = FirebaseStorage.getInstance().getReference().child("img_comprimidas");
        cargando = new ProgressDialog(this);
        cajaObtiene = findViewById(R.id.solucionLinkgaleria);
        btngoGaleria = findViewById(R.id.btnirGaleria);
        profilePpick = findViewById(R.id.solucionLinkgaleria);
        tituimagen = findViewById(R.id.titulodelaimagen);
        cajagkeR = findViewById(R.id.solucionLinkgaleria);


        btnseleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tituimagen.getText().toString().equals(""))
                {

                    Toast.makeText(administradorGaleria.this, "Escribe un titulo", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(seleccionUsuario.isEmpty())
                    {
                        Toast.makeText(administradorGaleria.this, "Seleccione usuario para cargar imagen", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        CropImage.startPickImageActivity(administradorGaleria.this);
                        //   Toast.makeText(administradorGaleria.this, "Gracias por seleccionar", Toast.LENGTH_SHORT).show();
                    }

                }



            }
        });

        btngoGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(seleccionUsuario.isEmpty())
                {
                    Toast.makeText(administradorGaleria.this, "Seleccione usuario para cargar galeria", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(administradorGaleria.this, primerActivity.class);
                    intent.putExtra("mandogkeR", cajaObtiene.getText().toString());
                    //    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }


            }
        });


        reff = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients");
        mDialog = new SpotsDialog.Builder().setContext(administradorGaleria.this).setMessage("Espere Un Momento").build();
        MedicaSelected = new EducapyModelUser();
        listV_personas = findViewById(R.id.lv_datosPersonas); //insertar datos
        inicializarFirebase(); //insertar datos
        listarDatos();

        listV_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MedicaSelected = (EducapyModelUser) parent.getItemAtPosition(position);
                seleccionUsuario = MedicaSelected.getGkeR();

                Log.d("angelasorotaki", seleccionUsuario);

                cajaObtiene.setText(MedicaSelected.getGkeR());

               // Toast.makeText(administradorGaleria.this, ""+cajagkeR.getText().toString(), Toast.LENGTH_SHORT).show();

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

        serializalo = cajaObtiene.getText().toString();

        Log.d("pico",serializalo);

        Log.d("fusion",cajaObtiene.getText().toString());


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Intent intent = new Intent(administradorGaleria.this, menuadministrador.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
        //startActivity(intent);
    }

    public void actualizatitulo() {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference scoreRef = rootRef.child("Users").child("Clients").child(cajaObtiene.getText().toString()).child("Fotos_subidas").child(seleccionUsuario).child("titulo");

        scoreRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Integer score = mutableData.getValue(Integer.class);
                if (score == null) {
                    return Transaction.success(mutableData);
                }
                mutableData.setValue(tituimagen);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
            }
        });

        //  mDatabase.child("Users").child("Clients").child(id).child("puntosTotales").setValue(+5);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        imgref = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients").child(cajaObtiene.getText().toString()).child("Fotos_subidas");


        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri imageuri = CropImage.getPickImageResultUri(this, data);

            //recortar imagen
            CropImage.activity(imageuri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setRequestedSize(640, 480)
                    .setAspectRatio(2, 1).start(administradorGaleria.this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                File url = new File(resultUri.getPath());
                Picasso.with(this).load(url).into(foto);
                //comprimiendo imagen

                try {

                    thumb_bitmap = new Compressor(this)
                            .setMaxWidth(640)
                            .setMaxHeight(480)
                            .setQuality(90)
                            .compressToBitmap(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                thumb_bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
                final byte[] thumb_byte = byteArrayOutputStream.toByteArray();

                //fin del compresor

                int p = (int) (Math.random() * 25 + 1);
                int s = (int) (Math.random() * 25 + 1);
                int t = (int) (Math.random() * 25 + 1);
                int c = (int) (Math.random() * 25 + 1);
                int numero1 = (int) (Math.random() * 1012 + 2111);
                int numero2 = (int) (Math.random() * 1012 + 2111);
                String[] elementos = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "k", "K", "L", "M", "N", "O",
                        "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

                final String aleatoriO = elementos[p] + elementos[s] + numero1 + elementos[t] + elementos[c] + numero2 + "comprimido.jpg";

                cargando.setTitle("Procesando Imagen...");
                cargando.setMessage("Espere porfavor.....");
                cargando.show();



                final StorageReference ref = storageReference.child(aleatoriO); //
                //final StorageReference ref = storageReference.child("nombredeimagen.jpg");

                UploadTask uploadTask = ref.putBytes(thumb_byte);

                //subir imagen en firebase

                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                        if (!task.isSuccessful()) {
                            throw Objects.requireNonNull(task.getException());
                        }
                        return ref.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        Uri downloaduri = task.getResult();

                        imgref.push().child("urlfoto").setValue(downloaduri.toString()); //nodo hijo de hijo
                     //   imgref.push().child("titulo").setValue(tituimagen.toString());
                        foto.setVisibility(View.VISIBLE);
                        cargando.dismiss();
                        Toast.makeText(administradorGaleria.this, "Imagen Procesada con Exito", Toast.LENGTH_SHORT).show();

                        cajaObtiene.setText(downloaduri.toString());
                    }
                });

            }
        }

        actualizatitulo();
        seleccionUsuario = "";
        tituimagen.setText("");
    }


    private void listarDatos() {
        databaseReference.child("Users").child("Clients").orderByChild("uidProfesor").equalTo(mPref.getString("uidProfesor", "")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listEspecialidad.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    maxid = (dataSnapshot.getChildrenCount());
                    EducapyModelUser p = objSnaptshot.getValue(EducapyModelUser.class);
                    listEspecialidad.add(p);
                    arrayAdapterEspecialidad = new ArrayAdapter<EducapyModelUser>(administradorGaleria.this, android.R.layout.simple_list_item_1, listEspecialidad);
                    listV_personas.setAdapter(arrayAdapterEspecialidad);
                   // String guardado = objSnaptshot.child("gkeR").getValue(String.class);

                   // Log.d("cataliza",String.valueOf(guardado));
                }
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

    void logout() {
        mauthProvider.logout();
        Intent intent = new Intent(administradorGaleria.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //insertar datos
    private void limpiarCajas() {
        nomP.setText("");
        preguntaP1.setText("");
        preguntaP2.setText("");
        preguntaP3.setText("");
        preguntaP4.setText("");
        obtienespiner = "";
        justificacionP.setText("");
        imagenP.setText("");
        foto.setVisibility(View.GONE);
    }

}