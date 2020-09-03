package com.example.saman.youtubedownloader;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saman on 3/25/2016.
 */
public class DownloadInfoArrayAdapter extends ArrayAdapter<DownloadInfo> implements View.OnClickListener {
    public static TextView progpercent;
    // Simple class to make it so that we don't have to call findViewById frequently
    //  private static final String    TAG = FileDownloadTask.class.getSimpleName();
    public static Integer result;
    LayoutInflater inflater5;
    ImageView delete;
    Activity activity1;
    int mvalue;
    ContextWrapper cw1;
    int flag = 0;
    int status = 0;
    GlobalDownload download;
    //   ArrayList downloadTask1;
    FileDownloadTask task;
    int cancelstatus = 0;
    ViewHolder holder;
    //  Integer aPosition;
    //  int position;
    Integer index;
    long mLastClickedPosition = -1;
    LayoutInflater inflater;

    private ArrayList list;
    private static class ViewHolder {
        TextView textView;
        ProgressBar progressBar;
        ImageView delete;
        DownloadInfo info;
        TextView size;
        TextView prog;
        public TextView progpercent;
    }


    private static final String TAG = DownloadInfoArrayAdapter.class.getSimpleName();

    public DownloadInfoArrayAdapter(Context context, int textViewResourceId,
                                    List<DownloadInfo> objects, ContextWrapper cw, Activity DownloadScreen) {
        super(context, textViewResourceId, objects);
        cw1 = cw;
        activity1 = DownloadScreen;
        list= (ArrayList) objects;
       // inflater = ( LayoutInflater )activity1.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public DownloadInfoArrayAdapter(Context context, int textViewResourceId,
                                    List<DownloadInfo> objects, ContextWrapper cw, Activity DownloadScreen, Integer aPos) {
        super(context, textViewResourceId, objects);
        cw1 = cw;
        activity1 = DownloadScreen;
        download = ((GlobalDownload) activity1.getApplicationContext());
        //   downloadTask1 = (ArrayList) download.getDownloadInfo();
        // aPosition = aPos;
        list= (ArrayList) objects;
       // inflater = ( LayoutInflater )activity1.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }




    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final DownloadInfo info = (DownloadInfo) getItem(position);
        holder = null;
        if (null == row) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row = inflater.inflate(R.layout.file_download_row, parent, false);
            holder = new ViewHolder();
            holder.textView = (TextView) row.findViewById(R.id.downloadFileName);
            holder.progressBar = (ProgressBar) row.findViewById(R.id.downloadProgressBar);
            holder.size = (TextView) row.findViewById(R.id.downloadFileSize);
            holder.progpercent = (TextView) row.findViewById(R.id.downloadFileProgress);
            holder.delete = (ImageView) row.findViewById(R.id.deletebutton);
            //  holder.button = (Button)row.findViewById(R.id.downloadButton);
            //  holder.info = info;

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();

            /*holder.info.setProgressBar(null);
            holder.info = info;
            holder.info.setProgressBar(holder.progressBar);
            holder.info.setProgress(null);
            holder.info = info;*/
            // holder.info.setProgress(holder.prog);
        }

        holder.textView.setText(info.getFilename() + "." + info.getFileType());
        Log.e("LIST ITEM", "FILE DOWNLOAD STATUS " + info.getDownloadState());
        Log.e("LIST ITEM", "FILE STATUS " + info.getStatus());
        Log.e("LIST ITEM", "FILE NAME " + info.getFilename());
        Log.e("LIST ITEM", "STATUS " + status);


