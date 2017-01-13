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
import java.util.Date;
import java.util.GregorianCalendar;

import static java.lang.Boolean.TRUE;
import static java.lang.Boolean.FALSE;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

	public static int UsedTime;
	public static int day;
	public static String URL = "http://www.google.com";
	public static String NowActivity = null;
	public static Intent RunningIntent = null;
	public static Boolean Viewable = FALSE;
	public static Boolean GetRssLock = FALSE;
	public static Boolean BrowserActive = FALSE;
	public static Boolean AppActiv = FALSE;
	public static Boolean ServiceActiv = FALSE;

	private ArrayList mItems = new ArrayList();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AppActiv = TRUE;
		Viewable = FALSE;
		NowActivity = "MainActivity";
		startJudgement();

		setContentView(R.layout.activity_main);

		BrowserActive = FALSE;

		RunningIntent = new Intent(this, PopupNotification.class);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

//タイトルバーの文字を変更
		setTitle("新着記事");

		if(ServiceActiv == FALSE) {
//ストップウォッチサービスを開始
			Intent intent;
			intent = new Intent(this, StopWatchService.class);
			startService(intent);

//タイマーサービスを開始
			intent = new Intent(this, TimerService.class);
			startService(intent);

			Toast.makeText(this, "Started services", Toast.LENGTH_SHORT).show();

			ServiceActiv = TRUE;
		}
		else{
			Toast.makeText(this, "Services has already been starting", Toast.LENGTH_SHORT).show();
		}

//ここからRSS取得の設定
		RssListAdapter mAdapter = new RssListAdapter(this, mItems);

		ListView _listview = (ListView)findViewById(R.id.listView1);

//task.executeを同時に複数回呼び出してしまわないよう複数生成
		RssParserTask task[] = new RssParserTask[3];
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

		for(int i=0; i<3; i++) {
			task[i] = new RssParserTask(this, mAdapter, _listview);
		}

		if(Viewable == FALSE)
			Toast.makeText(this, "閲覧可能時間外", Toast.LENGTH_SHORT).show();
		else {
		//設定値を取得し、task.executeを呼ぶ
			String num = null;
			for (int i = 0, f = 0; i < 3; i++) {
				num = String.valueOf(i);
				if(sharedPreferences.getBoolean("settingUse" + i, FALSE) == TRUE) { //設定で使用するになっている場合のみ読み込む
				//////////////////設定値がnullの場合は実行しないようにしたかったけど効いてない？//////////////////////////
					if(sharedPreferences.getString(num, null) != null) {
						GetRssLock = TRUE;
						task[i].execute(sharedPreferences.getString(num, null)); //task.executeを同時に複数回呼び出してしまわないよう対策
					}
				}
				do{ //RssParserTaskが動作している間はループを回すことで擬似的にロックし、処理が終わったらロックを解除することで表示処理の安定化
					f++;
					if(f > 1000000000) //長過ぎるととりあえずで終了させる
						GetRssLock = FALSE;
				}while(GetRssLock == TRUE);
			}
			_listview.setOnItemClickListener(this);
		}

		Date date = new Date();
		GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
		gc.setGregorianChange(date);
		day = gc.get(Calendar.DATE);

//グラフ更新日が今日でない場合は古いデータをずらす
		if(day != sharedPreferences.getInt("Today", 0)) {
			sharedPreferences.edit().putInt("2dAgo", sharedPreferences.getInt("1dAgo", 0)).commit();
			sharedPreferences.edit().putInt("1dAgo", sharedPreferences.getInt("Today", 0)).commit();
			sharedPreferences.edit().putInt("Today", day).commit();
			sharedPreferences.edit().putInt("2dAgoUse", sharedPreferences.getInt("1dAgoUse", 0)).commit();
			sharedPreferences.edit().putInt("1dAgoUse", sharedPreferences.getInt("TodayUse", 0)).commit();
			sharedPreferences.edit().putInt("TodayUse", 0).commit();
		}
	}
//リストをタップした時
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Item item = (Item)mItems.get(arg2);
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

//設定画面を呼び出し
		if (id == R.id.action_settings) {
			Intent intent = new Intent(this, Preferences.class);
			startActivity(intent);
		}

//画面を再読み込み
		if (id == R.id.action_reload) {
			reload();
		}

//オフライン試験用に追加
		if(id == R.id.action_webView){
			Intent intent = new Intent(this, Browser.class);
			startActivity(intent);
		}

//ポップアップテスト
		if(id == R.id.action_popup){
			Intent intent = new Intent(this, PopupNotification.class);
			startActivity(intent);
		}

		return super.onOptionsItemSelected(item);
	}

//画面を再読み込みするための設定
	public void reload(){
		Intent intent = getIntent();
		overridePendingTransition(0,0);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		finish();

		overridePendingTransition(0,0);
		startActivity(intent);
	}

//起動時に閲覧可能時間内かどうかの判定
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
//DebugMessage
		Toast.makeText(this, "Judge = " + Viewable, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		UsedTime = (StopWatchService.Hor * 60) + StopWatchService.Min;

		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

		if(day != sharedPreferences.getInt("Today", 0)) { //グラフ更新日が今日でない場合はそのまま書き込み
			sharedPreferences.edit().putInt("TodayUse", UsedTime).commit();
		}
		else{
			sharedPreferences.edit().putInt("TodayUse", UsedTime + sharedPreferences.getInt("TodayUse", 0)).commit();
		}
		StopWatchService.Hor = 0;
		StopWatchService.Min = 0;

		Intent intent;

		intent = new Intent(this, StopWatchService.class);
		stopService(intent);

		intent = new Intent(this, TimerService.class);
		stopService(intent);

		ServiceActiv = FALSE;
	}

	@Override
	public void onResume(){
		super.onResume();
		if(AppActiv == FALSE)
			reload();

		AppActiv = TRUE;
	}

	@Override
	public void onPause(){
		super.onPause();
		AppActiv = FALSE;
	}
}
