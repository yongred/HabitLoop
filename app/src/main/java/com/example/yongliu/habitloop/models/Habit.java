package com.example.yongliu.habitloop.models;

import java.util.ArrayList;
import java.util.Date;

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

    private ArrayList<Date> mCompleteDays;
    private ArrayList<Date> mIncompleteDays;
    //private String mDays;

    public Habit() {

    }

    public Habit(String habitName, int streak, String startTime, String endTime, WeekDays days){
        mHabitName = habitName;
        mStartTime = startTime;
        mEndTime = endTime;
        mStreak = streak;
        mDays = days;
        mCompleteDays = new ArrayList<Date>();
        mIncompleteDays = new ArrayList<Date>();
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


    public ArrayList<Date> getCompleteDays() {
        return mCompleteDays;
    }

    public void addCompleteDays(Date date){
        mCompleteDays.add(date);
    }

    public void removeCompleteDays(Date date){
        mCompleteDays.remove(date);
    }

    public ArrayList<Date> getIncompleteDays() {
        return mIncompleteDays;
    }

    public void addIncompleteDays(Date date){
        mIncompleteDays.add(date);
    }

    public void removeIncompleteDays(Date date){
        mIncompleteDays.remove(date);
    }

    //not finish
    public void calculateStreak(){
        mStreak = mCompleteDays.size();
    }

    //get number of completes monthly
    public int monthlyComplete(Date date){
        int month = date.getMonth();
        int year = date.getYear();
        int count = 0;

        for(Date comp : mCompleteDays){
            if(comp.getYear() == year && comp.getMonth() == month){
                count++;
            }
        }

        return count;
    }

    //get number of incompletes monthly
    public int monthlyIncomplete(Date date){
        int month = date.getMonth();
        int year = date.getYear();
        int count = 0;

        for(Date incomp : mIncompleteDays){
            if(incomp.getYear() == year && incomp.getMonth() == month){
                count++;
            }
        }

        return count;
    }

}
