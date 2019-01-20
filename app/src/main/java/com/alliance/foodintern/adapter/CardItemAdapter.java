package com.alliance.foodintern.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.alliance.foodintern.R;
import com.alliance.foodintern.activity.DetailsfoodActivity;
import com.alliance.foodintern.activity.MainActivity;
import com.alliance.foodintern.fragment.CartFragment;
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
    public void onBindViewHolder(@NonNull myViewHolder holder, final int position) {
        final CardItem data=cardItems.get(position);
        Glide.with(mCtx)
                .load(data.getImage())
                .into(holder.imageView);
        holder.name.setText(data.getFood_name());
        holder.price.setText(data.getFood_price());
        final String id=data.getId();
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(mCtx);
                alertDialog.setTitle("Remove Item?");
                alertDialog.setMessage("Do you want to remove an item from the Cart!");
                alertDialog.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // DO SOMETHING HERE
                        SqlDataBaseAdapter db= new SqlDataBaseAdapter(mCtx);
                        db.open();
                        Toast.makeText(mCtx, "going to delete", Toast.LENGTH_SHORT).show();

                        if(db.delete(Integer.parseInt(id))){
                            Toast.makeText(mCtx, "Item Removed Successfully", Toast.LENGTH_SHORT).show();
                            cardItems.remove(position);
                            notifyItemChanged(position);
                            notifyItemChanged(position,cardItems.size());
                            CartFragment mNoificationFragment=new CartFragment();
                            MainActivity myActivity = (MainActivity) mCtx;

                            android.support.v4.app.FragmentTransaction fragmentTransaction=myActivity.getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.container,mNoificationFragment);
                            fragmentTransaction.commit();



                        }else{
                            Toast.makeText(mCtx, "Not able to delete:"+id, Toast.LENGTH_SHORT).show();

                        }
                        db.close();
                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();



                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return cardItems.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView name,price;
        ImageView imageView;
        public myViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            name=itemView.findViewById(R.id.c_name);
            price=itemView.findViewById(R.id.c_price);
        }



    }

}
