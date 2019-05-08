package com.kerry.gogobasketball.home.map;

import static com.google.common.base.Preconditions.checkNotNull;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.util.Constants;

import java.util.ArrayList;


public class CourtsMapFragment extends Fragment implements CourtsMapContract.View,
        OnMapReadyCallback, LocationListener {

    private CourtsMapContract.Presenter mPresenter;

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private static final float DEFAULT_ZOOM = 15;
    private boolean mLocationPermissionGranted = false;
    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    public CourtsMapFragment() {
    }

    public static CourtsMapFragment newInstance() {
        return new CourtsMapFragment();
    }

    @Override
    public void getLocationPermissionGranted(boolean locationPermissionGranted) {
        mLocationPermissionGranted = locationPermissionGranted;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        mLocationPermissionGranted = false;

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionGranted = true;
                    // init my Map
                    initMap();

                } else {
                    Log.d(Constants.TAG, "Location Permission Denied !");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
            }
        }
    }

    private void getDeviceLocation() {
        Log.d(Constants.TAG, "Map Fragment getDeviceLocation");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        try {
            Task location = mFusedLocationProviderClient.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        Log.d(Constants.TAG, "onComplete: found location!");
                        Location currentLocation = (Location) task.getResult();

                        moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                DEFAULT_ZOOM);

                    } else {
                        Log.d(Constants.TAG, "onComplete: current location is null!");
                    }
                }
            });

        } catch (SecurityException e) {
            Log.e(Constants.TAG, "Map Fragment getDeviceLocation: SecurityException" + e.getMessage());
        }
    }

    private void moveCamera(LatLng latLng, float zoom) {
        Log.d(Constants.TAG, "moving the camera to lat :" + latLng.latitude + ", lng : " + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    @Override
    public void initMap() {
        Log.i(Constants.TAG, "MapFragment initMap: ");
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(Constants.TAG, "map is ready");
        mPresenter.setOnPopulationChangeListener();
        mMap = googleMap;
//        mPresenter.getCurrentCourtPopulation();
        if (mLocationPermissionGranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void getPopulationFromPresenter(ArrayList<String> populationList) {
        initCourtsMarker(populationList);
    }

    private void initCourtsMarker(ArrayList<String> populationList) {
        Log.i(Constants.TAG, "initCourtsMarker size : " + populationList.size());
        if (getActivity() != null) {
            addCourtsMarker(new LatLng(25.032598, 121.561610), getString(R.string.adidas_101), "目前人數 : " + populationList.get(0));
            addCourtsMarker(new LatLng(25.024163, 121.475227), getString(R.string.ba_der_park), "目前人數 : " + populationList.get(1));
            addCourtsMarker(new LatLng(25.013797, 121.457748), getString(R.string.banqiao_second), "目前人數 : " + populationList.get(2));
            addCourtsMarker(new LatLng(25.031693, 121.535961), getString(R.string.da_an_park), "目前人數 : " + populationList.get(3));
            addCourtsMarker(new LatLng(25.008116, 121.493991), getString(R.string.dinosaur_park), "目前人數 : " + populationList.get(4));
            addCourtsMarker(new LatLng(25.002419, 121.514499), getString(R.string.fourth_823_park), "目前人數 : " + populationList.get(5));
            addCourtsMarker(new LatLng(25.006869, 121.528114), getString(R.string.fu_her_bridge), "目前人數 : " + populationList.get(6));
            addCourtsMarker(new LatLng(25.018408, 121.510075), getString(R.string.green_stone_park), "目前人數 : " + populationList.get(7));
            addCourtsMarker(new LatLng(25.014367, 121.525744), getString(R.string.gu_ting_river), "目前人數 : " + populationList.get(8));
            addCourtsMarker(new LatLng(25.053201, 121.574055), getString(R.string.mac_handsome_bridge), "目前人數 : " + populationList.get(9));
            addCourtsMarker(new LatLng(25.033570, 121.488031), getString(R.string.shuang_yuan_river), "目前人數 : " + populationList.get(10));
            addCourtsMarker(new LatLng(25.043572, 121.565559), getString(R.string.song_san_high_school), "目前人數 : " + populationList.get(11));
            addCourtsMarker(new LatLng(25.020213, 121.536475), getString(R.string.tai_da_central), "目前人數 : " + populationList.get(12));
            addCourtsMarker(new LatLng(25.028251, 121.483837), getString(R.string.wan_ban_bridge), "目前人數 : " + populationList.get(13));
            addCourtsMarker(new LatLng(25.068558, 121.532685), getString(R.string.xin_sheng_park), "目前人數 : " + populationList.get(14));
            addCourtsMarker(new LatLng(25.045040, 121.530423), getString(R.string.xin_sheng_high), "目前人數 : " + populationList.get(15));
            addCourtsMarker(new LatLng(24.991559, 121.528378), getString(R.string.xiu_lang_bridge), "目前人數 : " + populationList.get(16));
            addCourtsMarker(new LatLng(25.021023, 121.505110), getString(R.string.younth_park), "目前人數 : " + populationList.get(17));

        } else {
            Log.d(Constants.TAG, "initCourtsMarker: activity or context is null");
        }

    }

    private void addCourtsMarker(LatLng latLng, String title, String snippet) {

        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .title(title)
                .snippet(snippet)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_court_pin));
        if (mMap != null) {
            mMap.addMarker(markerOptions);
        } else {
            Log.d(Constants.TAG, "addCourtsMarker Error : mMap is null");
        }
    }

    @Override
    public void refreshMarkers() {
        if (mMap != null) {
            mMap.clear();
        } else {
            Log.d(Constants.TAG, "refreshMarkers Error: mMap is null");
        }
        if (mPresenter != null) {
            mPresenter.getCurrentCourtPopulation();
        } else {
            Log.d(Constants.TAG, "refreshMarkers Error: mPresenter is null");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapsInitializer.initialize(GoGoBasketball.getAppContext());
    }

    @Override
    public void setPresenter(CourtsMapContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home_child_map, container, false);

        if (isServicesOk()) {
            mPresenter.getLocationPermission(getActivity());
        }

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter.getCurrentCourtPopulation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.removeListener();
        } else {
            Log.d(Constants.TAG, "onDestroy: ");
        }
    }

    @Override
    public void onLocationChanged(Location location) {

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

    public boolean isServicesOk() {
        Log.d(Constants.TAG, "isServicesOk : checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getActivity());

        if (available == ConnectionResult.SUCCESS) {

            Log.d(Constants.TAG, "isServicesOk : google services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            // an error occured but we can resolve it
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), available, 1);
            dialog.show();
        } else {
            Toast.makeText(getContext(), "You can't make map request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
