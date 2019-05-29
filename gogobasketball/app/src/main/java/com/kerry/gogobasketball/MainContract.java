package com.kerry.gogobasketball;

import android.app.Activity;
import android.content.Intent;

import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;
import com.kerry.gogobasketball.home.item.Looking4RoomFragment;
import com.kerry.gogobasketball.home.map.CourtsMapFragment;
import com.kerry.gogobasketball.rank.player.RankPlayerFragment;
import com.kerry.gogobasketball.rank.referee.RankRefereeFragment;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void openHomeByBackStack();

        void openHomeUi();

        void openProfileUi();

        void openFriendsUi();

        void openRankUi();

        void openWant2CreateRoomUi();

        void openWaiting4JoinSlaveUi(WaitingRoomInfo waitingRoomInfo);

        void openLoginUi();

        void openLogOutUi();

        void openChangeIdUi();

        void openChangeGenderUi(String currentGender);

        void openChangePositionUi(String currentPosition);

        void openFindHostUi();

        void openInstructionUi();

        void openWait4JoinUi(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatInfo, String roomDocId);

        void openGamePlayingOfRefereeUi(String hostName);

        void openGamePlayingOfPlayerUi(String hostName, int nowSort);

        void openRefereeResultUi(String hostName);

        void openPlayerResultUi(String hostName, int nowSort);

        void openCommentRefereeUi(String refereeName);

        void openUserDetailUi(String userId);

        void openCreateUserUi(String userDocId);

        void popBackStackUi();

        Looking4RoomFragment findHomeView();

        CourtsMapFragment findMapView();

        RankPlayerFragment findRankPlayerView();

        RankRefereeFragment findRankRefereeView();

        void showMessageDialogUi(@MessageDialog.MessageType int type);

        void hideToolbarUi();

        void showToolbarUi();

        void hideBottomNavigationUi();

        void showBottomNavigationUi();

        void setToolbarTitleUi(String title);

        void showActivityBackgroundWhenLandScape();

        void showActivityBackgroundWhenPortrait();

        void switchProfileUiInitiative();

        void switchHotsUiInitiative();

        void stopGettingLocationService();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode, Intent data);

        void openHome();

        void openFriend();

        void openRank();

        void openProfile();

        void switchToProfileByBottomNavigation();

        void switchToHotsByBottomNavigation();

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void updateToolbar(String title);

        void showLoginFragment();

        boolean disableBackKey();

        boolean isGamingNow();

        boolean have2Comment();

        boolean openingWant2CreateRoom();

        void showErrorToast(String message, boolean isShort);

        void onLoginSuccessBeforeOpenApp(String userDocId);

        void getDeviceCurrentLocation();

        void getUserInfoWhenGetOutOfApp();

        void setLocationHandler();

        void removeHandler();
    }
}
