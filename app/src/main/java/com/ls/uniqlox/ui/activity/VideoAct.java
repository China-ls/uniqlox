package com.ls.uniqlox.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ls.uniqlox.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

@ContentView(R.layout.activity_video)
public class VideoAct extends AppCompatActivity implements MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener {
    final String TAG = "LSVIDEO";



    private VideoView videoView;
    private ProgressBar pb;
    private TextView downloadRateView;
    private TextView loadRateView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.isInitialized(getApplicationContext());
        x.view().inject(this);
        playfunction();
    }

    void playfunction() {
        String path = getIntent().getStringExtra("path").replace("https", "http");
        if (path == "") {
            // Tell the user to provide a media file URL/path.
            Toast.makeText(this, "路径出错，无法播放此视频。", Toast.LENGTH_LONG).show();
            finish();
        } else {
            videoView = (VideoView) findViewById(R.id.buffer);
            pb = (ProgressBar) findViewById(R.id.probar);

            downloadRateView = (TextView) findViewById(R.id.download_rate);
            loadRateView = (TextView) findViewById(R.id.load_rate);

            videoView.setHardwareDecoder(true);
            videoView.setVideoURI(Uri.parse(path));
            videoView.setMediaController(new MediaController(this));
            videoView.requestFocus();
            videoView.setOnInfoListener(this);
            videoView.setOnBufferingUpdateListener(this);
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    // optional need Vitamio 4.0
                    mediaPlayer.setPlaybackSpeed(1.0f);
                }
            });
        }
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                if (videoView.isPlaying()) {
                    videoView.pause();
                    pb.setVisibility(View.VISIBLE);
                    downloadRateView.setText("");
                    loadRateView.setText("");
                    downloadRateView.setVisibility(View.VISIBLE);
                    loadRateView.setVisibility(View.VISIBLE);

                }
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                videoView.start();
                pb.setVisibility(View.GONE);
                downloadRateView.setVisibility(View.GONE);
                loadRateView.setVisibility(View.GONE);
                break;
            case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                downloadRateView.setText("" + extra + "kb/s" + "  ");
                break;
        }
        return true;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        loadRateView.setText(percent + "%");
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        videoView.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Process.killProcess(Process.myPid());
    }

}
