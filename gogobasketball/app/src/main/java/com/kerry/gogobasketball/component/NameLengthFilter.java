package com.kerry.gogobasketball.component;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Toast;

import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameLengthFilter implements InputFilter {

    int MAX_EN; // 最大英文/数字长度 一个汉字算两个字母
    String regEx = "[\\u4e00-\\u9fa5]"; // unicode编码，判断是否为汉字

    public NameLengthFilter(int mAX_EN) {
        super();
        MAX_EN = mAX_EN;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {
        int destCount = dest.toString().length()
                + getChineseCount(dest.toString());
        int sourceCount = source.toString().length()
                + getChineseCount(source.toString());
        String speChat = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern pattern = Pattern.compile(speChat);
        Matcher matcher = pattern.matcher(source.toString());

        if (destCount + sourceCount > MAX_EN) {
            Toast.makeText(GoGoBasketball.getAppContext(),
                    GoGoBasketball.getAppContext().getString(R.string.over_limit_letter_count),
                    Toast.LENGTH_SHORT).show();
            return "";
        } else if (matcher.find()) {
            return "";
        } else if (source.equals(" ") || source.toString().contentEquals("\n")) {
            return "";
        }else {
            return source;
        }
    }

    private int getChineseCount(String str) {
        int count = 0;
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        while (m.find()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                count = count + 1;
            }
        }
        return count;
    }
}
