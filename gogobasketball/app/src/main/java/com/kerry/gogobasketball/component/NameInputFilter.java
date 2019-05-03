package com.kerry.gogobasketball.component;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameInputFilter implements InputFilter {

    private int mMaxLength;
    private Toast toast;

    public NameInputFilter(Context context, int maxLen) {
        mMaxLength = maxLen;
        toast = Toast.makeText(context, "限" + maxLen / 2 + "中文或" + maxLen + "個英文)", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {

        String speChat = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern pattern = Pattern.compile(speChat);
        Matcher matcher = pattern.matcher(source.toString());

        if (matcher.find()) {
            return "";
        } else if (source.equals(" ") || source.toString().contentEquals("\n")) {
            return "";
        } else {


            int count = 0;    // 判断是否到达最大长度
            int dindex = 0;   // 之前就存在的内容

            while (count <= mMaxLength && dindex < dest.length()) {
                char c = dest.charAt(dindex++);
                if (c < 128) {
                    count = count + 1;
                } else {
                    count = count + 2;
                }
            }
            if (count > mMaxLength) {
                toast.show();
                return dest.subSequence(0, dindex - 1);
            }
            // 从编辑框刚刚输入进去的内容
            int sindex = 0;
            while (count <= mMaxLength && sindex < source.length()) {
                char c = source.charAt(sindex++);
                if (c < 128) {
                    count = count + 1;
                } else {
                    count = count + 2;
                }
            }
            if (count > mMaxLength) {
                toast.show();
                sindex--;
            }
            return source.subSequence(0, sindex);
        }
    }
}
