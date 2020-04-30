package com.project.placementcell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class placedStudentInfo extends AppCompatActivity {
    Button stuContinue;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_student_info);
        stuContinue = findViewById(R.id.stuContinue);
        category = getIntent().getStringExtra("forumCategory");
        Toast.makeText(placedStudentInfo.this, "Category: " + category, Toast.LENGTH_LONG).show();

        // add data in firebase under category.. placed or interview
        // in single java page you can differentiate category -> placed or interview

        // code will be
        // firebase database.child("forumData").child(category). --- add data

        stuContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(placedStudentInfo.this, addForumData.class));
            }
        });

    }
}
