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
        mCursorList=new ArrayList<>();

        mCardItemAdapter=new CardItemAdapter(getContext(),mCursorList);
        mCardRecycler.setHasFixedSize(true);
        mCardRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mCardRecycler.setAdapter(mCardItemAdapter);


        db.open();
        Cursor c = db.retrieve();
        if (c.moveToFirst())
        {
            do {
                CardItem item=new CardItem();
                item.setFood_price(c.getString(4));
                item.setFood_name(c.getString(1));
                mCursorList.add(item);

            } while (c.moveToNext());
            mCardItemAdapter.notifyDataSetChanged();
        }else{
            view.findViewById(R.id.rl_empty).setVisibility(View.VISIBLE);
        }
        Log.d("TAG", "onCreateView: "+mCursorList);
        db.close();

        return view;
    }

}
