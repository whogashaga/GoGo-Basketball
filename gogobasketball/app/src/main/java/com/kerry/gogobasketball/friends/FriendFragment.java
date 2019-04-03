package com.kerry.gogobasketball.friends;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kerry.gogobasketball.R;

import static com.google.common.base.Preconditions.checkNotNull;

public class FriendFragment extends Fragment implements FriendContract.View {

    private FriendContract.Presenter mPresenter;

    public FriendFragment() {
        // Requires empty public constructor
    }

    public static FriendFragment newInstance() {
        return new FriendFragment();
    }

    @Override
    public void showFriendUi() {

    }

    @Override
    public boolean isActive() {
        return false;
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
    public void setPresenter(FriendContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_friends, container, false);

        return root;
    }

}
