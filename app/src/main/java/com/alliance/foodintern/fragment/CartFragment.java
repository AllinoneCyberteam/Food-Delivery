package com.alliance.foodintern.fragment;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alliance.foodintern.MapsActivity;
import com.alliance.foodintern.R;
import com.alliance.foodintern.activity.OrderActivity;
import com.alliance.foodintern.adapter.CardItemAdapter;
import com.alliance.foodintern.adapter.SqlDataBaseAdapter;
import com.alliance.foodintern.model.CardItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */

public class CartFragment extends Fragment {
    RecyclerView mCardRecycler;
    ArrayList<CardItem> mCursorList;
    CardItemAdapter mCardItemAdapter;
    TextView st,dis,tax,mtotal;
    Button order;
    private static final int ERROR_REQUEST = 9001;
    private static final String TAG ="TAG" ;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public static final int MY_PERMISSIONS_REQUEST_COERSE = 98;


    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cart, container, false);
        SqlDataBaseAdapter db = new SqlDataBaseAdapter(view.getContext());
        mCardRecycler=view.findViewById(R.id.card_recycler);
        st=view.findViewById(R.id.subtt);
        dis=view.findViewById(R.id.discountt);
        tax=view.findViewById(R.id.ts);
        mtotal=view.findViewById(R.id.total_price);
        order=view.findViewById(R.id.order);

        mCursorList=new ArrayList<>();

        mCardItemAdapter=new CardItemAdapter(getContext(),mCursorList);
        mCardRecycler.setHasFixedSize(true);
        mCardRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mCardRecycler.setAdapter(mCardItemAdapter);

        order.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

               // getCurrentLocation();
                //Uri mapUri = Uri.parse("geo:0,0?q="+latitude+","+longitude);
                Intent intent= new Intent(getActivity(),OrderActivity.class);
                startActivity(intent);
            }





        });



        db.open();
        int total=0,grandtotal=0;
        int discount_tot=0;
        int count=0;

        Cursor c = db.retrieve();
        if (c.moveToFirst())
        {
            do {
                CardItem item=new CardItem();
                String price=c.getString(4);
                String image=c.getString(6);
                int discount=Integer.valueOf(c.getString(3));
                discount_tot+=discount;
                count++;
                int temp=Integer.valueOf(price.substring(1));
                total+=temp;
                item.setFood_price(price);
                item.setFood_name(c.getString(1));
                item.setImage(image);
                mCursorList.add(item);

            } while (c.moveToNext());
            st.setText(getString(R.string.rupee_symbol)+total);
            dis.setText(getString(R.string.rupee_symbol)+total*discount_tot/count*100);
            tax.setText(getString(R.string.minus)+getString(R.string.rupee_symbol)+total*6/100);
            grandtotal=total-(total*discount_tot/count*100)+(total*6/100);
            mtotal.setText(getString(R.string.rupee_symbol)+grandtotal);
            mCardItemAdapter.notifyDataSetChanged();
        }else{
            view.findViewById(R.id.rl_empty).setVisibility(View.VISIBLE);
        }
        db.close();

        return view;
    }

}
