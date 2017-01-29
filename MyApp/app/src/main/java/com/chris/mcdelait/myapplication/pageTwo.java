package com.chris.mcdelait.myapplication;


import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class pageTwo extends AppCompatActivity {

    ImageButton img;

    String pagetwo[];
    Spinner toHead,region,nationality;
    EditText birth_date,age;

String  housedetails[],householdcode,pageonedetails[];
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_two);

        toHead=(Spinner)findViewById(R.id.toHead);
        region=(Spinner)findViewById(R.id.region);
        nationality=(Spinner)findViewById(R.id.nationality);
        birth_date=(EditText)findViewById(R.id.birth_date);
        age=(EditText)findViewById(R.id.age);


        img=(ImageButton)findViewById(R.id.twoNext);
        try {

            housedetails = getIntent().getStringArrayExtra("mainhouse");
            householdcode = getIntent().getStringExtra("HHcode");
            pageonedetails = getIntent().getStringArrayExtra("pageone");

        }catch (Exception e){

        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    String tohead, region_, nationality_, birth_date_, _age;
                    tohead = toHead.getSelectedItem().toString();
                    region_ = region.getSelectedItem().toString();
                    nationality_ = nationality.getSelectedItem().toString();
                    birth_date_ = birth_date.getText().toString();
                    _age = age.getText().toString();

                    String[] _pagetwo = {tohead, region_, birth_date_,_age, nationality_ };

                    Bundle b = new Bundle();
                    b.putStringArray("pageone", pageonedetails);
                    b.putStringArray("pagetwo", _pagetwo);
                    b.putString("HHcode", householdcode);
                    b.putStringArray("mainhouse", housedetails);
                    Intent intent = new Intent(pageTwo.this, pageThree.class);
                    intent.putExtras(b);
                    startActivity(intent);
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
            MenuItem item = menu.findItem(R.id.basicInfo);
            item.setVisible(false);
        }catch (Exception e){
            Toast.makeText(pageTwo.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.agricdetails:
                startActivity(new Intent(pageTwo.this,AgricultureFrag.class));break;
            case R.id.fertinfo:
                startActivity(new Intent(pageTwo.this,Fertility.class));break;

            case R.id.disabinfo:
                startActivity(new Intent(pageTwo.this,Main2Activity.class));break;

            case R.id.tribeInfo:
                startActivity(new Intent(pageTwo.this,pageThree.class));break;
            case R.id.nameAndGender:
                startActivity(new Intent(getBaseContext(),PageOne.class)); break;


            case R.id.householdI:
                startActivity(new Intent(pageTwo.this,HouseholdConditions.class));break;
            case R.id.householdII:
                startActivity(new Intent(pageTwo.this,HouseholdContinue.class));break;

            default:
                startActivity(new Intent(getBaseContext(),PageFour.class));
                 break;
        }
        return  true;
    }
}


