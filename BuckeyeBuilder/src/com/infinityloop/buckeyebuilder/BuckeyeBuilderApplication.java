package com.infinityloop.buckeyebuilder;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;

import android.app.Application;
import android.content.Context;

public class BuckeyeBuilderApplication extends Application{
	static final String TAG="MyApp";
	private static Context context;
	
	@Override
	public void onCreate(){
		super.onCreate();
		
		Parse.initialize(this, "***REMOVED***", "***REMOVED***");
		ParseFacebookUtils.initialize("***REMOVED***");
		context = getApplicationContext();

	}
	public static Context getAppContext() {
		return BuckeyeBuilderApplication.context;
	}
}
