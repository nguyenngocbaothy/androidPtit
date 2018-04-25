package com.developer.nguyenngocbaothy.android_ptit;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    GoogleMap map;
    LocationManager locationManager;
    LocationListener locationListener;
    Double latitude,longitude;
    Boolean GpsStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //askOpenGPS();
        LocationManager locationManager = (LocationManager)MapActivity.this.getSystemService(Context.LOCATION_SERVICE);
        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(GpsStatus) {
            MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.myMap);
            mapFragment.getMapAsync(this);
        }
        else {
            askOpenGPS();
        }

    }

    private void askOpenGPS() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MapActivity.this);
        alert.setTitle("Message");
        alert.setIcon(R.mipmap.ic_launcher_round);
        alert.setMessage("You need to open GPS to view the map!");

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
                dialogInterface.dismiss();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MapActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        alert.show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        //set current location
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
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, MapActivity.this);
        map.setMyLocationEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);

        //LatLng city = new LatLng(10.848093, 106.786588);
//        LatLng city = new LatLng(latitude, longitude);
//
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(city, 13));
//
//        map.addMarker(new MarkerOptions()
//                .title("Current Loction")
//                .snippet("Hoc vien cong nghe buu chinh vien thong TPHCM")
//                .position(city));

        new GetCoordinate().execute("Hotel Dalvik".replace(" ", "+"));
    }

    @Override
    public void onLocationChanged(Location location) {
        // Toast.makeText(this, "Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude(), Toast.LENGTH_SHORT).show();

        if(location!=null)
        {
            latitude = location.getLatitude();
            longitude = location.getLongitude();

//            LatLng city = new LatLng(location.getLatitude(), location.getLongitude());
//            map.addMarker(new MarkerOptions().position(city).title("Current Loction"));
//            map.moveCamera(CameraUpdateFactory.newLatLngZoom(city, 13));


        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


    private class GetCoordinate extends AsyncTask<String, Void, String> {
        ProgressDialog dialog = new ProgressDialog(MapActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait...");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String response;
            try {
                String address = strings[0];
                HttpDateHandler http = new HttpDateHandler();
                String url = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s,", address);
                response = http.getHTTPData(url);
                return response;
            }
            catch (Exception ex) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            Log.d("abc", s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                String lat = ((JSONArray) jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lat").toString();
                String lng = ((JSONArray) jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lng").toString();

                Toast.makeText(MapActivity.this, "lat " + lat + " - lng " + lng, Toast.LENGTH_LONG).show();
                Toast.makeText(MapActivity.this, "current position " + latitude + " - " + longitude, Toast.LENGTH_SHORT).show();

                if(dialog.isShowing()) {
                    dialog.dismiss();
                }

                map.addPolyline(new PolylineOptions().add(
                        new LatLng(latitude, longitude),
                        new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))
                )
                        .width(10)
                        .color(Color.RED)

                );
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
