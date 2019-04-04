package com.kerry.gogobasketball.want2create;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;

import static com.google.common.base.Preconditions.checkNotNull;

public class Want2CreateRoomFragment extends Fragment implements Want2CreateRoomContract.View, RadioGroup.OnCheckedChangeListener {

    private Want2CreateRoomContract.Presenter mPresenter;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioRefereeYes;
    private RadioButton mRadioRefereeNo;
    private TextView mTextRefereeWarning;

    public Want2CreateRoomFragment() {
        // Requires empty public constructor
    }

    public static Want2CreateRoomFragment newInstance() {
        return new Want2CreateRoomFragment();
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
    public void setPresenter(Want2CreateRoomContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_want2create_room, container, false);

        mRadioGroup = root.findViewById(R.id.radios_timer_selector);
        mRadioRefereeYes = root.findViewById(R.id.radios_timer_yes);
        mRadioRefereeNo = root.findViewById(R.id.radios_timer_no);
        mTextRefereeWarning = root.findViewById(R.id.text_want2create_warning);

        mRadioGroup.setOnCheckedChangeListener(this);


        return root;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.radios_timer_yes:
//                mTextRefereeWarning.setText(GoGoBasketball.getAppContext().getString(R.string.referee_yes));
                mTextRefereeWarning.setText("裁判模式結果將列入天梯排名");
                mTextRefereeWarning.setTextColor(GoGoBasketball.getAppContext().getColor(R.color.orange_FF6025));
                break;
            case R.id.radios_timer_no:
//                mTextRefereeWarning.setText(GoGoBasketball.getAppContext().getString(R.string.referee_no));
                mTextRefereeWarning.setText("非裁判模式結果不列入天梯排名");
                mTextRefereeWarning.setTextColor(GoGoBasketball.getAppContext().getColor(R.color.red_FF001F));
                break;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.hideToolbarAndBottomNavigation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.showToolbarAndBottomNavigation();
    }

    @Override
    public void showWant2CreateRoomUi() {

    }

    @Override
    public boolean needReferee() {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }



}
