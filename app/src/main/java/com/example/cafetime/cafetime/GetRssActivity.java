package com.example.cafetime.cafetime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

//ここから追加

//ここまで追加

public class GetRssActivity extends AppCompatActivity {

	EditText editText = (EditText)findViewById(R.id.urlData1);
	String URL1 = editText.getText().toString();

		@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_rss);
	}
}
