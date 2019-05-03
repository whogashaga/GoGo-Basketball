package com.kerry.gogobasketball.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.data.User;

public class LocationManager {

    private FusedLocationProviderClient mFusedLocationProviderClient;

    private static class LocationManagerHolder {
        private static final LocationManager INSTANCE = new LocationManager();
    }

    private LocationManager() {
    }

    public static LocationManager getInstance() {
        return LocationManager.LocationManagerHolder.INSTANCE;
    }

    public void getDeviceLocation(final LocationCallback locationCallback) {
        Log.d(Constants.TAG, "Location Manager getDeviceLocation: getting the device current location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(GoGoBasketball.getAppContext());
        try {
            Task location = mFusedLocationProviderClient.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        Log.d(Constants.TAG, "onComplete: found location!");
                        Location currentLocation = (Location) task.getResult();
                        locationCallback.onSuccess(currentLocation.getLatitude(),currentLocation.getLongitude());
                    } else {
                        Log.d(Constants.TAG, "onComplete: current location is null!");
                    }
                }
            });

        } catch (SecurityException e) {
            Log.e("Kerry", "getDeviceLocation: SecurityException" + e.getMessage());
        }
    }

    public interface LocationCallback {

        void onSuccess(double latitude, double longitude);

        void onFail(String errorMessage);

    }

}
