package com.kerry.gogobasketball.profile;

import static com.google.common.base.Preconditions.checkNotNull;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.android.gms.maps.GoogleMap;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.component.ProfileAvatarOutlineProvider;
import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.util.Constants;
import com.kerry.gogobasketball.util.ImageManager;
import com.kerry.gogobasketball.util.UserManager;

import java.text.DecimalFormat;

public class ProfileFragment extends Fragment implements ProfileContract.View, View.OnClickListener {

    private ProfileContract.Presenter mPresenter;

    private ImageView mAvatar, mGender, mPosition;
    private TextView mFbName, mUserId;
    private TextView mTotalGames, mWinRate;
    private TextView mTotalScore, mTotalRebound, mTotalFoul;
    private TextView mAvScore, mAvRebound, mAvFoul;
    private TextView mJustices, mRefereeRating;

    private View mBtnHeader;
    private Button mBtnChangeId;
    private Button mBtnChangeGender;
    private Button mBtnChangePosition;
    private Button mBtnLogout;
    private Button mBtnSetting;

    private String mCurrentGender;
    private String mCurrentPosition;
    private boolean mSettingIsChecked;

    public ProfileFragment() {
        // Requires empty public constructor
        mCurrentGender = "";
        mCurrentPosition = "";
        mSettingIsChecked = false;
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
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
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, parent, false);

        mAvatar = root.findViewById(R.id.image_profile_avatar);
        mAvatar.setOutlineProvider(new ProfileAvatarOutlineProvider());
        mGender = root.findViewById(R.id.image_profile_gender);
        mPosition = root.findViewById(R.id.image_profile_position);
        mFbName = root.findViewById(R.id.text_profile_name);
        mUserId = root.findViewById(R.id.text_profile_Id);

        mTotalGames = root.findViewById(R.id.text_profile_total_games_content);
        mWinRate = root.findViewById(R.id.text_profile_win_rate_content);
        mTotalScore = root.findViewById(R.id.text_profile_total_score_content);
        mTotalRebound = root.findViewById(R.id.text_profile_total_rebound_content);
        mTotalFoul = root.findViewById(R.id.text_profile_total_foul_content);

        mAvScore = root.findViewById(R.id.text_profile_av_score_content);
        mAvRebound = root.findViewById(R.id.text_profile_av_rebound_content);
        mAvFoul = root.findViewById(R.id.text_profile_av_foul_content);

        mJustices = root.findViewById(R.id.text_profile_total_justices_content);
        mRefereeRating = root.findViewById(R.id.text_profile_referee_rating_content);

        mBtnHeader = root.findViewById(R.id.layout_profile_btn_header);
        mBtnHeader.setVisibility(View.GONE);
        mBtnChangeId = root.findViewById(R.id.btn_profile_change_id);
        mBtnChangeId.setOnClickListener(this);
        mBtnChangeGender = root.findViewById(R.id.btn_profile_change_gender);
        mBtnChangeGender.setOnClickListener(this);
        mBtnChangePosition = root.findViewById(R.id.btn_profile_change_position);
        mBtnChangePosition.setOnClickListener(this);
        mBtnLogout = root.findViewById(R.id.btn_profile_facebook_logout);
        mBtnLogout.setOnClickListener(this);
        mBtnSetting = root.findViewById(R.id.btn_profile_setting);
        mBtnSetting.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_profile_change_id:
                mPresenter.openChangeIdDialog();
                break;
            case R.id.btn_profile_change_gender:
                mPresenter.openChangeGender(mCurrentGender);
                break;
            case R.id.btn_profile_change_position:
                mPresenter.openChangePosition(mCurrentPosition);
                break;
            case R.id.btn_profile_facebook_logout:
                mPresenter.openLogoutDialog();
                break;
            case R.id.btn_profile_setting:
                if (!mSettingIsChecked) {
                    mBtnHeader.setVisibility(View.VISIBLE);
                    mBtnSetting.setBackgroundResource(R.drawable.ic_settings_selected);
                    mSettingIsChecked = true;
                } else {
                    mBtnHeader.setVisibility(View.GONE);
                    mBtnSetting.setBackgroundResource(R.drawable.ic_settings);
                    mSettingIsChecked = false;
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.loadProfileUserData(getActivity());
    }

    @Override
    public void showUserUi(User user) {
        // set Avatar
        ImageManager.getInstance().setImageByUrl(mAvatar, user.getAvatar());

        // set Gender
        setGenderImage(user, mGender);
        mCurrentGender = user.getGender();

        // set Position
        setPositionImage(user, mPosition);
        mCurrentPosition = user.getPosition();

        // set FB Name
        mFbName.setText(user.getName());

        // set ID
        mUserId.setText(user.getId());

        // set Player Record
        mTotalGames.setText(String.valueOf(user.getPlayerRecord().getGames()));
        mTotalScore.setText(String.valueOf(user.getPlayerRecord().getScore()));
        mTotalRebound.setText(String.valueOf(user.getPlayerRecord().getRebound()));
        mTotalFoul.setText(String.valueOf(user.getPlayerRecord().getFoul()));

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
        mJustices.setText(String.valueOf(user.getRefereeRecord().getJustices()));

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
        } else if (user.getPosition().equals("r")) {
            imageView.setImageResource(R.drawable.ic_position_referee);
        } else {
            Log.e(Constants.TAG, "Set Position Error!!");
        }
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showLogoutDialogUi() {

    }


}
