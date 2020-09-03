package com.example.saman.youtubedownloader;

import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by saman on 3/11/2016.
 */
public class ListModelDownload {

    private  String VideoName="";
    private String Size="";
    private String Status="";
    public ProgressBar bar;

    public void setVideoName(String VideoName)
    {
        this.VideoName = VideoName;
    }

    public String getVideoName()
    {
        return this.VideoName;
    }


    public void setSize(String Size)
    {
        this.Size = Size;
    }

    public String getSize()
    {
        return this.Size;
    }

    public void setStatus(String Status)
    {
        this.Status = Status;
    }

    public String getStatus()
    {
        return this.Status;
    }

    public ProgressBar getBar() {
        return this.bar;
    }

    public void setBar(ProgressBar bar)
    {
        this.bar = bar;
    }
}
