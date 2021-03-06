package com.kerry.gogobasketball.util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.data.User;

import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

public class UserManager {

    private static User mUser;
    private CallbackManager mFbCallbackManager;

    private static class UserManagerHolder {
        private static final UserManager INSTANCE = new UserManager();
    }

    private UserManager() {
        mUser = new User();
    }

    public static UserManager getInstance() {
        return UserManagerHolder.INSTANCE;
    }

    /**
     * Login GoGoBasketball by Facebook: Step 1. Register FB Login Callback
     *
     * @param context
     * @param loadCallback
     */
    public void loginGoGoBasketballByFacebook(Context context, final LoadCallback loadCallback) {

        mFbCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mFbCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Log.d(Constants.TAG, "FB Login Success");
                Log.i(Constants.TAG, "loginResult.getAccessToken().getToken() = " + loginResult.getAccessToken().getToken());
                Log.i(Constants.TAG, "loginResult.getAccessToken().getUserId() = " + loginResult.getAccessToken().getUserId());
                Log.i(Constants.TAG, "loginResult.getAccessToken().getApplicationId() = " + loginResult.getAccessToken().getApplicationId());

                loginGoGoBasketball(context, loginResult, loadCallback);
            }

            @Override
            public void onCancel() {
                Log.d(Constants.TAG, "FB Login Cancel");
                loadCallback.onFail("FB Login Cancel");
            }

            @Override
            public void onError(FacebookException exception) {

                Log.d(Constants.TAG, "FB Login Error");
                loadCallback.onFail("FB Login Error: " + exception.getMessage());
            }
        });
        loginFacebook(context);
    }

    /**
     * Login GoGoBasketball by Facebook: Step 2. Login Facebook
     */
    private void loginFacebook(Context context) {
        LoginManager.getInstance().logInWithReadPermissions(
                (Activity) context, Arrays.asList("public_profile", "email"));
    }

    private void loginGoGoBasketball(Context context, LoginResult loginResult, LoadCallback loadCallback) {
        Log.e(Constants.TAG, "loginGoGoBasketball Token = " + loginResult.getAccessToken().getToken());
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            String name = object.getString("name");
                            long facebookId = object.getLong(Constants.USER_ID);
                            String profileImage = "https://graph.facebook.com/" + String.valueOf(facebookId) + "/picture?type=large";
                            mUser.setName(name);
                            mUser.setAvatar(profileImage);
                            mUser.setFacebookId(String.valueOf(facebookId));
                            loadCallback.onSuccess(mUser);

                        } catch (JSONException e) {
                            Log.e(Constants.TAG, "unexpected JSON exception", e);
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email");
        request.setParameters(parameters);
        request.executeAsync();

    }

    public void clearUserLogin() {
        setUser(null);
        GoGoBasketball.getAppContext().getSharedPreferences(Constants.USERS, Context.MODE_PRIVATE).edit()
                .remove(Constants.USER_TOKEN)
                .apply();
    }

    public void getUserProfile(LoadCallback loadCallback) {

        if (AccessToken.getCurrentAccessToken() != null) {
            DocumentReference docRef = FirebaseFirestore.getInstance()
                    .collection(Constants.USERS)
                    .document(AccessToken.getCurrentAccessToken().getUserId().trim());

            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d(Constants.TAG, "DocumentSnapshot data: " + document.getData());
                            User userInfo = document.toObject(User.class);
                            loadCallback.onSuccess(userInfo);
                        } else {
                            Log.d(Constants.TAG, "No such document");
                            loadCallback.onFail("No such document!");
                        }
                    } else {
                        Log.d(Constants.TAG, "get failed with ", task.getException());
                    }
                }
            }).addOnFailureListener(e -> Log.d(Constants.TAG, " getUserProfile Error !!"));
        }
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    public boolean hasUserInfo() {
        return getUser() != null;
    }

    public CallbackManager getFbCallbackManager() {
        return mFbCallbackManager;
    }

    public interface LoadCallback {

        void onSuccess(User user);

        void onFail(String errorMessage);

        void onInvalidToken(String errorMessage);
    }

    public interface CheckUserCallback {

        void haveCreated(User user);

        void haveNotCreated(User user);

    }
}
