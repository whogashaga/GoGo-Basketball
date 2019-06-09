package com.kerry.gogobasketball.home.item.filter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.data.WaitingRoomInfo;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class CourtsFilterDialog extends DialogFragment implements CourtsFilterContract.View,
        View.OnClickListener, WheelPicker.OnItemSelectedListener {

    private CourtsFilterContract.Presenter mPresenter;
    private View mLayoutDialog;
    private View mLayout;
    private Button mBtnCancel;
    private Button mBtnConfirm;
    private WheelPicker mWheelPosition;
    private TextView textViewTitle;

    @Override
    public void setPresenter(CourtsFilterContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
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

        textViewTitle = root.findViewById(R.id.text_change_position_title);

        mWheelPosition = root.findViewById(R.id.wheel_change_position);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewTitle.setText("請篩選場地");
        mWheelPosition.setOnItemSelectedListener(this);
        mPresenter.getCourtsListFromFirebase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_change_position:
                // do nothing
                break;
            case R.id.btn_change_position_yes:
                mPresenter.queryCourts();
                mBtnConfirm.setClickable(false);
                break;
            default:
                dismiss();
                break;
        }
    }

    @Override
    public void setWheelPicker(ArrayList<String> courtsList) {
        mWheelPosition.setData(courtsList);
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        mPresenter.getLocationFromWheel(String.valueOf(data.toString().trim()));
    }

    @Override
    public void showFindNoCourts() {
        mPresenter.showErrorToast("此場地目前無人開房！", true);
        mBtnConfirm.setClickable(true);
    }

    @Override
    public void showCourtsFilterSuccessUi(ArrayList<WaitingRoomInfo> list) {
        mPresenter.updateLooking4RoomView(list);
        dismiss();
    }

    @Override
    public void finishCourtsFilterUi() {

    }
}
