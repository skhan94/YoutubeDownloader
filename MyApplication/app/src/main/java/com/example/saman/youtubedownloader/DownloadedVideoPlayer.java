package com.example.saman.youtubedownloader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by saman on 4/5/2016.
 */
public class DownloadedVideoPlayer extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.downloaded_video_player);
        String videopath=getIntent().getStringExtra("path");
        VideoView vid = (VideoView)findViewById(R.id.videoView);
        vid.setVideoPath(videopath);
        vid.setMediaController(new MediaController(this));
        vid.start();
        vid.requestFocus();
    }
}
