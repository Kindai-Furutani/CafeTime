package com.example.cafetime.cafetime;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by c200 on 17/01/12.
 */

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class GraphViewActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graph_view);

		BarChart barChart = (BarChart) findViewById(R.id.chart);

		// HorizontalBarChart barChart= (HorizontalBarChart) findViewById(R.id.chart);

		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

		ArrayList<BarEntry> entries = new ArrayList<>();
		entries.add(new BarEntry(sharedPreferences.getInt("2dAgoUse", 0), 0)); //2dAgo
		entries.add(new BarEntry(sharedPreferences.getInt("1dAgoUse", 0), 1)); //1dAgo
		entries.add(new BarEntry(sharedPreferences.getInt("TodayUse", 0), 2)); //today

		BarDataSet dataset = new BarDataSet(entries, "# of Calls");

		ArrayList<String> labels = new ArrayList<String>();
		labels.add(String.valueOf(sharedPreferences.getInt("2dAgo", 0)) + "日");
		labels.add(String.valueOf(sharedPreferences.getInt("1dAgo", 0)) + "日");
		labels.add(String.valueOf(sharedPreferences.getInt("Today", 0)) + "日");

        /* for create Grouped Bar chart
        ArrayList<BarEntry> group1 = new ArrayList<>();
        group1.add(new BarEntry(4f, 0));
        group1.add(new BarEntry(8f, 1));
        group1.add(new BarEntry(6f, 2));
        group1.add(new BarEntry(12f, 3));
        group1.add(new BarEntry(18f, 4));
        group1.add(new BarEntry(9f, 5));

        ArrayList<BarEntry> group2 = new ArrayList<>();
        group2.add(new BarEntry(6f, 0));
        group2.add(new BarEntry(7f, 1));
        group2.add(new BarEntry(8f, 2));
        group2.add(new BarEntry(12f, 3));
        group2.add(new BarEntry(15f, 4));
        group2.add(new BarEntry(10f, 5));

        BarDataSet barDataSet1 = new BarDataSet(group1, "Group 1");
        //barDataSet1.setColor(Color.rgb(0, 155, 0));
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

        BarDataSet barDataSet2 = new BarDataSet(group2, "Group 2");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<BarDataSet> dataset = new ArrayList<>();
        dataset.add(barDataSet1);
        dataset.add(barDataSet2);
        */

		BarData data = new BarData(labels, dataset);
		// dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
		barChart.setData(data);
		barChart.animateY(5000);
	}

	@Override
	public void onPause(){
		super.onPause();
		MainActivity.AppActiv = FALSE;
	}

	@Override
	public void onResume(){
		super.onResume();
		MainActivity.AppActiv = TRUE;
	}
}
