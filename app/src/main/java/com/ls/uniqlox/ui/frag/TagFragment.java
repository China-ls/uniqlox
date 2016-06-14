package com.ls.uniqlox.ui.frag;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ls.uniqlox.R;
import com.ls.uniqlox.domain.TagUrl;
import com.ls.uniqlox.ui.adapter.VideoAdapter;
import com.ls.uniqlox.util.ViewUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class TagFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private TagUrl tagUrl;
    private VideoAdapter adapter;
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    @ViewInject(R.id.swipeRefresh)
    private SwipeRefreshLayout swipeRefreshLayout;
    private Handler handler = new Handler(Looper.getMainLooper());
    private boolean dataloaded;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        tagUrl = (TagUrl) getArguments().getSerializable("uniqlox_urls");
        View view = inflater.inflate(R.layout.frag_tag_url, null);
        x.view().inject(this, view);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(this);
        adapter = new VideoAdapter(getContext(), inflater);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (null == savedInstanceState) {
            dataloaded = false;
        } else {
            dataloaded = savedInstanceState.getBoolean("dataloaded");
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!dataloaded) {
            dataloaded = true;
            loadData();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("dataloaded", dataloaded);
        outState.putSerializable("uniqlox_urls", tagUrl);
    }

    private void loadData() {
        ViewUtil.refreshSwipeRefreshLayout(swipeRefreshLayout, true);
    }

    private void restoreFrame() {

    }

    @Override
    public void onRefresh() {
        new Thread(){
            @Override
            public void run() {
                handler.postDelayed(new Runnable() {
                    @Override public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 5000);
            }
        }.start();
    }
}
