//サイト設定のアクティビティ

package com.example.cafetime.cafetime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//追加物(大体自動)
import android.view.View;
//ここまで追加物

public class SiteSettingsActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_site_settings);

//タイトル設定
		setTitle("サイト設定");
//ここまでタイトル設定
	}


//終了時、Activityを閉じてMainActivityに戻る
	public void onClick (View v) {
		finish();
	}
}
