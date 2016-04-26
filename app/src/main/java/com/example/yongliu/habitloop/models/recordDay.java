package com.example.yongliu.habitloop.models;

import java.util.Date;

/**
 * Created by YongLiu on 4/25/16.
 */
public class recordDay {

    private Date mDate;
    private int mStatus; //0 unfill, 1 complete, 2 incomplete

    recordDay(){

    }
    recordDay(Date date, int status){
        mDate = date;
        mStatus = status;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }
}
