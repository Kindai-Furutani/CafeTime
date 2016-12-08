package com.example.cafetime.cafetime;

/**
 * Created by c200 on 16/12/08.
 */
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.TimePicker;


public class Preferences extends PreferenceActivity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 設定画面の作成
		addPreferencesFromResource(R.xml.preferences);

		TimePickerDialog timePickerDialog;

		TimePickerDialog.OnTimeSetListener TimeSetListener = new TimePickerDialog.OnTimeSetListener() {
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

				//ログ出力
				Log.d("TimePicker","hourOfDay:" + hourOfDay + " minute:" + minute);
			}
		};

	}
}
