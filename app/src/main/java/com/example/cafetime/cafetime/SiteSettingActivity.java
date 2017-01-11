package com.example.cafetime.cafetime;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by c200 on 16/12/15.
 */

public class SiteSettingActivity extends AppCompatActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_site_setting);

//タイトルバーの文字を変更
		setTitle("サイト設定" + (Preferences.SaveNum+1));

//Activity開始時に設定値を読み込み
		EditText editText = (EditText)findViewById(R.id.EditText);
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		editText.setText(sharedPreferences.getString(Preferences.SaveNum, null), TextView.BufferType.NORMAL);

//とりあえず見かけだけスイッチ配置してインスタンス化
		ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

//セーブボタンにアクションを設定
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

		ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
//		sharedPreferences.edit().putBoolean(("settingUse" + Preferences.SaveNum), toggleSwitch.);

//確認メッセージの表示
		Toast.makeText(this, editText.getText().toString() + "として保存しました", Toast.LENGTH_LONG).show();

		finish();
	}
}
