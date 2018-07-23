package com.mood.gnimadi.alexandra.moodtracker.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

import java.util.Date;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="History";
    private static final int DATABASE_VERSION=1;


    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /**Creating the column for the database*/
        String strSql ="create table T_History ("
                + "idHistory integer primary key autoincrement"
                + "numberHistory integer not null"
                + "textComment text not null"
                + "Mood text not null"
                + "timeOf integer not null"
                +")";

        sqLiteDatabase.execSQL(strSql);
        Log.i("DATABASE","onCreate invoked");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String strSql="upgrade table T_History";
        sqLiteDatabase.execSQL(strSql);
        Log.i("DATABASE","onUpgrade invoked");
    }

    public void insertComment(String stringTextComment){
        stringTextComment = stringTextComment.replace("'","''");
        String strSql="insert into T_History (textComment, timeOf) values ('"
                + stringTextComment+"',"+new Date().getTime()+")";
        this.getWritableDatabase().execSQL(strSql);
        Log.i("DATABASE","insertComment invoked");
    }

    public void insertMood(String MoodText){
        String strSql="insert into T_History (Mood, timeOf) values ('"
                + MoodText+"',"+new Date().getTime()+")";
        this.getWritableDatabase().execSQL(strSql);
        Log.i("DATABASE","insertMood invoked");
    }
}
