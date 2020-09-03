package com.example.saman.youtubedownloader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
/**
 * Created by saman on 3/25/2016.
 */
public class DownloadScreen extends Activity {

    //public static String[] file_url={"http://imaze.net/apps/groups/123.mp4","http://imaze.net/apps/groups/456.mp4"};
    //"http://imaze.net/apps/groups/456.mp4"
    //public static String[] file={"FILE 1", "FILE 2"};
    //public static Integer[] filesize={26,22};

    public static ListView listView;
    public static View view1;
    public static DownloadInfoArrayAdapter adapter;
    Animation animationslidedown;
    Animation animationslideup;
    TextView textView2;
    public static Intent intent;
    ImageView menu;
    ImageView menuicon1;
    String text = "";
    public static Context context1;
    //  public static View popupView;
    public static LayoutInflater layoutInflater1;
    DownloadInfo downloadInfo;
    //  CustomBaseAdapterDownload adapter;
    // public static int i;
    static final int REQUEST = 1;
    public static String f_type;
    LinearLayout androidDropDownMenuIconItem;
    // public static Integer fileSize;
    public static Integer result;

    public static MyReceiver myReceiver;
    public static IntentFilter intentFilter;
    LayoutInflater inflater;
    ContextWrapper cw;
    GlobalDownload downloadList;
    ArrayList downloadState;
    DownloadInfo insertInfo;
    //    public static SQLiteDatabase newDB;
//    public static DatabaseHelper mydb;
    public static int dbvalue = 0;
    public static String statusvalue = "DOWNLOADING";
    public static PowerManager mgr;
    public static PowerManager.WakeLock wakeLock;
    public static String Absolutefilename;
    int aPosiiton = 0;
    FileDownloadTask task2;
    String file_name;
    String file_type;
    String file_url;
    String file_size;
    String file_id;


    //   public static Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_layout);
        cw = new ContextWrapper(getApplicationContext());
        //  downloadInfo = new ArrayList<DownloadInfo>();
        myReceiver = new MyReceiver();
        intentFilter = new IntentFilter("com.hmkcode.android.USER_ACTION");
        inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MainScreen.activity1 = this;
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        Log.e("WAKELOCK", "Wakelock initializing");
        mgr = (PowerManager) MainScreen.activity1.getSystemService(Context.POWER_SERVICE);
        mgr = (PowerManager) MainScreen.activity1.getSystemService(Context.POWER_SERVICE);
        wakeLock = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyWakeLock");
        Log.e("WAKELOCK", "Wakelock initialized");
        // newDB= new SQLiteDatabase();
     /*  mydb = new DatabaseHelper(this);

        Log.e("DATABASE", " " + 2);
        newDB = mydb.getWritableDatabase();*/
        intent = new Intent(Intent.ACTION_GET_CONTENT);
        menuicon1 = (ImageView) findViewById(R.id.menuicon);
     /*   animationslidedown.setAnimationListener(this);
        animationslidedown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        animationslideup = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationslideup.setAnimationListener(this);
     */
        textView2 = (TextView) findViewById(R.id.videodownloadname);
        Typeface typeFace2 = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf");
        textView2.setTypeface(typeFace2);
        text = "Video <b> Download </b>";
        textView2.setText(Html.fromHtml(text));
        //  androidDropDownMenuIconItem = (LinearLayout) findViewById(R.id.horizontal_dropdown_icon_menu_items);
        listView = (ListView) findViewById(R.id.downloadListView);
        downloadList = ((GlobalDownload) getApplicationContext());
        downloadState = (ArrayList) downloadList.getDownloadInfo();

        Log.e("DATABASE", " " + 3);
        if (dbvalue == 0) {
            populatelistview();
            dbvalue = 1;
        }
        Log.e("DATABASE", "listview poulated " + 4);
//        Cursor res= mydb.getAllData();
//        if(res.getCount()!=0)
//        {
//
//        }

