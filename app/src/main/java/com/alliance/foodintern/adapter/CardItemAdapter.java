package com.alliance.foodintern.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alliance.foodintern.R;
import com.alliance.foodintern.activity.DetailsfoodActivity;
import com.alliance.foodintern.model.CardItem;
import com.alliance.foodintern.model.FoodData;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class CardItemAdapter extends RecyclerView.Adapter<CardItemAdapter.myViewHolder> {
    private Context mCtx;
    private ArrayList<CardItem> cardItems;

    public CardItemAdapter(Context mCtx, ArrayList<CardItem> cardItems) {
        this.mCtx = mCtx;
        this.cardItems = cardItems;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.card_items, parent, false);
        return new myViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        final CardItem data=cardItems.get(position);

        holder.name.setText(data.getFood_name());
        holder.price.setText(data.getFood_price());
        Log.d("TAG", "onBindViewHolder: "+data.getFood_price());


    }

    @Override
    public int getItemCount() {
        return cardItems.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView name,price;

        public myViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.c_name);
            price=itemView.findViewById(R.id.c_price);
        }



    }

}
