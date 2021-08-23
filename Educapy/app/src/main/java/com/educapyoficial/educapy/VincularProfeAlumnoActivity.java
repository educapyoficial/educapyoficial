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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.educapyoficial.educapy.adapters.AdapterListInbox;
import com.educapyoficial.educapy.adapters.ListaUsuariosAdapter;
import com.educapyoficial.educapy.utils.Tools;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class VincularProfeAlumnoActivity extends AppCompatActivity {

    private View parent_view;

    private RecyclerView recyclerView;
    private AdapterListInbox mAdapter;
    private ActionModeCallback actionModeCallback;
    private ActionMode actionMode;
    private Toolbar toolbar;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    ArrayList<EducapyModelUser> items = new ArrayList<>();

    String uidProfesor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vincular_profe_alumno);
        parent_view = findViewById(R.id.lyt_parent);

        inicializarFirebase();

        initToolbar();
        initComponent();
        Toast.makeText(this, "Long press for multi selection", Toast.LENGTH_SHORT).show();

        Intent intent = getIntent();
        uidProfesor = intent.getExtras().getString("uidprofesor", "");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (Integer i : mAdapter.getSelectedItems()) {
                    EducapyModelUser educapyModelUser = mAdapter.getItem(i);
                    educapyModelUser.setUidProfesor(uidProfesor);
                    databaseReference.child("Users").child("Clients").child(educapyModelUser.getUid()).setValue(educapyModelUser);

                }
                Toast.makeText(getApplicationContext(), "Alumnos Asignados con Éxito!!.", Toast.LENGTH_SHORT).show();
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
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Inbox");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.colorPrimaryDark);
    }


    private void listarDatos() {
        databaseReference.child("Users").child("Clients").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                items.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    //maxid = (dataSnapshot.getChildrenCount());
                    EducapyModelUser p = objSnaptshot.getValue(EducapyModelUser.class);
                    p.setUid(objSnaptshot.getKey());
                    items.add(p);
                }
                mAdapter = new AdapterListInbox(VincularProfeAlumnoActivity.this, items);
                mAdapter.setOnClickListener(new AdapterListInbox.OnClickListener() {
                    @Override
                    public void onItemClick(View view, EducapyModelUser obj, int pos) {
                        if (mAdapter.getSelectedItemCount() > 0) {
                            enableActionMode(pos);
                        } else {
                            // read the inbox which removes bold from the row
                            EducapyModelUser inbox = mAdapter.getItem(pos);
                            Toast.makeText(getApplicationContext(), "Read: " + inbox.getNombre(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onItemLongClick(View view, EducapyModelUser obj, int pos) {
                        enableActionMode(pos);
                    }
                });
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
            Tools.setSystemBarColor(VincularProfeAlumnoActivity.this, R.color.blue_grey_700);
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
            Tools.setSystemBarColor(VincularProfeAlumnoActivity.this, R.color.red_600);
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
        getMenuInflater().inflate(R.menu.menu_search_setting, menu);
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