package com.example.yongliu.habitloop.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by YongLiu on 1/11/16.
 */
public class Habit implements Parcelable {
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

    public Habit(Parcel in){
        mHabitName = in.readString();
        mLastDate = in.readString();
        mStreak = in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mHabitName);
        parcel.writeString(mLastDate);
        parcel.writeInt(mStreak);
    }

    public static final Creator<Habit> CREATOR = new Creator<Habit>() {
        @Override
        public Habit createFromParcel(Parcel parcel) {
            return new Habit(parcel);
        }

        @Override
        public Habit[] newArray(int i) {
            return new Habit[i];
        }
    };
}
