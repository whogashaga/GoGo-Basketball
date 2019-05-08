package com.kerry.gogobasketball.profile.gender;

import static com.google.common.base.Preconditions.checkNotNull;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.util.Constants;

public class ChangeGenderDialog extends DialogFragment implements ChangeGenderContract.View,
        View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private ChangeGenderContract.Presenter mPresenter;
    private View mLayoutDialog;
    private View mLayout;
    private Button mBtnCancel;
    private Button mBtnConfirm;
    private RadioGroup mRadioGroupGender;
    private RadioButton mBoy;
    private RadioButton mGirl;
    private String mNewGender;

    @Override
    public void setPresenter(ChangeGenderContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
        mNewGender = GoGoBasketball.getAppContext().getString(R.string.gender_male);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.CommentRefereeDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dialog_change_gender, container, false);

        mLayoutDialog = root.findViewById(R.id.dialog_change_gender);
        mLayoutDialog.setOnClickListener(this);
        mLayout = root.findViewById(R.id.layout_change_gender);
        mLayout.setOnClickListener(this);
        mBtnConfirm = root.findViewById(R.id.btn_change_gender_yes);
        mBtnConfirm.setOnClickListener(this);
        mBtnCancel = root.findViewById(R.id.btn_change_gender_no);
        mBtnCancel.setOnClickListener(this);

        mRadioGroupGender = root.findViewById(R.id.radios_change_gender);
        mRadioGroupGender.setOnCheckedChangeListener(this);
        mBoy = root.findViewById(R.id.radios_change_gender_male);
        mGirl = root.findViewById(R.id.radios_change_gender_female);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_change_gender:
                // do nothing
                break;
            case R.id.btn_change_gender_yes:
                Log.d(Constants.TAG, "onClick: ");
                mBtnConfirm.setClickable(false);
                mPresenter.compareOldNewGender(getActivity());
                break;
            default:
                dismiss();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radios_change_gender_male:
                mNewGender = getString(R.string.gender_male);
                mPresenter.getGenderFromRadios(Constants.GENDER_MALE);
                break;
            case R.id.radios_change_gender_female:
                mNewGender = getString(R.string.gender_female);
                mPresenter.getGenderFromRadios(Constants.GENDER_FEMALE);
                break;
            default:
                break;
        }

    }

    @Override
    public void showChangeGenderSuccessUi() {
        mPresenter.showDataChangeSuccessDialog();
    }

    @Override
    public void showNewProfileUi() {
        mPresenter.loadProfileUserData(getActivity());
    }

    @Override
    public void finishChangeGenderUi() {
        dismiss();
    }

    @Override
    public void showErrorGender() {
        mBtnConfirm.setClickable(true);
        if (mBoy.isChecked()) {
            mPresenter.showErrorToast(getString(R.string.gender_already_male), true);
        } else {
            mPresenter.showErrorToast(getString(R.string.gender_already_female), true);
        }
    }

}
