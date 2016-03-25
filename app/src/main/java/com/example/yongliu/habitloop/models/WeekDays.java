package com.example.yongliu.habitloop.models;

import java.util.Arrays;

/**
 * representing the days user wants to do the activity
 * Created by YongLiu on 3/25/16.
 */
public class WeekDays {
    static final String [] DAY_STRINGS = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    private String mDisplayString;

    // 1st (index 0)= Mon, ... 7th (index 6)= Sun
    private Boolean [] mDayBools = {false, false, false, false, false, false, false};

    public WeekDays(){
        mDayBools = new Boolean[7];
        for (Boolean day:mDayBools) {
            day = false;
        }
    }

    public WeekDays(String [] days){
        setCheckedDays(days);
    }

    private void setCheckedDays(String [] days){
        for(String d: days){
            int index = Arrays.asList(DAY_STRINGS).indexOf(d);
            if(index != -1){
                mDayBools[index] = true;
            }
        }
    }

    public void setDisplayString(){
        int countDays =0;
        for(int i = 0; i< DAY_STRINGS.length; i++){
            if(mDayBools[i]){
                countDays++;
                String tempStr = DAY_STRINGS[i] + " ";
                mDisplayString += tempStr;
            }
        }
        if(countDays == 7){
            mDisplayString = "everyday";
        }
    }

    public String getDisplayString(){
        return mDisplayString;
    }

    public Boolean[] getDayBools() {
        return mDayBools;
    }

    public void setDayBools(Boolean[] dayBools) {
        this.mDayBools = dayBools;
    }
}
