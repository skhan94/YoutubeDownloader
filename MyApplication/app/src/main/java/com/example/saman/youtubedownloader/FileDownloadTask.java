package com.example.saman.youtubedownloader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.PowerManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by saman on 3/25/2016.
 */
public class FileDownloadTask extends AsyncTask<String, Integer, Integer> {
    private static final String    TAG = FileDownloadTask.class.getSimpleName();
    final DownloadInfo  mInfo;
    TextView display;
    public int progress;
    public  String encodedurl;
    PopupWindow popupWindow;
    Activity activity;
    // ListView listview1;
    //  Context context;
    LayoutInflater layoutInflater;
    int i=0;
    int Mvalue;
    ContextWrapper cw;
    File file;
    String nameoffile;

    public static int cancelstatus =0;
    int a=0;
    ProgressBar bar;
     PowerManager mgr;
    PowerManager.WakeLock wakeLock;
  //  ProgressBar bar;

    //  DownloadInfoArrayAdapter mAdapter;

    public FileDownloadTask(DownloadInfo info, ContextWrapper cw1, Activity activity1, int mvalue) {
        mInfo = info;
        cw=cw1;
        activity=activity1;
        this.Mvalue=mvalue;
        mgr = (PowerManager) MainScreen.activity1.getSystemService(Context.POWER_SERVICE);
        wakeLock = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyWakeLock");
       // bar = mInfo.getProgressBar();

        //   listView1 = (ListView) listview.findViewById(R.id.downloadListView);
        //   mAdapter = new DownloadInfoArrayAdapter(, R.id.downloadListView,mInfo);
    }

    // @Override
    protected void onProgressUpdate(Integer... values) {
        mInfo.setProgress(values[0]);
        ProgressBar bar = mInfo.getProgressBar();
        if(!isCancelled()) {
            mInfo.setFilePercent(values[0]);
            //  Log.e("FILE PERCENT", String.valueOf(mInfo.getFilePercent()));
            //  mAdapter.notifyDataSetChanged();
            // mAdapter.setNotifyOnChange(true);

            // display = (TextView) row.findViewById(R.id.downloadFileProgress);
        //  ProgressBar bar = mInfo.getProgressBar();
            // Integer percent=mInfo.getFilePercent();
           // bar = mInfo.getProgressBar();
            if (bar != null) {
                bar.setProgress(mInfo.getProgress());
                // percent(mInfo.getFilePercent());
                // display.setText(mInfo.getProgress());

                //  bar.invalidate();
            }

//        DownloadScreen.adapter.notifyDataSetChanged();
//        DownloadScreen.listView.setAdapter(DownloadScreen.adapter);

        }
        else {
            bar.invalidate();

        }

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();

       /* GlobalDownload downloadList = ((GlobalDownload) MainScreen.activity1.getApplicationContext());
        ArrayList downloadState = (ArrayList) downloadList.getDownloadInfo();
        mInfo.setDownloadState(DownloadInfo.DownloadState.FAILED);
        downloadState.remove(CustomDialogClass2.mPosition);
       */
        mInfo.setDownloadState(DownloadInfo.DownloadState.CANCELLED);
        //   mInfo.setStatus(DownloadInfo.State.COMPLETE);
        //   mInfo.setDownloadState(DownloadInfo.DownloadState.COMPLETE);
//        bar.setVisibility(View.GONE);
      /*  File rootdirectory = new File(Environment.getExternalStorageDirectory(), "YoutubeDownloaderVideos");


        File file = new File(rootdirectory, CustomDialogClass2.customfilename2);
        Log.e("FILE", " name" + file.toString());
        //  File videofiles=new File(Environment.getExternalStorageDirectory()+"/MyCameraVideo.mp4");

        file.delete();*/

        DownloadScreen.adapter.notifyDataSetChanged();
        DownloadScreen.listView.setAdapter(DownloadScreen.adapter);

//        DownloadScreen.listView.setAdapter(DownloadScreen.adapter);

    }


    @Override

