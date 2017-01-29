package com.chris.mcdelait.myapplication;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Dangote on 1/19/2017.
 */

public class CheckInternetConnection  extends Activity{

    public void checkInternet(){
        ConnectivityManager connectivityManage=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfoactive=connectivityManage.getActiveNetworkInfo();

      if( networkInfoactive!=null&&networkInfoactive.isConnected())
          Toast.makeText(getBaseContext(),"Connected",Toast.LENGTH_LONG).show();
      else
          Toast.makeText(getBaseContext(),"No active internet connection ",Toast.LENGTH_LONG).show();

    }
}
