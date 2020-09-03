package com.example.saman.youtubedownloader;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Aksystem on 3/9/2016.
 */
public class CustomBaseAdapterDownload extends BaseAdapter  {

    private LayoutInflater inflater;
    private ArrayList list;
    private Activity activity;
    ListModelDownload tempValues=null;
    ArrayList<VideoItem> video = new ArrayList<VideoItem>();

    public CustomBaseAdapterDownload(Activity a, ArrayList arrayList){

        this.list = arrayList;
        this.activity = a;
        this.inflater = ( LayoutInflater )activity.
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
        TextView hTextView2;
        ImageView hImageView;
        ImageView hTextView3;
    }

    public mHolder holder;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if ( convertView == null ){
            convertView = inflater.inflate(R.layout.list_row1,parent,false);

            holder = new mHolder();
            holder.hTextView = (TextView) convertView.findViewById(R.id.row_textView1);
            holder.hTextView2 = (TextView) convertView.findViewById(R.id.row_textView2);
            holder.hImageView = (ImageView)convertView.findViewById(R.id.row_imageView1);
         //   holder.hTextView3 = (ImageView)convertView.findViewById(R.id.row_textView3);
            convertView.setTag(holder);

        } else {
            holder = (mHolder) convertView.getTag();
        }
        tempValues=null;
        tempValues = (ListModelDownload)list.get(position);
        holder.hTextView.setText(tempValues.getVideoName());
        holder.hTextView2.setText(tempValues.getSize());
        holder.hImageView.setImageResource(R.drawable.download_icon);
        holder.hTextView3.setImageResource(R.drawable.blue);
        return convertView;
    }

}
