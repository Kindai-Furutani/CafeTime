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

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by c200 on 16/12/22.
 */

public class TimerService extends Service{
	private Timer mTimer = null;
	Handler mHandler = new Handler();
	SharedPreferences sharedPreferences;
	Calendar calendar = Calendar.getInstance();
	int StartTime1;
	int StartTime2;
	int EndTime1;
	int EndTime2;
	int NowHour;
	int NowMinute;
	int NowTime;

	@Override
	public void onCreate() {
//		Log.i("TestService", "onCreate");
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
//		Log.i("TestService", "onStartCommand");
		// タイマーの設定
		mTimer = new Timer(true);
		mTimer.schedule( new TimerTask(){
			@Override
			public void run(){
				mHandler.post( new Runnable(){
					public void run(){
//						Log.d( "TestService" , "Timer run" );
						StartTime1 = sharedPreferences.getInt("3StartTime", 0);
						StartTime2 = sharedPreferences.getInt("4StartTime", 0);
						EndTime1 = sharedPreferences.getInt("3EndTime", 0);
						EndTime2 = sharedPreferences.getInt("4EndTime", 0);

						NowHour = calendar.get(Calendar.HOUR_OF_DAY);
						NowMinute = calendar.get(Calendar.MINUTE);
						NowTime = NowHour*100 + NowMinute;
						MainActivity.Viewable = FALSE;

					//DebugMessage
						Toast.makeText(TimerService.this, "StartTime1 = " + StartTime1
								+ "\nEndTime1 = " + EndTime1
								+ "\nStartTime2 = " + StartTime2
								+ "\nEndTime2 = " + EndTime2, Toast.LENGTH_SHORT).show();
						Toast.makeText(TimerService.this, "NowHour = " + NowHour
								+ "\nNowMinute = " + NowMinute
								+ "\nNowTime = " + NowTime, Toast.LENGTH_SHORT).show();
						Toast.makeText(TimerService.this, "Viewable = " + MainActivity.Viewable, Toast.LENGTH_SHORT).show();

						if(NowTime > StartTime1){
							if(NowTime < EndTime1){
								MainActivity.Viewable = TRUE;
							//DebugMessage
								Toast.makeText(TimerService.this, "Start1 < Now < End1", Toast.LENGTH_SHORT).show();
								Toast.makeText(TimerService.this, "Viewable = " + MainActivity.Viewable, Toast.LENGTH_SHORT).show();
							}
						}
						if(NowTime > StartTime2){
							if(NowTime < EndTime2){
								MainActivity.Viewable = TRUE;
							//DebugMessage
								Toast.makeText(TimerService.this, "Start2 < Now < End2", Toast.LENGTH_SHORT).show();
								Toast.makeText(TimerService.this, "Viewable = " + MainActivity.Viewable, Toast.LENGTH_SHORT).show();
							}
						}
					}
				});
			}
		}, 500, 30000); //起動後ディレイ, 実行間隔 ms

		return START_STICKY;
	}


	@Override
	public void onDestroy() {
		super.onDestroy();

		// タイマー停止
		if( mTimer != null ){
			mTimer.cancel();
			mTimer = null;
		}
		Toast.makeText(this, "TimerService onDestroy", Toast.LENGTH_SHORT).show();
	}


	@Override
	public IBinder onBind(Intent arg0) {
//		Log.i("TestService", "onBind");
		return null;
	}
}
