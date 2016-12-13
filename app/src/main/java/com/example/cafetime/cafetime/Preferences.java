package com.example.cafetime.cafetime;

/**
 * Created by c200 on 16/12/08.
 */
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.TimePicker;


public class Preferences extends PreferenceActivity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 設定画面の作成
		addPreferencesFromResource(R.xml.preferences);

/*
		TimePickerDialog timePickerDialog;

		TimePickerDialog.OnTimeSetListener TimeSetListener = new TimePickerDialog.OnTimeSetListener() {
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

				//ログ出力
				Log.d("TimePicker","hourOfDay:" + hourOfDay + " minute:" + minute);
			}
		};
*/

/*
		EditText editText = (EditText)findViewById(R.id.urlData1);
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		sharedPreferences.edit().putString("SaveString", editText.getText().toString()).commit();
*/

	}
}
