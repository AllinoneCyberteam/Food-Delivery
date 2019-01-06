package com.alliance.foodintern.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.alliance.foodintern.R;
import com.bumptech.glide.Glide;

public class DetailsfoodActivity extends AppCompatActivity
{
    int fixed;
    int total_price;
    ImageView mImageView;
    TextView mfoodname,mdes,mdis,mprice,price_total,mText;
    Button mButton;
    SlidingDrawer mSlidingDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsfood);
        fixed=Integer.parseInt(getIntent().getStringExtra("price_of_food"));

        mImageView= findViewById(R.id.img);
        mfoodname=findViewById(R.id.textView1);
        mdes=findViewById(R.id.textView2);
        mdis=findViewById(R.id.textView3);
        mprice=findViewById(R.id.text4);
        price_total=findViewById(R.id.total_amount);
        mText=findViewById(R.id.number_of_items);
        mButton=findViewById(R.id.inc);
        mSlidingDrawer=findViewById(R.id.SlidingDrawer);
        mSlidingDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened()
            {
                price_total.setText(getString(R.string.rupee_symbol)+fixed);
            }
        });


        display();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int max=10;
                String price=mText.getText().toString();
                int count=Integer.parseInt(price);
                if(count<max)
                {
                    count++;
                    total_price = fixed*count;
                    mText.setText(String.valueOf(count));
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Reached Maximum Limit! ", Toast.LENGTH_SHORT).show();
                }
                price_total.setText(getString(R.string.rupee_symbol)+total_price);
            }
        });
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

    public void decrement(View view)
    {
        int min=1;
        String price=mText.getText().toString();
        int count=Integer.parseInt(price);
        if(count>min)
        {
            count--;
            total_price -= fixed;
            mText.setText(String.valueOf(count));
        }
        else
        {
            Toast.makeText(this, "Keep atleast one Item! ", Toast.LENGTH_SHORT).show();
        }

        price_total.setText(getString(R.string.rupee_symbol)+total_price);

    }
}
