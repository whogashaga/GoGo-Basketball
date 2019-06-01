package com.kerry.gogobasketball;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.AccessToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.data.CourtsInfo;
import com.kerry.gogobasketball.data.CourtsPeople;
import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.util.Constants;
import com.kerry.gogobasketball.util.LocationManager;
import com.kerry.gogobasketball.util.UserManager;

public class GetLocationService extends Service {

    private String mCourtsLocation;
    private User mUser;
    private Runnable mRunnable;
    private Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mCourtsLocation = "";
        mUser = new User();
        mHandler = new Handler();

        mRunnable = new Runnable() {
            @Override
            public void run() {
                mHandler.postDelayed(this, 5000);
                getDeviceCurrentLocation();
                Log.w("Kerry", "Service onCreate : getDeviceCurrentLocation()");
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Kerry", "Service onStartCommand : start Getting Device Location");
        startGettingLocation();
        return super.onStartCommand(intent, flags, startId);
//        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Kerry", "Service onDestroy !");
        mHandler.removeCallbacks(mRunnable);
        getUserInfoWhenGetOutOfApp();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.i("Kerry", "onTrimMemory: ");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.e("Kerry", "Service onTaskRemoved !");
        super.onTaskRemoved(rootIntent);
//        getUserInfoWhenGetOutOfApp();
//        mHandler.removeCallbacks(mRunnable);
//        stopSelf();
    }

    public void startGettingLocation() {
        mHandler.postDelayed(mRunnable, 5000);
    }


    public void getDeviceCurrentLocation() {
        Log.d("Kerry", "Service getDeviceCurrentLocation ");
        LocationManager.getInstance().getDeviceLocation(new LocationManager.LocationCallback() {
            @Override
            public void onSuccess(double latitude, double longitude) {
                getCourtsLocation(latitude, longitude);
            }

            @Override
            public void onFail(String errorMessage) {

            }
        });
    }

    private void getCourtsLocation(double latitude, double longitude) {

        if (checkCoordinateScope(latitude, longitude) != null) {
            checkIfUpdateLocation(checkCoordinateScope(latitude, longitude));
        } else {
            Log.w("Kerry", "Service getCourtsLocation : location is null !");
        }
    }

    public String checkCoordinateScope(double latitude, double longitude) {

        if (25.042300 <= latitude && latitude <= 25.044416
                && 121.563557 <= longitude && longitude <= 121.566868) {
            return Constants.SONG_SAN_HIGH_SCHOOL;

        } else if (25.032135 <= latitude && latitude <= 25.032994
                && 121.561168 <= longitude && longitude <= 121.562496) {
            return Constants.ADIDAS101;

        } else if (25.03069 <= latitude && latitude <= 25.032751
                && 121.534593 <= longitude && longitude <= 121.53753) {
            return Constants.DA_AN;

        } else if (25.019771 <= latitude && latitude <= 25.020811
                && 121.535612 <= longitude && longitude <= 121.537397) {
            return Constants.TAI_DA_CENTRAL;

        } else if (25.044851 <= latitude && latitude <= 25.045585
                && 121.530165 <= longitude && longitude <= 121.530777) {
            return Constants.XIN_SHENG_VIADUCT;

        } else if (25.020526 <= latitude && latitude <= 25.021701
                && 121.50447 <= longitude && longitude <= 121.505921) {
            return Constants.YOUTH_PARK;

        } else if (25.007798 <= latitude && latitude <= 25.009361
                && 121.490091 <= longitude && longitude <= 121.494754) {
            return Constants.DINOSAUR_PARK;

        } else if (25.001918 <= latitude && latitude <= 25.002907
                && 121.514287 <= longitude && longitude <= 121.514901) {
            return Constants.FOURTH_PARK;

        } else if (25.006242 <= latitude && latitude <= 25.007506
                && 121.527342 <= longitude && longitude <= 121.528675) {
            return Constants.FU_HER_BRIDGE;

        } else if (25.017736 <= latitude && latitude <= 25.018961
                && 121.509566 <= longitude && longitude <= 121.510660) {
            return Constants.GREEN_STONE_PARK;

        } else if (25.027551 <= latitude && latitude <= 25.028681
                && 121.483664 <= longitude && longitude <= 121.484508) {
            return Constants.WAN_BAN_BRIDGE;

        } else if (25.032995 <= latitude && latitude <= 25.034521
                && 121.486989 <= longitude && longitude <= 121.488330) {
            return Constants.SHUANG_YUAN_RIVER;

        } else if (25.013862 <= latitude && latitude <= 25.015228
                && 121.525101 <= longitude && longitude <= 121.526356) {
            return Constants.GU_TING_RIVER;

        } else if (24.991043 <= latitude && latitude <= 24.992618
                && 121.527668 <= longitude && longitude <= 121.529181) {
            return Constants.XIU_LANG_BRIDGE;

        } else if (25.052797 <= latitude && latitude <= 25.053949
                && 121.573592 <= longitude && longitude <= 121.575089) {
            return Constants.MAC_HANDSOME_BRIDGE;

        } else if (25.068288 <= latitude && latitude <= 25.068973
                && 121.532206 <= longitude && longitude <= 121.533075) {
            return Constants.XIN_SHENG_PARK;

        } else if (25.023490 <= latitude && latitude <= 25.025483
                && 121.474695 <= longitude && longitude <= 121.476583) {
            return Constants.BA_DER_PARK;

        } else if (25.012900 <= latitude && latitude <= 25.014651
                && 121.457014 <= longitude && longitude <= 121.458474) {
            return Constants.BANQIAO_SECOND;

        } else {
            Log.d("Kerry", "不在任何球場範圍內");
            if (!mCourtsLocation.equals("")) {
                deleteMyDocFromCourtsWhenLeave(mUser);
            } else {
                Log.d("Kerry", "checkCoordinateScope Error !");
            }
            return null;
        }
    }

    private void checkIfUpdateLocation(String location) {
        Log.d("Kerry", "checkIfUpdateLocation location = " + location);
        mCourtsLocation = location;
        if (AccessToken.getCurrentAccessToken() != null) {
            String facebookId = AccessToken.getCurrentAccessToken().getUserId().trim();
            DocumentReference docRef = FirebaseFirestore.getInstance()
                    .collection(Constants.COURTS)
                    .document(location)
                    .collection(Constants.PLAYERS)
                    .document(facebookId);

            docRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("Kerry", "已經在球場裡 " + document.getData());
                        // Already in the court, so do nothing
                    } else {
                        Log.d("Kerry", "不在球場，馬上加入");
                        // 更新球場人數
                        getUserInfo(location);
                    }
                } else {
                    Log.d("Kerry", "get failed with ", task.getException());
                }
            }).addOnFailureListener(e -> Log.d("Kerry", " getUserProfile Error !!"));
        } else {
            Log.e("Kerry", "checkIfUpdateLocation Error: AccessToken is null !");
        }
    }

    private void getUserInfo(String location) {
        UserManager.getInstance().getUserProfile(new UserManager.LoadCallback() {
            @Override
            public void onSuccess(User user) {
                getPopulationDocSize(user, location);
            }

            @Override
            public void onFail(String errorMessage) {
                Log.d("Kerry", "onFail: Service getUserId Error !");
            }

            @Override
            public void onInvalidToken(String errorMessage) {
                Log.d("Kerry", "onInvalidToken: Service getUserId");
            }
        });
    }

    private void getPopulationDocSize(User user, String location) {
        FirebaseFirestore.getInstance()
                .collection(Constants.COURTS)
                .document(location)
                .collection(Constants.PLAYERS)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            getCourtsInfo(user, location, task.getResult().size());

                        } else {
                            Log.w("Kerry", "getPopulationDocSize Error", task.getException());
                        }
                    }
                }).addOnFailureListener(e -> Log.e("Kerry", " getPopulationDocSize Error !!"));
    }

    private void getCourtsInfo(User user, String location, int nowPopulation) {

        DocumentReference docRef = FirebaseFirestore.getInstance()
                .collection(Constants.COURTS)
                .document(location);

        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Log.d("Kerry", "DocumentSnapshot data: " + document.getData());
                    // 取得球場資訊
                    CourtsInfo courtsInfo = document.toObject(CourtsInfo.class);
                    // 人數加一
                    courtsInfo.setPopulation(nowPopulation + 1);
                    updateCourtsPopulation(courtsInfo, user, location);

                } else {
                    Log.d("Kerry", "No such document");
                }
            } else {
                Log.d("Kerry", "get failed with ", task.getException());
            }
        }).addOnFailureListener(e -> Log.e("Kerry", " getUserProfile Error !!"));
    }

    private void updateCourtsPopulation(CourtsInfo courtsInfo, User user, String location) {

        FirebaseFirestore.getInstance()
                .collection(Constants.COURTS)
                .document(location)
                .set(courtsInfo)
                .addOnSuccessListener(aVoid -> {
                    Log.d("Kerry", "加入球場，並改變人數 !");
                    addMyself2Courts(user, location);
                }).addOnFailureListener(e -> Log.e("Kerry", "Error adding document", e));
    }

    private void addMyself2Courts(User user, String location) {
        mUser = user;
        mCourtsLocation = location;
        CourtsPeople courtsPeople = new CourtsPeople();
        courtsPeople.setId(user.getId());
        courtsPeople.setFacebookId(user.getFacebookId());

        FirebaseFirestore.getInstance()
                .collection(Constants.COURTS)
                .document(location)
                .collection(Constants.PLAYERS)
                .document(user.getFacebookId())
                .set(courtsPeople)
                .addOnCompleteListener(task -> {
                    Log.w("Kerry", "加入球場成功 ！");
                }).addOnFailureListener(e -> Log.w("Kerry", "Error adding document", e));
    }

    /* ------------------------------------------------------------------------------------------ */
    /* delete when get out app */

    public void getUserInfoWhenGetOutOfApp() {
        UserManager.getInstance().getUserProfile(new UserManager.LoadCallback() {
            @Override
            public void onSuccess(User user) {
                deleteMyDocFromCourtsWhenLeave(user);
            }

            @Override
            public void onFail(String errorMessage) {
                Log.d("Kerry", "onFail: Service getUserId Error !");
            }

            @Override
            public void onInvalidToken(String errorMessage) {
                Log.d("Kerry", "onInvalidToken: Service getUserId");
            }
        });
    }


    private void deleteMyDocFromCourtsWhenLeave(User user) {
        Log.w("Kerry", "deleteMyDocFromCourtsWhenLeave : mCourtsLocation = " + "'" + mCourtsLocation + "'");
        Log.w("Kerry", "deleteMyDocFromCourtsWhenLeave : user.getFacebookId() = " + user.getFacebookId());
        if (!mCourtsLocation.equals("") && !user.getFacebookId().equals("")) {
            FirebaseFirestore.getInstance()
                    .collection(Constants.COURTS)
                    .document(mCourtsLocation)
                    .collection(Constants.PLAYERS)
                    .document(user.getFacebookId())
                    .delete()
                    .addOnSuccessListener(aVoid -> {
                        checkPopulation();
                        Log.d("Kerry", "離開球場，刪除資料");
                    })
                    .addOnFailureListener(e -> Log.w("Kerry", "deleteMyDocFromCourtsWhenLeave Error : 無 Doc ID", e));
        } else {
            Log.e("Kerry", "離開球場 刪除資料 Fail");
        }
    }


    private void checkPopulation() {
        if (!mCourtsLocation.equals("")) {
            FirebaseFirestore.getInstance()
                    .collection(Constants.COURTS)
                    .document(mCourtsLocation)
                    .collection(Constants.PLAYERS)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                updateCourtsInfoWhenLeave(task.getResult().size());
                            } else {
                                Log.w("Kerry", "checkPopulation Error", task.getException());
                            }
                        }
                    }).addOnFailureListener(e -> Log.e("Kerry", " getPopulationDocSize Error !!"));
        } else {
            Log.d("Kerry", "checkPopulation Error : mCourtsLocation equals \"\" ");
        }
    }

    private void updateCourtsInfoWhenLeave(int nowPopulationSize) {
        FirebaseFirestore.getInstance()
                .collection(Constants.COURTS)
                .document(mCourtsLocation)
                .update("population", nowPopulationSize)
                .addOnSuccessListener(aVoid -> {
                    Log.d("Kerry", "離開球場，更新人數");
                })
                .addOnFailureListener(e -> Log.w("Kerry", "Error adding document", e));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
