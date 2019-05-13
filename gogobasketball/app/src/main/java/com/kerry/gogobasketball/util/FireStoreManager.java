package com.kerry.gogobasketball.util;

import com.google.firebase.firestore.FirebaseFirestore;

public class FireStoreManager {

    public static FirebaseFirestore mDb = FirebaseFirestore.getInstance();

    private static class FireStoreHolder {
        private static final FireStoreManager INSTANCE = new FireStoreManager();
    }

    public FireStoreManager() {
    }

    public static FireStoreManager getInstance() {
        return FireStoreManager.FireStoreHolder.INSTANCE;
    }

    public FirebaseFirestore getFireStore() {
        return mDb;
    }






}
