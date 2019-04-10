package com.kerry.gogobasketball.home.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.util.Constants;

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

        // Set host name
        holder.getHostName().setText(waitingRoomInfo.getHostName());

        // Set current player amount
        holder.getPlayerCount().setText(String.valueOf(waitingRoomInfo.getPlayerAmount()));

        // Set current referee amount
        holder.getRefereeCount().setText(String.valueOf(waitingRoomInfo.getRefereeAmount()));

        holder.getLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                determineOpenWaiting4Join(waitingRoomInfo);
            }
        });

    }

    private void determineOpenWaiting4Join(WaitingRoomInfo waitingRoomInfo) {

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .whereEqualTo("hostName", waitingRoomInfo.getHostName())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                determinePlayerAmount(document.getId());
                            }
                        } else {
                            Log.w("Kerry", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void determinePlayerAmount(String roomDocId) {
        DocumentReference docRef = FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(roomDocId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                WaitingRoomInfo waitingRoomInfo = documentSnapshot.toObject(WaitingRoomInfo.class);
                if (waitingRoomInfo.getTotalPlayerAmount() < 7) {
                    mPresenter.openWaiting4JoinSlave(waitingRoomInfo);
                } else {
                    mPresenter.showErrorToast("\""+waitingRoomInfo.getRoomName()+"\" 人數已滿 !");
                }
            }
        });
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
        private TextView mPlayerCount;
        private TextView mHostName;
        private TextView mLocation;
        private TextView mRefereeCount;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);

            mRoomName = itemView.findViewById(R.id.text_item_room_name);
            mLocation = itemView.findViewById(R.id.text_item_room_location);
            mHostName = itemView.findViewById(R.id.text_item_room_host_name);

            mPlayerCount = itemView.findViewById(R.id.text_item_room_current_player);
            mRefereeCount = itemView.findViewById(R.id.text_item_room_current_referee);

            mLayout = itemView.findViewById(R.id.item_child_looking4room);

        }

        public TextView getHostName() {
            return mHostName;
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
