package com.example.cafetime.cafetime;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
/**
 * Created by c200 on 16/12/22.
 */

public class TimerService extends Service{
	private Timer mTimer = null;
	Handler mHandler = new Handler();
	SharedPreferences sharedPreferences;
	Calendar calendar = Calendar.getInstance();
	int TimerHour1;
	int TimerMinute1;
	int TimerHour2;
	int TimerMinute2;
	int NowHour;
	int NowMinute;

	@Override
	public void onCreate() {
//		Log.i("TestService", "onCreate");
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
//		Log.i("TestService", "onStartCommand");
		// タイマーの設定 1秒毎にループ
		mTimer = new Timer(true);
		mTimer.schedule( new TimerTask(){
			@Override
			public void run(){
				mHandler.post( new Runnable(){
					public void run(){
//						Log.d( "TestService" , "Timer run" );
						TimerHour1 = sharedPreferences.getInt("4Hour", 0);
						TimerMinute1 = sharedPreferences.getInt("4Minute", 0);
						TimerHour2 = sharedPreferences.getInt("5Hour", 0);
						TimerMinute2 = sharedPreferences.getInt("5Minute", 0);

						NowHour = calendar.get(Calendar.HOUR_OF_DAY);
						NowMinute = calendar.get(Calendar.MINUTE);

						if(NowHour == TimerHour1){
							if(NowMinute == TimerMinute1){
								//callPopUp
							}
						}
						else if(NowHour == TimerHour2){
							if(NowMinute == TimerMinute2) {
								//callPopUp
							}
						}
						Toast.makeText(TimerService.this, "BG-TimerTest", Toast.LENGTH_SHORT).show();
					}
				});
			}
		}, 30000, 30000);

		return START_STICKY;
	}

/*
	@Override
	public void onDestroy() {
		Log.i("TestService", "onDestroy");

		// タイマー停止
		if( mTimer != null ){
			mTimer.cancel();
			mTimer = null;
		}
		Toast.makeText(this, "MyService onDestroy", Toast.LENGTH_SHORT).show();
	}
*/

	@Override
	public IBinder onBind(Intent arg0) {
//		Log.i("TestService", "onBind");
		return null;
	}
}
