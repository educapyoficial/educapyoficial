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

import com.educapyoficial.educapy.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class indicadoresActivityProfesor extends AppCompatActivity {

    ArrayAdapter<String> mAdapter, mAdapter2, mAdapter3, mAdapter4, mAdapter5, mAdapter6, mAdapter7, mAdapter8, mAdapter9, mAdapter10, mAdapter11,mAdapter12,mAdapter13,mAdapter14;
    Spinner spinneridentidad1, spinneridentidad2, spinneridentidad3, spinnerAutonomia1, spinnerAutonomia2, spinnerAutonomia3, spinnerAutonomia4, spinnerConvivencia1,spinnerConvivencia2,spinnerConvivencia3,
            spinnerConvivencia4,spinnervidaSaludable1,spinnervidaSaludable2,spinnervidaSaludable3;

    CheckBox cajaIndicador1,cajaIndicador2,cajaIndicador3,cajaIndicador4,cajaIndicador5,cajaIndicador6,cajaIndicador7,cajaIndicador8,cajaIndicador9,cajaIndicador10,
            cajaIndicador11,cajaIndicador12,cajaIndicador13,cajaIndicador14,cajaIndicador15,cajaIndicador16,cajaIndicador17,cajaIndicador18,cajaIndicador19,cajaIndicador20,
            cajaIndicador21,cajaIndicador22,cajaIndicador23,cajaIndicador24,cajaIndicador25,cajaIndicador26,cajaIndicador27,cajaIndicador28;

    String almacenaIndicador1,almacenaIndicador2,almacenaIndicador3,almacenaIndicador4,almacenaIndicador5,almacenaIndicador6,almacenaIndicador7,almacenaIndicador8,almacenaIndicador9,almacenaIndicador10,
            almacenaIndicador11,almacenaIndicador12,almacenaIndicador13,almacenaIndicador14;

    private CircleImageView mCircleImageNext;

    String obtengogkeR,obtengoNombre;

    TextView txtnombre;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicadores);

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
                    // Toast.makeText(PrimerVentana.this, "Caja 1 vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });



        mAdapter = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.indica1Q));
        spinneridentidad1.setAdapter(mAdapter);

        mAdapter2 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.indica2Q));
        spinneridentidad2.setAdapter(mAdapter2);

        mAdapter3 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.indica3Q));
        spinneridentidad3.setAdapter(mAdapter3);

        mAdapter4 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.autonomia1Q));
        spinnerAutonomia1.setAdapter(mAdapter4);

        mAdapter5 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.autonomia2Q));
        spinnerAutonomia2.setAdapter(mAdapter5);

        mAdapter6 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.autonomia3Q));
        spinnerAutonomia3.setAdapter(mAdapter6);

        mAdapter7 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.autonomia4Q));
        spinnerAutonomia4.setAdapter(mAdapter7);

        mAdapter8 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.convivencia1Q));
        spinnerConvivencia1.setAdapter(mAdapter8);

        mAdapter9 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.convivencia2Q));
        spinnerConvivencia2.setAdapter(mAdapter9);

        mAdapter10 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.convivencia3Q));
        spinnerConvivencia3.setAdapter(mAdapter10);

        mAdapter11 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.convivencia4Q));
        spinnerConvivencia4.setAdapter(mAdapter11);

        mAdapter12 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.vidaSaludable1Q));
        spinnervidaSaludable1.setAdapter(mAdapter12);

        mAdapter13 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.vidaSaludable2Q));
        spinnervidaSaludable2.setAdapter(mAdapter13);

        mAdapter14 = new ArrayAdapter<String>(indicadoresActivityProfesor.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.vidaSaludable3Q));
        spinnervidaSaludable3.setAdapter(mAdapter14);


        spinneridentidad1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ((TextView) spinneridentidad1.getSelectedView()).setTextColor(Color.BLACK);

                String seleccionEdad = spinneridentidad1.getSelectedItem().toString();

                /*
                if (seleccionEdad.equals("NINGUNO")) {
                    puntosAntecePerson = 0;
                }
                if (seleccionEdad.equals("HIPERTENSION ARTERIAL")) {
                    puntosAntecePerson = 4;
                }
                if (seleccionEdad.equals("DIABETES MELLITUS")) {
                    puntosAntecePerson = 4;
                }
                if (seleccionEdad.equals("CARDIOPATIA")) {
                    puntosAntecePerson = 4;
                }
                if (seleccionEdad.equals("OTRA ENFERMEDAD CRONICA Y/O SISTEMICA GRAVE")) {
                    puntosAntecePerson = 4;
                }

                 */
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinneridentidad2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ((TextView) spinneridentidad2.getSelectedView()).setTextColor(Color.BLACK);

                String seleccionEdad = spinneridentidad2.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinneridentidad3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ((TextView) spinneridentidad3.getSelectedView()).setTextColor(Color.BLACK);

                String seleccionEdad = spinneridentidad3.getSelectedItem().toString();

                /*
                if (seleccionEdad.equals("NINGUNO")) {
                    puntosAntecePerson = 0;
                }
                if (seleccionEdad.equals("HIPERTENSION ARTERIAL")) {
                    puntosAntecePerson = 4;
                }
                if (seleccionEdad.equals("DIABETES MELLITUS")) {
                    puntosAntecePerson = 4;
                }
                if (seleccionEdad.equals("CARDIOPATIA")) {
                    puntosAntecePerson = 4;
                }
                if (seleccionEdad.equals("OTRA ENFERMEDAD CRONICA Y/O SISTEMICA GRAVE")) {
                    puntosAntecePerson = 4;
                }

                 */
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void validacion() {

        //    String grupoT = cajapuntos.getText().toString();
        if (almacenaIndicador1==null||almacenaIndicador2==null||almacenaIndicador3==null||almacenaIndicador4==null
                ||almacenaIndicador5==null||almacenaIndicador6==null||almacenaIndicador7==null||almacenaIndicador8==null
                ||almacenaIndicador9==null||almacenaIndicador10==null||almacenaIndicador11==null||almacenaIndicador12==null
                ||almacenaIndicador13==null||almacenaIndicador14==null) {
            Toast.makeText(this, "Llena todos los indicadores", Toast.LENGTH_SHORT).show();
        }else
        {
            Map<String, Object> personmap = new HashMap<>();
            // Toast.makeText(administrador.this, "", Toast.LENGTH_SHORT).show();
            personmap.put("indicador1",almacenaIndicador1);
            personmap.put("indicador2",almacenaIndicador2);
            personmap.put("indicador3",almacenaIndicador3);
            personmap.put("indicador4",almacenaIndicador4);
            personmap.put("indicador5",almacenaIndicador5);
            personmap.put("indicador6",almacenaIndicador6);
            personmap.put("indicador7",almacenaIndicador7);
            personmap.put("indicador8",almacenaIndicador8);
            personmap.put("indicador9",almacenaIndicador9);
            personmap.put("indicador10",almacenaIndicador10);
            personmap.put("indicador11",almacenaIndicador11);
            personmap.put("indicador12",almacenaIndicador12);
            personmap.put("indicador13",almacenaIndicador13);
            personmap.put("indicador14",almacenaIndicador14);


            mDatabase.child("Users").child("Clients").child(obtengogkeR).updateChildren(personmap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                 //   Intent intent = new Intent(indicadoresActivity.this, principal.class);
                 //   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
                 //   startActivity(intent);
                    Toast.makeText(indicadoresActivityProfesor.this, "Indicadores Actualizados", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                     Toast.makeText(indicadoresActivityProfesor.this, "Indicadores no actualizados", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent intent = new Intent(indicadoresActivityProfesor.this, evaluacionVentanaProfesor.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CONDUCTOR NO REGRESE A LA ACTIVIDAD DE CREAR CUENTA
//        startActivity(intent);
    }
}