package com.kerry.gogobasketball.rank.player;

import static com.google.common.base.Preconditions.checkNotNull;

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
import android.widget.TextView;

import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.component.GridSpacingItemDecoration;
import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.util.Constants;

import java.util.ArrayList;

public class RankPlayerFragment extends Fragment implements RankPlayerContract.View, SwipeRefreshLayout.OnRefreshListener {

    private RankPlayerContract.Presenter mPresenter;
    private ArrayList<User> mUserList;

    private Spinner mSpinnerRankPlayer;
    private ArrayList<String> mPlayerRecordList;
    private RecyclerView mRecyclerView;
    private RankPlayerAdapter mRankPlayerAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mRecordTitle;
    private String mNowSpinnerItem;

    public RankPlayerFragment() {
        mUserList = new ArrayList<>();
        mNowSpinnerItem = "";
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

    @Override
    public void showRankPlayerUi(ArrayList<User> arrayList, String recordType) {
        mUserList.clear();
        mUserList.addAll(arrayList);
        mRankPlayerAdapter.updateData(mUserList, recordType);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rank_child_player, container, false);

        mSwipeRefreshLayout = root.findViewById(R.id.swipe_container_rank_player);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        mRecordTitle = root.findViewById(R.id.text_child_player_record_title);

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
        mPlayerRecordList = new ArrayList<>();
        mPlayerRecordList.add(getString(R.string.rank_total_games));
        mPlayerRecordList.add(getString(R.string.rank_win_rate));
        mPlayerRecordList.add(getString(R.string.rank_av_score));
        mPlayerRecordList.add(getString(R.string.rank_av_rebound));
        mPlayerRecordList.add(getString(R.string.rank_av_foul));

        String[] recordArray = new String[mPlayerRecordList.size()];
        recordArray = mPlayerRecordList.toArray(recordArray);

        ArrayAdapter<String> recordsAdapter = new ArrayAdapter<>(GoGoBasketball.getAppContext(),
                android.R.layout.simple_spinner_dropdown_item, recordArray);
        mSpinnerRankPlayer.setAdapter(recordsAdapter);
        mSpinnerRankPlayer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getSelectedItem().toString().equals(getString(R.string.rank_total_games))) {
                    mPresenter.loadRankPlayerByGames();
                    mRecordTitle.setText(getString(R.string.rank_total_games));
                    mNowSpinnerItem = getString(R.string.rank_total_games);

                } else if (parent.getSelectedItem().toString().equals(getString(R.string.rank_win_rate))) {
                    mPresenter.loadRankPlayerByWinRate();
                    mRecordTitle.setText(getString(R.string.rank_win_rate));
                    mNowSpinnerItem = getString(R.string.rank_win_rate);

                } else if (parent.getSelectedItem().toString().equals(getString(R.string.rank_av_score))) {
                    mPresenter.loadRankPlayerByAvScore();
                    mRecordTitle.setText(getString(R.string.rank_av_score));
                    mNowSpinnerItem = getString(R.string.rank_av_score);

                } else if (parent.getSelectedItem().toString().equals(getString(R.string.rank_av_rebound))) {
                    mRecordTitle.setText(getString(R.string.rank_av_rebound));
                    mPresenter.loadRankPlayerByAvRebound();
                    mNowSpinnerItem = getString(R.string.rank_av_rebound);

                } else if (parent.getSelectedItem().toString().equals(getString(R.string.rank_av_foul))) {
                    mPresenter.loadRankPlayerByAvFoul();
                    mRecordTitle.setText(getString(R.string.rank_av_foul));
                    mNowSpinnerItem = getString(R.string.rank_av_foul);

                } else {
                    Log.d(Constants.TAG, "setSpinnerRankPlayer Error !!");
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
    public boolean isActive() {
        return false;
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
        if (mNowSpinnerItem.equals(getString(R.string.rank_total_games))) {
            mPresenter.loadRankPlayerByGames();
        } else if (mNowSpinnerItem.equals(getString(R.string.rank_win_rate))) {
            mPresenter.loadRankPlayerByWinRate();
        } else if (mNowSpinnerItem.equals(getString(R.string.rank_av_score))) {
            mPresenter.loadRankPlayerByAvScore();
        } else if (mNowSpinnerItem.equals(getString(R.string.rank_av_rebound))) {
            mPresenter.loadRankPlayerByAvRebound();
        } else if (mNowSpinnerItem.equals(getString(R.string.rank_av_foul))) {
            mPresenter.loadRankPlayerByAvFoul();
        } else {
            Log.d(Constants.TAG, "Player rank onRefresh Error !!");
        }
    }
}
