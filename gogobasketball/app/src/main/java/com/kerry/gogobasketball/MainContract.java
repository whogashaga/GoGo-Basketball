package com.kerry.gogobasketball;

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

        void openHomeUi();

        void openProfileUi();

        void openFriendsUi();

        void openRankUi();

        void openWant2CreateRoomUi();

        void openWaiting4JoinSlaveUi(WaitingRoomInfo waitingRoomInfo);

        void openLoginUi();

        void openLogOutUi();

        void openCheckOutSuccessUi();

        void openWait4JoinUi(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatInfo, String roomDocId);

        void openGamePlayingOfRefereeUi(String hostName);

        void openGamePlayingOfPlayerUi(String hostName, int nowSort);

        void openRefereeResultUi(String hostName);

        void openPlayerResultUi(String hostName, int nowSort);

        void openCommentRefereeUi(String refereeName);

        void openCreateUserUi(String userDocId);

        void popBackStackUi();

        Looking4RoomFragment findHomeView();

        CourtsMapFragment findMapView();

        RankPlayerFragment findRankPlayerView();

        RankRefereeFragment findRankRefereeView();

        void showMessageDialogUi(@MessageDialog.MessageType int type);

        void showToastUi(String message);

        void hideToolbarUi();

        void showToolbarUi();

        void hideBottomNavigationUi();

        void showBottomNavigationUi();

//        void updateFriendBadgeUi(int amount);

        void setToolbarTitleUi(String title);

        void closeDrawerUi();

        void showDrawerUserUi();

        void showActivityBackgroundWhenLandScape();

        void showActivityBackgroundWhenPortrait();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode, Intent data);

        void openHome();

        void openFriend();

        void openRank();

        void openProfile();

//        void openPayment();

        void switchToProfileByBottomNavigation();

//        CatalogItemFragment findWomen();

//        CatalogItemFragment findMen();

//        CatalogItemFragment findAccessories();

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void hideBottomNavigation();

        void showBottomNavigation();

        void updateFriendBadge();

        void updateToolbar(String title);

        void onLoginSuccess();

        void showLoginFragment();

        void showRatingRefereeSuccessDialog();

        void showLoginSuccessDialog();

        void showToast(String message);

        void onDrawerOpened();

        void onClickDrawerAvatar();

        boolean disableBackKey();

        boolean isGamingNow();

        boolean have2Comment();

        void showErrorToast(String message, boolean isShort);

        void onLoginSuccessBeforeOpenApp(String userDocId);
    }
}
