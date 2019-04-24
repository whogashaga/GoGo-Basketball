package com.kerry.gogobasketball.profile.logout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.login.LoginManager;
import com.kerry.gogobasketball.R;

import static com.google.common.base.Preconditions.checkNotNull;

public class LogoutDialog extends DialogFragment implements LogoutContract.View, View.OnClickListener {

    private LogoutContract.Presenter mPresenter;
    private View mLayoutDialog;
    private View mLayout;
    private Button mBtnCancel;
    private Button mBtnLogoutYes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.CommentRefereeDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dialog_logout_confirm, container, false);


        mLayoutDialog = root.findViewById(R.id.dialog_logout_confirm);
        mLayoutDialog.setOnClickListener(this);
        mLayout = root.findViewById(R.id.layout_logout);
        mLayout.setOnClickListener(this);
        mBtnLogoutYes = root.findViewById(R.id.btn_logout_yes);
        mBtnLogoutYes.setOnClickListener(this);
        mBtnCancel = root.findViewById(R.id.btn_logout_no);
        mBtnCancel.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_logout:
                // do nothing
                break;
            case R.id.btn_logout_yes:
                LoginManager.getInstance().logOut();
                mPresenter.showLogoutSuccessDialog();
                mPresenter.showLoginFragment();
                break;
            case R.id.btn_logout_no:
                dismiss();
                break;
            default:
                dismiss();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void setPresenter(LogoutContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void openLoginFragment() {

    }
}
