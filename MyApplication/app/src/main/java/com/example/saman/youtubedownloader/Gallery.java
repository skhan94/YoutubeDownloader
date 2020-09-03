package com.example.saman.youtubedownloader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by saman on 4/7/2016.
 */
public class Gallery extends Activity {
    GridView mygrid;
    ImageView imageView;
    private LayoutInflater inflater;
    ImageView back;

    ArrayList<File> list;

    @Override
    protected void onResume() {
        super.onResume();

        if (Settings.loading.isShowing()) {
            Settings.loading.dismiss();
        }

     //   Settings.spinner.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        if (Settings.loading.isShowing()) {
            Settings.loading.dismiss();
        }

      //  Settings.spinner.setVisibility(View.GONE);
        mygrid=(GridView)findViewById(R.id.gridView);
        back=(ImageView)findViewById(R.id.back1);
        Log.e("GALLERY", " " + 0);
        File file1= new File(Environment.getExternalStorageDirectory(),"YoutubeDownloaderVideos");
        Log.e("GALLERY", " " + file1.toString());
        list= imageReader(file1);
        mygrid.setAdapter(new galleryAdapter(this, list));
    }

    public class galleryAdapter extends BaseAdapter implements View.OnClickListener {

        LayoutInflater inflater;
        ArrayList list;
        int i=0;
      ArrayList<File> tempValues=null;

         Activity activity;

        public galleryAdapter(Activity a, ArrayList arrayList){


            this.activity = a;
            this.list = arrayList;
            inflater = ( LayoutInflater )activity.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

         class mHolder{

            ImageView hImageView;
        }

        public mHolder holder;

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if ( convertView == null ){
                convertView = inflater.inflate(R.layout.gallery_row,parent,false);

                holder = new mHolder();

                holder.hImageView = (ImageView)convertView.findViewById(R.id.imageView2);

                convertView.setTag(holder);
            } else {
                holder = (mHolder) convertView.getTag();
            }
         //   tempValues=null;
           // tempValues = (ArrayList<File>) list.get(position);
Log.e("GALLERY ADAPTER", " " + list.get(position).toString());
           Bitmap bm = ThumbnailUtils.createVideoThumbnail(list.get(position).toString(), MediaStore.Images.Thumbnails.MINI_KIND);
           Glide.with(activity.getApplicationContext()).load(list.get(position).toString()).into(holder.hImageView);
         //  holder.hImageView.setImageBitmap(bm);
             // holder.hImageView.setImageURI(Uri.parse(getItem(position).toString()));
            Log.e("GALLERY ADAPTER", " " + "holder1");
           // holder.hImageView.setImageUri();
//        convertView.setTag(holder);
            convertView.setOnClickListener(new OnItemClickListener(position));
            return convertView;
        }

        @Override
        public void onClick(View v) {
            Log.v("CustomAdapter", "=====Row button clicked=====");
        }

        /********* Called when Item click in ListView ************/
        private class OnItemClickListener  implements View.OnClickListener {
            private int mPosition;

            OnItemClickListener(int position){
                mPosition = position;
            }

            @Override
            public void onClick(View arg0) {


                Intent i = new Intent(Gallery.this, DownloadedVideoPlayer.class);
             //   i.putExtra("path", code);
                 i.putExtra("path", list.get(mPosition).toString());
                startActivity(i);
//                SearchScreen sct = (SearchScreen)activity;
//
//                /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/
//
//                sct.onItemClick(mPosition);
            }
        }
    }
    ArrayList<File> imageReader (File root)
    {
        ArrayList<File> a= new ArrayList<>();
        File[] files = root.listFiles();
        Log.e("GALLERY", "LENGTH " + files.length);
        for(int i=0; i<files.length; i++)
        {    // String filename= root + ".dthumb";
            File filename= new File (root.toString()+"/.dthumb");
              if(files[i]!=filename) {
                  Log.e("FILES ADDED ", "FILENAME " + filename);
                  a.add(files[i]);
                  Log.e("FILES ADDED ", "FILES " + files[i]);
              }
        }
      return a;
    }

    public void back_click(View view)
    { /* Intent i= new Intent (Gallery.this, Settings.class);
        startActivity(i);*/

        finish();
    }
}
