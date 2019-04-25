package com.kerry.gogobasketball.home.map;

import static com.google.common.base.Preconditions.checkNotNull;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.util.Constants;


public class CourtsMapFragment extends Fragment implements CourtsMapContract.View, OnMapReadyCallback, LocationListener {

    private CourtsMapContract.Presenter mPresenter;

    private double mLat;
    private double mLong;
    private LocationManager mLocationManager;
    private Location mLocation;
    private String mProvider;

    private static final String FINE_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_PERMISSION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private boolean mLocationPermissionGranted = false;
    private SupportMapFragment mapFragment;
    private GoogleMap mGoogleMap;

    private MapView mMap;


    private void getLocationPermission() {

        String[] permissions = {FINE_PERMISSION, COARSE_PERMISSION};

        if (ContextCompat.checkSelfPermission(getActivity(), FINE_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(getActivity(), COARSE_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
            } else {
                ActivityCompat.requestPermissions(getActivity(), permissions, MY_PERMISSIONS_REQUEST_LOCATION);
            }
        } else {
            ActivityCompat.requestPermissions(getActivity(), permissions, MY_PERMISSIONS_REQUEST_LOCATION);
        }
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

    private void initMap() {
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

//        googleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(0, 0))
//                .title("Marker"));

//        mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
//        mLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        mProvider = mLocationManager.getBestProvider(new Criteria(), false);
//        mLat = mLocation.getLatitude();  // 取得經度
//        mLong = mLocation.getLongitude(); // 取得緯度
//        LatLng HOME = new LatLng(mLat, mLong);
//        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HOME, 15.0f));//數字越大放越大
//        //Request location updates:
//        mLocationManager.requestLocationUpdates(mProvider, 400, 1, this);
    }

    public CourtsMapFragment() {
    }

    public static CourtsMapFragment newInstance() {
        return new CourtsMapFragment();
    }

    @Override
    public void showMapsUi() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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


//            mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
//            if (mapFragment == null) {
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                mapFragment = SupportMapFragment.newInstance();
//                ft.replace(R.id.map, mapFragment).commit();
//            }
//            mapFragment.getMapAsync(this);
        }

        return root;
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(getContext())
                        .setTitle("開啟 GPS")
                        .setMessage("想被別人知道你的位置？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    private void openAppSettingsIntent() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", getActivity().getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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
            Toast.makeText(getContext(), "You can't make map requset", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
