package com.alliance.foodintern.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.alliance.foodintern.R;
import com.alliance.foodintern.fragment.DashBoardFragment;
import com.alliance.foodintern.fragment.FoodFragment;
import com.alliance.foodintern.fragment.NoificationFragment;

import static com.alliance.foodintern.activity.SplashScreenActivity.HomeFragment.MY_PERMISSIONS_REQUEST_COERSE;
import static com.alliance.foodintern.activity.SplashScreenActivity.HomeFragment.MY_PERMISSIONS_REQUEST_LOCATION;

public class MainActivity extends AppCompatActivity {



    SharedPreferences pref;
    private Toolbar mtoolbar;
    SplashScreenActivity.HomeFragment mHomeFragment;
    FoodFragment mFoodFragment;
    DashBoardFragment mDashBoardFragment;
    NoificationFragment mNoificationFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportActionBar().setTitle("HOME");
                    loadFragment(mHomeFragment);

                    return true;
                case R.id.navigation_offer:
                    getSupportActionBar().setTitle("OFFERS");
                    loadFragment(mDashBoardFragment);


                    return true;
                case R.id.navigation_notifications:
                    getSupportActionBar().setTitle("NOTIFICATION");
                    loadFragment(mNoificationFragment);



                    return true;
                case R.id.food:
                    getSupportActionBar().setTitle("FOOD");
                    loadFragment(mFoodFragment);


                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();


        mHomeFragment=new SplashScreenActivity.HomeFragment();
        mDashBoardFragment=new DashBoardFragment();
        mFoodFragment=new FoodFragment();
        mNoificationFragment=new NoificationFragment();
        mtoolbar=findViewById(R.id.maintoolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("HOME");
        loadFragment(mHomeFragment);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
         pref=getSharedPreferences("pref", Context.MODE_PRIVATE);



        String data=pref.getString("START",null);
        if(data==null){
            Intent intent=new Intent(MainActivity.this,SplashScreenActivity.class);
            startActivity(intent);
            finish();
        }



    }

    private void requestPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_COERSE);
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestPermission();
                    }

                } else
                    requestPermission();
            }
        }
    }

            @Override
            protected void onStop() {
                super.onStop();
                pref.edit().remove("START").apply();

            }

}
