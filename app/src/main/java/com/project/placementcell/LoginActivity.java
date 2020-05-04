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

public class LoginActivity extends AppCompatActivity {
    EditText email, pwd;
    Button login;
    TextView barcode;
    String pass, mail;
    TextView register,forgot_pwd;
    FirebaseAuth auth;
    public static String UID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        barcode = findViewById(R.id.barcode);
        email = findViewById(R.id.userEmail);
        pwd = findViewById(R.id.userPwd);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        auth = FirebaseAuth.getInstance();
        forgot_pwd = findViewById(R.id.forgotPass);

        barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, BarcodeActivity.class));

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, Register.class));
            }
        });

        if (auth.getCurrentUser() != null) {
            UID = auth.getCurrentUser().getUid();
            startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("UID", UID));
            finish();
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass = pwd.getText().toString().trim();
                mail = email.getText().toString().trim();

                String emailPattern = "[a-zA-Z0-9._-]+@[r][v][c][e]+\\.+[e][d][u]+\\.[i][n]";

                if(mail.matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(),"Valid Email Address",Toast.LENGTH_SHORT).show();

                    auth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid Email Address", Toast.LENGTH_SHORT).show();
                }
            }
        });

        forgot_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ResetPasswordActivity.class));
            }
        });

    }
}