package com.chris.mcdelait.myapplication;


 import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

 import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

 import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class HouseDetails extends AppCompatActivity {
    Button start;
    EditText houseId,town;
    Spinner region;

    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
DatabaseReference refRegion;
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);
        houseId=(EditText)findViewById(R.id.houseID);
        town=(EditText)findViewById(R.id.townOrVillage) ;
        region=(Spinner)findViewById(R.id.regionOfHouse);



          start=(Button)findViewById(R.id.startRecord);
          start.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View v) {
                 String houseID=houseId.getText().toString();
                 String townOrVillage=town.getText().toString();
                 String getRegion=region.getSelectedItem().toString();

                DatabaseReference refHouseId=myRef.child("HouseID");
                 refHouseId.setValue(houseID);
                 DatabaseReference refTown=myRef.child("Town'\'Village");
                 refTown.setValue(townOrVillage);
                 refRegion=myRef.child("Region");
                 refRegion.setValue(getRegion);
                  startActivity(new Intent(HouseDetails.this,MainActivity.class));


                finish();
            }
        });

        }
}
