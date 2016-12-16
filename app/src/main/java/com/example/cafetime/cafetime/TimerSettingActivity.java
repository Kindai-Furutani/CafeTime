package com.example.cafetime.cafetime;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

/**
 * Created by c200 on 16/12/16.
 */

public class TimerSettingActivity extends AppCompatActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timer_setting);

		Button saveButton = (Button)findViewById(R.id.SaveButton);
		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				saveButtonClick();
			}
		});

	}

	private void saveButtonClick() {
		// 保存
		TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

		finish();
	}
}