        holder.progpercent.setText(info.getDownloadState().toString());
        if (info.getStatus() == null) {
            Log.e("DATABASE1", "null ");
            status = 3;
            info.setLastState(2);
//           info.setStatus("DOWNLOADING");
        } else if (info.getStatus() == DownloadInfo.State.DONE) {
            if (info.getLastState() == 1) {
                Log.e("DATABASE", "LS " + 1);
                info.setDownloadState(DownloadInfo.DownloadState.COMPLETE);
                status = 1;

            } else if (info.getLastState() == 0) {
                Log.e("DATABASE", "LS " + 2);
                info.setDownloadState(DownloadInfo.DownloadState.FAILED);

                status = 1;

            } else if (info.getLastState() == 3) {
                Log.e("DATABASE", "LS " + 3);
                info.setDownloadState(DownloadInfo.DownloadState.CANCELLED);

                status = 1;

            }

         /*  else if(info.getStatus()== DownloadInfo.State.COMPLETE)
       {
           info.setDownloadState(DownloadInfo.DownloadState.COMPLETE);
       }*/
            else {
                status = 2;
            }
        }

       /* if(cancelstatus==1)
        {
            info.setDownloadState(DownloadInfo.DownloadState.COMPLETE);
        }*/


        Log.e("DATABASE", "DOWNLOAD STATE " + info.getDownloadState().toString());
//        Log.e("DATABASE", "STATUS " + info.getStatus().toString());
        if (info.getDownloadState() == DownloadInfo.DownloadState.NOT_STARTED) {
            if (status == 3) {
                //   if (cancelstatus == 0) {
                //   Log.e("DATABASE", "STATUS  " + info.getStatus().toString());
                holder.progressBar.setProgress(info.getProgress());
                holder.progressBar.setMax(100);
                holder.progpercent.setText("DOWNLOADING");
                // }
            }
        }

//        if(info.getDownloadState()=="NOT_STARTED")
//        {
//            holder
//        }
//         holder.progpercent.setText(info.getDownloadState().toString());
        holder.size.setText(info.getFileSize());
        //     holder.delete.setVisibility(holder.delete.INVISIBLE);
        info.setProgressBar(holder.progressBar);

        //  info.setStatus(DownloadInfo.State.DOWNLOADING);

        if ((info.getDownloadState() != DownloadInfo.DownloadState.COMPLETE) && (info.getDownloadState() != DownloadInfo.DownloadState.DOWNLOADING) && (info.getDownloadState() != DownloadInfo.DownloadState.FAILED)
                && (info.getStatus() != DownloadInfo.State.COMPLETE) && (info.getStatus() != DownloadInfo.State.FAILED) && (info.getStatus() != DownloadInfo.State.DONE) && (info.getDownloadState() != DownloadInfo.DownloadState.CANCELLED)) {
            info.setDownloadState(DownloadInfo.DownloadState.DOWNLOADING);
            if (MainScreen.settingsvalue == 0) {

                Log.e("DATABASE", " " + 0);

                mvalue = 0;
                task = new FileDownloadTask(info, cw1, activity1, mvalue);
                info.setDownloadTask((FileDownloadTask) task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR));


            } else {
                Log.e("DATABASE", " " + 1);

                Log.e("DATABASE", " " + 2);
                if ((Settings.radiodevice.isChecked())) {
                    Log.e("DATABASE", " " + 3);
                    mvalue = 0;
                    task = new FileDownloadTask(info, cw1, activity1, mvalue);
//                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    info.setDownloadTask((FileDownloadTask) task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR));
                }

                if ((Settings.radiosdcard.isChecked())) {
                    Log.e("DATABASE", " " + 4);
                    mvalue = 1;
                    task = new FileDownloadTask(info, cw1, activity1, mvalue);
//                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    info.setDownloadTask((FileDownloadTask) task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR));

                }

            }


        }
        // if((info.getDownloadState()== DownloadInfo.DownloadState.FAILED) || (info.getDownloadState()== DownloadInfo.DownloadState.COMPLETE))
