package com.kerry.gogobasketball.create_user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkNotNull;

public class CreateUserFragment extends Fragment implements CreateUserContract.View, RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private final String MALE = "male";
    private final String FEMALE = "female";

    private CreateUserContract.Presenter mPresenter;
    private ArrayList<String> mPositionList;
    private EditText mEditUserId;
    private Spinner mSpinnerPosition;
    private RadioGroup mRadioGenderSelector;
    private Button mBtnCreateUserConfirm;

    public CreateUserFragment() {
        // Requires empty public constructor
    }

    public static CreateUserFragment newInstance() {
        return new CreateUserFragment();
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
    public void setPresenter(CreateUserContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create_user_info, container, false);

        mEditUserId = root.findViewById(R.id.edit_create_user_id);
        mEditUserId.getBackground().setAlpha(100);

        mRadioGenderSelector = root.findViewById(R.id.radios_create_user_gender_selector);
        mRadioGenderSelector.setOnCheckedChangeListener(this);

        mSpinnerPosition = root.findViewById(R.id.spinner_create_user_position_selector);
        mSpinnerPosition.getBackground().setAlpha(100);
        setSpinnerPosition();

        mBtnCreateUserConfirm = root.findViewById(R.id.btn_create_user_confirm);
        mBtnCreateUserConfirm.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create_user_confirm:
                Log.e("Kerry", "btn_create_user_confirm onClick ! ");
                mPresenter.createUserClickConfirm();
                break;
            default:
        }
    }

    public void setSpinnerPosition() {
        mPositionList = new ArrayList<>();
        mPositionList.add(getString(R.string.position_center));
        mPositionList.add(getString(R.string.position_pf));
        mPositionList.add(getString(R.string.position_sf));
        mPositionList.add(getString(R.string.position_sg));
        mPositionList.add(getString(R.string.position_pg));

        String[] courtsArray = new String[mPositionList.size()];
        courtsArray = mPositionList.toArray(courtsArray);

        ArrayAdapter<String> courtsAdapter = new ArrayAdapter<>(GoGoBasketball.getAppContext(),
                android.R.layout.simple_spinner_dropdown_item, courtsArray);
        mSpinnerPosition.setAdapter(courtsAdapter);
        mSpinnerPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("Kerry", "now selected = " + parent.getSelectedItem().toString());
                mPresenter.getPositionFromSpinner(parent.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.hideToolbarAndBottomNavigation();
        setBtnCreateConfirmClickable(true);
        setEditTextInputSpace(mEditUserId);
        setEditTextInputSpeChat(mEditUserId);
        mEditUserId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!"".equals(s.toString()) && s.length() < 7) {
                    mPresenter.onUserIdEditTextChange(s);
                    setBtnCreateConfirmClickable(true);
                    Log.e("Kerry", "fragment onTextChanged : " + s.toString());
                    mEditUserId.setFocusable(true);
                    if (s.length() == 8) {
                        mPresenter.showErrorToast(GoGoBasketball.getAppContext().getString(R.string.at_most_8_word), true);
                    }
                } else if (s.length() == 0) {
                    mPresenter.showErrorToast(GoGoBasketball.getAppContext().getString(R.string.edit_user_id), true);
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

    private void setBtnCreateConfirmClickable(boolean clickable) {

        if (clickable) {
            mBtnCreateUserConfirm.setClickable(true);
            mBtnCreateUserConfirm.setBackgroundResource(R.drawable.btn_black_ripple);
        } else {
            mBtnCreateUserConfirm.setClickable(false);
            mBtnCreateUserConfirm.setBackgroundResource(R.drawable.btn_gray_ripple);
        }
    }

    @Override
    public void showCreateUserSuccessUi() {
        if (mPresenter != null) {
            mPresenter.showCreateUserSuccessDialog();
            mPresenter.onCreateUserSuccess();
        }
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radios_create_male:
                mPresenter.getGenderFromRadioGroup(MALE);
                Log.d("Kerry", "radio male = " + MALE);
                break;
            case R.id.radios_create_female:
                mPresenter.getGenderFromRadioGroup(FEMALE);
                Log.d("Kerry", "radio male = " + FEMALE);
                break;
            default:
                break;
        }
    }

    public static void setEditTextInputSpace(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.equals(" ") || source.toString().contentEquals("\n")) {
                    return "";
                } else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    public static void setEditTextInputSpeChat(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if (matcher.find()) {
                    return "";
                } else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

}
