package com.example.cafetime.cafetime;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by c200 on 16/12/16.
 */

public class TimerSettingActivity extends AppCompatActivity {

	TimePicker timePicker;
	String SaveHour = Preferences.SaveNum + "Hour";
	String SaveMinute = Preferences.SaveNum + "Minute";


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timer_setting);

//タイトルバーの文字を変更
		setTitle("時間設定");

		timePicker = (TimePicker)findViewById(R.id.timePicker);

		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		timePicker.setCurrentHour(sharedPreferences.getInt(SaveHour, 0));
		timePicker.setCurrentMinute(sharedPreferences.getInt(SaveMinute, 0));

		Button saveButton = (Button)findViewById(R.id.SaveButton);
		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				saveButtonClick();
			}
		});

	}

	private void saveButtonClick() {
//保存
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		sharedPreferences.edit().putInt(SaveHour, timePicker.getCurrentHour()).commit();
		sharedPreferences.edit().putInt(SaveMinute, timePicker.getCurrentMinute()).commit();

//確認メッセージの表示
		Toast.makeText(this, timePicker.getCurrentHour() + "時" + timePicker.getCurrentMinute() + "分にタイマーを設定しました", Toast.LENGTH_LONG).show();

		finish();
	}
}