//        if((info.getDownloadState()== DownloadInfo.DownloadState.FAILED))
        if (info.getDownloadState() == DownloadInfo.DownloadState.COMPLETE) {
            //   if(status==1) {
            Log.e("DATABASE", "UPDATE COMPLETE " + info.getDownloadState());
            info.setStatus(DownloadInfo.State.DONE);
            String script2 = "update " + DatabaseHelper.TABLE_NAME + " set " + DatabaseHelper.COL_6 + " = '" + info.getStatus() + "', " + DatabaseHelper.COL_7 + " = 1 where " + DatabaseHelper.COL_1 + " = " + info.getFileid();
            Log.e("DATABASE", " " + script2);
            MainScreen.newDB.execSQL("update " + DatabaseHelper.TABLE_NAME + " set " + DatabaseHelper.COL_6 + " = '" + info.getStatus() + "', " + DatabaseHelper.COL_7 + " =1 where " + DatabaseHelper.COL_1 + " = " + info.getFileid());
            Log.e("DATABASE", "UPDATED ");
            info.setLastState(1);
            holder.progressBar.setProgress(R.drawable.download_bar);
            holder.progpercent.setText(info.getDownloadState().toString());
            cancelstatus = 0;

            //     holder.delete.setVisibility(holder.delete.VISIBLE);
            //  }
        }

        if (info.getDownloadState() == DownloadInfo.DownloadState.FAILED) {
            //  if(status==1) {
            info.setStatus(DownloadInfo.State.DONE);
            MainScreen.newDB.execSQL("update " + DatabaseHelper.TABLE_NAME + " set " + DatabaseHelper.COL_6 + " = '" + info.getStatus() + "', " + DatabaseHelper.COL_7 + " =0 where " + DatabaseHelper.COL_1 + " = " + info.getFileid());
            Log.e("DATABASE", "UPDATED ");
            info.setLastState(0);
            holder.progpercent.setText(info.getDownloadState().toString());
            cancelstatus = 0;


            // holder.delete.setVisibility(holder.delete.VISIBLE);
            // }
        }

        if (info.getDownloadState() == DownloadInfo.DownloadState.CANCELLED) {
            //  if(status==1) {
            info.setStatus(DownloadInfo.State.DONE);
            MainScreen.newDB.execSQL("update " + DatabaseHelper.TABLE_NAME + " set " + DatabaseHelper.COL_6 + " = '" + info.getStatus() + "', " + DatabaseHelper.COL_7 + " =0 where " + DatabaseHelper.COL_1 + " = " + info.getFileid());
            Log.e("DATABASE", "UPDATED ");
            //  holder.progressBar.setProgress(R.drawable.download_bar);
            info.setLastState(3);
            holder.progpercent.setText(info.getDownloadState().toString());
            cancelstatus = 0;


            // holder.delete.setVisibility(holder.delete.VISIBLE);
            // }
        }
         /*   String f_name=info.getFilename();
            Log.e("DATABASE", " " + f_name);
            Cursor c = DownloadScreen.newDB.rawQuery("SELECT FILE_NAME FROM " +
                    DatabaseHelper.TABLE_NAME, null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {

                        String file_name = c.getString(c.getColumnIndex("FILE_NAME"));
                        Log.e("DATABASE", " FILE NAME" + file_name);

                        if(file_name == f_name)
                        {   flag=1;}
                    }
                     while (c.moveToNext());
                }
            }

            Log.e("DATABASE", "FLAG" + flag);
            if(flag==0)
            {
                String fullfilename= info.getFilename() + "." + info.getFileType();
                Log.e("DATABASE", " " + fullfilename);
               // DownloadScreen.mydb.onInsert(fullfilename, info.getFileSize().toString(), info.getStatus().toString());

            }
           // holder.progressBar.setProgressDrawable();


             Log.e("DATABASE", "file inserted" + 0);*/


        //   holder.button.setEnabled(info.getDownloadState() == DownloadState.NOT_STARTED);
        // final Button button = holder.delete;
 /*       if(MainScreen.popup==1)

        {

            Log.e("FILE", "REMOVE" + MainScreen.popup);
            downloadList = ((GlobalDownload) activity1.getApplicationContext());
            downloadState = (ArrayList) downloadList.getDownloadInfo();
            Log.e("FILE", "REMOVE" + position);
            info.setDownloadState(DownloadInfo.DownloadState.FAILED);
            downloadState.remove(position);

         //   downloadState.get(position).notify();
      //      DownloadScreen.listView.setAdapter(DownloadScreen.adapter);
            MainScreen.popup=0;
            MainScreen.newDB.execSQL("delete from " + DatabaseHelper.TABLE_NAME + " where " + DatabaseHelper.COL_1 + " = " + info.getFileid());
            Log.e("FILE", "REMOVE" + info.getFileid());

            notifyDataSetChanged();
        }*/
    /*    public void popup_window(View v)
    {

    }*/
        holder.delete.setTag(position);

        holder.delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //     mLastClickedPosition = position;
                //   Log.e("POSITION", "POSITION " + position);
                index = (Integer) v.getTag();
                Log.e("POSITION", "  " + position);
                Log.e("POSITION", "TAG " + index);
                if (position == index) {
                    // DownloadInfo info1 = getItem(position);
                    //   if ((info.getDownloadState() == DownloadInfo.DownloadState.DOWNLOADING || info.getDownloadState() == DownloadInfo.DownloadState.NOT_STARTED)) {
                    //  info.setDownloadState(DownloadInfo.DownloadState.COMPLETE);
                    DownloadScreen sct = (DownloadScreen) activity1;

                    /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/

                    sct.onItemClick(position);
                    //     cancelstatus = 1;
                    //     holder.progressBar.invalidate();
                    //        info.setLastState(1);
                    //        info.setStatus(DownloadInfo.State.DONE);
                    //     task.cancel(true);
//                 task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                    //  info.setDownloadState(DownloadInfo.DownloadState.COMPLETE);
                }


                //   downloadList = ((GlobalDownload) activity1.getApplicationContext());
                //  downloadState = (ArrayList) downloadList.getDownloadInfo();

            /*    MainScreen.newDB.execSQL("delete from " + DatabaseHelper.TABLE_NAME + " where " + DatabaseHelper.COL_1 + " = " + info1.getFileid());
                //items.remove(index.intValue());
                //   CustomDialogClass.downloadfile=file.getAbsolutePath().toString();
                CustomDialogClass2.customfilename2 = info1.getFilename() + "." + info1.getFileType();
                Log.e("VIDEOPATH", " " + CustomDialogClass.downloadfile);
                CustomDialogClass2 cdd = new CustomDialogClass2(MainScreen.activity1);
                cdd.show();
                CustomDialogClass2.mPosition = position;
                CustomDialogClass2.id = info1.getFileid();
                //  downloadState.remove(position);

                Log.e("STATUS", "Status before stopping  " + info1.getDownloadState());

                // DownloadScreen.listView.setAdapter(null);


                //   DownloadScreen.listView.getAdapter().;
                //   notifyDataSetChanged();
                Log.e("DATABASE", "DELETED ");*/


                //   info.setDownloadState(DownloadState.QUEUED);
                // button.setEnabled(false);
                // button.invalidate();
                // FileDownloadTask task = new FileDownloadTask(info);
                // task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);






           /*  //   downloadList = ((GlobalDownload) MainScreen.activity1.getApplicationContext());
             //   downloadState = (ArrayList) downloadList.getDownloadInfo();
                if (info.getDownloadState() == DownloadInfo.DownloadState.COMPLETE) {
                    CustomDialogClass2.state = 1;

                }  else
                    {
                        CustomDialogClass2.state=0;
                    }
                    CustomDialogClass2.customfilename2 = info.getFilename() + "." + info.getFileType();
                    CustomDialogClass2.mPosition = position;
                    CustomDialogClass2.id = info.getFileid();
                    Log.e("DATABASE", "FILE ID " + info.getFileid());
                //    MainScreen.newDB.execSQL("delete from " + DatabaseHelper.TABLE_NAME + " where " + DatabaseHelper.COL_1 + " = " + info.getFileid());
                    Log.e("VIDEOPATH", " " + CustomDialogClass.downloadfile);
                    CustomDialogClass2 cdd = new CustomDialogClass2(MainScreen.activity1);
                    cdd.show();
                  //  notifyDataSetChanged();
                 //   thread.wait(5000);
            *//*    } else if (info.getDownloadState()== DownloadInfo.DownloadState.FAILED || info.getDownloadState() == DownloadInfo.DownloadState.DOWNLOADING){
                    CustomDialogClass2.state = 0;
                    downloadState.remove(position);
                    MainScreen.newDB.execSQL("delete from " + DatabaseHelper.TABLE_NAME + " where " + DatabaseHelper.COL_1 + " = " + info.getFileid());


                }
                else
                { downloadState.remove(position);
                    CustomDialogClass2.state = 0;
                }*//*

//                CustomDialogClass2.customfilename2 = info.getFilename() + "." + info.getFileType();
//                CustomDialogClass2.mPosition = position;
//                Log.e("DATABASE", "FILE ID " + info.getFileid());
//                MainScreen.newDB.execSQL("delete from " + DatabaseHelper.TABLE_NAME + " where " + DatabaseHelper.COL_1 + " = " + info.getFileid());
//                Log.e("VIDEOPATH", " " + CustomDialogClass.downloadfile);
//                CustomDialogClass2 cdd = new CustomDialogClass2(MainScreen.activity1);
//                    cdd.show();


*/
            }

        });


        //TODO: When reusing a view, invalidate the current progressBar.
        row.setOnClickListener(new OnItemClickListener(position));

        return row;
    }
    @Override
    public void onClick(View v) {
        Log.v("CustomAdapter", "=====Row button clicked=====");
    }

    /********* Called when Item click in ListView ************/
    private class OnItemClickListener  implements View.OnClickListener {
        private int mPosition;


        OnItemClickListener(final int position){
            mPosition = position;

        }

        @Override
        public void onClick(View view) {


            mLastClickedPosition = mPosition;

//
//
//            // next, update mLastClickedPosition
//            // find the image in your view and update it
           if(mPosition==mLastClickedPosition){

               DownloadInfo downloadInfo = getItem(mPosition);
               if(downloadInfo.getDownloadState() == DownloadInfo.DownloadState.COMPLETE) {
                   File rootdirectory = new File(Environment.getExternalStorageDirectory(), "YoutubeDownloaderVideos");
                   String nameoffile = downloadInfo.getFilename() + "." + downloadInfo.getFileType();
                   File file = new File(rootdirectory, nameoffile);
                   Uri contentUri = Uri.fromFile(file);
                   Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, contentUri);
                   activity1.sendBroadcast(mediaScanIntent);
//           intent.setData(Uri.fromFile(file));
//           activity.sendBroadcast(intent);
                   CustomDialogClass.downloadfile = file.getAbsolutePath().toString();
                   CustomDialogClass.customfilename = nameoffile;
                   CustomDialogClass.status = 1;
                   Log.e("VIDEOPATH", " " + CustomDialogClass.downloadfile);
                   CustomDialogClass cdd = new CustomDialogClass(MainScreen.activity1);
                   cdd.show();
               }
           }
//        //      ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
//          //      imageView.setImageResource(R.drawable.check);
//                Log.e("Testing", "mPosition==mLastClickedPosition " + mPosition);
//            }
//            else
//            if(mPosition!=mLastClickedPosition)
//            {
//               ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
//                imageView.setImageResource(R.drawable.unckeck);
//                Log.e("Testing", "mPosition!=mLastClickedPosition " + mPosition);
//
//            }




        }

    }


}