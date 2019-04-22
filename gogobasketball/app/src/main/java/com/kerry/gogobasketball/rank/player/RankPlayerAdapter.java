package com.kerry.gogobasketball.rank.player;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.component.SeatAvatarOutlineProvider;
import com.kerry.gogobasketball.data.User;

import java.util.ArrayList;

public class RankPlayerAdapter extends RecyclerView.Adapter {

    RankPlayerContract.Presenter mPresenter;
    private ArrayList<User> mUserList;

    public RankPlayerAdapter(RankPlayerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new RankPlayerAdapter.RankPlayerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rank_player, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if (holder instanceof RankPlayerAdapter.RankPlayerViewHolder) {

            bindView((RankPlayerAdapter.RankPlayerViewHolder) holder, new User());
        }
    }

    private void bindView(RankPlayerViewHolder holder, User user) {


    }

    @Override
    public int getItemCount() {
        return 16;
    }

    private class RankPlayerViewHolder extends RecyclerView.ViewHolder {

        private View mLayout;

        private TextView mTextRank;
        private TextView mTextUserId;
        private ImageView mAavatar;
        private ImageView mGender;
        private ImageView mPosition;

        public RankPlayerViewHolder(@NonNull View itemView) {
            super(itemView);

            mLayout = itemView.findViewById(R.id.layout_item_rank_player);
            mTextRank = itemView.findViewById(R.id.text_rank_player_ranking);
            mTextUserId = itemView.findViewById(R.id.text_rank_player_id);
            mAavatar = itemView.findViewById(R.id.image_rank_player_avatar);
            mAavatar.setOutlineProvider(new SeatAvatarOutlineProvider());
            mGender = itemView.findViewById(R.id.image_rank_player_gender);
            mPosition = itemView.findViewById(R.id.image_rank_player_position);

        }

        public View getLayout() {
            return mLayout;
        }

        public TextView getTextRank() {
            return mTextRank;
        }

        public TextView getTextUserId() {
            return mTextUserId;
        }

        public ImageView getAavatar() {
            return mAavatar;
        }

        public ImageView getGender() {
            return mGender;
        }

        public ImageView getUserPosition() {
            return mPosition;
        }
    }
}
