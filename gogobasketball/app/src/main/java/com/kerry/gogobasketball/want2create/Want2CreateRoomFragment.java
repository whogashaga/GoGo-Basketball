package com.kerry.gogobasketball.want2create;

import static com.google.common.base.Preconditions.checkNotNull;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;

import java.util.ArrayList;

public class Want2CreateRoomFragment extends Fragment implements Want2CreateRoomContract.View,
        RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private Want2CreateRoomContract.Presenter mPresenter;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioRefereeYes;
    private RadioButton mRadioRefereeNo;
    private TextView mTextRefereeWarning;
    private Button mBtnCreateConfirm;
    private Button mBtnCreateCancel;
    private ImageButton mBtnBackStack;
    private EditText mEditorRoomName;
    private String mRoomName;
    private Spinner mSpinnerCourts;
    private ArrayList<String> mCourtsList;
    private WaitingRoomInfo mWaitingRoomInfo;
    private WaitingRoomSeats mHostSeatInfo;
    private String mRoomDocId;

    public Want2CreateRoomFragment() {
        // Requires empty public constructor
        mWaitingRoomInfo = new WaitingRoomInfo();
        mHostSeatInfo = new WaitingRoomSeats();
        mRoomDocId = "";
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

        mTextRefereeWarning = root.findViewById(R.id.text_want2create_warning);
        mRadioGroup = root.findViewById(R.id.radios_referee_selector);
        mRadioGroup.setOnCheckedChangeListener(this);

        mBtnCreateConfirm = root.findViewById(R.id.btn_want2create_build_confirm);
        mBtnCreateConfirm.setOnClickListener(this);
        mBtnCreateConfirm.setClickable(false);
        mBtnCreateConfirm.setBackgroundResource(R.drawable.btn_gray_ripple);

        mBtnCreateCancel = root.findViewById(R.id.btn_want2create_build_cancel);
        mBtnCreateCancel.setOnClickListener(this);

        mBtnBackStack = root.findViewById(R.id.btn_want2create_backstack);
        mBtnBackStack.setOnClickListener(this);

        mEditorRoomName = root.findViewById(R.id.edit_want2create_room_name_content);

        mSpinnerCourts = root.findViewById(R.id.spinner_courts_selector);
        setSpinnerCourts();

        return root;
    }

    public void setSpinnerCourts() {
        mCourtsList = new ArrayList<>();
        mCourtsList.add("青年公園");
        mCourtsList.add("大安森林公園");
        mCourtsList.add("建國高架球場");

        String[] courtsArray = new String[mCourtsList.size()];
        courtsArray = mCourtsList.toArray(courtsArray);

        ArrayAdapter<String> courtsAdapter = new ArrayAdapter<>(GoGoBasketball.getAppContext(),
                android.R.layout.simple_spinner_dropdown_item, courtsArray);
        mSpinnerCourts.setAdapter(courtsAdapter);
        mSpinnerCourts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Log.e("Kerry", "now selected = " + parent.getSelectedItem().toString());
                mPresenter.getCourtLocationFromSpinner(parent.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_want2create_build_confirm:
                mPresenter.updateRoomInfo2FireStore();
                break;
            case R.id.btn_want2create_build_cancel:
                mPresenter.finishWant2CreateRoomUi();
                break;
            case R.id.btn_want2create_backstack:
                mPresenter.finishWant2CreateRoomUi();
                break;
            default:
                break;
        }
    }

    @Override
    public void getRoomInfoFromPresenter(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats waitingRoomSeats, String roomDocId) {
        mWaitingRoomInfo = waitingRoomInfo;
        mHostSeatInfo = waitingRoomSeats;
        mRoomDocId = roomDocId;
        mPresenter.openWaitingJoin(waitingRoomInfo, waitingRoomSeats, roomDocId);
        mPresenter.updateUserInfo2FireBase(waitingRoomSeats, roomDocId);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radios_referee_yes:
                mTextRefereeWarning.setText("裁判模式結果將列入天梯排名");
                mTextRefereeWarning.setTextColor(GoGoBasketball.getAppContext().getColor(R.color.blue_facebook));
                mPresenter.getRefereeOnOffFromRadioGroup(true);
                if (mEditorRoomName.getText().length() != 0) {
                    setBtnCreateConfirmClickable(true);
                }
                break;
            case R.id.radios_referee_no:
                mTextRefereeWarning.setText("暫不支援非裁判模式～");
                mTextRefereeWarning.setTextColor(GoGoBasketball.getAppContext().getColor(R.color.red_FF001F));
                mPresenter.getRefereeOnOffFromRadioGroup(false);
                setBtnCreateConfirmClickable(false);
                break;
            default:
                break;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.hideToolbarAndBottomNavigation();
        mEditorRoomName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!"".equals(s.toString()) && s.length() < 11) {
                    mPresenter.onRoomNameEditTextChange(s);
                    mEditorRoomName.setFocusable(true);
                    setBtnCreateConfirmClickable(true);
                    if (s.length() == 10) {
                        mPresenter.showErrorToast(GoGoBasketball.getAppContext().getString(R.string.at_most_10_word), true);
                    }
                } else if (s.length() == 0) {
                    mPresenter.showErrorToast(GoGoBasketball.getAppContext().getString(R.string.edit_room_name), true);
                    setBtnCreateConfirmClickable(false);
                } else {
                    Log.d("Kerry", "no this kind of situation!");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.showToolbarAndBottomNavigation();
    }

    @Override
    public boolean needReferee() {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    public void setBtnCreateConfirmClickable(boolean clickable) {

        if (clickable) {
            mBtnCreateConfirm.setClickable(true);
            mBtnCreateConfirm.setBackgroundResource(R.drawable.btn_black_ripple);
        } else {
            mBtnCreateConfirm.setClickable(false);
            mBtnCreateConfirm.setBackgroundResource(R.drawable.btn_gray_ripple);
        }
    }

}
