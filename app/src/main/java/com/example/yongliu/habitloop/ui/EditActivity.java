package com.example.yongliu.habitloop.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.yongliu.habitloop.R;
import com.example.yongliu.habitloop.adapters.EditAdapter;
import com.example.yongliu.habitloop.models.TempHabits;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EditActivity extends AppCompatActivity {

    @Bind(R.id.editHabitsList) ListView mEditListView;

    EditAdapter mEditAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        mEditAdapter = new EditAdapter(this, TempHabits.mHabits);
        mEditListView.setAdapter(mEditAdapter);
    }

}
