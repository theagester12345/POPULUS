package com.chris.mcdelait.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PageFive extends Fragment {

    public static PageFive newInstance(String param1, String param2) {
        PageFive fragment = new PageFive();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         return inflater.inflate(R.layout.fragment_page_five, container, false);
    }

}
