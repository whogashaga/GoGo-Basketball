package com.kerry.gogobasketball.home.item.find_host;

import android.app.Activity;
import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class FindHostPresenter implements FindHostContract.Presenter {

    private final FindHostContract.View mFindHostView;
    private String mNewId;


    public FindHostPresenter(@NonNull FindHostContract.View findHostView) {
        mFindHostView = checkNotNull(findHostView, "changeIdView cannot be null!");
        mFindHostView.setPresenter(this);
        mNewId = "";
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void showErrorToast(String message, boolean isShort) {

    }

    @Override
    public void onUserNewIdEditTextChange(CharSequence charSequence) {

    }

    @Override
    public void checkIfUserNewIdExists(Activity activity) {

    }

    @Override
    public void showFindSuccessDialog() {

    }

    @Override
    public void showFindFailDialog() {

    }

    @Override
    public void updateRecyclerView(Activity activity) {

    }

    @Override
    public void start() {

    }
}
