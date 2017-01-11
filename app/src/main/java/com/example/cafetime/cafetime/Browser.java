package com.example.cafetime.cafetime;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;


public class Browser extends Activity {
	static TextView useHour;
	static TextView useMinute;
	static TextView useSecond;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browser);

		MainActivity.RunningIntent = new Intent(this, PopupNotification.class);

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

	public static void setTime(int Hor, int Min, int Sec){
		useHour.setText(Hor);
		useMinute.setText(Min);
		useSecond.setText(Sec);
	}
}
