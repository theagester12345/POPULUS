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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;


public class pageThree extends AppCompatActivity {
String details[],housecode,pageone[],pagetwo[];
ImageButton imageButton;
    String religion_,ethnic,marital,literacy_,schooled_,edu_level;

    Spinner religion,ethinicity,marital_status,literacy,education_level;
            RadioGroup schooled;
 RadioButton schooledOption;
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_page_three);
        religion=(Spinner)findViewById(R.id.religion);
        ethinicity=(Spinner)findViewById(R.id.ethnicity);
        marital_status=(Spinner)findViewById(R.id.marital_status);
        literacy=(Spinner)findViewById(R.id.literacy);
        schooled=(RadioGroup) findViewById(R.id.schooled);
        education_level=(Spinner)findViewById(R.id.education_level);


try {
     details = getIntent().getStringArrayExtra("mainhouse");
     housecode=getIntent().getStringExtra("HHcode");
    pageone=getIntent().getStringArrayExtra("pageone");
    pagetwo=getIntent().getStringArrayExtra("pagetwo");
 }
catch (Exception e){

}
imageButton=(ImageButton)findViewById(R.id.threeNext);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sch=schooled.getCheckedRadioButtonId();

                try {
                    schooledOption=(RadioButton)findViewById(sch);

                    schooled_=schooledOption.getText().toString();
                }catch (Exception e){

                }
                try {
                    religion_ = religion.getSelectedItem().toString();
                    ethnic = ethinicity.getSelectedItem().toString();
                    marital = marital_status.getSelectedItem().toString();
                    literacy_ = literacy.getSelectedItem().toString();
                    edu_level = education_level.getSelectedItem().toString();


                    String pagethreedetails[] = {religion_, ethnic, marital, literacy_,schooled_, edu_level};
                    Bundle bundle = new Bundle();
                    bundle.putString("housecode", housecode);
                    bundle.putStringArray("mainhouse", details);
                    bundle.putStringArray("pageone", pageone);
                    bundle.putStringArray("pagetwo", pagetwo);
                    bundle.putStringArray("pagethree", pagethreedetails);

                    Intent ints = new Intent(pageThree.this, Main2Activity.class);
                    ints.putExtras(bundle);
                    startActivity(ints);

                }
                catch (Exception e){

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
            MenuItem item = menu.findItem(R.id.tribeInfo);
            item.setVisible(false);
        }catch (Exception e){
            Toast.makeText(pageThree.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.agricdetails:
                startActivity(new Intent(pageThree.this,AgricultureFrag.class));break;
            case R.id.fertinfo:
                startActivity(new Intent(pageThree.this,Fertility.class));break;

            case R.id.disabinfo:
                startActivity(new Intent(pageThree.this,Main2Activity.class));break;

            case R.id.basicInfo:
                startActivity(new Intent(pageThree.this,pageTwo.class));
            case R.id.nameAndGender:
                startActivity(new Intent(getBaseContext(),PageOne.class)); break;


            case R.id.householdI:
                startActivity(new Intent(pageThree.this,HouseholdConditions.class));break;
            case R.id.householdII:
                startActivity(new Intent(pageThree.this,HouseholdContinue.class));break;

            default:
                startActivity(new Intent(getBaseContext(),PageFour.class));

                break;
        }
        return  true;
    }
}
