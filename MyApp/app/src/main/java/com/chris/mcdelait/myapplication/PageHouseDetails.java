package com.chris.mcdelait.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.ArrayList;

public class PageHouseDetails extends AppCompatActivity {
    Button saveDetails;
    EditText town,area,houseNum;
    Spinner region;
    Firebase fbase;
    public String houseName;
    AutoCompleteTextView district;
    ArrayAdapter <String> adapter;

    String[] districtName=
    { "Adansi North District",
            "Adansi South District",
            "Afigya-Kwabre District",
            "Ahafo Ano North District",
            "Ahafo Ano South District",
            "Amansie Central District",
            "Amansie West District",
            "Asante Akim Central Municipal District",
            "Asante Akim North District",
            "Asante Akim South District",
            "Asokore Mampong Municipal District",
            "Atwima Kwanwoma District",
            "Atwima Mponua District",
            "Atwima Nwabiagya District",
            "Bekwai Municipal District",
            "Bosome Freho District",
            "Botsomtwe District",
            "Ejisu-Juaben Municipal District",
            "Ejura - Sekyedumase District",
            "Kumasi Metropolitan District",
            "Kumawu District",
            "Kwabre East District",
            "Mampong Municipal District",
            "Obuasi Municipal District",
            "Offinso North District",
            "Offinso South Municipal District",
            "Sekyere Afram Plains District",
            "Sekyere Central District",
            "Sekyere East District",
            "Sekyere South District",
            "Asunafo North Municipal District",
            "Asunafo South District",
            "Asutifi District",
            "Asutifi South District",
            "Atebubu-Amantin District",
            "Banda District",
            "Berekum Municipal District",
            "Dormaa East District",
            "Dormaa Municipal District",
            "Dormaa West District",
            "Jaman North District",
            "Jaman South District",
            "Kintampo North Municipal District",
            "Kintampo South District",
            "Nkoranza North District",
            "Nkoranza South Municipal District",
            "Pru District",
            "Sene District",
            "Sene West District",
            "Sunyani Municipal District",
            "Sunyani West District",
            "Tain District",
            "Tano North District",
            "Tano South District",
            "Techiman Municipal District",
            "Techiman North District",
            "Wenchi Municipal District",
            "Abura/Asebu/Kwamankese District",
            "Agona East District",
            "Agona West Municipal District",
            "Ajumako/Enyan/Essiam District",
            "Asikuma/Odoben/Brakwa District",
            "Assin North Municipal District",
            "Assin South District",
            "Awutu-Senya District",
            "Awutu Senya East District",
            "Cape Coast Metropolitan District",
            "Effutu Municipal District",
            "Ekumfi District",
            "Gomoa East District",
            "Gomoa West District",
            "Komenda/Edina/Eguafo/Abirem Municipal District",
            "Mfantsiman Municipal District",
            "Twifo-Ati Mokwa District",
            "Twifo/Heman/Lower Denkyira District",
            "Upper Denkyira East Municipal District",
            "Upper Denkyira West District",
            "Akuapim South District",
            "Afram Plains South District",
            "Akuapim North District",
            "Akuapim South Municipal District",
            "Akyemansa District",
            "Asuogyaman District",
            "Ayensuano District",
            "Atiwa District",
            "Birim Central Municipal District",
            "Birim North District",
            "Birim South District",
            "Denkyembour District",
            "East Akim Municipal District",
            "Fanteakwa District",
            "Kwaebibirem District",
            "Kwahu East District",
            "Kwahu North District",
            "Kwahu South District",
            "Kwahu West Municipal District",
            "Lower Manya Krobo District",
            "New-Juaben Municipal District",
            "Suhum/Kraboa/Coaltar District",
            "Upper Manya Krobo District",
            "Upper West Akim District",
            "West Akim Municipal District",
            "Yilo Krobo District",
            "Accra Metropolitan District",
            "Ada West District",
            "Adenta Municipal District",
            "Ashaiman Municipal District",
            "Dangme East District",
            "Dangme West District",
            "Ga Central District",
            "Ga East Municipal District",
            "Ga South Municipal District",
            "Ga West Municipal District",
            "Kpone Katamanso District",
            "La Dade Kotopon Municipal District",
            "La Nkwantanang Madina District",
            "Ledzokuku-Krowor Municipal District",
            "Ningo Prampram District",
            "Tema Metropolitan District",
            "Bole District",
            "Bunkpurugu-Yunyoo District",
            "Central Gonja District",
            "Chereponi District",
            "East Gonja District",
            "East Mamprusi District",
            "Gushegu District",
            "Karaga District",
            "Kpandai District",
            "Kumbungu District",
            "Mamprugo Moaduri District",
            "Mion District",
            "Nanumba North District",
            "Nanumba South District",
            "North Gonja District",
            "Saboba District",
            "Sagnarigu District",
            "Savelugu-Nanton District",
            "Sawla-Tuna-Kalba District",
            "Tamale Metropolitan District",
            "Tatale Sangule District",
            "Tolon District",
            "West Gonja District",
            "West Mamprusi District",
            "Yendi Municipal District",
            "Zabzugu District",
            "Bawku Municipal District",
            "Bawku West District",
            "Binduri District",
            "Bolgatanga Municipal District",
            "Bongo District",
            "Builsa District",
            "Builsa South District",
            "Garu-Tempane District",
            "Kassena Nankana East District",
            "Kassena Nankana West District",
            "Nabdam District",
            "Pusiga District",
            "Talensi District",
            "Daffiama Bussie Issa District",
            "Jirapa District",
            "Lambussie Karni District",
            "Lawra District",
            "Nadowli District",
            "Nandom District",
            "Sissala East District",
            "Sissala West District",
            "Wa East District",
            "Wa Municipal District",
            "Wa West District",
            "Adaklu District",
            "Afadjato South District",
            "Agotime Ziope District",
            "Akatsi North District",
            "Akatsi South District",
            "Biakoye District",
            "Central Tongu District",
            "Ho Municipal District",
            "Ho West District",
            "Hohoe Municipal District",
            "Jasikan District",
            "Kadjebi District",
            "Keta Municipal District",
            "Ketu North District",
            "Ketu South Municipal District",
            "Kpando Municipal District",
            "Krachi East District",
            "Krachi Nchumuru District",
            "Krachi West District",
            "Nkwanta North District",
            "Nkwanta South District",
            "North Dayi District",
            "North Tongu District",
            "South Dayi District",
            "South Tongu District",
            "Ahanta West District",
            "Aowin/Suaman District",
            "Bia District",
            "Bia East District",
            "Bibiani/Anhwiaso/Bekwai District",
            "Bodi District",
            "Ellembele District",
            "Jomoro District",
            "Juabeso District",
            "Mpohor District",
            "Mpohor/Wassa East District",
            "Nzema East Municipal District",
            "Prestea-Huni Valley District",
            "Sefwi Akontombra District",
            "Sefwi-Wiawso District",
            "Sekondi Takoradi Metropolitan Assembly",
            "Shama District",
            "Suaman District",
            "Tarkwa-Nsuaem Municipal District",
            "Wasa Amenfi East District",
            "Wasa Amenfi West District",
            "Wassa Amenfi Central District"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_house_details);

