package com.yongliu.habitloop.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yongliu.habitloop.R;
import com.yongliu.habitloop.adapters.HabitAdapter;
import com.yongliu.habitloop.models.Storage;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.mainHabitsList) ListView mHabitListView;
    HabitAdapter mHabitAdapter;
    Storage mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_toolbar_edit_48);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

        ButterKnife.bind(this);
        mStorage = new Storage(this);

        mHabitAdapter = new HabitAdapter(this, Storage.mHabits);
        mHabitListView.setAdapter(mHabitAdapter);

        setListViewClickListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Storage.mHabits = mStorage.readFromInternalStorage();
        mHabitAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Storage.mHabits = mStorage.readFromInternalStorage();
        mHabitAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mStorage.saveToInternalStorage(Storage.mHabits);
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
            Intent intent = new Intent(this, AddHabitActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.action_check_statistics) {
            if(Storage.mHabits.isEmpty()){
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle(R.string.dialog_empty_habit_title)
                        .setMessage(R.string.dialog_empty_habit_message)
                        .setPositiveButton(R.string.dialog_ok_button, null)
                        .show();

            }
            else {
                Intent intent = new Intent(this, GraphActivity.class);
                startActivity(intent);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void setListViewClickListener(){
        mHabitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                intent.putExtra(getString(R.string.EXTRA_HABIT_CLICKED_INDEX), position);
                startActivity(intent);
            }
        });
    }

}
