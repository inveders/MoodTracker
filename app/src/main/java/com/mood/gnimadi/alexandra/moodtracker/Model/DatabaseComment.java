package com.mood.gnimadi.alexandra.moodtracker.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseComment extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Comment";
    public static final String DATABASE_ID="idComment";
    public static final String DATABASE_COMMENT="textComment";
    public static final String DATABASE_MOOD="gesture";
    public static final String DATABASE_DATE="dayOfComment";
    private static final int DATABASE_VERSION=6;

    /**Creating the columns for the database of Comments*/
   public static final String DATABASE_TABLE_CREATE =
           "CREATE TABLE "+DATABASE_NAME+"("+
                   DATABASE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                   DATABASE_COMMENT+" TEXT,"+
                   DATABASE_MOOD+" INTEGER,"+
                   DATABASE_DATE+" INTEGER);";

    public DatabaseComment(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_TABLE_CREATE);
        Log.d("DEBUG","onCreate invoked");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
        Log.d("DEBUG","onUpgrade invoked");
    }

    public void insertTable(String stringTextComment, int gesture){

        String strSql="insert into T_Comment (textComment, gesture, dayOfComment) values ('"
                +stringTextComment+"',"+gesture +","+new Date().getTime()+")";
        this.getWritableDatabase().execSQL(strSql);
        Log.d("DEBUG","insertTable invoked");
    }

    public void updateTable(String stringTextComment, int gesture){

        String strSql="update T_Comment SET gesture=3 WHERE id=2 ";
        this.getWritableDatabase().execSQL(strSql);
        Log.d("DEBUG","insertTable invoked");
    }


    public List<MoodAndComment> manipulateTable(){
        Log.d("DEBUG","Manipulated Table invoked");
        List<MoodAndComment> moodAndCommentList = new ArrayList<>();

        /**Put values of the table in a ArrayList to manipulate them*/
        Cursor cursor = this.getReadableDatabase().query("T_Comment", new String[] {"idComment","textComment","gesture","dayOfComment"},null,null,null,"dayOfComment desc","7");
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {

            MoodAndComment moodMe = new MoodAndComment(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3));
            moodAndCommentList.add(moodMe);



        }

        cursor.close();
        return moodAndCommentList;
    }






}
