package com.chris.mcdelait.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread period=new Thread(){
            public void run(){

                try {

                    sleep(3000);
                    startActivity(new Intent(getBaseContext(),LoginActivity.class));
                    finish();
                }catch (Exception e){
                    Log.d("Thread failed","Failed to implement thread");
                }
            }
        };
        period.start();
    }
    }

