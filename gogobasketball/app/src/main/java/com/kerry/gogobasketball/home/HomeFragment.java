package com.kerry.gogobasketball.home;

import static com.google.common.base.Preconditions.checkNotNull;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;
import com.kerry.gogobasketball.MainMvpController;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.home.HomeContract;
import com.kerry.gogobasketball.util.Constants;

public class HomeFragment extends Fragment implements HomeContract.View {

    private HomeContract.Presenter mPresenter;
    private HomeAdapter mHomeAdapter;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public HomeFragment() {

    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeAdapter = new HomeAdapter(getChildFragmentManager(), mPresenter);
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mTabLayout = root.findViewById(R.id.tabs_home);

        mViewPager = root.findViewById(R.id.viewpager_home);

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        Log.d("Kerry", "home fragment onDestroy !!");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.showToolbarAndBottomNavigation();
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(mHomeAdapter);
//        mViewPager.addOnPageChangeListener(
//                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }
}
