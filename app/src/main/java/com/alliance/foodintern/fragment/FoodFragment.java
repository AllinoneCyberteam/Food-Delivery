package com.alliance.foodintern.fragment;


import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alliance.foodintern.R;
import com.alliance.foodintern.adapter.FoodItemsAdapter;
import com.alliance.foodintern.model.FoodData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.support.v7.widget.TintTypedArray.obtainStyledAttributes;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends Fragment {

    public FoodFragment() {
        // Required empty public constructor
    }


    //TODO adding the progress bar in this fragment
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    FoodItemsAdapter mAdapter;
    ProgressBar mProgressBar;
    ArrayList<FoodData> foodDataArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_food, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        foodDataArrayList=new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler);
        mProgressBar=view.findViewById(R.id.foodprogress);
        mProgressBar.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        mAdapter= new FoodItemsAdapter(getContext(),foodDataArrayList);
        recyclerView.setAdapter(mAdapter);

        databaseReference= FirebaseDatabase.getInstance().getReference("Foods");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshots) {
//                progressBar.setVisibility(View.GONE);
                if (dataSnapshots.exists()) {
                    for (DataSnapshot dataSnapshot : dataSnapshots.getChildren()) {
                        String name = dataSnapshot.child("Name").getValue(String.class);
                        String desc = dataSnapshot.child("Description").getValue(String.class);
                        String image = dataSnapshot.child("Image").getValue(String.class);
                        String price = dataSnapshot.child("Price").getValue(String.class);
                        String discount = dataSnapshot.child("Discount").getValue(String.class);
                        String menuId = dataSnapshot.child("MenuId").getValue(String.class);
                        Log.e("asdghjklkj",name);
                        foodDataArrayList.add(new FoodData(discount,name,desc,price,image,menuId));
                    }
                    mProgressBar.setVisibility(View.INVISIBLE);

                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



    }






}
