package com.kerry.gogobasketball.rank;

import static com.google.common.base.Preconditions.checkNotNull;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kerry.gogobasketball.R;

public class RankFragment extends Fragment implements RankContract.View {

    private RankContract.Presenter mPresenter;
    private RankAdapter mRankAdapter;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public RankFragment() {
        // Requires empty public constructor
    }

    public static RankFragment newInstance() {
        return new RankFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRankAdapter = new RankAdapter(getChildFragmentManager(), mPresenter);
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

        mTabLayout = root.findViewById(R.id.tabs_rank);

        mViewPager = root.findViewById(R.id.viewpager_rank);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.showToolbarAndBottomNavigation();
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(mRankAdapter);
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
