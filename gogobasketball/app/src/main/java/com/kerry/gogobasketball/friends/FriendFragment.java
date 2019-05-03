package com.kerry.gogobasketball.friends;

import static com.google.common.base.Preconditions.checkNotNull;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kerry.gogobasketball.R;

public class FriendFragment extends Fragment implements FriendContract.View, View.OnClickListener {

    private FriendContract.Presenter mPresenter;

    private Button mBtnWayne;
    private Button mBtnMike;
    private Button mBtnJohnson;
    private Button mBtnClaire;
    private Button mBtnKimJonAn;
    private Button mBtnUpdateNumber;
    private Button mBtnTony;

    public FriendFragment() {
        // Requires empty public constructor
    }

    public static FriendFragment newInstance() {
        return new FriendFragment();
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

        mBtnTony = root.findViewById(R.id.button_tony);
        mBtnTony.setOnClickListener(this);

        mBtnWayne = root.findViewById(R.id.button_an_teacher);
        mBtnWayne.setOnClickListener(this);

        mBtnMike = root.findViewById(R.id.button_mike);
        mBtnMike.setOnClickListener(this);

        mBtnJohnson = root.findViewById(R.id.button_johnson);
        mBtnJohnson.setOnClickListener(this);

        mBtnClaire = root.findViewById(R.id.button_claire);
        mBtnClaire.setOnClickListener(this);

        mBtnKimJonAn = root.findViewById(R.id.button_mark);
        mBtnKimJonAn.setOnClickListener(this);

        mBtnUpdateNumber = root.findViewById(R.id.button_update_number);
        mBtnUpdateNumber.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_tony:
                mPresenter.postDemoPlayer(0);
                break;
            case R.id.button_an_teacher:
                mPresenter.postDemoPlayer(1);
                break;
            case R.id.button_mike:
                mPresenter.postDemoPlayer(2);
                break;
            case R.id.button_johnson:
                mPresenter.postDemoPlayer(3);
                break;
            case R.id.button_claire:
                mPresenter.postDemoPlayer(4);
                break;
            case R.id.button_mark:
                mPresenter.postDemoPlayer(5);
                break;
            case R.id.button_update_number:
                mPresenter.updateDemoTotalNumber();
                break;
        }
    }

    @Override
    public void showFriendUi() {

    }
}
