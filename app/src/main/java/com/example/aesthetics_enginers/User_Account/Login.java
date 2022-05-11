package com.example.aesthetics_enginers.User_Account;

import androidx.appcompat.app.AppCompatActivity;
import com.example.aesthetics_enginers.MainActivity;
import com.example.aesthetics_enginers.R;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.example.aesthetics_enginers.Models.User;
import com.example.aesthetics_enginers.R;
import com.example.aesthetics_enginers.Utility.UserClient;




public class   Login extends AppCompatActivity implements View.OnClickListener {

    private TextView register, ForgotPassword;
    EditText UserEmailAddress, UserPassword;
    Button LoginButton;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton = findViewById(R.id.LoginButton);
        UserEmailAddress= findViewById(R.id.UserEmailAddress);
        UserPassword= findViewById(R.id.UserPassword);
        progressBar = findViewById(R.id.progressBar);
        firebaseAuth = FirebaseAuth.getInstance();
        setupFirebaseAuth();


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Extract and validate
                String email =UserEmailAddress.getText().toString();
                String password = UserPassword.getText().toString();
                if(!email.equals("") && !password.equals("")){
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                            hideDialog();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Login.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                            hideDialog();

                        }
                    });}

            }
        });

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);
        ForgotPassword = (TextView) findViewById(R.id.UserForgotPassword);
        ForgotPassword.setOnClickListener(this);
    }

    /*
     Firebase
       */
    private void setupFirebaseAuth(){
       // Log.d(TAG, "setupFirebaseAuth: started.");

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(Login.this, "Authenticated with: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                            .build();
                    db.setFirestoreSettings(settings);
                    DocumentReference userRef = db.collection("collection_users")
                            .document(user.getUid());
                    userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful()){
                                // Log.d(TAG, "onComplete: successfully set the user client.");
                                User user = task.getResult().toObject(User.class);
                                ((UserClient)(getApplicationContext())).setUser(user);
                            }
                        }
                    });

                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();

                } else {

                }

            }
        };
    }


    private void showDialog(){
        progressBar.setVisibility(View.VISIBLE);

    }

    private void hideDialog(){
        if(progressBar.getVisibility() == View.VISIBLE){
            progressBar.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.register:
                startActivity(new Intent(this, Register.class));
                break;

            case R.id.UserForgotPassword:
                startActivity(new Intent(this, ForgotPassword.class));
                break;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(firebaseAuthListener);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (firebaseAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(firebaseAuthListener);
        }

    }
}