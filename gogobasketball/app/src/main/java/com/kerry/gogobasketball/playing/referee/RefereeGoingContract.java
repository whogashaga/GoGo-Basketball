package com.kerry.gogobasketball.playing.referee;

import android.app.Activity;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.GamingRoomInfo;

public interface RefereeGoingContract {

    interface View extends BaseView<Presenter> {

        void setBtnGameOverClickable(boolean clickable);

        void showPlayingGameUi(GamingRoomInfo gamingRoomInfo);

        void getHostNameFromPresenter(String hostName);

        void openGameResultRefereeUi(String hostName);

        void setGamingNow(boolean isGamingNow);

        void setScorePlusClickableTeamA(boolean clickable);

        void setScorePlusClickableTeamB(boolean clickable);

        boolean isActive();

        void showErrorToast(String message, boolean isShort);


        void setTextScoreTeamA(String textScoreTeamA);

        void setTextScoreTeamB(String textScoreTeamB);

        void setTextReboundTeamA(String textReboundTeamA);

        void setTextReboundTeamB(String textReboundTeamB);

        void setTextFoulTeamA(String textFoulTeamA);

        void setTextFoulTeamB(String textFoulTeamB);


        void setTextScoreP1(String textScoreViewP1);

        void setBtnClickableScoreMinusP1(boolean clickable);

        void setTextReboundP1(String textReboundP1);

        void setBtnClickableReboundPlusP1(boolean clickable);

        void setBtnClickableReboundMinusP1(boolean clickable);

        void setTextFoulP1(String textFoulP1);

        void setBtnClickableFoulPlusP1(boolean clickable);

        void setBtnClickableFoulMinusP1(boolean clickable);


        void setTextScoreP2(String textScoreViewP2);

        void setBtnClickableScoreMinusP2(boolean clickable);

        void setTextReboundP2(String textReboundP2);

        void setBtnClickableReboundPlusP2(boolean clickable);

        void setBtnClickableReboundMinusP2(boolean clickable);

        void setTextFoulP2(String textFoulP2);

        void setBtnClickableFoulPlusP2(boolean clickable);

        void setBtnClickableFoulMinusP2(boolean clickable);


        void setTextScoreP3(String textScoreViewP3);

        void setBtnClickableScoreMinusP3(boolean clickable);

        void setTextReboundP3(String textReboundP3);

        void setBtnClickableReboundPlusP3(boolean clickable);

        void setBtnClickableReboundMinusP3(boolean clickable);

        void setTextFoulP3(String textFoulP3);

        void setBtnClickableFoulPlusP3(boolean clickable);

        void setBtnClickableFoulMinusP3(boolean clickable);


        void setTextScoreP4(String textScoreViewP4);

        void setBtnClickableScoreMinusP4(boolean clickable);

        void setTextReboundP4(String textReboundP4);

        void setBtnClickableReboundPlusP4(boolean clickable);

        void setBtnClickableReboundMinusP4(boolean clickable);

        void setTextFoulP4(String textFoulP4);

        void setBtnClickableFoulPlusP4(boolean clickable);

        void setBtnClickableFoulMinusP4(boolean clickable);


        void setTextScoreP5(String textScoreViewP5);

        void setBtnClickableScoreMinusP5(boolean clickable);

        void setTextReboundP5(String textReboundP5);

        void setBtnClickableReboundPlusP5(boolean clickable);

        void setBtnClickableReboundMinusP5(boolean clickable);

        void setTextFoulP5(String textFoulP5);

        void setBtnClickableFoulPlusP5(boolean clickable);

        void setBtnClickableFoulMinusP5(boolean clickable);


        void setTextScoreP6(String textScoreViewP6);

        void setBtnClickableScoreMinusP6(boolean clickable);

        void setTextReboundP6(String textReboundP6);

        void setBtnClickableReboundPlusP6(boolean clickable);

        void setBtnClickableReboundMinusP6(boolean clickable);

        void setTextFoulP6(String textFoulP6);

        void setBtnClickableFoulPlusP6(boolean clickable);

        void setBtnClickableFoulMinusP6(boolean clickable);
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void setBackKeyDisable(boolean isBackKeyDisable);

        void setGamingNowMessage(boolean isGamingNow);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void showErrorToast(String message, boolean isShort);

        void getHostNameFromWaitingJoin(String hostName);

        void getGamingRoomFromFireStore(String hostName);

        void loadRefereeInfoFromFirebase();

        void forced2FinishPlayingUi();

        void checkWhichTeamWon();

        void increaseScoreP1();

        void decreaseScoreP1();

        void increaseReboundP1();

        void decreaseReboundP1();

        void increaseFoulP1();

        void decreaseFoulP1();

        void increaseScoreP2();

        void decreaseScoreP2();

        void increaseReboundP2();

        void decreaseReboundP2();

        void increaseFoulP2();

        void decreaseFoulP2();


        void increaseScoreP3();

        void decreaseScoreP3();

        void increaseReboundP3();

        void decreaseReboundP3();

        void increaseFoulP3();

        void decreaseFoulP3();


        void increaseScoreP4();

        void decreaseScoreP4();

        void increaseReboundP4();

        void decreaseReboundP4();

        void increaseFoulP4();

        void decreaseFoulP4();


        void increaseScoreP5();

        void decreaseScoreP5();

        void increaseReboundP5();

        void decreaseReboundP5();

        void increaseFoulP5();

        void decreaseFoulP5();


        void increaseScoreP6();

        void decreaseScoreP6();

        void increaseReboundP6();

        void decreaseReboundP6();

        void increaseFoulP6();

        void decreaseFoulP6();


        void updateGameResultOfPlayer(GamingRoomInfo gamingRoomInfo);

        void openGameResultReferee(String hostName);

        void getRefereeUserData(Activity activity);

        void forced2FinishGaming();

    }
}
