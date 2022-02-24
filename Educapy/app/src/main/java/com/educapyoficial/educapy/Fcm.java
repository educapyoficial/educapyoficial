package com.educapyoficial.educapy;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.educapyoficial.educapy.models.EducapyModelUser;
import com.educapyoficial.educapy.models.RegistroAnecdotario;
import com.educapyoficial.educapy.pojos.Users;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.Principal;
import java.util.Random;

public class Fcm extends FirebaseMessagingService {

    /*
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

        Log.e("token2","mi token es : "+s);
        guardartoken(s);

    }

    private void guardartoken(String s) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("token");
        ref.child("matias").setValue(s);
    }

     */

    RegistroAnecdotario registroAnecdotario;
    EducapyModelUser educapyModelUser;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;

    @Override
    public void onNewToken(String token) {
        Log.d("REGENERAR_TOKEN", "Refreshed token: " + token);
        //DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("token");
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
        sendRegistrationToServer(token);

    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String from = remoteMessage.getFrom();

        //  Log.e("TAG","mensaje recibido de "+ from);

        /*
        if(remoteMessage.getNotification() != null)
        {
            Log.e("TAG","el titulo es "+remoteMessage.getNotification().getTitle());
            Log.e("TAG","el detalle es "+remoteMessage.getNotification().getBody());
        }
         */
        if (remoteMessage.getData().size() > 0) {

            Gson gson = new GsonBuilder()
                    .setDateFormat("dd/MM/yyyy")
                    .create();

            registroAnecdotario = gson.fromJson(remoteMessage.getData().get("registroAnecdotario"), RegistroAnecdotario.class);
            educapyModelUser = gson.fromJson(remoteMessage.getData().get("educapyModelUser"), EducapyModelUser.class);

            String titulo = remoteMessage.getData().get("titulo");
            String detalle = remoteMessage.getData().get("detalle");

            mayorqueoreo(titulo, detalle);

        }
    }


    private void mayorqueoreo(String titulo, String detalle) {
        String id = "mensaje";

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel nc = new NotificationChannel(id, "nuevo", NotificationManager.IMPORTANCE_HIGH);
            nc.setShowBadge(true);
            assert nm != null;
            nm.createNotificationChannel(nc);
        }

        builder.setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(titulo)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(detalle)
                .setContentIntent(clicknoti())
                .setContentInfo("nuevo");
        Random random = new Random();
        int idNotify = random.nextInt(8000);

        assert nm != null;
        nm.notify(idNotify, builder.build());
    }

    public PendingIntent clicknoti() {
        Intent nf = new Intent(getApplicationContext(), RegistrarAnectotarioAlumno.class); //al activitiy que querems que nos redireccione la notificacion
        nf.putExtra("educapyModelUser", educapyModelUser);
        nf.putExtra("registroAnecdotario", registroAnecdotario);
        nf.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(this, 0, nf, 0);
    }


    public void sendRegistrationToServer(String tokenNuevo) {

        educapyModelUser.setTokenFirebase(tokenNuevo);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref_user = mDatabase.child("UsersChat").child(educapyModelUser.getUid());
        Users users = new Users();
        users.setFecha("");
        users.setTokenFirebase(tokenNuevo);
        users.setId(educapyModelUser.getUid());
        users.setUid(educapyModelUser.getUid());
        users.setMail(educapyModelUser.getEmailR());
        users.setNombre(educapyModelUser.getNombre1R());
        ref_user.setValue(users);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").child("Clients").child(educapyModelUser.getUid()).setValue(educapyModelUser);





       /* FirebaseUser user = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        if (user != null) {
            String email = user.getEmail();
            Query query = mDatabase.child("Users").child("Clients").orderByChild("emailR").equalTo(email);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (!dataSnapshot.getChildren().iterator().hasNext()) {
                        //signInProfesor();
                    } else {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            educapyModelUser = data.getValue(EducapyModelUser.class);
                            if (educapyModelUser != null) {
                                if (educapyModelUser.getEstado() != null && !educapyModelUser.getEstado().equals("I")) {
                                    //educapyModelUser.setGkeR(user.getUid());
                                    //educapyModelUser.setUidfirebase(user.getUid());
                                    //educapyModelUser.setEstado("A");
                                    //mDatabase.child("Users").child("Clients").child(educapyModelUser.getUid()).setValue(educapyModelUser);
                                    //SharedPreferences.Editor editor = mPref.edit();
                                    //editor.putString("uid", educapyModelUser.getUid());
                                    //editor.commit();

                                    //registrarToken(educapyModelUser, null);


                                } else {
                                    Toast.makeText(Fcm.this, "El usuario " + educapyModelUser.getEmailR() + " se encuentra inactivado, Consulte con el administrador.", Toast.LENGTH_LONG).show();
                                }

                            }
                        }
                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    //signInProfesor();
                    Log.d("Error dd", databaseError.getMessage());
                }
            });
        } else {
            Toast.makeText(this, "No Hay usuario logueado.", Toast.LENGTH_SHORT).show();
        }*/
    }
}
