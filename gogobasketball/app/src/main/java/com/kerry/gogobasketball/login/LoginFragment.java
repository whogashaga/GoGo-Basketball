package com.kerry.gogobasketball.login;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.util.Constants;
import com.kerry.gogobasketball.util.UserManager;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoginFragment extends Fragment implements LoginContract.View, View.OnClickListener {

    private LoginContract.Presenter mPresenter;
    private View mLayout;
    private Button mBtnLogin;

    public LoginFragment() {
        // Requires empty public constructor
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v("Kerry", "LoginFragment onResume !");
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
        UserManager.getInstance().getFbCallbackManager().onActivityResult(requestCode, resultCode, data);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        mLayout = root.findViewById(R.id.layout_login);
        mBtnLogin = root.findViewById(R.id.btn_facebook_login);
        mBtnLogin.setOnClickListener(this);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.hideToolbarAndBottomNavigation();
        if (isServicesOk()) {
            mPresenter.getLocationPermissionWhenLogin(getActivity());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("Kerry", "LoginFragment onDestroy !");
//        mPresenter.showToolbarAndBottomNavigation();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_facebook_login:
                mPresenter.loginFbOnClick(getActivity());
                break;
            default:
                break;
        }
    }

    @Override
    public void showLoginSuccessUi(User user) {
        if (mPresenter != null) {
            mPresenter.showLoginSuccessDialog();
            mPresenter.onLoginSuccess(user);
        }
        onDestroy();
    }

    @Override
    public boolean isActive() {
        return false;
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
