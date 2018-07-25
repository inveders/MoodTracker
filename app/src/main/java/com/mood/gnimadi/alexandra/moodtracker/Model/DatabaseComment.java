package com.mood.gnimadi.alexandra.moodtracker.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

public class DatabaseComment extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="Comment";
    private static final int DATABASE_VERSION=4;


    public DatabaseComment(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /**Creating the columns for the database of Comment*/

        String strSql ="create table T_Comment ("
                + "idComment integer primary key autoincrement,"
                + "textComment text,"
                + "gesture integer,"
                + "timeOfComment integer"
                +")";
        Log.d("DEBUG","onCreate tried");
        sqLiteDatabase.execSQL(strSql);
        Log.d("DEBUG","onCreate invoked");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String strSql="drop table T_Comment";
        sqLiteDatabase.execSQL(strSql);
        this.onCreate(sqLiteDatabase);
        Log.d("DEBUG","onUpgrade invoked");
    }

    public void insertTable(String stringTextComment, int gesture){

        String strSql="insert into T_Comment (textComment, gesture, timeOfComment) values ('"
                +stringTextComment+"',"+gesture +","+new Date().getTime()+")";
        this.getWritableDatabase().execSQL(strSql);
        Log.d("DEBUG","insertTable invoked");
    }

    public void updateTable(String stringTextComment, int gesture){

        String strSql="update T_Comment SET gesture=3 WHERE id=2 ";
        this.getWritableDatabase().execSQL(strSql);
        Log.d("DEBUG","insertTable invoked");
    }
}
