package com.example.saman.youtubedownloader;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class PlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
     private static int i=0;
    ListView listView;
    private YouTubePlayerView playerView;
    public static final String KEY="AIzaSyAlNc59CIDo-yisGQ8Q0pOuOvqIWX4M5rA";
    Button button;
    Animation animationslidedown;
    Animation animationslideup;
    LinearLayout androidDropDownMenuIconItem;
    LinearLayout mLinearLayout;
    private ProgressDialog dialog;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    String text = "";
    RadioButton r1;
    RadioButton r2;
    RadioButton r3;
    Button download;
    ArrayList<MediaSize> media = new ArrayList<MediaSize>();
    CustomBaseAdapterMedia madapter;
    public PlayerActivity  CustomListView = null;
    RadioGroup rg;
    RadioButton[] rb;
    public static String name;
    public static String url;
    public static String size;
    public static String type;
    public static String video_id;
    public static String f_type;
    public static String status;
    public static String fileID;
    MediaSize mSize;
  //  public static SQLiteDatabase newDB1;
  //  public static DatabaseHelper mydb1;

    static final int REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        CustomListView=this;
        Log.e("Player Activity", "" + i);
        dialog = new ProgressDialog(PlayerActivity.this);
        playerView = (YouTubePlayerView) findViewById(R.id.player_view);
        playerView.initialize(KEY, this);
        listView = (ListView) findViewById(R.id.listView2);
        textView1=(TextView)findViewById(R.id.videoname);
        Typeface typeFace3=Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Semibold.ttf");
        textView1.setTypeface(typeFace3);
        mLinearLayout = (LinearLayout) findViewById(R.id.qualityvideo);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
         RadioButton[] rb = new RadioButton[10];
          RadioGroup rg = new RadioGroup(PlayerActivity.this);
          textView1.setText("");
       MainScreen.activity1=this;
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

       status="DOWNLOADING";

        //  download=(Button)findViewById(R.id.download);
     // download.setEnabled(false);



         //  textView1.setText(getIntent().getStringExtra("TITLE"));

        video_id=getIntent().getStringExtra("VIDEO_ID");
        new HttpAsyncTask().execute("http://mobile.fsdcloud.com/api/you_tube_downloader/getvideo.php?videoid=" + video_id.toString() );
        madapter = new CustomBaseAdapterMedia(PlayerActivity.this, media);
        listView.setAdapter(madapter);
    //    Log.e("ADAPTER", " " + madapter);

//        if(madapter==null)
//        {
//            try {
//                AlertDialog alertDialog = new AlertDialog.Builder(PlayerActivity.this).create();
//
//                alertDialog.setTitle("Info");
//                alertDialog.setMessage("Sorry, the video can't be downloaded.");
//                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
//                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                        Intent i= new Intent(PlayerActivity.this,MainScreen.class);
//                        startActivity(i);
//
//                    }
//                });
//
//                alertDialog.show();
//            }
//            catch(Exception e)
//            {
//                Log.d("ALERT DIALOG ERROR", "Show Dialog: "+e.getMessage());
//            }
//        }

