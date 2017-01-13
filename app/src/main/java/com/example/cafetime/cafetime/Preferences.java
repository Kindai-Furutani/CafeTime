package com.example.cafetime.cafetime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class Preferences extends AppCompatActivity{
	public static String SaveNum;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_preferences);

		MainActivity.RunningIntent = new Intent(this, PopupNotification.class);

//タイトルバーの文字を変更
		setTitle("設定");

//ListViewを追加
		final ListView listView = new ListView(this);

		setContentView(listView);

		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
//ListViewの中身を追加
		adapter.add("Site1");
		adapter.add("Site2");
		adapter.add("Site3");
		adapter.add("Time1");
		adapter.add("Time2");
		adapter.add("Graph");

		listView.setAdapter(adapter);
//ListViewの設定ここまで

//ListViewが押された時の動作
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				Intent intent[] = new Intent[adapter.getCount()]; //ListViewの中身の数と配列の数を連動

				for(int i=0; i<intent.length; i++)
					intent[i] = new Intent(getApplicationContext(), SiteSettingActivity.class);

				for(int i=3; i<intent.length; i++)
					intent[i] = new Intent(getApplicationContext(), EndTimerSettingActivity.class);

				intent[5] = new Intent(getApplicationContext(), GraphViewActivity.class);

				SaveNum = String.valueOf(position);
				startActivity(intent[position]);
			}
		});
//ListViewが押された時の動作ここまで
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
}
