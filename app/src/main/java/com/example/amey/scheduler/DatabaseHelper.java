package com.example.amey.scheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(email text primary key,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");

    }


    public boolean insert(String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = db.insert("user", null, contentValues);
        if (ins == -1) return false;
        else return true;
    }

    public Boolean chkemail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email});
        if (cursor.getCount() > 0) return false;
        else return true;
    }


    public boolean updateData(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("email", email);

        contentValues.put("password", password);

        db.update("user", contentValues, "email=?", new String[]{email});
        return true;
    }

    public Boolean chkpass(String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where password=?", new String[]{password});
        if (cursor.getCount() > 0) return false;
        else return true;
    }


    public Integer deleteData (String email) {
      SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("user", "email= ?", new String[]{email});
   }


   public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
       Cursor res = db.rawQuery("select * from user", null);
   return res;
   }

   public Boolean chkemailpass(String email, String password){
       SQLiteDatabase db = this.getReadableDatabase();
       Cursor cursor = db.rawQuery("Select * from user where email=? and password=?", new String[]{email,password});
       if (cursor.getCount() > 0) return true;
       else return false;
   }

}