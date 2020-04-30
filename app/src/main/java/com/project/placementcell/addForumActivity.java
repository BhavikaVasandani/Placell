package com.project.placementcell;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class addForumActivity extends AppCompatActivity {
    CardView interview, placed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_forum);
        placed = findViewById(R.id.placed);
        interview = findViewById(R.id.interview);

        placed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addForumActivity.this, addForumData.class);
                intent.putExtra("forumCategory", "placed");
                startActivity(intent);
            }
        });
        interview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addForumActivity.this, addForumData.class);
                intent.putExtra("forumCategory", "interview");

                startActivity(intent);
            }
        });
    }
}
