package com.alliance.foodintern.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alliance.foodintern.R;

public class ChangeLoactionActivity extends AppCompatActivity {


    EditText pinCode,flat,area,landmark,city;
    Button confirmLocation;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_loaction);
        pinCode=findViewById(R.id.pincode);
        flat=findViewById(R.id.flat);
        area=findViewById(R.id.area);
        landmark=findViewById(R.id.landmark);
        city=findViewById(R.id.city);
        confirmLocation=findViewById(R.id.confirm_location);

        confirmLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pins=pinCode.getText().toString();
                String flats=flat.getText().toString();
                String areas=area.getText().toString();
                String lm=landmark.getText().toString();
                String citys=city.getText().toString();
                if(!TextUtils.isEmpty(pins) && !TextUtils.isEmpty(flats) && !TextUtils.isEmpty(areas) && !TextUtils.isEmpty(lm) && !TextUtils.isEmpty(citys)) {
                    String finalAmount = getIntent().getStringExtra("totalAmount");

                    Intent intent = new Intent(getApplicationContext(), SuccessPage.class);
                    intent.putExtra("pin", pinCode.getText().toString());
                    intent.putExtra("flat", flat.getText().toString());
                    intent.putExtra("area", area.getText().toString());
                    intent.putExtra("landmark", landmark.getText().toString());
                    intent.putExtra("city", city.getText().toString());
                    intent.putExtra("totalAmount", finalAmount);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ChangeLoactionActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
