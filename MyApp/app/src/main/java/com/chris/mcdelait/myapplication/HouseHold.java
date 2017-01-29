package com.chris.mcdelait.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.firebase.client.Firebase;


public class HouseHold extends AppCompatActivity {


    ImageButton img;
    String code;
    String s[];
    EditText householdCode;
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_house_hold);


         householdCode=(EditText)findViewById(R.id.householdCode) ;
        img=(ImageButton)findViewById(R.id.houseHold);

          Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking);
          img.startAnimation(animation);
try {
    s = getIntent().getExtras().getStringArray("details");
 }catch (Exception e){

}
         img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = householdCode.getText().toString();

                switch (code){
                    case "":
                        Toast.makeText(HouseHold.this,"Provide household code to continue ",Toast.LENGTH_SHORT).show();
                        break;
                        default:
                            Bundle b=new Bundle();
                            b.putString("householdCode",code);
                            b.putStringArray("mainHouseDetails",s);
                              Intent ints=new Intent(HouseHold.this,PageOne.class);
                              ints.putExtras(b);
                              startActivity(ints);
                }
            }
        });

    }
}
