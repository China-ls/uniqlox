package com.ls.uniqlox.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.ls.uniqlox.R;
import com.ls.uniqlox.domain.TagUrl;
import com.ls.uniqlox.ui.adapter.VideoAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.frag_tag_url)
public class TestAct extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private TagUrl tagUrl;
    private VideoAdapter adapter;
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    @ViewInject(R.id.swipeRefresh)
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        swipeRefreshLayout.setColorSchemeColors(
                android.R.color.white,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light
        );
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);
        adapter = new VideoAdapter(this, LayoutInflater.from(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onRefresh() {

    }
}
