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
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.Calendar;


public class PageOne extends AppCompatActivity {


    ImageButton img;
    RadioGroup gender;
    RadioButton getGender;
    EditText fullname;
    String mainhouse[];
    String householdcode;
    String fullname_,gender_;
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  setContentView(R.layout.fragment_page_one);
        fullname=(EditText)findViewById(R.id.fullname) ;
   gender=(RadioGroup)findViewById(R.id.gender);
        try {
            mainhouse=getIntent().getStringArrayExtra("mainHouseDetails");
            householdcode=getIntent().getStringExtra("householdCode");
         }catch (Exception e){

        }
    img=(ImageButton)findViewById(R.id.oneNext);
  ;
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected=gender.getCheckedRadioButtonId();
               try {
                   getGender = (RadioButton) findViewById(selected);
                   fullname_ = fullname.getText().toString();
                   gender_ = getGender.getText().toString();
                   String s = "" + selected;
               }catch (Exception s){

               }
                try {
                    String pageone[] = {fullname_, gender_};
                    Bundle b = new Bundle();
                    b.putString("HHcode", householdcode);
                    b.putStringArray("pageone", pageone);
                    b.putStringArray("mainhouse", mainhouse);
                    Intent ints = new Intent(PageOne.this, pageTwo.class);
                    ints.putExtras(b);
                    startActivity(ints);
                }catch (Exception e){
                    startActivity(new Intent(PageOne.this,pageTwo.class));
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
            MenuItem item = menu.findItem(R.id.nameAndGender);
            item.setVisible(false);
        }catch (Exception e){
            Toast.makeText(PageOne.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.agricdetails:
                startActivity(new Intent(PageOne.this,AgricultureFrag.class));break;
            case R.id.fertinfo:
                startActivity(new Intent(PageOne.this,Fertility.class));break;

            case R.id.disabinfo:
                try {
                    startActivity(new Intent(PageOne.this,Main2Activity.class));break;

                }catch (Exception e){
                    startActivity(new Intent(PageOne.this,Main2Activity.class));

                }

            case R.id.tribeInfo:
                startActivity(new Intent(PageOne.this,pageThree.class));break;

            case R.id.basicInfo:
                try {
                    startActivity(new Intent(PageOne.this,pageTwo.class));break;

                }catch (Exception e){
                    startActivity(new Intent(PageOne.this,pageTwo.class));

                }
            case R.id.householdI:
                startActivity(new Intent(PageOne.this,HouseholdConditions.class));break;
            case R.id.householdII:
                startActivity(new Intent(PageOne.this,HouseholdContinue.class));break;

            default:
                try {
                    startActivity(new Intent(PageOne.this, PageFour.class));
                } catch (Exception e){

                }
                break;
        }
        return  true;
    }
}
