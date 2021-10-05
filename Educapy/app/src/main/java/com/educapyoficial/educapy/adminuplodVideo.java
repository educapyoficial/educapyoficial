package com.educapyoficial.educapy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.educapyoficial.educapy.models.modelVideo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class adminuplodVideo extends AppCompatActivity {


    FloatingActionButton addVideoBtnR;

    private ArrayList<modelVideo> videoArrayList;

    private AdapterVideo adapterVideo;
    private RecyclerView videosRvT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminuplod_video);

        Log.d("jale2","entreaqui2");

        setTitle("Videos");

        addVideoBtnR = findViewById(R.id.addVideosBtn);
        videosRvT = findViewById(R.id.videosRv);

        //esta funcion llama a cargar los videos
        loadVideosFromFirebase();

        addVideoBtnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(adminuplodVideo.this, addVideoActivity.class));
            }
        });
    }

    private void loadVideosFromFirebase() {
        videoArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Videos");
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
                adapterVideo = new AdapterVideo(adminuplodVideo.this, videoArrayList);
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
        Intent intent = new Intent(adminuplodVideo.this, selectorvisual.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
        startActivity(intent);
    }

}