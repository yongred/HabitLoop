package com.example.yongliu.habitloop.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.yongliu.habitloop.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by YongLiu on 4/11/16.
 */
public class GridCellAdapter extends BaseAdapter {

    private final Context mContext;
    private ArrayList<Date> mDays;
    private Date mCurrentDate;

    public GridCellAdapter(Context context, ArrayList<Date> days, Date currentDate) {
        mContext = context;
        mDays = days;
        mCurrentDate = currentDate;
    }

    @Override
    public int getCount() {
        return mDays.size();
    }

    @Override
    public Object getItem(int position) {
        return mDays.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Date date = (Date)getItem(position);
        int day = date.getDate();
        int month = date.getMonth();
        int year = date.getYear();
        Calendar calendar = Calendar.getInstance();
        Date today = new Date();

        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.calendar_day_gridcell,
                    null);
            holder = new ViewHolder();
            holder.gridcellButton = (Button)convertView.findViewById(R.id.calendarGridcellButton);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.gridcellButton.setText(String.valueOf(date.getDate()));
        if (month != mCurrentDate.getMonth() || year != mCurrentDate.getYear()){
            holder.gridcellButton.setTextColor(ContextCompat.getColor(mContext, R.color.colorGridcellTextGreyOut));
        }
        if (day == today.getDate() && month == today.getMonth() && year == today.getYear()){
            holder.gridcellButton.setTypeface(null, Typeface.BOLD);
            holder.gridcellButton.setTextColor(ContextCompat.getColor(mContext, R.color.colorGridcellToday));
        }

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public static class ViewHolder{
        Button gridcellButton;
    }

}
