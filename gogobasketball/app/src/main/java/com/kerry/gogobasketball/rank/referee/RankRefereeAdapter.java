package com.kerry.gogobasketball.rank.referee;

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

public class RankRefereeAdapter extends RecyclerView.Adapter {

    RankRefereeContract.Presenter mPresenter;
    private ArrayList<User> mUserList;
    private String mRecordType;

    public RankRefereeAdapter(RankRefereeContract.Presenter presenter) {
        mPresenter = presenter;
        mRecordType = "";
        mUserList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        return new RankRefereeAdapter.RankRefereeViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rank_referee, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RankRefereeAdapter.RankRefereeViewHolder) {

            bindView((RankRefereeAdapter.RankRefereeViewHolder) holder, mUserList.get(position), position);
        }
    }

    private void bindView(RankRefereeViewHolder holder, User user, int position) {

        // set Rank
        holder.getTextRank().setText(String.valueOf(position + 1));

        // set Avatar
        ImageManager.getInstance().setImageByUrl(holder.getAavatar(), user.getAvatar());

        // set Id
        holder.getTextUserId().setText(user.getId());

        // set Gender
        ImageManager.getInstance().setGenderImage(holder.getGender(), user.getGender());

        // set Position
        ImageManager.getInstance().setPositionImage(holder.getUserPosition(), user.getPosition());

        // set Record
        DecimalFormat avDf = new DecimalFormat("0.00");

        if (mRecordType.equals(GoGoBasketball.getAppContext().getString(R.string.rank_total_justices))) {
            holder.getRecordContent().setText(String.valueOf(user.getRefereeRecord().getJustices()));

        } else if (mRecordType.equals(GoGoBasketball.getAppContext().getString(R.string.rank_referee_rating))) {
            String avRating = avDf.format(user.getRefereeRecord().getAvRating());
            holder.getRecordContent().setText(avRating);

        } else {
            Log.d(Constants.TAG, "Rank Referee Adapter bindView Error !!");
        }

    }

    private class RankRefereeViewHolder extends RecyclerView.ViewHolder {

        private View mLayout;

        private TextView mTextRank;
        private TextView mTextUserId;
        private ImageView mAvatar;
        private ImageView mGender;
        private ImageView mPosition;
        private TextView mRecordContent;

        public RankRefereeViewHolder(@NonNull View itemView) {
            super(itemView);

            mLayout = itemView.findViewById(R.id.layout_item_rank_referee);
            mTextRank = itemView.findViewById(R.id.text_rank_referee_ranking);
            mTextUserId = itemView.findViewById(R.id.text_rank_referee_id);
            mAvatar = itemView.findViewById(R.id.image_rank_referee_avatar);
            mAvatar.setOutlineProvider(new SeatAvatarOutlineProvider());
            mGender = itemView.findViewById(R.id.image_rank_referee_gender);
            mPosition = itemView.findViewById(R.id.image_rank_referee_position);
            mRecordContent = itemView.findViewById(R.id.text_rank_referee_number);

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
