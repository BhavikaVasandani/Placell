package com.project.placementcell;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.L;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Notification extends AppCompatActivity {

   List<ListData3> listData;
    private RecyclerView rv;
    private NotificationAdapter adapter;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        rv = (RecyclerView) findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(linearLayoutManager);
        listData = new ArrayList<>();
       final DatabaseReference nm = FirebaseDatabase.getInstance().getReference("notification");

        nm.addListenerForSingleValueEvent(new ValueEventListener() {
                                              @Override
                                              public void onDataChange(DataSnapshot dataSnapshot) {
                                                  if (dataSnapshot.exists()) {
                                                      for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                                                          ListData3 l = npsnapshot.getValue(ListData3.class);
                                                          listData.add(l);
                                                      }

                                                      adapter = new NotificationAdapter(listData);
                                                      rv.setAdapter(adapter);

                                                      String name = dataSnapshot.child("name").getValue(String.class);
                                                      String description = dataSnapshot.child("description").getValue(String.class);
                                                      Log.d("TAG", name + " / " + description);
                                                  }
                                              }

                                              @Override
                                              public void onCancelled(@NonNull DatabaseError databaseError) {

                                              }
                                          });

       Intent intent = getIntent();

        FirebaseDatabase.getInstance()
                .getReference().child("notification")
                .push()
                .setValue(new ListData3(intent.getStringExtra("title"),intent.getStringExtra("message")));

    }
}

