package com.alliance.foodintern.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.alliance.foodintern.R;
import com.alliance.foodintern.adapter.SqlDataBaseAdapter;

public class CartActivity extends AppCompatActivity {
    TextView mfoodName,mdes,mdiscount,mtotalPrice,mnoOfItmes,morderID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mfoodName=findViewById(R.id.foodName);
        mdes=findViewById(R.id.desc);
        mdiscount=findViewById(R.id.discount);
        mtotalPrice=findViewById(R.id.totalPrice);
        mnoOfItmes=findViewById(R.id.noOfItems);
        morderID=findViewById(R.id.orderid);

        SqlDataBaseAdapter db = new SqlDataBaseAdapter(getApplication());
        db.open();
        Cursor c = db.retrieve();
        if (c.moveToFirst())
        {
            do {
                Display(c);
            } while (c.moveToNext());
        }
        db.close();
    }

    private void Display(Cursor c)
    {
        morderID.setText(c.getString(0));
        mfoodName.setText(c.getString(1));
        mdes.setText(c.getString(2));
        mdiscount.setText(c.getString(3));
        mtotalPrice.setText(c.getString(4));
        mnoOfItmes.setText(c.getString(5));
    }
}
