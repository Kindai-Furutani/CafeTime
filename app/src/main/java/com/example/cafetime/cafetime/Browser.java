package com.example.cafetime.cafetime;


import android.app.Activity;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;


public class Browser extends Activity {
	static TextView useHour = null;
	static TextView useMinute = null;
	static TextView useSecond = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browser);

		//レイアウトで指定したWebViewのIDを指定する。
		WebView  myWebView = (WebView)findViewById(R.id.webView1);

		//リンクをタップしたときに標準ブラウザを起動させない
		myWebView.setWebViewClient(new WebViewClient());

		//MainActivityのURLに代入されたページを表示
		myWebView.loadUrl(MainActivity.URL);

		//jacascriptを許可する
		myWebView.getSettings().setJavaScriptEnabled(true);

		useHour = (TextView)findViewById(R.id.useHour);
		useMinute = (TextView)findViewById(R.id.useMinute);
		useSecond = (TextView)findViewById(R.id.useSecond);
	}

	public static void setTime(){
		useHour.setText(StopWatchService.Hor);
		useMinute.setText(StopWatchService.Min);
		useSecond.setText(StopWatchService.Sec);
	}
}
