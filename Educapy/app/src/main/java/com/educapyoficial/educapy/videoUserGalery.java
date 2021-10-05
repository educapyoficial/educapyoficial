package com.educapyoficial.educapy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.educapyoficial.educapy.models.modelVideo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class videoUserGalery extends AppCompatActivity {

    FloatingActionButton addVideoBtnR;

    private ArrayList<modelVideo> videoArrayList;

    private AdapterVideo adapterVideo;
    private RecyclerView videosRvT;

    String jalogkeR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_user_galery);

        Intent i = getIntent();
        jalogkeR = i.getStringExtra("obtengogkeR");

        Toast.makeText(this, "Si no aparece nada el profesor no ha subido contenido", Toast.LENGTH_SHORT).show();

        Log.d("jale","entreaquivideovisor");
        Log.d("kimbo13",jalogkeR);

        setTitle("Videos");

        addVideoBtnR = findViewById(R.id.addVideosBtn2);
        videosRvT = findViewById(R.id.videosRv2);

        //esta funcion llama a cargar los videos
        loadVideosFromFirebase();

        addVideoBtnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(videoUserGalery.this, addVideoActivity.class));
            }
        });
    }

    private void loadVideosFromFirebase() {
        videoArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients").child(jalogkeR).child("Videos");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                videoArrayList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    //limpiar el video cargado
                    //    videoArrayList.clear(); //borradodescarga
                    //carga los datos
                    modelVideo modelVideo = ds.getValue(com.educapyoficial.educapy.models.modelVideo.class);
                    //agrega modelo y datos en la lista
                    videoArrayList.add(modelVideo);
                }
                //setup adaptador
                adapterVideo = new AdapterVideo(videoUserGalery.this, videoArrayList);
                //este metodo carga al recyclew view
                videosRvT.setAdapter(adapterVideo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(videoUserGalery.this, principal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
        startActivity(intent);
    }

}