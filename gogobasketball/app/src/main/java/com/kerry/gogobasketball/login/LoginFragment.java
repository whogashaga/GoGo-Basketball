package com.kerry.gogobasketball.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.util.UserManager;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoginFragment extends Fragment implements LoginContract.View, View.OnClickListener {

    private LoginContract.Presenter mPresenter;

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
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        mBtnLogin = root.findViewById(R.id.btn_facebook_login);
        mBtnLogin.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_facebook_login:

                UserManager.getInstance().loginGoGoBasketballByFacebook(getActivity(), new UserManager.LoadCallback() {
                    @Override
                    public void onSuccess() {

                        if (mPresenter != null) {
                            mPresenter.showLoginSuccessDialog();
                            mPresenter.onLoginSuccess();
                        }
                    }

                    @Override
                    public void onFail(String errorMessage) {
                        Log.d("Kerry","LoginFragment loginGoGoBasketballByFacebook Fail!");
                    }

                    @Override
                    public void onInvalidToken(String errorMessage) {
                        Log.d("Kerry","LoginFragment Token 過期!");
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void showLoginUi() {

    }

    @Override
    public boolean isActive() {
        return false;
    }
}
