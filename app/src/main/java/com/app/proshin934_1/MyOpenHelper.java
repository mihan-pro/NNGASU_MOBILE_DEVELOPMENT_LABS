package com.app.proshin934_1;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {

    public String TABLE_NAME="first_table";
    public String FIELD_NAME_1="first_field";
    public String FIELD_NAME_2="second_field";

    MyOpenHelper(Context ct, String nm, SQLiteDatabase.CursorFactory cf, int vs){
        super(ct, nm, cf, vs);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        String query="create table " + TABLE_NAME + " ( _id integer primary key autoincrement, " + FIELD_NAME_1 + " TEXT, " + FIELD_NAME_2 + " TEXT)";
        DB.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

