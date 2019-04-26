package com.kerry.gogobasketball.profile.change_id;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.component.NameLengthFilter;
import com.kerry.gogobasketball.util.Constants;

import static com.google.common.base.Preconditions.checkNotNull;

public class ChangeIdDialog extends DialogFragment implements ChangeIdContract.View, View.OnClickListener {

    private ChangeIdContract.Presenter mPresenter;
    private View mLayoutDialog;
    private View mLayout;
    private Button mBtnCancel;
    private Button mBtnConfirm;
    private EditText mEditNewId;
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

        mEditNewId = root.findViewById(R.id.edit_change_id);

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
                    mPresenter.checkIfUserNewIdExists(getActivity());
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
        mEditNewId.setFilters(new NameLengthFilter[]{new NameLengthFilter(12)});
        mEditNewId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mChar = s;
                if (!"".equals(s.toString()) && s.length() < 12) {
                    mPresenter.onUserNewIdEditTextChange(s);
                    Log.e(Constants.TAG, "Change ID onTextChanged : " + s.toString());
                } else if (s.length() == 0) {
                    mPresenter.showErrorToast(GoGoBasketball.getAppContext().getString(R.string.enter_new_user_id), true);
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
    public void setPresenter(ChangeIdContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showIdAlreadyExist() {
        mPresenter.showErrorToast("此名稱已有人使用", true);
        mBtnConfirm.setClickable(true);
    }

    @Override
    public void showChangeIdSuccessUi() {
        mPresenter.showDataChangeSuccessDialog();
    }

    @Override
    public void showNewProfileUi() {
        mPresenter.loadProfileUserData(getActivity());
    }

    @Override
    public void finishChangeIdUi() {
        dismiss();
    }
}
