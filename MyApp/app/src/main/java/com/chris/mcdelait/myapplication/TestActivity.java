package com.chris.mcdelait.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class TestActivity extends AppCompatActivity {
Firebase fbase;
    EditText testName;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Firebase.setAndroidContext(this);
        fbase=new Firebase("https://fir-d-934c2.firebaseio.com/");
        testName=(EditText)findViewById(R.id.name);
        findViewById(R.id.testButton).setOnClickListener(new View.OnClickListener() {
            String getName=testName.getText().toString();
            @Override
            public void onClick(View v) {

                Firebase dbChild=fbase.child("Region");
                dbChild.setValue("Chris");
            }
        });
    }
}
