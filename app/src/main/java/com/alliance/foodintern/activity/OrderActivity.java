package com.alliance.foodintern.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.alliance.foodintern.R;
import com.alliance.foodintern.adapter.SqlDataBaseAdapter;
import com.alliance.foodintern.model.CardItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.alliance.foodintern.fragment.CartFragment.MY_PERMISSIONS_REQUEST_COERSE;
import static com.alliance.foodintern.fragment.CartFragment.MY_PERMISSIONS_REQUEST_LOCATION;

public class OrderActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    EditText phone, address;
    Button button;
    String foods="";
    String totalPrice;
    private static final String TAG ="TAG" ;
    ImageButton imageButton;

    String cityName = null;
    String longitude,latitude;
    String phn,addrs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        databaseReference = FirebaseDatabase.getInstance().getReference("orders");
        phone= findViewById(R.id.phone);
        address=findViewById(R.id.address);
        button=findViewById(R.id.order);
        imageButton=findViewById(R.id.imageButton);
        getData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                phn=phone.getText().toString();
                addrs=address.getText().toString();
                DatabaseReference db=databaseReference.child(phn);
                db.child("phone").setValue(phn);
                db.child("foods").setValue(foods);
                db.child("address").setValue(addrs);
                Toast.makeText(getApplicationContext(),"Your order have been placed",Toast.LENGTH_SHORT);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"getting location", Toast.LENGTH_SHORT).show();
                getCurrentLocation();

            }
        });


    }
    private void getCurrentLocation() {

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new MyLocationListener();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_COERSE);
        }

        if (locationManager != null) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
        }
    }

    class MyLocationListener implements LocationListener
    {


        @Override
        public void onLocationChanged(android.location.Location loc) {

            longitude = "Longitude: " + loc.getLongitude();
            Log.v(TAG, longitude);
            latitude = "Latitude: " + loc.getLatitude();
            Log.v(TAG, latitude);

            /*------- To get city name from coordinates -------- */


            try {
                Locale pname = Locale.getDefault();
                if (pname != null) {
                    Geocoder gcd = new Geocoder(getBaseContext(), pname);
                    List<Address> addresses;
                    addresses = gcd.getFromLocation(loc.getLatitude(),
                            loc.getLongitude(), 1);
                    if (addresses.size() > 0) {
                        System.out.println(addresses.get(0).getLocality());
                        cityName = addresses.get(0).getSubLocality();

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            String s = longitude + "\n" + latitude + "\n\nMy Current City is: " + cityName;
            addrs=longitude+","+latitude+","+cityName;
            address.setText(addrs);

            Toast.makeText(getBaseContext(), ""+s, Toast.LENGTH_SHORT).show();
           /*
            Intent mapIntent = new Intent(getActivity(), MapsActivity.class);
            mapIntent.putExtra("lat",latitude);
            mapIntent.putExtra("lon",longitude);
            mapIntent.putExtra("name",cityName);
            //mapIntent.setPackage("com.google.android.apps.maps");
            // startActivity(mapIntent);

           */



        }



        public void onStatusChanged(String provider, int status, Bundle extras) {

        }


        public void onProviderEnabled(String provider) {

        }


        public void onProviderDisabled(String provider) {

        }
    }

    private void getData(){
        SqlDataBaseAdapter db = new SqlDataBaseAdapter(this);

        db.open();
        int total=0,grandtotal=0;
        int discount_tot=0;
        int count=0;
        Cursor c = db.retrieve();
        if (c.moveToFirst())
        {
            do {
               // CardItem item=new CardItem();
                String price=c.getString(4);
                int discount=Integer.valueOf(c.getString(3));
                discount_tot+=discount;
                count++;
                int temp=Integer.valueOf(price.substring(1));
                total+=temp;
                foods=foods+ ","+ c.getString(1);
             // item.setFood_price(price);
               // item.setFood_name();
               // mCursorList.add(item);

            } while (c.moveToNext());
            /*
            st.setText(getString(R.string.rupee_symbol)+total);
            dis.setText(getString(R.string.rupee_symbol)+total*discount_tot/count*100);
            tax.setText(getString(R.string.minus)+getString(R.string.rupee_symbol)+total*6/100);
            grandtotal=total-(total*discount_tot/count*100)+(total*6/100);

            mtotal.setText(getString(R.string.rupee_symbol)+grandtotal);
            mCardItemAdapter.notifyDataSetChanged();
        */
            totalPrice=""+grandtotal;
        }else{
          /*
          view.findViewById(R.id.rl_empty).setVisibility(View.VISIBLE);
        */
        }
        db.close();

    }


}
