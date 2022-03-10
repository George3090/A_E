package com.example.aesthetics_enginers.User_Account;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.aesthetics_enginers.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.aesthetics_enginers.Models.User;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;


import android.os.Bundle;

public class Register extends AppCompatActivity {
    private TextView Logo;
    private EditText UserFullname,UserPhoneNumber,UserAge,UserFirstLineOfAddress,UserEmail,UserPasswordReg;
    private ProgressBar progressBar;
    private Button RegisterUser;
    FirebaseAuth fAuth;
    private FirebaseFirestore mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Logo = (TextView)findViewById(R.id.Logo);
        RegisterUser = (Button) findViewById(R.id.RegisterUser);
        UserFullname = (EditText)findViewById(R.id.UserFullname);
        UserPhoneNumber = (EditText)findViewById(R.id.UserPhoneNumber);
        UserAge = (EditText)findViewById(R.id.UserAge);
        UserFirstLineOfAddress = (EditText)findViewById(R.id.UserFirstLineOfAddress);
        UserEmail = (EditText)findViewById(R.id.UserEmail);
        UserPasswordReg = (EditText)findViewById(R.id.UserPasswordReg);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        fAuth = FirebaseAuth.getInstance();
        mDb = FirebaseFirestore.getInstance();


        Logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });


        RegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email, Password, FullName, PhoneNr, Age, FirstLineOfAddress;
                Email = String.valueOf(UserEmail.getText());
                Password = String.valueOf(UserPasswordReg.getText());
                FullName = String.valueOf(UserFullname.getText());
                PhoneNr = String.valueOf(UserPhoneNumber.getText());
                Age = String.valueOf(UserAge.getText());
                FirstLineOfAddress = String.valueOf(UserFirstLineOfAddress.getText());
                if(FullName.isEmpty()){
                    UserFullname.setError("Full Name is required");
                    return;
                }
                if(Email.isEmpty()){
                    UserEmail.setError("Email is required");
                    return;
                }
                if(Password.isEmpty()){
                    UserPasswordReg.setError("Password is required");
                    return;
                }
                if(PhoneNr.isEmpty()){
                    UserPhoneNumber.setError("Phone Nr  is required");
                    return;
                }
                if(Age.isEmpty()){
                    UserAge.setError("Age is required");
                    return;
                }
                if(FirstLineOfAddress.isEmpty()){
                    UserFirstLineOfAddress.setError("Address is required");
                    return;
                }
                else {
                    registerNewEmail(Email, Password, Age, FullName, PhoneNr);
                }

                /**
                 * Register a new email and password to Firebase Authentication
                 * @param email
                 * @param password
                 */

            }

            private void registerNewEmail(String Email, String Password, String Age, String Fullname, String PhoneNr) {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                                if (task.isSuccessful()){
                                    // Log.d(TAG, "onComplete: AuthState: " + FirebaseAuth.getInstance().getCurrentUser().getUid());

                                    //insert some default data
                                    User user = new User();
                                    user.setEmail(Email);
                                    user.setAge(Age);
                                    user.setFullName(Fullname);
                                    user.setPassword(Password);
                                    user.setPhoneNr(PhoneNr);
                                    user.setUsername(Email);
                                    user.setUsername(Email.substring(0, Email.indexOf("@")));
                                    user.setUser_id(FirebaseAuth.getInstance().getUid());

                                    FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                                            //.setTimestampsInSnapshotsEnabled(true)
                                            .build();
                                    mDb.setFirestoreSettings(settings);

                                    DocumentReference newUserRef = mDb
                                            .collection("collection_users")
                                            .document(FirebaseAuth.getInstance().getUid());

                                    newUserRef.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {


                                            if(task.isSuccessful()){
                                                Intent intent = new Intent(Register.this, Login.class);
                                                startActivity(intent);
                                                finish();
                                            }else{
                                                View parentLayout = findViewById(android.R.id.content);
                                                Snackbar.make(parentLayout, "Something went wrong.", Snackbar.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                                }
                                else {
                                    View parentLayout = findViewById(android.R.id.content);
                                    Snackbar.make(parentLayout, "Something went wrong.", Snackbar.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });


            }
        });

    }
}