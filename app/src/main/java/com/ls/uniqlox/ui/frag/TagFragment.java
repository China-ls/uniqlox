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
import android.widget.TextView;

import com.ls.uniqlox.App;
import com.ls.uniqlox.R;
import com.ls.uniqlox.domain.Page;
import com.ls.uniqlox.domain.TagUrl;
import com.ls.uniqlox.domain.VideoDto;
import com.ls.uniqlox.logic.UniqloxParser;
import com.ls.uniqlox.ui.adapter.VideoAdapter;
import com.ls.uniqlox.util.ToastUtil;
import com.ls.uniqlox.util.ViewUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TagFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private TagUrl tagUrl;
    private VideoAdapter adapter;
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    @ViewInject(R.id.loadMore)
    private TextView tvLoadMore;
    @ViewInject(R.id.swipeRefresh)
    private SwipeRefreshLayout swipeRefreshLayout;
    private Handler handler = new Handler(Looper.getMainLooper());
    private boolean dataloaded;

    private UniqloxParser parser = new UniqloxParser();
    private Page page = null;

    private App app;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        tagUrl = (TagUrl) getArguments().getSerializable("uniqlox_urls");
        app = (App) getActivity().getApplicationContext();
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

        app.putCacheVideo(tagUrl.getName(), adapter.getItem());
        System.out.println("--------save instance");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        adapter.addAll(app.getCacheVideo(tagUrl.getName()));
        adapter.notifyDataSetChanged();

        System.out.println("--------onViewStateRestored");

    }

    private void loadData() {
        ViewUtil.refreshSwipeRefreshLayout(swipeRefreshLayout, true);
    }

    @Event(value = {R.id.loadMore})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.loadMore:
                ViewUtil.refreshSwipeRefreshLayout(swipeRefreshLayout, false);
                loadTagURL(page.getNexUrl(), false);
                break;
        }
    }

    @Override
    public void onRefresh() {
        loadTagURL(tagUrl, true);
    }

    private void loadTagURL(final TagUrl url,final boolean clear) {
        new Thread(){
            @Override
            public void run() {
                String toast = "";
                try {
                    page = parser.parse(url);
                    tagUrl = page.getTagUrl();
                    List<VideoDto> dtos = new ArrayList<VideoDto>();
                    List<VideoDto> list = page.getVideos();
                    for (VideoDto dto : list) {
                        VideoDto fullDto = parser.parseVideo(dto);
                        if (null != fullDto) {
                            dtos.add(fullDto);
                        }
                    }
                    if (clear) {
                        adapter.clear();
                    }
                    adapter.addAll(dtos);
                    toast = "刷新成功";
                } catch (IOException e) {
                    e.printStackTrace();
                    toast = "刷新失败：" + e.getMessage();
                }
                final String tt = toast;
                handler.post(new Runnable() {
                    @Override public void run() {
                        ToastUtil.Toast(getContext(), tt);
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                        if (null != page) {
                            boolean hasMore = null != page.getNexUrl();
                            tvLoadMore.setVisibility( hasMore ? View.VISIBLE : View.GONE );
                            tvLoadMore.setClickable( hasMore ? true : false );
                            tvLoadMore.setText( hasMore ? R.string.load_more : R.string.no_more_data );
                        }
                    }
                });
            }
        }.start();
    }
}
