package com.example.saman.youtubedownloader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by saman on 3/8/2016.
 */
public class Download extends Activity {

    ListView listView;
    ListModelDownload downloadList;
    Animation animationslidedown;
    Animation animationslideup;
    TextView textView2;
    ListView l1;
    ImageView menu;
    ImageView menuicon1;
    String text="";


    //ArrayList<String> arrayList;
    CustomBaseAdapterDownload adapter;
    //  Button button;

    public  Download CustomListView = null;
    public  ArrayList<ListModelDownload> CustomListViewValuesArr = new ArrayList<ListModelDownload>();


    LinearLayout androidDropDownMenuIconItem;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download);
        menuicon1=(ImageView)findViewById(R.id.menuicon);
//        animationslidedown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
//        animationslidedown.setAnimationListener(this);
//        animationslideup = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
//        animationslideup.setAnimationListener(this);
//        CustomListView=this;
        textView2 = (TextView) findViewById(R.id.videoname);
        Typeface typeFace2 = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf");
        textView2.setTypeface(typeFace2);
        text = "Video <b> Download </b>";
        textView2.setText(Html.fromHtml(text));
//        l1 = (ListView) findViewById(R.id.listview1);
//        Typeface typeFace3 = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Semibold.ttf");
//        textView2.setTypeface();
      //  androidDropDownMenuIconItem = (LinearLayout) findViewById(R.id.horizontal_dropdown_icon_menu_items);
        setListData();
//        //create arraylist
//       CustomListViewValuesArr = new ArrayList<>();
//        CustomListViewValuesArr.add("Jurassic World");
//        arrayList.add("Fast and Furious");

        //listview
        //listView = (ListView) findViewById(R.id.listview1);

        //adapter
        //adapter = new CustomBaseAdapterDownload(Download.this, arrayList);
        adapter=new CustomBaseAdapterDownload(Download.this,CustomListViewValuesArr);
        listView.setAdapter(adapter);

    }

    public void setListData() {

        for (int i = 0; i < 2; i++) {

            ListModelDownload downloadList = new ListModelDownload();

            /******* Firstly take data in model object ******/
            downloadList.setVideoName("Jurassic Park " +i);
            downloadList.setSize("24MB" +i);

            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add(downloadList);
            downloadList.setVideoName("Fast and Furious " +i);
            downloadList.setSize("24MB" +i);


            CustomListViewValuesArr.add(downloadList);
        }
    }
//        }

//    }

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

    public void menuItemClick(View view) {
        androidDropDownMenuIconItem.setVisibility(View.INVISIBLE);
    }
*/

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

    public void back_click(View view)
    {  Intent i= new Intent (Download.this, MainScreen.class);
        startActivity(i);
    }

}
