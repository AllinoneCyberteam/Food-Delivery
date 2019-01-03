package com.alliance.foodintern.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alliance.foodintern.R;

public class FoodItemsAdapter extends RecyclerView.Adapter<FoodItemsAdapter.myViewHolder> {


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
