package com.ls.uniqlox.util;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by hx on 2016/3/8.
 */
public abstract class WindowUtil {
    public static void hideWindowTitle(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public static void fullScreen(Activity activity) {
        //隐藏状态栏
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
