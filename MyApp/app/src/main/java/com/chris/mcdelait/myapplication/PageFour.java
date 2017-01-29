package com.chris.mcdelait.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class PageFour extends AppCompatActivity {

   String DataParseUrl = "http://mcdelait.000webhostapp.com/trial.php";
   // String DataParseUrl = "http://10.5.1.110/trial.php";


    CheckInternetConnection connection;
    Button saveButton,continueButton;
   ImageButton Home;
    Firebase firebase;
    String agric[],pageone[],mainhouse[],housecode,pagetwo[],pagethree[],maintwo[],
            fertility[],householdconditions[],householdcontinue[];

RadioGroup havePhone,useInternet,have_computer;

    RadioButton havephone,useinternet,havecomputer;
    int Housecode;
    String region, district, town, area, housenumber;    //mainhouse data
    String fullname, gender;  //pageone data

    String tohead, region_, birth_date, nationality; //pagetwo data
    String religion, ethnicity, marital_status, literacy, schooled, education_level; //pagethree

    int age;
    String employed, disabled, emp_status, emp_sector, product, sight, hearing, speech,
            emotional, physical,
            intellect;                        //maintwo
    int maleborn, malesurviving, maledead, femaleborn, femalesurviving, femaledead; //Fertility and Mortality
    String cropfarming, treegrowing, livestockrearing, fishfarming, croppingtype,
            livestockreared, fishreared;//Agricultural Activity

    String dwelling, roofing, light, wall, water ,
      ownership, tenancy, toilet_facility, solid_waste, liquid_waste;  //Household Conditions

    String pcYesNo,internetYesNo,phoneYesNo; //pagefour

TextView v1;

    @Override
   protected void onCreate(Bundle savedInstanceState) {

 super.onCreate(savedInstanceState);
setContentView(R.layout.fragment_page_four);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.customview);
     //   View view=getSupportActionBar().getCustomView();



        final SQLiteDatabase db=openOrCreateDatabase("classes",MODE_PRIVATE,null);

        connection=new CheckInternetConnection();

         firebase.setAndroidContext(this);
        firebase=new Firebase("https://fir-d-934c2.firebaseio.com/");


        Home=(ImageButton)findViewById(R.id.homeButton) ;
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),NavActivity.class));
            }
        });
        havePhone=(RadioGroup)findViewById(R.id.have_phone);
        useInternet=(RadioGroup)findViewById(R.id.internet_facility);
        have_computer=(RadioGroup)findViewById(R.id.have_computer) ;

        try {
            v1=(TextView) findViewById(R.id.viewtextt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ;
saveButton=(Button)findViewById(R.id.saveButton);
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
            householdcontinue=getIntent().getStringArrayExtra("householdcontinue");

         }catch (Exception e){

        }

        try {
            //mainhouse data

            region = mainhouse[0];
            district = mainhouse[1];
            town = mainhouse[2];
            area = mainhouse[3];
            housenumber = mainhouse[4];

            //housecode
             Housecode = Integer.parseInt(housecode);

            //pageone data
            fullname = pageone[0];
            gender = pageone[1];

            //pagetwo : basic details
            tohead = pagetwo[0];
            region_ = pagetwo[1];
            birth_date = pagetwo[2];
            age = Integer.parseInt(pagetwo[3]);
            nationality = pagetwo[4];

            //pagethree data : Education ,Religion And Tribe page
            religion = pagethree[0];
            ethnicity = pagethree[1];
            marital_status = pagethree[2];
            literacy = pagethree[3];
            schooled = pagethree[4];
            education_level = pagethree[5];

            //maintwo data: Economic activity ,Disability details

            employed = maintwo[0];
            emp_status = maintwo[1];
            emp_sector = maintwo[2];
            product = maintwo[3];
            disabled = maintwo[4];
            sight = maintwo[5];
            hearing = maintwo[6];
            speech = maintwo[7];
            physical = maintwo[8];
            intellect = maintwo[9];
            emotional = maintwo[10];

            //Fertility ,mortality details
            maleborn = Integer.parseInt(fertility[0]);
            malesurviving = Integer.parseInt(fertility[1]);
            maledead = Integer.parseInt(fertility[2]);
            femaleborn = Integer.parseInt(fertility[3]);
            femalesurviving = Integer.parseInt(fertility[4]);
            femaledead = Integer.parseInt(fertility[5]);

            //Agricultural Activity
            cropfarming = agric[0];
            treegrowing = agric[1];
            livestockrearing = agric[2];
            fishfarming = agric[3];
            croppingtype = agric[4];
            livestockreared = agric[5];
            fishreared = agric[6];

            // Household conditions
            dwelling = householdconditions[0];
            roofing = householdconditions[1];
            light = householdconditions[2];
            wall = householdconditions[3];
            water = householdconditions[4];

            //Household Continued..
            ownership = householdcontinue[0];
            tenancy = householdcontinue[1];
            toilet_facility = householdcontinue[2];
            solid_waste = householdcontinue[3];
            liquid_waste = householdcontinue[4];

        }catch (Exception s){

        }
        saveButton.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View v) {
   try {
       ConnectivityManager connectivityManage=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
       NetworkInfo networkInfoactive=connectivityManage.getActiveNetworkInfo();

       if( networkInfoactive!=null&&networkInfoactive.isConnected())
           Toast.makeText(getBaseContext(),"Connected",Toast.LENGTH_LONG).show();
       else
           Toast.makeText(getBaseContext(),"No active internet connection ",Toast.LENGTH_LONG).show();

      }catch (Exception e){
       Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();
   }
        int phone,pc,internet;
        phone=havePhone.getCheckedRadioButtonId();
        pc=have_computer.getCheckedRadioButtonId();
        internet=have_computer.getCheckedRadioButtonId();

 try {
    havephone=(RadioButton)findViewById(phone);
    havecomputer=(RadioButton)findViewById(pc);
     useinternet=(RadioButton)findViewById(internet);


     pcYesNo=havecomputer.getText().toString();
     internetYesNo=useinternet.getText().toString();
     phoneYesNo=havephone.getText().toString();





 }catch (Exception s){

 }

        Toast.makeText(getBaseContext(),water,Toast.LENGTH_LONG).show();

          Firebase house,fromHousehold,thehouse,persondetails,person,hhold,education,work,disability,parts
                  ,childrenInfo,agric,communication,Ag_engaged,conditions;

   house=firebase.child("house");
  thehouse=house.child("houses").child("House "+town+" : "+area +" "+housenumber);
                 thehouse.child("house_number").setValue(housenumber);
                 thehouse.child("region").setValue(region);
        thehouse.child("district").setValue(district);
        thehouse.child("town").setValue(town);
           thehouse.child("area").setValue(area);
    fromHousehold=thehouse.push().child("household");

        hhold=fromHousehold.child("ID "+housecode)  ;
person=hhold.child("persons") ;
       persondetails= person.child("Person : "+fullname) ;
     persondetails.child("fullname").setValue(fullname);
     persondetails.child("gender").setValue(gender);
     persondetails.child("relation_to_head").setValue(tohead);
        persondetails.child("date_of_birth").setValue(birth_date);
        persondetails.child("age").setValue(age);
        persondetails.child("nationality").setValue(nationality);
        persondetails.child("religion").setValue(religion);
        persondetails.child("ethnicity").setValue(ethnicity);
        persondetails.child("marital_status").setValue(marital_status);
        persondetails.child("literacy/languages").setValue(literacy);

 education= persondetails.child("education") ;

       education.child("status").setValue(schooled);
        education.child("level").setValue(education_level);

  work=persondetails.child("work") ;
        work.child("working").setValue(employed);
        work.child("status").setValue(emp_status);
        work.child("sector").setValue(emp_sector);
  disability=persondetails.push().child("disability") ;
        disability.child("disabled").setValue(disabled);
     parts= disability.push().child("affected_parts").push();
        parts.child("sight").setValue(sight);
        parts.child("speech").setValue(speech);
        parts.child("intellect").setValue(intellect);
        parts.child("physical").setValue(physical);
        parts.child("hearing").setValue(hearing);
        parts.child("emotional").setValue(emotional);

  childrenInfo=persondetails.child("fertility_and_mortality") ;
        childrenInfo.child("male_born").setValue(maleborn);
        childrenInfo.child("male_surviving").setValue(malesurviving);
        childrenInfo.child("male_dead").setValue(maledead);
        childrenInfo.child("female_born").setValue(femaleborn);
        childrenInfo.child("female_surviving").setValue(femalesurviving);
        childrenInfo.child("female_dead").setValue(femaledead);

 agric=persondetails.child("agric_info").push();
      Ag_engaged=agric.push().child("Farming_engaged");
        Ag_engaged.child("crop").setValue(cropfarming);
        Ag_engaged.child("tree").setValue(treegrowing);
        Ag_engaged.child("livestock").setValue(livestockrearing);
        Ag_engaged.child("fish").setValue(fishfarming);
        agric.child("cropping_type").setValue(croppingtype);
        agric.child("livestock_reared").setValue(livestockreared);
        agric.child("fish_reared").setValue(fishreared);

    communication=persondetails.child("communication")  ;
        communication.child("internet_facility").setValue(internetYesNo);
        communication.child("have_phone").setValue(phoneYesNo);
        communication.child("have_laptop_or_desktop").setValue(pcYesNo);

    conditions =thehouse.push().child("household_conditions");
        conditions.child("dwelling_type").setValue(dwelling);
        conditions.child("roofing").setValue(roofing);
        conditions.child("source_of_light").setValue(light);
        conditions.child("building_material").setValue(wall);
        conditions.child("source_of_water").setValue(water);
        conditions.child("ownership").setValue(ownership);
        conditions.child("tenancy").setValue(tenancy);
        conditions.child("toilet_facility").setValue(toilet_facility);
        conditions.child("solid_waste").setValue(solid_waste);
        conditions.child("liquid_waste").setValue(liquid_waste);





try {
    db.execSQL("CREATE TABLE IF NOT EXISTS basicinfo (Name varchar(20),ID varchar(8))");
    db.execSQL("insert into basicinfo (Name,ID) values ('"+age+"','"+fullname+"')");


    Cursor c = db.rawQuery("SELECT * FROM basicinfo", null);
    c.moveToNext();
    v1.setText(c.getString(c.getColumnIndex("Name")));
    db.close();

}catch (Exception d){
    Toast.makeText(getBaseContext(),d.getMessage(), Toast.LENGTH_LONG).show();
}

        SendDataToServer();

    }
});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.navigate,menu);
try {
    MenuItem item = menu.findItem(R.id.lastpage);
    item.setVisible(false);
}catch (Exception e){
    Toast.makeText(PageFour.this,e.getMessage(),Toast.LENGTH_LONG).show();
}
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);
switch (item.getItemId()){
    case R.id.agricdetails:
        startActivity(new Intent(PageFour.this,AgricultureFrag.class));break;
    case R.id.fertinfo:
        startActivity(new Intent(PageFour.this,Fertility.class));break;

    case R.id.disabinfo:
        startActivity(new Intent(PageFour.this,Main2Activity.class));break;

    case R.id.tribeInfo:
        startActivity(new Intent(PageFour.this,pageThree.class));break;

    case R.id.basicInfo:
        startActivity(new Intent(PageFour.this,pageTwo.class));break;

    case R.id.nameAndGender:
        startActivity(new Intent(PageFour.this,PageOne.class));break;
    case R.id.householdI:
        startActivity(new Intent(PageFour.this,HouseholdConditions.class));break;
    case R.id.householdII:
        startActivity(new Intent(PageFour.this,HouseholdContinue.class));break;

    default:
         break;


}
        return  true;
    }


    public void SendDataToServer( ){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {



                List<NameValuePair> nameValuePairs = new ArrayList<>();

                nameValuePairs.add(new BasicNameValuePair("name", fullname));
                nameValuePairs.add(new BasicNameValuePair("email", String.valueOf(age)));
                nameValuePairs.add(new BasicNameValuePair("website", gender));

                //Add main house :
                nameValuePairs.add(new BasicNameValuePair("region",region));
                nameValuePairs.add(new BasicNameValuePair("district",district));
                nameValuePairs.add(new BasicNameValuePair("town",town));
                nameValuePairs.add(new BasicNameValuePair("area",area));
                nameValuePairs.add(new BasicNameValuePair("housenumber",housenumber));
               //Housecode
                nameValuePairs.add(new BasicNameValuePair("housecode",housecode));

                //page 1
                nameValuePairs.add(new BasicNameValuePair("fullname",fullname));
                nameValuePairs.add(new BasicNameValuePair("gender",gender));
               //page 2
                nameValuePairs.add(new BasicNameValuePair("tohead",tohead));
                nameValuePairs.add(new BasicNameValuePair("region_",region_));
                nameValuePairs.add(new BasicNameValuePair("birth_date",birth_date));
                nameValuePairs.add(new BasicNameValuePair("age", String.valueOf(age)));
                nameValuePairs.add(new BasicNameValuePair("nationality",nationality));

                //page 3
                nameValuePairs.add(new BasicNameValuePair("religion",religion));
                nameValuePairs.add(new BasicNameValuePair("ethnicity",ethnicity));
                nameValuePairs.add(new BasicNameValuePair("marital_status",marital_status));
                nameValuePairs.add(new BasicNameValuePair("literacy",literacy));
                nameValuePairs.add(new BasicNameValuePair("schooled",schooled));
                nameValuePairs.add(new BasicNameValuePair("education_level",education_level));

                //page 4 : maintwo
                nameValuePairs.add(new BasicNameValuePair("employed",employed));
                nameValuePairs.add(new BasicNameValuePair("emp_status",emp_status));
                nameValuePairs.add(new BasicNameValuePair("emp_sector",emp_sector));
                nameValuePairs.add(new BasicNameValuePair("product",product));
                nameValuePairs.add(new BasicNameValuePair("disabled",disabled));

                nameValuePairs.add(new BasicNameValuePair("sight",sight));
                nameValuePairs.add(new BasicNameValuePair("hearing",hearing));
                nameValuePairs.add(new BasicNameValuePair("speech",speech));
                nameValuePairs.add(new BasicNameValuePair("physical",physical));
                nameValuePairs.add(new BasicNameValuePair("intellect",intellect));
                nameValuePairs.add(new BasicNameValuePair("emotional",emotional));

                //page 5 : fertility
                nameValuePairs.add(new BasicNameValuePair("maleborn",String.valueOf(maleborn)));
                nameValuePairs.add(new BasicNameValuePair("malesurviving",String.valueOf(malesurviving)));
                nameValuePairs.add(new BasicNameValuePair("maledead",String.valueOf(maledead)));
                nameValuePairs.add(new BasicNameValuePair("femaleborn",String.valueOf(femaleborn)));
                nameValuePairs.add(new BasicNameValuePair("femalesurviving",String.valueOf(femalesurviving)));
                nameValuePairs.add(new BasicNameValuePair("femaledead",String.valueOf(femaledead)));

                //page 6: agric
                nameValuePairs.add(new BasicNameValuePair("cropfarming",cropfarming));
                nameValuePairs.add(new BasicNameValuePair("treegrowing",treegrowing));
                nameValuePairs.add(new BasicNameValuePair("livestockrearing",livestockrearing));
                nameValuePairs.add(new BasicNameValuePair("fishfarming",fishfarming));
                nameValuePairs.add(new BasicNameValuePair("croppingtype",croppingtype));
                nameValuePairs.add(new BasicNameValuePair("livestockreared",livestockreared));
                nameValuePairs.add(new BasicNameValuePair("fishreared",fishreared));

                // page 6 & 7 : household conditions
                nameValuePairs.add(new BasicNameValuePair("dwelling",dwelling));
                nameValuePairs.add(new BasicNameValuePair("roofing",roofing));
                nameValuePairs.add(new BasicNameValuePair("light",light));
                nameValuePairs.add(new BasicNameValuePair("wall",wall));
                nameValuePairs.add(new BasicNameValuePair("water",water));

                nameValuePairs.add(new BasicNameValuePair("ownership",ownership));
                nameValuePairs.add(new BasicNameValuePair("tenancy",tenancy));
                nameValuePairs.add(new BasicNameValuePair("toilet_facility",toilet_facility));
                nameValuePairs.add(new BasicNameValuePair("solid_waste",solid_waste));
                nameValuePairs.add(new BasicNameValuePair("liquid_waste",liquid_waste));

                //page 8 : communication
                nameValuePairs.add(new BasicNameValuePair("havePhone",phoneYesNo));
                nameValuePairs.add(new BasicNameValuePair("have_computer",pcYesNo));
                nameValuePairs.add(new BasicNameValuePair("useInternet",internetYesNo));



                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(DataParseUrl);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Submit Successfully";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(PageFour.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();
    }

}
