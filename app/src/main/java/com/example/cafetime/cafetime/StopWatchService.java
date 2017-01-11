package com.example.cafetime.cafetime;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by c200 on 16/12/22.
 */

public class StopWatchService extends Service{
	private Timer mTimer = null;
	Handler mHandler = new Handler();
	public static int Sec = 0;
	public static int Min = 0;
	public static int Hor = 0;

	@Override
	public void onCreate() {
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "StopWatch Started", Toast.LENGTH_SHORT).show();

		// タイマーの設定
		mTimer = new Timer(true);
		mTimer.schedule( new TimerTask(){
			@Override
			public void run(){
				mHandler.post( new Runnable(){
					public void run(){
						Min++;
						if(Min>=60){
							Min = 0;
							Hor++;
						}
						//Browser.setTime(Hor, Min, Sec);

						if(Min%15 == 0) { //とりあえず15分毎にポップアップ呼び出す設定
							Toast.makeText(StopWatchService.this, "使用開始から" + Hor + "時間" + Min + "分が経過しました", Toast.LENGTH_SHORT).show();
							PopupNotification.CalledBy = "StopWatchService";

						//現在起動しているActivityからポップアップを呼び出す
							MainActivity.RunningIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(MainActivity.RunningIntent);

						}
					}
				});
			}
		}, 500, 60000); //起動後ディレイ, 実行間隔 ms

		return START_STICKY;
	}

	@Override
	public void onDestroy() {

		// タイマー停止
		if( mTimer != null ){
			mTimer.cancel();
			mTimer = null;
		}
		Toast.makeText(this, "StopWatch onDestroy", Toast.LENGTH_SHORT).show();
	}


	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
}
