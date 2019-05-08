package com.kerry.gogobasketball.profile.position;

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

import com.aigestudio.wheelpicker.WheelPicker;
import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.util.Constants;

import java.util.ArrayList;

public class ChangePositionDialog extends DialogFragment implements ChangePositionContract.View,
        View.OnClickListener, WheelPicker.OnItemSelectedListener {

    private ChangePositionContract.Presenter mPresenter;
    private View mLayoutDialog;
    private View mLayout;
    private Button mBtnCancel;
    private Button mBtnConfirm;
    private WheelPicker mWheelPosition;
    private ArrayList<String> mPositionList;
    private String mNowPosition;

    @Override
    public void setPresenter(ChangePositionContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
        mPositionList = new ArrayList<>();
        mNowPosition = GoGoBasketball.getAppContext().getString(R.string.position_center);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.CommentRefereeDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dialog_change_position, container, false);

        mLayoutDialog = root.findViewById(R.id.dialog_change_position);
        mLayoutDialog.setOnClickListener(this);
        mLayout = root.findViewById(R.id.layout_change_position);
        mLayout.setOnClickListener(this);
        mBtnConfirm = root.findViewById(R.id.btn_change_position_yes);
        mBtnConfirm.setOnClickListener(this);
        mBtnCancel = root.findViewById(R.id.btn_change_position_no);
        mBtnCancel.setOnClickListener(this);

        mWheelPosition = root.findViewById(R.id.wheel_change_position);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_change_position:
                // do nothing
                break;
            case R.id.btn_change_position_yes:
                mBtnConfirm.setClickable(false);
                mPresenter.compareNewOldPosition(getActivity());
                break;
            default:
                dismiss();
                break;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPositionList.add(getString(R.string.position_center));
        mPositionList.add(getString(R.string.position_pf));
        mPositionList.add(getString(R.string.position_sf));
        mPositionList.add(getString(R.string.position_pg));
        mPositionList.add(getString(R.string.position_sg));
        mWheelPosition.setOnItemSelectedListener(this);
        mWheelPosition.setData(mPositionList);

    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        switch (picker.getId()) {
            case R.id.wheel_change_position:
                mNowPosition = String.valueOf(data.toString());
                mPresenter.getPositionFromWheel(String.valueOf(data.toString().trim()));
                break;
            default:
                break;
        }
    }

    @Override
    public void showChangePositionSuccessUi() {
        mPresenter.showDataChangeSuccessDialog();
    }

    @Override
    public void showNewProfileUi() {
        mPresenter.loadProfileUserData(getActivity());
    }

    @Override
    public void finishChangePositionUi() {
        dismiss();
    }

    @Override
    public void showErrorPosition() {
        mBtnConfirm.setClickable(true);
        if (mNowPosition.equals(getString(R.string.position_center))) {
            mPresenter.showErrorToast(getString(R.string.gender_already_, mNowPosition), true);
        } else if (mNowPosition.equals(getString(R.string.position_pf))) {
            mPresenter.showErrorToast(getString(R.string.gender_already_, mNowPosition), true);
        } else if (mNowPosition.equals(getString(R.string.position_sf))) {
            mPresenter.showErrorToast(getString(R.string.gender_already_, mNowPosition), true);
        } else if (mNowPosition.equals(getString(R.string.position_pg))) {
            mPresenter.showErrorToast(getString(R.string.gender_already_, mNowPosition), true);
        } else if (mNowPosition.equals(getString(R.string.position_sg))) {
            mPresenter.showErrorToast(getString(R.string.gender_already_, mNowPosition), true);
        } else {
            Log.d(Constants.TAG, "ChangePosition showErrorPosition Error !");
        }
    }

}
