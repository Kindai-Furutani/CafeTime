package com.example.cafetime.cafetime;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class Browser extends Activity {
	static TextView useHour;
	static TextView useMinute;
	public WebView myWebView;

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
		myWebView = (WebView)findViewById(R.id.webView1);

		//リンクをタップしたときに標準ブラウザを起動させない
		myWebView.setWebViewClient(new WebViewClient());

		//MainActivityのURLに代入されたページを表示
		myWebView.loadUrl(MainActivity.URL);

		//jacascriptを許可する
		myWebView.getSettings().setJavaScriptEnabled(true);

		Button closeBrowser = (Button)findViewById(R.id.CloseBrowser);
		closeBrowser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}

	public static void setTime(int Hor, int Min){
		useHour.setText(String.valueOf(Hor));
		useMinute.setText(String.valueOf(Min));
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()){
			myWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
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
