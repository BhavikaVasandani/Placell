package com.project.placementcell;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ForumView extends Fragment {
    RecyclerView rv;
    ArrayList listData;

    FirebaseAuth auth;
    MessageAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum, null);
        rv = (RecyclerView) view.findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        listData = new ArrayList<>();
        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference("messages");

        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        ListData2 l = npsnapshot.getValue(ListData2.class);
                        listData.add(l);
                    }

                    adapter = new MessageAdapter(listData);
                    rv.setAdapter(adapter);

                    String text = dataSnapshot.child("messageText").getValue(String.class);
                    String user= dataSnapshot.child("messageUser").getValue(String.class);
                    Log.d("TAG", text + " / " + user);
               //     adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        FloatingActionButton fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                View v=getView();
                EditText input = getView().findViewById(R.id.data);

                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database
                FirebaseDatabase.getInstance()
                        .getReference().child("messages")
                        .push()
                        .setValue(new ListData2(input.getText().toString() ));
                // Clear the input
                input.setText("");
                listData = new ArrayList<>();

                final DatabaseReference nm = FirebaseDatabase.getInstance().getReference("messages");

                nm.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                                ListData2 l = npsnapshot.getValue(ListData2.class);

                                listData.add(l);
                            }

                            adapter = new MessageAdapter(listData);
                            rv.setAdapter(adapter);

                            String text = dataSnapshot.child("messageText").getValue(String.class);
                            String user= dataSnapshot.child("messageUser").getValue(String.class);
                            Log.d("TAG", text + " / " + user);
                            adapter.notifyDataSetChanged();

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }


        });
        return view;
    }

}