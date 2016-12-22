package com.example.cafetime.cafetime;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

	public static String URL = null;

	private ArrayList mItems = new ArrayList();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

//タイトルバーの文字を変更
		setTitle("新着記事");

//ここからRSS取得の設定
		RssListAdapter mAdapter = new RssListAdapter(this, mItems);

		ListView _listview = (ListView)findViewById(R.id.listView1);

//task.executeを同時に複数回呼び出してしまわないよう対策 task.execcuteにsynchronize使えばもう少しスマートになるかもしれない
		RssParserTask task[] = new RssParserTask[3];
		for(int i=0; i<3; i++)
			task[i] = new RssParserTask(this, mAdapter, _listview);

		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

//設定値を取得し、task.executeを呼ぶ
		String num = null;
		for(int i=0; i<3; i++) {
			num = String.valueOf(i);
			task[i].execute(sharedPreferences.getString(num, null)); //task.executeを同時に複数回呼び出してしまわないよう対策
		}
		_listview.setOnItemClickListener(this);

//タイマーサービスを実行
		Intent intent = new Intent(this, TimerService.class);
		startService(intent);
	}
//リストをタップした時
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Item item = (Item)mItems.get(arg2);
/*
		Intent intent = new Intent(this, ItemDetailActivity.class);
		intent.putExtra("TITLE", item.getTitle());
		intent.putExtra("DESCRIPTION", item.getDescription());
*/
		URL = item.getLink().toString();
		Intent intent = new Intent(this, Browser.class);

		startActivity(intent);
	}


//右上の設定ボタン
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
//設定ボタンここまで

//右上から設定画面への遷移設定
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.action_settings) {
			Intent intent = new Intent(this, Preferences.class);
			startActivity(intent);
		}

		return super.onOptionsItemSelected(item);
	}
//設定画面への遷移ここまで
}
