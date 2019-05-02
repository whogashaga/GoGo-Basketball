package com.kerry.gogobasketball.waiting4join.instruction;

import android.app.Activity;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface InstructionContract {

    interface View extends BaseView<Presenter> {

        void finishInstructionUi();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void showErrorToast(String message, boolean isShort);

    }
}
