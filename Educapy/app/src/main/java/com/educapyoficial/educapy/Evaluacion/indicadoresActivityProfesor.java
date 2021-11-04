package com.educapyoficial.educapy.Evaluacion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.educapyoficial.educapy.AltabajaCursos;
import com.educapyoficial.educapy.R;
import com.educapyoficial.educapy.adapters.AdapterAnecdotario;
import com.educapyoficial.educapy.adapters.ListOpcionesAdapter;
import com.educapyoficial.educapy.models.EducapyModelUser;
import com.educapyoficial.educapy.models.EvaluacionIndicadores;
import com.educapyoficial.educapy.models.Indicador;
import com.educapyoficial.educapy.models.Opcion;
import com.educapyoficial.educapy.models.RegistroAnecdotario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import de.hdodenhof.circleimageview.CircleImageView;

public class indicadoresActivityProfesor extends AppCompatActivity {

    ListOpcionesAdapter adapterIndicadorOpcion1;
    private ListOpcionesAdapter adapterIndicadorOpcion2;
    private ListOpcionesAdapter adapterIndicadorOpcion3;
    private ListOpcionesAdapter adapterIndicadorOpcion4;
    private ListOpcionesAdapter adapterIndicadorOpcion5;
    private ListOpcionesAdapter adapterIndicadorOpcion6;
    private ListOpcionesAdapter adapterIndicadorOpcion7;
    private ListOpcionesAdapter adapterIndicadorOpcion8;
    private ListOpcionesAdapter adapterIndicadorOpcion9;
    private ListOpcionesAdapter adapterIndicadorOpcion10;
    private ListOpcionesAdapter adapterIndicadorOpcion11;
    private ListOpcionesAdapter adapterIndicadorOpcion12;
    private ListOpcionesAdapter adapterIndicadorOpcion13;
    private ListOpcionesAdapter adapterIndicadorOpcion14;


    ArrayAdapter<String> mAdapter, mAdapter2, mAdapter3, mAdapter4, mAdapter5, mAdapter6, mAdapter7, mAdapter8, mAdapter9, mAdapter10, mAdapter11, mAdapter12, mAdapter13, mAdapter14;
    Spinner spinneridentidad1, spinneridentidad2, spinneridentidad3, spinnerAutonomia1, spinnerAutonomia2, spinnerAutonomia3, spinnerAutonomia4, spinnerConvivencia1, spinnerConvivencia2, spinnerConvivencia3,
            spinnerConvivencia4, spinnervidaSaludable1, spinnervidaSaludable2, spinnervidaSaludable3;

    CheckBox cajaIndicador1, cajaIndicador2, cajaIndicador3, cajaIndicador4, cajaIndicador5, cajaIndicador6, cajaIndicador7, cajaIndicador8, cajaIndicador9, cajaIndicador10,
            cajaIndicador11, cajaIndicador12, cajaIndicador13, cajaIndicador14, cajaIndicador15, cajaIndicador16, cajaIndicador17, cajaIndicador18, cajaIndicador19, cajaIndicador20,
            cajaIndicador21, cajaIndicador22, cajaIndicador23, cajaIndicador24, cajaIndicador25, cajaIndicador26, cajaIndicador27, cajaIndicador28;

    String almacenaIndicador1, almacenaIndicador2, almacenaIndicador3, almacenaIndicador4, almacenaIndicador5, almacenaIndicador6, almacenaIndicador7, almacenaIndicador8, almacenaIndicador9, almacenaIndicador10,
            almacenaIndicador11, almacenaIndicador12, almacenaIndicador13, almacenaIndicador14;

    private CircleImageView mCircleImageNext;

    String obtengogkeR, obtengoNombre;

    TextView txtnombre;
    private DatabaseReference mDatabase;

    EvaluacionIndicadores evaluacionIndicadores;
    EducapyModelUser educapyModelUser;

    private Indicador indicador1 = new Indicador("", "");
    private Indicador indicador2 = new Indicador("", "");
    private Indicador indicador3 = new Indicador("", "");
    private Indicador indicador4 = new Indicador("", "");
    private Indicador indicador5 = new Indicador("", "");
    private Indicador indicador6 = new Indicador("", "");
    private Indicador indicador7 = new Indicador("", "");
    private Indicador indicador8 = new Indicador("", "");
    private Indicador indicador9 = new Indicador("", "");
    private Indicador indicador10 = new Indicador("", "");
    private Indicador indicador11 = new Indicador("", "");
    private Indicador indicador12 = new Indicador("", "");
    private Indicador indicador13 = new Indicador("", "");
    private Indicador indicador14 = new Indicador("", "");

