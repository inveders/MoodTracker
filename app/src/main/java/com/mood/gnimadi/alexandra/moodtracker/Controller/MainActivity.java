package com.mood.gnimadi.alexandra.moodtracker.Controller;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mood.gnimadi.alexandra.moodtracker.Model.DatabaseComment;
import com.mood.gnimadi.alexandra.moodtracker.Model.MoodAndComment;
import com.mood.gnimadi.alexandra.moodtracker.R;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class MainActivity extends AppCompatActivity implements android.view.GestureDetector.OnGestureListener {

    private GestureDetector mGestureDetector;
    public int gesture=3;
    private DatabaseComment databaseComment;
    public String stringTextComment ;

    //The tab containing values of drawables
    int[] mDraw = {
            R.drawable.smiley_sad,
            R.drawable.smiley_disappointed,
            R.drawable.smiley_normal,
            R.drawable.smiley_happy,
            R.drawable.smiley_super_happy};

    //Colors for the background of drawables
    String[] mColor={"#ffde3c50","#ff9b9b9b","#a5468ad9","#ffb8e986","#fff9ec4f"};

    ImageView ImageSwipe;
    RelativeLayout Li;

    //To have the date of today (for example 30 or 31 etc)
    final Calendar now = GregorianCalendar.getInstance();
    final int dayNumber = now.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Link with the database to make actions
        databaseComment = new DatabaseComment(this);
        List<MoodAndComment> dayTable ;
        dayTable = databaseComment.manipulateTable();
        int lastValue = dayTable.size();
        int indexLastValue = lastValue-1;
        int j = dayTable.get(indexLastValue).getGesture();

        //Configuration for launching image
       ImageSwipe = findViewById(R.id.draw);
       Li= findViewById(R.id.relativelayout);
        ImageSwipe.setImageResource(mDraw[0]);
        if (j==0) {
            ImageSwipe.setImageResource(mDraw[0]);
            Li.setBackgroundColor(Color.parseColor(mColor[0]));
        }

        else if (j==1) {
            ImageSwipe.setImageResource(mDraw[1]);
            Li.setBackgroundColor(Color.parseColor(mColor[1]));
        }
        else if (j==2) {
            ImageSwipe.setImageResource(mDraw[2]);
            Li.setBackgroundColor(Color.parseColor(mColor[2]));
        }
        else if (j==4) {
            ImageSwipe.setImageResource(mDraw[4]);
            Li.setBackgroundColor(Color.parseColor(mColor[4]));
        }
        else if (dayTable.get(indexLastValue).getDayOfMood()== dayNumber-1){
            ImageSwipe.setImageResource(mDraw[3]);
            Li.setBackgroundColor(Color.parseColor(mColor[3]));
        }
        else {
            ImageSwipe.setImageResource(mDraw[3]);
            Li.setBackgroundColor(Color.parseColor(mColor[3]));
        }

        //Configuration for swipe
       mGestureDetector = new GestureDetector(this, this);

        //Configuration for comment
        ImageButton mBtnComment = findViewById(R.id.btnComment);

        mBtnComment.setOnClickListener(new View.OnClickListener(){

            @SuppressLint("InflateParams")
            @Override
            public void onClick(View view){
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView;
                mView = getLayoutInflater().inflate(R.layout.comment, null);
                final EditText mTextComment = mView.findViewById(R.id.textComment);


                Button mValidComment = mView.findViewById(R.id.validComment);
                Button mCancelComment = mView.findViewById(R.id.cancelComment);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();

                dialog.show();
                databaseComment.showComment(mTextComment);

                mValidComment.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public  void onClick(View view){
                        final Calendar now = GregorianCalendar.getInstance();
                        final int dayNumber = now.get(Calendar.DAY_OF_MONTH);
                        stringTextComment = mTextComment.getText().toString();
                        databaseComment.CommentStatusInsertion(stringTextComment,gesture,dayNumber);
                        databaseComment.close();
                        dialog.cancel();

                    }
                });

                mCancelComment.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public  void onClick(View view){
                        dialog.cancel();
                    }
                });
            }
        });

    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {

        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {

        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    //Method onFling is use to detect the vertical swipe and choose the good action (gesture++ or gesture--)
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        if (e1.getY() < e2.getY()) {
            gesture--;
            Image_Change();
        }

        if (e1.getY() > e2.getY()) {
            gesture++;
            Image_Change();
        }

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return mGestureDetector.onTouchEvent(event);

    }

    /*Method to call a modification of the image
     * This method is placed into the gestures detector methods*/
    public void Image_Change() {

        if (gesture<5 && gesture >=0) ParametersImage();

        else if (gesture >=5) {
            gesture = 0;

            ParametersImage();

        }
    }

    //Method to change an image (background too) and add the mood in the SQLite database. Go too in the song method to add song when we swipe
    public void ParametersImage(){
        final Calendar now = GregorianCalendar.getInstance();
        final int dayNumber = now.get(Calendar.DAY_OF_MONTH);
        ImageSwipe.setImageResource(mDraw[gesture]);
        Li.setBackgroundColor(Color.parseColor(mColor[gesture]));
        databaseComment.MoodStatusInsertion(gesture,dayNumber);
        song();
    }

    //To open the History Activity
    public void LogoHistory (View view) {
        //To pass from MainActivity to HistoryActivity
        startActivity(new Intent(this, HistoryActivity.class));
    }

    //To configure songs when we swipe
    public void song(){

        if(gesture==0) {
            MediaPlayer beep16 = MediaPlayer.create(MainActivity.this, R.raw.beep16);
            beep16.start();
        }
        else if (gesture==1) {
            MediaPlayer beep17 = MediaPlayer.create(MainActivity.this, R.raw.beep17);
            beep17.start();
        }

        else if (gesture==2) {
            MediaPlayer beep18 = MediaPlayer.create(MainActivity.this, R.raw.beep18);
            beep18.start();
        }

        else if(gesture==3) {
            MediaPlayer beep19 = MediaPlayer.create(MainActivity.this, R.raw.beep19);
            beep19.start();
        }

        else if(gesture==4) {
            MediaPlayer beep20 = MediaPlayer.create(MainActivity.this, R.raw.beep20);
            beep20.start();
        }

    }

}

