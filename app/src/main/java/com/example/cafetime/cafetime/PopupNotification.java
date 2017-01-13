package com.example.cafetime.cafetime;

/**
 * Created by c200 on 17/01/02.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class PopupNotification extends Activity {

	public static String CalledBy;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_popup);

		if(CalledBy == "StopWatchService"){
			CalledBy = null;

			AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
			alertDlg.setTitle("経過時刻");
			alertDlg.setMessage("使用時間が" + StopWatchService.Hor + "時間" + StopWatchService.Min + "分を経過しました");

			alertDlg.setCancelable(false);

			alertDlg.setNegativeButton(
				"閉じる",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				});

			alertDlg.create().show();
		}
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

	public void setCanceledOnTouchOutside(boolean cansel){

	}
}
