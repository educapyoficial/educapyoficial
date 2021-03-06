package com.educapyoficial.educapy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.educapyoficial.educapy.adapters.AdapterChatLista;
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


public class chatsFragment extends Fragment {

    private String uidCurso;
    private String uid;

    public chatsFragment() {
        // Required empty public constructor
    }


    ArrayList<Users> usersArrayList;

    ArrayList<Users> usersArrayListChats;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        if (this.getArguments() != null) {
            uidCurso = this.getArguments().getString("uidCurso");
            uid = this.getArguments().getString("uid");
        }

        final ProgressBar progressBar;

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //referenciando a este objeto tengo accesos atodos los parametros de google de l cuenta

        View view = inflater.inflate(R.layout.fragment_chats, container, false);

        progressBar = view.findViewById(R.id.progressbar);

        assert user != null;

        final RecyclerView rv;
        final AdapterChatLista adapter;
        LinearLayoutManager mLayoutManager;

        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        rv = view.findViewById(R.id.rv);
        rv.setLayoutManager(mLayoutManager);

        usersArrayList = new ArrayList<>();
        usersArrayListChats = new ArrayList<>();
        adapter = new AdapterChatLista(usersArrayListChats, getContext(), uid);
        rv.setAdapter(adapter);

        String uidCurso = this.getArguments().getString("uidCurso");
        String uid = this.getArguments().getString("uid");

        usersArrayList = ((usuariosFragment) getParentFragmentManager().getFragments().get(0)).usersArrayList;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        if (usersArrayList != null) {
            for (Users o : usersArrayList) {
                DatabaseReference myref = database.getReference("Chats").child(uid + o.getUid());
                myref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            rv.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            //usersArrayList.removeAll(usersArrayList);
                        /*for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Users users = snapshot.getValue(Users.class);
                            usersArrayList.add(users);
                        }*/
                            boolean band = false;
                            for (Users u : usersArrayListChats) {
                                if (u.getUid().equals(o.getUid())){
                                    band = true;
                                }

                            }
                            if (!band) {
                                usersArrayListChats.add(o);
                            }

                            adapter.notifyDataSetChanged();
                        } else {
                            progressBar.setVisibility(View.GONE);
                            //Toast.makeText(getContext(), "No Existen Usuarios", Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }


            for (Users o : usersArrayList) {
                DatabaseReference myref = database.getReference("Chats").child(o.getUid() + uid);
                myref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists()) {
                            rv.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            //usersArrayList.removeAll(usersArrayList);
                       /* for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Users users = snapshot.getValue(Users.class);
                            usersArrayList.add(users);
                        }*/
                            boolean band = false;
                            for (Users u : usersArrayListChats) {
                                if (u.getUid().equals(o.getUid())){
                                    band = true;
                                }

                            }
                            if (!band) {
                                usersArrayListChats.add(o);
                            }

                            adapter.notifyDataSetChanged();
                        } else {
                            progressBar.setVisibility(View.GONE);
                            //Toast.makeText(getContext(), "No Existen Usuarios", Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }

        return view;
    }
}