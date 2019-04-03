package com.example.med_to_wheel;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private static final String TAG = "MapActivity";
    LocationManager locationManager;
    EditText editText;
    private int SMS_PERMISSION_CODE = 1;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref;
    Button registeration;
    private final int REQUEST_LOCATION_PERMISSION = 1;
    d obj=new d();
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final long MIN_TIME = 400;
    private static final float MIN_DISTANCE = 1000;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
//        firebaseDatabase = FirebaseDatabase.getInstance();
    //    myref=firebaseDatabase.getReference("Hospitality");

        Bundle bundle = getIntent().getExtras();
        data = bundle.getString("value");
        registeration=(Button)findViewById(R.id.send);
        registeration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String ks=obj.getmethod();

                    if (ks!=null&& data!=null)
                    {
                        Intent i=new Intent(MapsActivity.this,finaldatabase.class);
                        i.putExtra("datass",ks+"="+data);
                        startActivity(i);
                        Log.d("aa",ks+data);
                   //     myref.setValue(ss);
                    }
                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }


            }
        });



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this);

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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
       mMap.getUiSettings().setMyLocationButtonEnabled(false);
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        Geocoder geocoder = new Geocoder(getApplicationContext());
        try{
            List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
            String string = addressList.get(0).getLocality() + "\n";
            string += addressList.get(0).getCountryName();
            String finaladdr = addressList.get(0).getSubAdminArea() + "\n" + addressList.get(0).getSubLocality() + "\t" + addressList.get(0).getAdminArea() + "\n" + addressList.get(0).getPostalCode() + "\t" + string;
            obj.setmethod(finaladdr);
            Toast.makeText(getApplicationContext(), finaladdr, Toast.LENGTH_LONG).show();

        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
        mMap.animateCamera(cameraUpdate);
        locationManager.removeUpdates(this);
        /* double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String string = addressList.get(0).getLocality() + "\n";
                        string += addressList.get(0).getCountryName();
                        String finaladdr = addressList.get(0).getSubAdminArea() + "\n" + addressList.get(0).getSubLocality() + "\t" + addressList.get(0).getAdminArea() + "\n" + addressList.get(0).getPostalCode() + "\t" + string;
                        mMap.addMarker(new MarkerOptions().position(latLng).title(string));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.2f));
                        Toast.makeText(getApplicationContext(), finaladdr, Toast.LENGTH_LONG).show();*/
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
class d
{
    String ks;
    public void setmethod(String s)
    {
        this.ks=s;
    }
    public String getmethod()
    {
        return ks;
    }
}