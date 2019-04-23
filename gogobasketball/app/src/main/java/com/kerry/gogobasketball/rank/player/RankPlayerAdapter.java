package com.kerry.gogobasketball.rank.player;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.component.SeatAvatarOutlineProvider;
import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.util.Constants;
import com.kerry.gogobasketball.util.ImageManager;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class RankPlayerAdapter extends RecyclerView.Adapter {

    RankPlayerContract.Presenter mPresenter;
    private ArrayList<User> mUserList;
    private String mRecordType;

    public RankPlayerAdapter(RankPlayerContract.Presenter presenter) {
        mPresenter = presenter;
        mRecordType = "";
        mUserList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new RankPlayerAdapter.RankPlayerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rank_player, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof RankPlayerAdapter.RankPlayerViewHolder) {

            bindView((RankPlayerAdapter.RankPlayerViewHolder) holder, mUserList.get(position), position);
        }
    }

    private void bindView(RankPlayerViewHolder holder, User user, int position) {

        // set Rank
        holder.getTextRank().setText(String.valueOf(position + 1));

        // set Avatar
        ImageManager.getInstance().setImageByUrl(holder.getAavatar(), user.getAvatar());

        // set Id
        holder.getTextUserId().setText(user.getId());

        // set Gender
        if (user.getGender().equals("male")) {
            holder.getGender().setImageResource(R.drawable.ic_male);
        } else {
            holder.getGender().setImageResource(R.drawable.ic_female);
        }

        // set Position
        setPositionImage(user, holder.getUserPosition());



        // set Record
        DecimalFormat avDf = new DecimalFormat("0.00");

        if (mRecordType.equals(GoGoBasketball.getAppContext().getString(R.string.rank_total_games))) {
            holder.getRecordContent().setText(String.valueOf(user.getPlayerRecord().getGames()));

        } else if (mRecordType.equals(GoGoBasketball.getAppContext().getString(R.string.rank_win_rate))) {
            String winRateStr = avDf.format(user.getPlayerRecord().getAvWinRate() * 100);
            holder.getRecordContent().setText(winRateStr + "%");

        } else if (mRecordType.equals(GoGoBasketball.getAppContext().getString(R.string.rank_av_score))) {
            String avScore = avDf.format(user.getPlayerRecord().getAvScore());
            holder.getRecordContent().setText(avScore);

        } else if (mRecordType.equals(GoGoBasketball.getAppContext().getString(R.string.rank_av_rebound))) {
            String avRebound = avDf.format(user.getPlayerRecord().getAvRebound());
            holder.getRecordContent().setText(avRebound);

        } else if (mRecordType.equals(GoGoBasketball.getAppContext().getString(R.string.rank_av_foul))) {
            String avFoul = avDf.format(user.getPlayerRecord().getAvFoul());
            holder.getRecordContent().setText(avFoul);
        } else {
            Log.d("Kerry", "Rank Player Adapter bindView Error !!");
        }

    }

    public void setPositionImage(User user, ImageView imageView) {

        if (user.getPosition().equals(Constants.POSITION_PG)) {
            imageView.setImageResource(R.drawable.ic_position_pg);
        } else if (user.getPosition().equals(Constants.POSITION_SG)) {
            imageView.setImageResource(R.drawable.ic_position_sg);
        } else if (user.getPosition().equals(Constants.POSITION_SF)) {
            imageView.setImageResource(R.drawable.ic_position_sf);
        } else if (user.getPosition().equals(Constants.POSITION_PF)) {
            imageView.setImageResource(R.drawable.ic_position_pf);
        } else if (user.getPosition().equals(Constants.POSITION_CENTER)) {
            imageView.setImageResource(R.drawable.ic_position_center);
        } else {
            Log.e(Constants.TAG, "Set Position Image Error!!");
        }
    }

    private class RankPlayerViewHolder extends RecyclerView.ViewHolder {

        private View mLayout;

        private TextView mTextRank;
        private TextView mTextUserId;
        private ImageView mAvatar;
        private ImageView mGender;
        private ImageView mPosition;
        private TextView mRecordContent;

        public RankPlayerViewHolder(@NonNull View itemView) {
            super(itemView);

            mLayout = itemView.findViewById(R.id.layout_item_rank_player);
            mTextRank = itemView.findViewById(R.id.text_rank_player_ranking);
            mTextUserId = itemView.findViewById(R.id.text_rank_player_id);
            mAvatar = itemView.findViewById(R.id.image_rank_player_avatar);
            mAvatar.setOutlineProvider(new SeatAvatarOutlineProvider());
            mGender = itemView.findViewById(R.id.image_rank_player_gender);
            mPosition = itemView.findViewById(R.id.image_rank_player_position);
            mRecordContent = itemView.findViewById(R.id.text_rank_player_number);

        }

        public TextView getRecordContent() {
            return mRecordContent;
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
            return mAvatar;
        }

        public ImageView getGender() {
            return mGender;
        }

        public ImageView getUserPosition() {
            return mPosition;
        }
    }

    @Override
    public int getItemCount() {
        if (mUserList == null) {
            return 0;
        }
        return mUserList.size();
    }


    public void updateData(ArrayList<User> arrayList, String recordType) {
        mRecordType = recordType;
        mUserList = arrayList;
        notifyDataSetChanged();
    }
}
