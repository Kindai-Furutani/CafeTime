package com.example.cafetime.cafetime;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class EndTimerSettingActivity extends AppCompatActivity {

	TimePicker timePicker;
	String EndTime = Preferences.SaveNum + "EndTime";
	String EndHour = Preferences.SaveNum + "EndHour";
	String EndMinute = Preferences.SaveNum + "EndMinute";
	int Hour;
	int Minute;
	int Time;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = new Intent(this, StartTimerSettingActivity.class);
		startActivity(intent);

		setContentView(R.layout.activity_timer_setting);

		MainActivity.RunningIntent = new Intent(this, PopupNotification.class);

//タイトルバーの文字を変更
		setTitle("閲覧可能時刻(終了)");

		timePicker = (TimePicker)findViewById(R.id.timePicker);

		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		timePicker.setCurrentHour(sharedPreferences.getInt(EndHour, 0));
		timePicker.setCurrentMinute(sharedPreferences.getInt(EndMinute, 0));

		Button saveButton = (Button)findViewById(R.id.SaveButton);
		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				saveButtonClick();
			}
		});

	}

	private void saveButtonClick() {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		Hour = timePicker.getCurrentHour(); //時を取得
		Minute = timePicker.getCurrentMinute(); //分を取得
//TimePicker呼び出すときのために時と分を別々に保存
		sharedPreferences.edit().putInt(EndHour, Hour).commit();
		sharedPreferences.edit().putInt(EndMinute, Minute).commit();

//時間判定しやすくするために時*100+分の3または4桁で保存
		Time = Hour*100 + Minute;
		sharedPreferences.edit().putInt(EndTime, Time).commit();

//確認メッセージの表示
		Toast.makeText(this, "閲覧可能終了時刻を" + Hour + "時" + Minute + "分に設定しました", Toast.LENGTH_LONG).show();

//DebugMessage
//		Toast.makeText(this, "EndTime = " + sharedPreferences.getInt(EndTime, 99999), Toast.LENGTH_LONG).show();
		finish();
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
