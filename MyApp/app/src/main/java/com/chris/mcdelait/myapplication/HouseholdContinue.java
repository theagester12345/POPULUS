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


public class HouseholdContinue extends AppCompatActivity {
ImageButton imageButton;
    Spinner ownership,tenancy,toilet_facility,liquid_waste,solid_waste;
    String agric[],pageone[],mainhouse[],housecode,pagetwo[],pagethree[],maintwo[],
            fertility[],householdconditions[];


    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_household_continue);

        ownership=(Spinner)findViewById(R.id.ownership);
        tenancy=(Spinner)findViewById(R.id.tenancy);
        toilet_facility=(Spinner)findViewById(R.id.toilet_facility);
        liquid_waste=(Spinner)findViewById(R.id.liquid_waste);
        solid_waste=(Spinner)findViewById(R.id.solid_waste);


    imageButton=(ImageButton)findViewById(R.id.continueNext);
        try{
            mainhouse=getIntent().getStringArrayExtra("mainhouse");
            housecode=getIntent().getStringExtra("housecode");
            pageone=getIntent().getStringArrayExtra("pageone");
            pagetwo=getIntent().getStringArrayExtra("pagetwo");
            pagethree=getIntent().getStringArrayExtra("pagethree");
            maintwo=getIntent().getStringArrayExtra("maintwo");
            fertility=getIntent().getStringArrayExtra("fertility");
            agric=getIntent().getStringArrayExtra("agric");
            householdconditions=getIntent().getStringArrayExtra("householdconditions");
         }catch (Exception e){

        }
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String owner,tenancy_,toilet,solid__waste,liquid_waste_;
                owner=ownership.getSelectedItem().toString();
                tenancy_=tenancy.getSelectedItem().toString();
                toilet=toilet_facility.getSelectedItem().toString();
                solid__waste=solid_waste.getSelectedItem().toString();
                liquid_waste_=liquid_waste.getSelectedItem().toString();

                String []householdcontinue={owner,tenancy_,toilet,solid__waste,liquid_waste_};

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
                bundle.putStringArray("householdcontinue",householdcontinue);

                Intent intent=new Intent(HouseholdContinue.this,PageFour.class);

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
            MenuItem item = menu.findItem(R.id.householdII);
            item.setVisible(false);
        }catch (Exception e){
            Toast.makeText(HouseholdContinue.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.agricdetails:
                startActivity(new Intent(HouseholdContinue.this,AgricultureFrag.class));break;
            case R.id.fertinfo:
                startActivity(new Intent(HouseholdContinue.this,Fertility.class));break;

            case R.id.disabinfo:
                startActivity(new Intent(HouseholdContinue.this,Main2Activity.class));break;

            case R.id.tribeInfo:
                startActivity(new Intent(HouseholdContinue.this,pageThree.class));break;

            case R.id.basicInfo:
                startActivity(new Intent(HouseholdContinue.this,pageTwo.class));break;

            case R.id.householdI:
                startActivity(new Intent(HouseholdContinue.this,HouseholdConditions.class));break;
            case R.id.householdII:
                startActivity(new Intent(HouseholdContinue.this,HouseholdContinue.class));break;
            case R.id.nameAndGender:
                startActivity(new Intent(getBaseContext(),PageOne.class)); break;

            default:
                startActivity(new Intent(getBaseContext(),PageFour.class));
                break;
        }
        return  true;
    }
}

 