        fbase.setAndroidContext(this);
        fbase=new Firebase("https://fir-d-934c2.firebaseio.com/");


        district=(AutoCompleteTextView)findViewById(R.id.districts);
        saveDetails=(Button)findViewById(R.id.continueButton);
        region=(Spinner)findViewById(R.id.regionName);
        town=(EditText)findViewById(R.id.townName) ;
        area=(EditText)findViewById(R.id.areaName) ;
        houseNum=(EditText)findViewById(R.id.houseNumber) ;

        adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,districtName);
        district.setThreshold(1);
        district.setAdapter(adapter);


        saveDetails.setOnClickListener(new View.OnClickListener() {

        public  Firebase House,Reg,Hn,Twn,A_rea,dist;
             @Override
            public void onClick(View v) {

                     String HNum = houseNum.getText().toString();
                     String Area = area.getText().toString();
                     String Town = town.getText().toString();
                     String Region = region.getSelectedItem().toString();
                     String District = district.getText().toString();

try {
    Bundle bundle = new Bundle();
    bundle.putStringArray("details", new String[]{Region, District, Town, Area, HNum});
    Intent int_ = new Intent(PageHouseDetails.this, HouseHold.class);
    int_.putExtras(bundle);


    startActivity(int_);
}catch (Exception e){
    Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();
}

            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
