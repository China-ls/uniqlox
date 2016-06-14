package com.ls.uniqlox.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ls.uniqlox.R;
import com.ls.uniqlox.ui.adapter.VpTagAdapter;
import com.viewpagerindicator.TabPageIndicator;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_app)
public class AppAct extends AppCompatActivity {
    @ViewInject(R.id.indicator)
    private TabPageIndicator tabPageIndicator;

    //适配器类
    private VpTagAdapter adapter;
    @ViewInject(R.id.viewPager)
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        adapter = new VpTagAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabPageIndicator.setViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);
    }
}
