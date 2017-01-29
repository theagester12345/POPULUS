package com.chris.mcdelait.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
 import android.widget.Button;
import android.widget.EditText;
 import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity  {
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;

private  ProgressDialog progress;
    EditText email,password;
    Button signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signIn=(Button)findViewById(R.id.signIn);
        mAuth = FirebaseAuth.getInstance();
progress=new ProgressDialog(this);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        signIn.setOnClickListener(new OnClickListener() {

             @Override
            public void onClick(View v) {
                 String email_, password_;

                 email_ = email.getText().toString();
                 password_ = password.getText().toString();

                 if (TextUtils.isEmpty(password_) || TextUtils.isEmpty(email_)) {
                     Toast.makeText(LoginActivity.this, "Provide email and password to continue", Toast.LENGTH_SHORT).show();
                 }
else{


                     progress.setMessage("Please wait...");
                     progress.show();
                 mAuth.signInWithEmailAndPassword(email_, password_).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         Log.d("", "signInWithEmail:onComplete:" + task.isSuccessful());

                         if (!task.isSuccessful()) {
                             Toast.makeText(LoginActivity.this, "Failed to log in", Toast.LENGTH_SHORT).show();
                         } else {
                             startActivity(new Intent(LoginActivity.this, NavActivity.class));

                             finish();

                         }
                         progress.dismiss();
                         // ...

                     }
                 });
             }
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}










