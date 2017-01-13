package com.example.cafetime.cafetime;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class GraphViewActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graph_view);

		BarChart barChart = (BarChart) findViewById(R.id.chart);


		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

		ArrayList<BarEntry> entries = new ArrayList<>();
		entries.add(new BarEntry(sharedPreferences.getInt("2dAgoUse", 0), 0)); //2dAgo
		entries.add(new BarEntry(sharedPreferences.getInt("1dAgoUse", 0), 1)); //1dAgo
		entries.add(new BarEntry(sharedPreferences.getInt("TodayUse", 0), 2)); //today

		BarDataSet dataset = new BarDataSet(entries, "使用時間(分)");

		ArrayList<String> labels = new ArrayList<String>();
		labels.add(String.valueOf(sharedPreferences.getInt("2dAgo", 0)) + "日");
		labels.add(String.valueOf(sharedPreferences.getInt("1dAgo", 0)) + "日");
		labels.add(String.valueOf(sharedPreferences.getInt("Today", 0)) + "日");

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
