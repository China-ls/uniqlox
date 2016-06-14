package com.ls.uniqlox.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ls.uniqlox.R;
import com.ls.uniqlox.domain.VideoDto;
import com.ls.uniqlox.ui.activity.VideoAct;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<VideoDto> videoDtos;

    public VideoAdapter(Context context, LayoutInflater inflater) {
        this.inflater = inflater;
        this.context = context;
        videoDtos = new ArrayList<VideoDto>();
    }

    public ArrayList<VideoDto> getItem() {
        return new ArrayList<VideoDto>(videoDtos);
    }

    public VideoDto getItem(int position) {
        if (position >= videoDtos.size()) {
            return null;
        }
        return videoDtos.get(position);
    }

    public void clear() {
        videoDtos.clear();
    }
    public void addAll(List<VideoDto> list) {
        if (null == list) {
            return;
        }
        videoDtos.addAll(list);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_video, parent,
                false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final VideoDto item = getItem(position);

        holder.tvTitle.setText(item.getTitile());
        holder.tvHot.setText(item.getHot());
        x.image().bind(holder.imageView, item.getPicurl());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoAct.class);
                intent.putExtra("path", item.getVideourl());
                context.startActivity(intent);
            }
        });
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
