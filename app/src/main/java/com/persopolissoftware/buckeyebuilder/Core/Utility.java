package com.persopolissoftware.buckeyebuilder.Core;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utility {
	public static boolean isConnectedToInternet(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(connectivity !=null)
		{
			NetworkInfo[] info= connectivity.getAllNetworkInfo();
			if(info != null)
			{
				for (int i =0; i < info.length; i++)
				{
					if(info[i].getState() == NetworkInfo.State.CONNECTED)
						return true;
				}
			}
		}
		return false;
	}
	public static void showTextAlertDialog(Context context, String title, String message) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
		
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
	//	alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);
		alertDialog.setCancelable(true);
		alertDialog.setNegativeButton("Close", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		alertDialog.show();
	}
}
