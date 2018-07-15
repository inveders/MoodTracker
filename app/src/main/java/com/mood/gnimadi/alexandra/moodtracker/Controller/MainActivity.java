package com.mood.gnimadi.alexandra.moodtracker.Controller;

import android.content.Context;
import android.gesture.GestureOverlayView;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mood.gnimadi.alexandra.moodtracker.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button mButton;
    ImageView mImage;
    ArrayList<ImageView> mDraw = new ArrayList<ImageView>();
    int gesture;

    mDraw{
        R.drawable.smiley_sad,
        R.drawable.smiley_disappointed,
        R.drawable.smiley_normal,
        R.drawable.smiley_happy,
        R.drawable.smiley_super_happy
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onTouchEvent();
    }





    public class MyView extends View implements GestureOverlayView.OnGestureListener {

        private GestureDetector mGestureDetector;
        private int gesture=2;
        public MyView(Context context) {
            super(context);
            mGestureDetector = new GestureDetector(this);
        }

        public boolean onTouchEvent(MotionEvent event) {
            return mGestureDetector.onTouchEvent(event);
            Image_Change(gesture++);


            }


        public boolean onDown(MotionEvent arg0) {
            // Don't forget to return true here to get the following touch events
            return true;
        }

        public void Image_Change(int gesture){

            //mImage = (ImageView) findViewById(R.id.draw);

            //SWIPE UP we add +1 to gesture

            if (gesture<5 && gesture >=0) {
                switch (gesture) {
                    case 0:
                        mDraw.set(0, R.drawable.smiley_sad);
                        break;
                    case 1:
                        mDraw.set(1, R.drawable.smiley_disappointed);
                        break;
                    case 2:
                        mDraw.set(2, R.drawable.smiley_normal);
                        break;
                    case 3:
                        mDraw.set(3, R.drawable.smiley_happy);
                        break;
                    case 4:
                        mDraw.set(4, R.drawable.smiley_super_happy);
                        break;
                    default:
                        mDraw.set(2, R.drawable.smiley_normal);
                        break;

                }
            }
            else if (gesture >5) {
                gesture = 0;
                mDraw.set(0, R.drawable.smiley_sad);
            }
            else (gesture <0){
                gesture = 4;
                mDraw.set(4, R.drawable.smiley_super_happy);
            }
        }


    }