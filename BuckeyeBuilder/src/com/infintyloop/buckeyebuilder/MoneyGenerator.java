package com.infintyloop.buckeyebuilder;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

public class MoneyGenerator extends IntentService{

	public MoneyGenerator(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return 0;
	}
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	@Override
	public void onCreate()
	{
		super.onCreate();
	}
	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}
}
