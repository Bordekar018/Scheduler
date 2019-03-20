package com.example.amey.scheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperTimetableTeacher extends SQLiteOpenHelper {
    public DatabaseHelperTimetableTeacher(Context context) {
        super(context, "Teacherschedule.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table ttt(spin text primary key,date text,subject text,time text,batch text,branch text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists ttt");
    }
    public boolean insert(String spin, String date,String subject,String time,String batch,String branch) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("spin", spin);
        contentValues.put("date",date);
        contentValues.put("subject",subject);
        contentValues.put("time", time);
        contentValues.put("batch",batch);
        contentValues.put("branch",branch);
        long ins = db.insert("ttt", null, contentValues);
        if (ins == -1) return false;
        else return true;
    }

    public Boolean chkday(String spin) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from ttt where spin=?", new String[]{spin});
        if (cursor.getCount() > 0) return false;
        else return true;
    }


    public boolean updateData(String spin, String date,String subject,String time,String batch,String branch) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("spin", spin);
        contentValues.put("date",date);
        contentValues.put("subject",subject);
        contentValues.put("time", time);
        contentValues.put("batch",batch);
        contentValues.put("branch",branch);
        db.update("ttt", contentValues, "spin=?", new String[]{spin});
        return true;
    }

    public Boolean chkdayupdate(String spin) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from ttt where spin=?", new String[]{spin});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from ttt", null);
        return res;
    }

    public Integer deleteData (String spin) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("ttt", "spin= ?", new String[]{spin});
    }
}


