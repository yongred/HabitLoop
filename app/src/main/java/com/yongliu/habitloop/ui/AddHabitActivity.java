package com.yongliu.habitloop.ui;

import android.app.AlertDialog;
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

import com.yongliu.habitloop.R;
import com.yongliu.habitloop.models.Habit;
import com.yongliu.habitloop.models.Storage;
import com.yongliu.habitloop.models.WeekDays;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddHabitActivity extends AppCompatActivity {
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

    //input values from views
    private String mHabitName;
    private String mStartTime;
    private String mEndTime;
    private boolean [] mCheckDays;

    //Storage
    private Storage mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_clear_black_24dp);

        ButterKnife.bind(this);
        setOnclickTimeDialog(pickTimeStartEdit);
        setOnclickTimeDialog(pickTimeEndEdit);

        mStorage = new Storage(this);

        mCheckBoxes = new CheckBox[] {monCheck, tueCheck, wedCheck, thuCheck, friCheck,
                satCheck, sunCheck};
    }

    @Override
    protected void onPause() {
        super.onPause();
        mStorage.saveToInternalStorage(Storage.mHabits);
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
            if(checkInfoError()) {
                WeekDays days = new WeekDays(mCheckDays);
                Habit hb = new Habit(mHabitName, 0, mStartTime, mEndTime, days);

                Storage.mHabits.add(hb);
                mStorage.saveToInternalStorage(Storage.mHabits);
                finish();
            }
            else{
                //do nothing
            }
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
                mTimePicker = new TimePickerDialog(AddHabitActivity.this, new TimePickerDialog.OnTimeSetListener
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

    //return which box for weekdays is checked
    private boolean [] getCheckedDays(){
        boolean [] checks = {false, false, false, false, false, false, false};
        for(int i = 0; i< checks.length; i++){
            if(mCheckBoxes[i].isChecked()){
                checks[i] = true;
            }
        }

        return checks;
    }

    //
    private boolean checkInfoError() {
        boolean allCorrect = true;
        mHabitName = habitNameEditText.getText().toString();
        mStartTime = pickTimeStartEdit.getText().toString();
        mEndTime = pickTimeEndEdit.getText().toString();
        mCheckDays = getCheckedDays();

        if(mHabitName.matches("")){
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(R.string.dialog_name_empty_title)
                    .setMessage(R.string.dialog_name_empty_message)
                    .setPositiveButton(R.string.dialog_ok_button, null)
                    .show();
            allCorrect = false;
        }

        else if(!mStartTime.matches("") && !mEndTime.matches("")){
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            try {
                Date startTime = sdf.parse(mStartTime);
                Date endTime = sdf.parse(mEndTime);
                if(startTime.compareTo(endTime) == 1){
                    AlertDialog dialog = new AlertDialog.Builder(this)
                            .setTitle(R.string.dialog_time_error_title)
                            .setMessage(R.string.dialog_time_error_message)
                            .setPositiveButton(R.string.dialog_ok_button, null)
                            .show();
                    allCorrect = false;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(mStartTime.matches("")){
            mStartTime = "Unset Time";
        }

        if(mEndTime.matches("")){
            mEndTime = "Unset Time";
        }

        return allCorrect;
    }

}
