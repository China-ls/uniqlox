package com.ls.uniqlox.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by hx on 2016/3/21.
 */
public abstract class FrameUtil {

    public static void startActivity(Context context, Class<? extends Activity> target) {
        startActivity(context, target, null, -1, null);
    }

    public static void startActivity(Context context, Class<? extends Activity> target, Bundle data) {
        startActivity(context, target, data, -1, null);
    }

    public static void startActivity(Context context, Class<? extends Activity> target, Bundle data, int flags) {
        startActivity(context, target, data, flags, null);
    }

    public static void startActivity(Context context, Class<? extends Activity> target, Bundle data, int flags, String action) {
        Intent intent = prepareIntent(context, target, data, flags, action);
        startActivity(context, intent);
    }

    public static Intent prepareIntent(Context context, Class<? extends Activity> target, Bundle data, int flags, String action) {
        Intent intent = new Intent();
        intent.setClass(context, target);
        if (flags > 0) {
            intent.addFlags(flags);
        }
        intent.setAction(action);
        if( null != data ) {
            intent.putExtras(data);
        }
        return intent;
    }

    public static void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }


    public static void startActivityForResult(Activity context, int requestCode, Class<? extends Activity> target) {
        startActivityForResult(context, requestCode, target, null, -1, null);
    }

    public static void startActivityForResult(Activity context, int requestCode, Class<? extends Activity> target, Bundle data) {
        startActivityForResult(context, requestCode, target, data, -1, null);
    }

    public static void startActivityForResult(Activity context, int requestCode, Class<? extends Activity> target, Bundle data, int flags) {
        startActivityForResult(context, requestCode, target, data, flags, null);
    }

    public static void startActivityForResult(Activity context, int requestCode, Class<? extends Activity> target, Bundle data, int flags, String action) {
        Intent intent = prepareIntent(context, target, data, flags, action);
        startActivityForResult(context, intent, requestCode);
    }

    public static void startActivityForResult(Activity context, Intent intent, int requestCode) {
        context.startActivityForResult(intent, requestCode);
    }

    public static void goAndroidHome(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        startActivity(context, intent);
    }

    public static void selectPicture(Activity activity, int requestCode) {
        Intent intent = new Intent();/* 开启Pictures画面Type设定为image */
        intent.setType("image/*");/* 使用Intent.ACTION_GET_CONTENT这个Action */
        intent.setAction(Intent.ACTION_GET_CONTENT);/* 取得相片后返回本画面 */
        activity.startActivityForResult(intent, requestCode);
    }

    public static void takePicture(Activity activity, int requestCode) {
        Intent intent = new Intent();/* 开启Pictures画面Type设定为image */
        intent.setType("image/*");/* 使用Intent.ACTION_GET_CONTENT这个Action */
        intent.setAction(Intent.ACTION_GET_CONTENT);/* 取得相片后返回本画面 */
        activity.startActivityForResult(intent, requestCode);
    }

}
