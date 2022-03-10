package com.example.aesthetics_enginers.User_Account;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aesthetics_enginers.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPassword extends AppCompatActivity {


    EditText ResetPasswordEmail;
    Button ResetPasswordButton;
    FirebaseAuth firebaseAuth;
    TextView Logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        firebaseAuth = FirebaseAuth.getInstance();
        ResetPasswordEmail = findViewById(R.id.ResetPasswordEmail);
        ResetPasswordButton = findViewById(R.id.ResetPasswordButton);
        Logo = (TextView)findViewById(R.id.Logo);


        Logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });


        ResetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate Email
                if(ResetPasswordEmail.getText().toString().isEmpty()){
                    ResetPasswordEmail.setError("Email Required");
                    return;
                }
                // Send the reset link
                firebaseAuth.sendPasswordResetEmail(ResetPasswordEmail.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startActivity(new Intent(getApplicationContext(),Login.class));
                        finish();
                        Toast.makeText(ForgotPassword.this, "Reset Password Email Sent", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ForgotPassword.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });




            }
        });


    }
}