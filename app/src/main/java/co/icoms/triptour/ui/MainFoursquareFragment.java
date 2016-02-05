package co.icoms.triptour.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import co.icoms.triptour.R;
import co.icoms.triptour.utils.Final;


public class MainFoursquareFragment extends Fragment implements LocationListener{

    LocationManager locationManager;
    LocationListener locationListener;
    Location location;
    public boolean status = false;


    @Override
    public void onLocationChanged(Location loc) {
        location=loc;
        Log.e("GPS New Lon", String.valueOf(loc.getLongitude()));
        Log.e("GPS New Lat", String.valueOf(loc.getLatitude()));
        Toast toast = Toast.makeText(getContext(), "Listener "+String.valueOf(location.getLongitude())+","+String.valueOf(location.getLatitude()), Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        status=true;
    }

    @Override
    public void onProviderDisabled(String provider) {
        status=false;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_foursquare, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
    }

    public class tryLocation extends TimerTask{

        @Override
        public void run() {
            if(!status){
                //tryLocation();
            }
            else if(location==null){
//                Toast toast = Toast.makeText(getContext(), "Null Location try", Toast.LENGTH_LONG);
  //              toast.show();
                Log.e("GPS New", "Null Location try");
            }
            else {
    //            Toast toast = Toast.makeText(getContext(), "Try "+String.valueOf(location.getLongitude())+","+String.valueOf(location.getLatitude()), Toast.LENGTH_LONG);
      //          toast.show();
                Log.e("GPS New Lon", String.valueOf(location.getLongitude()));
                Log.e("GPS New Lat", String.valueOf(location.getLatitude()));
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        lastLocation();
        Timer t = new Timer();
            t.scheduleAtFixedRate(new tryLocation(), 0L, 500L);
    }

    public void lastLocation(){
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Final.Permissions.FINE_LOCATION);
            }
            return;
        }

        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        status = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.e("GPS", "Location setup");
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Final.Permissions.FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                   lastLocation();
                } else {
                }
                return;
            }
        }
    }

    public void enable(){
        Intent gpsOptionsIntent = new Intent(
                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(gpsOptionsIntent);
    }
}