package com.ls.uniqlox.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ls.uniqlox.domain.Page;
import com.ls.uniqlox.logic.UniqloxParser;
import com.ls.uniqlox.model.ConstTagurl;

import java.io.IOException;

/**
 * Created by hx on 16-5-22.
 */
public class SplishAct extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(){
            @Override
            public void run() {
                UniqloxParser parser = new UniqloxParser();
                Page page = null;
                try {
                    page = parser.parse(ConstTagurl.uniqlox_urls[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(page);
            }
        }.start();

    }
}
