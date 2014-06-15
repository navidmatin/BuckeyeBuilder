package com.infintyloop.buckeyebuilder;

import java.util.ArrayList;

import com.nvanbenschoten.motion.ParallaxImageView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class SplashScreen extends Activity {
	private static int SPLASH_TIME_OUT = 3000;
	
	private int mCurrentImage;
	private ParallaxImageView mBackground;
	private boolean mParallaxSet = true;
	private boolean mPortraitLock = true;
	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		ArrayList<Building> tempBuildingList = new ArrayList<Building>();
		Intent oldIntent = getIntent();
		IUser tempUser = oldIntent.getParcelableExtra("User");
		ArrayList<Building> tempbuilding = oldIntent.getParcelableArrayListExtra("BuildingList");
		intent=new Intent(this, MainActivity.class);
		intent.putExtra("User", tempUser);
		intent.putExtra("BuildingList", tempbuilding);
		
		
		
		mBackground = (ParallaxImageView) findViewById(android.R.id.background);
		mBackground.setForwardTiltOffset(.35f);
		setCurrentImage();
		if(mParallaxSet)
			mBackground.registerSensorManager();
		new Handler().postDelayed(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				startActivity(intent);
				finish();
			}
			
		}, SPLASH_TIME_OUT);
		
	}
	
	private void setCurrentImage(){
		if(mCurrentImage == 1) {
			mBackground.setImageResource(R.drawable.image1);
		} else
		{
			mBackground.setImageResource(R.drawable.image2);
		}
	}
	
	@Override
	public void onPause() {
		mBackground.unregisterSensorManager();
		
		super.onPause();
	}
}
