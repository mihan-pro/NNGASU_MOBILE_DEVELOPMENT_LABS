package com.app.proshin934_1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

public class MainActivity extends Activity {

    TextView tvOut;
    TextView tvLon;
    TextView tvLat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOut = (TextView) findViewById(R.id.title1);
        tvLon = (TextView) findViewById(R.id.longitude);
        tvLat = (TextView) findViewById(R.id.latitude);

        //Получаем сервис
        LocationManager mlocManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        LocationListener mlocListener = new LocationListener() {
            public void onLocationChanged(Location location) {

                //Called when a new location is found by the network location provider.
                tvLat.setText("Latitude: " + location.getLatitude());
                tvLon.setText("Longitude: " + location.getLongitude());
            }
        };

        //Подписываемся на изменения в показаниях датчика

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
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
                mlocListener);

        //Если gps включен, то ... , иначе вывести "GPS is not turned on..."
        if (mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            tvOut.setText("GPS is turned on...");

        } else {
            tvOut.setText("GPS is not turned on...");
        }
    }
}
