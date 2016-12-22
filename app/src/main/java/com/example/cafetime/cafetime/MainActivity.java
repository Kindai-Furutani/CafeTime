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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import static java.lang.Boolean.TRUE;
import static java.lang.Boolean.FALSE;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

	public static String URL = null;
	public static Boolean Viewable = FALSE;

	private ArrayList mItems = new ArrayList();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Viewable = FALSE;
		startJudgement();

		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

//タイトルバーの文字を変更
		setTitle("新着記事");

//タイマーサービスを実行
//		Intent intent = new Intent(this, TimerService.class);
//		startService(intent);


//ここからRSS取得の設定
		RssListAdapter mAdapter = new RssListAdapter(this, mItems);

		ListView _listview = (ListView)findViewById(R.id.listView1);

//task.executeを同時に複数回呼び出してしまわないよう対策 task.execcuteにsynchronize使えばもう少しスマートになるかもしれない
		RssParserTask task[] = new RssParserTask[3];
		for(int i=0; i<3; i++)
			task[i] = new RssParserTask(this, mAdapter, _listview);

		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

		if(Viewable == FALSE)
			Toast.makeText(this, "閲覧可能時間外", Toast.LENGTH_SHORT).show();
		else {
//設定値を取得し、task.executeを呼ぶ
			String num = null;
			for (int i = 0; i < 3; i++) {
				num = String.valueOf(i);
				task[i].execute(sharedPreferences.getString(num, null)); //task.executeを同時に複数回呼び出してしまわないよう対策
			}
			_listview.setOnItemClickListener(this);
		}
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

		if (id == R.id.action_reload) {
			reload();
		}

		return super.onOptionsItemSelected(item);
	}

	public void reload(){
		Intent intent = getIntent();
		overridePendingTransition(0,0);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		finish();

		overridePendingTransition(0,0);
		startActivity(intent);
	}

	public void startJudgement(){
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		Calendar calendar = Calendar.getInstance();

		int StartTime1 = sharedPreferences.getInt("3StartTime", 0);
		int StartTime2 = sharedPreferences.getInt("4StartTime", 0);
		int EndTime1 = sharedPreferences.getInt("3EndTime", 0);
		int EndTime2 = sharedPreferences.getInt("4EndTime", 0);

		int NowHour = calendar.get(Calendar.HOUR_OF_DAY);
		int NowMinute = calendar.get(Calendar.MINUTE);
		int NowTime = NowHour*100 + NowMinute;

		if(NowTime > StartTime1)
			if(NowTime < EndTime1)
				Viewable = TRUE;

		if(NowTime > StartTime2)
			if(NowTime < EndTime2)
				Viewable = TRUE;

		Toast.makeText(this, "Judge = " + Viewable, Toast.LENGTH_SHORT).show();
	}
}
