package com.kerry.gogobasketball.home.item.find_host;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.component.NameInputFilter;
import com.kerry.gogobasketball.component.NameLengthFilter;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.util.Constants;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkNotNull;

public class FindHostDialog extends DialogFragment implements FindHostContract.View, View.OnClickListener {

    private FindHostContract.Presenter mPresenter;
    private View mLayoutDialog;
    private View mLayout;
    private Button mBtnCancel;
    private Button mBtnConfirm;
    private TextView mTextFindHostTitle;
    private EditText mEditHostId;
    private CharSequence mChar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.CommentRefereeDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dialog_change_id, container, false);


        mLayoutDialog = root.findViewById(R.id.dialog_change_id);
        mLayoutDialog.setOnClickListener(this);
        mLayout = root.findViewById(R.id.layout_change_id);
        mLayout.setOnClickListener(this);
        mBtnConfirm = root.findViewById(R.id.btn_change_id_yes);
        mBtnConfirm.setOnClickListener(this);
        mBtnCancel = root.findViewById(R.id.btn_change_id_no);
        mBtnCancel.setOnClickListener(this);

        mTextFindHostTitle = root.findViewById(R.id.text_change_id_title);
        mTextFindHostTitle.setText("請輸入房主名稱 :");

        mEditHostId = root.findViewById(R.id.edit_change_id);
        mEditHostId.setHint("在此輸入");

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_change_id:
                // do nothing
                break;
            case R.id.btn_change_id_yes:
                if(mChar.length() == 0){
                    mPresenter.showErrorToast("名稱不留白 !", true);
                } else {
                    mPresenter.checkIfRoomExists(getActivity());
                    mBtnConfirm.setClickable(false);
                }
                break;
            default:
                dismiss();
                break;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEditHostId.setFilters(new InputFilter[]{new NameInputFilter(getContext(), 12)});
        mEditHostId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mChar = s;
                if (!"".equals(s.toString()) && s.length() < 12) {
                    mPresenter.onHostIdEditTextChange(s);
                    Log.e(Constants.TAG, "Find Host onTextChanged : " + s.toString());
                } else if (s.length() == 0) {
                    mPresenter.showErrorToast("", true);
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
    public void showFindNoHost() {
        mPresenter.showErrorToast("查無此人或房間", true);
        mBtnConfirm.setClickable(true);
    }

    @Override
    public void showFindHostSuccessUi(ArrayList<WaitingRoomInfo> list) {
        mPresenter.getWaitingRoomFromFindHost(list);
        dismiss();
    }

    @Override
    public void finishFindHostUi() {

    }

    @Override
    public void setPresenter(FindHostContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

}
