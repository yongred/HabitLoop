package com.example.yongliu.habitloop.ui;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.yongliu.habitloop.R;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HabitInfoActivity extends AppCompatActivity {

    @Bind(R.id.habitNameInput) EditText habitNameEditText;
    @Bind(R.id.timeLabel) TextView timeLabelTextView;
    @Bind(R.id.pickTimeStart) EditText pickTimeStartEdit;
    @Bind(R.id.pickTimeEnd) EditText pickTimeEndEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_clear_black_24dp);

        ButterKnife.bind(this);
        setOnclickTimeDialog(pickTimeStartEdit);
        setOnclickTimeDialog(pickTimeEndEdit);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_habit_info, menu);
        return true;
    }

    public void setOnclickTimeDialog(final EditText timeEdit){
        timeEdit.setFocusable(false);
        timeEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(HabitInfoActivity.this, new TimePickerDialog.OnTimeSetListener
                        () {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timeEdit.setText( selectedHour + ":" + String.format("%02d",
                                selectedMinute) ); //01, 02... 2 digit representation for mins
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }

}
