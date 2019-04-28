package com.kerry.gogobasketball.home.map;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.data.CourtsInfo;
import com.kerry.gogobasketball.util.Constants;

import java.util.ArrayList;

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
                                Log.e(Constants.TAG, "population = " + courtsInfo.getPopulation());
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

        mCourtsListener = query.addSnapshotListener((value, e) -> {
            if (e != null) {
                Log.w(Constants.TAG, "Listen failed.", e);
                return;
            }
            mCourtsMapView.refreshMarkers();
        });


    }

    @Override
    public void removeListener() {
        mCourtsListener.remove();
    }

    @Override
    public void start() {

    }

    public interface getCurrentCourtPopulationCallback {

        void onSuccess(ArrayList<String> populationList);

        void onFail();

    }



}
