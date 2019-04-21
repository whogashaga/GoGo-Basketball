package com.kerry.gogobasketball.rank.referee;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kerry.gogobasketball.R;

import static com.google.common.base.Preconditions.checkNotNull;

public class RankRefereeFragment extends Fragment implements RankRefereeContract.View {

    private RankRefereeContract.Presenter mPresenter;

    public RankRefereeFragment() {
    }

    public static RankRefereeFragment newInstance() {
        return new RankRefereeFragment();
    }

    @Override
    public void setPresenter(RankRefereeContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rank_child_referee, container, false);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showRankRefereeUi() {

    }

    @Override
    public boolean isActive() {
        return false;
    }
}
