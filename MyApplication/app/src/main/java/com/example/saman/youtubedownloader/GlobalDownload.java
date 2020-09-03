package com.example.saman.youtubedownloader;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saman on 3/28/2016.
 */
public class GlobalDownload extends Application {

    private List<DownloadInfo> downloadinfo;
 //   private List<FileDownloadTask> downloadTask;
    private String state;
    public static Context context;
    public static SQLiteDatabase newDB;
    public static DatabaseHelper mydb;

    @Override
    public void onCreate() {
        super.onCreate();

        downloadinfo = new ArrayList<DownloadInfo>();
    //    downloadTask = new ArrayList<FileDownloadTask>();
     /*   mydb = new DatabaseHelper(this);
       // Log.e("DATABASE", " " + 2);
        newDB = mydb.getWritableDatabase();*/

    }

  /* public List<FileDownloadTask> getDownloadTask()
   {
    return downloadTask;
    }
    public  void setDownloadTask(FileDownloadTask task)
    {
        this.downloadTask = (List<FileDownloadTask>) task;
    }*/


    public List<DownloadInfo> getDownloadInfo(){
        return downloadinfo;
    }
    public String getDownloadStates()
    {
        return state;
    }

//    public String setDownloadStates(DownloadInfo.DownloadState s)
//    {
//        state = s;
//
//    }


//    public int getI(){
//        return i;
//    }

//    public void setI(int s)
//    {
//        i = s;
//    }
//    public void setDownloadInfo(ArrayList<List> s)
//    {
//        downloadinfo = s;
//    }


//    public DownloadScreen getDownloadScreen()
//    {
//        return DScreen;
//    }
//
//    public void setDownloadScreen(DownloadScreen d)
//    {
//        DScreen=d;
//    }
}
