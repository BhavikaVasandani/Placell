package com.project.placementcell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DisplayStudent extends AppCompatActivity {
    String uid;
    String name,email,DOB,phone,gender,dept,usn,marks1,marks2,bachlor1,religion,rank,fatherName,fatherPhone,fatherEmail,motherName,motherPhone,motherEmail;
    EditText name1,email1,DOB1,phone1,usn1,gender1,dept1,edit10,edit12,bachlor,editReligion,editRank,editFatherName,editFatherPhone,editFatherEmail,editMotherName,editMotherPhone,editMotherEmail;

    Button stuContinue,stuContinue1;
    RelativeLayout first_layout,second_layout,third_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_student);

        name1= findViewById(R.id.editName);
        email1=findViewById(R.id.editEmail);
        usn1=findViewById(R.id.editUSN);
        phone1=findViewById(R.id.editPhone);
        gender1=findViewById(R.id.gender);
        DOB1=findViewById(R.id.editDOB);
        dept1=findViewById(R.id.stu_dept);

        first_layout = findViewById(R.id.first_layout);
        second_layout = findViewById(R.id.second_layout);
        third_layout = findViewById(R.id.third_layout);
        edit10=findViewById(R.id.edit10Marks);
        edit12=findViewById(R.id.edit12Marks);
        bachlor=findViewById(R.id.editBachlorsMarks);
        editReligion=findViewById(R.id.editReligion);
        editRank=findViewById(R.id.editRank);
        editFatherName=findViewById(R.id.editFatherName);
        editFatherPhone=findViewById(R.id.editFatherPhone);
        editFatherEmail=findViewById(R.id.editFatherEmail);
        editMotherName=findViewById(R.id.editMotherName);
        editMotherPhone=findViewById(R.id.editMotherPhone);
        editMotherEmail=findViewById(R.id.editMotherEmail);


        uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference().child("student").child(uid);
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                name=dataSnapshot.child("Name").getValue(String.class);
                name1.setText(name);

                email=dataSnapshot.child("Email").getValue(String.class);
                email1.setText(email);

                usn=dataSnapshot.child("USN").getValue(String.class);
                usn1.setText(usn);

                phone=dataSnapshot.child("Phone").getValue(String.class);
                phone1.setText(phone);

                gender=dataSnapshot.child("Gender").getValue(String.class);
                gender1.setText(gender);

                DOB=dataSnapshot.child("DOB").getValue(String.class);
                DOB1.setText(DOB);

                dept=dataSnapshot.child("Department").getValue(String.class);
                dept1.setText(dept);

                marks1=dataSnapshot.child("marks1").getValue(String.class);
                edit10.setText(marks1);

                marks2=dataSnapshot.child("marks2").getValue(String.class);
                edit12.setText(marks2);

                bachlor1=dataSnapshot.child("bachlor").getValue(String.class);
                bachlor.setText(bachlor1);

                religion=dataSnapshot.child("religion").getValue(String.class);
                editReligion.setText(religion);

                rank=dataSnapshot.child("rank").getValue(String.class);
                editRank.setText(rank);

                fatherName=dataSnapshot.child("fatherName").getValue(String.class);
                editFatherName.setText(fatherName);

                fatherEmail=dataSnapshot.child("fatherEmail").getValue(String.class);
                editFatherEmail.setText(fatherEmail);

                fatherPhone=dataSnapshot.child("fatherPhone").getValue(String.class);
                editFatherPhone.setText(fatherPhone);

                motherName=dataSnapshot.child("motherName").getValue(String.class);
                editMotherName.setText(motherName);

                motherPhone=dataSnapshot.child("motherPhone").getValue(String.class);
                editMotherPhone.setText(motherPhone);

                motherEmail=dataSnapshot.child("motherEmail").getValue(String.class);
                editMotherEmail.setText(motherEmail);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}