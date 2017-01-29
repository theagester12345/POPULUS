package com.chris.mcdelait.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;


public class HouseholdConditions extends AppCompatActivity {

ImageButton imageButton;
    String agric[],pageone[],mainhouse[],housecode,pagetwo[],pagethree[],maintwo[],
            fertility[];
    Spinner dwelling_type,roofing,lightSource,outer_wall,water_source;
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_household_conditions);
dwelling_type=(Spinner)findViewById(R.id.dwelling_type);
        roofing=(Spinner)findViewById(R.id.roofing);
        lightSource=(Spinner)findViewById(R.id.lightSource);
        outer_wall=(Spinner)findViewById(R.id.outer_wall);
        water_source=(Spinner)findViewById(R.id.water_source);
        try {
            agric = getIntent().getStringArrayExtra("agric");
            mainhouse=getIntent().getStringArrayExtra("mainhouse");
            housecode=getIntent().getStringExtra("housecode");
            pageone=getIntent().getStringArrayExtra("pageone");
            pagetwo=getIntent().getStringArrayExtra("pagetwo");
            pagethree=getIntent().getStringArrayExtra("pagethree");
            maintwo=getIntent().getStringArrayExtra("maintwo");
            fertility=getIntent().getStringArrayExtra("fertility");

         }catch (Exception e){

        }

imageButton=(ImageButton)findViewById(R.id.condNext);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                  String dwelling,roof,light,wall,water;
                    dwelling=dwelling_type.getSelectedItem().toString();
                    roof=roofing.getSelectedItem().toString();
                    light=lightSource.getSelectedItem().toString();
                    wall=outer_wall.getSelectedItem().toString();
                    water=water_source.getSelectedItem().toString();

                    String householdconditions[]={dwelling,roof,light,wall,water};


                    Bundle bundle=new Bundle();

                    bundle.putString("housecode",housecode);
                    bundle.putStringArray("mainhouse",mainhouse);
                    bundle.putStringArray("pageone",pageone);
                    bundle.putStringArray("pagetwo",pagetwo);
                    bundle.putStringArray("pagethree",pagethree);
                    bundle.putStringArray("maintwo",maintwo);
                    bundle.putStringArray("fertility",fertility);
                    bundle.putStringArray("agric",agric);
                    bundle.putStringArray("householdconditions",householdconditions);

                    Intent ints =new Intent(HouseholdConditions.this,HouseholdContinue.class);
                    ints.putExtras(bundle);
                    startActivity(ints);

                }catch (Exception e){

                }

             }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.navigate,menu);
        try {
            MenuItem item = menu.findItem(R.id.householdI);
            item.setVisible(false);
        }catch (Exception e){
            Toast.makeText(HouseholdConditions.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.agricdetails:
                startActivity(new Intent(HouseholdConditions.this,AgricultureFrag.class));break;
            case R.id.fertinfo:
                startActivity(new Intent(HouseholdConditions.this,Fertility.class));break;

            case R.id.disabinfo:
                startActivity(new Intent(HouseholdConditions.this,Main2Activity.class));break;

            case R.id.tribeInfo:
                startActivity(new Intent(HouseholdConditions.this,pageThree.class));break;

            case R.id.basicInfo:
                startActivity(new Intent(HouseholdConditions.this,pageTwo.class));break;


            case R.id.householdII:
                startActivity(new Intent(HouseholdConditions.this,HouseholdContinue.class));break;
            case R.id.nameAndGender:
                startActivity(new Intent(getBaseContext(),PageOne.class)); break;

            default:
                startActivity(new Intent(getBaseContext(),PageFour.class));

                break;
        }
        return  true;
    }
}


