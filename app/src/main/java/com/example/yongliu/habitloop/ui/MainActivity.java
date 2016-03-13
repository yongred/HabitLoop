package com.example.yongliu.habitloop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.yongliu.habitloop.R;
import com.example.yongliu.habitloop.adapters.HabitAdapter;
import com.example.yongliu.habitloop.models.Habit;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.habits) ListView mHabitList;
    HabitAdapter mHabitAdapter;
    private ArrayList<Habit> mHabits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_dehaze_black_24dp);

        ButterKnife.bind(this);

        mHabits = new ArrayList<Habit>();
        mHabitAdapter = new HabitAdapter(this, mHabits);
        mHabitList.setAdapter(mHabitAdapter);
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
            //mHabits.add(newHabit);
            //mHabitAdapter.notifyDataSetChanged();
            Intent intent = new Intent(this, HabitInfoActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
