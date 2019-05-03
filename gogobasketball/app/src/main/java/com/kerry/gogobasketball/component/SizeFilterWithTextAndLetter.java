package com.kerry.gogobasketball.component;

import android.text.InputFilter;
import android.text.Spanned;
import android.view.Gravity;
import android.widget.Toast;

import com.kerry.gogobasketball.GoGoBasketball;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SizeFilterWithTextAndLetter implements InputFilter {
    private int mMaxLength;
    private int englishLength;
    private int chineseLength;

    private boolean hasChinese;

    Pattern p = Pattern.compile("[\u4e00-\u9fa5]+");
    Matcher m;
    private Toast toast;

    public SizeFilterWithTextAndLetter(int onlyLetterLength, int normalLength) {
        toast = Toast.makeText(GoGoBasketball.getAppContext(), "限" + normalLength + "中文或" + onlyLetterLength + "個英文", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        this.englishLength = onlyLetterLength;
        this.chineseLength = normalLength;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {


        String speChat = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern pattern = Pattern.compile(speChat);
        Matcher matcher = pattern.matcher(source.toString());

        if (matcher.find()) {
            return "";
        } else if (source.equals(" ") || source.toString().contentEquals("\n")) {
            return "";
        } else {

            if (!hasChinese && dest.length() <= chineseLength) {
                if (dest.length() >= chineseLength) {
                    m = p.matcher(dest.toString());

                } else {
                    String tmp = source.toString() + dest.toString();

                    if (tmp.length() >= chineseLength) {
                        tmp = tmp.substring(0, chineseLength);
                        toast.show();
                    }
                    m = p.matcher(tmp);
                }
                hasChinese = m.find();
                mMaxLength = hasChinese ? chineseLength : englishLength;
            }
            if (mMaxLength == englishLength) {
                m = p.matcher(source);
                if (m.find()) return "";
            }

            int keep = mMaxLength - (dest.length() - (dend - dstart));
            if (keep <= 0) {
                return "";
            } else if (keep >= end - start) {
                return null;
            } else {
                keep += start;
                if (Character.isHighSurrogate(source.charAt(keep - 1))) {
                    --keep;
                    if (keep == start) {
                        return "";
                    }
                }
                return source.subSequence(start, keep);
            }
        }
    }
}
