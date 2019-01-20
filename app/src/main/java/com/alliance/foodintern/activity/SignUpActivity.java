package com.alliance.foodintern.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alliance.foodintern.R;
import com.alliance.foodintern.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    EditText name, phone, password;
    Button signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name= findViewById(R.id.name);
        phone= findViewById(R.id.phone);
        password= findViewById(R.id.password);
        signUp= findViewById(R.id.sign_up);
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("User");

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this);
                progressDialog.setMessage("Please wait");
                progressDialog.show();
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(phone.getText().toString()).exists()) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"already registered",Toast.LENGTH_SHORT).show();
                        }else{

                            progressDialog.dismiss();
                            User user= new User(name.getText().toString(),password.getText().toString());
                            databaseReference.child(phone.getText().toString()).setValue(user);
                            Toast.makeText(SignUpActivity.this,"Registered",Toast.LENGTH_SHORT);
                            Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });




    }




}
