package com.mood.gnimadi.alexandra.moodtracker.Controller;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mood.gnimadi.alexandra.moodtracker.Model.DatabaseComment;
import com.mood.gnimadi.alexandra.moodtracker.R;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class MainActivity extends AppCompatActivity implements android.view.GestureDetector.OnGestureListener {

    private GestureDetector mGestureDetector;
    public int gesture=3;
    private DatabaseComment databaseComment;
    public TextView mTextTest;

    int[] mDraw = {
            R.drawable.smiley_sad,
            R.drawable.smiley_disappointed,
            R.drawable.smiley_normal,
            R.drawable.smiley_happy,
            R.drawable.smiley_super_happy};
    String[] mColor={"#ffde3c50","#ff9b9b9b","#a5468ad9","#ffb8e986","#fff9ec4f"};

    ImageView ImageSwipe;
    RelativeLayout Li;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /**Configuration for the swipe*/
       ImageSwipe = findViewById(R.id.draw);
       Li= findViewById(R.id.relativelayout);
       ImageSwipe.setImageResource(mDraw[gesture]);
       mGestureDetector = new GestureDetector(this, this);
       mTextTest = findViewById(R.id.textTest);

       /**Configuration for the comment*/
        ImageButton mBtnComment = findViewById(R.id.btnComment);
        databaseComment = new DatabaseComment(this);
        mBtnComment.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.comment, null);
                EditText mTextComment = mView.findViewById(R.id.textComment);
                final String stringTextComment = mTextComment.getText().toString();
                Button mValidComment = mView.findViewById(R.id.validComment);
                Button mCancelComment = mView.findViewById(R.id.cancelComment);




                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();

                dialog.show();

                mValidComment.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public  void onClick(View view){
                        final Calendar now = GregorianCalendar.getInstance();
                        final int dayNumber = now.get(Calendar.DAY_OF_MONTH);

                        Toast.makeText(MainActivity.this,"Commenta is ok",Toast.LENGTH_SHORT).show();
                        databaseComment.insertTable(stringTextComment,gesture,dayNumber);
                        databaseComment.close();
                        dialog.cancel();



                    }
                });

                mCancelComment.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public  void onClick(View view){
                        Toast.makeText(MainActivity.this,"Cancel is ok",Toast.LENGTH_SHORT).show();
                        dialog.cancel();


                    }
                });
            }
        });

    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        //Toast.makeText(getApplicationContext(),"OnDown",100).show();

        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        // Toast.makeText(getApplicationContext(),"OnShow Press",100).show();

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        //Toast.makeText(getApplicationContext(),"Single Tap",100).show();

        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        //Toast.makeText(getApplicationContext(),"OnScroll",100).show();

        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        // Toast.makeText(getApplicationContext(),"LongPress",100).show();

    }

    /**Method onFling is use to detect the vertical swipe and choode the good action (gesture++ or gesture--)
     * This method permit too to disabled horizontal swipe*/

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        if (e1.getX() < e2.getX()) {
            Log.d("DABAGO", "Left to Right swipe performed");


        }

        if (e1.getX() > e2.getX()) {
            Log.d("DABAGO", "Right to Left swipe performed");

        }

        if (e1.getY() < e2.getY()) {
            Log.d("DABAGO", "Up to Down swipe performed");
            gesture--;
            Image_Change();
        }

        if (e1.getY() > e2.getY()) {
            Log.d("DABAGO", "Down to Up swipe performed");
            gesture++;
            Image_Change();
        }

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return mGestureDetector.onTouchEvent(event);

    }
    /**Method to call a changement of the image
     * This method is placed into the gestures detector methods*/

    public void Image_Change() {

        Log.d("DABAGO", "Image change");
        if (gesture<5 && gesture >=0) ParametersImage();



        else if (gesture >=5) {
            gesture = 0;

            ParametersImage();


        }
        else if (gesture <0){
            gesture = 4;

            ParametersImage();

        }
    }

    /**Method to change an image (background too) and add the mood in the SQLite database*/

    public void ParametersImage(){
        final Calendar now = GregorianCalendar.getInstance();
        final int dayNumber = now.get(Calendar.DAY_OF_MONTH);
        ImageSwipe.setImageResource(mDraw[gesture]);
        Li.setBackgroundColor(Color.parseColor(mColor[gesture]));
        Log.d("DABAGO", "ParameterImage");
        databaseComment.MoodStatusInsertion(mTextTest,gesture,dayNumber);

    }

    /**Open the History Activity*/
    public void LogoHistory (View view) {
        startActivity(new Intent(this, HistoryActivity.class));

    }


}

