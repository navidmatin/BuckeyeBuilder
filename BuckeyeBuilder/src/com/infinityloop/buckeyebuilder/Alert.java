package com.infinityloop.buckeyebuilder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
/**
 * A class with static methods for showing different alerts!
 */
public class Alert {
	public static void notEnoughMoneyAlert(Context context){
	AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
			
			//Dialog title
			alertDialog.setTitle("Low Budget!");
			
			//Dialog message
			alertDialog.setMessage("You don't have enough money for this action");
			//TO DO: Give options to make money
			/*alertDialog.setPositiveButton("Enable GPS",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which){
					Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					mContext.startActivity(intent);
				}
			});*/
			alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which){
					dialog.cancel();
				}
			});
			alertDialog.show();
	}
	public static void upgradeFailed(Context context){
	AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
			
			//Dialog title
			alertDialog.setTitle("Upgrade Failed");
			
			//Dialog message
			alertDialog.setMessage("Building reached maximum level!");
			//TO DO: Give options to make money
			/*alertDialog.setPositiveButton("Enable GPS",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which){
					Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					mContext.startActivity(intent);
				}
			});*/
			alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which){
					dialog.cancel();
				}
			});
			alertDialog.show();
	}
	public static void showAlert(String title, String message)
	{
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(BuckeyeBuilderApplication.getAppContext());
		
		//Dialog title
		alertDialog.setTitle(title);
		
		//Dialog message
		alertDialog.setMessage(message);

		alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which){
				dialog.cancel();
			}
		});
		alertDialog.show();
	}

}
