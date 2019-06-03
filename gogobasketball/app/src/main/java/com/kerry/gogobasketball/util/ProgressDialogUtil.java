package com.kerry.gogobasketball.util;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kerry.gogobasketball.R;

public class ProgressDialogUtil {

    private static AlertDialog mAlertDialog;

    /**
     * 彈出耗時對話方塊
     * @param context
     */
    public static void showProgressDialog(Context context) {
        if (mAlertDialog == null) {
            mAlertDialog = new AlertDialog.Builder(context, R.style.CustomProgressDialog).create();
        }

        View loadView = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        mAlertDialog.setView(loadView, 0, 0, 0, 0);
        mAlertDialog.setCanceledOnTouchOutside(false);

        ProgressBar progressBar = loadView.findViewById(R.id.progressBar_loading);

        TextView tvTip = loadView.findViewById(R.id.text_message_loading);
        tvTip.setText("載入中...");

        mAlertDialog.show();
    }

    /**
     * 隱藏耗時對話方塊
     */
    public static void dismiss() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }

}
