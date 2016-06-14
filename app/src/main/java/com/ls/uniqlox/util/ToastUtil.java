package com.ls.uniqlox.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hx on 2016/3/21.
 */
public abstract class ToastUtil {

    public static Toast Toast(Context context, String text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
        return toast;
    }

    public static Toast Toast(Context context, int text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
        return toast;
    }

    public static Toast ToastLong(Context context, String text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.show();
        return toast;
    }

    public static Toast ToastLong(Context context, int text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.show();
        return toast;
    }

}
