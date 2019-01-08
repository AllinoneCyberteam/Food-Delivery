package com.alliance.foodintern.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alliance.foodintern.R;
import com.alliance.foodintern.activity.DetailsfoodActivity;
import com.alliance.foodintern.model.FoodData;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class FoodItemsAdapter extends RecyclerView.Adapter<FoodItemsAdapter.myViewHolder> {
    private Context mCtx;
    private ArrayList<FoodData> foodList;

    public FoodItemsAdapter(Context mCtx, ArrayList<FoodData> foodList) {
        this.mCtx = mCtx;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.food_items, parent, false);
        return new myViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        final FoodData data=foodList.get(position);
        Glide.with(mCtx)
                .load(data.getImage())
                .into(holder.imageView);
        holder.discount.setText(data.getDiscount());
        holder.description.setText(data.getDescription());
        holder.name.setText(data.getFoodName());
        holder.price.setText(mCtx.getString(R.string.rupee_symbol)+data.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mCtx, DetailsfoodActivity.class);
                Bundle bundle=new Bundle();
                intent.putExtra("food_image",data.getImage());
                intent.putExtra("food_name",data.getFoodName());
                intent.putExtra("details_of_food",data.getDescription());
                intent.putExtra("discount",data.getDiscount());
                intent.putExtra("price_of_food",data.getPrice());
                mCtx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name,description,price,discount;

        public myViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            name=itemView.findViewById(R.id.name);
            description=itemView.findViewById(R.id.desc);
            price=itemView.findViewById(R.id.price);
            discount=itemView.findViewById(R.id.disc);
        }



    }

}
