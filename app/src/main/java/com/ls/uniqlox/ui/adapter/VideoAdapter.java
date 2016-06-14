package com.ls.uniqlox.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ls.uniqlox.R;
import com.ls.uniqlox.domain.VideoDto;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<VideoDto> videoDtos;

    public VideoAdapter(Context context, LayoutInflater inflater) {
        this.inflater = inflater;
        this.context = context;
        videoDtos = new ArrayList<VideoDto>();
        videoDtos.add(new VideoDto());
        videoDtos.add(new VideoDto());
        videoDtos.add(new VideoDto());
        videoDtos.add(new VideoDto());
        videoDtos.add(new VideoDto());
    }

    public VideoDto getItem(int position) {
        if (position >= videoDtos.size()) {
            return null;
        }
        return videoDtos.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_video, parent,
                false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VideoDto item = getItem(position);
        holder.tvTitle.setText("item" + position);
        holder.tvHot.setText("hot" + position);
    }

    @Override
    public int getItemCount() {
        return videoDtos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @ViewInject(R.id.title)
        TextView tvTitle;
        @ViewInject(R.id.hot)
        TextView tvHot;
        @ViewInject(R.id.image)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            x.view().inject(this, itemView);
        }
    }

}
