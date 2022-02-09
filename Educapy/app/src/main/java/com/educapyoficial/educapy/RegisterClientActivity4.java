package com.educapyoficial.educapy;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.educapyoficial.educapy.models.EducapyModelUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;

public class RegisterClientActivity4 extends AppCompatActivity {

    String cajatextInputName1, cajatextInputapellidos1, cajatextInputlollaman1, cajatextInputlugarnacimiento1, cajatextInputEdad1, cajatextInputpeso1,
            cajatextInputestatura1, cajatextInputdomicilio1, cajatextInputtelefono1, cajatextInputtelefono1de2, almacenasexo1Q;

    String cajatextInputmama2, cajatextInputestudioscursa2, cajatextInputocupacionmama2, cajatextInputlugarnacimiento2, cajatextInputtelefonomama2, cajatextInputtelefonomovil2,
            cajatextInputhorariomama2, cajatextInputnombrepapa2, cajatextInputestudiosPapá2, cajatextInputocupacionpapa2, cajatextInputlugartrabajopapa2, cajatextInputtelefonopapacasa2, cajatextInputtelefonocelular2, cajatextInputhorariotrabajopapa2,
            cajatextInputnumeropersonashogar2, cajatextInputcuantoshermanos2, cajatextInputquelugarocupa2, cajatextInputvivecon2, cajatextInputrelacionmama2, cajatextInputrelacionpapa2, cajatextInputrelacionhermano2, cajatextInputrelacionotros2;

    String cajatextInputcomofuembarazo3, cajatextInputcomofueparto3, cajatextInputdestete3, cajatextInputlechematerna3, cajatextInputprimeraspalabras3, cajatextInputlegustacomer3, cajatextInputfamiliacomejunta3, cajatextInputduermebien3, cajatextInputcuandolesalieron3,
            cajatextInputsanosusdientes3, cajatextInputcontrolesfinteres3, cajatextInputpronunciabien3, cajatextInputcomprendeloquesedice3, cajatextInputcomoseporta3, cajatextInputcomosecomportacalle3, cajatextInputvistesolo3, cajatextInputColaboraenActividadescotidianas3, cajatextInputquelegustahacer3;

    private CircleImageView mCircleImageBack4, mCircleImageNext4;

    EditText cajatextInputinstitucioneducativa4, cajatextInputespacioeducativo4,
    //cajatextInputEsperafinalizarpreescolar4,
    //cajatextInputEscribirsunombre4,
    //cajatextInputleerpalabras4,
    //cajatextInputcontar4,
    cajatextInputseleccionintituto4, cajatextInputesperasinstitucion4, cajatextInputenquepuedelaborar4
            //cajacorreo
    ;


    AuthProvider mAuthProvider;
    ClientProvider mClientProvider;
    AlertDialog mDialog;
    DatabaseReference imgref;
    StorageReference storageReference;
    ProgressDialog cargando;
    private DatabaseReference mDatabase;

    //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    //String uid = user.getUid();
    private EducapyModelUser educapyModelUser;

    RadioGroup radioReconoceLetras;
    RadioButton radioButtonReconoceSi;
    RadioButton radioButtonReconoceNo;

    RadioGroup radioEscribeNombre;
    RadioButton radioButtonEscribeNomSi;
    RadioButton radioButtonEscribeNomNo;
    String escribeNombre;

    RadioGroup radioLeePalabras;
    RadioButton radioButtonLeePalabrasSi;
    RadioButton radioButtonLeePalabrasNo;
    String leePalabras;

