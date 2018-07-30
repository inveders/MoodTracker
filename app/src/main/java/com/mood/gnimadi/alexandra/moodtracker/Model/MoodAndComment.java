package com.mood.gnimadi.alexandra.moodtracker.Model;

public class MoodAndComment {

    private String textComment;
    private int gesture;
    private int dayOfMood;

    public MoodAndComment(int idComment, String textComment, int gesture, int dayOfMood) {
        this.setIdComment(idComment);
        this.setTextComment(textComment);
        this.setGesture(gesture);
        this.setDayOfMood(dayOfMood);
    }

    private void setIdComment(int idComment) {
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

    private void setGesture(int gesture) {
        this.gesture = gesture;
    }

    public int getDayOfMood() {

        return dayOfMood;
    }

    private void setDayOfMood(int dayOfMood) {
        this.dayOfMood = dayOfMood;
    }

    @Override
    public String toString(){
        return textComment;
    }

}
