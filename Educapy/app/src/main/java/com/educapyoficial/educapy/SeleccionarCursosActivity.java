package com.educapyoficial.educapy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.educapyoficial.educapy.adapters.AdapterListInbox2;
import com.educapyoficial.educapy.models.CursosModel;
import com.educapyoficial.educapy.models.EducapyModelUser;
import com.educapyoficial.educapy.models.EducapyModelUserProfesor;
import com.educapyoficial.educapy.pojos.Users;
import com.educapyoficial.educapy.utils.Tools;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SeleccionarCursosActivity extends AppCompatActivity {

    private static final String TAG = "VINCULAR";
    private View parent_view;

    private RecyclerView recyclerView;
    private AdapterListInbox2 mAdapter;
    private ActionModeCallback actionModeCallback;
    private ActionMode actionMode;
    private Toolbar toolbar;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    ArrayList<CursosModel> items = new ArrayList<>();
    String uidProfesor;

    EducapyModelUserProfesor educapyModelUserProfesor;
    private ArrayList<EducapyModelUserProfesor> listUsuarios = new ArrayList<EducapyModelUserProfesor>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_cursos);

        setContentView(R.layout.activity_vincular_profe_alumno);
        parent_view = findViewById(R.id.lyt_parent);

        inicializarFirebase();
        Intent intent = getIntent();
        uidProfesor = intent.getExtras().getString("uidprofesor", "");
        listUsuarios = (ArrayList<EducapyModelUserProfesor>) intent.getSerializableExtra("list_profesores");


        initToolbar();
        initComponent();
        Toast.makeText(this, "Mantenga presionado para la selección multiple", Toast.LENGTH_SHORT).show();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> cursosStrings = new ArrayList<>();

                for (CursosModel o : mAdapter.getItems()) {
                    o.setUidProfesor("");
                    databaseReference.child("Cursos").child("id").child(o.getUid()).setValue(o);
                }

                for (Integer i : mAdapter.getSelectedItems()) {
                    CursosModel cursosModel = mAdapter.getItem(i);
                    cursosModel.setUidProfesor(educapyModelUserProfesor.getUid());
                    databaseReference.child("Cursos").child("id").child(cursosModel.getUid()).setValue(cursosModel);
                    cursosStrings.add(cursosModel.getUid());
                }
                educapyModelUserProfesor.setUidCursosList(cursosStrings);
                databaseReference.child("Profesores").child("id").child(educapyModelUserProfesor.getUid()).setValue(educapyModelUserProfesor);


                try {
                    databaseReference = firebaseDatabase.getReference();
                    DatabaseReference ref_user = databaseReference.child("UsersChat").child(educapyModelUserProfesor.getUid());
                    Users users = new Users();
                    users.setTokenFirebase(educapyModelUserProfesor.getTokenFirebase());
                    users.setId(educapyModelUserProfesor.getUidfirebase());
                    users.setUid(educapyModelUserProfesor.getUid());
                    users.setMail(educapyModelUserProfesor.getCorreo());
                    users.setNombre(educapyModelUserProfesor.getNombre());
                    users.setUidCurso(educapyModelUserProfesor.getUidCursosList().get(0));
                    ref_user.setValue(users);
                } catch (Exception e) {
                }



                Toast.makeText(getApplicationContext(), "Cursos Asignados con Éxito!!.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Seleccionar Cursos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.colorPrimaryDark);
    }


    private void listarDatos() {

        databaseReference.child("Profesores").child("id").child(uidProfesor).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    //for (DataSnapshot objSnaptshot : snapshot.getChildren()) {
                    DataSnapshot objSnaptshot = snapshot;
                    educapyModelUserProfesor = objSnaptshot.getValue(EducapyModelUserProfesor.class);
                    cargarCursos();
                    // }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void cargarCursos() {
        List<Integer> listPosition = new ArrayList<>();
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        databaseReference.child("Cursos").child("id").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int position = 0;
                items.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    //maxid = (dataSnapshot.getChildrenCount());
                    CursosModel p = objSnaptshot.getValue(CursosModel.class);
                    if (p.getUidProfesor() != null && !p.getUidProfesor().equals("")) {
                        if (p.getUidProfesor().equals(educapyModelUserProfesor.getUid())) {
                            p.setUid(objSnaptshot.getKey());
                            items.add(p);
                            sparseBooleanArray.put(position, true);
                            listPosition.add(position);
                            position++;
                        }
                    } else {
                        p.setUid(objSnaptshot.getKey());
                        items.add(p);
                        //listPosition.add(position);
                        //sparseBooleanArray.put(position, false);
                        position++;
                    }
//                    p.setSelect(false);
//
//                    if (educapyModelUserProfesor.getUidCursosList() != null) {
//                        for (String e : educapyModelUserProfesor.getUidCursosList()) {
//                            if (e.equals(p.getUid())) {
//                                band = true;
//                                sparseBooleanArray.put(position, band);
//                            }
//                        }
//                    }


                }
                mAdapter = new AdapterListInbox2(SeleccionarCursosActivity.this, items, educapyModelUserProfesor);
                mAdapter.setItemChecked(sparseBooleanArray);
                mAdapter.setOnClickListener(new AdapterListInbox2.OnClickListener() {
                    @Override
                    public void onItemClick(View view, CursosModel obj, int pos) {
                        if (mAdapter.getSelectedItemCount() > 0) {
                            enableActionMode(pos);
                        } else {
                            // read the inbox which removes bold from the row
                            CursosModel inbox = mAdapter.getItem(pos);
                            //Toast.makeText(getApplicationContext(), "Read: " + inbox.getCursos(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onItemLongClick(View view, CursosModel obj, int pos) {
                        enableActionMode(pos);
                    }
                });

                for (Integer i : listPosition) {
                    //enableActionMode(i);
                    //toggleSelection(i);
                }
                recyclerView.setAdapter(mAdapter);
//                usuariosAdapter.setUsuariosList(listUsuarios);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initComponent() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        //List<User> items = DataGenerator.getInboxData(this);
        //set data and list adapter
        listarDatos();
        actionModeCallback = new ActionModeCallback();

    }

    private void enableActionMode(int position) {
        if (actionMode == null) {
            actionMode = startSupportActionMode(actionModeCallback);
        }
        toggleSelection(position);
    }

    private void toggleSelection(int position) {

        mAdapter.toggleSelection(position);
        int count = mAdapter.getSelectedItemCount();

        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }

    private class ActionModeCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            Tools.setSystemBarColor(SeleccionarCursosActivity.this, R.color.blue_grey_700);
            mode.getMenuInflater().inflate(R.menu.menu_delete, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.action_delete) {
                deleteInboxes();
                mode.finish();
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mAdapter.clearSelections();
            actionMode = null;
            Tools.setSystemBarColor(SeleccionarCursosActivity.this, R.color.red_600);
        }

    }

    private void deleteInboxes() {
        List<Integer> selectedItemPositions = mAdapter.getSelectedItems();
        for (int i = selectedItemPositions.size() - 1; i >= 0; i--) {
            mAdapter.removeData(selectedItemPositions.get(i));
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_search_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}