package com.kerry.gogobasketball.home.item.find_host;

import android.app.Activity;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.WaitingRoomInfo;

import java.util.ArrayList;

public interface FindHostContract {

    interface View extends BaseView<Presenter> {

        void showFindNoHost();

        void showFindHostSuccessUi(ArrayList<WaitingRoomInfo> list);

        void finishFindHostUi();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void showErrorToast(String message, boolean isShort);

        void onHostIdEditTextChange(CharSequence charSequence);

        void checkIfRoomExists(Activity activity);

        void updateRecyclerView(Activity activity);

        void getWaitingRoomFromFindHost(ArrayList<WaitingRoomInfo> list);
    }
}
