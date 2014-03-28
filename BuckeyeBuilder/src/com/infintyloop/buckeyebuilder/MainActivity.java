package com.infintyloop.buckeyebuilder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {
	IUser user = new User();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
 		setContentView(R.layout.activity_main);
 		Intent intent = getIntent();
 		user = intent.getParcelableExtra("User");
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
	}
	@Override
	protected void onStop(){
		super.onStop();
/*		Context context = getApplicationContext();
		CharSequence text = "Don't LEAVEEEE!!!!!";
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		Log.i("OnStop Called","index");*/
	}
}

