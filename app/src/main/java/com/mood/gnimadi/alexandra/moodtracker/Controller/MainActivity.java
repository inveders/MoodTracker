package com.mood.gnimadi.alexandra.moodtracker.Controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mood.gnimadi.alexandra.moodtracker.R;


public class MainActivity extends Activity implements android.view.GestureDetector.OnGestureListener {

    private GestureDetector mGestureDetector;
    private int gesture=3;

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
        //Configuration for the swipe
       ImageSwipe = findViewById(R.id.draw);
       Li= findViewById(R.id.relativelayout);
       ImageSwipe.setImageResource(mDraw[gesture]);
       mGestureDetector = new GestureDetector(this, this);
       //Configuration for the comment
        ImageButton mBtnComment = findViewById(R.id.btnComment);
        mBtnComment.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.comment, null);
                EditText mComment = mView.findViewById(R.id.textComment);
                Button mValidComment = mView.findViewById(R.id.validComment);

                mValidComment.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public  void onClick(View view){
                        Toast.makeText(MainActivity.this,"Comment is ok",Toast.LENGTH_SHORT).show();
                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
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
            Log.d("DEBUG", "Left to Right swipe performed");


        }

        if (e1.getX() > e2.getX()) {
            Log.d("DEBUG", "Right to Left swipe performed");

        }

        if (e1.getY() < e2.getY()) {
            Log.d("DEBUG", "Up to Down swipe performed");
            gesture--;
            Image_Change();
        }

        if (e1.getY() > e2.getY()) {
            Log.d("DEBUG", "Down to Up swipe performed");
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

        //Toast.makeText(getApplicationContext(),"Image Change",100).show();

        if (gesture<5 && gesture >=0) {
            ParametersImage();

        }

        else if (gesture >=5) {
            gesture = 0;
            ParametersImage();
            //getDrawable(gesture);

        }
        else if (gesture <0){
            gesture = 4;
            ParametersImage();

        }
    }

    /**Method to change an image (background too)*/
    public void ParametersImage(){
        ImageSwipe.setImageResource(mDraw[gesture]);
        Li.setBackgroundColor(Color.parseColor(mColor[gesture]));
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        preferences.edit().putInt("Mood", gesture);

    }

    public void LogoHistory (View view) {
        startActivity(new Intent(this, HistoryActivity.class));

    }

    public void LogoComment (View view) {
        
    }

}

