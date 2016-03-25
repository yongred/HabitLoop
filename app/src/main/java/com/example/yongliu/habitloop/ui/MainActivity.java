package com.example.yongliu.habitloop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.yongliu.habitloop.R;
import com.example.yongliu.habitloop.adapters.HabitAdapter;
import com.example.yongliu.habitloop.models.Habit;
import com.example.yongliu.habitloop.models.TempHabits;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.mainHabitsList) ListView mHabitListView;
    HabitAdapter mHabitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_dehaze_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

        ButterKnife.bind(this);

        mHabitAdapter = new HabitAdapter(this, TempHabits.mHabits);
        mHabitListView.setAdapter(mHabitAdapter);

        Habit h1 = new Habit("habit1", 2, "5:00PM - 9:00PM", "Tue Thr Fri Sat");
        Habit h2 = new Habit("habit2", 4, "1:00AM - 2:00PM", "Tue Fri Sun");
        TempHabits.mHabits.add(h1);
        TempHabits.mHabits.add(h2);
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

        else if (id == R.id.action_check_statistics) {

            Intent intent = new Intent(this, GraphActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}
