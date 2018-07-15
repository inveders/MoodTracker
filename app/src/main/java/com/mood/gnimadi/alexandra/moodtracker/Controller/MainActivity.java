package com.mood.gnimadi.alexandra.moodtracker.Controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;

import com.mood.gnimadi.alexandra.moodtracker.R;


public class MainActivity extends Activity implements android.view.GestureDetector.OnGestureListener {

    private GestureDetector mGestureDetector;
    private int gesture=2;

    int[] mDraw = {
            R.drawable.smiley_sad,
            R.drawable.smiley_disappointed,
            R.drawable.smiley_normal,
            R.drawable.smiley_happy,
            R.drawable.smiley_super_happy};
    ImageView imageSwipe = (ImageView) findViewById(R.id.draw);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// lui il enlève


        imageSwipe.setImageResource(mDraw[gesture]);
        mGestureDetector = new GestureDetector(this, this);
        //setContentView(imageSwipe);

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
        Toast.makeText(getApplicationContext(),"Single Tap",100).show();
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

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        //Toast.makeText(getApplicationContext(),"OnFlying",100).show();
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       Image_Change();
       return mGestureDetector.onTouchEvent(event);

    }

   public void Image_Change() {
        gesture++;
        imageSwipe.setImageResource(mDraw[gesture]);
        System.out.println("Ok "+gesture);
       Toast.makeText(getApplicationContext(),"Image Change",100).show();

    }

}



//SWIPE UP we add +1 to gesture
/*
            if (gesture<5 && gesture >=0) {
                switch (gesture) {
                    case 0:

                        mImage.setImageDrawable(mDraw[gesture]);
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
                getDrawable(gesture);
            }
            else (gesture <0){
                gesture = 4;
                mDraw.set(4, R.drawable.smiley_super_happy);
                getResources().getIdentifier(variable,"drawable",getPackageName());
            }
        }

*/