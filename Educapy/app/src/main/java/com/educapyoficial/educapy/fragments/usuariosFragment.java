package com.educapyoficial.educapy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.educapyoficial.educapy.adapters.AdaptersUsuarios;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.educapyoficial.educapy.R;
import com.educapyoficial.educapy.pojos.Users;

import java.util.ArrayList;

public class usuariosFragment extends Fragment {



    public usuariosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String uidCurso = this.getArguments().getString("uidCurso");

        // Inflate the layout for this fragment

        final ProgressBar progressBar;

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //referenciando a este objeto tengo accesos atodos los parametros de google de l cuenta

        View view = inflater.inflate(R.layout.fragment_usuarios, container, false);

        TextView tv_user = view.findViewById(R.id.tv_user);
        ImageView img_user = view.findViewById(R.id.img_user);
        progressBar = view.findViewById(R.id.progressbar);

        assert user != null;
        tv_user.setText(user.getDisplayName());
        Glide.with(this).load(user.getPhotoUrl()).into(img_user);

        final RecyclerView rv;
        final ArrayList<Users>usersArrayList;
        final AdaptersUsuarios adapter;
        LinearLayoutManager mLayoutManager;

        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        rv =  view.findViewById(R.id.rv);
        rv.setLayoutManager(mLayoutManager);

        usersArrayList = new ArrayList<>();
        adapter = new AdaptersUsuarios(usersArrayList,getContext());
        rv.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("UsersChat").orderByChild("uidCurso").equalTo(uidCurso).getRef();
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {
                    rv.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    usersArrayList.removeAll(usersArrayList);
                    for(DataSnapshot snapshot : dataSnapshot.getChildren())
                    {
                        Users users = snapshot.getValue(Users.class);
                        if (users.getUidCurso() != null && users.getUidCurso().equals(uidCurso)){
                            usersArrayList.add(users);
                        }

                    }
                    adapter.notifyDataSetChanged();
                }else
                {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "No Existen Usuarios", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}