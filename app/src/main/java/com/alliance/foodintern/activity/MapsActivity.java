package com.alliance.foodintern.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alliance.foodintern.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    TextView location;
    EditText address,landmark;
    private GoogleMap mMap;
    Button confirmLocation;
    double lat,lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        location=findViewById(R.id.location);
        address=findViewById(R.id.address);
        landmark=findViewById(R.id.landmark);
        confirmLocation=findViewById(R.id.confirm_location);

final String tot=getIntent().getStringExtra("totalAmount");


        confirmLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String land=landmark.getText().toString();
                String addrs=address.getText().toString();
                if(!TextUtils.isEmpty(land) && !TextUtils.isEmpty(addrs)){
                    Intent intent=new Intent(getApplicationContext(),SuccessPage.class);
                    intent.putExtra("landmark",land);
                    intent.putExtra("address",addrs);
                    intent.putExtra("totalAmount",tot);
                    intent.putExtra("finalAddress",getIntent().getStringExtra("fullAddress"));
                    startActivity(intent);
                }else{
                    Toast.makeText(MapsActivity.this, "Please fill address and landmark", Toast.LENGTH_SHORT).show();
                }

            }
        });
        lat=Double.parseDouble(getIntent().getStringExtra("lat").substring(9));
        lon=Double.parseDouble(getIntent().getStringExtra("lon").substring(10));
        Log.d("TAG", "onCreate: "+lat+"\n"+lon);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng map = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(map).title(getIntent().getStringExtra("name")));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(map,15));
//        Toast.makeText(this, getIntent().getStringExtra("fullAddress"), Toast.LENGTH_SHORT).show();
        location.setText("Current Location:"+getIntent().getStringExtra("fullAddress"));
    }
}
