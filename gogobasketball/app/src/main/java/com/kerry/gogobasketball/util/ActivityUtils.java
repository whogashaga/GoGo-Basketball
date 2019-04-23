package com.kerry.gogobasketball.util;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.kerry.gogobasketball.MainMvpController;
import com.kerry.gogobasketball.R;

public class ActivityUtils {

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     *
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static void showOrAddFragmentByTag(@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment,
                                              @MainMvpController.FragmentType String fragmentTag) {
        checkNotNull(fragmentManager);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.setCustomAnimations(R.anim.fragment_no_change,R.anim.fragment_out_from_right_side);

        for (Fragment element : fragmentManager.getFragments()) {
            if (!element.isHidden()) {
                transaction.hide(element);
                break;
            }
        }

        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.add(R.id.layout_main_container, fragment, fragmentTag);
        }

        transaction.commit();
    }

    public static void addFragmentByTag(@NonNull FragmentManager fragmentManager,
                                        @NonNull Fragment fragment,
                                        @MainMvpController.FragmentType String fragmentTag) {
        checkNotNull(fragmentManager);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.setCustomAnimations(R.anim.fragment_no_change,R.anim.fragment_out_from_right_side);

        for (Fragment element : fragmentManager.getFragments()) {
            if (!element.isHidden()) {
                transaction.hide(element);
                transaction.addToBackStack(null);
                break;
            }
        }

        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.add(R.id.layout_main_container, fragment, fragmentTag);
        }

        transaction.commit();
    }

    public static void addFragmentByTagStateLoss(@NonNull FragmentManager fragmentManager,
                                        @NonNull Fragment fragment,
                                        @MainMvpController.FragmentType String fragmentTag) {
        checkNotNull(fragmentManager);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.setCustomAnimations(R.anim.fragment_no_change,R.anim.fragment_out_from_right_side);

        for (Fragment element : fragmentManager.getFragments()) {
            if (!element.isHidden()) {
                transaction.hide(element);
                break;
            }
        }

        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.add(R.id.layout_main_container, fragment, fragmentTag);
        }
        transaction.commitAllowingStateLoss();
    }
}
