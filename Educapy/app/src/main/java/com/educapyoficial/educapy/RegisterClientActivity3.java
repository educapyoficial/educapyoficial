package com.educapyoficial.educapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterClientActivity3 extends AppCompatActivity {

    String cajatextInputName1, cajatextInputapellidos1, cajatextInputlollaman1, cajatextInputlugarnacimiento1, cajatextInputEdad1, cajatextInputpeso1,
            cajatextInputestatura1, cajatextInputdomicilio1, cajatextInputtelefono1, cajatextInputtelefono1de2, almacenasexo;

    String cajatextInputmama2, cajatextInputestudioscursa2, cajatextInputocupacionmama2, cajatextInputlugarnacimiento2, cajatextInputtelefonomama2, cajatextInputtelefonomovil2,
            cajatextInputhorariomama2, cajatextInputnombrepapa, cajatextInputestudiosPapá, cajatextInputocupacionpapa, cajatextInputlugartrabajopapa, cajatextInputtelefonopapacasa, cajatextInputtelefonocelular, cajatextInputhorariotrabajopapa,
            cajatextInputnumeropersonashogar, cajatextInputcuantoshermanos, cajatextInputquelugarocupa, cajatextInputvivecon, cajatextInputrelacionmama, cajatextInputrelacionpapa, cajatextInputrelacionhermano, cajatextInputrelacionotros;

    EditText cajatextInputcomofuembarazo3, cajatextInputcomofueparto3, cajatextInputdestete3, cajatextInputlechematerna3, cajatextInputprimeraspalabras3, cajatextInputlegustacomer3, cajatextInputfamiliacomejunta3, cajatextInputduermebien3, cajatextInputcuandolesalieron3,
            cajatextInputsanosusdientes3, cajatextInputcontrolesfinteres3, cajatextInputpronunciabien3, cajatextInputcomprendeloquesedice3, cajatextInputcomoseporta3, cajatextInputcomosecomportacalle3, cajatextInputvistesolo3, cajatextInputColaboraenActividadescotidianas3, cajatextInputquelegustahacer3;

    private CircleImageView mCircleImageBack3, mCircleImageNext3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client3);

        mCircleImageBack3 = findViewById(R.id.circleImageBack3);
        mCircleImageNext3 = findViewById(R.id.circleImageNext3);
        cajatextInputcomofuembarazo3 = findViewById(R.id.textInputcomofuembarazo3);
        cajatextInputcomofueparto3 = findViewById(R.id.textInputcomofueparto3);
        cajatextInputdestete3 = findViewById(R.id.textInputdestete3);
        cajatextInputlechematerna3 = findViewById(R.id.textInputlechematerna3);
        cajatextInputprimeraspalabras3 = findViewById(R.id.textInputprimeraspalabras3);
        cajatextInputlegustacomer3 = findViewById(R.id.textInputlegustacomer3);
        cajatextInputfamiliacomejunta3 = findViewById(R.id.textInputfamiliacomejunta3);
        cajatextInputduermebien3 = findViewById(R.id.textInputduermebien3);
        cajatextInputcuandolesalieron3 = findViewById(R.id.textInputcuandolesalieron3);
        cajatextInputsanosusdientes3 = findViewById(R.id.textInputsanosusdientes3);
        cajatextInputcontrolesfinteres3 = findViewById(R.id.textInputcontrolesfinteres3);
        cajatextInputpronunciabien3 = findViewById(R.id.textInputpronunciabien3);
        cajatextInputcomprendeloquesedice3 = findViewById(R.id.textInputcomprendeloquesedice3);
        cajatextInputcomoseporta3 = findViewById(R.id.textInputcomoseporta3);
        cajatextInputcomosecomportacalle3 = findViewById(R.id.textInputcomosecomportacalle3);
        cajatextInputColaboraenActividadescotidianas3 = findViewById(R.id.textInputColaboraenActividadescotidianas3);
        cajatextInputvistesolo3 = findViewById(R.id.textInputvistesolo3);
        cajatextInputquelegustahacer3 = findViewById(R.id.textInputquelegustahacer3);

        mCircleImageBack3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RegisterClientActivity3.this, RegisterClientActivity2.class);
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
        cajatextInputmama2 = i.getStringExtra("nombremama2T");
        cajatextInputestudioscursa2 = i.getStringExtra("estudioscursadosmama2T");
        cajatextInputocupacionmama2 = i.getStringExtra("ocupacionmama2T");
        cajatextInputlugarnacimiento2 = i.getStringExtra("nombremama2T");
        cajatextInputtelefonomama2 = i.getStringExtra("lugarnacimiento2T");
        cajatextInputtelefonomovil2 = i.getStringExtra("telefonomovil2T");
        cajatextInputhorariomama2 = i.getStringExtra("horariomama2T");
        cajatextInputnombrepapa = i.getStringExtra("nombrepapa2T");
        cajatextInputestudiosPapá = i.getStringExtra("estudiospapa2T");
        cajatextInputocupacionpapa = i.getStringExtra("ocupacionpapa2T");
        cajatextInputlugartrabajopapa = i.getStringExtra("trabajopapa2T");
        cajatextInputtelefonopapacasa = i.getStringExtra("telefonocasapapa2T");
        cajatextInputtelefonocelular = i.getStringExtra("telefonocelularpapa2T");
        cajatextInputhorariotrabajopapa = i.getStringExtra("horariopapa2T");
        cajatextInputnumeropersonashogar = i.getStringExtra("personashogar2T");
        cajatextInputcuantoshermanos = i.getStringExtra("cuantoshermanos2T");
        cajatextInputquelugarocupa = i.getStringExtra("lugarocupa2T");
        cajatextInputvivecon = i.getStringExtra("vivecon2T");
        cajatextInputrelacionmama = i.getStringExtra("relacionmama2T");
        cajatextInputrelacionpapa = i.getStringExtra("relacionpapa2T");
        cajatextInputrelacionhermano = i.getStringExtra("relacionhermano2T");


        mCircleImageNext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cajatextInputcomofuembarazo3.getText().toString().equals("") || cajatextInputcomofueparto3.getText().toString().equals("") || cajatextInputdestete3.getText().toString().equals("") || cajatextInputlechematerna3.getText().toString().equals("") || cajatextInputprimeraspalabras3.getText().toString().equals("") || cajatextInputlegustacomer3.getText().toString().equals("") || cajatextInputfamiliacomejunta3.getText().toString().equals("") || cajatextInputduermebien3.getText().toString().equals("") || cajatextInputcuandolesalieron3.getText().toString().equals("") || cajatextInputsanosusdientes3.getText().toString().equals("") || cajatextInputcontrolesfinteres3.getText().toString().equals("")
                        || cajatextInputpronunciabien3.getText().toString().equals("") || cajatextInputcomprendeloquesedice3.getText().toString().equals("") || cajatextInputcomoseporta3.getText().toString().equals("") || cajatextInputcomosecomportacalle3.getText().toString().equals("") || cajatextInputColaboraenActividadescotidianas3.getText().toString().equals("") || cajatextInputvistesolo3.getText().toString().equals("") || cajatextInputquelegustahacer3.getText().toString().equals("")) { // compruebo que no este vacio los campos antes de enviar
                    validacion3();
                } else {
                    Intent myIntent = new Intent(RegisterClientActivity3.this, RegisterClientActivity4.class);
                    myIntent.putExtra("nombremama2T", cajatextInputmama2);
                    myIntent.putExtra("estudioscursadosmama2T", cajatextInputestudioscursa2);
                    myIntent.putExtra("ocupacionmama2T", cajatextInputocupacionmama2);
                    myIntent.putExtra("lugarnacimiento2T", cajatextInputlugarnacimiento2);
                    myIntent.putExtra("telefonomama2T", cajatextInputtelefonomama2);
                    myIntent.putExtra("telefonomovil2T", cajatextInputtelefonomovil2);
                    myIntent.putExtra("horariomama2T", cajatextInputhorariomama2);
                    myIntent.putExtra("nombrepapa2T", cajatextInputnombrepapa);
                    myIntent.putExtra("estudiospapa2T", cajatextInputestudiosPapá);
                    myIntent.putExtra("ocupacionpapa2T", cajatextInputocupacionpapa);
                    myIntent.putExtra("trabajopapa2T", cajatextInputlugartrabajopapa);
                    myIntent.putExtra("telefonocasapapa2T", cajatextInputtelefonopapacasa);
                    myIntent.putExtra("telefonocelularpapa2T", cajatextInputtelefonocelular);
                    myIntent.putExtra("horariopapa2T", cajatextInputhorariotrabajopapa);
                    myIntent.putExtra("personashogar2T", cajatextInputnumeropersonashogar);
                    myIntent.putExtra("cuantoshermanos2T", cajatextInputcuantoshermanos);
                    myIntent.putExtra("lugarocupa2T", cajatextInputquelugarocupa);
                    myIntent.putExtra("vivecon2T", cajatextInputvivecon);
                    myIntent.putExtra("relacionmama2T", cajatextInputrelacionmama);
                    myIntent.putExtra("relacionpapa2T", cajatextInputrelacionpapa);
                    myIntent.putExtra("relacionhermano2T", cajatextInputrelacionhermano);
                    myIntent.putExtra("relacionotros2T", cajatextInputrelacionotros);
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
                    myIntent.putExtra("comofueembarazo3T", cajatextInputcomofuembarazo3.getText().toString());
                    myIntent.putExtra("comofueparto3T", cajatextInputcomofueparto3.getText().toString());
                    myIntent.putExtra("destetea3T", cajatextInputdestete3.getText().toString());
                    myIntent.putExtra("lechematerna3T", cajatextInputlechematerna3.getText().toString());
                    myIntent.putExtra("primeraspalabras3T", cajatextInputprimeraspalabras3.getText().toString());
                    myIntent.putExtra("legustacomer3T", cajatextInputlegustacomer3.getText().toString());
                    myIntent.putExtra("familiacomejunta3T", cajatextInputfamiliacomejunta3.getText().toString());
                    myIntent.putExtra("duermebien3T", cajatextInputduermebien3.getText().toString());
                    myIntent.putExtra("cuandolesalieron3T", cajatextInputcuandolesalieron3.getText().toString());
                    myIntent.putExtra("sanosusdientes3T", cajatextInputsanosusdientes3.getText().toString());
                    myIntent.putExtra("controlinteres3T", cajatextInputcontrolesfinteres3.getText().toString());
                    myIntent.putExtra("pronunciabien3T", cajatextInputpronunciabien3.getText().toString());
                    myIntent.putExtra("comprendeloquedice3T", cajatextInputcomprendeloquesedice3.getText().toString());
                    myIntent.putExtra("comosecomporta3T", cajatextInputcomoseporta3.getText().toString());
                    myIntent.putExtra("comosecomportacalle3T", cajatextInputcomosecomportacalle3.getText().toString());
                    myIntent.putExtra("actividadescotidianas3T", cajatextInputColaboraenActividadescotidianas3.getText().toString());
                    myIntent.putExtra("sevistesolo3T", cajatextInputvistesolo3.getText().toString());
                    myIntent.putExtra("legustaquehacer3T", cajatextInputquelegustahacer3.getText().toString());
                    // myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL BORRAR LA ACTIVIDAD COMPLETA Y NO REGRESAR AQUI
                    startActivity(myIntent);
                }
            }
        });


    }

    private void validacion3() {
        /*1*/
        String nombremama2t = cajatextInputcomofuembarazo3.getText().toString(); //utilizo esta forma para obtener el valor de los campos y validar los campos vacios  atravez de la clase validacion
        String estudioscursa2t = cajatextInputcomofueparto3.getText().toString();
        String ocupacionmama2T = cajatextInputdestete3.getText().toString();
        String lugarnacimiento2T = cajatextInputlechematerna3.getText().toString();
        String telefonomama2T = cajatextInputprimeraspalabras3.getText().toString();
        String telefonomovil2T = cajatextInputlegustacomer3.getText().toString();
        String horariomama2T = cajatextInputfamiliacomejunta3.getText().toString();
        String nombrepapa2T = cajatextInputduermebien3.getText().toString();
        String estudiospapa2T = cajatextInputcuandolesalieron3.getText().toString();
        String ocupacionpapa2T = cajatextInputsanosusdientes3.getText().toString();
        String trabajopapa2T = cajatextInputcontrolesfinteres3.getText().toString();
        String telefonocasapapa2T = cajatextInputpronunciabien3.getText().toString();
        String telefonocelularpapa2T = cajatextInputcomprendeloquesedice3.getText().toString();
        String trabajopapa2TRevisa = cajatextInputcomoseporta3.getText().toString();
        String numeropersonahogar2T = cajatextInputcomosecomportacalle3.getText().toString();
        String cuantoshermanos2T = cajatextInputColaboraenActividadescotidianas3.getText().toString();
        String lugarocupa2T = cajatextInputvistesolo3.getText().toString();
        String vivecon2T = cajatextInputquelegustahacer3.getText().toString();


        if (nombremama2t.equals("")) {
            cajatextInputcomofuembarazo3.setError("Requerido");
        } else if (estudioscursa2t.equals("")) {
            cajatextInputcomofueparto3.setError("Requerido");
        } else if (ocupacionmama2T.equals("")) {
            cajatextInputdestete3.setError("Requerido");
        } else if (lugarnacimiento2T.equals("")) {
            cajatextInputlechematerna3.setError("Requerido");
        } else if (telefonomama2T.equals("")) {
            cajatextInputprimeraspalabras3.setError("Requerido");
        } else if (telefonomovil2T.equals("")) {
            cajatextInputlegustacomer3.setError("Requerido");
        } else if (horariomama2T.equals("")) {
            cajatextInputfamiliacomejunta3.setError("Requerido");
        } else if (nombrepapa2T.equals("")) {
            cajatextInputduermebien3.setError("Requerido");
        } else if (estudiospapa2T.equals("")) {
            cajatextInputcuandolesalieron3.setError("Requerido");
        } else if (ocupacionpapa2T.equals("")) {
            cajatextInputsanosusdientes3.setError("Requerido");
        } else if (trabajopapa2T.equals("")) {
            cajatextInputcontrolesfinteres3.setError("Requerido");
        } else if (telefonocasapapa2T.equals("")) {
            cajatextInputpronunciabien3.setError("Requerido");
        } else if (telefonocelularpapa2T.equals("")) {
            cajatextInputcomprendeloquesedice3.setError("Requerido");
        } else if (trabajopapa2TRevisa.equals("")) {
            cajatextInputcomoseporta3.setError("Requerido");
        } else if (numeropersonahogar2T.equals("")) {
            cajatextInputcomosecomportacalle3.setError("Requerido");
        } else if (cuantoshermanos2T.equals("")) {
            cajatextInputColaboraenActividadescotidianas3.setError("Requerido");
        } else if (lugarocupa2T.equals("")) {
            cajatextInputvistesolo3.setError("Requerido");
        } else if (vivecon2T.equals("")) {
            cajatextInputquelegustahacer3.setError("Requerido");
        }
    }
}
