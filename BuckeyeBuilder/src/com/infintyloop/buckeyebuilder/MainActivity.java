package com.infintyloop.buckeyebuilder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
 		setContentView(R.layout.activity_main);
		Context context = getApplicationContext();
		CharSequence text = "Welcome to Initial BuckeyeBuilder! V.000.000.000.01";
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		Log.i("OnCreate Called","index");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	protected void onRestart(){
		super.onRestart();
		Context context = getApplicationContext();
		CharSequence text = "WELCOME BACK";
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		Log.i("OnRestart Called","index");
	}
	@Override
	protected void onStop(){
		super.onStop();
		Context context = getApplicationContext();
		CharSequence text = "Don't LEAVEEEE!!!!!";
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		Log.i("OnStop Called","index");
	}
}

