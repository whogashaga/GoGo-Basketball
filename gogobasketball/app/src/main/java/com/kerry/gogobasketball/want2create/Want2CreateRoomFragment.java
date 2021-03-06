package com.kerry.gogobasketball.want2create;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static com.google.common.base.Preconditions.checkNotNull;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;
import com.kerry.gogobasketball.util.Constants;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Want2CreateRoomFragment extends Fragment implements Want2CreateRoomContract.View,
        RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private Want2CreateRoomContract.Presenter mPresenter;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioBtnYes;
    private RadioButton mRadioBtnNo;
    private TextView mTextRefereeWarning;
    private Button mBtnCreateConfirm;
    private Button mBtnCreateCancel;
    private ImageButton mBtnBackStack;
    private EditText mEditorRoomName;
    private String mRoomName;
    private Spinner mSpinnerCourts;
    private WaitingRoomInfo mWaitingRoomInfo;
    private WaitingRoomSeats mHostSeatInfo;
    private String mRoomDocId;
    private static final String TAG = "Want2CreateRoomFragment";

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
        mPresenter.loadHostUserData();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(Constants.TAG, " Want2CreateRoom onResume: ");
        mPresenter.setActivityBackgroundPortrait();
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
        mRadioBtnNo = root.findViewById(R.id.radios_referee_no);
        mRadioBtnYes = root.findViewById(R.id.radios_referee_yes);

        mBtnCreateConfirm = root.findViewById(R.id.btn_want2create_build_confirm);
        mBtnCreateConfirm.setOnClickListener(this);
        mBtnCreateConfirm.setClickable(false);
        mBtnCreateConfirm.setTextColor(GoGoBasketball.getAppContext().getColor(R.color.gray_cccccc));

        mBtnCreateCancel = root.findViewById(R.id.btn_want2create_build_cancel);
        mBtnCreateCancel.setOnClickListener(this);

        mBtnBackStack = root.findViewById(R.id.btn_want2create_backstack);
        mBtnBackStack.setOnClickListener(this);

        mEditorRoomName = root.findViewById(R.id.edit_want2create_room_name_content);

        mSpinnerCourts = root.findViewById(R.id.spinner_courts_selector);
        mPresenter.getCourtsListFromCloud();

        return root;
    }


    public void setSpinnerCourts(ArrayList<String> courtsList) {

        String[] courtsArray = new String[courtsList.size()];
        courtsArray = courtsList.toArray(courtsArray);

        ArrayAdapter<String> courtsAdapter = new ArrayAdapter<>(GoGoBasketball.getAppContext(),
                android.R.layout.simple_spinner_dropdown_item, courtsArray);
        mSpinnerCourts.setAdapter(courtsAdapter);
        mSpinnerCourts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.getSpinnerCourtLocation(parent.getSelectedItem().toString());
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
                mBtnCreateConfirm.setClickable(false);
                if (mRadioBtnNo.isChecked()) {
                    mPresenter.showErrorToast("只支援裁判模式！", true);
                } else {
                    mPresenter.updateRoomInfo2Cloud();
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mEditorRoomName.getWindowToken(), 0);
                }
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
    public void getRoomInfoFromPresenter4NextFragment(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats waitingRoomSeats, String roomDocId) {
        mWaitingRoomInfo = waitingRoomInfo;
        mHostSeatInfo = waitingRoomSeats;
        mRoomDocId = roomDocId;
        mPresenter.openWaitingJoinMaster(waitingRoomInfo, waitingRoomSeats, roomDocId);
        mPresenter.updateHostSeat2Cloud(waitingRoomSeats, roomDocId);
    }

    @Override
    public void setCreateRoomBtnClickable() {
        mBtnCreateConfirm.setClickable(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radios_referee_yes:
                mTextRefereeWarning.setText("裁判模式結果將列入天梯排名");
                mRadioBtnYes.setTextColor(GoGoBasketball.getAppContext().getColor(R.color.white));
                mRadioBtnNo.setTextColor(GoGoBasketball.getAppContext().getColor(R.color.black_3f3a3a));
                mTextRefereeWarning.setTextColor(GoGoBasketball.getAppContext().getColor(R.color.blue_facebook));
                mPresenter.getRadioRefereeMode(true);
                if (mEditorRoomName.getText().length() != 0) {
                    setBtnCreateConfirmClickable(true);
                }
                break;
            case R.id.radios_referee_no:
                mTextRefereeWarning.setText("目前只支援裁判模式～");
                mRadioBtnYes.setTextColor(GoGoBasketball.getAppContext().getColor(R.color.black_3f3a3a));
                mRadioBtnNo.setTextColor(GoGoBasketball.getAppContext().getColor(R.color.white));
                mTextRefereeWarning.setTextColor(GoGoBasketball.getAppContext().getColor(R.color.red_FF001F));
                mPresenter.getRadioRefereeMode(false);
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
        mPresenter.setWant2CreateNow(true);
        setEditTextInputSpecialChar(mEditorRoomName);
        mEditorRoomName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!"".equals(s.toString()) && s.length() < 16) {
                    mPresenter.onRoomNameEditTextChange(s);
                    if (mRadioBtnNo.isChecked()) {
                        // do nothing
                    } else {
                        setBtnCreateConfirmClickable(true);
                        if (s.length() == 15) {
                            mPresenter.showErrorToast(GoGoBasketball.getAppContext().getString(R.string.at_most_15_word), true);
                        }
                    }
                } else if (s.length() == 0) {
                    setBtnCreateConfirmClickable(false);
                } else {
                    Log.d(Constants.TAG, "no this kind of situation!");
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
        Log.d(Constants.TAG, "Want2CreateRoom fragment onDestroy !!");
        mPresenter.showToolbarAndBottomNavigation();
        mPresenter.setWant2CreateNow(false);
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
            mBtnCreateConfirm.setTextColor(GoGoBasketball.getAppContext().getColor(R.color.black_3f3a3a));
        } else {
            mBtnCreateConfirm.setClickable(false);
            mBtnCreateConfirm.setTextColor(GoGoBasketball.getAppContext().getColor(R.color.gray_cccccc));
        }
    }

    public static void setEditTextInputSpecialChar(EditText editText) {
        InputFilter filter = new InputFilter() {

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                String speChat = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if (matcher.find()) {
                    return "";
                } else if (source.toString().contentEquals("\n")) {
                    return "";
                } else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(15)});
    }

}
