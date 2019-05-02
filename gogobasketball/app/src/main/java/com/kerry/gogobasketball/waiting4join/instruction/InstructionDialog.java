package com.kerry.gogobasketball.waiting4join.instruction;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kerry.gogobasketball.R;

import static com.google.common.base.Preconditions.checkNotNull;

public class InstructionDialog extends DialogFragment implements InstructionContract.View, View.OnClickListener {

    private InstructionContract.Presenter mPresenter;
    private View mLayoutDialog;
    private View mLayout;
    private ImageView mBtnClose;
    private View mRoot;

    @Override
    public void setPresenter(InstructionContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.CommentRefereeDialog);
    }

    @Override
    public void onResume() {
        super.onResume();
        mRoot.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.dialog_instruction, container, false);

        mLayoutDialog = mRoot.findViewById(R.id.dialog_instruction);
        mLayoutDialog.setOnClickListener(this);
        mLayout = mRoot.findViewById(R.id.layout_instruction);
        mLayout.setOnClickListener(this);
        mBtnClose = mRoot.findViewById(R.id.btn_instruction_close);
        mBtnClose.setOnClickListener(this);

        return mRoot;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_instruction:
                // do nothing, avoiding dismiss
                break;
            default:
                dismiss();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRoot.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    public void finishInstructionUi() {
        dismiss();
    }
}
