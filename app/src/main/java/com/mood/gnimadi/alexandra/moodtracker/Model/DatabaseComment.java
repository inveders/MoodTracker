package com.mood.gnimadi.alexandra.moodtracker.Model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DatabaseComment extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="Comment";
    private static final String DATABASE_ID="idComment";
    private static final String DATABASE_COMMENT="textComment";
    private static final String DATABASE_MOOD="gesture";
    private static final String DATABASE_DATE="dayOfMood";
    private static final int DATABASE_VERSION=39;

    //Creating the columns for the database of Comments
    private static final String DATABASE_TABLE_CREATE =
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String strSql ="drop table Comment";
        db.execSQL(strSql);
        this.onCreate(db);

    }

    private void insertTable(int gesture,int dayNumber){

        String strSql="insert into Comment (textComment, gesture, dayOfMood) values ('"
                +gesture+","+ dayNumber+")";
        this.getWritableDatabase().execSQL(strSql);
    }

    private void insertComment(String stringTextComment){

        String strSql="insert into Comment (textComment) values ('"+stringTextComment+"')";
        this.getWritableDatabase().execSQL(strSql);
    }

    private void updateMood(int gesture){

        List<MoodAndComment> dayTable ;
        dayTable = manipulateTable();
        int lastValue = dayTable.size();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("gesture",gesture);
        db.update("Comment", contentValues, "idComment = ?",new String[] {String.valueOf(lastValue)});
    }

   private void updateComment(String stringTextComment) {
        List<MoodAndComment> dayTable ;
        dayTable = manipulateTable();
        int lastValue = dayTable.size();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("textComment",stringTextComment);
        db.update("Comment", contentValues, "idComment = ?",new String[] {String.valueOf(lastValue)});
    }

    public List<MoodAndComment> manipulateTable(){

        List<MoodAndComment> moodAndCommentList = new ArrayList<>();


        //Put values of the table in a ArrayList to manipulate them

        Cursor cursor = this.getReadableDatabase().query("Comment", new String[] {"idComment","textComment","gesture","dayOfMood"},null,null,null,null,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {

            MoodAndComment moodMe = new MoodAndComment(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3));
            moodAndCommentList.add(moodMe);

            cursor.moveToNext();
        }
        cursor.close();

        return moodAndCommentList;

    }

    private static boolean isNullOrBlank(String s)
    {
        return (s==null || s.trim().equals(""));
    }

    //Put the mood in the SQLite database
    public void MoodStatusInsertion(int gesture,int dayOfMood){

        final Calendar now = GregorianCalendar.getInstance();
        final int dayNumber = now.get(Calendar.DAY_OF_MONTH);

        List<MoodAndComment> dayTable ;
        dayTable = manipulateTable();

        Cursor cursor;
        cursor = this.getReadableDatabase().query("Comment", new String[] {"idComment","textComment","gesture","dayOfMood"},null,null,null,null,null);

        //Define the line where we made modifications
        int lastValue = dayTable.size();
        int indexLastValue = lastValue-1;

        if(!cursor.moveToFirst()){
            insertTable(gesture,dayOfMood);
        }
        else {

            if(dayTable.get(indexLastValue).getDayOfMood() == dayNumber) {
                updateMood(gesture);
            }
            else if (dayTable.get(indexLastValue).getDayOfMood()== dayNumber-1) {
                insertTable(gesture,dayNumber);
            }
        }
    }

    public void CommentStatusInsertion (String stringTextComment, int gesture, int dayOfMood){
        List<MoodAndComment> dayTable ;
        dayTable = manipulateTable();

        @SuppressLint("Recycle") Cursor cursor = this.getReadableDatabase().query("Comment", new String[] {"idComment","textComment","gesture","dayOfMood"},null,null,null,null,null);

        //Define the line where we made modifications
        int lastValue = dayTable.size();
        int indexLastValue = lastValue-1;


        if(!cursor.moveToFirst()){
            insertTable(gesture,dayOfMood);
        }
        else {
            if(dayTable.get(indexLastValue).getTextComment() == null) {
                insertComment(stringTextComment);
            }
            else if (dayTable.get(indexLastValue).getTextComment() != null){
                updateComment(stringTextComment);
            }
        }



    }

    public void showComment (EditText mComment){

        List<MoodAndComment> dayTable ;
        dayTable = manipulateTable();

        //Define the line where we made modifications
        int lastValue = dayTable.size();
        int indexLastValue = lastValue-1;

        if(isNullOrBlank(dayTable.get(indexLastValue).getTextComment())){
            mComment.setText("");
        }
        else if (dayTable.get(indexLastValue).getTextComment().equals("null"))
            mComment.setText("");
        else if (!isNullOrBlank(dayTable.get(indexLastValue).getTextComment())){
            mComment.setText(dayTable.get(indexLastValue).getTextComment());
        }

    }

}