package com.alliance.foodintern.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alliance.foodintern.R;
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

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        FoodData data=foodList.get(position);
        Glide.with(mCtx)
                .load(data.getImage())
                .into(holder.imageView);
        holder.discount.setText(data.getDiscount());
        holder.description.setText(data.getDescription());
        holder.name.setText(data.getFoodName());
        holder.price.setText(data.getPrice());

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
