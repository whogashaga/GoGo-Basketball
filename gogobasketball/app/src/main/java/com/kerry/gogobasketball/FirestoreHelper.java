package com.kerry.gogobasketball;

import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreHelper {

    public static FirebaseFirestore mDb = FirebaseFirestore.getInstance();
//    private static StylishSQLiteHelper mStylishSQLiteHelper;

    public FirestoreHelper() {}

    public static FirebaseFirestore getFirestore() {
        return mDb;
    }
}
