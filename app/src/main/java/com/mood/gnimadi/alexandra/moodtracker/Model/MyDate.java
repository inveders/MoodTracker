package com.mood.gnimadi.alexandra.moodtracker.Model;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Map;

@TargetApi(Build.VERSION_CODES.O)
@RequiresApi(api = Build.VERSION_CODES.O)
public class MyDate {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void dayToday (){
        /**Get the current date and time*/
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDate DayToday = currentTime.toLocalDate();
        Month month = currentTime.getMonth();
        int dayNumber = currentTime.getDayOfMonth();
        int hour = currentTime.getHour();
    }

    public void MyZoneId() {
        /**Get the current ZoneId*/
        Map<String,String> maps = ZoneId.SHORT_IDS;
        ZoneId.systemDefault();
        ZoneId.systemDefault().getRules();
    }



}