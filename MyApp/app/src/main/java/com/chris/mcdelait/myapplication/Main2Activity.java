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
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;


public class Main2Activity extends AppCompatActivity {
String mainhouse[],housecode,pageone[],pagetwo[],pagethree[];
    RadioGroup employed,disabled;
    Spinner emp_status,emp_sector;
     EditText product;

    CheckBox sight,hearing,physical, emotional,intellect,speech;
    RadioButton emplyed,disabld;
 ImageButton imageButton;
    String employed_,disabled_,empstatus,empsector
            ,product_,speech_,sight_,physical_,
            intellect_,emotional_,
            hearing_;
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        employed=(RadioGroup)findViewById(R.id.employed);
        disabled=(RadioGroup)findViewById(R.id.disabled);
        emp_status=(Spinner)findViewById(R.id.emp_status);
        emp_sector=(Spinner)findViewById(R.id.emp_sector);
        product=(EditText)findViewById(R.id.product);

        sight=(CheckBox)findViewById(R.id.sight);
        hearing=(CheckBox)findViewById(R.id.hearing);
        physical=(CheckBox)findViewById(R.id.physical);
        emotional=(CheckBox)findViewById(R.id.emotional);
        intellect=(CheckBox)findViewById(R.id.intellect);
        speech=(CheckBox)findViewById(R.id.speech);

        try {

            mainhouse=getIntent().getStringArrayExtra("mainhouse");
            housecode=getIntent().getStringExtra("housecode");
            pageone=getIntent().getStringArrayExtra("pageone");
            pagetwo=getIntent().getStringArrayExtra("pagetwo");
            pagethree=getIntent().getStringArrayExtra("pagethree");

         }catch (Exception e){

        }

imageButton=(ImageButton)findViewById(R.id.main2next);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int empl,disabl;


                try{
                    empl=employed.getCheckedRadioButtonId();
                    disabl=disabled.getCheckedRadioButtonId();
                    emplyed=(RadioButton)findViewById(empl);
                    disabld=(RadioButton)findViewById(disabl);

                    employed_=emplyed.getText().toString();
                    disabled_=disabld.getText().toString();


                }catch (Exception e){

                }
                empstatus=emp_status.getSelectedItem().toString();
                empsector=emp_sector.getSelectedItem().toString();
                product_=product.getText().toString();
                if(speech.isChecked())
                    speech_=speech.getText().toString();
                if(hearing.isChecked())
                    hearing_=hearing.getText().toString();
                if(sight.isChecked())
                    sight_=sight.getText().toString();
                if(physical.isChecked())
                    physical_=physical.getText().toString();
                if(intellect.isChecked())
                    intellect_=intellect.getText().toString();
                if(emotional.isChecked())
                    emotional_=emotional.getText().toString();

                String maintwo[]={employed_,empstatus,empsector,product_,disabled_,sight_
                        ,hearing_,speech_,physical_,intellect_,emotional_};

                Bundle bundle=new Bundle();
                bundle.putString("housecode",housecode);
                bundle.putStringArray("mainhouse",mainhouse);
                bundle.putStringArray("pageone",pageone);
                bundle.putStringArray("pagetwo",pagetwo);
                bundle.putStringArray("pagethree",pagethree);
                bundle.putStringArray("maintwo",maintwo);

                Intent intent=new Intent(Main2Activity.this,Fertility.class);
                intent.putExtras(bundle);

                startActivity(intent);

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.navigate,menu);
        try {
            MenuItem item = menu.findItem(R.id.disabinfo);
            item.setVisible(false);
        }catch (Exception e){
            Toast.makeText(Main2Activity.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.agricdetails:
                startActivity(new Intent(Main2Activity.this,AgricultureFrag.class));break;
            case R.id.fertinfo:
                startActivity(new Intent(Main2Activity.this,Fertility.class));break;

            case R.id.disabinfo:
                startActivity(new Intent(Main2Activity.this,Main2Activity.class));break;

            case R.id.tribeInfo:
                startActivity(new Intent(Main2Activity.this,pageThree.class));break;

            case R.id.basicInfo:
                startActivity(new Intent(Main2Activity.this,pageTwo.class));break;

            case R.id.householdI:
                startActivity(new Intent(Main2Activity.this,HouseholdConditions.class));break;
            case R.id.householdII:
                startActivity(new Intent(Main2Activity.this,HouseholdContinue.class));break;
            case R.id.nameAndGender:
                startActivity(new Intent(getBaseContext(),PageOne.class)); break;

            default:
                startActivity(new Intent(getBaseContext(),PageFour.class));

                break;
        }
        return  true;
    }
}


