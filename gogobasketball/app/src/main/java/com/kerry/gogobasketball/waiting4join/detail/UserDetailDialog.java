package com.kerry.gogobasketball.waiting4join.detail;

import static com.google.common.base.Preconditions.checkNotNull;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.component.SeatAvatarOutlineProvider;
import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.util.ImageManager;

import java.text.DecimalFormat;

public class UserDetailDialog extends DialogFragment implements UserDetailContract.View, View.OnClickListener {

    private UserDetailContract.Presenter mPresenter;
    private View mLayout;
    private View mLayoutDialog;
    private View mRoot;
    private Button mBtnClose;
    private ImageView mAvatar, mGender, mPosition;
    private TextView mUserId;
    private TextView mGames, mWinRate, mAvScore, mAvRebound, mAvFoul;
    private TextView mJustice, mRefereeRating;

    @Override
    public void setPresenter(UserDetailContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.CommentRefereeDialog);
    }

    @Override
    public void onResume() {
        super.onResume();
        mRoot.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.dialog_user_detail, container, false);

        mLayoutDialog = mRoot.findViewById(R.id.dialog_user_detail);
        mLayoutDialog.setOnClickListener(this);
        mLayout = mRoot.findViewById(R.id.layout_user_detail);
        mLayout.setOnClickListener(this);

        mBtnClose = mRoot.findViewById(R.id.btn_user_detail_close);
        mBtnClose.setOnClickListener(this);

        mAvatar = mRoot.findViewById(R.id.image_detail_user_avatar);
        mAvatar.setOutlineProvider(new SeatAvatarOutlineProvider());
        mGender = mRoot.findViewById(R.id.image_detail_user_gender);
        mPosition = mRoot.findViewById(R.id.image_detail_user_position);
        mUserId = mRoot.findViewById(R.id.text_detail_user_id);

        mGames = mRoot.findViewById(R.id.text_detail_game_content);
        mWinRate = mRoot.findViewById(R.id.text_detail_win_rate_content);
        mAvScore = mRoot.findViewById(R.id.text_detail_av_score_content);
        mAvRebound = mRoot.findViewById(R.id.text_detail_av_rebound_content);
        mAvFoul = mRoot.findViewById(R.id.text_detail_av_foul_content);
        mJustice = mRoot.findViewById(R.id.text_detail_justices_content);
        mRefereeRating = mRoot.findViewById(R.id.text_detail_referee_rating_content);

        return mRoot;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.layout_user_detail:
                // do nothing
                break;
            default:
                dismiss();
                break;
        }
    }

    @Override
    public void shotDetailUi(User user) {
        // set ID
        mUserId.setText(user.getId());

        // set Avatar
        ImageManager.getInstance().setImageByUrl(mAvatar, user.getAvatar());

        // set Gender
        ImageManager.getInstance().setGenderImage(mGender, user.getGender());

        // set Position
        ImageManager.getInstance().setPositionImage(mPosition, user.getPosition());

        // set Record
        mGames.setText(String.valueOf(user.getPlayerRecord().getGames()));

        DecimalFormat avDf = new DecimalFormat("0.00");
        String winRateStr = avDf.format(user.getPlayerRecord().getAvWinRate() * 100);
        mWinRate.setText(winRateStr + "%");

        String avScore = avDf.format(user.getPlayerRecord().getAvScore());
        mAvScore.setText(avScore);

        String avRebound = avDf.format(user.getPlayerRecord().getAvRebound());
        mAvRebound.setText(avRebound);

        String avFoul = avDf.format(user.getPlayerRecord().getAvFoul());
        mAvFoul.setText(avFoul);

        // set Referee Record
        mJustice.setText(String.valueOf(user.getRefereeRecord().getJustices()));

        String refereeRating = avDf.format(user.getRefereeRecord().getAvRating());
        mRefereeRating.setText(refereeRating);
    }

    public void setGenderImage(User user, ImageView gender) {
        if (user.getGender().equals("male")) {
            gender.setImageResource(R.drawable.ic_male);
        } else {
            gender.setImageResource(R.drawable.ic_female);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mRoot.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    public void finishDetailUi() {
        dismiss();
    }

}
