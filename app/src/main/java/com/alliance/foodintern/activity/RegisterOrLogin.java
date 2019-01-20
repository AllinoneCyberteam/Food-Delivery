package com.alliance.foodintern.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alliance.foodintern.R;

public class RegisterOrLogin extends AppCompatActivity {

    Button btnSignUp, btnLogIn;
    TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnLogIn=findViewById(R.id.login);
        btnSignUp= findViewById(R.id.sign_up);
        skip= findViewById(R.id.textView);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(RegisterOrLogin.this,SignUpActivity.class);
                startActivity(intent);
            }
        });


        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RegisterOrLogin.this,LoginActivity.class);
                startActivity(intent);
            }
        });


        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RegisterOrLogin.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }




}
