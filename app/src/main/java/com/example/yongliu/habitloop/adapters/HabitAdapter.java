package com.example.yongliu.habitloop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yongliu.habitloop.R;
import com.example.yongliu.habitloop.models.Habit;

import java.util.ArrayList;

/**
 * Created by YongLiu on 1/11/16.
 */
public class HabitAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<Habit> mHabits;

    public HabitAdapter(Context context, ArrayList<Habit> habits){
       mContext = context;
        mHabits = habits;
    }

    @Override
    public int getCount() {
        return mHabits.size();//mHabits.length;
    }

    @Override
    public Object getItem(int position) {
        return mHabits.get(position);//mHabits[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertView (2nd parameter) is the view we reuse
        ViewHolder holder;

        if(convertView == null){
            //its brand new, create view by inflating it from the context
            //layoutInflater is an android obj that takes xml layouts, and turns them into views
            // and codes we can use
            convertView = LayoutInflater.from(mContext).inflate(R.layout.habit_list_item, null);
            holder = new ViewHolder();
            holder.habitNameView = (TextView) convertView.findViewById(R.id.habitName);
            holder.streakView = (EditText) convertView.findViewById(R.id.streak);
            holder.lastDateView = (EditText) convertView.findViewById(R.id.lastDate);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        Habit habit = mHabits.get(position);
        holder.habitNameView.setText(habit.getHabitName());
        holder.streakView.setText(habit.getStreak() + "");
        holder.lastDateView.setText(habit.getLastDate());

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public static class ViewHolder{
        TextView habitNameView;
        EditText streakView;
        EditText lastDateView;
    }

}
