package com.kerry.gogobasketball.rank.player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.component.GridSpacingItemDecoration;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class RankPlayerFragment extends Fragment implements RankPlayerContract.View, SwipeRefreshLayout.OnRefreshListener {

    private RankPlayerContract.Presenter mPresenter;

    private Spinner mSpinnerRankPlayer;
    private ArrayList<String> mRankPlayerList;
    private RecyclerView mRecyclerView;
    private RankPlayerAdapter mRankPlayerAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public RankPlayerFragment() {
    }

    public static RankPlayerFragment newInstance() {
        return new RankPlayerFragment();
    }

    @Override
    public void setPresenter(RankPlayerContract.Presenter presenter) {
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
        View root = inflater.inflate(R.layout.fragment_rank_child_player, container, false);

        mSwipeRefreshLayout = root.findViewById(R.id.swipe_container_rank_player);

        mRankPlayerAdapter = new RankPlayerAdapter(mPresenter);
        mRecyclerView = root.findViewById(R.id.recycler_rank_child_player);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, getContext().getResources().getDimensionPixelSize(R.dimen.space_rank_grid), true));
        mRecyclerView.setAdapter(mRankPlayerAdapter);

        mSpinnerRankPlayer = root.findViewById(R.id.spinner_rank_player);
        setSpinnerRankPlayer();

        return root;
    }

    public void setSpinnerRankPlayer() {
        mRankPlayerList = new ArrayList<>();
        mRankPlayerList.add(getString(R.string.rank_winning));
        mRankPlayerList.add(getString(R.string.rank_score));
        mRankPlayerList.add(getString(R.string.rank_rebound));
        mRankPlayerList.add(getString(R.string.rank_foul));

        String[] courtsArray = new String[mRankPlayerList.size()];
        courtsArray = mRankPlayerList.toArray(courtsArray);

        ArrayAdapter<String> courtsAdapter = new ArrayAdapter<>(GoGoBasketball.getAppContext(),
                android.R.layout.simple_spinner_dropdown_item, courtsArray);
        mSpinnerRankPlayer.setAdapter(courtsAdapter);
        mSpinnerRankPlayer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Log.e(Constants.TAG, "now selected = " + parent.getSelectedItem().toString());
//                mPresenter.getCourtLocationFromSpinner(parent.getSelectedItem().toString());

                if (parent.getSelectedItem().toString().equals(getString(R.string.rank_winning))) {

                } else if (parent.getSelectedItem().toString().equals(getString(R.string.rank_score))) {

                } else if (parent.getSelectedItem().toString().equals(getString(R.string.rank_rebound))) {

                } else if (parent.getSelectedItem().toString().equals(getString(R.string.rank_foul))) {

                } else {
                    Log.d("Kerry","setSpinnerRankPlayer Error !!");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
    public void showRankPlayerUi() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);

    }
}
