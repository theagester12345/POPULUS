package com.chris.mcdelait.myapplication;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
 import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
 import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



   // private SectionsPagerAdapter mSectionsPagerAdapter;
public   ArrayList<String> getDetails;
//public  String housenumber;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    //    mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

     String  housenumber=getIntent().getExtras().getString("housenum");
        mViewPager = (ViewPager) findViewById(R.id.container);
      //  mViewPager.setAdapter(mSectionsPagerAdapter);

        Toast.makeText(getBaseContext(),housenumber,Toast.LENGTH_LONG).show();



    }



/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
       public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }


         * Returns a new instance of this fragment for the given section
         * number.

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();

            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            return rootView;
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
 switch (position) {
     case 0:
         return HouseHold.newInstance();

     case 1:
         return PageOne.newInstance();

     case 2:
         return  pageTwo.newInstance();

     case 3:
         return pageThree.newInstance();

     case 4:
         return Main2Activity.newInstance();

     case 5:
         return Fertility.newInstance();

     case 6:
         return AgricultureFrag.newInstance();

     case 7:
         return HouseholdConditions.newInstance();

     case 8:
         return HouseholdContinue.newInstance();

     case 9:
     return PageFour.newInstance();
 }
     return  null;
        }

        @Override
        public int getCount() {
             return 10;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";


            }
            return null;
        }

    } */
}
