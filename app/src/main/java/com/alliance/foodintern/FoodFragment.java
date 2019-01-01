package com.alliance.foodintern;


import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import static android.support.v7.widget.TintTypedArray.obtainStyledAttributes;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends Fragment {
    Integer[] imageIDs = {
            R.drawable.download1,
            R.drawable.download2,
            R.drawable.download3,
            R.drawable.download4,
            R.drawable.download5,
            R.drawable.download6,
            R.drawable.download7,
            R.drawable.download8,
            R.drawable.download9,
            R.drawable.download10,
            R.drawable.download11,
            R.drawable.download12
    };


    public FoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_food, container, false);
        Gallery gallery = (Gallery) view.findViewById(R.id.gallery1);
        final ImageView imageView =(ImageView) view.findViewById(R.id.image1);

        gallery.setAdapter(new ImageAdapter(getActivity()));
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView parent, View v,
                                    int position, long id)
            {

//---display the images selected---
                imageView.setImageResource(imageIDs[position]);
            }
        });

        return view;
    }
    public class ImageAdapter extends BaseAdapter
    {
        Context context;
        int itemBackground;
        public ImageAdapter(Context c)
        {
            context = c;
//---setting the style---
            TypedArray a = getActivity().obtainStyledAttributes(R.styleable.Gallery1);
            itemBackground = a.getResourceId(
                    R.styleable.Gallery1_android_galleryItemBackground,
                    0);
            a.recycle();
        }
        //---returns the number of images---
        public int getCount() {
            return imageIDs.length;
        }
        //---returns the item---
        public Object getItem(int position) {
            return position;
        }
        //---returns the ID of an item---
        public long getItemId(int position) {
            return position;
        }
        //---returns an ImageView view---
        public View getView(int position, View convertView,
                            ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(context);
                imageView.setImageResource(imageIDs[position]);
                imageView.setScaleType(
                        ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(
                        new Gallery.LayoutParams(150, 120));
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setBackgroundResource(itemBackground);
            return imageView;
        }
    }

}