        if (getIntent().getStringExtra("FILE_NAME") != null) {
            file_name = getIntent().getStringExtra("FILE_NAME");
            file_type = getIntent().getStringExtra("FILE_TYPE").toString();
            Log.e("file_type", "" + file_type);
            if (file_type.equals("video/mp4")) {
                f_type = "mp4";
            } else if (file_type.equals("video/webm")) {
                f_type = "webm";
            } else if (file_type.equals("video/x-flv")) {
                f_type = "flv";
            } else if (file_type.equals("video/3gpp")) {
                f_type = "3gpp";
            }
            Log.e("NAME", "" + file_name);

            file_url = getIntent().getStringExtra("FILE_URL");
            Log.e("URL", "" + file_url);
            file_size = getIntent().getStringExtra("FILE_SIZE");
            Log.e("SIZE", "" + file_size);
            Log.e("TYPE", file_type.toString());
            file_id = getIntent().getStringExtra("DATABASE_ID");
            Log.e("DATABASE", "FILE ID" + file_id);
            aPosiiton = MainScreen.actualPosition;

            int currentapiVersion = android.os.Build.VERSION.SDK_INT;
            if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP)
            {
                // Do something for lollipop and above versions

                PackageManager pm = cw.getPackageManager();
                int hasPerm = pm.checkPermission(
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        cw.getPackageName());
                if (hasPerm == PackageManager.PERMISSION_GRANTED)
                {
                    // do stuff
                    downloadState.add(new DownloadInfo(file_name, file_url, file_size, f_type.toString(), file_id, aPosiiton));

                    Log.e("DATABASE", "arraylist added ");
                    adapter = new DownloadInfoArrayAdapter(getApplicationContext(), R.id.downloadListView, downloadState, cw, DownloadScreen.this, aPosiiton);
                    MainScreen.actualPosition += 1;
                    //  downloadState.set(MainScreen.actualPosition, )
                    listView.setAdapter(adapter);
                } else
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(DownloadScreen.this).create();

                    alertDialog.setTitle("Info");
                    alertDialog.setMessage("You dont have the permission");
                    alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            Intent i = new Intent(DownloadScreen.this, SearchScreen.class);
                            startActivity(i);

                        }
                    });

                    alertDialog.show();
                }

            }
         else
            {
            downloadState.add(new DownloadInfo(file_name, file_url, file_size, f_type.toString(), file_id, aPosiiton));

            Log.e("DATABASE", "arraylist added ");
            adapter = new DownloadInfoArrayAdapter(getApplicationContext(), R.id.downloadListView, downloadState, cw, DownloadScreen.this, aPosiiton);
            MainScreen.actualPosition += 1;
            //  downloadState.set(MainScreen.actualPosition, )
            listView.setAdapter(adapter);
        }

    }

    else

    {
        adapter = new DownloadInfoArrayAdapter(getApplicationContext(), R.id.downloadListView, downloadState, cw, DownloadScreen.this);
        listView.setAdapter(adapter);

    }

    }

    private void populatelistview() {

        Cursor c = MainScreen.newDB.rawQuery("SELECT ID, FILE_NAME, FILE_SIZE, FILE_STATUS, STATE, FILE_TYPE FROM " +
                DatabaseHelper.TABLE_NAME + " where FILE_STATUS!= ?", new String[]{"DOWNLOADING"});
        Log.e("DATABASE", "CURSOR " + c);
    /*    if (c != null) {
            Log.e("DATABASE", "Cursor is not null");*/
        c.moveToFirst();
        Log.e("DATABASE", "Cursor is not null");
        while (c.isAfterLast() == false) {
            Log.e("DATABASE", "Cursor is not null");
            String file_name1 = c.getString(c.getColumnIndex("FILE_NAME"));
            Log.e("DATABASE", " FILE NAME" + file_name1);
            String file_size1 = c.getString(c.getColumnIndex("FILE_SIZE"));
            Log.e("DATABASE", " FILE SIZE" + file_size1);
            String file_status1 = c.getString(c.getColumnIndex("FILE_STATUS"));
            DownloadInfo.State.valueOf(file_status1);
            Log.e("DATABASE", " FILE STATUS" + DownloadInfo.State.valueOf(file_status1));
            String file_id1 = c.getString(c.getColumnIndex("ID"));
            Log.e("DATABASE", " " + file_id1);
            Integer file_state = c.getInt(c.getColumnIndex("STATE"));

            Log.e("DATABASE", " " + file_state);
            String file_type1 = c.getString(c.getColumnIndex("FILE_TYPE"));
            Log.e("DATABASE", " " + file_type1);
            aPosiiton = MainScreen.actualPosition;
            downloadState.add(new DownloadInfo(file_name1, file_size1, DownloadInfo.State.valueOf(file_status1), file_id1, file_state, file_type1, aPosiiton));

            adapter = new DownloadInfoArrayAdapter(getApplicationContext(), R.id.downloadListView, downloadState, cw, DownloadScreen.this, aPosiiton);
            listView.setAdapter(adapter);
            MainScreen.actualPosition += 1;
            c.moveToNext();
        }
        c.close();

         /*   if (c.moveToFirst()) {
                do {

                    String file_name1 = c.getString(c.getColumnIndex("FILE_NAME"));
                    Log.e("DATABASE", " FILE NAME" + file_name1);
                    String file_size1 = c.getString(c.getColumnIndex("FILE_SIZE"));
                    Log.e("DATABASE", " FILE SIZE" + file_size1);
                    String file_status1 = c.getString(c.getColumnIndex("FILE_STATUS"));
                    Log.e("DATABASE", " FILE STATUS" + file_status1);
                    String file_id1 = c.getString(c.getColumnIndex("ID"));
                    Log.e("DATABASE", " " + file_id1);
                    downloadState.add(new DownloadInfo(file_name1, file_size1, file_status1,  file_id1));
                    adapter = new DownloadInfoArrayAdapter(getApplicationContext(), R.id.downloadListView, downloadState, cw, DownloadScreen.this);
                    listView.setAdapter(adapter);
                } while (c.moveToNext());
            }*/
        // }
        //  newDB.execSQL("delete from " + DatabaseHelper.TABLE_NAME);
        //  Log.e("DATABASE", " DATABASE deleted");
    }


