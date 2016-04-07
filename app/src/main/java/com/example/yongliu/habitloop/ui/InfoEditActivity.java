package com.example.yongliu.habitloop.ui;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class InfoEditActivity extends AppCompatActivity {

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
    //save delete buttons
    @Bind(R.id.infoEditDeleteButton) Button deleteButton;
    @Bind(R.id.infoEditSaveButton) Button saveButton;

    private CheckBox [] mCheckBoxes;
    private Habit mHabit; //current habit to edit
    private int mIndex; //current habit index

    static final String TAG = InfoEditActivity.class.toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_clear_black_24dp);

        ButterKnife.bind(this);
        setOnclickTimeDialog(pickTimeStartEdit);
        setOnclickTimeDialog(pickTimeEndEdit);
        //check boxes
        mCheckBoxes = new CheckBox[] {monCheck, tueCheck, wedCheck, thuCheck, friCheck,
                satCheck, sunCheck};
        //get the index extra from editActivity to get the habit needed to edit
        Intent intent = getIntent();
        int position = intent.getIntExtra(getString(R.string.EXTRA_HABIT_CLICKED_INDEX), -1);
        if(position != -1) {
            mHabit = TempHabits.mHabits.get(position);
            mIndex = position;
        }
        else{
            Log.e(TAG, getString(R.string.passing_extra_error));
        }

        //set buttons onclick listener
        setOnclickDeleteButton();
        setOnclickSaveButton();
        //set up input infos
        putStartedInputInfos();
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
                mTimePicker = new TimePickerDialog(InfoEditActivity.this, new TimePickerDialog
                        .OnTimeSetListener
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

    public void setOnclickDeleteButton(){
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(InfoEditActivity.this)
                        .setTitle(R.string.dialog_delete_title)
                        .setMessage(R.string.dialog_delete_message)
                        .setPositiveButton(R.string.dialog_positive_button, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                TempHabits.mHabits.remove(mHabit);
                                finish();
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

    }

    public void setOnclickSaveButton(){
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getting the infos from the Views and set it in new habit for storage
                String name = habitNameEditText.getText().toString();
                String startTime = pickTimeStartEdit.getText().toString();
                String endTime = pickTimeEndEdit.getText().toString();
                boolean [] boolDays = getCheckedDays();
                WeekDays days = new WeekDays(boolDays);
                //set the habit chosen to new infos
                TempHabits.mHabits.set(mIndex, new Habit(name, 0, startTime, endTime, days));

                finish();
            }
        });
    }

    //informations on the habit put it in the inputs
    private void putStartedInputInfos(){
        boolean [] daysChecked = mHabit.getDays().getDayBools();
        habitNameEditText.setText(mHabit.getHabitName());
        pickTimeStartEdit.setText(mHabit.getStartTime());
        pickTimeEndEdit.setText(mHabit.getEndTime());
        for(int i=0; i< mCheckBoxes.length; i++){
            if(daysChecked[i]){
                mCheckBoxes[i].setChecked(true);
            }
        }
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



}
