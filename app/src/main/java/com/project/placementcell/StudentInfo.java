package com.project.placementcell;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class StudentInfo extends AppCompatActivity {
    Button stuContinue,stuContinue1,stuContinue2;
    private RelativeLayout first_layout, second_layout, third_layout;
    String name1,email1,dob1,phone1,gender,dept1,usn1,marks1,marks2,bachlor1,religion,rank,fatherName,fatherPhone,fatherEmail,motherName,motherPhone,motherEmail;
    private EditText name,email,dob,phone,usn,edit10,edit12,bachlor,editReligion,editRank,editFatherName,editFatherPhone,editFatherEmail,editMotherName,editMotherPhone,editMotherEmail;
    FirebaseAuth auth;
    DatabaseReference reference;
    int mYear,mMonth,mDay;
    RadioGroup radioGroup;
    AppCompatSpinner dept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        stuContinue = findViewById(R.id.stuContinue);
        stuContinue1 = findViewById(R.id.stuContinue1);
        stuContinue2 = findViewById(R.id.stuContinue2);

        first_layout = findViewById(R.id.first_layout);
        second_layout = findViewById(R.id.second_layout);
        third_layout = findViewById(R.id.third_layout);
        second_layout.setVisibility(View.GONE);
        third_layout.setVisibility(View.GONE);
        name = findViewById(R.id.editName);
        dob = findViewById(R.id.editDOB);
        email = findViewById(R.id.editEmail);
        usn = findViewById(R.id.editUSN);
        phone = findViewById(R.id.editphone);
        dept=findViewById(R.id.stu_dept);
        usn=findViewById(R.id.edit10Marks);
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
        first_layout.setVisibility(View.VISIBLE);
        dept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            dept1= parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
             }
        });
            radioGroup = findViewById(R.id.groupradio);
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group,int checkedId) {
                             RadioButton radioButton = group.findViewById(checkedId);
                    }
                });

                    final ViewGroup transitionsContainerfirst = (ViewGroup) first_layout;
        TransitionManager.beginDelayedTransition(transitionsContainerfirst);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(StudentInfo.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                dob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                dob1=dob.getText().toString();

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        stuContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first_layout.setVisibility(View.GONE);
                third_layout.setVisibility(View.GONE);
                second_layout.setVisibility(View.VISIBLE);
            }
        });
        stuContinue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first_layout.setVisibility(View.GONE);
                second_layout.setVisibility(View.GONE);
                third_layout.setVisibility(View.VISIBLE);

            }
        });

        stuContinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name1=name.getText().toString().trim();
                dob1=dob.getText().toString().trim();
                email1=email.getText().toString().trim();
                phone1=phone.getText().toString().trim();
                marks1=edit10.getText().toString().trim();
                marks2=edit12.getText().toString().trim();
                usn1=usn.getText().toString().trim();
                bachlor1=bachlor.getText().toString().trim();
                religion=editReligion.getText().toString().trim();
                rank=editRank.getText().toString().trim();
                fatherName=editFatherName.getText().toString().trim();
                fatherPhone=editFatherPhone.getText().toString().trim();
                fatherEmail=editFatherEmail.getText().toString().trim();
                motherName=editMotherName.getText().toString().trim();
                motherPhone=editMotherPhone.getText().toString().trim();
                motherEmail=editMotherEmail.getText().toString().trim();
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton= (RadioButton)radioGroup.findViewById(selectedId);
                gender=radioButton.getText().toString();

                //  String uid = auth.getCurrentUser().getUid();
                HashMap<String, Object> data = new HashMap<>();

                auth = FirebaseAuth.getInstance();
                reference = FirebaseDatabase.getInstance().getReference().child("user").child(new LoginActivity().UID);
                data.put("Name", name1);
              data.put("Email", email1);
                data.put("USN",usn1);
                data.put("DOB",dob1);
                data.put("Phone",phone1);
                data.put("Department",dept1);
                data.put("Gender",gender);
                data.put("marks1",marks1);
                data.put("marks2",marks2);
                data.put("bachlor",bachlor1);
                data.put("religion",religion);
                data.put("rank",rank);
                data.put("fatherName",fatherName);
                data.put("fatherPhone",fatherPhone);
                data.put("fatherEmail",fatherEmail);
                data.put("motherName",motherName);
                data.put("motherPhone",motherPhone);
                data.put("motherEmail",motherEmail);
                FirebaseDatabase.getInstance().getReference().child(new LoginActivity().UID);
                reference.updateChildren(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                         if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Data updated", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    });
    }
}
