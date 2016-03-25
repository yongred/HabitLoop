package com.example.yongliu.habitloop.models;

/**
 * Created by YongLiu on 1/11/16.
 */
public class Habit{
    private String mHabitName;
    private String mTime; //when they want to do the habit at that day
    private int mStreak;
    private String mDays;

    public Habit() {

    }

    public Habit(String habitName, int streak, String time, String days){
        mHabitName = habitName;
        mTime = time;
        mStreak = streak;
        mDays = days;
    }

    public String getHabitName() {
        return mHabitName;
    }

    public void setHabitName(String habitName) {
        mHabitName = habitName;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public int getStreak() {
        return mStreak;
    }

    public void setStreak(int streak) {
        mStreak = streak;
    }

    public String getDays() {
        return mDays;
    }

    public void setDays(String days) {
        mDays = days;
    }
}
