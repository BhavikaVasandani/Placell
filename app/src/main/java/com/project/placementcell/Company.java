package com.project.placementcell;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Company extends AppCompatActivity {
    EditText search;
    ImageButton back, like;
    String search1;
    List<ListData> listData, listData1;
    private RecyclerView rv, rv1;
    private MyAdapter adapter, adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_name);
        rv = (RecyclerView) findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv1 = (RecyclerView) findViewById(R.id.recyclerview);
        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(new LinearLayoutManager(this));
        listData = new ArrayList<>();
        listData1 = new ArrayList<>();

        search = findViewById(R.id.search);
        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference("data");
        final DatabaseReference nm1 = FirebaseDatabase.getInstance().getReference("data");

        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        ListData l = npsnapshot.getValue(ListData.class);
                        listData.add(l);
                    }

                    adapter = new MyAdapter(listData);
                    rv.setAdapter(adapter);

                    String name = dataSnapshot.child("name").getValue(String.class);
                    String description = dataSnapshot.child("description").getValue(String.class);
                    Log.d("TAG", name + " / " + description);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
                                                ListData l = npsnapshot.getValue(ListData.class);
                                                search1 = search.getText().toString().trim();

                                                Toast.makeText(getApplicationContext(),search1,Toast.LENGTH_LONG).show();
                                                if(l.getName().equalsIgnoreCase(search1))
                                                listData1.add(l);
                                            }

                                            adapter = new MyAdapter(listData1);
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