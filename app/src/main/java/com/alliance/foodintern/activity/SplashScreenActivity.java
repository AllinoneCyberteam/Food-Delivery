package com.alliance.foodintern.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alliance.foodintern.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class SplashScreenActivity extends AppCompatActivity {

    boolean firstTime = false;
    private static int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        SharedPreferences prefs=getSharedPreferences("SharedPreferences", 0); // 0 - for private mode;
        SharedPreferences.Editor editor = prefs.edit();

        if (prefs.getBoolean("firstRun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            editor.putBoolean("firstRun", false).apply();
            firstTime = true;

        }



        PostDelayedMethod();
    }




    private void PostDelayedMethod()
    {



        new Handler().postDelayed(new Runnable() {


            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                // This method will be executed once the timer is over
                // Start your app main activity

                boolean InternetResult = checkConnection();
                if (InternetResult) {

                    //open Activity when internet is connected

                    if (firstTime) {

                        //TODO: creating the hint activity
                        Intent intent = new Intent(SplashScreenActivity.this, RegisterOrLogin.class);
                        startActivity(intent);

                    } else {
                        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    finish();

                } else {


                    /*spinner.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.GONE);
                     */

                    //Dialog Box show when internet is not connected
                    DialogAppear();


                }
            }
        }, SPLASH_TIME_OUT);
    }









    //DialogBox Main Function
    public void DialogAppear() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                SplashScreenActivity.this);
        builder.setTitle("Network Error");   //Title
        builder.setMessage("No Internet Connectivity");//Message
        builder.setCancelable(false);


        //Negative Message
        builder.setNegativeButton("Exit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {

                        /* close this activity
                         *  When Exit is clicked
                         */
                        finish();

                    }
                });

        //Positive Message
        builder.setPositiveButton("Retry",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {

                        //Check internet again when click on Retry by calling function
                        //  spinner.setVisibility(View.VISIBLE);

                        PostDelayedMethod();

                    }
                });
        builder.show();
    }



    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private boolean checkConnection() {
        if (isOnline()) {
            return true;
            //Toast.makeText(MainActivity.this, "You are connected to Internet", Toast.LENGTH_SHORT).show();
        } else {
            return false;
            // Toast.makeText(MainActivity.this, "You are not connected to Internet", Toast.LENGTH_SHORT).show();

        }

    }

}
