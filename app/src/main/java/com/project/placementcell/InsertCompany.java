package com.project.placementcell;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class InsertCompany extends AppCompatActivity {
    FirebaseAuth auth;
    DatabaseReference reference;
    int mYear,mMonth,mDay;
    Button submit;
    EditText companyName,companyEmail,companyDescription,companyVacancies,companyPlaced,companyDate;
    String companyName1,companyEmail1,companyDescription1,companyVacancies1,companyPlaced1,companyDate1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_company);
        companyName=findViewById(R.id.companyName);
        companyEmail=findViewById(R.id.companyEmail);
        companyDescription=findViewById(R.id.companyDescription);
        companyVacancies=findViewById(R.id.companyVacancies);
        companyPlaced=findViewById(R.id.companyPlaced);
        companyDate=findViewById(R.id.companyDate);
        submit=findViewById(R.id.submit);
        companyDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(InsertCompany.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                companyDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                companyDate1=companyDate.getText().toString();

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyName1=companyName.getText().toString().trim();
                companyEmail1=companyEmail.getText().toString().trim();
                companyDescription1=companyDescription.getText().toString().trim();
                companyPlaced1=companyPlaced.getText().toString().trim();
                companyVacancies1=companyVacancies.getText().toString().trim();
                companyDate1=companyDate.getText().toString().trim();
                HashMap<String, Object> data = new HashMap<>();

                auth = FirebaseAuth.getInstance();
                data.put("name",companyName1);
                data.put("Email",companyEmail1);
                data.put("description",companyDescription1);
                data.put("Vacancies",companyVacancies1);
                data.put("Placed",companyPlaced1);
                data.put("Date",companyDate1);
                reference = FirebaseDatabase.getInstance().getReference().child("data").child(companyName1);

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
