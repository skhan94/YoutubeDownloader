package com.example.saman.youtubedownloader;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by saman on 4/7/2016.
 */
public class CustomDialogClass2 extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;
    public ImageView no2;
    public Button yes, no;
    public static String downloadfile;
    public static final int REQUEST_TAKE_GALLERY_VIDEO = 1;
    Activity dialogactivity;
    public static String customfilename2;
    TextView filename;
    public static Integer mPosition;
    public static Integer state;
   // DownloadInfoArrayAdapter mAdapter;
    ContextWrapper cw;
    GlobalDownload downloadList;
    ArrayList downloadState;
    DownloadInfo info;
    TextView text;


    public static String id;

    public CustomDialogClass2(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_window2);
        yes = (Button) findViewById(R.id.imageView4);
        no = (Button) findViewById(R.id.imageView5);
        no2 = (ImageView) findViewById(R.id.imageView3);
        filename = (TextView) findViewById(R.id.textView1);
        text = (TextView) findViewById(R.id.textView);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        no2.setOnClickListener(this);
        dialogactivity = MainScreen.activity1;
        filename.setText(customfilename2.toString());
        downloadList = ((GlobalDownload) MainScreen.activity1.getApplicationContext());
        downloadState = (ArrayList) downloadList.getDownloadInfo();
        cw = new ContextWrapper(MainScreen.activity1.getApplicationContext());
      //  mAdapter = new DownloadInfoArrayAdapter(MainScreen.activity1.getApplicationContext(), R.id.downloadListView, downloadState, cw, MainScreen.activity1);
  /*       if (state==1)
         {
            text.setText("Are you sure you want to delete the file permanantly?");
         }
         else
         {
             text.setText("Are you sure you want to delete the file? ");
         }*/
       // mAdapter = new DownloadInfoArrayAdapter(context,R.id.listview)
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView4:

            { // int count =DownloadScreen.adapter.getCount();
                //for(int i=0;i<count;i++) {
               //      DownloadScreen.adapter.getView(mPosition + 1, v, null);
                   // DownloadScreen.adapter.getItem(mPosition +1);
               // DownloadScreen.adapter.notifyDataSetChanged();
                //}

              //  downloadState.remove(mPosition);
                DownloadScreen.adapter.remove(DownloadScreen.adapter.getItem(mPosition));
                DownloadScreen.adapter.notifyDataSetChanged();



                dismiss();
                break;
            }

            case R.id.imageView5: {


//             final Intent intent = new Intent(Intent.ACTION_VIEW, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
//                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath()
//                        + File.separator + "YoutubeDownloaderVideos" + File.separator );
//            //    Uri uri = Uri.parse(Environment.
//              //          + "/YoutubeDownloaderVideos/");
//                intent.setDataAndType(uri, "*/*");
//                Log.e("URI", " " + uri);
                //   MainScreen.activity1.startActivityForResult(Intent.createChooser(intent, "Select Video");


                File rootdirectory = new File(Environment.getExternalStorageDirectory(), "YoutubeDownloaderVideos");


                File file = new File(rootdirectory, customfilename2);

                //  File videofiles=new File(Environment.getExternalStorageDirectory()+"/MyCameraVideo.mp4");
                Log.e("FILE", " name" + file.toString());
                file.delete();
                Log.e("FILE", "POSITION " + mPosition);
                 MainScreen.popup=1;

                DownloadScreen.adapter.remove(DownloadScreen.adapter.getItem(mPosition));
                DownloadScreen.adapter.notifyDataSetChanged();
//                DownloadScreen.adapter.getView(mPosition +1,v,null);
             //   DownloadScreen.adapter.
             //   DownloadScreen.adapter.notifyDataSetChanged();
             //   downloadState.remove(mPosition);

             //   DownloadScreen.adapter.notifyDataSetInvalidated();
             //   DownloadScreen.listView.setAdapter(DownloadScreen.adapter);
//                downloadState.remove(mPosition);
//                Log.e("FILE", "ADAPTER " + DownloadScreen.adapter);

                Log.e("FILE", "ADAPTER NOTIFIED ");

                dismiss();

                break;
/*

           File rootdirectory = new File(Environment.getExternalStorageDirectory(), "YoutubeDownloaderVideos");
           File file = new File(rootdirectory, customfilename2);
                file.delete();
                MainScreen.newDB.execSQL("delete from " + DatabaseHelper.TABLE_NAME + " where " + DatabaseHelper.COL_1 + " = " + id);

                downloadState.remove(mPosition);
               DownloadScreen.adapter.notifyDataSetChanged();
                    //  File videofiles=new File(Environment.getExternalStorageDirectory()+"/MyCameraVideo.mp4");
*/


       //         Log.e("POSITION", " " + mPosition);
       //
       //
//           Log.e("DATABASE", "FILE ID " + info.getFileid());
//          MainScreen.newDB.execSQL("delete from " + DatabaseHelper.TABLE_NAME + " where " + DatabaseHelper.COL_1 + " = " + info.getFileid());
//        Log.e("DATABASE", "DELETED ");
       /*    dismiss();



                break;*/
            }
            case R.id.imageView3:

            {
              // downloadState.remove(mPosition);
             //   DownloadScreen.adapter.remove(DownloadScreen.adapter.getItem(mPosition));
              //  DownloadScreen.adapter.notifyDataSetChanged();


                 dismiss();
                break;
            }
            default:
                break;
        }
        dismiss();
    }


//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == RESULT_OK) {
//            if (requestCode == REQUEST_TAKE_GALLERY_VIDEO) {
//                Uri selectedImageUri = data.getData();
//                Log.e("VIDEOPATH", " " +selectedImageUri.toString());
//                // OI FILE Manager
//                //   String filemanagerstring = selectedImageUri.getPath();
//
//                String[] filePathColumn = { MediaStore.Video.Media.DATA };
//
//                Cursor cursor = getContentResolver().query(selectedImageUri,
//                        filePathColumn, null, null, null);
//                cursor.moveToFirst();
//
//                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                String picturePath = cursor.getString(columnIndex);
//                cursor.close();
//
//
//
//
//                // MEDIA GALLERY
//                //   String selectedImagePath = selectedImageUri.getPath();
//                Log.e("VIDEOPATH", " " +picturePath);
//                if (picturePath != null) {
//
//                    Intent intent = new Intent(Activity.this,
//                            DownloadedVideoPlayer.class);
//                    intent.putExtra("path", picturePath);
//                    MainScreen.activity1.startActivity(intent);
//                }
//            }
//        }
//    }

}