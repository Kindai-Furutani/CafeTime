package com.example.cafetime.cafetime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//ここから追加

//ここまで追加

public class GetRssActivity extends AppCompatActivity {
	public static String RssURL1 = "http://itpro.nikkeibp.co.jp/rss/ITpro.rdf";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_rss);
	}
}