    List<Indicador> indicadorOpcionesList = new ArrayList<>();
    List<EvaluacionIndicadores> evaluacionIndicadoresList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicadores);

        evaluacionIndicadores = new EvaluacionIndicadores();
        txtnombre = findViewById(R.id.txtNombre);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mCircleImageNext = findViewById(R.id.circleImageNextComRT);
        spinneridentidad1 = (Spinner) findViewById(R.id.spinnerPesoT);
        spinneridentidad2 = (Spinner) findViewById(R.id.spinnerPesoT2);
        spinneridentidad3 = (Spinner) findViewById(R.id.spinnerPesoT3);
        spinnerAutonomia1 = (Spinner) findViewById(R.id.spinnerPesoT4);
        spinnerAutonomia2 = (Spinner) findViewById(R.id.spinnerPesoT5);
        spinnerAutonomia3 = (Spinner) findViewById(R.id.spinnerPesoT6);
        spinnerAutonomia4 = (Spinner) findViewById(R.id.spinnerPesoT7);
        spinnerConvivencia1 = (Spinner) findViewById(R.id.spinnerPesoT8);
        spinnerConvivencia2 = (Spinner) findViewById(R.id.spinnerPesoT9);
        spinnerConvivencia3 = (Spinner) findViewById(R.id.spinnerPesoT10);
        spinnerConvivencia4 = (Spinner) findViewById(R.id.spinnerPesoT11);
        spinnervidaSaludable1 = (Spinner) findViewById(R.id.spinnerPesoT12);
        spinnervidaSaludable2 = (Spinner) findViewById(R.id.spinnerPesoT13);
        spinnervidaSaludable3 = (Spinner) findViewById(R.id.spinnerPesoT14);

        Intent i = getIntent();
        obtengogkeR = i.getStringExtra("mandogkeR");
        obtengoNombre = i.getStringExtra("mandoNombre");
        educapyModelUser = (EducapyModelUser) i.getSerializableExtra("educapyModelUser");
        txtnombre.setText(obtengoNombre);
        cajaIndicador1 = (CheckBox) findViewById(R.id.checkBoxSi);
        cajaIndicador2 = (CheckBox) findViewById(R.id.checkBoxNo);
        cajaIndicador3 = (CheckBox) findViewById(R.id.checkBoxSi2);
        cajaIndicador4 = (CheckBox) findViewById(R.id.checkBoxNo2);
        cajaIndicador5 = (CheckBox) findViewById(R.id.checkBoxSi3);
        cajaIndicador6 = (CheckBox) findViewById(R.id.checkBoxNo3);
        cajaIndicador7 = (CheckBox) findViewById(R.id.checkBoxSi4);
        cajaIndicador8 = (CheckBox) findViewById(R.id.checkBoxNo4);
        cajaIndicador9 = (CheckBox) findViewById(R.id.checkBoxSi5);
        cajaIndicador10 = (CheckBox) findViewById(R.id.checkBoxNo5);
        cajaIndicador11 = (CheckBox) findViewById(R.id.checkBoxSi6);
        cajaIndicador12 = (CheckBox) findViewById(R.id.checkBoxNo6);
        cajaIndicador13 = (CheckBox) findViewById(R.id.checkBoxSi7);
        cajaIndicador14 = (CheckBox) findViewById(R.id.checkBoxNo7);
        cajaIndicador15 = (CheckBox) findViewById(R.id.checkBoxSi8);
        cajaIndicador16 = (CheckBox) findViewById(R.id.checkBoxNo8);
        cajaIndicador17 = (CheckBox) findViewById(R.id.checkBoxSi9);
        cajaIndicador18 = (CheckBox) findViewById(R.id.checkBoxNo9);
        cajaIndicador19 = (CheckBox) findViewById(R.id.checkBoxSi10);
        cajaIndicador20 = (CheckBox) findViewById(R.id.checkBoxNo10);
        cajaIndicador21 = (CheckBox) findViewById(R.id.checkBoxSi11);
        cajaIndicador22 = (CheckBox) findViewById(R.id.checkBoxNo11);
        cajaIndicador23 = (CheckBox) findViewById(R.id.checkBoxSi12);
        cajaIndicador24 = (CheckBox) findViewById(R.id.checkBoxNo12);
        cajaIndicador25 = (CheckBox) findViewById(R.id.checkBoxSi13);
        cajaIndicador26 = (CheckBox) findViewById(R.id.checkBoxNo13);
        cajaIndicador27 = (CheckBox) findViewById(R.id.checkBoxSi14);
        cajaIndicador28 = (CheckBox) findViewById(R.id.checkBoxNo14);

        final LinearLayout contenedor1RT = (LinearLayout) findViewById(R.id.contenedor1);
        final LinearLayout contenedor2RT = (LinearLayout) findViewById(R.id.contenedor2);
        final LinearLayout contenedor3RT = (LinearLayout) findViewById(R.id.contenedor3);
        final LinearLayout contenedor4RT = (LinearLayout) findViewById(R.id.contenedor4);
        final LinearLayout contenedor5RT = (LinearLayout) findViewById(R.id.contenedor5);
        final LinearLayout contenedor6RT = (LinearLayout) findViewById(R.id.contenedor6);
        final LinearLayout contenedor7RT = (LinearLayout) findViewById(R.id.contenedor7);
        final LinearLayout contenedor8RT = (LinearLayout) findViewById(R.id.contenedor8);
        final LinearLayout contenedor9RT = (LinearLayout) findViewById(R.id.contenedor9);
        final LinearLayout contenedor10RT = (LinearLayout) findViewById(R.id.contenedor10);
        final LinearLayout contenedor11RT = (LinearLayout) findViewById(R.id.contenedor11);
        final LinearLayout contenedor12RT = (LinearLayout) findViewById(R.id.contenedor12);
        final LinearLayout contenedor13RT = (LinearLayout) findViewById(R.id.contenedor13);
        final LinearLayout contenedor14RT = (LinearLayout) findViewById(R.id.contenedor14);

        mCircleImageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validacion();
            }
        });

        cajaIndicador1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador2.setChecked(false);
                    //  Toast.makeText(PrimerVentana.this, "Caja 2 vacia", Toast.LENGTH_SHORT).show();
                    almacenaIndicador1 = "en proceso";
                    indicador1.setValor(almacenaIndicador1);
                    contenedor1RT.setVisibility(View.VISIBLE);

                }
            }
        });
        cajaIndicador2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador1.setChecked(false);
                    almacenaIndicador1 = "logrado";
                    contenedor1RT.setVisibility(View.GONE);
                    indicador1.setValor(almacenaIndicador1);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cajaIndicador3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador4.setChecked(false);
                    almacenaIndicador2 = "en proceso";
                    contenedor2RT.setVisibility(View.VISIBLE);
                    indicador2.setValor(almacenaIndicador2);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cajaIndicador4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador3.setChecked(false);
                    almacenaIndicador2 = "logrado";
                    contenedor2RT.setVisibility(View.GONE);
                    indicador2.setValor(almacenaIndicador2);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cajaIndicador5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador6.setChecked(false);
                    almacenaIndicador3 = "en proceso";
                    contenedor3RT.setVisibility(View.VISIBLE);
                    indicador3.setValor(almacenaIndicador3);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cajaIndicador6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador5.setChecked(false);
                    almacenaIndicador3 = "logrado";
                    contenedor3RT.setVisibility(View.GONE);
                    indicador3.setValor(almacenaIndicador3);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cajaIndicador7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador8.setChecked(false);
                    almacenaIndicador4 = "en proceso";
                    contenedor4RT.setVisibility(View.VISIBLE);
                    indicador4.setValor(almacenaIndicador4);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cajaIndicador8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador7.setChecked(false);
                    almacenaIndicador4 = "logrado";
                    contenedor4RT.setVisibility(View.GONE);
                    indicador4.setValor(almacenaIndicador4);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cajaIndicador9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador10.setChecked(false);
                    almacenaIndicador5 = "en proceso";
                    contenedor5RT.setVisibility(View.VISIBLE);
                    indicador5.setValor(almacenaIndicador5);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cajaIndicador10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador9.setChecked(false);
                    almacenaIndicador5 = "logrado";
                    contenedor5RT.setVisibility(View.GONE);
                    indicador5.setValor(almacenaIndicador5);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cajaIndicador11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador12.setChecked(false);
                    almacenaIndicador6 = "en proceso";
                    contenedor6RT.setVisibility(View.VISIBLE);
                    indicador6.setValor(almacenaIndicador6);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cajaIndicador12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador11.setChecked(false);
                    almacenaIndicador6 = "logrado";
                    contenedor6RT.setVisibility(View.GONE);
                    indicador6.setValor(almacenaIndicador6);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cajaIndicador13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador14.setChecked(false);
                    almacenaIndicador7 = "en proceso";
                    contenedor7RT.setVisibility(View.VISIBLE);
                    indicador7.setValor(almacenaIndicador7);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cajaIndicador14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador13.setChecked(false);
                    almacenaIndicador7 = "logrado";
                    contenedor7RT.setVisibility(View.GONE);
                    indicador7.setValor(almacenaIndicador7);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cajaIndicador15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador16.setChecked(false);
                    almacenaIndicador8 = "en proceso";
                    contenedor8RT.setVisibility(View.VISIBLE);
                    indicador8.setValor(almacenaIndicador8);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cajaIndicador16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador15.setChecked(false);
                    almacenaIndicador8 = "logrado";
                    contenedor8RT.setVisibility(View.GONE);
                    indicador8.setValor(almacenaIndicador8);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cajaIndicador17.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador18.setChecked(false);
                    almacenaIndicador9 = "en proceso";
                    contenedor9RT.setVisibility(View.VISIBLE);
                    indicador9.setValor(almacenaIndicador9);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cajaIndicador18.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador17.setChecked(false);
                    almacenaIndicador9 = "logrado";
                    contenedor9RT.setVisibility(View.GONE);
                    indicador9.setValor(almacenaIndicador9);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cajaIndicador19.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador20.setChecked(false);
                    almacenaIndicador10 = "en proceso";
                    contenedor10RT.setVisibility(View.VISIBLE);
                    indicador10.setValor(almacenaIndicador10);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cajaIndicador20.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador19.setChecked(false);
                    almacenaIndicador10 = "logrado";
                    contenedor10RT.setVisibility(View.GONE);
                    indicador10.setValor(almacenaIndicador10);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cajaIndicador21.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador22.setChecked(false);
                    almacenaIndicador11 = "en proceso";
                    contenedor11RT.setVisibility(View.VISIBLE);
                    indicador11.setValor(almacenaIndicador11);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cajaIndicador22.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador21.setChecked(false);
                    almacenaIndicador11 = "logrado";
                    contenedor11RT.setVisibility(View.GONE);
                    indicador11.setValor(almacenaIndicador11);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cajaIndicador23.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador24.setChecked(false);
                    almacenaIndicador12 = "en proceso";
                    contenedor12RT.setVisibility(View.VISIBLE);
                    indicador12.setValor(almacenaIndicador12);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cajaIndicador24.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador23.setChecked(false);
                    almacenaIndicador12 = "logrado";
                    contenedor12RT.setVisibility(View.GONE);
                    indicador12.setValor(almacenaIndicador12);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cajaIndicador25.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador26.setChecked(false);
                    almacenaIndicador13 = "en proceso";
                    contenedor13RT.setVisibility(View.VISIBLE);
                    indicador13.setValor(almacenaIndicador13);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cajaIndicador26.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador25.setChecked(false);
                    almacenaIndicador13 = "logrado";
                    contenedor13RT.setVisibility(View.GONE);
                    indicador13.setValor(almacenaIndicador13);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cajaIndicador27.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador28.setChecked(false);
                    almacenaIndicador14 = "en proceso";
                    contenedor14RT.setVisibility(View.VISIBLE);
                    indicador14.setValor(almacenaIndicador14);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cajaIndicador28.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cajaIndicador27.setChecked(false);
                    almacenaIndicador14 = "logrado";
                    contenedor14RT.setVisibility(View.GONE);
                    indicador14.setValor(almacenaIndicador14);
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //mAdapter = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.indica1Q));
        //spinneridentidad1.setAdapter(mAdapter);

