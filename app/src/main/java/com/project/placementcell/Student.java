package com.project.placementcell;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Student extends AppCompatActivity {
    RecyclerView rv,rv1;
    ArrayList listData,listData1;
    EditText search;
    FirebaseAuth auth;
    StudentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        rv = (RecyclerView) findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv1 = (RecyclerView) findViewById(R.id.recyclerview);
        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(new LinearLayoutManager(this));
        listData = new ArrayList<>();
        listData1 = new ArrayList<>();

        search = findViewById(R.id.search);
        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference().child("Student");
        final DatabaseReference nm1 = FirebaseDatabase.getInstance().getReference().child("Student");

        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        ListData1 l = npsnapshot.getValue(ListData1.class);
                        listData1.add(l);
                    }

                    adapter = new StudentAdapter(listData1);
                    rv.setAdapter(adapter);

                    String name = dataSnapshot.child("Name").getValue(String.class);
                    String description = dataSnapshot.child("Gender").getValue(String.class);
                    Log.d("TAG", name + " / " + description);
                    Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        search=findViewById(R.id.search);
        search.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event != null &&
                                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (event == null || !event.isShiftPressed()) {
                                // the user is done typing.
                                listData1.clear();
                                nm1.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                                                ListData1 l = npsnapshot.getValue(ListData1.class);
                                                String search1;

                                                search1 = search.getText().toString().trim();

                                                Toast.makeText(getApplicationContext(),search1,Toast.LENGTH_LONG).show();
                                                if(l.getName().equalsIgnoreCase(search1))
                                                    listData1.add(l);
                                            }

                                            adapter = new StudentAdapter(listData1);
                                            rv1.setAdapter(adapter);

                                            String name = dataSnapshot.child("name").getValue(String.class);
                                            String description = dataSnapshot.child("description").getValue(String.class);
                                            Log.d("TAG", name + " / " + description);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                            return true; // consume.
                        }

                        return false; // pass on to other listeners.
                    }
                });
    }
}