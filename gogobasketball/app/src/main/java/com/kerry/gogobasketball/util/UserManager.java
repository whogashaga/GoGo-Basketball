package com.kerry.gogobasketball.util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.MainActivity;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.data.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class UserManager {

    private User mUser;
    private CallbackManager mFbCallbackManager;
    private long mLastChallengeTime;
    private int mChallengeCount;
    private static final int CHALLENGE_LIMIT = 23;
    private String mFbUserId;

    private static class UserManagerHolder {
        private static final UserManager INSTANCE = new UserManager();
    }

    private UserManager() {
//        mFbCallbackManager = CallbackManager.Factory.create();
        mUser = new User();
        mFbUserId = "";
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

                mFbUserId = loginResult.getAccessToken().getUserId();
                Log.d("Kerry", "FB Login Success");
                Log.i("Kerry", "loginResult.getAccessToken().getToken() = " + loginResult.getAccessToken().getToken());
                Log.i("Kerry", "loginResult.getAccessToken().getUserId() = " + loginResult.getAccessToken().getUserId());
                Log.i("Kerry", "loginResult.getAccessToken().getApplicationId() = " + loginResult.getAccessToken().getApplicationId());

                loginGoGoBasketball(context, loginResult, loadCallback);
            }

            @Override
            public void onCancel() {
                Log.d("Kerry", "FB Login Cancel");
                loadCallback.onFail("FB Login Cancel");
            }

            @Override
            public void onError(FacebookException exception) {

                Log.d("Kerry", "FB Login Error");
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
                (Activity) context, Arrays.asList("email"));
    }

    private void loginGoGoBasketball(Context context, LoginResult loginResult, LoadCallback loadCallback) {
        Log.e("Kerry", "loginGoGoBasketball Token = " + loginResult.getAccessToken().getToken());
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            String name = object.getString("name");
                            long facebookId = object.getLong("id");
                            String profileImage = "https://graph.facebook.com/" + String.valueOf(facebookId) + "/picture?type=large&width=small";
                            mUser.setName(name);
                            mUser.setAvatar(profileImage);
                            mUser.setFacebookId(String.valueOf(facebookId));
                            loadCallback.onSuccess(mUser);

                            ((MainActivity) context).saveFacebookIdFile(String.valueOf(facebookId));
//                            updateUser2FireStore(mUser, loadCallback);

                        } catch (JSONException e) {
                            Log.e("Kerry", "unexpected JSON exception", e);
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email");
        request.setParameters(parameters);
        request.executeAsync();

    }

    public void challenge() {
        if (System.currentTimeMillis() - mLastChallengeTime > 5000) {
            mLastChallengeTime = System.currentTimeMillis();
            mChallengeCount = 0;
        } else {
            if (mChallengeCount == CHALLENGE_LIMIT) {
                clearUserLogin();
                Toast.makeText(GoGoBasketball.getAppContext(),
                        GoGoBasketball.getAppContext().getString(R.string.profile_default_information),
                        Toast.LENGTH_SHORT).show();
            } else {
                mChallengeCount++;
            }
        }
    }

    public void clearUserLogin() {
        setUser(null);
        GoGoBasketball.getAppContext().getSharedPreferences(Constants.USERS, Context.MODE_PRIVATE).edit()
                .remove(Constants.USER_TOKEN)
                .apply();
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