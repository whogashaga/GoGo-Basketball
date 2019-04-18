package com.kerry.gogobasketball.util;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;

public class Util {

    public static void setTouchDelegate(View view) {
        final View parent = (View) view.getParent();  // button: the view you want to enlarge hit area
        parent.post(() -> {
            final Rect rect = new Rect();
            view.getHitRect(rect);
            rect.top -= 100;    // increase top hit area
            rect.left -= 100;   // increase left hit area
            rect.bottom += 100; // increase bottom hit area
            rect.right += 100;  // increase right hit area
            parent.setTouchDelegate(new TouchDelegate(rect, view));
        });
    }
}
