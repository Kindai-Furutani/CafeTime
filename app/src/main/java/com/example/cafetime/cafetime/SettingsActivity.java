//設定画面、ここから各設定に遷移

package com.example.cafetime.cafetime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//追加物(大体自動)
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
//追加物ここまで

public class SettingsActivity extends AppCompatActivity {

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

//タイトルバーの文字を変更する
		setTitle("設定");
//タイトルバーの変更ここまで

//ListViewを追加
		final ListView listView = new ListView(this);
		setContentView(listView);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
//ListViewの中身を追加
		adapter.add("サイト設定");
		adapter.add("平日の通知時間");
		adapter.add("休日の通知時間");
		listView.setAdapter(adapter);
//ListViewの設定ここまで

//ListViewが押された時の動作
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				ListView listView1 = (ListView)adapterView;

//何番目のListViewが押されたかによって開くアクティビティが変わる
				Intent intent;
				switch (position){
					case 0:
						intent = new Intent(getApplicationContext(), SiteSettingsActivity.class);
						startActivity(intent);
						break;

					case 1:
						intent = new Intent(getApplicationContext(), TimeSettingsWeekdayActivity.class);
						startActivity(intent);
						break;

					case 2:
						intent = new Intent(getApplicationContext(), TimeSettingsHolidayActivity.class);
						startActivity(intent);
						break;
				}
			}
		});
//ListViewが押された時の動作ここまで
	}

//終了時、Activityを閉じてMainActivityに戻る
	public void onClick (View v) {
		finish();
	}
}
