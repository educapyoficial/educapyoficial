package com.educapyoficial.educapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.educapyoficial.educapy.models.EducapyModelUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterClientActivity3 extends AppCompatActivity {

    String cajatextInputName1, cajatextInputapellidos1, cajatextInputlollaman1, cajatextInputlugarnacimiento1, cajatextInputEdad1, cajatextInputpeso1,
            cajatextInputestatura1, cajatextInputdomicilio1, cajatextInputtelefono1, cajatextInputtelefono1de2, almacenasexo;

    String cajatextInputmama2, cajatextInputestudioscursa2, cajatextInputocupacionmama2, cajatextInputlugarnacimiento2, cajatextInputtelefonomama2, cajatextInputtelefonomovil2,
            cajatextInputhorariomama2, cajatextInputnombrepapa, cajatextInputestudiosPapá, cajatextInputocupacionpapa, cajatextInputlugartrabajopapa, cajatextInputtelefonopapacasa, cajatextInputtelefonocelular, cajatextInputhorariotrabajopapa,
            cajatextInputnumeropersonashogar, cajatextInputcuantoshermanos, cajatextInputquelugarocupa, cajatextInputvivecon, cajatextInputrelacionmama, cajatextInputrelacionpapa, cajatextInputrelacionhermano, cajatextInputrelacionotros;

    EditText cajatextInputcomofuembarazo3, cajatextInputcomofueparto3, cajatextInputdestete3,
    //cajatextInputlechematerna3,
    cajatextInputprimeraspalabras3,
            cajatextInputlegustacomer3,
    //cajatextInputfamiliacomejunta3,
    //cajatextInputduermebien3,
    cajatextInputcuandolesalieron3,
            //cajatextInputsanosusdientes3,
            cajatextInputcontrolesfinteres3,
                    //cajatextInputpronunciabien3,
                    //cajatextInputcomprendeloquesedice3,
                            cajatextInputcomoseporta3, cajatextInputcomosecomportacalle3,
                            //cajatextInputvistesolo3,
                            cajatextInputColaboraenActividadescotidianas3, cajatextInputquelegustahacer3;

    private CircleImageView mCircleImageBack3, mCircleImageNext3;
    private EducapyModelUser educapyModelUser;

    String lecheMaterna;
    RadioGroup radioGroupLecheMaterna;
    private RadioButton radioButtonSiLeche;
    private RadioButton radioButtonNoLeche;

    String comeJunto;
    RadioGroup radioFamiliaComeJunto;
    RadioButton radioButtonSiJuntos;
    RadioButton radioButtonNoJuntos;

    String duermeBien;
    RadioGroup radioDuermeBien;
    RadioButton radioButtonDuermeBienSi;
    RadioButton radioButtonDuermeBienNo;

    String dienteSano;
    RadioGroup radioSanoDiente;
    RadioButton radioButtonSanoDienteSi;
    RadioButton radioButtonSanoDienteNo;

    String pronunciaBien;
    RadioGroup radioPronunciaBien;
    RadioButton radioButtonPronunciaBienSi;
    RadioButton radioButtonPronunciaBienNo;

    String comprendeDice;
    RadioGroup radioComprendeDice;
    RadioButton radioButtonComprendeDiceSi;
    RadioButton radioButtonComprendeDiceNo;

    String visteSolo;
    RadioGroup radioVisteSolo;
    RadioButton radioButtonVisteSoloSi;
    RadioButton radioButtonVisteSoloNo;
    private String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client3);

        mCircleImageBack3 = findViewById(R.id.circleImageBack3);
        mCircleImageNext3 = findViewById(R.id.circleImageNext3);
        cajatextInputcomofuembarazo3 = findViewById(R.id.textInputcomofuembarazo3);
        cajatextInputcomofueparto3 = findViewById(R.id.textInputcomofueparto3);
        cajatextInputdestete3 = findViewById(R.id.textInputdestete3);
        //cajatextInputlechematerna3 = findViewById(R.id.textInputlechematerna3);
        radioGroupLecheMaterna = findViewById(R.id.radioLecheMaterna);
        radioButtonSiLeche = findViewById(R.id.radioButtonSiLeche);
        radioButtonNoLeche = findViewById(R.id.radioButtonNoLeche);

        cajatextInputprimeraspalabras3 = findViewById(R.id.textInputprimeraspalabras3);
        cajatextInputlegustacomer3 = findViewById(R.id.textInputlegustacomer3);
        //cajatextInputfamiliacomejunta3 = findViewById(R.id.textInputfamiliacomejunta3);
        radioFamiliaComeJunto = findViewById(R.id.radioFamiliaComeJunto);
        radioButtonSiJuntos = findViewById(R.id.radioButtonSiJuntos);
        radioButtonNoJuntos = findViewById(R.id.radioButtonNoJuntos);

        // cajatextInputduermebien3 = findViewById(R.id.textInputduermebien3);
        radioDuermeBien = findViewById(R.id.radioDuermeBien);
        radioButtonDuermeBienSi = findViewById(R.id.radioButtonDuermeBienSi);
        radioButtonDuermeBienNo = findViewById(R.id.radioButtonDuermeBienNo);

        cajatextInputcuandolesalieron3 = findViewById(R.id.textInputcuandolesalieron3);

        //cajatextInputsanosusdientes3 = findViewById(R.id.textInputsanosusdientes3);
        radioSanoDiente = findViewById(R.id.radioSanoDiente);
        radioButtonSanoDienteSi = findViewById(R.id.radioButtonSanoDienteSi);
        radioButtonSanoDienteNo = findViewById(R.id.radioButtonSanoDienteNo);

        cajatextInputcontrolesfinteres3 = findViewById(R.id.textInputcontrolesfinteres3);

        //cajatextInputpronunciabien3 = findViewById(R.id.textInputpronunciabien3);
        radioPronunciaBien = findViewById(R.id.radioPronunciaBien);
        radioButtonPronunciaBienSi = findViewById(R.id.radioButtonPronunciaBienSi);
        radioButtonPronunciaBienNo = findViewById(R.id.radioButtonPronunciaBienNo);


        //cajatextInputcomprendeloquesedice3 = findViewById(R.id.textInputcomprendeloquesedice3);
        radioComprendeDice = findViewById(R.id.radioComprendeDice);
        radioButtonComprendeDiceSi = findViewById(R.id.radioButtonComprendeDiceSi);
        radioButtonComprendeDiceNo = findViewById(R.id.radioButtonComprendeDiceNo);

        cajatextInputcomoseporta3 = findViewById(R.id.textInputcomoseporta3);
        cajatextInputcomosecomportacalle3 = findViewById(R.id.textInputcomosecomportacalle3);
        cajatextInputColaboraenActividadescotidianas3 = findViewById(R.id.textInputColaboraenActividadescotidianas3);

        //cajatextInputvistesolo3 = findViewById(R.id.textInputvistesolo3);
        radioVisteSolo = findViewById(R.id.radioVisteSolo);
        radioButtonVisteSoloSi = findViewById(R.id.radioButtonVisteSoloSi);
        radioButtonVisteSoloNo = findViewById(R.id.radioButtonVisteSoloNo);
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
        uid = i.getStringExtra("uid");

        Bundle bundle = i.getExtras();

        educapyModelUser = (EducapyModelUser) bundle.get("educapyModelUser");

        mCircleImageNext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedIdLecheMaterna = radioGroupLecheMaterna.getCheckedRadioButtonId();

                if (selectedIdLecheMaterna != 0) {
                    RadioButton radioButton = findViewById(selectedIdLecheMaterna);
                    lecheMaterna = radioButton.getText().toString();
                } else {
                    lecheMaterna = "";
                }


                int selectIdRadioFamiliaJunto = radioFamiliaComeJunto.getCheckedRadioButtonId();

                if (selectIdRadioFamiliaJunto != 0) {
                    RadioButton radioButton = findViewById(selectIdRadioFamiliaJunto);
                    comeJunto = radioButton.getText().toString();
                } else {
                    comeJunto = "";
                }

                int selectIdRadioDuermeBien = radioDuermeBien.getCheckedRadioButtonId();
                if (selectIdRadioDuermeBien != 0) {
                    RadioButton radioButton = findViewById(selectIdRadioDuermeBien);
                    duermeBien = radioButton.getText().toString();
                } else {
                    duermeBien = "";
                }

                int selectIdDienteSano = radioSanoDiente.getCheckedRadioButtonId();
                if (selectIdDienteSano != 0) {
                    RadioButton radioButton = findViewById(selectIdDienteSano);
                    dienteSano = radioButton.getText().toString();
                } else {
                    dienteSano = "";
                }

                int selectIdPronunciaBien = radioPronunciaBien.getCheckedRadioButtonId();
                if (selectIdPronunciaBien != 0) {
                    RadioButton radioButton = findViewById(selectIdPronunciaBien);
                    pronunciaBien = radioButton.getText().toString();
                } else {
                    pronunciaBien = "";
                }

                int selectIdComprendeDice = radioComprendeDice.getCheckedRadioButtonId();
                if (selectIdComprendeDice != 0) {
                    RadioButton radioButton = findViewById(selectIdComprendeDice);
                    comprendeDice = radioButton.getText().toString();
                } else {
                    comprendeDice = "";
                }

                int selectIdVisteSolo = radioVisteSolo.getCheckedRadioButtonId();
                if (selectIdVisteSolo != 0) {
                    RadioButton radioButton = findViewById(selectIdVisteSolo);
                    visteSolo = radioButton.getText().toString();
                } else {
                    visteSolo = "";
                }

                if (cajatextInputcomofuembarazo3.getText().toString().equals("") || cajatextInputcomofueparto3.getText().toString().equals("") || cajatextInputdestete3.getText().toString().equals("") || lecheMaterna.equals("") || cajatextInputprimeraspalabras3.getText().toString().equals("") || cajatextInputlegustacomer3.getText().toString().equals("") || comeJunto.equals("") || duermeBien.equals("") || cajatextInputcuandolesalieron3.getText().toString().equals("") || dienteSano.equals("") || cajatextInputcontrolesfinteres3.getText().toString().equals("")
                        || pronunciaBien.equals("") || comprendeDice.equals("") || cajatextInputcomoseporta3.getText().toString().equals("") || cajatextInputcomosecomportacalle3.getText().toString().equals("") || cajatextInputColaboraenActividadescotidianas3.getText().toString().equals("") || visteSolo.equals("") || cajatextInputquelegustahacer3.getText().toString().equals("")) { // compruebo que no este vacio los campos antes de enviar
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
                    //myIntent.putExtra("lechematerna3T", cajatextInputlechematerna3.getText().toString());
                    myIntent.putExtra("lechematerna3T", lecheMaterna);
                    myIntent.putExtra("primeraspalabras3T", cajatextInputprimeraspalabras3.getText().toString());
                    myIntent.putExtra("legustacomer3T", cajatextInputlegustacomer3.getText().toString());
                    myIntent.putExtra("familiacomejunta3T", comeJunto);
                    myIntent.putExtra("duermebien3T", duermeBien);
                    myIntent.putExtra("cuandolesalieron3T", cajatextInputcuandolesalieron3.getText().toString());
                    myIntent.putExtra("sanosusdientes3T", dienteSano);
                    myIntent.putExtra("controlinteres3T", cajatextInputcontrolesfinteres3.getText().toString());
                    myIntent.putExtra("pronunciabien3T", pronunciaBien);
                    myIntent.putExtra("comprendeloquedice3T", comprendeDice);
                    myIntent.putExtra("comosecomporta3T", cajatextInputcomoseporta3.getText().toString());
                    myIntent.putExtra("comosecomportacalle3T", cajatextInputcomosecomportacalle3.getText().toString());
                    myIntent.putExtra("actividadescotidianas3T", cajatextInputColaboraenActividadescotidianas3.getText().toString());
                    myIntent.putExtra("sevistesolo3T", visteSolo);
                    myIntent.putExtra("legustaquehacer3T", cajatextInputquelegustahacer3.getText().toString());
                    myIntent.putExtra("educapyModelUser", educapyModelUser);
                    myIntent.putExtra("uid", uid);
                    // myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL BORRAR LA ACTIVIDAD COMPLETA Y NO REGRESAR AQUI
                    startActivity(myIntent);
                }
            }
        });
        cargarDatos();

    }

    public void cargarDatos() {

        cajatextInputcomofuembarazo3.setText(educapyModelUser.getComofueembarazo3R());
        cajatextInputcomofueparto3.setText(educapyModelUser.getComofueparto3R());
        cajatextInputdestete3.setText(educapyModelUser.getDestete3R());
        //cajatextInputlechematerna3.setText(educapyModelUser.getLechematerna3R());

        if (educapyModelUser.getLechematerna3R() != null && !educapyModelUser.getLechematerna3R().equals("")) {
            lecheMaterna = educapyModelUser.getLechematerna3R();
            if (lecheMaterna.equalsIgnoreCase("Si")) {
                radioButtonSiLeche.setChecked(true);
                radioButtonNoLeche.setChecked(false);
            } else {
                radioButtonSiLeche.setChecked(false);
                radioButtonNoLeche.setChecked(true);
            }
        }

        cajatextInputprimeraspalabras3.setText(educapyModelUser.getPrimeraspalabras3R());
        cajatextInputlegustacomer3.setText(educapyModelUser.getLegustacomer3R());
        //cajatextInputfamiliacomejunta3.setText(educapyModelUser.getFamiliarcomejun3R());


        if (educapyModelUser.getFamiliarcomejun3R() != null && !educapyModelUser.getFamiliarcomejun3R().equals("")) {
            comeJunto = educapyModelUser.getFamiliarcomejun3R();
            if (comeJunto.equalsIgnoreCase("Si")) {
                radioButtonSiJuntos.setChecked(true);
                radioButtonNoJuntos.setChecked(false);
            } else {
                radioButtonSiJuntos.setChecked(false);
                radioButtonNoJuntos.setChecked(true);
            }
        }

        //cajatextInputduermebien3.setText(educapyModelUser.getDuermebien3R());
        if (educapyModelUser.getDuermebien3R() != null && !educapyModelUser.getDuermebien3R().equals("")) {
            duermeBien = educapyModelUser.getDuermebien3R();
            if (duermeBien.equalsIgnoreCase("Si")) {
                radioButtonDuermeBienSi.setChecked(true);
                radioButtonDuermeBienNo.setChecked(false);
            } else {
                radioButtonDuermeBienSi.setChecked(false);
                radioButtonDuermeBienNo.setChecked(true);
            }
        }

        cajatextInputcuandolesalieron3.setText(educapyModelUser.getCuandolesalieron3R());
        //cajatextInputsanosusdientes3.setText(educapyModelUser.getSanosusdientes3R());
        if (educapyModelUser.getSanosusdientes3R() != null && !educapyModelUser.getSanosusdientes3R().equals("")) {
            dienteSano = educapyModelUser.getSanosusdientes3R();
            if (dienteSano.equalsIgnoreCase("Si")) {
                radioButtonSanoDienteSi.setChecked(true);
                radioButtonSanoDienteNo.setChecked(false);
            } else {
                radioButtonSanoDienteSi.setChecked(false);
                radioButtonSanoDienteNo.setChecked(true);
            }
        }



        cajatextInputcontrolesfinteres3.setText(educapyModelUser.getControlesfinteres3R());
        //cajatextInputpronunciabien3.setText(educapyModelUser.getPronunciabien3R());
        if (educapyModelUser.getPronunciabien3R() != null && !educapyModelUser.getPronunciabien3R().equals("")) {
            pronunciaBien = educapyModelUser.getPronunciabien3R();
            if (pronunciaBien.equalsIgnoreCase("Si")) {
                radioButtonPronunciaBienSi.setChecked(true);
                radioButtonPronunciaBienNo.setChecked(false);
            } else {
                radioButtonPronunciaBienSi.setChecked(false);
                radioButtonPronunciaBienNo.setChecked(true);
            }
        }

        //cajatextInputcomprendeloquesedice3.setText(educapyModelUser.getComprendeloquedice3R());
        if (educapyModelUser.getComprendeloquedice3R() != null && !educapyModelUser.getComprendeloquedice3R().equals("")) {
            comprendeDice = educapyModelUser.getComprendeloquedice3R();
            if (comprendeDice.equalsIgnoreCase("Si")) {
                radioButtonComprendeDiceSi.setChecked(true);
                radioButtonComprendeDiceNo.setChecked(false);
            } else {
                radioButtonComprendeDiceSi.setChecked(false);
                radioButtonComprendeDiceNo.setChecked(true);
            }
        }

        cajatextInputcomoseporta3.setText(educapyModelUser.getComosecomporta3R());
        cajatextInputcomosecomportacalle3.setText(educapyModelUser.getComosecomportacalle3R());
        cajatextInputColaboraenActividadescotidianas3.setText(educapyModelUser.getActividadescotidianas3R());
        //cajatextInputvistesolo3.setText(educapyModelUser.getSevistesolo3R());
        if (educapyModelUser.getSevistesolo3R() != null && !educapyModelUser.getSevistesolo3R().equals("")) {
            visteSolo = educapyModelUser.getSevistesolo3R();
            if (visteSolo.equalsIgnoreCase("Si")) {
                radioButtonVisteSoloSi.setChecked(true);
                radioButtonVisteSoloNo.setChecked(false);
            } else {
                radioButtonVisteSoloSi.setChecked(false);
                radioButtonVisteSoloNo.setChecked(true);
            }
        }


        cajatextInputquelegustahacer3.setText(educapyModelUser.getLegustaquehacer3R());


    }

    private void validacion3() {
        /*1*/
        String nombremama2t = cajatextInputcomofuembarazo3.getText().toString(); //utilizo esta forma para obtener el valor de los campos y validar los campos vacios  atravez de la clase validacion
        String estudioscursa2t = cajatextInputcomofueparto3.getText().toString();
        String ocupacionmama2T = cajatextInputdestete3.getText().toString();
        String lecheMaternaSiNo = lecheMaterna;
        String telefonomama2T = cajatextInputprimeraspalabras3.getText().toString();
        String telefonomovil2T = cajatextInputlegustacomer3.getText().toString();
        //String horariomama2T = cajatextInputfamiliacomejunta3.getText().toString();
        //String nombrepapa2T = cajatextInputduermebien3.getText().toString();
        String estudiospapa2T = cajatextInputcuandolesalieron3.getText().toString();
        //String ocupacionpapa2T = cajatextInputsanosusdientes3.getText().toString();
        String trabajopapa2T = cajatextInputcontrolesfinteres3.getText().toString();
        //String telefonocasapapa2T = cajatextInputpronunciabien3.getText().toString();
        //String telefonocelularpapa2T = cajatextInputcomprendeloquesedice3.getText().toString();
        String trabajopapa2TRevisa = cajatextInputcomoseporta3.getText().toString();
        String numeropersonahogar2T = cajatextInputcomosecomportacalle3.getText().toString();
        String cuantoshermanos2T = cajatextInputColaboraenActividadescotidianas3.getText().toString();
        String lugarocupa2T = visteSolo;
        String vivecon2T = cajatextInputquelegustahacer3.getText().toString();


        if (nombremama2t.equals("")) {
            cajatextInputcomofuembarazo3.setError("Requerido");
        } else if (estudioscursa2t.equals("")) {
            cajatextInputcomofueparto3.setError("Requerido");
        } else if (ocupacionmama2T.equals("")) {
            cajatextInputdestete3.setError("Requerido");
        } else if (lecheMaternaSiNo.equals("")) {
            //radioGroupLecheMaterna.setError("Requerido");
        } else if (telefonomama2T.equals("")) {
            cajatextInputprimeraspalabras3.setError("Requerido");
        } else if (telefonomovil2T.equals("")) {
            cajatextInputlegustacomer3.setError("Requerido");
        } else if (comeJunto.equals("")) {
            //cajatextInputfamiliacomejunta3.setError("Requerido");
        } else if (duermeBien.equals("")) {
            //cajatextInputduermebien3.setError("Requerido");
        } else if (estudiospapa2T.equals("")) {
            cajatextInputcuandolesalieron3.setError("Requerido");
        } else if (dienteSano.equals("")) {
            //cajatextInputsanosusdientes3.setError("Requerido");
        } else if (trabajopapa2T.equals("")) {
            cajatextInputcontrolesfinteres3.setError("Requerido");
        } else if (pronunciaBien.equals("")) {
            //cajatextInputpronunciabien3.setError("Requerido");
        } else if (comprendeDice.equals("")) {
            //cajatextInputcomprendeloquesedice3.setError("Requerido");
        } else if (trabajopapa2TRevisa.equals("")) {
            cajatextInputcomoseporta3.setError("Requerido");
        } else if (numeropersonahogar2T.equals("")) {
            cajatextInputcomosecomportacalle3.setError("Requerido");
        } else if (cuantoshermanos2T.equals("")) {
            cajatextInputColaboraenActividadescotidianas3.setError("Requerido");
        } else if (lugarocupa2T.equals("")) {
            //cajatextInputvistesolo3.setError("Requerido");
        } else if (vivecon2T.equals("")) {
            cajatextInputquelegustahacer3.setError("Requerido");
        }
    }
}
