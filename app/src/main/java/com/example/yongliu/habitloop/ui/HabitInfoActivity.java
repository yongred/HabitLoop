package com.example.yongliu.habitloop.ui;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.yongliu.habitloop.R;
import com.example.yongliu.habitloop.models.Habit;
import com.example.yongliu.habitloop.models.TempHabits;
import com.example.yongliu.habitloop.models.WeekDays;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HabitInfoActivity extends AppCompatActivity {
    //habit name needs to save
    @Bind(R.id.habitNameInput) EditText habitNameEditText;

    @Bind(R.id.timeLabel) TextView timeLabelTextView;
    @Bind(R.id.daysLabel) TextView daysLabelTextView;
    //time needs to save
    @Bind(R.id.pickTimeStart) EditText pickTimeStartEdit;
    @Bind(R.id.pickTimeEnd) EditText pickTimeEndEdit;
    //checkbox value needs save
    @Bind(R.id.mondayCheck) CheckBox monCheck;
    @Bind(R.id.tuesdayCheck) CheckBox tueCheck;
    @Bind(R.id.wednesdayCheck) CheckBox wedCheck;
    @Bind(R.id.thursdayCheck) CheckBox thuCheck;
    @Bind(R.id.fridayCheck) CheckBox friCheck;
    @Bind(R.id.saturdayCheck) CheckBox satCheck;
    @Bind(R.id.sundayCheck) CheckBox sunCheck;

    private CheckBox [] mCheckBoxes;

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

        mCheckBoxes = new CheckBox[] {monCheck, tueCheck, wedCheck, thuCheck, friCheck,
                satCheck, sunCheck};
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_habit_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) { //saving data for new habit
            //getting the infos from the Views and set it in new habit for storage
            String name = habitNameEditText.getText().toString();
            String time = pickTimeStartEdit.getText().toString() + " - "
                    + pickTimeEndEdit.getText().toString();
            Boolean [] boolDays = getCheckedDays();
            WeekDays days = new WeekDays(boolDays);
            Habit hb = new Habit(name, 0, time, days);

            TempHabits.mHabits.add(hb);

            finish();
        }

        return super.onOptionsItemSelected(item);
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

    private Boolean [] getCheckedDays(){
        Boolean [] checks = {false, false, false, false, false, false, false};
        for(int i = 0; i< checks.length; i++){
            if(mCheckBoxes[i].isChecked()){
                checks[i] = true;
            }
        }

        return checks;
    }

}
