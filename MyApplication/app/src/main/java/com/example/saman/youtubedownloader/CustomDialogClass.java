package com.example.saman.youtubedownloader;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saman.youtubedownloader.DownloadedVideoPlayer;
import com.example.saman.youtubedownloader.MainScreen;
import com.example.saman.youtubedownloader.R;

public class CustomDialogClass extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;
    public ImageView no2;
    public Button yes, no;
    public static String downloadfile;
    public static final int REQUEST_TAKE_GALLERY_VIDEO = 1;
    Activity dialogactivity;
    public static String customfilename;
    TextView filename;
    TextView text;
    public static int status;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_window);

        yes = (Button) findViewById(R.id.imageView4);
        no = (Button) findViewById(R.id.imageView5);
        no2 = (ImageView) findViewById(R.id.imageView3);
        filename = (TextView) findViewById(R.id.textView1);
        text = (TextView) findViewById(R.id.textView);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        no2.setOnClickListener(this);
        dialogactivity = MainScreen.activity1;
        filename.setText(customfilename.toString());
        if (status==2)
        {
            text.setText("Your video has been downloaded!");
        }
        else if (status == 1)
        {
            text.setText("Do you want to play this video?");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView4:
                dismiss();
                break;
            case R.id.imageView5: {
//             final Intent intent = new Intent(Intent.ACTION_VIEW, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
//                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath()
//                        + File.separator + "YoutubeDownloaderVideos" + File.separator );
//            //    Uri uri = Uri.parse(Environment.
//              //          + "/YoutubeDownloaderVideos/");
//                intent.setDataAndType(uri, "*/*");
//                Log.e("URI", " " + uri);
                //   MainScreen.activity1.startActivityForResult(Intent.createChooser(intent, "Select Video");


                //  final Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                // galleryIntent.setType("*/*");
                //  MainScreen.activity1.startActivity(intent);
                Intent intent = new Intent(this.c, DownloadedVideoPlayer.class);
                intent.putExtra("path", downloadfile);
                Log.e("VIDEOPATH", " " + downloadfile);
                //   intent.putExtra("path",downloadfile);
                c.startActivity(intent);
                // c.startActivityForResult(Intent.createChooser(intent, "Select Video"), REQUEST_TAKE_GALLERY_VIDEO);

                break;
            }
            case R.id.imageView3:
                dismiss();
                break;
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