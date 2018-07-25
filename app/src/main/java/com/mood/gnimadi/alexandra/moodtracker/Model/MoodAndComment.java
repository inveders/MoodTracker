package com.mood.gnimadi.alexandra.moodtracker.Model;

public class MoodAndComment {

    private int idComment ;
    private String textComment;
    private int gesture;
    private int dayOfComment;



    public MoodAndComment(int idComment, String textComment, int gesture, int dayOfComment) {
        this.setIdComment(idComment);
        this.setTextComment(textComment);
        this.setGesture(gesture);
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
        return gesture;
    }

    public void setGesture(int gesture) {
        this.gesture = gesture;
    }

    public int getDayOfComment() {

        return dayOfComment;
    }

    public void setDayOfComment(int dayOfComment) {
        this.dayOfComment = dayOfComment;
    }

    @Override
    public String toString(){
        return textComment;
    }

   /* public void MyZoneId() {
        /**Get the current ZoneId

        ZoneId.systemDefault();
        ZoneId.systemDefault().getRules();
    }*/
}
