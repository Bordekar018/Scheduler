package com.example.amey.scheduler;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelperTimetable extends SQLiteOpenHelper {
    public DatabaseHelperTimetable(Context context) {
        super(context, "Studentschedule.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table stt(spin text primary key,date text,subject text,time text,professor text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists stt");

    }


    public boolean insert(String spin, String date,String subject,String time,String professor) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("spin", spin);
        contentValues.put("date",date);
        contentValues.put("subject",subject);
        contentValues.put("time", time);
        contentValues.put("professor",professor);
        long ins = db.insert("stt", null, contentValues);
        if (ins == -1) return false;
        else return true;
    }

    public Boolean chkday(String spin) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from stt where spin=?", new String[]{spin});
        if (cursor.getCount() > 0) return false;
        else return true;
    }


    public boolean updateData(String spin, String date,String subject,String time,String professor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("spin", spin);
        contentValues.put("date",date);
        contentValues.put("subject",subject);
        contentValues.put("time", time);
        contentValues.put("professor",professor);
        db.update("stt", contentValues, "spin=?", new String[]{spin});
        return true;
    }

    public Boolean chkdayupdate(String spin) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from stt where spin=?", new String[]{spin});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from stt", null);
        return res;
    }

    public Integer deleteData (String spin) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("stt", "spin= ?", new String[]{spin});
    }
}
