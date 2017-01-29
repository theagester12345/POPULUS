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
import android.widget.Toast;


public class Fertility extends AppCompatActivity {
ImageButton imageButton;

    String maintwo[], housecode,mainhouse[],pageone[],pagetwo[],pagethree[];

    EditText maleBorn,femaleBorn,maleSurviving,femaleSurviving,maleDead,femaleDead;

    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fertility);

        maleBorn=(EditText)findViewById(R.id.maleBorn);
        maleSurviving=(EditText)findViewById(R.id.maleSurviving);
        maleDead=(EditText)findViewById(R.id.maleDead);
        femaleBorn=(EditText)findViewById(R.id.femaleBorn);
        femaleSurviving=(EditText)findViewById(R.id.femaleSurviving);
        femaleDead=(EditText)findViewById(R.id.femaleDead);



        try{
         maintwo=getIntent().getStringArrayExtra("maintwo") ;
            mainhouse=getIntent().getStringArrayExtra("mainhouse");
            housecode=getIntent().getStringExtra("housecode");
             pageone=getIntent().getStringArrayExtra("pageone");
            pagetwo=getIntent().getStringArrayExtra("pagetwo");
            pagethree=getIntent().getStringArrayExtra("pagethree");


         }catch (Exception e){

        }
imageButton=(ImageButton)findViewById(R.id.fertNext);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String maleborn=maleBorn.getText().toString(),malesurviving=maleSurviving.getText().toString(),
                        maledead=maleDead.getText().toString(),femaleborn=femaleBorn.getText().toString(),
                        femalesurviving=femaleSurviving.getText().toString(),
                        femaledead=femaleDead.getText().toString();

                String fertility[]={maleborn,malesurviving,maledead,femaleborn,femalesurviving,femaledead};

                Bundle bundle=new Bundle();

                bundle.putStringArray("mainhouse",mainhouse);;
                bundle.putString("housecode",housecode);
                bundle.putStringArray("pageone",pageone);
                bundle.putStringArray("pagetwo",pagetwo);
                bundle.putStringArray("pagethree",pagethree);
                bundle.putStringArray("maintwo",maintwo);
                bundle.putStringArray("fertility",fertility);

           Intent ints=(new Intent(Fertility.this,AgricultureFrag.class));
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
            MenuItem item = menu.findItem(R.id.fertinfo);
            item.setVisible(false);
        }catch (Exception e){
            Toast.makeText(Fertility.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.agricdetails:
                startActivity(new Intent(Fertility.this,AgricultureFrag.class));break;

            case R.id.disabinfo:
                startActivity(new Intent(Fertility.this,Main2Activity.class));break;

            case R.id.tribeInfo:
                startActivity(new Intent(Fertility.this,pageThree.class));break;

            case R.id.basicInfo:
                startActivity(new Intent(Fertility.this,pageTwo.class));break;

            case R.id.householdI:
                startActivity(new Intent(Fertility.this,HouseholdConditions.class));break;
            case R.id.householdII:
                startActivity(new Intent(Fertility.this,HouseholdContinue.class));break;
            case R.id.nameAndGender:
                startActivity(new Intent(getBaseContext(),PageOne.class)); break;

            default:
                startActivity(new Intent(getBaseContext(),PageFour.class));

                break;
        }
        return  true;
    }
}