//        mAdapter2 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.indica2Q));
//        spinneridentidad2.setAdapter(mAdapter2);

//        mAdapter3 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.indica3Q));
//        spinneridentidad3.setAdapter(mAdapter3);
//
//        mAdapter4 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.autonomia1Q));
//        spinnerAutonomia1.setAdapter(mAdapter4);
//
//        mAdapter5 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.autonomia2Q));
//        spinnerAutonomia2.setAdapter(mAdapter5);
//
//        mAdapter6 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.autonomia3Q));
//        spinnerAutonomia3.setAdapter(mAdapter6);
//
//        mAdapter7 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.autonomia4Q));
//        spinnerAutonomia4.setAdapter(mAdapter7);
//
//        mAdapter8 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.convivencia1Q));
//        spinnerConvivencia1.setAdapter(mAdapter8);
//
//        mAdapter9 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.convivencia2Q));
//        spinnerConvivencia2.setAdapter(mAdapter9);
//
//        mAdapter10 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.convivencia3Q));
//        spinnerConvivencia3.setAdapter(mAdapter10);
//
//        mAdapter11 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.convivencia4Q));
//        spinnerConvivencia4.setAdapter(mAdapter11);
//
//        mAdapter12 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.vidaSaludable1Q));
//        spinnervidaSaludable1.setAdapter(mAdapter12);
//
//        mAdapter13 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.vidaSaludable2Q));
//        spinnervidaSaludable2.setAdapter(mAdapter13);
//
//        mAdapter14 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.vidaSaludable3Q));
//        spinnervidaSaludable3.setAdapter(mAdapter14);


        spinneridentidad1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) spinneridentidad1.getSelectedView()).setTextColor(Color.BLACK);
