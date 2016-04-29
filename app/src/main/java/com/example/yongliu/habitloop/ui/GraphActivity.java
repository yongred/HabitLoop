package com.example.yongliu.habitloop.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        sixMonthGraph();

    }

    private void sixMonthGraph(){

        Habit hb = TempHabits.mHabits.get(0);
        Calendar currentCal = Calendar.getInstance();

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

}
