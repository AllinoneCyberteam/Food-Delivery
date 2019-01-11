package com.alliance.foodintern.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alliance.foodintern.R;
import com.alliance.foodintern.activity.MainActivity;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashBoardFragment extends Fragment {
        Button mButton,mapply;
        EditText mEditText;

    public DashBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_dash_board, container, false);
        SharedPreferences pref = getContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        int verifier=pref.getInt("key_int", 0);// getting Integer
        if (verifier==1)
        {

            view.findViewById(R.id.r2_offers).setVisibility(View.VISIBLE);

        }
        // Inflate the layout for this fragment

        mButton= view.findViewById(R.id.couponCode);
        mEditText=view.findViewById(R.id.coupon);
        mapply=view.findViewById(R.id.apply);

        mapply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditText.getText().toString() == null) {
                    Toast.makeText(getContext(), "Please Enter the Code!", Toast.LENGTH_SHORT).show();
                } else {
                    if (mEditText.getText().toString().equals("FP120")) {
                        Toast.makeText(getContext(), "Code Applied..", Toast.LENGTH_SHORT).show();
                        NoificationFragment mFragment = new NoificationFragment();
                        MainActivity myActivity = (MainActivity) getContext();

                        android.support.v4.app.FragmentTransaction fragmentTransaction = myActivity.getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, mFragment);
                        fragmentTransaction.commit();

                        SharedPreferences pref = getContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("key_success", 1);        // Saving integer
                        editor.commit();
                    } else {
                        Toast.makeText(getContext(), "Please enter the VAILD CODE!", Toast.LENGTH_SHORT).show();
                        mEditText.setText("");
                    }
                }
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("FP120");
            }
        });


        return view;
    }

}
