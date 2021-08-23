package com.educapyoficial.educapy;


import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthProvider {

    //para que pueda loguearme con los datos que estoy recibiendo en email y password que previamente los declare como string
    FirebaseAuth mAuth;

    public AuthProvider() {
        mAuth = FirebaseAuth.getInstance();
    }

    public Task<AuthResult> register(String email, String password) //corro el procedimento para que cree una estancia temporal y pueda trbajar con estos datos
    {
        return mAuth.createUserWithEmailAndPassword(email, password);
    }

    public Task<AuthResult> login(String email, String password) //hago el login atravez de este metodo login que envia a firebase email y password
    {
        return mAuth.signInWithEmailAndPassword(email, password);
    }

    public void logout() {
        mAuth.signOut();
    }
}
