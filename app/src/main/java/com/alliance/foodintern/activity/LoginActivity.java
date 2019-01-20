package com.alliance.foodintern.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
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

public class LoginActivity extends AppCompatActivity {
    EditText password, phone;
    Button signIn;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        password= findViewById(R.id.password);
        phone= findViewById(R.id.phone);

        signIn=  findViewById(R.id.button);

        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("User");

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Please wait");
                progressDialog.show();

                databaseReference.addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        progressDialog.dismiss();

                        if(dataSnapshot.child(phone.getText().toString()).exists()) {


                            User user = dataSnapshot.child(phone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(password.getText().toString())) {
                                Toast.makeText(LoginActivity.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent( LoginActivity.this, MainActivity.class);
                                startActivity(intent);

                            } else
                                Toast.makeText(LoginActivity.this, "failed to sign in", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(LoginActivity.this,"NO user exist Sign UP First",Toast.LENGTH_SHORT).show();

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