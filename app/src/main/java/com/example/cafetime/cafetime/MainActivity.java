package com.example.cafetime.cafetime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

	private ArrayList mItems = new ArrayList();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

//タイトルバーの文字を変更
		setTitle("ホーム");

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!現在デバッグ中の箇所!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//ここからRSS取得の設定
//		EditText editText1 = (EditText)findViewById(R.id.urlData1);
//		String URL1 = editText1.getText().toString();

//		EditText editText2 = (EditText)findViewById(R.id.urlData2);
//		String URL2 = editText2.getText().toString();

		RssListAdapter mAdapter = new RssListAdapter(this, mItems);

		ListView _listview = (ListView)findViewById(R.id.listView1);

		RssParserTask task = new RssParserTask(this, mAdapter,_listview);
		task.execute("http://andante.in/i/feed/");
//		task.execute(URL2);
		_listview.setOnItemClickListener(this);


	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Item item = (Item)mItems.get(arg2);
		Intent intent = new Intent(this, ItemDetailActivity.class);
		intent.putExtra("TITLE", item.getTitle());
		intent.putExtra("DESCRIPTION", item.getDescription());
		startActivity(intent);
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!ここまでデバッグ中!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	}

//右上の設定ボタン
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
//設定ボタンここまで

//右上から設定画面への遷移設定
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			Intent intent = new Intent(this, Preferences.class);
			startActivity(intent);
		}

		return super.onOptionsItemSelected(item);
	}
//設定画面への遷移ここまで
}
