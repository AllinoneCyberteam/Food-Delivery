package com.alliance.foodintern.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import com.alliance.foodintern.R;
import com.bumptech.glide.Glide;

public class DetailsfoodActivity extends AppCompatActivity
{
    ImageView mImageView;
    TextView mfoodname,mdes,mdis,mprice;
   // Button mButton;
    SlidingDrawer mSlidingDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsfood);
        mImageView= findViewById(R.id.img);
        mfoodname=findViewById(R.id.textView1);
        mdes=findViewById(R.id.textView2);
        mdis=findViewById(R.id.textView3);
        mprice=findViewById(R.id.text4);
        //mButton=findViewById(R.id.add);
        mSlidingDrawer=findViewById(R.id.SlidingDrawer);
        mSlidingDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {

            }
        });


        display();

//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });




    }

    private void display()
    {
       String url=getIntent().getStringExtra("food_image");
        Glide.with(getApplicationContext()).load(url).into(mImageView);
        mfoodname.setText(getIntent().getStringExtra("food_name"));
        mdes.setText(getIntent().getStringExtra("details_of_food"));
        mdis.setText(getString(R.string.discount)+getIntent().getStringExtra("discount"));
        mprice.setText(getString(R.string.rupee_symbol)+getIntent().getStringExtra("price_of_food"));

    }
}
