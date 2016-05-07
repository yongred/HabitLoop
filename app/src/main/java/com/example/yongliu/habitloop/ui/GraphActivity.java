package com.example.yongliu.habitloop.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yongliu.habitloop.R;
import com.example.yongliu.habitloop.models.Habit;
import com.example.yongliu.habitloop.models.TempHabits;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GraphActivity extends AppCompatActivity {

    @Bind(R.id.sixMonthGraph) GraphView monthGraph;
    @Bind(R.id.sixWeekGraph) GraphView weekGraph;
    @Bind(R.id.leftArrowButton) Button leftButton;
    @Bind(R.id.rightArrowButton) Button rightButton;
    @Bind(R.id.graphHabitTextView) TextView habitTileTextview;
    private int mHabitIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        mHabitIndex =0;
        habitTileTextview.setText(TempHabits.mHabits.get(mHabitIndex).getHabitName());

        setupOnclickListeners();
        sixMonthGraph();
        sixWeekGraph();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //sixMonthGraph();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //sixMonthGraph();
    }

    private void sixWeekGraph(){

        Habit hb = TempHabits.mHabits.get(mHabitIndex);
        Calendar currentCal = Calendar.getInstance();
        weekGraph.removeAllSeries();

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(weekGraph);
        staticLabelsFormatter.setHorizontalLabels(new String[]{
                "--",
                "-5w", "-4w", "-3w",
                "-2w", "-1w", "w",
                "--"
        });
        weekGraph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        DataPoint point0 = new DataPoint(0,0);
        DataPoint [] compPoints = new DataPoint[7];
        DataPoint [] incompPoints = new DataPoint[7];
        compPoints[0] = point0;
        incompPoints[0] = point0;

        currentCal.add(Calendar.WEEK_OF_YEAR, -5);
        for(int i=1; i<= 6; i++){
            int compCount = 0;
            int incompCount = 0;
            compCount = hb.weeklyComplete(currentCal.getTime());
            compPoints[i] = new DataPoint(i, compCount);
            incompCount = hb.weeklyIncomplete(currentCal.getTime());
            incompPoints[i] = new DataPoint(i, incompCount);
            //increments
            currentCal.add(Calendar.WEEK_OF_YEAR, 1);
        }

        BarGraphSeries<DataPoint> compSeries = new BarGraphSeries<DataPoint>(compPoints);
        BarGraphSeries<DataPoint> incompSeries = new BarGraphSeries<DataPoint>(incompPoints);
        compSeries.setColor(Color.GREEN);
        incompSeries.setColor(Color.RED);
        weekGraph.addSeries(compSeries);
        weekGraph.addSeries(incompSeries);
    }

    private void sixMonthGraph(){

        Habit hb = TempHabits.mHabits.get(mHabitIndex);
        Calendar currentCal = Calendar.getInstance();
        monthGraph.removeAllSeries();

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(monthGraph);
        staticLabelsFormatter.setHorizontalLabels(new String[]{
                "--",
                "-5m", "-4m", "-3m",
                "-2m", "-1m", "m",
                "--"
        });
        monthGraph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        DataPoint point0 = new DataPoint(0,0);
        DataPoint [] compPoints = new DataPoint[7];
        DataPoint [] incompPoints = new DataPoint[7];
        compPoints[0] = point0;
        incompPoints[0] = point0;

        currentCal.add(Calendar.MONTH, -5);
        for(int i=1; i<= 6; i++){
            int compCount = 0;
            int incompCount = 0;
            compCount = hb.monthlyComplete(currentCal.getTime());
            compPoints[i] = new DataPoint(i, compCount);
            incompCount = hb.monthlyIncomplete(currentCal.getTime());
            incompPoints[i] = new DataPoint(i, incompCount);
            //increments
            currentCal.add(Calendar.MONTH, 1);
        }

        BarGraphSeries<DataPoint> compSeries = new BarGraphSeries<DataPoint>(compPoints);
        BarGraphSeries<DataPoint> incompSeries = new BarGraphSeries<DataPoint>(incompPoints);
        compSeries.setColor(Color.GREEN);
        incompSeries.setColor(Color.RED);
        monthGraph.addSeries(compSeries);
        monthGraph.addSeries(incompSeries);
    }

    private void setupOnclickListeners(){
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mHabitIndex > 0) {
                    mHabitIndex--;
                }
                else{
                    mHabitIndex = TempHabits.mHabits.size() -1;
                }
                habitTileTextview.setText(TempHabits.mHabits.get(mHabitIndex).getHabitName());
                sixMonthGraph();
                sixWeekGraph();
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mHabitIndex < TempHabits.mHabits.size() -1) {
                    mHabitIndex++;
                }
                else{
                    mHabitIndex = 0;
                }
                habitTileTextview.setText(TempHabits.mHabits.get(mHabitIndex).getHabitName());
                sixMonthGraph();
                sixWeekGraph();
            }
        });
    }

}
