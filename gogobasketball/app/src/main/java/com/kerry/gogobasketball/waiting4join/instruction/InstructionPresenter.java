package com.kerry.gogobasketball.waiting4join.instruction;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class InstructionPresenter implements InstructionContract.Presenter {

    private final InstructionContract.View mInstructionView;

    public InstructionPresenter(@NonNull InstructionContract.View changeGenderView) {
        mInstructionView = checkNotNull(changeGenderView, "InstructionView cannot be null!");
        mInstructionView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void showErrorToast(String message, boolean isShort) {

    }

    @Override
    public void start() {

    }
}
