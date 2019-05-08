package com.kerry.gogobasketball.home.map;

import static com.google.common.base.Preconditions.checkNotNull;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.data.CourtsInfo;
import com.kerry.gogobasketball.util.Constants;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class CourtsMapPresenter implements CourtsMapContract.Presenter {


    private CourtsMapContract.View mCourtsMapView;
    private ArrayList<String> mPopulationList;
    private static ListenerRegistration mCourtsListener;

    public CourtsMapPresenter(@NonNull CourtsMapContract.View mapView) {
        mCourtsMapView = checkNotNull(mapView, "mapView cannot be null!");
        mCourtsMapView.setPresenter(this);
        mPopulationList = new ArrayList<>();
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void getLocationPermission(Activity activity) {

        String[] permissions = {Constants.FINE_PERMISSION, Constants.COARSE_PERMISSION};

        if (ContextCompat.checkSelfPermission(activity, Constants.FINE_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(activity, Constants.COARSE_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
                mCourtsMapView.getLocationPermissionGranted(true);
                mCourtsMapView.initMap();
            } else {
                ActivityCompat.requestPermissions(activity, permissions, Constants.MY_PERMISSIONS_REQUEST_LOCATION);
            }
        } else {
            ActivityCompat.requestPermissions(activity, permissions, Constants.MY_PERMISSIONS_REQUEST_LOCATION);
        }
    }

    @Override
    public void getCurrentCourtPopulation() {
        mPopulationList.clear();
        FirestoreHelper.getFirestore()
                .collection(Constants.COURTS)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CourtsInfo courtsInfo = document.toObject(CourtsInfo.class);
                                mPopulationList.add(String.valueOf(courtsInfo.getPopulation()));
                            }
                            Log.i(Constants.TAG, "size = " + mPopulationList.size());
                            mCourtsMapView.getPopulationFromPresenter(mPopulationList);

                        } else {
                            Log.w(Constants.TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    @Override
    public void setOnPopulationChangeListener() {
        Query query = FirestoreHelper.getFirestore()
                .collection(Constants.COURTS);
        Log.w(Constants.TAG, "setOnPopulationChangeListener : " + query.toString());

        mCourtsListener = query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(Constants.TAG, "Listen failed.", e);
                    return;
                }
                if (value != null) {
                    mCourtsMapView.refreshMarkers();
                } else {
                    Log.d(Constants.TAG, "onEvent FAIL !");
                }
            }
        });
    }

    @Override
    public void removeListener() {
        if (mCourtsListener != null) {
            mCourtsListener.remove();
        } else {
            Log.d(Constants.TAG, "MapFragment mCourtsListener is null !");
        }
    }

    @Override
    public void start() {

    }
}
