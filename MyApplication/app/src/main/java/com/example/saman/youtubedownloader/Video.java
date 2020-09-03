//package com.example.saman.youtubedownloader;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Typeface;
//import android.os.Bundle;
//import android.text.Html;
//import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.RadioButton;
//import android.widget.TextView;
//
////import com.aspsine.multithreaddownload.DownloadConfiguration;
////import com.aspsine.multithreaddownload.DownloadManager;
//
//import java.util.ArrayList;
//
///**
// * Created by saman on 3/8/2016.
// */
//public class Video extends Activity implements Animation.AnimationListener{
//
//
//    Button button;
//    Animation animationslidedown;
//    Animation animationslideup;
//    LinearLayout androidDropDownMenuIconItem;
//    TextView textView1;
//    TextView textView2;
//    TextView textView3;
//    TextView textView4;
//    String text = "";
//    RadioButton r1;
//    RadioButton r2;
//    RadioButton r3;
//    Button download;
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.video);
//        androidDropDownMenuIconItem = (LinearLayout) findViewById(R.id.horizontal_dropdown_icon_menu_items);
//        animationslidedown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
//        animationslidedown.setAnimationListener(this);
//        animationslideup = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
//
////        textView1=(TextView)findViewById(R.id.textView1);
////        Typeface typeFace2=Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Light.ttf");
////        textView1.setTypeface(typeFace2);
////        textView1=(TextView)findViewById(R.id.textView1);
////        Typeface typeFace3=Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Light.ttf");
////        textView1.setTypeface(typeFace3);
//        textView1=(TextView)findViewById(R.id.videoname);
//       Typeface typeFace3=Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Semibold.ttf");
//        textView1.setTypeface(typeFace3);
//        textView2=(TextView)findViewById(R.id.details);
//        Typeface typeFace4=Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Light.ttf");
//        textView2.setTypeface(typeFace4);
//        textView3=(TextView)findViewById(R.id.uploadername);
//        textView3.setTypeface(typeFace3);
//        textView4=(TextView)findViewById(R.id.medianame);
//        textView4.setTypeface(typeFace3);
//        download=(Button)findViewById(R.id.download);
//        Typeface typeFace9=Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Regular.ttf");
//        download.setTypeface(typeFace9);
//        text = "DOWNLOAD <b> VIDEO </b>";
//        download.setText(Html.fromHtml(text));
//
//  //      new HttpAsyncTask().execute("http://mobile.fsdcloud.com/api/you_tube_downloader/getvideo.php?videoid=olWG6jiMV0g" + str.toString());
//
//
////        r1=(RadioButton)findViewById(R.id.radioButton1);
////        Typeface typeFace10=Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Regular.ttf");
////        r1.setTypeface(typeFace10);
////        r2=(RadioButton)findViewById(R.id.radioButton2);
////        Typeface typeFace7=Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Regular.ttf");
////        r2.setTypeface(typeFace7);
////        r3=(RadioButton)findViewById(R.id.radioButton3);
////        Typeface typeFace8=Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Regular.ttf");
////        r3.setTypeface(typeFace8);
////
////
////
//
//        animationslideup.setAnimationListener(this);
//        button = (Button) findViewById(R.id.download);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
////                 initDownloader();
//
//    Intent i = new Intent(Video.this, Download.class);
//                startActivity(i);
//            }
//        });
//
//
//
//    }
//
//
//    public void horizontalDropDownIconMenu(View view) {
//        if (androidDropDownMenuIconItem.getVisibility() == View.VISIBLE) {
//            androidDropDownMenuIconItem.setVisibility(View.INVISIBLE);
//            androidDropDownMenuIconItem.startAnimation(animationslideup);
//        } else {
//            androidDropDownMenuIconItem.setVisibility(View.VISIBLE);
//            androidDropDownMenuIconItem.startAnimation(animationslidedown);
//        }
//    }
//
//    public void shareIt(View view)
//    {
//        Intent i=new Intent(android.content.Intent.ACTION_SEND);
//        i.setType("text/plain");
//        i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject test");
//        i.putExtra(android.content.Intent.EXTRA_TEXT, "extra text that you want to put");
//        startActivity(Intent.createChooser(i,"Share via"));
//
//
//    }
//    public void menuItemClick(View view) {
//        androidDropDownMenuIconItem.setVisibility(View.INVISIBLE);
//    }
//
//    @Override
//    public void onAnimationStart(Animation animation) {
//
//    }
//
//    @Override
//    public void onAnimationEnd(Animation animation) {
//
//    }
//
//    @Override
//    public void onAnimationRepeat(Animation animation) {
//
//    }
//}
//
//
