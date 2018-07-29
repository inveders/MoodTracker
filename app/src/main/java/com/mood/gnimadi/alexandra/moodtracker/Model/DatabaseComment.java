package com.mood.gnimadi.alexandra.moodtracker.Model;

import android.app.AlarmManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mood.gnimadi.alexandra.moodtracker.Controller.MainActivity;
import com.mood.gnimadi.alexandra.moodtracker.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static android.app.AlarmManager.ELAPSED_REALTIME;

public class DatabaseComment extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Comment";
    public static final String DATABASE_ID="idComment";
    public static final String DATABASE_COMMENT="textComment";
    public static final String DATABASE_MOOD="gesture";
    public static final String DATABASE_DATE="dayOfMood";
    private static final int DATABASE_VERSION=38;
    private static Context context;

    /**Creating the columns for the database of Comments*/
   public static final String DATABASE_TABLE_CREATE =
           "CREATE TABLE "+DATABASE_NAME+"("+
                   DATABASE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                   DATABASE_COMMENT+" TEXT,"+
                   DATABASE_MOOD+" INTEGER,"+
                   DATABASE_DATE+" INTEGER);";

   /**Initialization of Time*/
   final Calendar now = GregorianCalendar.getInstance();
   final int dayNumber = now.get(Calendar.DAY_OF_MONTH);


    public DatabaseComment(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_TABLE_CREATE);
        Log.d("DABAGO","onCreate invoked");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String strSql ="drop table Comment";
        db.execSQL(strSql);
        this.onCreate(db);
        Log.d("DABAGO","onUpgrade invoked");
        //db.execSQL("INSERT INTO Comment values (null,3,26);");
        Log.d("DABAGO","bobobo");

    }


    public void insertTable(String stringTextComment, int gesture,int dayNumber){

        String strSql="insert into Comment (textComment, gesture, dayOfMood) values ('"
                +stringTextComment+"',"+gesture+","+ dayNumber+")";
        this.getWritableDatabase().execSQL(strSql);
        Log.d("DABAGO","insertTable invoked");
    }

    public void insertComment(String stringTextComment){

        String strSql="insert into Comment (textComment) values ('"+stringTextComment+"')";
        this.getWritableDatabase().execSQL(strSql);
        Log.d("DABAGO","insertComment invoked");
    }

    public void updateMood(int gesture,int indexLastValue){

        String strSql="UPDATE Comment SET gesture="+gesture+" WHERE idComment="+indexLastValue;
        //db.execSQL(strSql);
        this.getWritableDatabase().execSQL(strSql);
        Log.d("DABAGO","updateMood invoked");

    }

    public void updateComment(String stringTextComment,int indexLastValue){

        String strSql="UPDATE Comment SET textComment="+stringTextComment+" WHERE idComment="+indexLastValue;
        Log.d("DABAGO","on rentre dans updtae comment");
        //db.execSQL(strSql);
       // this.getWritableDatabase().execSQL(strSql);
        Log.d("DABAGO","updateComment invoked");

    }



    public List<MoodAndComment> manipulateTable(){

        List<MoodAndComment> moodAndCommentList = new ArrayList<>();


        /**Put values of the table in a ArrayList to manipulate them*/

        Cursor cursor = this.getReadableDatabase().query("Comment", new String[] {"idComment","textComment","gesture","dayOfMood"},null,null,null,null,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {

            MoodAndComment moodMe = new MoodAndComment(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3));
            moodAndCommentList.add(moodMe);

            cursor.moveToNext();
        }
        Log.d("DABAGO","Manipulated Table");
        cursor.close();

        return moodAndCommentList;

    }

    private static boolean isNullOrBlank(String s)
    {
        return (s==null || s.trim().equals(""));
    }

    /**Put the mood in the SQLite database*/
    public void MoodStatusInsertion(TextView mTextTest, int gesture,int dayOfMood){

       final Calendar now = GregorianCalendar.getInstance();
        final int dayNumber = now.get(Calendar.DAY_OF_MONTH);

        //insertTable(null, gesture,dayOfMood);
        Log.d("DABAGO", "MoodStatusInsertion");

        List<MoodAndComment> dayTable ;
        dayTable = manipulateTable();

        Cursor cursor = this.getReadableDatabase().query("Comment", new String[] {"idComment","textComment","gesture","dayOfMood"},null,null,null,null,null);

        /**Define the line where we made modifications*/
        int lastValue = dayTable.size();
        int indexLastValue = lastValue-1;
        mTextTest.setText(String.valueOf(dayNumber-1));

        if(!cursor.moveToFirst()){
            mTextTest.setText("C'est vide");
            insertTable(null, gesture,dayOfMood);
            Log.d("DABAGO", "FIRST INSERTION");
        }
        else {
            mTextTest.setText(String.valueOf(gesture));
            if(dayTable.get(indexLastValue).getDayOfMood() == dayNumber) {
                Log.d("DABAGO", "UPDATE SWIPE");
                updateMood(gesture,indexLastValue);
            }
            else if (dayTable.get(indexLastValue).getDayOfMood()== dayNumber-1) {
                Log.d("DABAGO", "INSERTION SWIPE");
                insertTable(null, gesture,dayNumber);
            }
        }


    }

    public void CommentStatusInsertion (String stringTextComment, int gesture, int dayOfMood){
        final Calendar now = GregorianCalendar.getInstance();
        final int dayNumber = now.get(Calendar.DAY_OF_MONTH);

        //insertTable(null, gesture,dayOfMood);
        Log.d("DABAGO", "MoodCommentInsertion");

        List<MoodAndComment> dayTable ;
        dayTable = manipulateTable();

        Cursor cursor = this.getReadableDatabase().query("Comment", new String[] {"idComment","textComment","gesture","dayOfMood"},null,null,null,null,null);

        /**Define the line where we made modifications*/
        int lastValue = dayTable.size();
        int indexLastValue = lastValue-1;


        if(!cursor.moveToFirst()){
            // mTextTest.setText("C'est vide");
            insertTable(null, gesture,dayOfMood);
            Log.d("DABAGO", "FIRST INSERTION COMMENT AND ALL");
        }
        else {
           // mTextTest.setText(String.valueOf(gesture));
            if(dayTable.get(indexLastValue).getTextComment() == null) {
                // mTextTest.setText(String.valueOf(gesture));
                Log.d("DABAGO", "INSERTION SWIPE");
                insertComment(stringTextComment);
            }
            else if (dayTable.get(indexLastValue).getTextComment() != null){
                Log.d("DABAGO", "UPDATE COMMENT");
                updateComment(stringTextComment,indexLastValue);
            }
        }



    }

    public EditText showComment (EditText mComment){

        List<MoodAndComment> dayTable ;
        dayTable = manipulateTable();


        /**Define the line where we made modifications*/
        int lastValue = dayTable.size();
        int indexLastValue = lastValue-1;

        if(isNullOrBlank(dayTable.get(indexLastValue).getTextComment())){
            // mTextTest.setText("C'est vide");
            mComment.setText("");
        }
        else if (dayTable.get(indexLastValue).getTextComment().equals("null"))
            mComment.setText("");
        else {
            //A ENLEVER ENSUITE
            mComment.setText(dayTable.get(indexLastValue).getTextComment());
        }


        return mComment;
    }

    public void addHistory (){

    }




}