//                String seleccion = spinneridentidad1.getSelectedItem().toString();
//                String resultado = seleccion.replaceAll("\\D+", "");
//                if (resultado != null && !resultado.equals("")) {
//                    evaluacionIndicadores.setAlmacenaIndicador1(resultado);
//                } else {
//                    evaluacionIndicadores.setAlmacenaIndicador1("");
//                }

                indicador1.getOpcionList().clear();
                indicador1.getOpcionList().add(0, (Opcion) spinneridentidad1.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinneridentidad2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ((TextView) spinneridentidad2.getSelectedView()).setTextColor(Color.BLACK);
//                String seleccion = spinneridentidad2.getSelectedItem().toString();
//                String resultado = seleccion.replaceAll("\\D+", "");
//                if (resultado != null && !resultado.equals("")) {
//                    evaluacionIndicadores.setAlmacenaIndicador2(resultado);
//                } else {
//                    evaluacionIndicadores.setAlmacenaIndicador2("");
//                }
                indicador2.getOpcionList().clear();
                indicador2.getOpcionList().add(0, (Opcion) spinneridentidad2.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinneridentidad3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ((TextView) spinneridentidad3.getSelectedView()).setTextColor(Color.BLACK);
//                String seleccion = spinneridentidad3.getSelectedItem().toString();
//                String resultado = seleccion.replaceAll("\\D+", "");
//                if (resultado != null && !resultado.equals("")) {
//                    evaluacionIndicadores.setAlmacenaIndicador3(resultado);
//                } else {
//                    evaluacionIndicadores.setAlmacenaIndicador3("");
                indicador3.getOpcionList().clear();
                indicador3.getOpcionList().add(0, (Opcion) spinneridentidad3.getSelectedItem());

                Toast.makeText(indicadoresActivityProfesor.this, "Seleccionado " + indicador3.getOpcionList().get(0).getValor(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerAutonomia1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ((TextView) spinnerAutonomia1.getSelectedView()).setTextColor(Color.BLACK);
//                String seleccion = spinnerAutonomia1.getSelectedItem().toString();
//                String resultado = seleccion.replaceAll("\\D+", "");
//                if (resultado != null && !resultado.equals("")) {
//                    evaluacionIndicadores.setAlmacenaIndicador4(resultado);
//                } else {
//                    evaluacionIndicadores.setAlmacenaIndicador4("");
//                }
//                indicador4 = (Indicador) spinnerAutonomia1.getSelectedItem();

                indicador4.getOpcionList().clear();
                indicador4.getOpcionList().add(0, (Opcion) spinnerAutonomia1.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerAutonomia2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ((TextView) spinnerAutonomia2.getSelectedView()).setTextColor(Color.BLACK);
//                String seleccion = spinnerAutonomia2.getSelectedItem().toString();
//                String resultado = seleccion.replaceAll("\\D+", "");
//                if (resultado != null && !resultado.equals("")) {
//                    evaluacionIndicadores.setAlmacenaIndicador5(resultado);
//                } else {
//                    evaluacionIndicadores.setAlmacenaIndicador5("");
//                }

                // indicador5 = (Indicador) spinnerAutonomia2.getSelectedItem();

                indicador5.getOpcionList().clear();
                indicador5.getOpcionList().add(0, (Opcion) spinnerAutonomia2.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerAutonomia3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) spinnerAutonomia3.getSelectedView()).setTextColor(Color.BLACK);
//                String seleccion = spinnerAutonomia3.getSelectedItem().toString();
//                String resultado = seleccion.replaceAll("\\D+", "");
//                if (resultado != null && !resultado.equals("")) {
//                    evaluacionIndicadores.setAlmacenaIndicador6(resultado);
//                } else {
//                    evaluacionIndicadores.setAlmacenaIndicador6("");
//                }
                //indicador6 = (Indicador) spinnerAutonomia3.getSelectedItem();

                indicador6.getOpcionList().clear();
                indicador6.getOpcionList().add(0, (Opcion) spinnerAutonomia3.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerAutonomia4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) spinnerAutonomia4.getSelectedView()).setTextColor(Color.BLACK);
//                String seleccion = spinnerAutonomia4.getSelectedItem().toString();
//                String resultado = seleccion.replaceAll("\\D+", "");
//                if (resultado != null && !resultado.equals("")) {
//                    evaluacionIndicadores.setAlmacenaIndicador7(resultado);
//                } else {
//                    evaluacionIndicadores.setAlmacenaIndicador7("");
//                }

//                indicador7 = (Indicador) spinnerAutonomia4.getSelectedItem();

                indicador7.getOpcionList().clear();
                indicador7.getOpcionList().add(0, (Opcion) spinnerAutonomia4.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerConvivencia1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) spinnerConvivencia1.getSelectedView()).setTextColor(Color.BLACK);
//                String seleccion = spinnerConvivencia1.getSelectedItem().toString();
//                String resultado = seleccion.replaceAll("\\D+", "");
//                if (resultado != null && !resultado.equals("")) {
//                    evaluacionIndicadores.setAlmacenaIndicador8(resultado);
//                } else {
//                    evaluacionIndicadores.setAlmacenaIndicador8("");
//                }

                //indicador8 = (Indicador) spinnerConvivencia1.getSelectedItem();

                indicador8.getOpcionList().clear();
                indicador8.getOpcionList().add(0, (Opcion) spinnerConvivencia1.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerConvivencia2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) spinnerConvivencia2.getSelectedView()).setTextColor(Color.BLACK);
//                String seleccion = spinnerConvivencia2.getSelectedItem().toString();
//                String resultado = seleccion.replaceAll("\\D+", "");
//                if (resultado != null && !resultado.equals("")) {
//                    evaluacionIndicadores.setAlmacenaIndicador9(resultado);
//                } else {
//                    evaluacionIndicadores.setAlmacenaIndicador9("");
//                }

                //   indicador9 = (Indicador) spinnerConvivencia2.getSelectedItem();

                indicador9.getOpcionList().clear();
                indicador9.getOpcionList().add(0, (Opcion) spinnerConvivencia2.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerConvivencia3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) spinnerConvivencia3.getSelectedView()).setTextColor(Color.BLACK);
//                String seleccion = spinnerConvivencia3.getSelectedItem().toString();
//                String resultado = seleccion.replaceAll("\\D+", "");
//                if (resultado != null && !resultado.equals("")) {
//                    evaluacionIndicadores.setAlmacenaIndicador10(resultado);
//                } else {
//                    evaluacionIndicadores.setAlmacenaIndicador10("");
//                }

                // indicador10 = (Indicador) spinnerConvivencia3.getSelectedItem();
                indicador10.getOpcionList().clear();
                indicador10.getOpcionList().add(0, (Opcion) spinnerConvivencia3.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerConvivencia4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) spinnerConvivencia4.getSelectedView()).setTextColor(Color.BLACK);
//                String seleccion = spinnerConvivencia4.getSelectedItem().toString();
//                String resultado = seleccion.replaceAll("\\D+", "");
//                if (resultado != null && !resultado.equals("")) {
//                    evaluacionIndicadores.setAlmacenaIndicador11(resultado);
//                } else {
//                    evaluacionIndicadores.setAlmacenaIndicador11("");
//                }

                //  indicador11 = (Indicador) spinnerConvivencia4.getSelectedItem();

                indicador11.getOpcionList().clear();
                indicador11.getOpcionList().add(0, (Opcion) spinnerConvivencia4.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnervidaSaludable1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) spinnervidaSaludable1.getSelectedView()).setTextColor(Color.BLACK);
//                String seleccion = spinnervidaSaludable1.getSelectedItem().toString();
//                String resultado = seleccion.replaceAll("\\D+", "");
//                if (resultado != null && !resultado.equals("")) {
//                    evaluacionIndicadores.setAlmacenaIndicador12(resultado);
//                } else {
//                    evaluacionIndicadores.setAlmacenaIndicador12("");
//                }

                // indicador12 = (Indicador) spinnervidaSaludable1.getSelectedItem();

                indicador12.getOpcionList().clear();
                indicador12.getOpcionList().add(0, (Opcion) spinnervidaSaludable1.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnervidaSaludable2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) spinnervidaSaludable2.getSelectedView()).setTextColor(Color.BLACK);
//                String seleccion = spinnervidaSaludable2.getSelectedItem().toString();
//                String resultado = seleccion.replaceAll("\\D+", "");
//                if (resultado != null && !resultado.equals("")) {
//                    evaluacionIndicadores.setAlmacenaIndicador13(resultado);
//                } else {
//                    evaluacionIndicadores.setAlmacenaIndicador13("");
//                }

                // indicador13 = (Indicador) spinnervidaSaludable2.getSelectedItem();

                indicador13.getOpcionList().clear();
                indicador13.getOpcionList().add(0, (Opcion) spinnervidaSaludable2.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnervidaSaludable3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) spinnervidaSaludable3.getSelectedView()).setTextColor(Color.BLACK);
