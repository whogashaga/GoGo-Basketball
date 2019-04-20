package com.kerry.gogobasketball;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

public class LogoActivity extends BaseActivity {

    private int mTotalDuration = 4000;
    private View mView;
    private ImageView mImageLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        mView = this.findViewById(R.id.layout_logo);
        mView.setBackgroundResource(R.drawable.anim_wheel_dunk);
        AnimationDrawable drawable = (AnimationDrawable) mView.getBackground();
        drawable.start();

        new Handler().postDelayed(() -> {
            finish();
        }, mTotalDuration);
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {
    }

}