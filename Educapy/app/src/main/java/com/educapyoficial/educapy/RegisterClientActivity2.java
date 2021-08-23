package com.educapyoficial.educapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterClientActivity2 extends AppCompatActivity {

    EditText cajatextInputmama2, cajatextInputestudioscursa2, cajatextInputocupacionmama2, cajatextInputlugarnacimiento2, cajatextInputtelefonomama2, cajatextInputtelefonomovil2,
            cajatextInputhorariomama2, cajatextInputnombrepapa, cajatextInputestudiosPapá, cajatextInputocupacionpapa, cajatextInputlugartrabajopapa, cajatextInputtelefonopapacasa, cajatextInputtelefonocelular, cajatextInputhorariotrabajopapa,
            cajatextInputnumeropersonashogar, cajatextInputcuantoshermanos, cajatextInputquelugarocupa, cajatextInputvivecon, cajatextInputrelacionmama, cajatextInputrelacionpapa, cajatextInputrelacionhermano, cajatextInputrelacionotros;

    private CircleImageView mCircleImageBack, mCircleImageNext;

    String cajatextInputName1, cajatextInputapellidos1, cajatextInputlollaman1, cajatextInputlugarnacimiento1, cajatextInputEdad1, cajatextInputpeso1,
            cajatextInputestatura1, cajatextInputdomicilio1, cajatextInputtelefono1, cajatextInputtelefono1de2, almacenasexo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client2);
        mCircleImageBack = findViewById(R.id.circleImageBack2);
        mCircleImageNext = findViewById(R.id.circleImageNext2);
        cajatextInputmama2 = findViewById(R.id.textInputmama2);
        cajatextInputestudioscursa2 = findViewById(R.id.textInputestudioscursa2);
        cajatextInputocupacionmama2 = findViewById(R.id.textInputocupacionmama2);
        cajatextInputlugarnacimiento2 = findViewById(R.id.textInputlugarnacimiento2);
        cajatextInputtelefonomama2 = findViewById(R.id.textInputtelefonomama2);
        cajatextInputtelefonomovil2 = findViewById(R.id.textInputtelefonomovil2);
        cajatextInputhorariomama2 = findViewById(R.id.textInputhorariomama2);
        cajatextInputnombrepapa = findViewById(R.id.textInputnombrepapa);
        cajatextInputestudiosPapá = findViewById(R.id.textInputestudiosPapá);
        cajatextInputocupacionpapa = findViewById(R.id.textInputocupacionpapa);
        cajatextInputlugartrabajopapa = findViewById(R.id.textInputlugartrabajopapa);
        cajatextInputtelefonopapacasa = findViewById(R.id.textInputtelefonopapacasa);
        cajatextInputtelefonocelular = findViewById(R.id.textInputtelefonocelular);
        cajatextInputhorariotrabajopapa = findViewById(R.id.textInputhorariotrabajopapa);
        cajatextInputnumeropersonashogar = findViewById(R.id.textInputnumeropersonashogar);
        cajatextInputcuantoshermanos = findViewById(R.id.textInputcuantoshermanos);
        cajatextInputquelugarocupa = findViewById(R.id.textInputquelugarocupa);
        cajatextInputvivecon = findViewById(R.id.textInputvivecon);
        cajatextInputrelacionmama = findViewById(R.id.textInputrelacionmama);
        cajatextInputrelacionpapa = findViewById(R.id.textInputrelacionpapa);
        cajatextInputrelacionhermano = findViewById(R.id.textInputrelacionhermano);
        cajatextInputrelacionotros = findViewById(R.id.textInputrelacionotros);

        mCircleImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RegisterClientActivity2.this, RegisterClientActivity.class);
                startActivity(myIntent);
            }
        });


        Intent i = getIntent();
        cajatextInputName1 = i.getStringExtra("nombre1T");
        cajatextInputapellidos1 = i.getStringExtra("apellidos1T");
        cajatextInputlollaman1 = i.getStringExtra("lollaman1T");
        cajatextInputlugarnacimiento1 = i.getStringExtra("lugarnacimiento11T");
        cajatextInputEdad1 = i.getStringExtra("Edad11T");
        cajatextInputpeso1 = i.getStringExtra("peso1T");
        cajatextInputestatura1 = i.getStringExtra("estatura1T");
        cajatextInputdomicilio1 = i.getStringExtra("domicilio1T");
        cajatextInputtelefono1 = i.getStringExtra("telefono1T");
        cajatextInputtelefono1de2 = i.getStringExtra("telefono1de2T");
        almacenasexo = i.getStringExtra("sexo1T");


        mCircleImageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cajatextInputmama2.getText().toString().equals("") || cajatextInputestudioscursa2.getText().toString().equals("") || cajatextInputocupacionmama2.getText().toString().equals("") || cajatextInputlugarnacimiento2.getText().toString().equals("") || cajatextInputtelefonomama2.getText().toString().equals("") || cajatextInputtelefonomovil2.getText().toString().equals("") || cajatextInputhorariomama2.getText().toString().equals("") || cajatextInputnombrepapa.getText().toString().equals("") || cajatextInputestudiosPapá.getText().toString().equals("") || cajatextInputocupacionpapa.getText().toString().equals("") || cajatextInputlugartrabajopapa.equals("")
                        || cajatextInputtelefonopapacasa.equals("") || cajatextInputtelefonocelular.equals("") || cajatextInputhorariotrabajopapa.equals("") || cajatextInputnumeropersonashogar.equals("") || cajatextInputcuantoshermanos.equals("") || cajatextInputquelugarocupa.equals("") || cajatextInputvivecon.equals("") || cajatextInputrelacionmama.equals("") || cajatextInputrelacionpapa.equals("") || cajatextInputrelacionhermano.equals("") || cajatextInputrelacionotros.equals("")) { // compruebo que no este vacio los campos antes de enviar
                    validacion2();
                } else {
                    Intent myIntent = new Intent(RegisterClientActivity2.this, RegisterClientActivity3.class);
                    myIntent.putExtra("nombremama2T", cajatextInputmama2.getText().toString());
                    myIntent.putExtra("estudioscursadosmama2T", cajatextInputestudioscursa2.getText().toString());
                    myIntent.putExtra("ocupacionmama2T", cajatextInputocupacionmama2.getText().toString());
                    myIntent.putExtra("lugarnacimiento2T", cajatextInputlugarnacimiento2.getText().toString());
                    myIntent.putExtra("telefonomama2T", cajatextInputtelefonomama2.getText().toString());
                    myIntent.putExtra("telefonomovil2T", cajatextInputtelefonomovil2.getText().toString());
                    myIntent.putExtra("horariomama2T", cajatextInputhorariomama2.getText().toString());
                    myIntent.putExtra("nombrepapa2T", cajatextInputnombrepapa.getText().toString());
                    myIntent.putExtra("estudiospapa2T", cajatextInputestudiosPapá.getText().toString());
                    myIntent.putExtra("ocupacionpapa2T", cajatextInputocupacionpapa.getText().toString());
                    myIntent.putExtra("trabajopapa2T", cajatextInputlugartrabajopapa.getText().toString());
                    myIntent.putExtra("telefonocasapapa2T", cajatextInputtelefonopapacasa.getText().toString());
                    myIntent.putExtra("telefonocelularpapa2T", cajatextInputtelefonocelular.getText().toString());
                    myIntent.putExtra("horariopapa2T", cajatextInputhorariotrabajopapa.getText().toString());
                    myIntent.putExtra("personashogar2T", cajatextInputnumeropersonashogar.getText().toString());
                    myIntent.putExtra("cuantoshermanos2T", cajatextInputcuantoshermanos.getText().toString());
                    myIntent.putExtra("lugarocupa2T", cajatextInputquelugarocupa.getText().toString());
                    myIntent.putExtra("vivecon2T", cajatextInputvivecon.getText().toString());
                    myIntent.putExtra("relacionmama2T", cajatextInputrelacionmama.getText().toString());
                    myIntent.putExtra("relacionpapa2T", cajatextInputrelacionpapa.getText().toString());
                    myIntent.putExtra("relacionhermano2T", cajatextInputrelacionhermano.getText().toString());
                    myIntent.putExtra("relacionotros2T", cajatextInputrelacionotros.getText().toString());
                    myIntent.putExtra("nombre1T", cajatextInputName1);
                    myIntent.putExtra("apellidos1T", cajatextInputapellidos1);
                    myIntent.putExtra("lollaman1T", cajatextInputlollaman1);
                    myIntent.putExtra("lugarnacimiento11T", cajatextInputlugarnacimiento1);
                    myIntent.putExtra("Edad11T", cajatextInputEdad1);
                    myIntent.putExtra("peso1T", cajatextInputpeso1);
                    myIntent.putExtra("estatura1T", cajatextInputestatura1);
                    myIntent.putExtra("domicilio1T", cajatextInputdomicilio1);
                    myIntent.putExtra("telefono1T", cajatextInputtelefono1);
                    myIntent.putExtra("telefono1de2T", cajatextInputtelefono1de2);
                    myIntent.putExtra("sexo1T", almacenasexo);
                    // myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL BORRAR LA ACTIVIDAD COMPLETA Y NO REGRESAR AQUI
                    startActivity(myIntent);
                }


            }
        });


    }


    private void validacion2() {
        /*1*/
        String nombremama2t = cajatextInputmama2.getText().toString(); //utilizo esta forma para obtener el valor de los campos y validar los campos vacios  atravez de la clase validacion
        String estudioscursa2t = cajatextInputestudioscursa2.getText().toString();
        String ocupacionmama2T = cajatextInputocupacionmama2.getText().toString();
        String lugarnacimiento2T = cajatextInputlugarnacimiento2.getText().toString();
        String telefonomama2T = cajatextInputtelefonomama2.getText().toString();
        String telefonomovil2T = cajatextInputtelefonomovil2.getText().toString();
        String horariomama2T = cajatextInputhorariomama2.getText().toString();
        String nombrepapa2T = cajatextInputnombrepapa.getText().toString();
        String estudiospapa2T = cajatextInputestudiosPapá.getText().toString();
        String ocupacionpapa2T = cajatextInputocupacionpapa.getText().toString();
        String trabajopapa2T = cajatextInputlugartrabajopapa.getText().toString();
        String telefonocasapapa2T = cajatextInputtelefonopapacasa.getText().toString();
        String telefonocelularpapa2T = cajatextInputtelefonocelular.getText().toString();
        String trabajopapa2TRevisa = cajatextInputhorariotrabajopapa.getText().toString();
        String numeropersonahogar2T = cajatextInputnumeropersonashogar.getText().toString();
        String cuantoshermanos2T = cajatextInputcuantoshermanos.getText().toString();
        String lugarocupa2T = cajatextInputquelugarocupa.getText().toString();
        String vivecon2T = cajatextInputvivecon.getText().toString();
        String relacionmama2T = cajatextInputrelacionmama.getText().toString();
        String relacionpapa2T = cajatextInputrelacionpapa.getText().toString();
        String relacionhermano2T = cajatextInputrelacionhermano.getText().toString();
        String relacionotros2T = cajatextInputrelacionotros.getText().toString();


        if (nombremama2t.equals("")) {
            cajatextInputmama2.setError("Requerido");
        } else if (estudioscursa2t.equals("")) {
            cajatextInputestudioscursa2.setError("Requerido");
        } else if (ocupacionmama2T.equals("")) {
            cajatextInputocupacionmama2.setError("Requerido");
        } else if (lugarnacimiento2T.equals("")) {
            cajatextInputlugarnacimiento2.setError("Requerido");
        } else if (telefonomama2T.equals("")) {
            cajatextInputtelefonomama2.setError("Requerido");
        } else if (telefonomovil2T.equals("")) {
            cajatextInputtelefonomovil2.setError("Requerido");
        } else if (horariomama2T.equals("")) {
            cajatextInputhorariomama2.setError("Requerido");
        } else if (nombrepapa2T.equals("")) {
            cajatextInputnombrepapa.setError("Requerido");
        } else if (estudiospapa2T.equals("")) {
            cajatextInputestudiosPapá.setError("Requerido");
        } else if (ocupacionpapa2T.equals("")) {
            cajatextInputocupacionpapa.setError("Requerido");
        } else if (trabajopapa2T.equals("")) {
            cajatextInputlugartrabajopapa.setError("Requerido");
        } else if (telefonocasapapa2T.equals("")) {
            cajatextInputtelefonopapacasa.setError("Requerido");
        } else if (telefonocelularpapa2T.equals("")) {
            cajatextInputtelefonocelular.setError("Requerido");
        } else if (trabajopapa2TRevisa.equals("")) {
            cajatextInputlugartrabajopapa.setError("Requerido");
        } else if (numeropersonahogar2T.equals("")) {
            cajatextInputnumeropersonashogar.setError("Requerido");
        } else if (cuantoshermanos2T.equals("")) {
            cajatextInputcuantoshermanos.setError("Requerido");
        } else if (lugarocupa2T.equals("")) {
            cajatextInputquelugarocupa.setError("Requerido");
        } else if (vivecon2T.equals("")) {
            cajatextInputvivecon.setError("Requerido");
        } else if (relacionmama2T.equals("")) {
            cajatextInputrelacionmama.setError("Requerido");
        } else if (relacionpapa2T.equals("")) {
            cajatextInputrelacionpapa.setError("Requerido");
        } else if (relacionhermano2T.equals("")) {
            cajatextInputrelacionhermano.setError("Requerido");
        } else if (relacionotros2T.equals("")) {
            cajatextInputrelacionotros.setError("Requerido");
        }
    }

}
