package com.infintyloop.buckeyebuilder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class BuildPlanActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_build_plan);
		
		
		findViewById(R.id.buildbutton).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view)
					{
						
					}
				});
		findViewById(R.id.managebutton).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						
					}
				});
		
		findViewById(R.id.planbutton).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						randomTest();
					}
				});
		
		
		
	}

	
	public void randomTest()
	{
		startActivity(new Intent(this, TestActivity.class));	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.build_plan, menu);
		return true;
	}
	
	

}
