package com.mood.gnimadi.alexandra.moodtracker.Controller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mood.gnimadi.alexandra.moodtracker.Model.DatabaseComment;
import com.mood.gnimadi.alexandra.moodtracker.Model.MoodAndComment;
import com.mood.gnimadi.alexandra.moodtracker.R;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private DatabaseComment databaseComment;
    RelativeLayout mRelative_oneWeek, mRelative_sixDays, mRelative_fiveDays, mRelative_fourDays, mRelative_threeDays, mRelative_beforeYesterday, mRelative_yesterday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        databaseComment = new DatabaseComment(this);

        //Link the different Relative Layout id with the Activity
        mRelative_oneWeek = findViewById(R.id.relative_oneWeek);
        mRelative_sixDays = findViewById(R.id.relative_sixDays);
        mRelative_fiveDays = findViewById(R.id.relative_fiveDays);
        mRelative_fourDays = findViewById(R.id.relative_fourDays);
        mRelative_threeDays = findViewById(R.id.relative_threeDays);
        mRelative_beforeYesterday = findViewById(R.id.relative_beforeYesterday);
        mRelative_yesterday = findViewById(R.id.relative_yesterday);


        //Link the different Image Button id with the Activity
        ImageButton mComment_oneWeek = findViewById(R.id.comment_oneWeek);
        ImageButton mComment_sixDays = findViewById(R.id.comment_sixDays);
        ImageButton mComment_fiveDays = findViewById(R.id.comment_fiveDays);
        ImageButton mComment_fourDays = findViewById(R.id.comment_fourDays);
        ImageButton mComment_threeDays = findViewById(R.id.comment_threeDays);
        ImageButton mComment_beforeYesterday = findViewById(R.id.comment_beforeYesterday);
        ImageButton mComment_yesterday = findViewById(R.id.comment_yesterday);

        //Link the different TextView id with the Activity
        TextView mText_oneWeek = findViewById(R.id.text_oneWeek);
        TextView mText_sixDays = findViewById(R.id.text_sixDays);
        TextView mText_fiveDays = findViewById(R.id.text_fiveDays);
        TextView mText_fourDays = findViewById(R.id.text_fourDays);
        TextView mText_threeDays = findViewById(R.id.text_threeDays);
        TextView mText_beforeYesterday = findViewById(R.id.text_beforeYesterday);
        TextView mText_yesterday = findViewById(R.id.text_yesterday);

        //Calling the organizeHistory to to arrange the layout of the activity
        organizeHistory(mComment_oneWeek, mComment_sixDays, mComment_fiveDays, mComment_fourDays, mComment_threeDays, mComment_beforeYesterday, mComment_yesterday,
                mText_oneWeek, mText_sixDays, mText_fiveDays, mText_fourDays, mText_threeDays, mText_beforeYesterday, mText_yesterday );

        //Calling the database to make action with it
        final List<MoodAndComment> dayTable ;
        dayTable = databaseComment.manipulateTable();

        int lastValue = dayTable.size();

        //Define the last 7 values of the database
        final int oneWeekValue = lastValue-8;
        final int sixDaysValue = lastValue-7;
        final int fiveDaysValue = lastValue-6;
        final int fourDaysValue = lastValue-5;
        final int threeDaysValue = lastValue-4;
        final int beforeYesterdayValue = lastValue-3;
        final int yesterdayValue = lastValue-2;

        //In this part of the code, we show a Toast when we click on the comment button in the History Activity
        mComment_oneWeek.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(isNullOrBlank(dayTable.get(oneWeekValue).getTextComment())) Toast.makeText(HistoryActivity.this,dayTable.get(oneWeekValue).getTextComment(),Toast.LENGTH_SHORT).show();
            }
        });

        mComment_sixDays.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(isNullOrBlank(dayTable.get(sixDaysValue).getTextComment())) Toast.makeText(HistoryActivity.this,dayTable.get(sixDaysValue).getTextComment(),Toast.LENGTH_SHORT).show();
            }
        });

        mComment_fiveDays.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(isNullOrBlank(dayTable.get(fiveDaysValue).getTextComment())) Toast.makeText(HistoryActivity.this,dayTable.get(fiveDaysValue).getTextComment(),Toast.LENGTH_SHORT).show();
            }
        });

        mComment_fourDays.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(isNullOrBlank(dayTable.get(fourDaysValue).getTextComment())) Toast.makeText(HistoryActivity.this,dayTable.get(fourDaysValue).getTextComment(),Toast.LENGTH_SHORT).show();
            }
        });

        mComment_threeDays.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(isNullOrBlank(dayTable.get(threeDaysValue).getTextComment())) Toast.makeText(HistoryActivity.this,dayTable.get(threeDaysValue).getTextComment(),Toast.LENGTH_SHORT).show();
            }
        });

        mComment_beforeYesterday.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(isNullOrBlank(dayTable.get(beforeYesterdayValue).getTextComment())) Toast.makeText(HistoryActivity.this,dayTable.get(beforeYesterdayValue).getTextComment(),Toast.LENGTH_SHORT).show();
            }
        });

        mComment_yesterday.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(isNullOrBlank(dayTable.get(yesterdayValue).getTextComment())) Toast.makeText(HistoryActivity.this,dayTable.get(yesterdayValue).getTextComment(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private static boolean isNullOrBlank(String s)
    {
        return (s != null && !s.trim().equals(""));
    }

    public void organizeHistory(ImageButton mComment_oneWeek, ImageButton mComment_sixDays, ImageButton mComment_fiveDays, ImageButton mComment_fourDays, ImageButton mComment_threeDays, ImageButton mComment_beforeYesterday, ImageButton mComment_Yesterday,
                                TextView mText_oneWeek, TextView mText_sixDays, TextView mText_fiveDays, TextView mText_fourDays, TextView mText_threeDays, TextView mText_beforeYesterday, TextView mText_Yesterday){

        //To set the width of each Relative Layout dynamically
        RelativeLayout relativeLayout1 = findViewById(R.id.relative_oneWeek);
        RelativeLayout relativeLayout2 = findViewById(R.id.relative_sixDays);
        RelativeLayout relativeLayout3 = findViewById(R.id.relative_fiveDays);
        RelativeLayout relativeLayout4 = findViewById(R.id.relative_fourDays);
        RelativeLayout relativeLayout5 = findViewById(R.id.relative_threeDays);
        RelativeLayout relativeLayout6 = findViewById(R.id.relative_beforeYesterday);
        RelativeLayout relativeLayout7 = findViewById(R.id.relative_yesterday);

        List<MoodAndComment> dayTable ;
        dayTable = databaseComment.manipulateTable();

        int lastValue = dayTable.size();
        int oneWeekValue = lastValue-8;
        int sixDaysValue = lastValue-7;
        int fiveDaysValue = lastValue-6;
        int fourDaysValue = lastValue-5;
        int threeDaysValue = lastValue-4;
        int beforeYesterdayValue = lastValue-3;
        int yesterdayValue = lastValue-2;

        //The values for the width of each Relative Layout
        int[] widthGesture ={250,500,700,900,RelativeLayout.LayoutParams.MATCH_PARENT};

        //The colors corresponding to the Mood et associated with the History
        String[] mColor={"#ffde3c50","#ff9b9b9b","#a5468ad9","#ffb8e986","#fff9ec4f"};

        //Put the good color and the good width in the history. enable or disable the comment Image button if there is or not comment in the database
        if(oneWeekValue>=0){
            mRelative_oneWeek.setBackgroundColor(Color.parseColor(mColor[dayTable.get(oneWeekValue).getGesture()]));
            ViewGroup.LayoutParams lp = relativeLayout1.getLayoutParams();
            lp.width=widthGesture[dayTable.get(oneWeekValue).getGesture()];
            relativeLayout1.setLayoutParams(lp);
            mText_oneWeek.setVisibility(View.VISIBLE);
            if(isNullOrBlank(dayTable.get(oneWeekValue).getTextComment())) mComment_oneWeek.setVisibility(View.VISIBLE);
        }
        else {
            mRelative_oneWeek.setBackgroundColor(0xFFFFFFFF);
            mText_oneWeek.setVisibility(View.INVISIBLE);
        }

        if(sixDaysValue>=0) {
            mRelative_sixDays.setBackgroundColor(Color.parseColor(mColor[dayTable.get(sixDaysValue).getGesture()]));
            ViewGroup.LayoutParams lp = relativeLayout2.getLayoutParams();
            lp.width=widthGesture[dayTable.get(sixDaysValue).getGesture()];
            relativeLayout2.setLayoutParams(lp);
            mText_sixDays.setVisibility(View.VISIBLE);
            if(isNullOrBlank(dayTable.get(sixDaysValue).getTextComment())) mComment_sixDays.setVisibility(View.VISIBLE);
        }
        else{
            mRelative_sixDays.setBackgroundColor(0xFFFFFFFF);
            mText_sixDays.setVisibility(View.INVISIBLE);
        }

        if(fiveDaysValue>=0){
            mRelative_fiveDays.setBackgroundColor(Color.parseColor(mColor[dayTable.get(fiveDaysValue).getGesture()]));
            ViewGroup.LayoutParams lp = relativeLayout3.getLayoutParams();
            lp.width=widthGesture[dayTable.get(fiveDaysValue).getGesture()];
            relativeLayout3.setLayoutParams(lp);
            mText_fiveDays.setVisibility(View.VISIBLE);
            if(isNullOrBlank(dayTable.get(fiveDaysValue).getTextComment()))  mComment_fiveDays.setVisibility(View.VISIBLE);
        }
        else{
            mRelative_fiveDays.setBackgroundColor(0xFFFFFFFF);
            mText_fiveDays.setVisibility(View.INVISIBLE);
        }

        if(fourDaysValue>=0) {
            mRelative_fourDays.setBackgroundColor(Color.parseColor(mColor[dayTable.get(fourDaysValue).getGesture()]));
            ViewGroup.LayoutParams lp = relativeLayout4.getLayoutParams();
            lp.width=widthGesture[dayTable.get(fourDaysValue).getGesture()];
            relativeLayout4.setLayoutParams(lp);
            mText_fourDays.setVisibility(View.VISIBLE);
            if(isNullOrBlank(dayTable.get(fourDaysValue).getTextComment())) mComment_fourDays .setVisibility(View.VISIBLE);
        }
        else{
            mRelative_fourDays.setBackgroundColor(0xFFFFFFFF);
            mText_fourDays.setVisibility(View.INVISIBLE);
        }

        if(threeDaysValue>=0){
            mRelative_threeDays.setBackgroundColor(Color.parseColor(mColor[dayTable.get(threeDaysValue).getGesture()]));
            ViewGroup.LayoutParams lp = relativeLayout5.getLayoutParams();
            lp.width=widthGesture[dayTable.get(threeDaysValue).getGesture()];
            relativeLayout5.setLayoutParams(lp);
            mText_threeDays.setVisibility(View.VISIBLE);
            if(isNullOrBlank(dayTable.get(threeDaysValue).getTextComment())) mComment_threeDays.setVisibility(View.VISIBLE);
        }
        else{
            mRelative_threeDays.setBackgroundColor(0xFFFFFFFF);
            mText_threeDays.setVisibility(View.INVISIBLE);
        }

        if(beforeYesterdayValue>=0){
            mRelative_beforeYesterday.setBackgroundColor(Color.parseColor(mColor[dayTable.get(beforeYesterdayValue).getGesture()]));
            ViewGroup.LayoutParams lp = relativeLayout6.getLayoutParams();
            lp.width=widthGesture[dayTable.get(beforeYesterdayValue).getGesture()];
            relativeLayout6.setLayoutParams(lp);
            mText_beforeYesterday.setVisibility(View.VISIBLE);
            if(isNullOrBlank(dayTable.get(beforeYesterdayValue).getTextComment())) mComment_beforeYesterday.setVisibility(View.VISIBLE);
        }
        else{
            mRelative_beforeYesterday.setBackgroundColor(0xFFFFFFFF);
            mText_beforeYesterday.setVisibility(View.INVISIBLE);
        }

        if(yesterdayValue>=0){
            mRelative_yesterday.setBackgroundColor(Color.parseColor(mColor[dayTable.get(yesterdayValue).getGesture()]));
            ViewGroup.LayoutParams lp = relativeLayout7.getLayoutParams();
            lp.width=widthGesture[dayTable.get(yesterdayValue).getGesture()];
            relativeLayout7.setLayoutParams(lp);
            mText_Yesterday.setVisibility(View.VISIBLE);
            if(isNullOrBlank(dayTable.get(yesterdayValue).getTextComment())) mComment_Yesterday.setVisibility(View.VISIBLE);
        }
        else{
            mRelative_yesterday.setBackgroundColor(0xFFFFFFFF);
            mText_Yesterday.setVisibility(View.INVISIBLE);
        }

    }
}
