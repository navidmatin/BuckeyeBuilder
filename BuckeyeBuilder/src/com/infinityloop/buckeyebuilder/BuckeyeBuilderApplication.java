package com.infinityloop.buckeyebuilder;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;

import android.app.Application;

public class BuckeyeBuilderApplication extends Application{
	static final String TAG="MyApp";
	
	@Override
	public void onCreate(){
		super.onCreate();
		
		Parse.initialize(this, "***REMOVED***", "***REMOVED***");
		ParseFacebookUtils.initialize("***REMOVED***");

	}
}
