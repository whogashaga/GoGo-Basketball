package com.kerry.gogobasketball.home.item;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kerry.gogobasketball.MainMvpController;
import com.kerry.gogobasketball.R;

import static com.google.common.base.Preconditions.checkNotNull;

public class LookingForRoomFragment extends Fragment implements LookingForRoomContract.View {

    private LookingForRoomContract.Presenter mPresenter;


    private String mItemType;

    public LookingForRoomFragment(){}

    public static LookingForRoomFragment newInstance() {
        return new LookingForRoomFragment();
    }

    @Override
    public void showRoomsUi() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home_child_rooms, container, false);

//        mOrderItemAdapter = new OrderItemAdapter(mPresenter, mItemType);
//        RecyclerView recyclerView = root.findViewById(R.id.recycler_order_child);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(mOrderItemAdapter);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void setPresenter(LookingForRoomContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    public void setItemType(@MainMvpController.HomeItem String itemType) {
        mItemType = itemType;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }
}
