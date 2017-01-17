package com.example.cafetime.cafetime;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class SiteSettingActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

	public ToggleButton toggleButton;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_site_setting);

		MainActivity.RunningIntent = new Intent(this, PopupNotification.class);

//タイトルバーの文字を変更
		setTitle("サイト設定" + (Integer.parseInt(Preferences.SaveNum) + 1));

//Activity開始時に設定値を読み込み
		EditText editText = (EditText)findViewById(R.id.EditText);
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		editText.setText(sharedPreferences.getString(Preferences.SaveNum, null), TextView.BufferType.NORMAL);

//とりあえず見かけだけスイッチ配置してインスタンス化
		toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
		toggleButton.setChecked(sharedPreferences.getBoolean("settingUse" + Preferences.SaveNum, FALSE));
		toggleButton.setOnCheckedChangeListener(this);

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

//確認メッセージの表示
		Toast.makeText(this, editText.getText().toString() + "として保存しました", Toast.LENGTH_LONG).show();

		finish();
	}

//ToggleButtonが押された時に呼び出される
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
		toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		sharedPreferences.edit().putBoolean(("settingUse" + Preferences.SaveNum), isChecked).commit();

//確認メッセージの表示
//		Toast.makeText(this, String.valueOf(isChecked), Toast.LENGTH_LONG).show();
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

	@Override
	public void onDestroy(){
		super.onDestroy();

		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		if(sharedPreferences.getString(Preferences.SaveNum, null) == null || sharedPreferences.getString(Preferences.SaveNum, null).length() < 9)
			sharedPreferences.edit().putBoolean(("settingUse" + Preferences.SaveNum), FALSE).commit();
	}
}
