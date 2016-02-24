package com.example.yongliu.habitloop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.yongliu.habitloop.R;
import com.example.yongliu.habitloop.adapters.HabitAdapter;
import com.example.yongliu.habitloop.models.Habit;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.habits) ListView mRecyclerView;
    HabitAdapter mHabitAdapter;
    ArrayAdapter apt;
    private ArrayList<Habit> habits;
    ArrayList<String> temp = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        //Intent intent = getIntent();

        //Parcelable[] parcelables = intent.getParcelableArrayExtra("HABITS");
        //Log.d("PARCE", parcelables.toString());
        habits = //Arrays.copyOf(parcelables, parcelables.length, ArrayList<Habit>.class);

        HabitAdapter mHabitAdapter = new HabitAdapter(this, R.layout.habit_list_item ,temp);
        //apt = new ArrayAdapter(this, android.R.layout.simple_list_item_1 ,temp);
        mRecyclerView.setAdapter(apt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_habit) {
            //Habit newHabit = new Habit("habit1", 0, "01/26/16");
            temp.add("yong1");
            //mHabitAdapter.notifyDataSetChanged();
            apt.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }
}
