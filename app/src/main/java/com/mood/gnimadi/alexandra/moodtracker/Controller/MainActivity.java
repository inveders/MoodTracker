package com.mood.gnimadi.alexandra.moodtracker.Controller;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    String[] mColor={"#ffde3c50","#ff9b9b9b","#a5468ad9","#ffb8e986","#fff9ec4f"};

    ImageView ImageSwipe;
    LinearLayout Li;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// lui il enlève

       ImageSwipe = (ImageView) findViewById(R.id.draw);
       Li=(LinearLayout)findViewById(R.id.linearlayout);
       ImageSwipe.setImageResource(mDraw[gesture]);
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
        gesture++;
        Image_Change();

       return mGestureDetector.onTouchEvent(event);

    }

   public void Image_Change() {

      Toast.makeText(getApplicationContext(),"Image Change",100).show();

            if (gesture<5 && gesture >=0) {
                switch (gesture) {
                    case 0:

                        ImageSwipe.setImageResource(mDraw[0]);
                        Li.setBackgroundColor(Color.parseColor(mColor[0]));
                        break;
                    case 1:
                        ImageSwipe.setImageResource(mDraw[1]);
                        Li.setBackgroundColor(Color.parseColor(mColor[1]));
                        break;
                    case 2:
                        ImageSwipe.setImageResource(mDraw[2]);
                        Li.setBackgroundColor(Color.parseColor(mColor[2]));
                        break;
                    case 3:
                        ImageSwipe.setImageResource(mDraw[3]);
                        Li.setBackgroundColor(Color.parseColor(mColor[3]));
                        break;
                    case 4:
                        ImageSwipe.setImageResource(mDraw[4]);
                        Li.setBackgroundColor(Color.parseColor(mColor[4]));
                        break;
                    default:
                        ImageSwipe.setImageResource(mDraw[2]);
                        Li.setBackgroundColor(Color.parseColor(mColor[2]));
                        break;

                }
            }
            else if (gesture >5) {
                gesture = 0;
                ImageSwipe.setImageResource(mDraw[0]);
                Li.setBackgroundColor(Color.parseColor(mColor[0]));
                getDrawable(gesture);
            }
            else if (gesture <0){
                gesture = 4;
                ImageSwipe.setImageResource(mDraw[4]);
                Li.setBackgroundColor(Color.parseColor(mColor[4]));

            }
        }

}

