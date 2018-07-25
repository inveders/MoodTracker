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
import android.widget.Toast;

import com.mood.gnimadi.alexandra.moodtracker.Model.DatabaseComment;
import com.mood.gnimadi.alexandra.moodtracker.R;


public class MainActivity extends AppCompatActivity implements android.view.GestureDetector.OnGestureListener {

    private GestureDetector mGestureDetector;
    private int gesture=3;
    private DatabaseComment databaseComment;


    int[] mDraw = {
            R.drawable.smiley_sad,
            R.drawable.smiley_disappointed,
            R.drawable.smiley_normal,
            R.drawable.smiley_happy,
            R.drawable.smiley_super_happy};
    String[] mColor={"#ffde3c50","#ff9b9b9b","#a5468ad9","#ffb8e986","#fff9ec4f"};

    ImageView ImageSwipe;
    RelativeLayout Li;
    private String DayComment;


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
        ImageButton mBtnComment = (ImageButton) findViewById(R.id.btnComment);
        databaseComment = new DatabaseComment(this);
        mBtnComment.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.comment, null);
                EditText mTextComment = (EditText) mView.findViewById(R.id.textComment);
                final String stringTextComment = mTextComment.getText().toString();
                Button mValidComment = (Button) mView.findViewById(R.id.validComment);
                Button mCancelComment = (Button) mView.findViewById(R.id.cancelComment);


                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                Log.d("DEBUG", "ALertDialog");
                dialog.show();

                mValidComment.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public  void onClick(View view){
                        Toast.makeText(MainActivity.this,"Comment is ok",Toast.LENGTH_SHORT).show();
                        databaseComment.insertTable(stringTextComment,gesture);
                        databaseComment.close();
                        dialog.cancel();
                        String DayOfComment = stringTextComment.toString();


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

    /**Method to change an image (background too) and add the mood in the SQLite database*/
    public void ParametersImage(){
        ImageSwipe.setImageResource(mDraw[gesture]);
        Li.setBackgroundColor(Color.parseColor(mColor[gesture]));
        MoodStatusInsertion();

    }

    /**Open the History Activity*/
    public void LogoHistory (View view) {
        startActivity(new Intent(this, HistoryActivity.class));

    }

   /**Put the mood in the SQLite database*/
    public void MoodStatusInsertion(){

        //Récuper la valeur de mon tableau ici pour la colonne commentaire et voir si c'est null ou pas.
        if (DayComment==null) {
            databaseComment.insertTable(DayComment, gesture);
            Toast.makeText(MainActivity.this, "Gesture insert is ok", Toast.LENGTH_SHORT).show();
            databaseComment.close();
        }
        else {
            databaseComment.updateTable(DayComment, gesture);
            Toast.makeText(MainActivity.this, "Gesture update is ok", Toast.LENGTH_SHORT).show();
            databaseComment.close();
        }


    }


}

