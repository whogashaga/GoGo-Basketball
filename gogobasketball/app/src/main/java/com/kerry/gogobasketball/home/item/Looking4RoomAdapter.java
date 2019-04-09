package com.kerry.gogobasketball.home.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.data.WaitingRoomInfo;

import java.util.ArrayList;

public class Looking4RoomAdapter extends RecyclerView.Adapter {

    Looking4RoomContract.Presenter mPresenter;
    private ArrayList<WaitingRoomInfo> mWaitingRoomList;

    public Looking4RoomAdapter(Looking4RoomContract.Presenter presenter) {
        mPresenter = presenter;
        mWaitingRoomList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new Looking4RoomAdapter.RoomViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_looking4rooms, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof Looking4RoomAdapter.RoomViewHolder) {

            bindView((Looking4RoomAdapter.RoomViewHolder) holder, mWaitingRoomList.get(position));
        }

    }

    private void bindView(RoomViewHolder holder, WaitingRoomInfo waitingRoomInfo) {

        // Set room name
        holder.getRoomName().setText(waitingRoomInfo.getRoomName());

        // Set location
        holder.getLocation().setText(waitingRoomInfo.getCourtLocation());

        // Set current player amount
        holder.getPlayerCount().setText(String.valueOf(waitingRoomInfo.getPlayerAmount()));

        // Set current referee amount
        holder.getRefereeCount().setText(String.valueOf(waitingRoomInfo.getRefereeAmount()));

    }

    public void updateData(ArrayList<WaitingRoomInfo> roomInfoList) {
        mWaitingRoomList.addAll(roomInfoList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mWaitingRoomList == null) {
            return 0;
        }
        return mWaitingRoomList.size();
    }

    private class RoomViewHolder extends RecyclerView.ViewHolder {

        private View mLayout;

        private TextView mRoomName;
        private TextView mLocation;
        private TextView mPlayerCount;
        private TextView mRefereeCount;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);

            mRoomName = itemView.findViewById(R.id.text_item_room_name);
            mLocation = itemView.findViewById(R.id.text_item_room_location);

            mPlayerCount = itemView.findViewById(R.id.text_item_room_current_player);
            mRefereeCount = itemView.findViewById(R.id.text_item_room_current_referee);

            mLayout = itemView.findViewById(R.id.item_child_looking4room);
            mLayout.setOnClickListener(view -> {
                mPresenter.openWaiting4JoinSlave();
            });
        }

        public TextView getRoomName() {
            return mRoomName;
        }

        public TextView getLocation() {
            return mLocation;
        }

        public TextView getPlayerCount() {
            return mPlayerCount;
        }

        public TextView getRefereeCount() {
            return mRefereeCount;
        }

        public View getLayout() {
            return mLayout;
        }
    }

}
