package com.example.saman.youtubedownloader;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
/**
 * Created by saman on 3/8/2016.
 */
public class SearchScreen extends Activity  {
    ListView listView;
    //ListModel downloadList;
    LinearLayout androidDropDownMenuIconItem;
    ArrayList<String> arrayList;
    CustomBaseAdapter adapter;
    Animation animationslidedown;
    Animation animationslideup;
    RadioButton radiourl;
    RadioButton radiowords;
    EditText search;
    String url = "";
    String words = "";
    Button button;
    //  public static int i;
    TextView textView2;
    ImageView menuicon1;
    private ProgressDialog dialog;
    public SearchScreen CustomListView = null;
   ArrayList<VideoItem> video = new ArrayList<VideoItem>();
    Button bsearch;
    public String str = "";
    public String str1 = "";
    InputMethodManager imm;
    String searchurl;
    String searchwords;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);
     //   androidDropDownMenuIconItem = (LinearLayout) findViewById(R.id.horizontal_dropdown_icon_menu_items);
        CustomListView = this;
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
         dialog = new ProgressDialog(SearchScreen.this);
        MainScreen.activity1=this;
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        menuicon1 = (ImageView) findViewById(R.id.menuicon);
     /*   animationslidedown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        animationslidedown.setAnimationListener(this);
        animationslideup = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationslideup.setAnimationListener(this);
     */
        radiourl = (RadioButton) findViewById(R.id.radioURL);
        radiowords = (RadioButton) findViewById(R.id.radioWords);
        search = (EditText) findViewById(R.id.editText1);
        search.setError(null);
        // bsearch = (Button) findViewById(R.id.bsearch);
        url = "SEARCH BY <b> URL </b>";
        radiourl.setText(Html.fromHtml(url));
        words = "SEARCH BY <b> KEYWORDS </b>";
        radiowords.setText(Html.fromHtml(words));
        textView2 = (TextView) findViewById(R.id.textView1);
        Typeface typeFace2 = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Semibold.ttf");
        textView2.setTypeface(typeFace2);
        radiourl = (RadioButton) findViewById(R.id.radioURL);
        Typeface typeFace3 = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf");
        radiourl.setTypeface(typeFace3);
        radiowords = (RadioButton) findViewById(R.id.radioWords);
        radiowords.setTypeface(typeFace3);
        String mainsearch = getIntent().getStringExtra("SEARCH CONTENT");
        listView = (ListView) findViewById(R.id.listview);
        search.setText(mainsearch.toString());
        if (mainsearch != null) {
        //    str1 = mainsearch.toString();
            Log.e("str", "" + str);
         //   str1 = str1.replaceAll(" ", "+");
            try {
                str1 = URLEncoder.encode(mainsearch.toString(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Log.e("Str", "" + str);

            if (isConnected()) {
                //  adapter.clear();
                listView.setAdapter(null);
//

                new HttpAsyncTask().execute("https://www.googleapis.com/youtube/v3/search?order=viewCount&q=" + str1 + "&videoType=any&type=video&maxResults=10&part=snippet&fields=items(id/videoId,snippet/title,snippet/thumbnails)&key=AIzaSyAlNc59CIDo-yisGQ8Q0pOuOvqIWX4M5rA");
                adapter = new CustomBaseAdapter(SearchScreen.this, video);
                listView.setAdapter(adapter);
            }
        }

        search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
              final int DRAWABLE_RIGHT = 2;


              if (event.getAction() == MotionEvent.ACTION_UP) {
                  if (event.getRawX() >= (search.getRight() - search.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                      // your action here
                     if(radiowords.isChecked()) {
                         if(search.getText().length()!=0) {
                             String str2 = search.getText().toString();
                             Log.e("str", "" + str);
                             try {
                                 str = URLEncoder.encode(str2.toString(), "utf-8");
                             } catch (UnsupportedEncodingException e) {
                                 e.printStackTrace();
                             }
                             Log.e("Str", "" + str);

                             if (isConnected()) {
                                 //  adapter.clear();
                                 listView.setAdapter(null);
                                 new HttpAsyncTask().execute("https://www.googleapis.com/youtube/v3/search?order=viewCount&q=" + str.toString() + "&videoType=any&type=video&maxResults=50&part=snippet&fields=items(id/videoId,snippet/title,snippet/thumbnails)&key=AIzaSyAlNc59CIDo-yisGQ8Q0pOuOvqIWX4M5rA");
                                 adapter = new CustomBaseAdapter(SearchScreen.this, video);
                                 listView.setAdapter(adapter);
                             }
                             else {
                                 search.setError("Please enter a Keyword");
                             }

                         }
                     }
                      else
                     {
                         try {
                             String urlstr = search.getText().toString();

                             Uri uri = Uri.parse(urlstr);
                             String code = uri.getQueryParameter("v");
                             Log.e("VIDEO CODE", " " + code);
                             //  urlstr = urlstr.replace(" ", "");
                             if (code.length() == 11) {

                                 // Log.e("SUBSTRING CODE", " " + codestr.toString());
                                 //   getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                                 Intent i = new Intent(SearchScreen.this, PlayerActivity.class);
                                 i.putExtra("VIDEO_ID", code);

                                 startActivity(i);
                             } else {
                                 search.setError("Please Enter a valid youtube URL");
                             }
                         }
                         catch(Exception e)
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
        //adapter


    }
    public class HttpAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog.setMessage("Processing... Please wait!");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... urls) {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            Log.e("URL", "" + urls[0]);
            return GET(urls[0]);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
//


            if (result != null) // add this
            {
                String response = result.toString();
                   Log.e("Response", "" + response);

                try {

                    // JSONArray new_array = new JSONArray(response);
                    JSONObject jsonobject = new JSONObject(response);
                    JSONArray new_array = jsonobject.getJSONArray("items");
                 //   Log.e("urlobject","" + i);
                    video.clear();
                    for (int i = 0; i < new_array.length(); i++) {
                        VideoItem aVideo = new VideoItem();

                        JSONObject videoObject = new_array.getJSONObject(i);
                        JSONObject idObject = videoObject.getJSONObject("id");
                        aVideo.setId(idObject.getString("videoId"));
                        JSONObject snippetObject = videoObject.getJSONObject("snippet");
                        aVideo.setTitle(snippetObject.getString("title"));
                        JSONObject thumbnailObject = snippetObject.getJSONObject("thumbnails");
                        JSONObject defaultObject = thumbnailObject.getJSONObject("default");
                        aVideo.setThumbnailURL(defaultObject.getString("url"));



                        video.add(aVideo);


                        Log.e("urlobject", "" + i);

                    }
                //      adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally
                {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    adapter.notifyDataSetChanged();

                }

            }
        }
    }
        public String GET(String url){
            InputStream inputStream = null;

            String result = "";
            try {

                URL url1=new URL(url);
                HttpURLConnection connection= (HttpURLConnection)url1.openConnection();
                connection.connect();
                int code=connection.getResponseCode();
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

              if(code==200)
              {     Log.e("code","" + code);
                  inputStream = connection.getInputStream();

                  // convert inputstream to string
                  if (inputStream != null)
                      result = convertInputStreamToString(inputStream);
                  else
                      result = "Did not work!";

                  Log.e("Result", "" + result);

              }
                else
              {

                  try {
                      AlertDialog alertDialog = new AlertDialog.Builder(SearchScreen.this).create();

                      alertDialog.setTitle("Info");
                      alertDialog.setMessage("Something went wrong. Please check your internet connection");
                      alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                      alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int which) {
                              finish();
                              Intent i= new Intent(SearchScreen.this,MainScreen.class);
                              startActivity(i);

                                                       }
                      });

                      alertDialog.show();
                  }
                  catch(Exception e)
                  {
                      Log.d("ALERT DIALOG ERROR", "Show Dialog: "+e.getMessage());
                  }

              }

            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (SocketTimeoutException e) {

            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return result;
        }


        private static String convertInputStreamToString(InputStream inputStream) throws IOException{
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
            String line = "";
            String result = "";
            while((line = bufferedReader.readLine()) != null)
                result += line;

            inputStream.close();
            return result;

        }


    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

   public void searchurl(View view)
    {  searchurl=search.getText().toString();
        search.setText(searchwords);
        search.setError(null);
        search.setHint("Paste Youtube Video URL");

    }
    public void back_click(View view)
    {  /*Intent i= new Intent (SearchScreen.this, MainScreen.class);
        startActivity(i);*/
        finish();
    }

    public void searchwords(View view)
    {   searchwords=search.getText().toString();
        search.setText(searchurl);
        search.setError(null);
        search.setHint("Enter a Keyword");
    }

  /*  public void horizontalDropDownIconMenu(View view) {
        if (androidDropDownMenuIconItem.getVisibility() == View.VISIBLE) {
            androidDropDownMenuIconItem.setVisibility(View.INVISIBLE);
            androidDropDownMenuIconItem.startAnimation(animationslideup);
            menuicon1.setImageResource(R.drawable.menu);
        } else {
            androidDropDownMenuIconItem.setVisibility(View.VISIBLE);
            androidDropDownMenuIconItem.startAnimation(animationslidedown);
            menuicon1.setImageResource(R.drawable.menu_1);
        }
    }*/

//    public void menuItemClick(View view) {
//        androidDropDownMenuIconItem.setVisibility(View.INVISIBLE);
//    }
/*
    public void shareIt(View view)
    {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "URL path will be paste here");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }


    public void settingsIt(View view)
    {
        Intent sendIntent = new Intent(SearchScreen.this,Settings.class);
        startActivity(sendIntent);
    }

    public void downloadIt(View view)
    {
        Intent sendIntent = new Intent(SearchScreen.this,DownloadScreen.class);
        startActivity(sendIntent);
    }*/

    public void onItemClick(int mPosition) {
//        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        Intent i = new Intent(SearchScreen.this, PlayerActivity.class);
        i.putExtra("VIDEO_ID", video.get(mPosition).getId());
        i.putExtra("TITLE",video.get(mPosition).getTitle());
        Log.e("ID", "" + video.get(mPosition).getId().toString());
        startActivity(i);
    }

  /*  @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
*/
    @Override
    protected void onResume() {
        super.onResume();
        MainScreen.activity1=this;
     //   androidDropDownMenuIconItem.setVisibility(View.INVISIBLE);
      //  menuicon1.setImageResource(R.drawable.menu);
    }


}
