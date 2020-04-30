package com.project.placementcell;

import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class addForumData extends AppCompatActivity {
    private LinearLayout first_layout, second_layout, third_layout, fourth_Layout, fifth_Layout, sixth_Layout, seventh_Layout;
    RadioGroup radioSel;
    private ImageButton nextOne;
    private ImageButton nextTwo;
    private ImageButton nextThree;
    private ImageButton nextFour;
    private ImageButton nextFive;
    String option;
    private ProgressBar progressBar;

    private EditText verifiedNumber;
    private TextView UserPhoneNumber;

    private EditText userName;
    private ImageButton usernext;

    private RadioButton groupA,groupB,groupC,groupD;
    String BloodeGroup;
    String newString;

    private TextView UserNameFinal;
    private Button Request,groupContinue;
    private EditText Ques1,Ques2,Ques3,Ques4,Ques5,Ques6,Ques7;
    FirebaseAuth auth;
    DatabaseReference reference;
    String Ques1Str,Ques2Str,Ques3Str,Ques4Str,Ques5Str,Ques7Str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_forum_data);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("forumCategory");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("forumCategory");
        }


        first_layout = (LinearLayout) findViewById(R.id.first_layout);
        second_layout = (LinearLayout) findViewById(R.id.second_layout);
        third_layout = (LinearLayout) findViewById(R.id.third_layout);
        fourth_Layout = (LinearLayout) findViewById(R.id.forth_layout);
        fifth_Layout = (LinearLayout) findViewById(R.id.five_layout);
        sixth_Layout = (LinearLayout) findViewById(R.id.sixth_layout);
        seventh_Layout = (LinearLayout) findViewById(R.id.seventh_layout);
        Ques1=findViewById(R.id.Ques1);
        Ques2=findViewById(R.id.Ques2);
        Ques3=findViewById(R.id.Ques3);
        Ques4=findViewById(R.id.Ques4);
        Ques5=findViewById(R.id.Ques5);
        Ques7=findViewById(R.id.Ques7);

        radioSel=findViewById(R.id.radioSel);
        first_layout.setVisibility(View.VISIBLE);
        final ViewGroup transitionsContainerfirst = (ViewGroup) first_layout;
        TransitionManager.beginDelayedTransition(transitionsContainerfirst);

        nextOne = transitionsContainerfirst.findViewById(R.id.nextOne);
        nextOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first_layout.setVisibility(View.GONE);
                second_layout.setVisibility(View.VISIBLE);
            }
        });

        final ViewGroup transitionsContainersecond = (ViewGroup) second_layout;
        TransitionManager.beginDelayedTransition(transitionsContainersecond);

        nextTwo = (ImageButton) transitionsContainersecond.findViewById(R.id.nextTwo);

        nextTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                second_layout.setVisibility(View.GONE);
                third_layout.setVisibility(View.VISIBLE);
            }
        });

        final ViewGroup transitionsContainerthree = (ViewGroup) third_layout;
        TransitionManager.beginDelayedTransition(transitionsContainerfirst);

        nextThree = (ImageButton) transitionsContainerthree.findViewById(R.id.nextThree);
        nextThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                third_layout.setVisibility(View.GONE);
                fourth_Layout.setVisibility(View.VISIBLE);
            }
        });
        final ViewGroup transitionsContainerforth = (ViewGroup) fourth_Layout;
        TransitionManager.beginDelayedTransition(transitionsContainerfirst);

        nextFour = (ImageButton) transitionsContainerforth.findViewById(R.id.nextFour);
        nextFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fourth_Layout.setVisibility(View.GONE);
                fifth_Layout.setVisibility(View.VISIBLE);
            }
        });

        final ViewGroup transitionsContainerfifth = (ViewGroup) fifth_Layout;
        TransitionManager.beginDelayedTransition(transitionsContainerfifth);

        nextFive = (ImageButton) transitionsContainerfifth.findViewById(R.id.nextFive);
        nextFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fifth_Layout.setVisibility(View.GONE);
                sixth_Layout.setVisibility(View.VISIBLE);
            }
        });

        groupA = findViewById(R.id.groupA);
        groupB = findViewById(R.id.groupB);
        groupC = findViewById(R.id.groupC);
        groupD = findViewById(R.id.groupD);
        groupContinue =findViewById(R.id.groupContinue);

        groupA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupA.setBackground(getDrawable(R.drawable.create_back));
                groupA.setTextColor(getResources().getColor(R.color.whiteBodyColor));
                BloodeGroup = "A";
            }
        });
        groupB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupB.setBackground(getDrawable(R.drawable.create_back));
                groupB.setTextColor(getResources().getColor(R.color.whiteBodyColor));
                BloodeGroup = "B";
            }
        });

        groupC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupC.setBackground(getDrawable(R.drawable.create_back));
                groupC.setTextColor(getResources().getColor(R.color.whiteBodyColor));
                BloodeGroup = "C";
            }
        });

        groupD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupD.setBackground(getDrawable(R.drawable.create_back));
                groupD.setTextColor(getResources().getColor(R.color.whiteBodyColor));
                BloodeGroup = "O";
            }
        });

        groupContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sixth_Layout.setVisibility(View.GONE);
                seventh_Layout.setVisibility(View.VISIBLE);
            }
        });
        radioSel.clearCheck();
        radioSel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group,int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
            }
        });
        Request = (Button) findViewById(R.id.request);

        Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(addForumData.this, MainActivity.class));
                Ques1Str=Ques1.getText().toString();
                Ques2Str=Ques2.getText().toString();
                Ques3Str=Ques3.getText().toString();
                Ques4Str=Ques4.getText().toString();
                Ques5Str=Ques5.getText().toString();
                Ques7Str=Ques7.getText().toString();

                int selectedId = radioSel.getCheckedRadioButtonId();
                RadioButton radioButton= (RadioButton)radioSel.findViewById(selectedId);
                option=radioButton.getText().toString();

                //  String uid = auth.getCurrentUser().getUid();
                HashMap<String, Object> data = new HashMap<>();

                auth = FirebaseAuth.getInstance();
                reference = FirebaseDatabase.getInstance().getReference().child(newString).child(new LoginActivity().UID).child("Company");
                data.put("Ques1", Ques1Str);
                data.put("Ques2", Ques2Str);
                data.put("Ques3", Ques3Str);
                data.put("Ques4", Ques4Str);
                data.put("Ques5", Ques5Str);
                data.put("Ques6",option);
                data.put("Ques7", Ques7Str);

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