    protected Integer doInBackground(String... params) {
        int count;

//       if(mInfo.getDownloadState()!= DownloadInfo.DownloadState.COMPLETE)
//       {
        try {
            String state = Environment.getExternalStorageState();
            String root = Environment.getExternalStorageDirectory().toString();

            //   System.out.println("Downloading");
//

            URL url = new URL(mInfo.getFileUrl().toString());
            Log.e("URL", "" + url);
            HttpURLConnection conection = (HttpURLConnection) url.openConnection();
            conection.connect();
            conection.setConnectTimeout(1000*60*120);
            conection.setReadTimeout(1000*60*120);
            Log.e("connection", " " + 0);

            int lenghtOfFile = conection.getContentLength();
            // mInfo.setSize(lenghtOfFile);
            // mAdapter.notifyDataSetChanged();

            // getting file length
            //    int lenghtOfFile = mInfo.getFileSize()
            //      conection.getContentLength();
            Log.e("length", "" + lenghtOfFile);
            //input stream to read file - with 8k buffer
            String nameoffile = mInfo.getFilename() + "." + mInfo.getFileType();
            // Output stream to write file
            if(Mvalue==1) {
                if (Environment.MEDIA_MOUNTED.equals(state)) {
                    File rootdirectory = new File(Environment.getExternalStorageDirectory(), "YoutubeDownloaderVideos");
                    if (!rootdirectory.exists()) {
                        rootdirectory.mkdirs();
                    }


                    File file = new File(rootdirectory, nameoffile);
                    file.createNewFile();
                    Log.e("name of file", "" + nameoffile);


                    // GlobalDownload.dAdapter.notifyDataSetChanged();
                    InputStream input = new BufferedInputStream(url.openStream(), 8192);

                    OutputStream output = new FileOutputStream(file);
                    byte data[] = new byte[1024];
                    mInfo.setDownloadState(DownloadInfo.DownloadState.DOWNLOADING);
                    long total = 0;
                    while ((count = input.read(data)) != -1)
                    {
                      /*      if(mInfo.getDownloadState()== DownloadInfo.DownloadState.FAILED)
                            {
                                a=1;
                                i=0;
                            }*/
                        /*    else
                            {*/
                        if(!isCancelled()) {
                            total += count;
                            progress = (int) ((total * 100) / lenghtOfFile);

                                publishProgress(progress);

                               Log.e("PROGRESS", "" + mInfo.getFileType() + progress);
                            mInfo.setFilePercent(progress);
//               mAdapter.setNotifyOnChange(true);


                            // writing data to file
                            output.write(data, 0, count);
//                            i = 1;
                        }
                        else
                        {
                            Log.e("DOWNLOADING", "BREAK");
                           break;
                        }


                    }
                    if((count = input.read(data)) == -1)
                    {
                        Log.e("COUNT1", " " + count);
                        i=1;
                    }
                    else
                    {
                        Log.e("COUNT2", " " + count);
                        i=0;
                    }
                    //     }
//                       if(a==0) {
               //     mInfo.setDownloadState(DownloadInfo.DownloadState.COMPLETE);
                    mInfo.setStatus(DownloadInfo.State.COMPLETE);
                    // flushing output
                    output.flush();

                    // closing streams
                    output.close();
                    input.close();
                    Log.e("Download Complete", "" + 0);
                    Log.e("DEVICE SDCARD", " " + Mvalue);
                    //  }
                }

            }
            else {

                   /* File rootdirectory = activity.getDir("YoutubeDownloaderVideos", Context.MODE_PRIVATE);
                    if (!rootdirectory.exists()) {
                        rootdirectory.mkdirs();
                    }*/
                // ContextWrapper cw = new ContextWrapper(getApplicationContext());
                // path to /data/data/yourapp/app_data/imageDir
                Log.e("DEVICE", "start");
                File rootdirectory = new File(Environment.getExternalStorageDirectory(), "YoutubeDownloaderVideos");
                if (!rootdirectory.exists()) {
                    rootdirectory.mkdirs();
                }

                file = new File(rootdirectory, nameoffile);
                file.createNewFile();
                Log.e("DEVICE", "file created" + file.getAbsolutePath());
                Log.e("Context Wrapper ", " " + cw.toString());
                Log.e("DEVICE", "" + nameoffile);


                // GlobalDownload.dAdapter.notifyDataSetChanged();
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                OutputStream output = new FileOutputStream(file);
                byte data[] = new byte[1024];
                mInfo.setDownloadState(DownloadInfo.DownloadState.DOWNLOADING);
                long total = 0;
                while ((count = input.read(data)) != -1)
                {
                    if (!isCancelled()) {
                        total += count;
                        progress = (int) ((total * 100) / lenghtOfFile);


                            publishProgress(progress);

                        //    publishProgress(progress);
                         Log.e("PROGRESS", "" + mInfo.getFileType() + progress);
                        mInfo.setFilePercent(progress);
//               mAdapter.setNotifyOnChange(true);


                        // writing data to file
                        output.write(data, 0, count);
                     //   i = 1;
                        //   }

                    }
                    else
                    {Log.e("DOWNLOADING", "BREAK");
                       break;
                    }


                }
                if((count = input.read(data)) == -1)
                {
                    Log.e("COUNT3", " " + count);
                    i=1;
                }
                else
                {
                    Log.e("COUNT4", " " + count);
                    i=0;
                }
                //   if(a==0) {
                Log.e("Download Complete", "" + Mvalue);
                Log.e("DEVICE", " " + Mvalue);
              //  mInfo.setDownloadState(DownloadInfo.DownloadState.COMPLETE);
                //  mInfo.setStatus(DownloadInfo.State.COMPLETE);
                //  mInfo.setStatus("COMPLETE");
                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();
                Log.e("Download Complete", "" + 0);
                //    }

//
//                    String fileName = "MyFile";
//                    String content = "hello world";
//
//                    FileOutputStream outputStream = null;
//                    try {
//
//                        outputStream.write(content.getBytes());
//                        outputStream.close();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
            }

        } catch (Exception e) {
          
            Log.e("Error: ", e.getMessage());
            Log.e("DEVICE", "Error occured");
        }



        Log.e("DOWNLOAD", "" + mInfo.getDownloadState().toString());
        Log.e("ADAPTER", "" + DownloadScreen.adapter);



//


        return progress;
    }



