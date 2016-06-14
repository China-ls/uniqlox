package com.ls.uniqlox.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

/**
 * Created by hx on 2016/3/21.
 */
public abstract class ApkUtil {

    public static PackageInfo getPackageInfo(Context context) throws PackageManager.NameNotFoundException {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        return packInfo;
    }

    public static String getPhoneModel() {
        return android.os.Build.MODEL;
    }

    public static String getPhoneVersionRelease() {
        return android.os.Build.VERSION.RELEASE;
    }

    public static String getPhoneManufacturer() {
        return android.os.Build.MANUFACTURER;
    }

    public static String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

}
