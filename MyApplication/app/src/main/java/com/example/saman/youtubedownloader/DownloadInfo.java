package com.example.saman.youtubedownloader;

import android.util.Log;
import android.widget.ProgressBar;

import java.util.List;

/**
 * Created by saman on 3/25/2016.
 */
public class DownloadInfo
{
    private final static String TAG = DownloadInfo.class.getSimpleName();
    public enum DownloadState {
        NOT_STARTED,
        FAILED,
        QUEUED,
        DOWNLOADING,
        COMPLETE,
        DONE,
        CANCELLED
    }

    public enum State
    {
        NOT_STARTED,
        DOWNLOADING,
        COMPLETE,
        FAILED,
        DONE
    }

    public  volatile State mState;
    private volatile DownloadState mDownloadState = DownloadState.NOT_STARTED;
    private FileDownloadTask downloadTask;
    private  String mFilename;
    //    private final String mFileUrl;
    private  String mFileSize;
    private  String mFileUrl="";
    private volatile Integer mProgress;
    //  private final Integer mFileSize;
    private volatile ProgressBar mProgressBar;
    private Integer mFilePercent;
    private String mFileType;
    private Integer mSize;
    private String mStatus;
    private String mID;
    private int lastState;
    public Integer mActualPosition;


    public DownloadInfo(String filename, String FileUrl, String size, String type, String id, Integer aPosition) {
        mFilename = filename;
        mProgress = 0;
        mFileUrl = FileUrl;
        mFilePercent=0;
        mSize = 0;
        //  mFileSize = mFileSize;
        mFileType= type;
        mFileSize = size;
        mProgressBar = null;
        mID=id;
        mActualPosition = aPosition;
    }

    public DownloadInfo(String filename, String size, State mstate1, String id, Integer lstate, String type, Integer aPosition )
    {
        mFilename = filename;
        mFileSize=size;
        mState=mstate1;
        mID=id;
        lastState=lstate;
        mFileType=type;
        mActualPosition = aPosition;

    }

    public ProgressBar getProgressBar() {
        return mProgressBar;
    }
    public void setProgressBar(ProgressBar progressBar) {
        Log.d(TAG, "setProgressBar " + mFilename + " to " + progressBar);
        mProgressBar = progressBar;
    }


    public void setDownloadState(DownloadState state) {
        mDownloadState = state;
    }
    public DownloadState getDownloadState() {
        return mDownloadState;
    }

    public Integer getProgress() {
        return mProgress;
    }

    public void setProgress(Integer progress) {
        this.mProgress = progress;
    }
    public FileDownloadTask getDownloadTask()
    {
        return downloadTask;
    }
    public  void setDownloadTask(FileDownloadTask task)
    {
        this.downloadTask = task;
    }
//public void getAsyncTask()
//{
//    retur
//}
    public Integer getActualPosiiton() {
        return mActualPosition;
    }

    public void setActualPosiiton(int aPosiiton) {
        this.mActualPosition = aPosiiton;
    }



    public String getFileid() {
        return mID;
    }

    public void setFileid(String id) {
        this.mID = id;
    }
//
//
//    public Integer getFileSize() {
//        return mFileSize;
//   }

    public String getFileType() {
        return mFileType;
    }

    public void setFileType(String FileType) {
        mFileType = FileType;
    }
    public Integer getSize() {
        return mSize;
    }

    public void setSize(Integer FSize) {
        mSize = FSize;
    }

    public State getStatus() {
        return mState;
    }

    public void setStatus(State mstate) {
        mState = mstate;
    }

    public String getFileSize() {
        return mFileSize;
    }

    public void setLastState(Integer lstate) {
        lastState = lstate;
    }

    public Integer getLastState() {
        return lastState;
    }

    public void setFileSize(String FileSize) {
        mFileSize = FileSize;
    }

    public Integer getFilePercent() {
        return mFilePercent;
    }

    public void setFilePercent(Integer FilePercent) {
        mFilePercent = FilePercent;
    }
//    public void setFileUrl(String FileUrl)
//    {
//        this.FileUrl = FileUrl;
//    }


    public String getFilename() {
        return mFilename;
    }
    public String getFileUrl()
    {
        return mFileUrl;
    }
}