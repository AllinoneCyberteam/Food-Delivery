package com.alliance.foodintern.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.alliance.foodintern.R;
import com.alliance.foodintern.fragment.DashBoardFragment;
import com.alliance.foodintern.fragment.FoodFragment;
import com.alliance.foodintern.fragment.HomeFragment;
import com.alliance.foodintern.fragment.CartFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar mtoolbar;
    HomeFragment mHomeFragment;
    FoodFragment mFoodFragment;
    DashBoardFragment mDashBoardFragment;
    CartFragment cartFragment;

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
                    getSupportActionBar().setTitle("Cart");
                    loadFragment(cartFragment);
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
        //requestPermission();
        mHomeFragment=new HomeFragment();
        mDashBoardFragment=new DashBoardFragment();
        mFoodFragment=new FoodFragment();
        cartFragment =new CartFragment();
        mtoolbar=findViewById(R.id.maintoolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("HOME");
        loadFragment(mHomeFragment);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getIntent().getStringExtra("key") != null) {
            loadFragment(cartFragment);
        }
    }
}
