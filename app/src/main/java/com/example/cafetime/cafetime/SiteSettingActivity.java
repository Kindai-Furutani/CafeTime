package com.example.cafetime.cafetime;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by c200 on 16/12/15.
 */

public class SiteSettingActivity extends AppCompatActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_site_setting);

//タイトルバーの文字を変更
		setTitle("サイト設定");

//Activity開始時に設定値を読み込み
		EditText editText = (EditText)findViewById(R.id.EditText);
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		editText.setText(sharedPreferences.getString(Preferences.SaveNum, null), TextView.BufferType.NORMAL);

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
		EditText editText = (EditText)findViewById(R.id.EditText);
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		sharedPreferences.edit().putString(Preferences.SaveNum, editText.getText().toString()).commit();

//確認メッセージの表示
		Toast.makeText(this, editText.getText().toString() + "として保存しました", Toast.LENGTH_LONG).show();

		finish();
	}
}
