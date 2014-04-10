package com.infintyloop.buckeyebuilder;

import java.util.ArrayList;

import com.infinityloop.buckeyebuilder.adapter.TabsPagerAdapter;

import android.location.Location;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
	IUser user = new User();
	ArrayList<IBuilding> buildingList = new ArrayList<IBuilding>();
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	GPSManager gps= new GPSManager(this);
	private LocationHandler localHandler;
	double userLat, userLon;
	
	//Tab names
	private String[] tabs = { "Build", "Manage", "Build Plan" };
	
	@Override
	
	
	protected void onCreate(Bundle savedInstanceState) {

			   
		super.onCreate(savedInstanceState);
 		setContentView(R.layout.activity_main);
 		Intent intent = getIntent();
 		/**START DATA**/
		/* Data and Back-end Processes */
 		
 		if(savedInstanceState ==null)
 		{
 		/* Data and Back-end Processes */
 			user = intent.getParcelableExtra("User");
 			buildingList = intent.getParcelableArrayListExtra("BuildingList");
 		}
 		else{
 			IUser tempUser1 = intent.getParcelableExtra("User");
 			IUser tempUser2 = savedInstanceState.getParcelable("User");
 			if(tempUser1.GetUsername()==tempUser2.GetUsername())
 			{
 				user= savedInstanceState.getParcelable("User");
 				buildingList=savedInstanceState.getParcelableArrayList("BuildingList");
 			}
 			else
 			{
 				user=tempUser1;
 				buildingList = intent.getParcelableArrayListExtra("BuildingList");
 			}
 		}
 		
 		/**END DATA**/
 		
 		//Initialization of Tabs
 		viewPager = (ViewPager) findViewById(R.id.pager);
 		actionBar = getActionBar();
 		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
 		
 		viewPager.setAdapter(mAdapter);
 		//actionBar.setHomeButtonEnabled(false);
 		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
 		
 		//Adding Tabs
 		for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
 		
 		//ViewChange Listener so by changing view the tabs change
 		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
 			@Override
 			public void onPageSelected(int position){
 				//on changing the page, it changes the tab
 				actionBar.setSelectedNavigationItem(position);
 			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
 			
 		});
 		

 		

 

 		//LocationHandler myHandler = new LocationHandler();

 		//myHandler.Initialize(buildingList);
 	//	Thread t = new Thread(myHandler, "My Thread");
 	//	t.start();
 		
 		/*Location location = gps.getLocation();

 		if(gps.canGetLocation()){ 
			userLat = gps.getLatitude();
			userLon = gps.getLongitude();
			localHandler.RecieveLocation(userLat, userLon);
		}
		
		String currentBuilding = localHandler.CheckLocationForBuilding();*/
		
	}
	public void sendMessage(View view){
		 		Intent intent = new Intent(MainActivity.this, LoginActivity.class);
		 		startActivity(intent);
		 	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	@Override 
	public void onSaveInstanceState(Bundle savedInstanceState)
	{
		
		if(user!=null)
			savedInstanceState.putParcelable("User", user);
		if(buildingList!=null)
			savedInstanceState.putParcelableArrayList("BuildingList", buildingList);
		super.onSaveInstanceState(savedInstanceState);
	}
}