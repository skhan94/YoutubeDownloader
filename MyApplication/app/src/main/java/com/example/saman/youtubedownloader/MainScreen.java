package com.example.saman.youtubedownloader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.example.saman.youtubedownloader.R.drawable.menu_1;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
/**
 * Created by saman on 3/7/2016.
 */
public class MainScreen extends Activity implements Animation.AnimationListener {
    Button button;
    LinearLayout androidDropDownMenuIconItem;
    Animation animationslidedown;
  //  public static SQLiteDatabase newDB;
   // public static DatabaseHelper mydb;
    Animation animationbounceindown;
    Animation animationslideoutdown;

    Animation animationslideup;
    RadioButton radiourl;
    RadioButton radiowords;
    EditText search;
    String url = "";
    String words = "";
    TextView textView1;
    TextView textView2;
    RadioButton r1;
    RadioButton r2;
    ImageView menuicon1;
    public int j;
    public static int popup;
    public static Activity activity1;
    public static int actualPosition = 0;
   // public static RadioButton radiodevice;
   // public static RadioButton radiosdcard;
     // Activity settings;
  //  Settings settings;
     public static int settingsvalue=0;
     RadioButton rdevice;
    RadioButton rsdcard;
    View view;
    InputMethodManager imm;
    DownloadInfo insertInfo;
    public static SQLiteDatabase newDB;
    public static DatabaseHelper mydb;
    String searchurl="";
    String searchwords="";



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        androidDropDownMenuIconItem = (LinearLayout) findViewById(R.id.horizontal_dropdown_icon_menu_items);
        animationslidedown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        animationslidedown.setAnimationListener(this);
        animationslideup = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationslideup.setAnimationListener(this);
        this.activity1 = this;
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mydb = new DatabaseHelper(this);
        Log.e("DATABASE", " " + 2);
        newDB = mydb.getWritableDatabase();
        Log.e("DATABASE", " " + 4);
        popup=0;

        menuicon1 = (ImageView) findViewById(R.id.menuicon);
        radiourl = (RadioButton) findViewById(R.id.radioURL);
        radiowords = (RadioButton) findViewById(R.id.radioWords);
        search = (EditText) findViewById(R.id.editText1);
        url = "SEARCH BY <b> URL </b>";
        radiourl.setText(Html.fromHtml(url));
        words = "SEARCH BY <b> KEYWORDS </b>";
        radiowords.setText(Html.fromHtml(words));
        textView1 = (TextView) findViewById(R.id.textView1);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf");
        textView1.setTypeface(typeFace);
        //  textView2 = (TextView) findViewById(R.id.textView2);
        Typeface typeFace2 = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Semibold.ttf");
//        textView2.setTypeface(typeFace2);
        radiourl = (RadioButton) findViewById(R.id.radioURL);
        radiourl.setTypeface(typeFace);
        radiowords = (RadioButton) findViewById(R.id.radioWords);
        radiowords.setTypeface(typeFace);
        search.setError(null);
        button = (Button) findViewById(R.id.button);
        button.setTypeface(typeFace);
        Log.e("RADIO BUTTON", " value  " + Settings.radiodevice);

//        if(!(settings.radiodevice.equals(null) && settings.radiosdcard.equals(null)))
//        {
//            settings.radiodevice.isChecked();
//        }


        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                if (radiowords.isChecked()) { //  Log.e("search value", "" + search.getText().toString());
                    if (search.getText().toString().length() != 0) {
                        j = 0;


//                        View view = this.getCurrentFocus();
//                        if (view != null) {
//                            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//                        }
                        Intent i = new Intent(MainScreen.this, SearchScreen.class);
                        i.putExtra("SEARCH CONTENT", search.getText().toString());
                        i.putExtra("j", j);
                        startActivity(i);
                    } else {
                        search.setError("Please enter something");
                        //Toast.makeText(MainScreen.this,"Please enter something",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    String urlstr = search.getText().toString();

                   try {
                       Uri uri = Uri.parse(urlstr);
                       String code = uri.getQueryParameter("v");
                       Log.e("VIDEO CODE", " " + code);
                       //  urlstr = urlstr.replace(" ", "");
                       if (code.length() == 11) {

                           // Log.e("SUBSTRING CODE", " " + codestr.toString());
                           //   getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                           Intent i = new Intent(MainScreen.this, PlayerActivity.class);
                           i.putExtra("VIDEO_ID", code);

                           startActivity(i);
                       } else {
                           search.setError("Please Enter a valid youtube URL");
                       }
                   } catch(Exception e)
                   {
                       search.setError("Please Enter a valid youtube URL");
                   }
                }


            }
        });

        search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                final int DRAWABLE_RIGHT = 2;


                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (search.getRight() - search.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        if (radiowords.isChecked()) {
                            if (search.getText().toString().length() != 0) {
                                j = 0;
                                Intent i = new Intent(MainScreen.this, SearchScreen.class);
                                i.putExtra("SEARCH CONTENT", search.getText().toString());
                                i.putExtra("j", j);
                                startActivity(i);
                            } else {
                                search.setError("Please enter something");
                                //Toast.makeText(MainScreen.this,"Please enter something",Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            try {
                                String urlstr = search.getText().toString();

                                Uri uri = Uri.parse(urlstr);
                                String code = uri.getQueryParameter("v");
                                Log.e("VIDEO CODE", " " + code);
                                //  urlstr = urlstr.replace(" ", "");
                                if (code.length() == 11) {

                                    // Log.e("SUBSTRING CODE", " " + codestr.toString());
                                    //   getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                                    Intent i = new Intent(MainScreen.this, PlayerActivity.class);
                                    i.putExtra("VIDEO_ID", code);

                                    startActivity(i);
                                } else {
                                    search.setError("Please Enter a valid youtube URL");
                                }
                            }
                            catch (Exception e)
                            {
                                search.setError("Please Enter a valid youtube URL");
                            }
                        }

                        return true;
                    }
                }
                return false;
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
      //  client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        androidDropDownMenuIconItem.setVisibility(View.INVISIBLE);
        menuicon1.setImageResource(R.drawable.menu);
        MainScreen.activity1=this;
    }


//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.e("DATABASE", " " + 5);
//        Log.e("DATABASE", "loop started " + 6);
//        Log.e("DATABASE", "ADAPTER VALUE " + DownloadScreen.listView.getAdapter().getCount());
//        for (int len = 0; len <DownloadScreen.listView.getAdapter().getCount(); len++) {
//             Log.e("DATABASE", " " + len);
//            Log.e("COUNT", " " + DownloadScreen.listView.getAdapter().getCount());
//            mydb.onInsert(insertInfo.getFilename().toString(), insertInfo.getFileSize().toString(), insertInfo.getDownloadState().toString());
//
//
//        }
//        Log.e("COUNT", "inserted successfully " + 7);
//    }
    public void shareIt(View view)
    {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.fsd.youtubedownloader");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
    public void settingsIt(View view)
    {
        Intent sendIntent = new Intent(MainScreen.this,Settings.class);
        startActivity(sendIntent);
    }

    public void downloadIt(View view)
    {
        Intent sendIntent = new Intent(MainScreen.this,DownloadScreen.class);
        startActivity(sendIntent);
    }


    public void searchurl(View view) {
        searchurl=search.getText().toString();
        search.setText(searchwords);
        search.setError(null);
        search.setHint("Paste Youtube Video URL");

    }

    public void searchwords(View view) {
        searchwords=search.getText().toString();
        search.setText(searchurl);
        search.setError(null);
        search.setHint("Enter a Keyword");
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

    }


}
