package com.project.placementcell;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register  extends AppCompatActivity {
    EditText email,pwd;
    Button signup, login;
    String pass,mail;
    TextView Already_account;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        email = findViewById(R.id.editEmail);
        pwd = findViewById(R.id.editPwd);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);
        Already_account = findViewById(R.id.text_view_login);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null){
            startActivity(new Intent(Register.this, Register.class));
        }


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass = pwd.getText().toString().trim();
                mail = email.getText().toString().trim();

                String emailPattern = "[a-zA-Z0-9._-]+@[r][v][c][e]+\\.+[e][d][u]+\\.[i][n]";

                if (mail.matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(),"Valid Email Address",Toast.LENGTH_SHORT).show();

                    auth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(Register.this,"Signup Successful",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Register.this, MainActivity.class));
                                finish();
                            }
                            else {
                                Toast.makeText(Register.this,"Signup Failed",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid Email Address", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Already_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,LoginActivity.class));

            }
        });
    }
}
