package com.kerry.gogobasketball.rank.referee;

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

import static com.google.common.base.Preconditions.checkNotNull;

public class RankRefereeFragment extends Fragment implements RankRefereeContract.View, SwipeRefreshLayout.OnRefreshListener {

    private RankRefereeContract.Presenter mPresenter;

    private ArrayList<User> mUserList;

    private Spinner mSpinnerRankReferee;
    private ArrayList<String> mRefereeRecordList;
    private RecyclerView mRecyclerView;
    private RankRefereeAdapter mRankRefereeAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mRecordTitle;
    private String mNowSpinnerItem;

    public RankRefereeFragment() {
        mUserList = new ArrayList<>();
    }

    public static RankRefereeFragment newInstance() {
        return new RankRefereeFragment();
    }

    @Override
    public void setPresenter(RankRefereeContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
        mNowSpinnerItem = "";
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
    public void showRankRefereeUi(ArrayList<User> arrayList, String recordType) {
        mUserList.clear();
        mUserList.addAll(arrayList);
        mRankRefereeAdapter.updateData(mUserList, recordType);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rank_child_referee, container, false);

        mSwipeRefreshLayout = root.findViewById(R.id.swipe_container_rank_referee);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        mRecordTitle = root.findViewById(R.id.text_child_referee_record_title);

        mRankRefereeAdapter = new RankRefereeAdapter(mPresenter);
        mRecyclerView = root.findViewById(R.id.recycler_rank_child_referee);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, getContext().getResources().getDimensionPixelSize(R.dimen.space_rank_grid), true));
        mRecyclerView.setAdapter(mRankRefereeAdapter);

        mSpinnerRankReferee = root.findViewById(R.id.spinner_rank_referee);
        setSpinnerRankReferee();

        return root;
    }

    public void setSpinnerRankReferee() {
        mRefereeRecordList = new ArrayList<>();
        mRefereeRecordList.add(getString(R.string.rank_total_justices));
        mRefereeRecordList.add(getString(R.string.rank_referee_rating));

        String[] recordArray = new String[mRefereeRecordList.size()];
        recordArray = mRefereeRecordList.toArray(recordArray);

        ArrayAdapter<String> recordsAdapter = new ArrayAdapter<>(GoGoBasketball.getAppContext(),
                android.R.layout.simple_spinner_dropdown_item, recordArray);
        mSpinnerRankReferee.setAdapter(recordsAdapter);
        mSpinnerRankReferee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getSelectedItem().toString().equals(getString(R.string.rank_total_justices))) {
                    mPresenter.loadRankRefereeByJustices();
                    mRecordTitle.setText(getString(R.string.rank_total_justices));

                } else if (parent.getSelectedItem().toString().equals(getString(R.string.rank_referee_rating))) {
                    mPresenter.loadRankRefereeByRating();
                    mRecordTitle.setText(getString(R.string.rank_referee_rating));

                } else {
                    Log.d(Constants.TAG, "setSpinnerRankReferee Error !!");
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
        if (mNowSpinnerItem.equals(getString(R.string.rank_total_justices))) {
            mPresenter.loadRankRefereeByJustices();
        } else if (mNowSpinnerItem.equals(getString(R.string.rank_referee_rating))) {
            mPresenter.loadRankRefereeByRating();
        }
    }
}
