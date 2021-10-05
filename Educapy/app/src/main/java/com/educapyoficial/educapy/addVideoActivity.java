package com.educapyoficial.educapy;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.educapyoficial.educapy.models.EducapyModelUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class addVideoActivity extends AppCompatActivity {

    private List<EducapyModelUser> listcolaboradoresR = new ArrayList<EducapyModelUser>();
    ArrayAdapter<EducapyModelUser> arrayAdapterColaboradoresR;
    ListView listV_personasRR; //insertar datos
    EducapyModelUser getFocusSelectedR;

    private ActionBar actionBar;
    private EditText titleEt;
    private VideoView videoView;
    private Button uploadbtn;
    private FloatingActionButton pickVideofab;

    private static final int VIDEO_PICK_GALLERY_CODE = 100;
    private static final int VIDEO_PICK_CAMERA_CODE = 101;
    private static final int CAMERA_REQUEST_CODE = 102;

    private String[] cameraPermissions;

    private Uri videoUri = null;

    private ProgressDialog progressDialog;

    long maxid = 0;

    private String title;
    DatabaseReference databaseReference;
    String grupoasignado, obtienegkeR;
    AuthProvider mauthProvider;

    FirebaseDatabase firebaseDatabase;
    SharedPreferences mPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        mauthProvider = new AuthProvider();
        mPref = getApplicationContext().getSharedPreferences("validadmiRT", MODE_PRIVATE);
        listV_personasRR = findViewById(R.id.lv_datosPersonasRvideo); //insertar datos
        inicializarFirebase(); //insertar datos
        getFocusSelectedR = new EducapyModelUser();
        listarDatos();

        /*
        actionBar = getSupportActionBar();
        actionBar.setTitle("add new Video");

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

         */


        titleEt = findViewById(R.id.titleEtR);
        videoView = findViewById(R.id.videoViewR);
        uploadbtn = findViewById(R.id.buttonRT);
        pickVideofab = findViewById(R.id.pickVideoFab);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Espere un momento");
        progressDialog.setMessage("Subiendo Video");
        progressDialog.setCanceledOnTouchOutside(false);

        listV_personasRR.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getFocusSelectedR = (EducapyModelUser) parent.getItemAtPosition(position);
            //    nomP.setText(getFocusSelectedR.getNombre1R());
                obtienegkeR = getFocusSelectedR.getGkeR();

                Log.d("kimbo3",obtienegkeR);

                /*
                databaseReference.child("Tokens").child(obtienegkeR).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                            obtieneToken = dataSnapshot.child("token").getValue().toString();
                            //     Toast.makeText(admiNotificaciones.this, "" + obtieneToken, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });


                 */
            }
        });


        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title = titleEt.getText().toString().trim();
                if (TextUtils.isEmpty(title)) {
                    Toast.makeText(addVideoActivity.this, "Titulo es Requerido", Toast.LENGTH_SHORT).show();
                } else if (videoUri == null) {
                    Toast.makeText(addVideoActivity.this, "Graba o elige un video antes de Subirlo", Toast.LENGTH_SHORT).show();
                } else {
                    uploadVideoFirebase();
                }

            }
        });


        pickVideofab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videopickDialog();
            }
        });

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    private void listarDatos() {
        databaseReference.child("Users").child("Clients").orderByChild("uidProfesor").equalTo(mPref.getString("uidProfesor", "")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                listcolaboradoresR.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    int count = 0;
                    maxid = (dataSnapshot.getChildrenCount());
                    EducapyModelUser p = objSnaptshot.getValue(EducapyModelUser.class);
                    listcolaboradoresR.add(p);
                    arrayAdapterColaboradoresR = new ArrayAdapter<EducapyModelUser>(addVideoActivity.this, android.R.layout.simple_list_item_1, listcolaboradoresR);
                    listV_personasRR.setAdapter(arrayAdapterColaboradoresR);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void uploadVideoFirebase() {
        progressDialog.show();

        //calcula tiempo de carga
        final String timestamp = "" + System.currentTimeMillis();

        //ruta y nombre en firebase storage
        String filePathAndName = "Videos/" + "video_" + timestamp;

        //referencia de almacenamiento
        StorageReference storageReference = FirebaseStorage.getInstance().getReference(filePathAndName);

        //subir video, se usa este metodo

        storageReference.putFile(videoUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isSuccessful()) ;
                        Uri downloadUri = uriTask.getResult();
                        if (uriTask.isSuccessful()) {
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("id", "" + timestamp);
                            hashMap.put("title", "" + title);
                            hashMap.put("timestamp", "" + timestamp);
                            hashMap.put("videoUrl", "" + downloadUri);

                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients").child(obtienegkeR).child("Videos");

                            reference.child(timestamp)
                                    .setValue(hashMap)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                            progressDialog.dismiss();
                                            Toast.makeText(addVideoActivity.this, "Video Cargado...", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            progressDialog.dismiss();
                                            Toast.makeText(addVideoActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        progressDialog.dismiss();
                        Toast.makeText(addVideoActivity.this, "Fallo al subir el video error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void videopickDialog() {

        String[] options = {"Camera", "Galeria"};

        //dialog

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona ruta del video")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        if (i == 0) {
                            if (!checkCameraPermission()) {
                                requestCameraPermission();
                            } else {
                                videoPickCamera();
                            }
                        } else if (i == 1) {
                            videoPickGallery();
                        }
                    }
                })
                .show();
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE);
    }

    private boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        boolean result2 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;

        return result && result2;
    }

    private void videoPickGallery() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Videos"), VIDEO_PICK_GALLERY_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                if (grantResults.length > 0) {
                    //checa si se concedieron los permisos
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted && storageAccepted) {

                    } else {
                        Toast.makeText(this, "Camara y Almacenamiento necesitan peermisos concedidos", Toast.LENGTH_SHORT).show();
                    }
                }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == VIDEO_PICK_GALLERY_CODE) {
                videoUri = data.getData();
                setVideoToVideoView();
            } else if (requestCode == VIDEO_PICK_CAMERA_CODE) {
                videoUri = data.getData();
                setVideoToVideoView();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setVideoToVideoView() {
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);
        videoView.setVideoURI(videoUri);
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.pause();
            }
        });
    }

    private void videoPickCamera() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, VIDEO_PICK_CAMERA_CODE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}