    protected void onPostExecute(Integer progress) {
           wakeLock.release();


        if(i==1) {
            mInfo.setDownloadState(DownloadInfo.DownloadState.COMPLETE);
            //   mInfo.setStatus(DownloadInfo.State.COMPLETE);
            DownloadScreen.adapter.notifyDataSetChanged();
            DownloadScreen.listView.setAdapter(DownloadScreen.adapter);
            //  Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(file);
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,contentUri);
            activity.sendBroadcast(mediaScanIntent);
//           intent.setData(Uri.fromFile(file));
//           activity.sendBroadcast(intent);
            CustomDialogClass.downloadfile=file.getAbsolutePath().toString();
            CustomDialogClass.customfilename=mInfo.getFilename() + "." + mInfo.getFileType();
            Log.e("VIDEOPATH", " " + CustomDialogClass.downloadfile);
            CustomDialogClass cdd = new CustomDialogClass(MainScreen.activity1);
            CustomDialogClass.status=2;
            cdd.show();

        }
        else
        {   mInfo.setDownloadState(DownloadInfo.DownloadState.FAILED);
            mInfo.setStatus(DownloadInfo.State.FAILED);
            DownloadScreen.adapter.notifyDataSetChanged();
            DownloadScreen.listView.setAdapter(DownloadScreen.adapter);
        }


    }


    @Override
    protected void onPreExecute() {

//        PowerManager mgr =  (PowerManager)cw.getSystemService(Context.POWER_SERVICE);
//        PowerManager.WakeLock wakeLock = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyWakeLock");
        wakeLock.acquire();

    }



}