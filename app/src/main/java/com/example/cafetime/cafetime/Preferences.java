package com.example.cafetime.cafetime;

/**
 * Created by c200 on 16/12/08.
 */
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class Preferences extends AppCompatActivity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_preferences);

//ListViewを追加
		final ListView listView = new ListView(this);

		setContentView(listView);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
//ListViewの中身を追加
		adapter.add("Site1");
		adapter.add("Site2");

		listView.setAdapter(adapter);
//ListViewの設定ここまで


//ListViewが押された時の動作
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				ListView listView1 = (ListView)adapterView;

//何番目のListViewが押されたかによって開くアクティビティが変わる
				Intent intent[] = new Intent[2];

				for(int i=0; i<intent.length; i++)
					intent[i] = new Intent(getApplicationContext(), SiteSettingActivity.class);

				switch (position){
					case 0:
						SiteSettingActivity.SaveNum = String.valueOf(position);
						startActivity(intent[0]);
						break;

					case 1:
						SiteSettingActivity.SaveNum = String.valueOf(position);
						startActivity(intent[1]);
						break;
				}
			}
		});
//ListViewが押された時の動作ここまで






/*
		// 設定画面の作成
		addPreferencesFromResource(R.xml.preferences);
*/

/*
		TimePickerDialog timePickerDialog;

		TimePickerDialog.OnTimeSetListener TimeSetListener = new TimePickerDialog.OnTimeSetListener() {
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

				//ログ出力
				Log.d("TimePicker","hourOfDay:" + hourOfDay + " minute:" + minute);
			}
		};
*/

/*
		EditText editText = (EditText)findViewById(R.id.urlData1);
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		sharedPreferences.edit().putString("SaveString", editText.getText().toString()).commit();
*/

	}
}
