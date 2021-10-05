package com.educapyoficial.educapy.Calendario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.educapyoficial.educapy.R;
import com.educapyoficial.educapy.models.MyAdapter;
import com.educapyoficial.educapy.models.constructorCalendario;
import com.educapyoficial.educapy.principal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;

import de.hdodenhof.circleimageview.CircleImageView;

public class CalendarUser extends AppCompatActivity {

    private DatabaseReference mDatabaseR, mDatabaseRT;
    DatabaseReference referenceRT, reference2;
    RecyclerView recyclerView, recyclerView2;
    ArrayList<constructorCalendario> listRT, list2R;
    MyAdapter adapter, adapter2;
    //  AuthProvider mauthProvider;
    TextView txtcaja;
    private CircleImageView mCircleImageBack;
    public String lugares = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_user);

        mDatabaseR = FirebaseDatabase.getInstance().getReference();
        mDatabaseRT = FirebaseDatabase.getInstance().getReference();


        recyclerView2 = (RecyclerView) findViewById(R.id.myRecycler2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        //    mauthProvider = new AuthProvider();
        mCircleImageBack = findViewById(R.id.circleImageBackRT);
        mCircleImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(CalendarUser.this, principal.class));
                //  stopService(new Intent(Principal.this, contructorMusica.class)); //detener musica de fondo
                finish();
            }
        });

        reference2 = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("Calendario").child("Eventos");
        Query query2 = reference2.orderByChild("fechaEvento");
        query2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2R = new ArrayList<constructorCalendario>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    YoYo.with(Techniques.ZoomIn)
                            .duration(4000)
                            .repeat(0)
                            .playOn(recyclerView2);
                    constructorCalendario p = dataSnapshot1.getValue(constructorCalendario.class);
                    list2R.add(p);
                    // txtcaja.setText(String.valueOf(incrementa));
                }
                adapter2 = new MyAdapter(CalendarUser.this, list2R);
              //  Collections.reverse(list2R);
                recyclerView2.setAdapter(adapter2);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CalendarUser.this, "Opsss.... Fallo la Base", Toast.LENGTH_SHORT).show();
            }
        });


        //   query.addListenerForSingleValueEvent();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Intent intent = new Intent(CalendarUser.this, principal.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
        //startActivity(intent);
    }

}