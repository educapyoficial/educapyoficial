package com.educapyoficial.educapy.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.educapyoficial.educapy.MainActivity;
import com.educapyoficial.educapy.R;
import com.educapyoficial.educapy.homeActivity;
import com.educapyoficial.educapy.principal;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class mis_solicitudes_Fragment extends Fragment {


    Button btnd_salida;
    private FirebaseAuth mAuth;

    public mis_solicitudes_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_mis_solicitudes_, container, false);

        mAuth = FirebaseAuth.getInstance();

        btnd_salida = root.findViewById(R.id.btn_salir);

        btnd_salida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AuthUI.getInstance()
                        .signOut(getActivity())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                              //  finish();
                            //    Toast.makeText(getContext(), "Cerrando seccion", Toast.LENGTH_SHORT).show();

                                vamosalogin();
                            }
                        });

                Toast.makeText(getActivity(), "salida ok", Toast.LENGTH_SHORT).show();
            }
        });

        return root;

    }

    private void vamosalogin() {
        Intent i = new Intent(getActivity(),MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}