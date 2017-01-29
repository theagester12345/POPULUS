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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.security.spec.ECField;


public class AgricultureFrag extends AppCompatActivity {
ImageButton imageButton;

    CheckBox cropFarming,treeGrowing,livestockRearing,fishFarming;
    EditText livestockReared,fishReared;
    Spinner croppingType;

    String fert[],maintwo[],pagethree[],pagetwo[],pageone[],housecode,mainhouse[];
    String cropfarming,treegrowing,livestockrearing,fishfarming,croppingtype,livestockreared,fishreared;

    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_agriculture);
        cropFarming=(CheckBox)findViewById(R.id.cropFarming);
        treeGrowing=( CheckBox)findViewById(R.id.treeGrowing);
        livestockRearing=(CheckBox)findViewById(R.id.livestockRearing);
        fishFarming=(CheckBox)findViewById(R.id.fishFarming);
        croppingType=(Spinner)findViewById(R.id.croppingType);
        livestockReared=(EditText)findViewById(R.id.livestockReared);
        fishReared=(EditText)findViewById(R.id.fishReared);

        try{
            fert=getIntent().getStringArrayExtra("fertility");
            maintwo=getIntent().getStringArrayExtra("maintwo");
            housecode=getIntent().getStringExtra("housecode");
            mainhouse=getIntent().getStringArrayExtra("mainhouse");
            pageone=getIntent().getStringArrayExtra("pageone");
            pagetwo=getIntent().getStringArrayExtra("pagetwo");
            pagethree=getIntent().getStringArrayExtra("pagethree");


 }catch (Exception f){

}
imageButton=(ImageButton)findViewById(R.id.agricNext);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cropFarming.isChecked())
                cropfarming=cropFarming.getText().toString();
                if(treeGrowing.isChecked())
                    treegrowing=treeGrowing.getText().toString();
                if(livestockRearing.isChecked()) {
                     livestockrearing = livestockRearing.getText().toString();
                    livestockRearing.setEnabled(true);
                 }
                else{
                    livestockReared.setEnabled(false);
                 }
                if(fishFarming.isChecked()){
                     fishfarming=fishFarming.getText().toString();

                }

                     croppingtype=croppingType.getSelectedItem().toString();

                fishreared=fishReared.getText().toString();
                livestockreared=livestockReared.getText().toString();


                String agric[]={cropfarming,treegrowing,livestockrearing,fishfarming,croppingtype,
                livestockreared,fishreared};

                Bundle bundle=new Bundle();
                bundle.putString("housecode",housecode);
                bundle.putStringArray("mainhouse",mainhouse);
                bundle.putStringArray("pageone",pageone);
                bundle.putStringArray("pagetwo",pagetwo);
                bundle.putStringArray("pagethree",pagethree);
                bundle.putStringArray("maintwo",maintwo);
                bundle.putStringArray("fertility",fert);
                bundle.putStringArray("agric",agric);

                Intent ints=new Intent(AgricultureFrag.this,HouseholdConditions.class);
                ints.putExtras(bundle);
                startActivity(ints);

             }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.navigate,menu);
        try {
            MenuItem item = menu.findItem(R.id.agricdetails);
            item.setVisible(false);
        }catch (Exception e){
            Toast.makeText(AgricultureFrag.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.fertinfo:
                startActivity(new Intent(AgricultureFrag.this,Fertility.class));break;

            case R.id.disabinfo:
                startActivity(new Intent(AgricultureFrag.this,Main2Activity.class));break;

            case R.id.tribeInfo:
                startActivity(new Intent(AgricultureFrag.this,pageThree.class));break;

            case R.id.basicInfo:
                startActivity(new Intent(AgricultureFrag.this,pageTwo.class));break;

            case R.id.householdI:
                startActivity(new Intent(AgricultureFrag.this,HouseholdConditions.class));break;
            case R.id.householdII:
                startActivity(new Intent(AgricultureFrag.this,HouseholdContinue.class));break;
            case R.id.nameAndGender:
                startActivity(new Intent(getBaseContext(),PageOne.class)); break;

            default:
                try {
                    startActivity(new Intent(getBaseContext(), PageFour.class));
                }catch (Exception e){
                    startActivity(new Intent(getBaseContext(), PageFour.class));

                }
                break;
        }
        return  true;
    }
}


