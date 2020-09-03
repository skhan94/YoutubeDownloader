package com.example.saman.youtubedownloader;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import static com.example.saman.youtubedownloader.R.drawable.menu_1;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

/**
 * Created by saman on 3/31/2016.
 */
public class Settings extends Activity {
    LinearLayout androidDropDownMenuIconItem;
    Animation animationslidedown;
    Animation animationbounceindown;
    Animation animationslideoutdown;
    ImageView menuicon1;
    public static RadioButton radiodevice;
    public static RadioButton radiosdcard;
    Animation animationslideup;
    ImageView gallery;
    public static ProgressDialog loading;
    public static final int REQUEST_TAKE_GALLERY_VIDEO=1;
    public static ProgressBar spinner;


  //  public static RadioButton radiodevice;
//    public static RadioButton radiosdcard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_screen);
        MainScreen.activity1 = this;
        loading = new ProgressDialog(this);

    //    androidDropDownMenuIconItem = (LinearLayout) findViewById(R.id.horizontal_dropdown_icon_menu_items);
   /*     animationslidedown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        animationslidedown.setAnimationListener(this);
        animationslideup = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationslideup.setAnimationListener(this);
      */  menuicon1 = (ImageView) findViewById(R.id.menuicon);
        gallery = (ImageView) findViewById(R.id.opengallery);
        //spinner = (ProgressBar)findViewById(R.id.progressBar1);
        radiodevice = (RadioButton) findViewById(R.id.radioButtondevice);
        radiosdcard = (RadioButton) findViewById(R.id.radioButtonsdcard);
        //      radiodevice.isChecked();
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        MainScreen.settingsvalue = 1;
      //  spinner.setVisibility(View.GONE);

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Settings.this, Gallery.class);
                loading.setMessage("Loading.. Please wait!!");
                loading.setIndeterminate(true);
                loading.show();

             //   spinner.setVisibility(View.VISIBLE);


                //i.putExtra("VIDEO_ID", code);

                startActivity(i);
//                final Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
//                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath()
//                        + "/YoutubeDownloaderVideos/");
//
//                intent.setDataAndType(uri, "video/*");
//                Log.e("URI", " " + uri);
//                //   MainScreen.activity1.startActivityForResult(Intent.createChooser(intent, "Select Video");
//
//
//                //  final Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                // galleryIntent.setType("*/*");
//                MainScreen.activity1.startActivityForResult(Intent.createChooser(intent, "Select Video"), REQUEST_TAKE_GALLERY_VIDEO);
//               // startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Boolean sdcard=savedInstanceState.getBoolean("sd_card");
        Boolean device=savedInstanceState.getBoolean("device");
        if (sdcard != null || device != null)
        {
            radiodevice.setChecked(device);
            radiosdcard.setChecked(sdcard);
        }

    }

//     public void checked()
//     {
//        radiodevice= (RadioButton) findViewById(R.id.radioButtondevice);
//         radiosdcard= (RadioButton) findViewById(R.id.radioButtonsdcard);
//         radiodevice.isChecked();
//     }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_GALLERY_VIDEO) {
                Uri selectedImageUri = data.getData();
                Log.e("VIDEOPATH", " " +selectedImageUri.toString());
                // OI FILE Manager
        //   String filemanagerstring = selectedImageUri.getPath();

                String[] filePathColumn = { MediaStore.Video.Media.DATA };

                Cursor cursor = getContentResolver().query(selectedImageUri,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();




                // MEDIA GALLERY
          //   String selectedImagePath = selectedImageUri.getPath();
                Log.e("VIDEOPATH", " " +picturePath);
                if (picturePath != null) {

                    Intent intent = new Intent(Settings.this,
                            DownloadedVideoPlayer.class);
                    intent.putExtra("path", picturePath);
                    startActivity(intent);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
       // spinner.setVisibility(View.GONE);
     //   androidDropDownMenuIconItem.setVisibility(View.INVISIBLE);
     //   menuicon1.setImageResource(R.drawable.menu);
        MainScreen.activity1=this;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        boolean sdcardstate = radiosdcard.isChecked();
        outState.putBoolean("sd_card", sdcardstate);
        Log.e("sdcardstate", "  " + sdcardstate);
        boolean devicestate = radiodevice.isChecked();
        outState.putBoolean("device",devicestate);
        Log.e("sdcardstate", "  " + devicestate);
    }

    public void back_click(View view)
    {  /*Intent i= new Intent (Settings.this, MainScreen.class);
        startActivity(i);*/
        finish();
    }


    public void sdcard_click(View view)
    {
        Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
           Log.e("SDCARD", "LOADING");
        if(isSDPresent)
        {
            Boolean isSDremovable = android.os.Environment.isExternalStorageRemovable();

           Log.e("SDCARD", "PRESENT");

            if(isSDremovable)
            {
                Log.e("SDCARD", "REMOVABLE SD CARD PRESENT");

            }
            else {
                Log.e("SDCARD", "REMOVABLE SD CARD NOT PRESENT");
                try {
                    AlertDialog alertDialog = new AlertDialog.Builder(Settings.this).create();

                    alertDialog.setTitle("Info");
                    alertDialog.setMessage("Sorry, No SD Card Found!");
                    alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            radiosdcard.setChecked(false);
                            radiodevice.setChecked(true);
                           // finish();
//                                        Intent i= new Intent(PlayerActivity.this,MainScreen.class);
//                                        startActivity(i);

                        }
                    });

                    alertDialog.show();
                } catch (Exception e) {
                    Log.d("ALERT DIALOG ERROR", "Show Dialog: " + e.getMessage());
                }


            }
        }
        else
        {
            Log.e("SDCARD", "NOT PRESENT");
        }

    }
//    public void deviceclick(View view)
//    {
//        radiosdcard.toggle();
//        radiodevice.toggle();
//    }

 /*   public void shareIt(View view)
    {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "URL path will be paste here");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }


    public void settingsIt(View view)
    {
        Intent sendIntent = new Intent(Settings.this,Settings.class);
        startActivity(sendIntent);
    }

    public void downloadIt(View view)
    {
        Intent sendIntent = new Intent(Settings.this,DownloadScreen.class);
        startActivity(sendIntent);
    }


    public void horizontalDropDownIconMenu(View view) {
        if (androidDropDownMenuIconItem.getVisibility() == View.VISIBLE) {
            androidDropDownMenuIconItem.setVisibility(View.INVISIBLE);
            androidDropDownMenuIconItem.startAnimation(animationslideup);
            menuicon1.setImageResource(R.drawable.menu);

        } else {
            androidDropDownMenuIconItem.setVisibility(View.VISIBLE);
            androidDropDownMenuIconItem.startAnimation(animationslidedown);
            menuicon1.setImageResource(menu_1);

        }
    }

    public void menuItemClick(View view) {
        androidDropDownMenuIconItem.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }*/

}
