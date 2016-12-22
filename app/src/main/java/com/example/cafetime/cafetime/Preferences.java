package com.example.cafetime.cafetime;

/**
 * Created by c200 on 16/12/08.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class Preferences extends AppCompatActivity{
	public static String SaveNum;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_preferences);

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

				SaveNum = String.valueOf(position);
				startActivity(intent[position]);
			}
		});
//ListViewが押された時の動作ここまで

	}
}
