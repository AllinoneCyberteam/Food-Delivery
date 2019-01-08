package com.alliance.foodintern.fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alliance.foodintern.R;
import com.alliance.foodintern.adapter.CardItemAdapter;
import com.alliance.foodintern.adapter.SqlDataBaseAdapter;
import com.alliance.foodintern.model.CardItem;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoificationFragment extends Fragment {
    RecyclerView mCardRecycler;
    ArrayList<CardItem> mCursorList;
    CardItemAdapter mCardItemAdapter;
    TextView st,dis,tax,mtotal;
    Button order;



    public NoificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_noification, container, false);
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
                int discount=Integer.valueOf(c.getString(3));
                discount_tot+=discount;
                count++;
                int temp=Integer.valueOf(price.substring(1));
                total+=temp;
                item.setFood_price(price);
                item.setFood_name(c.getString(1));
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
