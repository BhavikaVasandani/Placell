package com.project.placementcell;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Home extends AppCompatActivity {

    Button add,disp;
    EditText sem, cls;
    String semStr, clsStr;
    FirebaseAuth auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cls = findViewById(R.id.clas);
        sem = findViewById(R.id.sem);
        add = findViewById(R.id.addData);
        disp = findViewById(R.id.display);

        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Data");
        if(auth.getCurrentUser() != null){
            add.setVisibility(View.VISIBLE);
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                semStr = sem.getText().toString();
                clsStr = cls.getText().toString();
                String uid = auth.getCurrentUser().getUid();

                HashMap<String, Object> data = new HashMap<>();

                data.put("semester", semStr);
                data.put("Class", clsStr);

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

        disp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  startActivity(new Intent(Home.this, Display.class));

            }
        });


    }


}