//    catch(QLiteException e
//    )
//
//    {
//        Log.e(getClass().getSimpleName(), "Could not create or Open the database");
//    }
//
//    finally
//    {
//        if (newDB != null)
//            newDB.execSQL("DELETE FROM " + tableName);
//        newDB.close();
//    }
//


//        Cursor c = mydb.getAllRows();
//        if(cursor!=null) {
//            String[] fromFieldNames = new String[]{DatabaseHelper.COL_2, DatabaseHelper.COL_3, DatabaseHelper.COL_4};
//            downloadState.add(new DownloadInfo(fromFieldNames[0], fromFieldNames[1], fromFieldNames[2]));
//            adapter = new DownloadInfoArrayAdapter(getApplicationContext(), R.id.downloadListView, downloadState, cw, DownloadScreen.this);
//            listView.setAdapter(adapter);
//        }
    //  }

/*

    public void horizontalDropDownIconMenu(View view) {
        if (androidDropDownMenuIconItem.getVisibility() == View.VISIBLE) {
            androidDropDownMenuIconItem.setVisibility(View.INVISIBLE);
            androidDropDownMenuIconItem.startAnimation(animationslideup);
            menuicon1.setImageResource(R.drawable.menu);
        } else {
            androidDropDownMenuIconItem.setVisibility(View.VISIBLE);
            androidDropDownMenuIconItem.startAnimation(animationslidedown);
            menuicon1.setImageResource(R.drawable.menu_1);
        }
    }

*/


/*    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
*//**/
//    public void shareIt(View view)
//    {
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "URL path will be paste here");
//        sendIntent.setType("text/plain");
//        startActivity(sendIntent);
//    }
//
//
//    public void settingsIt(View view)
//    {
//        Intent sendIntent = new Intent(DownloadScreen.this,Settings.class);
//        startActivity(sendIntent);
//    }
//
//    public void downloadIt(View view)
//    {
//        Intent sendIntent = new Intent(DownloadScreen.this,DownloadScreen.class);
//        startActivity(sendIntent);
//    }
//*/

    @Override
    protected void onResume() {
        super.onResume();
        MainScreen.activity1 = this;
        // androidDropDownMenuIconItem.setVisibility(View.INVISIBLE);
        // menuicon1.setImageResource(R.drawable.menu);
    }


    public void back_click(View view) {  /*Intent i= new Intent (DownloadScreen.this, MainScreen.class);
        startActivity(i);*/
        finish();
    }

    public void onItemClick(int position) {
        //DownloadInfo info1;
        // downloadInfo = (List<DownloadInfo>) DownloadScreen.adapter.getItem(position);
        downloadInfo = (DownloadInfo) DownloadScreen.adapter.getItem(position);
        if ((downloadInfo.getDownloadState() == DownloadInfo.DownloadState.DOWNLOADING || downloadInfo.getDownloadState() == DownloadInfo.DownloadState.NOT_STARTED)) {
            //  info.setDownloadState(DownloadInfo.DownloadState.COMPLETE);
            task2 = downloadInfo.getDownloadTask();
            task2.cancel(true);
            //cancelstatus = 1;
        }   // FileDownloadTask task = task1;
            //  FileDownloadTask task = new FileDownloadTask(downloadInfo, cw, this, 0);
            //    FileDownloadTask task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            //        info.setLastState(1);
            //        info.setStatus(DownloadInfo.State.DONE);
            // task.cancel(true);
//                 task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            //  info.setDownloadState(DownloadInfo.DownloadState.COMPLETE);
            //   }

            adapter.notifyDataSetChanged();

            //   downloadList = ((GlobalDownload) activity1.getApplicationContext());
            //  downloadState = (ArrayList) downloadList.getDownloadInfo();
            MainScreen.newDB.execSQL("delete from " + DatabaseHelper.TABLE_NAME + " where " + DatabaseHelper.COL_1 + " = " + downloadInfo.getFileid());
            //items.remove(index.intValue());
            //   CustomDialogClass.downloadfile=file.getAbsolutePath().toString();
            CustomDialogClass2.customfilename2 = downloadInfo.getFilename() + "." + downloadInfo.getFileType();
            Log.e("VIDEOPATH", " " + CustomDialogClass.downloadfile);
            CustomDialogClass2 cdd = new CustomDialogClass2(MainScreen.activity1);

            CustomDialogClass2.mPosition = position;
            CustomDialogClass2.id = downloadInfo.getFileid();
        cdd.show();
            //  downloadState.remove(position);
            Log.e("STATUS", "Status before stopping  " + downloadInfo.getDownloadState());



   /* protected void Insert() {

        Log.e("DATABASE", " " + 5);
        Log.e("DATABASE", "loop started " + 6);
        Log.e("DATABASE", "ADAPTER VALUE " + listView.getAdapter().getCount());
        for (int len = 0; len <listView.getAdapter().getCount(); len++) {

            Log.e("COUNT", " " + listView.getAdapter().getCount());
            mydb.onInsert(insertInfo.getFilename().toString(), insertInfo.getFileSize().toString(), insertInfo.getDownloadState().toString());


        }
        Log.e("COUNT", "inserted successfully " + 7);
    }*/
    }

}
