package com.ls.uniqlox.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ls.uniqlox.R;
import com.ls.uniqlox.domain.Const;
import com.ls.uniqlox.domain.Page;
import com.ls.uniqlox.logic.UniqloxParser;
import com.ls.uniqlox.ui.adapter.VpTagAdapter;
import com.viewpagerindicator.TabPageIndicator;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;

@ContentView(R.layout.activity_app)
public class AppAct extends AppCompatActivity {
    @ViewInject(R.id.indicator)
    private TabPageIndicator tabPageIndicator;

    //适配器类
    private VpTagAdapter adapter;
    @ViewInject(R.id.viewPager)
    private ViewPager viewPager;

    private UniqloxParser parser = new UniqloxParser();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        adapter = new VpTagAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabPageIndicator.setViewPager(viewPager);

        new Thread(){
            @Override
            public void run() {
                Page page = null;
                try {
                    page = parser.parse(Const.uniqlox_urls[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(page);
                try {
                    System.out.println(parser.parseVideo(page.getVideo(0)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

//        final OkHttpClient httpClient = ((App) getApplicationContext()).getHttpClient();
//        Request.Builder requestBuilder = new Request.Builder().url(Const.uniqlox_urls[0].getUrl());
//        //可以省略，默认是GET请求
//        requestBuilder.method("GET", null);
//        Request request = requestBuilder.build();
//        Call call = httpClient.newCall(request);
//        call.enqueue(new okhttp3.Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final UniqloxParser parser = new UniqloxParser();
//                Page page = parser.parse(Const.uniqlox_urls[0], response.body().string());
//                final VideoDto dto = page.getVideo(0);
//                System.out.println( dto.getVideoParseUrl() );
//                Request.Builder dtoBuilder = new Request.Builder().url( dto.getVideoParseUrl());
//                //可以省略，默认是GET请求
//                dtoBuilder.method("GET", null);
//                Request request = dtoBuilder.build();
//                Call callDto = httpClient.newCall(request);
//                callDto.enqueue(new okhttp3.Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        VideoDto videoDto = parser.parseVideo(dto , response.body().string());
//                        System.out.println( videoDto );
//                    }
//                });
//            }
//        });
    }

}
