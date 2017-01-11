package com.example.cafetime.cafetime;

/**
 * Created by c200 on 17/01/02.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class PopupNotification extends Activity {

	public static String CalledBy;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if(CalledBy == "StopWatchService"){
			CalledBy = null;

			AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
			alertDlg.setTitle("経過時刻");
			alertDlg.setMessage("使用時間が" + StopWatchService.Hor + "時間" + StopWatchService.Min + "分を経過しました");

			alertDlg.setNegativeButton(
					"閉じる",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							finish();
						}
					});

			alertDlg.create().show();
		}
		else {
			AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
			alertDlg.setTitle("title");
			alertDlg.setMessage("Massage");

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
}
