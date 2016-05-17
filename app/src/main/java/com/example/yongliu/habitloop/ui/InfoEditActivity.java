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
import com.example.yongliu.habitloop.models.Storage;
import com.example.yongliu.habitloop.models.WeekDays;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    private Storage mStorage;

    //editText view input values for error checking, editing and saving
    private String mHabitName;
    private String mStartTime;
    private String mEndTime;
    private boolean [] mCheckDays;

    static final String TAG = InfoEditActivity.class.toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_clear_black_24dp);

        ButterKnife.bind(this);
        setOnclickTimeDialog(pickTimeStartEdit);
        setOnclickTimeDialog(pickTimeEndEdit);

        mStorage = new Storage(this);
        //check boxes
        mCheckBoxes = new CheckBox[] {monCheck, tueCheck, wedCheck, thuCheck, friCheck,
                satCheck, sunCheck};
        //get the index extra from editActivity to get the habit needed to edit
        Intent intent = getIntent();
        int position = intent.getIntExtra(getString(R.string.EXTRA_HABIT_CLICKED_INDEX), -1);
        if(position != -1) {
            mHabit = Storage.mHabits.get(position);
            mIndex = position;
        }
        else{
            Log.e(TAG, getString(R.string.passing_extra_error));
        }
        //set actionbar title to name of habit
        this.setTitle(mHabit.getHabitName());
        //set buttons onclick listener
        setOnclickDeleteButton();
        setOnclickSaveButton();
        //set up input infos
        putStartedInputInfos();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mStorage.saveToInternalStorage(Storage.mHabits);
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
                                Storage.mHabits.remove(mHabit);
                                mStorage.saveToInternalStorage(Storage.mHabits);
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
                Habit targetHb = Storage.mHabits.get(mIndex);
                //getting the infos from the Views and set it in new habit for storage
                if(checkInfoError()) {//true no error

                    WeekDays days = new WeekDays(mCheckDays);
                    //set the habit chosen to new infos
                    targetHb.setHabitName(mHabitName);
                    targetHb.setStartTime(mStartTime);
                    targetHb.setEndTime(mEndTime);
                    targetHb.setDays(days);

                    mStorage.saveToInternalStorage(Storage.mHabits);
                    finish();
                }
                else{
                    // do nothing
                }
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

    //check for text edit view empty, compare time
    private boolean checkInfoError() { //true no error, false error
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

        else if(!mStartTime.matches("") && !mEndTime.matches("")
                && !mStartTime.matches("Unset Time") && !mEndTime.matches("Unset Time")){
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
