package com.example.yongliu.habitloop.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.yongliu.habitloop.R;
import com.example.yongliu.habitloop.models.Habit;
import com.example.yongliu.habitloop.ui.InfoEditActivity;

import java.util.ArrayList;

/**
 * Created by YongLiu on 3/24/16.
 */
public class EditAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Habit> mHabits;

    public EditAdapter(Context context, ArrayList<Habit> habits) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        //convertView (2nd parameter) is the view we reuse
        ViewHolder holder;

        if (convertView == null) {
            //its brand new, create view by inflating it from the context
            //layoutInflater is an android obj that takes xml layouts, and turns them into views
            // and codes we can use
            convertView = LayoutInflater.from(mContext).inflate(R.layout.edit_list_item,
                    null);
            holder = new ViewHolder();
            holder.habitNameView = (TextView) convertView.findViewById(R.id.habitNameViewEdit);
            holder.delButtonView = (ImageButton) convertView.findViewById(R.id.itemDelButton);
            holder.editButtonView = (ImageButton) convertView.findViewById(R.id.itemEditButton);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //get and set habit infos here
        Habit habit = mHabits.get(position);
        holder.habitNameView.setText(habit.getHabitName());
        holder.delButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(mContext)
                        .setTitle(R.string.dialog_delete_title)
                        .setMessage(R.string.dialog_delete_message)
                        .setPositiveButton(R.string.dialog_positive_button, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mHabits.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(R.string.dialog_negative_button, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //do nothing
                            }
                        }).show();
            }
        });

        holder.editButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, InfoEditActivity.class);
                intent.putExtra(mContext.getString(R.string.EXTRA_HABIT_CLICKED_INDEX), position);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public static class ViewHolder {
        TextView habitNameView;
        ImageButton delButtonView;
        ImageButton editButtonView;
    }
}
