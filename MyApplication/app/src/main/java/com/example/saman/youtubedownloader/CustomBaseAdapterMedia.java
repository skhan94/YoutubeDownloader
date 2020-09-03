package com.example.saman.youtubedownloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by saman on 3/24/2016.
 */
public class CustomBaseAdapterMedia extends BaseAdapter implements View.OnClickListener {

    private LayoutInflater inflater;
    private ArrayList list;
    int i=0;
    private  static int mlastcheck = -1;
    long mLastClickedPosition=-1;
    MediaSize tempValues=null;

    private Activity activity;

    public CustomBaseAdapterMedia(Activity a, ArrayList arrayList){


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
            convertView = inflater.inflate(R.layout.media_item,parent,false);

            holder = new mHolder();
        //    Typeface typeFace3 = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf");

            holder.hTextView = (TextView) convertView.findViewById(R.id.textView1);
          //  holder.hTextView.setTypeface(typeFace3);
            // holder.hTextView2 = (TextView) convertView.findViewById(R.id.video_description);
            // holder.hTextView3 = (TextView) convertView.findViewById(R.id.row_textView3);
           // holder.hImageView = (ImageView)convertView.findViewById(R.id.imageView2);

            convertView.setTag(holder);
        } else {
            holder = (mHolder) convertView.getTag();
        }
        tempValues=null;
        tempValues = (MediaSize)list.get(position);

        holder.hTextView.setText(tempValues.getQuality() + " " + tempValues.getType() + " " + tempValues.getSize());
//        holder.hImageView.setImageResource(R.drawable.unckeck);
       convertView.setTag(holder);
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


        OnItemClickListener(final int position){
            mPosition = position;

        }

        @Override
        public void onClick(View view) {


//            mLastClickedPosition = mPosition;
//
//
//            // next, update mLastClickedPosition
//            // find the image in your view and update it
//           if(mPosition==mLastClickedPosition){
//        //      ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
//          //      imageView.setImageResource(R.drawable.check);
//                Log.e("Testing", "mPosition==mLastClickedPosition " + mPosition);
//            }
//            else
//            if(mPosition!=mLastClickedPosition)
//            {
//               ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
//                imageView.setImageResource(R.drawable.unckeck);
//                Log.e("Testing", "mPosition!=mLastClickedPosition " + mPosition);
//
//            }





            PlayerActivity sct = (PlayerActivity)activity;

            /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/

           sct.onItemClick(mPosition);
        }

    }
}
