package com.mood.gnimadi.alexandra.moodtracker.Controller;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        addListenerOnButton();
    }



    public void addListenerOnButton() {

        mImage = (ImageView) findViewById(R.id.draw);

        mButton = (Button) findViewById(R.id.btnChangeImage);
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //mImage.setImageResource(R.drawable.smiley_sad);

                gesture=2;
                //SWIPE UP we add +1 to gesture

               switch(gesture){
                   case 0 :
                       mDraw.set(0,R.drawable.smiley_sad);
                       break;
                   case 1 :
                       mDraw.set(1,R.drawable.smiley_disappointed);
                       break;
                   case 2 :
                       mDraw.set(2,R.drawable.smiley_normal);
                       break;
                   case 3 :
                       mDraw.set(3,R.drawable.smiley_happy);
                       break;
                   case 4 :
                       mDraw.set(4,R.drawable.smiley_super_happy);
                       break;
                   default:
                       mDraw.set(2,R.drawable.smiley_normal);
                       break;

            }

        });

    }
}

