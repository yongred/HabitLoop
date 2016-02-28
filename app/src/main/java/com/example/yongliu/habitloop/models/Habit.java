package com.example.yongliu.habitloop.models;

/**
 * Created by YongLiu on 1/11/16.
 */
public class Habit{
    private String mHabitName;
    private String mLastDate;
    private int mStreak;

    public Habit() {

    }

    public Habit(String habitName, int streak, String lastDate){
        mHabitName = habitName;
        mLastDate = lastDate;
        mStreak = streak;
    }

    public String getHabitName() {
        return mHabitName;
    }

    public void setHabitName(String habitName) {
        mHabitName = habitName;
    }

    public String getLastDate() {
        return mLastDate;
    }

    public void setLastDate(String lastDate) {
        mLastDate = lastDate;
    }

    public int getStreak() {
        return mStreak;
    }

    public void setStreak(int streak) {
        mStreak = streak;
    }
}
