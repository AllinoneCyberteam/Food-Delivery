package com.alliance.foodintern.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alliance.foodintern.R;
import com.alliance.foodintern.adapter.SqlDataBaseAdapter;
import com.alliance.foodintern.model.CardItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    EditText phone, address;
    Button button;
    String foods="";
    String totalPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        databaseReference = FirebaseDatabase.getInstance().getReference("orders");
        phone= findViewById(R.id.phone);
        address=findViewById(R.id.address);
        button=findViewById(R.id.order);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String phn,addrs;
                getData();
                phn=phone.getText().toString();
                addrs=address.getText().toString();
                DatabaseReference db=databaseReference.child(phn);
                db.child("phone").setValue(phn);
                db.child("foods").setValue(foods);
                db.child("address").setValue(addrs);


            }
        });





    }


    private void getData(){
        SqlDataBaseAdapter db = new SqlDataBaseAdapter(this);

        db.open();
        int total=0,grandtotal=0;
        int discount_tot=0;
        int count=0;
        Cursor c = db.retrieve();
        if (c.moveToFirst())
        {
            do {
               // CardItem item=new CardItem();
                String price=c.getString(4);
                int discount=Integer.valueOf(c.getString(3));
                discount_tot+=discount;
                count++;
                int temp=Integer.valueOf(price.substring(1));
                total+=temp;
                foods=foods+ ","+ c.getString(1);
             // item.setFood_price(price);
               // item.setFood_name();
               // mCursorList.add(item);

            } while (c.moveToNext());
            /*
            st.setText(getString(R.string.rupee_symbol)+total);
            dis.setText(getString(R.string.rupee_symbol)+total*discount_tot/count*100);
            tax.setText(getString(R.string.minus)+getString(R.string.rupee_symbol)+total*6/100);
            grandtotal=total-(total*discount_tot/count*100)+(total*6/100);

            mtotal.setText(getString(R.string.rupee_symbol)+grandtotal);
            mCardItemAdapter.notifyDataSetChanged();
        */
            totalPrice=""+grandtotal;
        }else{
          /*
          view.findViewById(R.id.rl_empty).setVisibility(View.VISIBLE);
        */
        }
        db.close();

    }


}
