package com.ls.uniqlox.util;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

/**
 * Created by hx on 2016/4/19.
 */
public abstract class ViewUtil {

    public static void refreshSwipeRefreshLayout(SwipeRefreshLayout target, boolean notify) {
        Class type = SwipeRefreshLayout.class;
        View progressView = (View) com.ls.uniqlox.util.ReflectUtil.getDeclaredField(type, target, "mCircleView");
        if (null != progressView) {
            progressView.setVisibility(View.VISIBLE);
        }
        com.ls.uniqlox.util.ReflectUtil.invokeDeclaredMethod(type, target, "setRefreshing", new Class[] {boolean.class, boolean.class}, true, notify);
    }
}
