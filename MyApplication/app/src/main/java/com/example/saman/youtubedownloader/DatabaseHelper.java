package com.example.saman.youtubedownloader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by saman on 4/5/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "download";
    public static final String TABLE_NAME = "downloadlist";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FILE_ID";
    public static final String COL_3 = "FILE_NAME";
    public static final String COL_4 = "FILE_SIZE";
    public static final String COL_5 = "FILE_TYPE";
    public static final String COL_6 = "FILE_STATUS";
    public static final String COL_7 = "STATE";

    public static final String SCRIPT = "create table " + TABLE_NAME + " ("
            + COL_1 + " integer primary key autoincrement, " + COL_2
            + " text not null, " + COL_3 + " text not null, "  + COL_4 + " text not null, " + COL_5 + " text not null, " + COL_6 + " text not null, " + COL_7 + " integer );";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.e("DATABASE", " " + SCRIPT);
   //     SQLiteDatabase db= this.getWritableDatabase();
        Log.e("DATABASE", "Database created " + 0);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("DATABASE", " " + 0);
         db.execSQL(SCRIPT);
        Log.e("DATABASE", "DATABASE CREATED SUCCESSFULLY " + 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    /*    db.execSQL("drop table " + TABLE_NAME);
        onCreate(db);*/
    }

    public boolean onInsert(String file_id, String file_name, String file_size, String file_type, String file_status) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e("DATABASE", "onInsert " + 6);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,file_id);
        contentValues.put(COL_3,file_name);
        contentValues.put(COL_4,file_size);
        contentValues.put(COL_5,file_type);
        contentValues.put(COL_6,file_status);
       // contentValues.put(COL_6,file_status);
     //   contentValues.put(COL_7,file_url);


        long result=db.insert(TABLE_NAME,null,contentValues);
        if (result==-1)
           return false;
        else
            return true;



    }

//    public Cursor getAllRows()
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor c = db.query()
//        Cursor c= db.rawQuery("select * from " + TABLE_NAME,null);
//        if(c!=null)
//        {
//            c.moveToFirst();
//
//        }
//        return c;
//    }
}
