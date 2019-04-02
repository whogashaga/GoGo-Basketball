package com.kerry.gogobasketball;

import android.app.Activity;
import android.content.Intent;

import com.kerry.gogobasketball.home.item.LookingForRoomFragment;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void openHomeUi();

        void openLoginUi(int loginFrom);

//        void openAdd2CartUi(Product product);

        void openCheckOutSuccessUi();

//        void openDetailUi(Product product);

//        void finishDetailUi();

//        void finishPaymentUi();

        LookingForRoomFragment findHomeView();

        Activity findMapView();

//        CatalogItemFragment findAccessoriesView();

//        void switchProfileUiInitiative();

//        void showMessageDialogUi(@MessageDialog.MessageType int type);

        void showToastUi(String message);

        void hideToolbarUi();

        void showToolbarUi();

        void hideBottomNavigationUi();

        void showBottomNavigationUi();

//        void updateFriendBadgeUi(int amount);

        void setToolbarTitleUi(String title);

        void closeDrawerUi();

        void showDrawerUserUi();

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

        void onLoginSuccess(int loginFrom);

        void showLoginDialog(int loginFrom);

        void showCheckOutSuccessDialog();

        void showRatingRefereeSuccessDialog();

        void showLoginSuccessDialog();

        void showToast(String message);

        void onDrawerOpened();

        void onClickDrawerAvatar();

    }
}
