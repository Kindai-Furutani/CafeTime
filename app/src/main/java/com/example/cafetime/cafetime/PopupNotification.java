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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
		alertDlg.setTitle("title");
		alertDlg.setMessage("Massage");

/*
		alertDlg.setPositiveButton(
			"新着一覧",
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {

				}
			});
*/

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
