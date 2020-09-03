package com.example.saman.youtubedownloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * Created by saman on 3/29/2016.
 */
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        View popupView = GlobalDownload.layoutInflater2.inflate(R.layout.popup_window, null);
//        final PopupWindow popupWindow = new PopupWindow(
//                popupView,
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.WRAP_CONTENT);
//
//        ImageView btnDismiss = (ImageView) popupView.findViewById(R.id.imageView3);
//        ImageView btnDismiss2 = (ImageView) popupView.findViewById(R.id.imageView4);
//        ImageView btnDismiss3 = (ImageView) popupView.findViewById(R.id.imageView5);
//        btnDismiss.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                popupWindow.dismiss();
//            }
//        });
//
//        btnDismiss2.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                popupWindow.dismiss();
//            }
//        });
//
//        btnDismiss3.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                //   File downloaddirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), "Youtube Videos");
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                Uri uri = Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getPath()
//                        + "/Youtube Videos/");
//                intent.setData(uri);
//                GlobalDownload.context.startActivity(Intent.createChooser(DownloadScreen.intent, "Open folder"));
//
//            }
//        });
//
//        popupWindow.showAtLocation(DownloadScreen.view1, Gravity.CENTER, 0, 0);

    }
}