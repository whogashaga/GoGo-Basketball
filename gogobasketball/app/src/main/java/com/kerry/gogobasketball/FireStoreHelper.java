package com.kerry.gogobasketball;

import com.google.firebase.firestore.FirebaseFirestore;

public class FireStoreHelper {

    public static FirebaseFirestore mDb = FirebaseFirestore.getInstance();

    public FireStoreHelper() {}

    public static FirebaseFirestore getFireStore() {
        return mDb;
    }
}
