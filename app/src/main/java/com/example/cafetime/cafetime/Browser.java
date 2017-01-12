package com.example.cafetime.cafetime;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class Browser extends Activity {
	static TextView useHour;
	static TextView useMinute;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browser);

		useHour = (TextView)findViewById(R.id.useHour);
		useMinute = (TextView)findViewById(R.id.useMinute);

		useHour.setText(String.valueOf(StopWatchService.Hor));
		useMinute.setText(String.valueOf(StopWatchService.Min));

		MainActivity.BrowserActive = TRUE;

		MainActivity.RunningIntent = new Intent(this, PopupNotification.class);

		//レイアウトで指定したWebViewのIDを指定する。
		WebView  myWebView = (WebView)findViewById(R.id.webView1);

		//リンクをタップしたときに標準ブラウザを起動させない
		myWebView.setWebViewClient(new WebViewClient());

		//MainActivityのURLに代入されたページを表示
		myWebView.loadUrl(MainActivity.URL);

		//jacascriptを許可する
		myWebView.getSettings().setJavaScriptEnabled(true);

	}

	public static void setTime(int Hor, int Min){
		useHour.setText(String.valueOf(Hor));
		useMinute.setText(String.valueOf(Min));
	}
}
