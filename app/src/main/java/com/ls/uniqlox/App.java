package com.ls.uniqlox;

import android.app.Application;

import org.xutils.x;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by hx on 16-5-23.
 */
public class App extends Application {

    private OkHttpClient httpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }

    public synchronized OkHttpClient getHttpClient() {
        if (null == httpClient) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            httpClient = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .build();
        }
        return httpClient;
    }

}
