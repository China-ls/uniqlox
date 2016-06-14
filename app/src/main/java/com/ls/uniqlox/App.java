package com.ls.uniqlox;

import android.app.Application;

import com.ls.uniqlox.domain.VideoDto;

import org.xutils.cache.LruCache;
import org.xutils.x;

import java.util.List;

/**
 * Created by hx on 16-5-23.
 */
public class App extends Application {

//    private OkHttpClient httpClient;

    private LruCache<String, List<VideoDto>> videoCache = new LruCache<String, List<VideoDto>>(50);
    private LruCache<String, String> listIndexCache = new LruCache<String, String>(50);

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }

//    public synchronized OkHttpClient getHttpClient() {
//        if (null == httpClient) {
//            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//            httpClient = new OkHttpClient.Builder()
//                    .addInterceptor(httpLoggingInterceptor)
//                    .build();
//        }
//        return httpClient;
//    }

    public void putCacheVideo(String tag, List<VideoDto> dtos) {
        videoCache.put(tag, dtos);
    }

    public List<VideoDto> getCacheVideo(String tag) {
        return videoCache.get(tag);
    }

    public void putCacheIndex(String tag, int index) {
        listIndexCache.put(tag, index + "");
    }

    public int getCacheIndex(String tag) {
        return Integer.parseInt(listIndexCache.get(tag));
    }

}