    RadioGroup radioCuentaPalabras;
    RadioButton radioButtonCuentaPalabrasSi;
    RadioButton radioButtonCuentaPalabrasNo;
    String cuentaPalabra;
    private String reconoceLetras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client4);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mCircleImageBack4 = findViewById(R.id.circleImageBack4);
        mCircleImageNext4 = findViewById(R.id.circleImageNext4);
        //cajacorreo = findViewById(R.id.textInputcorreo4);
        cajatextInputinstitucioneducativa4 = findViewById(R.id.textInputinstitucioneducativa4);
        cajatextInputespacioeducativo4 = findViewById(R.id.textInputespacioeducativo4);
        //cajatextInputEsperafinalizarpreescolar4 = findViewById(R.id.textInputEsperafinalizarpreescolar4);
        //cajatextInputEscribirsunombre4 = findViewById(R.id.textInputEscribirsunombre4);
        //cajatextInputleerpalabras4 = findViewById(R.id.textInputleerpalabras4);
        //cajatextInputcontar4 = findViewById(R.id.textInputcontar4);

        radioReconoceLetras = findViewById(R.id.radioReconoceLetras);
        radioButtonReconoceSi = findViewById(R.id.radioButtonReconoceSi);
        radioButtonReconoceNo = findViewById(R.id.radioButtonReconoceNo);


        radioEscribeNombre = findViewById(R.id.radioEscribeNombre);
        radioButtonEscribeNomSi = findViewById(R.id.radioButtonEscribeNomSi);
        radioButtonEscribeNomNo = findViewById(R.id.radioButtonEscribeNomNo);

        radioLeePalabras = findViewById(R.id.radioLeePalabras);
        radioButtonLeePalabrasSi = findViewById(R.id.radioButtonLeePalabrasSi);
        radioButtonLeePalabrasNo = findViewById(R.id.radioButtonLeePalabrasNo);

        radioCuentaPalabras = findViewById(R.id.radioCuentaPalabras);
        radioButtonCuentaPalabrasSi = findViewById(R.id.radioButtonCuentaPalabrasSi);
        radioButtonCuentaPalabrasNo = findViewById(R.id.radioButtonCuentaPalabrasNo);

        cajatextInputseleccionintituto4 = findViewById(R.id.textInputseleccionintituto4);
        cajatextInputesperasinstitucion4 = findViewById(R.id.textInputesperasinstitucion4);
        cajatextInputenquepuedelaborar4 = findViewById(R.id.textInputenquepuedelaborar4);

        cargando = new ProgressDialog(this);
        mAuthProvider = new AuthProvider();
        mClientProvider = new ClientProvider();
        //cajacorreo.setText(user.getEmail());

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDialog = new SpotsDialog.Builder().setContext(RegisterClientActivity4.this).setMessage("Espere Un Momento").build();

        mCircleImageBack4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RegisterClientActivity4.this, RegisterClientActivity3.class);
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
        almacenasexo1Q = i.getStringExtra("sexo1T");
        cajatextInputmama2 = i.getStringExtra("nombremama2T");
        cajatextInputestudioscursa2 = i.getStringExtra("estudioscursadosmama2T");
        cajatextInputocupacionmama2 = i.getStringExtra("ocupacionmama2T");
        cajatextInputlugarnacimiento2 = i.getStringExtra("nombremama2T");
        cajatextInputtelefonomama2 = i.getStringExtra("lugarnacimiento2T");
        cajatextInputtelefonomovil2 = i.getStringExtra("telefonomovil2T");
        cajatextInputhorariomama2 = i.getStringExtra("horariomama2T");
        cajatextInputnombrepapa2 = i.getStringExtra("nombrepapa2T");
        cajatextInputestudiosPapá2 = i.getStringExtra("estudiospapa2T");
        cajatextInputocupacionpapa2 = i.getStringExtra("ocupacionpapa2T");
        cajatextInputlugartrabajopapa2 = i.getStringExtra("trabajopapa2T");
        cajatextInputtelefonopapacasa2 = i.getStringExtra("telefonocasapapa2T");
        cajatextInputtelefonocelular2 = i.getStringExtra("telefonocelularpapa2T");
        cajatextInputhorariotrabajopapa2 = i.getStringExtra("horariopapa2T");
        cajatextInputnumeropersonashogar2 = i.getStringExtra("personashogar2T");
        cajatextInputcuantoshermanos2 = i.getStringExtra("cuantoshermanos2T");
        cajatextInputquelugarocupa2 = i.getStringExtra("lugarocupa2T");
        cajatextInputvivecon2 = i.getStringExtra("vivecon2T");
        cajatextInputrelacionmama2 = i.getStringExtra("relacionmama2T");
        cajatextInputrelacionpapa2 = i.getStringExtra("relacionpapa2T");
        cajatextInputrelacionhermano2 = i.getStringExtra("relacionhermano2T");
        cajatextInputcomofuembarazo3 = i.getStringExtra("comofueembarazo3T");
        cajatextInputcomofueparto3 = i.getStringExtra("comofueparto3T");
        cajatextInputdestete3 = i.getStringExtra("destetea3T");
        cajatextInputlechematerna3 = i.getStringExtra("lechematerna3T");
        cajatextInputprimeraspalabras3 = i.getStringExtra("primeraspalabras3T");
        cajatextInputlegustacomer3 = i.getStringExtra("legustacomer3T");
        cajatextInputfamiliacomejunta3 = i.getStringExtra("familiacomejunta3T");
        cajatextInputduermebien3 = i.getStringExtra("duermebien3T");
        cajatextInputcuandolesalieron3 = i.getStringExtra("cuandolesalieron3T");
        cajatextInputsanosusdientes3 = i.getStringExtra("sanosusdientes3T");
        cajatextInputcontrolesfinteres3 = i.getStringExtra("controlinteres3T");
        cajatextInputpronunciabien3 = i.getStringExtra("pronunciabien3T");
        cajatextInputcomprendeloquesedice3 = i.getStringExtra("comprendeloquedice3T");
        cajatextInputcomoseporta3 = i.getStringExtra("comosecomporta3T");
        cajatextInputcomosecomportacalle3 = i.getStringExtra("comosecomportacalle3T");
        cajatextInputColaboraenActividadescotidianas3 = i.getStringExtra("actividadescotidianas3T");
        cajatextInputvistesolo3 = i.getStringExtra("sevistesolo3T");
        cajatextInputquelegustahacer3 = i.getStringExtra("legustaquehacer3T");

        Bundle bundle = i.getExtras();
        educapyModelUser = (EducapyModelUser) bundle.get("educapyModelUser");

        mCircleImageNext4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedIdReconoce = radioReconoceLetras.getCheckedRadioButtonId();
                if (selectedIdReconoce != 0) {
                    RadioButton radioButton = findViewById(selectedIdReconoce);
                    reconoceLetras = radioButton.getText().toString();
                } else {
                    reconoceLetras = "";
                }

                int selectedIdEsNom = radioEscribeNombre.getCheckedRadioButtonId();
                if (selectedIdEsNom != 0) {
                    RadioButton radioButton = findViewById(selectedIdEsNom);
                    escribeNombre = radioButton.getText().toString();
                } else {
                    escribeNombre = "";
                }

                int selectIdLeePalabras = radioLeePalabras.getCheckedRadioButtonId();
                if (selectIdLeePalabras != 0) {
                    RadioButton radioButton = findViewById(selectIdLeePalabras);
                    leePalabras = radioButton.getText().toString();
                } else {
                    leePalabras = "";
                }

                int selectIdCuentaPalabras = radioCuentaPalabras.getCheckedRadioButtonId();
                if (selectIdCuentaPalabras != 0) {
                    RadioButton radioButton = findViewById(selectIdCuentaPalabras);
                    cuentaPalabra = radioButton.getText().toString();
                } else {
                    cuentaPalabra = "";
                }



                if (cajatextInputinstitucioneducativa4.getText().toString().equals("") || cajatextInputespacioeducativo4.getText().toString().equals("")
                        || cajatextInputseleccionintituto4.getText().toString().equals("") ||
                        escribeNombre.equals("") || cajatextInputesperasinstitucion4.getText().toString().equals("")
                        || cajatextInputenquepuedelaborar4.getText().toString().equals("") || leePalabras.equals("")
                        || cuentaPalabra.equals("")
                || reconoceLetras.equals("")) { // compruebo que no este vacio los campos antes de enviar

                    validacion4();
                    Toast.makeText(RegisterClientActivity4.this, "falta llenar campos", Toast.LENGTH_SHORT).show();
                } else {

                    clickRegister();
                }

                /*
                if (cajacorreo.getText().toString().equals("") || cajacontraseña.getText().toString().equals("") || cajatextInputinstitucioneducativa4.getText().toString().equals("") || cajatextInputespacioeducativo4.getText().toString().equals("") || cajatextInputEsperafinalizarpreescolar4.getText().toString().equals("") || cajatextInputEscribirsunombre4.getText().toString().equals("") || cajatextInputleerpalabras4.getText().toString().equals("") || cajatextInputcontar4.getText().toString().equals("") || cajatextInputseleccionintituto4.getText().toString().equals("") || cajatextInputesperasinstitucion4.getText().toString().equals("") || cajatextInputenquepuedelaborar4.getText().toString().equals("")) { // compruebo que no este vacio los campos antes de enviar
                    validacion4();
                    Toast.makeText(RegisterClientActivity4.this, "falta llenar campos", Toast.LENGTH_SHORT).show();
                } else {

                    clickRegister();
                }

                 */
            }
        });

        cargarDatos();

    }

    public void cargarDatos() {

        cajatextInputinstitucioneducativa4.setText(educapyModelUser.getInstitucioneducativaR());
        cajatextInputespacioeducativo4.setText(educapyModelUser.getEspacioeducativoOR());
        //cajatextInputEsperafinalizarpreescolar4.setText(educapyModelUser.getFinalizarprescolarR());
        if (educapyModelUser.getFinalizarprescolarR() != null && !educapyModelUser.getFinalizarprescolarR().equals("")) {
            reconoceLetras = educapyModelUser.getFinalizarprescolarR();
            if (reconoceLetras.equalsIgnoreCase("Si")) {
                radioButtonReconoceSi.setChecked(true);
                radioButtonReconoceNo.setChecked(false);
            } else {
                radioButtonReconoceSi.setChecked(false);
                radioButtonReconoceNo.setChecked(true);
            }
        }

        if (educapyModelUser.getEscribirsunombreR() != null && !educapyModelUser.getEscribirsunombreR().equals("")) {
            escribeNombre = educapyModelUser.getEscribirsunombreR();
            if (escribeNombre.equalsIgnoreCase("Si")) {
                radioButtonEscribeNomSi.setChecked(true);
                radioButtonEscribeNomNo.setChecked(false);
            } else {
                radioButtonEscribeNomSi.setChecked(false);
                radioButtonEscribeNomNo.setChecked(true);
            }
        }

        if (educapyModelUser.getContarR() != null && !educapyModelUser.getContarR().equals("")) {
            cuentaPalabra = educapyModelUser.getContarR();
            if (cuentaPalabra.equalsIgnoreCase("Si")) {
                radioButtonCuentaPalabrasSi.setChecked(true);
                radioButtonCuentaPalabrasNo.setChecked(false);
            } else {
                radioButtonCuentaPalabrasSi.setChecked(false);
                radioButtonCuentaPalabrasNo.setChecked(true);
            }
        }

        if (educapyModelUser.getLeerpalabraR() != null && !educapyModelUser.getLeerpalabraR().equals("")) {
            leePalabras = educapyModelUser.getLeerpalabraR();
            if (leePalabras.equalsIgnoreCase("Si")) {
                radioButtonLeePalabrasSi.setChecked(true);
                radioButtonLeePalabrasNo.setChecked(false);
            } else {
                radioButtonLeePalabrasSi.setChecked(false);
                radioButtonLeePalabrasNo.setChecked(true);
            }
        }

        //cajatextInputEscribirsunombre4.setText(educapyModelUser.getEscribirsunombreR());
        //cajatextInputleerpalabras4.setText(educapyModelUser.getLeerpalabraR());
        //cajatextInputcontar4.setText(educapyModelUser.getContarR());
        cajatextInputseleccionintituto4.setText(educapyModelUser.getPaisRegister2R());
        cajatextInputesperasinstitucion4.setText(educapyModelUser.getPaisRegister3R());
        cajatextInputenquepuedelaborar4.setText(educapyModelUser.getPaisRegister4R());


        cajatextInputinstitucioneducativa4.getText().toString(); //utilizo esta forma para obtener el valor de los campos y validar los campos vacios  atravez de la clase validacion
        String estudioscursa2t = cajatextInputespacioeducativo4.getText().toString();
        //String ocupacionmama2T = cajatextInputEsperafinalizarpreescolar4.getText().toString();
        //String lugarnacimiento2T = cajatextInputEscribirsunombre4.getText().toString();
        //String telefonomama2T = cajatextInputleerpalabras4.getText().toString();
        //String telefonomovil2T = cajatextInputcontar4.getText().toString();
        String horariomama2T = cajatextInputseleccionintituto4.getText().toString();
        String nombrepapa2T = cajatextInputesperasinstitucion4.getText().toString();
        String estudiospapa2T = cajatextInputenquepuedelaborar4.getText().toString();
    }

    void clickRegister() {
        //final String email = cajacorreo.getText().toString();
        final String institucioneducativaO = cajatextInputinstitucioneducativa4.getText().toString();
        final String espacioeducativoO = cajatextInputespacioeducativo4.getText().toString();
        final String finalizarprescolarO = reconoceLetras;
        final String escribirsunombreO = escribeNombre;
        final String leerpalabraO = leePalabras;
        final String contarO = cuentaPalabra;
        final String paisRegister2 = cajatextInputseleccionintituto4.getText().toString();
        final String paisRegister3 = cajatextInputesperasinstitucion4.getText().toString();
        final String paisRegister4 = cajatextInputenquepuedelaborar4.getText().toString();
        final String gkey = "";

        final String nombre1 = cajatextInputName1;
        final String apellidos1 = cajatextInputapellidos1;
        final String lollaman1 = cajatextInputlollaman1;
        final String lugarnacimiento1 = cajatextInputlugarnacimiento1;
        final String edad1 = cajatextInputEdad1;
        final String peso1 = cajatextInputpeso1;
        final String estatura1 = cajatextInputestatura1;
        final String domicilio1 = cajatextInputdomicilio1;
        final String telefono1 = cajatextInputtelefono1;
        final String telefono2 = cajatextInputtelefono1de2;
        final String almacenasexo1 = almacenasexo1Q;

        final String nombremama2 = cajatextInputmama2;
        final String estudioscursamama2 = cajatextInputestudioscursa2;
        final String ocupacionmama2 = cajatextInputocupacionmama2;
        final String nacimientomama2 = cajatextInputlugarnacimiento2;
        final String telefonomama2 = cajatextInputtelefonomama2;
        final String telefonomovilmama2 = cajatextInputtelefonomovil2;
        final String horariomama2 = cajatextInputhorariomama2;
        final String nombrepapa2 = cajatextInputnombrepapa2;
        final String estudiospapa2 = cajatextInputestudiosPapá2;
        final String ocupacionpapa2 = cajatextInputocupacionpapa2;
        final String lugartrabajopapa2 = cajatextInputlugartrabajopapa2;
        final String telefonocasapapa2 = cajatextInputtelefonopapacasa2;
        final String telefonocelularpapa2 = cajatextInputtelefonocelular2;
        final String horariotrabajopapa2 = cajatextInputhorariotrabajopapa2;
        final String numeropersonashogar2 = cajatextInputnumeropersonashogar2;
        final String cuantoshermanos2 = cajatextInputcuantoshermanos2;
        final String lugarocupa2 = cajatextInputquelugarocupa2;
        final String vivecon2 = cajatextInputvivecon2;
        final String relacionmama2 = cajatextInputrelacionmama2;
        final String relacionpapa2 = cajatextInputrelacionpapa2;
        final String relacionhermano2 = cajatextInputrelacionhermano2;

        final String comofueembarazo3 = cajatextInputcomofuembarazo3;
        final String comofueparto3 = cajatextInputcomofueparto3;
        final String destete3 = cajatextInputdestete3;
        final String lechematerna3 = cajatextInputlechematerna3;
        final String primeraspalabras3 = cajatextInputprimeraspalabras3;
        final String legustacomer3 = cajatextInputlegustacomer3;
        final String familiarcomejun3 = cajatextInputfamiliacomejunta3;
        final String duermebien3 = cajatextInputduermebien3;
        final String cuandolesalieron3 = cajatextInputcuandolesalieron3;
        final String sanosusdientes3 = cajatextInputsanosusdientes3;
        final String controlesfinteres3 = cajatextInputcontrolesfinteres3;
        final String pronunciabien3 = cajatextInputpronunciabien3;
        final String comprendeloquedice3 = cajatextInputcomprendeloquesedice3;
        final String comosecomporta3 = cajatextInputcomoseporta3;
        final String comosecomportacalle3 = cajatextInputcomosecomportacalle3;
        final String actividadescotidianas3 = cajatextInputColaboraenActividadescotidianas3;
        final String sevistesolo3 = cajatextInputvistesolo3;
        final String legustaquehacer3 = cajatextInputquelegustahacer3;

        final String idgroup = "NA";
        final String calificacion = "NA";


        Map<String, Object> personmap = new HashMap<>();

        personmap.put("institucioneducativaR", institucioneducativaO);
        personmap.put("espacioeducativoOR", espacioeducativoO);
        personmap.put("finalizarprescolarR", finalizarprescolarO);
        personmap.put("escribirsunombreR", escribirsunombreO);
        personmap.put("leerpalabraR", leerpalabraO);
        personmap.put("contarR", contarO);
        personmap.put("paisRegister2R", paisRegister2);
        personmap.put("paisRegister3R", paisRegister3);
        personmap.put("paisRegister4R", paisRegister4);
        personmap.put("gkeR", gkey);

        personmap.put("nombre1R", nombre1);
        personmap.put("apellidos1R", apellidos1);
        personmap.put("lollaman1R", lollaman1);
        personmap.put("lugarnacimiento1R", lugarnacimiento1);
        personmap.put("edad1R", edad1);
        personmap.put("peso1R", peso1);
        personmap.put("estatura1R", estatura1);
        personmap.put("domicilio1R", domicilio1);
        personmap.put("telefono1R", telefono1);
        personmap.put("telefono2R", telefono2);
        personmap.put("almacenasexo1R", almacenasexo1);
        personmap.put("nombremama2R", nombremama2);
        personmap.put("estudioscursamamaR2", estudioscursamama2);
        personmap.put("ocupacionmama2R", ocupacionmama2);
        personmap.put("nacimientomama2R", nacimientomama2);
        personmap.put("telefonomama2R", telefonomama2);
        personmap.put("telefonomovilmama2R", telefonomovilmama2);
        personmap.put("horariomama2R", horariomama2);
        personmap.put("nombrepapa2R", nombrepapa2);
        personmap.put("estudiospapa2R", estudiospapa2);
        personmap.put("ocupacionpapa2R", ocupacionpapa2);
        personmap.put("lugartrabajopapa2R", lugartrabajopapa2);
        personmap.put("telefonocasapapa2R", telefonocasapapa2);
        personmap.put("telefonocelularpapa2R", telefonocelularpapa2);
        personmap.put("horariotrabajopapa2R", horariotrabajopapa2);
        personmap.put("numeropersonashogar2R", numeropersonashogar2);
        personmap.put("cuantoshermanos2R", cuantoshermanos2);
        personmap.put("lugarocupa2R", lugarocupa2);
        personmap.put("vivecon2R", vivecon2);
        personmap.put("relacionmama2R", relacionmama2);
        personmap.put("relacionpapa2R", relacionpapa2);
        personmap.put("relacionhermano2R", relacionhermano2);
        personmap.put("comofueembarazo3R", comofueembarazo3);
        personmap.put("comofueparto3R", comofueparto3);
        personmap.put("destete3R", destete3);
        personmap.put("lechematerna3R", lechematerna3);
        personmap.put("primeraspalabras3R", primeraspalabras3);
        personmap.put("legustacomer3R", legustacomer3);
        personmap.put("familiarcomejun3R", familiarcomejun3);
        personmap.put("duermebien3R", duermebien3);
        personmap.put("cuandolesalieron3R", cuandolesalieron3);
        personmap.put("sanosusdientes3R", sanosusdientes3);
        personmap.put("controlesfinteres3R", controlesfinteres3);
        personmap.put("pronunciabien3R", pronunciabien3);
        personmap.put("comprendeloquedice3R", comprendeloquedice3);
        personmap.put("comosecomporta3R", comosecomporta3);
        personmap.put("comosecomportacalle3R", comosecomportacalle3);
        personmap.put("actividadescotidianas3R", actividadescotidianas3);
        personmap.put("sevistesolo3R", sevistesolo3);
        personmap.put("legustaquehacer3R", legustaquehacer3);
        personmap.put("idgruR", idgroup);
        personmap.put("calificacionR", calificacion);

        mDatabase.child("Users").child("Clients").child(educapyModelUser.getUid()).updateChildren(personmap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //finish();
                Toast.makeText(RegisterClientActivity4.this, "Alumno Actualizado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterClientActivity4.this, principal.class);
                intent.putExtra("nombrealumnoT", cajatextInputName1);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CLIENTE NO REGRESE A REGISTRARSE
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterClientActivity4.this, "Fallo al Actualizar", Toast.LENGTH_SHORT).show();
            }
        });


        //aqui va el fragmento para actualizar por login por google

        /*

        if (!email.isEmpty() && !password.isEmpty() && !institucioneducativaO.isEmpty() && !espacioeducativoO.isEmpty() && !finalizarprescolarO.isEmpty() && !escribirsunombreO.isEmpty()
                && !leerpalabraO.isEmpty() && !contarO.isEmpty() && !paisRegister2.isEmpty() && !paisRegister3.isEmpty()
                && !paisRegister4.isEmpty()) {
            if (password.length() >= 6) {
                mDialog.show();
                register(institucioneducativaO,calificacion, email, password, espacioeducativoO, finalizarprescolarO, escribirsunombreO, leerpalabraO, contarO, paisRegister2, paisRegister3, paisRegister4, gkey, nombre1, apellidos1, lollaman1, lugarnacimiento1, edad1, peso1, estatura1, domicilio1, telefono1, telefono2,
                        almacenasexo1, nombremama2, estudioscursamama2, ocupacionmama2, nacimientomama2, telefonomama2, telefonomovilmama2, horariomama2, nombrepapa2, estudiospapa2, ocupacionpapa2, lugartrabajopapa2, telefonocasapapa2, telefonocelularpapa2, horariotrabajopapa2, numeropersonashogar2, cuantoshermanos2, lugarocupa2,
                        vivecon2, relacionmama2, relacionpapa2, relacionhermano2, comofueembarazo3, comofueparto3, destete3, lechematerna3, primeraspalabras3, legustacomer3, familiarcomejun3, duermebien3, cuandolesalieron3, sanosusdientes3, controlesfinteres3, pronunciabien3, comprendeloquedice3, comosecomporta3,
                        comosecomportacalle3, actividadescotidianas3, sevistesolo3, legustaquehacer3, idgroup);
            } else {
                Toast.makeText(this, "El Password Debe Ser Mayor a 5 Caracteres!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Debe llenar Todos los Campos y subir foto", Toast.LENGTH_SHORT).show();
        }

         */
    }

    /*
    void register(final String institucioneducativaR,final String calificacionR, final String emailR, final String passwordR, final String espacioeducativoR, final String finalizarprescolarR, final String escribirsunombreR, final String leerpalabraR,
                  final String contarR, final String paisRegister2R, final String paisRegister3R, final String paisRegister4R, final String gkeyR, final String nombre1R, final String apellidos1R, final String lollaman1R, final String lugarnacimiento1R, final String edad1R, final String peso1R, final String estatura1R, final String domicilio1R, final String telefono1R, final String telefono2R, final String almacenasexo1R,
                  final String nombremama2R, final String estudioscursamama2R, final String ocupacionmama2R, final String nacimientomama2R, final String telefonomama2R, final String telefonomovilmama2R, final String horariomama2R, final String nombrepapa2R, final String estudiospapa2R, final String ocupacionpapa2R, final String lugartrabajopapa2R, final String telefonocasapapa2R, final String telefonocelularpapa2R, final String horariotrabajopapa2R, final String numeropersonashogar2R,
                  final String cuantoshermanos2R, final String lugarocupa2R, final String vivecon2R, final String relacionmama2R, final String relacionpapa2R, final String relacionhermano2R, final String comofueembarazo3, final String comofueparto3, final String destete3, final String lechematerna3, final String primeraspalabras3, final String legustacomer3, final String familiarcomejun3, final String duermebien3, final String cuandolesalieron3,
                  final String sanosusdientes3, final String controlesfinteres3, final String pronunciabien3, final String comprendeloquedice3,
                  final String comosecomporta3, final String comosecomportacalle3, final String actividadescotidianas3, final String sevistesolo3, final String legustaquehacer3,
                  final String idgroupR) {
        mAuthProvider.register(emailR, passwordR).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mDialog.dismiss();
                if (task.isSuccessful()) {
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    ClientM client = new ClientM(calificacionR,id, emailR, gkeyR, institucioneducativaR, espacioeducativoR, finalizarprescolarR, escribirsunombreR, leerpalabraR, contarR,
                            paisRegister2R, paisRegister3R, paisRegister4R, nombre1R, apellidos1R, lollaman1R, lugarnacimiento1R, edad1R, peso1R, estatura1R, domicilio1R,
                            telefono1R, telefono2R, almacenasexo1R, nombremama2R, estudioscursamama2R, ocupacionmama2R, nacimientomama2R, telefonomama2R, telefonomovilmama2R,
                            horariomama2R, nombrepapa2R, estudiospapa2R, ocupacionpapa2R, lugartrabajopapa2R,telefonocasapapa2R, telefonocelularpapa2R,
                            lugartrabajopapa2R, horariotrabajopapa2R, numeropersonashogar2R, cuantoshermanos2R, lugarocupa2R, vivecon2R,
                            relacionmama2R, relacionpapa2R, relacionhermano2R, comofueembarazo3, comofueparto3, destete3, lechematerna3,
                            primeraspalabras3, legustacomer3, familiarcomejun3, duermebien3, cuandolesalieron3, sanosusdientes3, controlesfinteres3,
                            pronunciabien3, comprendeloquedice3, comosecomporta3, comosecomportacalle3, actividadescotidianas3, sevistesolo3,
                            legustaquehacer3, idgroupR); //debe tener la misma posicion del constructor
                    create(client);
                } else {
                    Toast.makeText(RegisterClientActivity4.this, "No Se Pudo Registrar al Cliente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void create(ClientM client) {
        mClientProvider.create(client).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterClientActivity4.this, "Se Registro Exitosamente al Estudiante", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterClientActivity4.this, principal.class);
                    intent.putExtra("nombrealumnoT",cajatextInputName1);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CLIENTE NO REGRESE A REGISTRARSE
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterClientActivity4.this, "No Se Pudo Crear el Cliente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


     */

    private void validacion4() {
        /*1*/
        //String email = cajacorreo.getText().toString();
        String nombremama2t = cajatextInputinstitucioneducativa4.getText().toString(); //utilizo esta forma para obtener el valor de los campos y validar los campos vacios  atravez de la clase validacion
        String estudioscursa2t = cajatextInputespacioeducativo4.getText().toString();
        //String ocupacionmama2T = cajatextInputEsperafinalizarpreescolar4.getText().toString();
        //String lugarnacimiento2T = cajatextInputEscribirsunombre4.getText().toString();
        //String telefonomama2T = cajatextInputleerpalabras4.getText().toString();
        //String telefonomovil2T = cajatextInputcontar4.getText().toString();
        String horariomama2T = cajatextInputseleccionintituto4.getText().toString();
        String nombrepapa2T = cajatextInputesperasinstitucion4.getText().toString();
        String estudiospapa2T = cajatextInputenquepuedelaborar4.getText().toString();

//        if (email.equals("")) {
//            cajacorreo.setError("Requerido");
//        } else
        if (nombremama2t.equals("")) {
            cajatextInputinstitucioneducativa4.setError("Requerido");
        } else if (estudioscursa2t.equals("")) {
            cajatextInputespacioeducativo4.setError("Requerido");
        } else if (reconoceLetras.equals("")) {
            //cajatextInputEsperafinalizarpreescolar4.setError("Requerido");
        } else if (escribeNombre.equals("")) {
            //cajatextInputEscribirsunombre4.setError("Requerido");
        } else if (leePalabras.equals("")) {
            //cajatextInputleerpalabras4.setError("Requerido");
        } else if (cuentaPalabra.equals("")) {
            //cajatextInputcontar4.setError("Requerido");
        } else if (horariomama2T.equals("")) {
            cajatextInputseleccionintituto4.setError("Requerido");
        } else if (nombrepapa2T.equals("")) {
            cajatextInputesperasinstitucion4.setError("Requerido");
        } else if (estudiospapa2T.equals("")) {
            cajatextInputenquepuedelaborar4.setError("Requerido");
        }
    }


}
