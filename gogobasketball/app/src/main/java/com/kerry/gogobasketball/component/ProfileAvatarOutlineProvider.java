package com.kerry.gogobasketball.component;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;

public class ProfileAvatarOutlineProvider extends ViewOutlineProvider {
    @Override
    public void getOutline(View view, Outline outline) {
        view.setClipToOutline(true);
        int radius = GoGoBasketball.getAppContext().getResources().getDimensionPixelSize(R.dimen.radius_profile_avatar);
        outline.setOval(0, 0, radius, radius);
    }
}
