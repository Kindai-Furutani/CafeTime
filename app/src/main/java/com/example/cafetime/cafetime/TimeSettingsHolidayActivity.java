//時間設定のアクティビティ

package com.example.cafetime.cafetime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//追加物(大体自動)
import android.view.View;
//追加物ここまで

public class TimeSettingsHolidayActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_settings);

		setTitle("休日の通知時間");
	}

//終了時、Activityを閉じてMainActivityに戻る
	public void onClick (View v) {
		finish();
	}
}
