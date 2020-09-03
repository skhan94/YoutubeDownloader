package com.example.saman.youtubedownloader;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

/**
 * Created by saman on 3/8/2016.
 */
public class CustomBaseAdapter extends BaseAdapter implements OnClickListener {

    private LayoutInflater inflater;
    private ArrayList list;
  int i=0;
    VideoItem tempValues=null;

    private Activity activity;

    public CustomBaseAdapter(Activity a, ArrayList arrayList){


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



    static class mHolder{
        TextView hTextView;
       // TextView hTextView2;
        ImageView hImageView;
    }

    public mHolder holder;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if ( convertView == null ){
            convertView = inflater.inflate(R.layout.list_row,parent,false);

            holder = new mHolder();
            holder.hTextView = (TextView) convertView.findViewById(R.id.video_title);
           // holder.hTextView2 = (TextView) convertView.findViewById(R.id.video_description);
           // holder.hTextView3 = (TextView) convertView.findViewById(R.id.row_textView3);
            holder.hImageView = (ImageView)convertView.findViewById(R.id.video_thumbnail);

            convertView.setTag(holder);
        } else {
            holder = (mHolder) convertView.getTag();
        }
        tempValues=null;
        tempValues = (VideoItem)list.get(position);

        holder.hTextView.setText((CharSequence) tempValues.getTitle());
         Glide.with(activity.getApplicationContext()).load(tempValues.getThumbnailURL()).into(holder.hImageView);
        // holder.hTextView2.setText((CharSequence) tempValues.getDescription());
        //holder.hTextView3.setText(tempValues.getDetails() );

     //   holder.hImageView.setImageResource(Integer.parseInt(String.valueOf(tempValues.getDescription())));
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



            SearchScreen sct = (SearchScreen)activity;

            /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/

            sct.onItemClick(mPosition);
        }
    }
}