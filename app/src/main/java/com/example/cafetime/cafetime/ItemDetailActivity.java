package com.example.cafetime.cafetime;

/**
 * Created by c200 on 16/12/09.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ItemDetailActivity extends Activity {
	private TextView mTitle;
	private TextView mDescr;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_detail);

		Intent intent = getIntent();

		String title = intent.getStringExtra("TITLE");
		mTitle = (TextView) findViewById(R.id.textView1);
		mTitle.setText(title);
		String descr = intent.getStringExtra("DESCRIPTION");
		mDescr = (TextView) findViewById(R.id.textView2);
		mDescr.setText(descr);

	}

}
