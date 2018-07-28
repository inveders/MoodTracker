package com.mood.gnimadi.alexandra.moodtracker.Model;

import android.util.Log;

public class MoodAndComment {

    private int idComment ;
    private String textComment;
    private int gesture;
    private int dayOfMood;



    public MoodAndComment(int idComment, String textComment, int gesture, int dayOfMood) {
        this.setIdComment(idComment);
        this.setTextComment(textComment);
        this.setGesture(gesture);
        this.setDayOfMood(dayOfMood);
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public String getTextComment() {
        return textComment;
    }

    public void setTextComment(String textComment) {
        this.textComment = textComment;
    }

    public int getGesture() {
        Log.d("DABAGO", "JVOIS PAS DE QUOI TU PARLES");
        return gesture;
    }

    public void setGesture(int gesture) {
        this.gesture = gesture;
    }

    public int getDayOfMood() {


        return dayOfMood;
    }

    public void setDayOfMood(int dayOfMood) {
        this.dayOfMood = dayOfMood;
    }

    @Override
    public String toString(){
        return textComment;
    }

}