//        download.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
////                 initDownloader();
////                Intent i = new Intent(PlayerActivity.this, DownloadScreen.class);
////                i.putExtra("FILE_NAME", name.toString());
////                i.putExtra("FILE_URL", url.toString());
////                i.putExtra("FILE_SIZE", size.toString());
////                i.putExtra("FILE_TYPE", type.toString());
////                Log.e("FILE_SIZE", "" + type.toString());
////                startActivity(i);
//
////                Intent i = new Intent(PlayerActivity.this, Download.class);
////                startActivity(i);
//            }
//        });



    }

    public void onItemClick(int mPosition) {
     //   String index= listView.getItem(mPosition);
          //  download.setEnabled(true);
        String str=media.get(mPosition).getSize().toString();
        str=str.replace(" ","");
        if(str.length()!=2) {
            name = textView1.getText().toString();
            Log.e("NAME", " " + name);
            url = media.get(mPosition).getUrl().toString();
            Log.e("URL", " " + url);
            size = media.get(mPosition).getSize().toString();
            Log.e("SIZE", " " + size);
            type = media.get(mPosition).getType().toString();
            Log.e("TYPE", " " + type);
            Intent i = new Intent(PlayerActivity.this, DownloadScreen.class);
            i.putExtra("FILE_NAME", name.toString());
            i.putExtra("FILE_URL", url.toString());
            i.putExtra("FILE_SIZE", size.toString());
            i.putExtra("FILE_TYPE", type.toString());
            Log.e("FILE_SIZE", "" + type.toString());

            if (type.equals("video/mp4")) {
                f_type = "mp4";
            } else if (type.equals("video/webm")) {
                f_type = "webm";
            } else if (type.equals("video/x-flv")) {
                f_type = "flv";
            } else if (type.equals("video/3gpp")) {
                f_type = "3gpp";
            }
            Log.e("DATABASE", "ID " + video_id);
            Log.e("DATABASE", "Name " + name);
            Log.e("DATABASE", "Size " + size);
            Log.e("DATABASE", "Type " + f_type);
            Log.e("DATABASE", "Status " + status);
            Log.e("DATABASE", "URL " + url);
            //   Log.e("")
            MainScreen.mydb.onInsert(video_id, name, size, f_type, status);
            //   fileID= MainScreen.newDB.execSQL("Select MAX(ID)  from" + DatabaseHelper.TABLE_NAME);
            Cursor cid = MainScreen.newDB.rawQuery("select MAX(ID) from " + DatabaseHelper.TABLE_NAME, null);


            Log.e("DATABASE", " FILE ID" + 00);

            cid.moveToFirst();
            Log.e("DATABASE", "Cursor is not null");
            while (cid.isAfterLast() == false) {
                fileID = cid.getString(cid.getColumnIndex("MAX(ID)"));
                Log.e("DATABASE", " FILE ID" + fileID);

                cid.moveToNext();
            }
            cid.close();


            i.putExtra("DATABASE_ID", fileID);
            startActivity(i);


            Log.e("FILE_SIZE", " " + media.get(mPosition).getType().toString());

        }
        else
        {

            try {
                AlertDialog alertDialog = new AlertDialog.Builder(PlayerActivity.this).create();

                alertDialog.setTitle("Info");
                alertDialog.setMessage("Sorry, the video can't be downloaded.");
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //     finish();
//                                        Intent i= new Intent(PlayerActivity.this,MainScreen.class);
//                                        startActivity(i);

                    }
                });

                alertDialog.show();
            }
            catch(Exception e)
            {
                Log.d("ALERT DIALOG ERROR", "Show Dialog: "+e.getMessage());
            }


        }

    }

//    private void startActivityForResult(Intent i) {
//    }

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

                try
                {
                    int count=0;

                  JSONObject titleObject = new JSONObject(response);

                    Log.e("TITLE", " " + titleObject.getString("title"));

                    textView1.setText(titleObject.getString("title"));

                  //  Log.e("TITLE", " " + textView1.getText().toString());

                    JSONArray new_array = titleObject.getJSONArray("data");



                  //  Log.e("ARRAY LENGTH", "" + new_array.length());

                   // rg.setOrientation(RadioGroup.VERTICAL);
                    for( int i=0; i<new_array.length();i++)
                    {
                        mSize= new MediaSize();
                      //  JSONArray new_array = dataObject.getJSONArray(i);
                        JSONObject mediaObject = new_array.getJSONObject(i);
                        mSize.setQuality(mediaObject.getString("quality"));
                        mSize.setSize(mediaObject.getString("size"));
                        mSize.setType(mediaObject.getString("type"));
                        mSize.setUrl(mediaObject.getString("url"));

                       Log.e("MEDIA", "" + i);
                        String str=mSize.getSize().toString();
                        str=str.replace(" ","");
                       // if(str.length()!=2) {
                            Log.e("SIZE", mSize.getSize().toString());
                            media.add(mSize);
                            count = count + 1;
                       // }
                       Log.e("COUNT", String.valueOf(count));
                    }
                 /*   if(count==0)
                    {

                            try {
                                AlertDialog alertDialog = new AlertDialog.Builder(PlayerActivity.this).create();

                                alertDialog.setTitle("Info");
                                alertDialog.setMessage("Sorry, the video can't be downloaded.");
                                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                   //     finish();
//                                        Intent i= new Intent(PlayerActivity.this,MainScreen.class);
//                                        startActivity(i);

                                    }
                                });

                                alertDialog.show();
                            }
                            catch(Exception e)
                            {
                                Log.d("ALERT DIALOG ERROR", "Show Dialog: "+e.getMessage());
                            }

                    }
*/
                }
                catch (JSONException e) {
                  e.printStackTrace();
                }

                finally {

                    madapter.notifyDataSetChanged();
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
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
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int code=connection.getResponseCode();

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

                Toast.makeText(PlayerActivity.this, "Status Code is not 200",
                        Toast.LENGTH_LONG).show();
            }

        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (SocketTimeoutException e)
        {

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



    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b) {
            youTubePlayer.cueVideo(getIntent().getStringExtra("VIDEO_ID"));
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Initialization Failed", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
      //  androidDropDownMenuIconItem.setVisibility(View.INVISIBLE);
        MainScreen.activity1=this;
    }

}