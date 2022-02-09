package com.educapyoficial.educapy.Galeria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.educapyoficial.educapy.R;
import com.educapyoficial.educapy.administradorGaleria;
import com.educapyoficial.educapy.altabajaUsuarios;
import com.educapyoficial.educapy.includes.MyToolbar;
import com.educapyoficial.educapy.menuadministrador;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;
import java.util.ArrayList;

public class primerActivity extends AppCompatActivity {

    RecyclerView mrecyclerView;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference mref;
    private ArrayList<Messages> messagesList;

    private RecyclerAdapter recyclerAdapter;

    private Context mcontext;

    String obtengogkeR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer);

        MyToolbar.show(this, "Galeria Educapy", false);

        mrecyclerView = findViewById(R.id.recyclerview1);

        Intent i = getIntent();
        obtengogkeR = i.getStringExtra("mandogkeR");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(layoutManager);
        mrecyclerView.setHasFixedSize(true);

        firebaseDatabase = FirebaseDatabase.getInstance();
        mref = firebaseDatabase.getInstance().getReference();

        messagesList = new ArrayList<>();

        ClearAll();
        GetDataFromFirebase();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent intent = new Intent(primerActivity.this, administradorGaleria.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
//        startActivity(intent);
    }


    private void GetDataFromFirebase() {

        Query query = mref.child("Users").child("Clients").child(obtengogkeR).child("fotos_subidas");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for (DataSnapshot snapshot1 : dataSnapshot.getChildren())
                {
                    Messages messages = new Messages();

//                    messages.setTitulo(snapshot1.child("titulo").getValue().toString());
                    messages.setUrlfoto(snapshot1.child("urlfoto").getValue().toString());

                    messagesList.add(messages);
                }

                recyclerAdapter = new RecyclerAdapter(getApplicationContext(),messagesList);
                //   recyclerAdapter = new RecyclerAdapter(mcontext,messagesList);
                mrecyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    private void ClearAll(){
        if(messagesList != null)
        {
            messagesList.clear();

            if(recyclerAdapter != null)
            {
                recyclerAdapter.notifyDataSetChanged();
            }
        }
        messagesList = new ArrayList<>();
    }


}