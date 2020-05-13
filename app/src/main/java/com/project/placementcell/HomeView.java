package com.project.placementcell;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeView extends Fragment {
    String uid,type;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        CardView companyCard = view.findViewById(R.id.company);
        CardView forumCard = view.findViewById(R.id.addForum);
        final CardView insertCompany = view.findViewById(R.id.insertCompany);
        CardView displayStudent=view.findViewById(R.id.displayStudent);
        uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference().child("student").child(uid);
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                type = dataSnapshot.child("type").getValue(String.class);
                if(type.equalsIgnoreCase("student"))
                {
                    insertCompany.setVisibility(View.INVISIBLE);
                    insertCompany.setEnabled(false);
                }
                Log.d("TAG", type);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        companyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Company.class));
            }
        });
        forumCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), addForumActivity.class));
            }
        });
        insertCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), InsertCompany.class));
            }
        });
        displayStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Student.class));
            }
        });
        return view;

    }
}