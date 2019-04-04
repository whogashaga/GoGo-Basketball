package com.kerry.gogobasketball.waiting4join;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.kerry.gogobasketball.R;

public class Waiting4JoinFragment extends Fragment implements Waiting4JoinContract.View {

    private Waiting4JoinContract.Presenter mPresenter;

    public Waiting4JoinFragment(){}

    public static Waiting4JoinFragment newInstance() {
        return new Waiting4JoinFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_waiting4join, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Kerry","onResume here!");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void showPlayingGameUi() {

    }

    @Override
    public boolean needTimer() {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(Waiting4JoinContract.Presenter presenter) {

    }
}
