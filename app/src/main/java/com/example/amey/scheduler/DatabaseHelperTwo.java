package com.example.amey.scheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperTwo extends SQLiteOpenHelper {
    public DatabaseHelperTwo(Context context) {
        super(context,"Check.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("Create table teacher(temail text primary key,tpassword text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          db.execSQL("drop table if exists teacher");
    }

    public boolean insert(String temail,String tpassword)
    {
       SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("temail",temail);
        contentValues.put("tpassword",tpassword);
        long ins=db.insert("teacher",null,contentValues);
        if(ins==-1) return false;
        else return true;
    }

    public  Boolean chktemail(String temail){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from teacher where temail=?",new String[]{temail});
        if (cursor.getCount()>0)return false;
        else return true;
    }
    public boolean updatetData(String temail, String tpassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("temail", temail);

        contentValues.put("tpassword", tpassword);

        db.update("teacher", contentValues, "temail=?", new String[]{temail});
        return true;
    }

    public Boolean chktpass(String tpassword) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from teacher where tpassword=?", new String[]{tpassword});
        if (cursor.getCount() > 0) return false;
        else return true;
    }
    public Integer deleteData(String temail) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("teacher", "temail= ?", new String[]{temail});
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from teacher", null);
        return res;
    }

    public Boolean chktemailpass(String temail, String tpassword){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from teacher where temail=? and tpassword=?", new String[]{temail,tpassword});
        if (cursor.getCount() > 0) return true;
        else return false;
    }
}
