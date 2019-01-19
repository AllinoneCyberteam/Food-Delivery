package com.alliance.foodintern.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alliance.foodintern.R;
import com.alliance.foodintern.activity.MainActivity;

public class SuccessPage extends AppCompatActivity {
    TextView finalAddress,grandTotalPrice,home;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_page);
        finalAddress=findViewById(R.id.finalLocation);
        grandTotalPrice=findViewById(R.id.finalAmount);
        home=findViewById(R.id.home_button);
        grandTotalPrice.setText(getString(R.string.rupee_symbol)+getIntent().getStringExtra("totalAmount"));
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });
        if (getIntent().getStringExtra("finalAddress")!=null) {
            finalAddress.setText(getIntent().getStringExtra("finalAddress"));
        }else {
            finalAddress.setText(getIntent().getStringExtra("flat") + "," + getIntent().getStringExtra("area") + "," + getIntent().getStringExtra("landmark") + "," + getIntent().getStringExtra("city") + "," + getIntent().getStringExtra("pin"));
        }
    }
}
