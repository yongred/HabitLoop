package com.example.yongliu.habitloop.models;

/**
 * Created by YongLiu on 1/11/16.
 */
public class Habit{
    private String mHabitName;
    private String mTime; //when they want to do the habit at that day
    private String mStartTime;
    private String mEndTime;
    private int mStreak;
    private WeekDays mDays;
    //private String mDays;

    public Habit() {

    }

    public Habit(String habitName, int streak, String startTime, String endTime, WeekDays days){
        mHabitName = habitName;
        mStartTime = startTime;
        mEndTime = endTime;
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

    public WeekDays getDays() {
        return mDays;
    }

    public void setDays(WeekDays days) {
        mDays = days;
    }

    public String getEndTime() {
        return mEndTime;
    }

    public void setEndTime(String endTime) {
        mEndTime = endTime;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public void setStartTime(String startTime) {
        mStartTime = startTime;
    }
}