//                String seleccion = spinnervidaSaludable3.getSelectedItem().toString();
//                String resultado = seleccion.replaceAll("\\D+", "");
//                if (resultado != null && !resultado.equals("")) {
//                    evaluacionIndicadores.setAlmacenaIndicador14(resultado);
//                } else {
//                    evaluacionIndicadores.setAlmacenaIndicador14("");
//                }
                // indicador14 = (Indicador) spinnervidaSaludable3.getSelectedItem();
                indicador14.getOpcionList().clear();
                indicador14.getOpcionList().add(0, (Opcion) spinnervidaSaludable3.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        listarOpcionesDatos();
        //listarDatos();

    }

    public void listarDatos() {
        mDatabase.child("EvaluacionIndicadores").child("id").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                evaluacionIndicadoresList.clear();
                try {
                    for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                        EvaluacionIndicadores p = objSnaptshot.getValue(EvaluacionIndicadores.class);
                        evaluacionIndicadoresList.add(p);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                acomodarListas();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    Map<Opcion, Integer> lista1 = new HashMap<>();
    Map<Opcion, Integer> lista2 = new HashMap<>();
    Map<Opcion, Integer> lista3 = new HashMap<>();
    Map<Opcion, Integer> lista4 = new HashMap<>();
    Map<Opcion, Integer> lista5 = new HashMap<>();
    Map<Opcion, Integer> lista6 = new HashMap<>();
    Map<Opcion, Integer> lista7 = new HashMap<>();
    Map<Opcion, Integer> lista8 = new HashMap<>();
    Map<Opcion, Integer> lista9 = new HashMap<>();
    Map<Opcion, Integer> lista10 = new HashMap<>();
    Map<Opcion, Integer> lista11 = new HashMap<>();
    Map<Opcion, Integer> lista12 = new HashMap<>();
    Map<Opcion, Integer> lista13 = new HashMap<>();
    Map<Opcion, Integer> lista14 = new HashMap<>();

    public void acomodarListas() {
        for (EvaluacionIndicadores o : evaluacionIndicadoresList) {
            if (o.getIndicador1() != null) {
                if (o.getIndicador1().getValor().equals("en proceso")) {
                    for (Opcion u : o.getIndicador1().getOpcionList()) {
                        if (lista1.isEmpty()) {
                            lista1.put(u, 1);
                        } else {
                            if (lista1.get(u) != null) {
                                lista1.put(u, (lista1.get(u) + 1));
                            } else {
                                lista1.put(u, 1);
                            }
                        }
                    }
                }
            }
            if (o.getIndicador2() != null) {
                if (o.getIndicador2().getValor().equals("en proceso")) {
                    for (Opcion u : o.getIndicador2().getOpcionList()) {
                        if (lista2.isEmpty()) {
                            lista2.put(u, 1);
                        } else {
                            if (lista2.get(u) != null) {
                                lista2.put(u, (lista2.get(u) + 1));
                            } else {
                                lista2.put(u, 1);
                            }
                        }
                    }
                }
            }
            if (o.getIndicador3() != null) {
                if (o.getIndicador3().getValor().equals("en proceso")) {
                    for (Opcion u : o.getIndicador3().getOpcionList()) {
                        if (lista3.isEmpty()) {
                            lista3.put(u, 1);
                        } else {
                            if (lista3.get(u) != null) {
                                lista3.put(u, (lista3.get(u) + 1));
                            } else {
                                lista3.put(u, 1);
                            }
                        }
                    }
                }
            }
            if (o.getIndicador4() != null) {
                if (o.getIndicador4().getValor().equals("en proceso")) {
                    for (Opcion u : o.getIndicador4().getOpcionList()) {
                        if (lista4.isEmpty()) {
                            lista4.put(u, 1);
                        } else {
                            if (lista4.get(u) != null) {
                                lista4.put(u, (lista4.get(u) + 1));
                            } else {
                                lista4.put(u, 1);
                            }
                        }
                    }
                }
            }
            if (o.getIndicador5() != null) {
                if (o.getIndicador5().getValor().equals("en proceso")) {
                    for (Opcion u : o.getIndicador5().getOpcionList()) {
                        if (lista5.isEmpty()) {
                            lista5.put(u, 1);
                        } else {
                            if (lista5.get(u) != null) {
                                lista5.put(u, (lista5.get(u) + 1));
                            } else {
                                lista5.put(u, 1);
                            }
                        }
                    }
                }
            }
            if (o.getIndicador6() != null) {
                if (o.getIndicador6().getValor().equals("en proceso")) {
                    for (Opcion u : o.getIndicador6().getOpcionList()) {
                        if (lista6.isEmpty()) {
                            lista6.put(u, 1);
                        } else {
                            if (lista6.get(u) != null) {
                                lista6.put(u, (lista6.get(u) + 1));
                            } else {
                                lista6.put(u, 1);
                            }
                        }
                    }
                }
            }
            if (o.getIndicador7() != null) {
                if (o.getIndicador7().getValor().equals("en proceso")) {
                    for (Opcion u : o.getIndicador7().getOpcionList()) {
                        if (lista7.isEmpty()) {
                            lista7.put(u, 1);
                        } else {
                            if (lista7.get(u) != null) {
                                lista7.put(u, (lista7.get(u) + 1));
                            } else {
                                lista7.put(u, 1);
                            }
                        }
                    }
                }
            }
            if (o.getIndicador8() != null) {
                if (o.getIndicador8().getValor().equals("en proceso")) {
                    for (Opcion u : o.getIndicador8().getOpcionList()) {
                        if (lista8.isEmpty()) {
                            lista8.put(u, 1);
                        } else {
                            if (lista8.get(u) != null) {
                                lista8.put(u, (lista8.get(u) + 1));
                            } else {
                                lista8.put(u, 1);
                            }
                        }
                    }
                }
            }
            if (o.getIndicador9() != null) {
                if (o.getIndicador9().getValor().equals("en proceso")) {
                    for (Opcion u : o.getIndicador9().getOpcionList()) {
                        if (lista9.isEmpty()) {
                            lista9.put(u, 1);
                        } else {
                            if (lista9.get(u) != null) {
                                lista9.put(u, (lista9.get(u) + 1));
                            } else {
                                lista9.put(u, 1);
                            }
                        }
                    }
                }
            }
            if (o.getIndicador10() != null) {
                if (o.getIndicador10().getValor().equals("en proceso")) {
                    for (Opcion u : o.getIndicador10().getOpcionList()) {
                        if (lista10.isEmpty()) {
                            lista10.put(u, 1);
                        } else {
                            if (lista10.get(u) != null) {
                                lista10.put(u, (lista10.get(u) + 1));
                            } else {
                                lista10.put(u, 1);
                            }
                        }
                    }
                }
            }
            if (o.getIndicador11() != null) {
                if (o.getIndicador11().getValor().equals("en proceso")) {
                    for (Opcion u : o.getIndicador11().getOpcionList()) {
                        if (lista11.isEmpty()) {
                            lista11.put(u, 1);
                        } else {
                            if (lista11.get(u) != null) {
                                lista11.put(u, (lista11.get(u) + 1));
                            } else {
                                lista11.put(u, 1);
                            }
                        }
                    }
                }
            }
            if (o.getIndicador12() != null) {
                if (o.getIndicador12().getValor().equals("en proceso")) {
                    for (Opcion u : o.getIndicador12().getOpcionList()) {
                        if (lista12.isEmpty()) {
                            lista12.put(u, 1);
                        } else {
                            if (lista12.get(u) != null) {
                                lista12.put(u, (lista12.get(u) + 1));
                            } else {
                                lista12.put(u, 1);
                            }
                        }
                    }
                }
            }
            if (o.getIndicador13() != null) {
                if (o.getIndicador13().getValor().equals("en proceso")) {
                    for (Opcion u : o.getIndicador13().getOpcionList()) {
                        if (lista13.isEmpty()) {
                            lista13.put(u, 1);
                        } else {
                            if (lista13.get(u) != null) {
                                lista13.put(u, (lista13.get(u) + 1));
                            } else {
                                lista13.put(u, 1);
                            }
                        }
                    }
                }
            }
            if (o.getIndicador14() != null) {
                if (o.getIndicador14().getValor().equals("en proceso")) {
                    for (Opcion u : o.getIndicador14().getOpcionList()) {
                        if (lista14.isEmpty()) {
                            lista14.put(u, 1);
                        } else {
                            if (lista14.get(u) != null) {
                                lista14.put(u, (lista14.get(u) + 1));
                            } else {
                                lista14.put(u, 1);
                            }
                        }
                    }
                }
            }
        }

        ArrayList<Opcion> opcionList = new ArrayList<>();
        for (Map.Entry<Opcion, Integer> entry : lista1.entrySet()) {
            Opcion key = entry.getKey();
            Integer value = entry.getValue();
            key.orden = value;
            opcionList.add(key);
        }


        for (Opcion u : indicadorOpcionesList.get(0).getOpcionList()) {
            boolean bandEncontrado = false;
            for (Opcion i : opcionList) {
                if (u.getValor().equals(i.getValor())) {
                    bandEncontrado = true;
                }
            }
            if (!bandEncontrado) {
                opcionList.add(u);
            }
        }


        Collections.sort(opcionList);
        adapterIndicadorOpcion1 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, opcionList);
        //TODO
        spinneridentidad1.setAdapter(adapterIndicadorOpcion1);


        ArrayList<Opcion> opcionList2 = new ArrayList<>();
        for (Map.Entry<Opcion, Integer> entry : lista2.entrySet()) {
            Opcion key = entry.getKey();
            Integer value = entry.getValue();
            key.orden = value;
            opcionList2.add(key);
        }


        for (Opcion u : indicadorOpcionesList.get(6).getOpcionList()) {
            boolean bandEncontrado = false;
            for (Opcion i : opcionList2) {
                if (u.getValor().equals(i.getValor())) {
                    bandEncontrado = true;
                }
            }
            if (!bandEncontrado) {
                opcionList2.add(u);
            }
        }


        Collections.sort(opcionList2);
        adapterIndicadorOpcion2 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, opcionList2);
        //TODO
        spinneridentidad2.setAdapter(adapterIndicadorOpcion2);

        ArrayList<Opcion> opcionList3 = new ArrayList<>();
        for (Map.Entry<Opcion, Integer> entry : lista3.entrySet()) {
            Opcion key = entry.getKey();
            Integer value = entry.getValue();
            key.orden = value;
            opcionList3.add(key);
        }


        for (Opcion u : indicadorOpcionesList.get(7).getOpcionList()) {
            boolean bandEncontrado = false;
            for (Opcion i : opcionList3) {
                if (u.getValor().equals(i.getValor())) {
                    bandEncontrado = true;
                }
            }
            if (!bandEncontrado) {
                opcionList3.add(u);
            }
        }


        Collections.sort(opcionList3);
        adapterIndicadorOpcion3 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, opcionList3);
        //TODO
        spinneridentidad3.setAdapter(adapterIndicadorOpcion3);

        ArrayList<Opcion> opcionList4 = new ArrayList<>();
        for (Map.Entry<Opcion, Integer> entry : lista4.entrySet()) {
            Opcion key = entry.getKey();
            Integer value = entry.getValue();
            key.orden = value;
            opcionList4.add(key);
        }


        for (Opcion u : indicadorOpcionesList.get(8).getOpcionList()) {
            boolean bandEncontrado = false;
            for (Opcion i : opcionList4) {
                if (u.getValor().equals(i.getValor())) {
                    bandEncontrado = true;
                }
            }
            if (!bandEncontrado) {
                opcionList4.add(u);
            }
        }


        Collections.sort(opcionList4);
        adapterIndicadorOpcion4 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, opcionList4);
        spinnerAutonomia1.setAdapter(adapterIndicadorOpcion4);

        ArrayList<Opcion> opcionList5 = new ArrayList<>();
        for (Map.Entry<Opcion, Integer> entry : lista5.entrySet()) {
            Opcion key = entry.getKey();
            Integer value = entry.getValue();
            key.orden = value;
            opcionList5.add(key);
        }


        for (Opcion u : indicadorOpcionesList.get(9).getOpcionList()) {
            boolean bandEncontrado = false;
            for (Opcion i : opcionList5) {
                if (u.getValor().equals(i.getValor())) {
                    bandEncontrado = true;
                }
            }
            if (!bandEncontrado) {
                opcionList5.add(u);
            }
        }


        Collections.sort(opcionList5);
        adapterIndicadorOpcion5 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, opcionList5);
        spinnerAutonomia2.setAdapter(adapterIndicadorOpcion5);

        ArrayList<Opcion> opcionList6 = new ArrayList<>();
        for (Map.Entry<Opcion, Integer> entry : lista6.entrySet()) {
            Opcion key = entry.getKey();
            Integer value = entry.getValue();
            key.orden = value;
            opcionList6.add(key);
        }


        for (Opcion u : indicadorOpcionesList.get(10).getOpcionList()) {
            boolean bandEncontrado = false;
            for (Opcion i : opcionList6) {
                if (u.getValor().equals(i.getValor())) {
                    bandEncontrado = true;
                }
            }
            if (!bandEncontrado) {
                opcionList6.add(u);
            }
        }


        Collections.sort(opcionList6);
        adapterIndicadorOpcion6 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, opcionList6);
        spinnerAutonomia3.setAdapter(adapterIndicadorOpcion6);


        ArrayList<Opcion> opcionList7 = new ArrayList<>();
        for (Map.Entry<Opcion, Integer> entry : lista7.entrySet()) {
            Opcion key = entry.getKey();
            Integer value = entry.getValue();
            key.orden = value;
            opcionList7.add(key);
        }


        for (Opcion u : indicadorOpcionesList.get(11).getOpcionList()) {
            boolean bandEncontrado = false;
            for (Opcion i : opcionList7) {
                if (u.getValor().equals(i.getValor())) {
                    bandEncontrado = true;
                }
            }
            if (!bandEncontrado) {
                opcionList7.add(u);
            }
        }


        Collections.sort(opcionList7);
        adapterIndicadorOpcion7 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, opcionList7);
        spinnerAutonomia4.setAdapter(adapterIndicadorOpcion7);


        ArrayList<Opcion> opcionList8 = new ArrayList<>();
        for (Map.Entry<Opcion, Integer> entry : lista8.entrySet()) {
            Opcion key = entry.getKey();
            Integer value = entry.getValue();
            key.orden = value;
            opcionList8.add(key);
        }


        for (Opcion u : indicadorOpcionesList.get(12).getOpcionList()) {
            boolean bandEncontrado = false;
            for (Opcion i : opcionList8) {
                if (u.getValor().equals(i.getValor())) {
                    bandEncontrado = true;
                }
            }
            if (!bandEncontrado) {
                opcionList8.add(u);
            }
        }


        Collections.sort(opcionList8);
        adapterIndicadorOpcion8 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, opcionList8);
        spinnerConvivencia1.setAdapter(adapterIndicadorOpcion8);

        ArrayList<Opcion> opcionList9 = new ArrayList<>();
        for (Map.Entry<Opcion, Integer> entry : lista9.entrySet()) {
            Opcion key = entry.getKey();
            Integer value = entry.getValue();
            key.orden = value;
            opcionList9.add(key);
        }

        for (Opcion u : indicadorOpcionesList.get(13).getOpcionList()) {
            boolean bandEncontrado = false;
            for (Opcion i : opcionList9) {
                if (u.getValor().equals(i.getValor())) {
                    bandEncontrado = true;
                }
            }
            if (!bandEncontrado) {
                opcionList9.add(u);
            }
        }


        Collections.sort(opcionList9);
        adapterIndicadorOpcion9 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, opcionList9);
        spinnerConvivencia2.setAdapter(adapterIndicadorOpcion9);


        ArrayList<Opcion> opcionList10 = new ArrayList<>();
        for (Map.Entry<Opcion, Integer> entry : lista10.entrySet()) {
            Opcion key = entry.getKey();
            Integer value = entry.getValue();
            key.orden = value;
            opcionList10.add(key);
        }

        for (Opcion u : indicadorOpcionesList.get(1).getOpcionList()) {
            boolean bandEncontrado = false;
            for (Opcion i : opcionList10) {
                if (u.getValor().equals(i.getValor())) {
                    bandEncontrado = true;
                }
            }
            if (!bandEncontrado) {
                opcionList10.add(u);
            }
        }


        Collections.sort(opcionList10);
        adapterIndicadorOpcion10 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, opcionList10);
        spinnerConvivencia3.setAdapter(adapterIndicadorOpcion10);


        ArrayList<Opcion> opcionList11 = new ArrayList<>();
        for (Map.Entry<Opcion, Integer> entry : lista11.entrySet()) {
            Opcion key = entry.getKey();
            Integer value = entry.getValue();
            key.orden = value;
            opcionList11.add(key);
        }

        for (Opcion u : indicadorOpcionesList.get(2).getOpcionList()) {
            boolean bandEncontrado = false;
            for (Opcion i : opcionList11) {
                if (u.getValor().equals(i.getValor())) {
                    bandEncontrado = true;
                }
            }
            if (!bandEncontrado) {
                opcionList11.add(u);
            }
        }


        Collections.sort(opcionList11);
        adapterIndicadorOpcion11 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, opcionList11);
        spinnerConvivencia4.setAdapter(adapterIndicadorOpcion11);

        ArrayList<Opcion> opcionList12 = new ArrayList<>();
        for (Map.Entry<Opcion, Integer> entry : lista12.entrySet()) {
            Opcion key = entry.getKey();
            Integer value = entry.getValue();
            key.orden = value;
            opcionList12.add(key);
        }

        for (Opcion u : indicadorOpcionesList.get(3).getOpcionList()) {
            boolean bandEncontrado = false;
            for (Opcion i : opcionList12) {
                if (u.getValor().equals(i.getValor())) {
                    bandEncontrado = true;
                }
            }
            if (!bandEncontrado) {
                opcionList12.add(u);
            }
        }


        Collections.sort(opcionList12);
        adapterIndicadorOpcion12 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, opcionList12);
        spinnervidaSaludable1.setAdapter(adapterIndicadorOpcion12);

        ArrayList<Opcion> opcionList13 = new ArrayList<>();
        for (Map.Entry<Opcion, Integer> entry : lista13.entrySet()) {
            Opcion key = entry.getKey();
            Integer value = entry.getValue();
            key.orden = value;
            opcionList13.add(key);
        }

        for (Opcion u : indicadorOpcionesList.get(4).getOpcionList()) {
            boolean bandEncontrado = false;
            for (Opcion i : opcionList13) {
                if (u.getValor().equals(i.getValor())) {
                    bandEncontrado = true;
                }
            }
            if (!bandEncontrado) {
                opcionList13.add(u);
            }
        }


        Collections.sort(opcionList13);
        adapterIndicadorOpcion13 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, opcionList13);
        spinnervidaSaludable2.setAdapter(adapterIndicadorOpcion13);


        ArrayList<Opcion> opcionList14 = new ArrayList<>();
        for (Map.Entry<Opcion, Integer> entry : lista14.entrySet()) {
            Opcion key = entry.getKey();
            Integer value = entry.getValue();
            key.orden = value;
            opcionList14.add(key);
        }

        for (Opcion u : indicadorOpcionesList.get(5).getOpcionList()) {
            boolean bandEncontrado = false;
            for (Opcion i : opcionList14) {
                if (u.getValor().equals(i.getValor())) {
                    bandEncontrado = true;
                }
            }
            if (!bandEncontrado) {
                opcionList14.add(u);
            }
        }


        Collections.sort(opcionList14);
        adapterIndicadorOpcion14 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, opcionList14);
        spinnervidaSaludable3.setAdapter(adapterIndicadorOpcion14);

    }

    private void listarOpcionesDatos() {
        mDatabase.child("indicadores").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                indicadorOpcionesList.clear();
                try {
                    for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                        Indicador p = objSnaptshot.getValue(Indicador.class);
                        indicadorOpcionesList.add(p);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                listarDatos();

//                adapterAnecdotario = new AdapterAnecdotario(registroAnecdotarioList, getApplicationContext());
//                lv_datosPersonasRnot.setAdapter(adapterAnecdotario);

//                adapterIndicadorOpcion1 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, indicadorOpcionesList.get(0).getOpcionList());
//                //TODO
//                spinneridentidad1.setAdapter(adapterIndicadorOpcion1);
//
//
//                adapterIndicadorOpcion2 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, indicadorOpcionesList.get(6).getOpcionList());
//                //TODO
//                spinneridentidad2.setAdapter(adapterIndicadorOpcion2);
//
//
//                adapterIndicadorOpcion3 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, indicadorOpcionesList.get(7).getOpcionList());
//                //TODO
//                spinneridentidad3.setAdapter(adapterIndicadorOpcion3);
//
//                adapterIndicadorOpcion4 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, indicadorOpcionesList.get(8).getOpcionList());
//                //TODO
//                spinnerAutonomia1.setAdapter(adapterIndicadorOpcion4);
//
//                adapterIndicadorOpcion5 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, indicadorOpcionesList.get(9).getOpcionList());
//                //TODO
//                spinnerAutonomia2.setAdapter(adapterIndicadorOpcion5);
//
//                adapterIndicadorOpcion6 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, indicadorOpcionesList.get(10).getOpcionList());
//                //TODO
//                spinnerAutonomia3.setAdapter(adapterIndicadorOpcion6);
//
//                adapterIndicadorOpcion7 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, indicadorOpcionesList.get(11).getOpcionList());
//                //TODO
//                spinnerAutonomia4.setAdapter(adapterIndicadorOpcion7);
//
//                adapterIndicadorOpcion8 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, indicadorOpcionesList.get(12).getOpcionList());
//                //TODO
//                spinnerConvivencia1.setAdapter(adapterIndicadorOpcion8);
//
//                adapterIndicadorOpcion9 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, indicadorOpcionesList.get(13).getOpcionList());
//                //TODO
//                spinnerConvivencia2.setAdapter(adapterIndicadorOpcion9);
//
//                adapterIndicadorOpcion10 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, indicadorOpcionesList.get(1).getOpcionList());
//                //TODO
//                spinnerConvivencia3.setAdapter(adapterIndicadorOpcion10);
//
//                adapterIndicadorOpcion11 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, indicadorOpcionesList.get(2).getOpcionList());
//                //TODO
//                spinnerConvivencia4.setAdapter(adapterIndicadorOpcion11);
//
//                adapterIndicadorOpcion12 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, indicadorOpcionesList.get(3).getOpcionList());
//                //TODO
//                spinnervidaSaludable1.setAdapter(adapterIndicadorOpcion12);
//
//                adapterIndicadorOpcion13 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, indicadorOpcionesList.get(4).getOpcionList());
//                //TODO
//                spinnervidaSaludable2.setAdapter(adapterIndicadorOpcion13);
//
//                adapterIndicadorOpcion14 = new ListOpcionesAdapter(indicadoresActivityProfesor.this, indicadorOpcionesList.get(5).getOpcionList());
//                //TODO
//                spinnervidaSaludable3.setAdapter(adapterIndicadorOpcion14);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    private void validacion() {

        //    String grupoT = cajapuntos.getText().toString();
        if (almacenaIndicador1 == null || almacenaIndicador2 == null || almacenaIndicador3 == null || almacenaIndicador4 == null
                || almacenaIndicador5 == null || almacenaIndicador6 == null || almacenaIndicador7 == null || almacenaIndicador8 == null
                || almacenaIndicador9 == null || almacenaIndicador10 == null || almacenaIndicador11 == null || almacenaIndicador12 == null
                || almacenaIndicador13 == null || almacenaIndicador14 == null) {
            Toast.makeText(this, "Llena todos los indicadores", Toast.LENGTH_SHORT).show();
        } else {
            Map<String, Object> personmap = new HashMap<>();
            // Toast.makeText(administrador.this, "", Toast.LENGTH_SHORT).show();
            personmap.put("indicador1", almacenaIndicador1);
            personmap.put("indicador2", almacenaIndicador2);
            personmap.put("indicador3", almacenaIndicador3);
            personmap.put("indicador4", almacenaIndicador4);
            personmap.put("indicador5", almacenaIndicador5);
            personmap.put("indicador6", almacenaIndicador6);
            personmap.put("indicador7", almacenaIndicador7);
            personmap.put("indicador8", almacenaIndicador8);
            personmap.put("indicador9", almacenaIndicador9);
            personmap.put("indicador10", almacenaIndicador10);
            personmap.put("indicador11", almacenaIndicador11);
            personmap.put("indicador12", almacenaIndicador12);
            personmap.put("indicador13", almacenaIndicador13);
            personmap.put("indicador14", almacenaIndicador14);

            EvaluacionIndicadores evaluacionIndicadores = new EvaluacionIndicadores();
//            evaluacionIndicadores.setAlmacenaIndicador1(almacenaIndicador1);
//            evaluacionIndicadores.setAlmacenaIndicador2(almacenaIndicador2);
//            evaluacionIndicadores.setAlmacenaIndicador3(almacenaIndicador3);
//            evaluacionIndicadores.setAlmacenaIndicador4(almacenaIndicador4);
//            evaluacionIndicadores.setAlmacenaIndicador5(almacenaIndicador5);
//            evaluacionIndicadores.setAlmacenaIndicador6(almacenaIndicador6);
//            evaluacionIndicadores.setAlmacenaIndicador7(almacenaIndicador7);
//            evaluacionIndicadores.setAlmacenaIndicador8(almacenaIndicador8);
//            evaluacionIndicadores.setAlmacenaIndicador9(almacenaIndicador9);
//            evaluacionIndicadores.setAlmacenaIndicador10(almacenaIndicador10);
//            evaluacionIndicadores.setAlmacenaIndicador11(almacenaIndicador11);
//            evaluacionIndicadores.setAlmacenaIndicador12(almacenaIndicador12);
//            evaluacionIndicadores.setAlmacenaIndicador13(almacenaIndicador13);
//            evaluacionIndicadores.setAlmacenaIndicador14(almacenaIndicador14);

            evaluacionIndicadores.setIndicador1(indicador1);
            evaluacionIndicadores.setIndicador2(indicador2);
            evaluacionIndicadores.setIndicador3(indicador3);
            evaluacionIndicadores.setIndicador4(indicador4);
            evaluacionIndicadores.setIndicador5(indicador5);
            evaluacionIndicadores.setIndicador6(indicador6);
            evaluacionIndicadores.setIndicador7(indicador7);
            evaluacionIndicadores.setIndicador8(indicador8);
            evaluacionIndicadores.setIndicador9(indicador9);
            evaluacionIndicadores.setIndicador10(indicador10);
            evaluacionIndicadores.setIndicador11(indicador11);
            evaluacionIndicadores.setIndicador12(indicador12);
            evaluacionIndicadores.setIndicador13(indicador13);
            evaluacionIndicadores.setIndicador14(indicador14);
//TODO
//            if (educapyModelUser.getEvaluacionIndicadoresList() == null) {
//                educapyModelUser.setEvaluacionIndicadoresList(new ArrayList<>());
//            }
//            int sizeList = educapyModelUser.getEvaluacionIndicadoresList().size();
//            educapyModelUser.getEvaluacionIndicadoresList().add(sizeList, evaluacionIndicadores);

            evaluacionIndicadores.setUidAlumno(educapyModelUser.getUid());

            //DatabaseReference usersRef = databaseReference.child("Cursos").child("id");
            mDatabase.child("EvaluacionIndicadores").child("id").push().setValue(evaluacionIndicadores);
            Toast.makeText(indicadoresActivityProfesor.this, "Indicadores Actualizados", Toast.LENGTH_SHORT).show();
            finish();
            //Toast.makeText(AltabajaCursos.this, "Curso Registrado Con Éxito", Toast.LENGTH_SHORT).show();

//            mDatabase.child("EvaluacionIndicadores")
//                    .child("id")
//                    .setValue(evaluacionIndicadores)
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            //   Intent intent = new Intent(indicadoresActivity.this, principal.class);
//                            //   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
//                            //   startActivity(intent);
//                            //guardarEvaluacionIndicadores();
//                            Toast.makeText(indicadoresActivityProfesor.this, "Indicadores Actualizados", Toast.LENGTH_SHORT).show();
//                            finish();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(indicadoresActivityProfesor.this, "Indicadores no actualizados", Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }

//    public void guardarEvaluacionIndicadores() {
//        mDatabase.child("EvaluacionIndicadores").setValue(evaluacionIndicadores).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                //   Intent intent = new Intent(indicadoresActivity.this, principal.class);
//                //   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
//                //   startActivity(intent);
//                Toast.makeText(indicadoresActivityProfesor.this, "Indicadores Actualizados", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(indicadoresActivityProfesor.this, "Indicadores no actualizados", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent intent = new Intent(indicadoresActivityProfesor.this, evaluacionVentanaProfesor.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
//        startActivity(intent);
    }
}