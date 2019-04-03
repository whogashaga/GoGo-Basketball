package com.kerry.gogobasketball.rank;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.friends.FriendContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class RankFragment extends Fragment implements RankContract.View {

    private RankContract.Presenter mPresenter;

    public RankFragment() {
        // Requires empty public constructor
    }

    public static RankFragment newInstance() {
        return new RankFragment();
    }

    @Override
    public void showRankUi() {

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
    public void setPresenter(RankContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rank, container, false);

        return root;
    }

}
