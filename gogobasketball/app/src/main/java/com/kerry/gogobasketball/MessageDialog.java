package com.kerry.gogobasketball;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MessageDialog extends AppCompatDialogFragment {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            LOGIN_SUCCESS, LOGIN_FAIL, CREATE_USER_SUCCESS
    })
    public @interface MessageType {}
    public static final int LOGIN_SUCCESS   = 0x11;
    public static final int LOGIN_FAIL      = 0x12;
    public static final int CREATE_USER_SUCCESS      = 0x13;

    private int mIconRes;
    private String mMessage;

    public MessageDialog() {}

    /**
     *
     * @param iconRes: Icon Resourse Id
     * @param message: Message Content
     */
    public void setMessage(int iconRes, String message) {
        mIconRes = iconRes;
        mMessage = message;
    }

    public void setMessage(@MessageType int messageType) {

        switch (messageType) {
            case LOGIN_SUCCESS:
                mIconRes = R.drawable.ic_success;
                mMessage = GoGoBasketball.getAppContext().getString(R.string.login_success);
                break;
            case CREATE_USER_SUCCESS:
                mIconRes = R.drawable.ic_success;
                mMessage = GoGoBasketball.getAppContext().getString(R.string.create_user_success);
                break;
            default:

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.MessageDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_message, container, false);
        view.setOnClickListener(v -> dismiss());

        ((ImageView) view.findViewById(R.id.image_message_icon)).setImageResource(mIconRes);
        ((TextView) view.findViewById(R.id.text_message_content)).setText(mMessage);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Handler().postDelayed(this::dismiss, 2000);
    }
}




