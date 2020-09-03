package com.example.saman.youtubedownloader;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by saman on 3/22/2016.
 */
public class VideoItem {
    private String title;
    private String description;
    private ArrayList<VideoThumbnail> thumbnails;
    private String thumbnailURL;
    private String id;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Array getThumbnailURL() {
//        return thumbnailURL;
//    }

//    public getThumbnail(VideoThumbnail thumbnail){
//       thumbnails.add(thumbnail);
//   }

//
   // public void setThumbnailURL(JSONObject thumbnail) {
   //    this.thumbnailURL = thumbnail;
//    }
public String getThumbnailURL() {
    return thumbnailURL;
}

    public void setThumbnailURL(String thumbnail) {
        this.thumbnailURL = thumbnail;
    }
}

class VideoThumbnail {
    private String url;
    private Integer width;
    private Integer height;


}