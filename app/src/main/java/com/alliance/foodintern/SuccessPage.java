package com.alliance.foodintern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SuccessPage extends AppCompatActivity {
    TextView finalAddress,grandTotalPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_page);
        finalAddress=findViewById(R.id.finalLocation);
        grandTotalPrice=findViewById(R.id.finalAmount);
        finalAddress.setText(getIntent().getStringExtra("finalAddress"));
    }
}
