package com.infinityloop.buckeyebuilder;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.infinityloop.buckeyebuilderUtilities.adapter.TabsPagerAdapter;
import com.infinityloop.buckeyebuilderUtilities.databasehelper.DataHandler;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
/**
 * The only activity other than the Login and Register Activity:
 * This activity holds all of the tabs and all of the fragments. It also works as a terminal for data
 * all of the fragments access data through the main activity
 */
public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
	IUser user = new User();
	ArrayList<Building> buildingList = new ArrayList<Building>();
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	GPSManager gps= new GPSManager(this);
	double userLat, userLon;
	int updateMap=1;
	String currentBuilding;
	Gson gson= new Gson();//new GsonBuilder().registerTypeAdapter(Building.class, new BuildingInstanceCreator()).create();
	//Tab names
	private String[] tabs = { "Build", "Manage", "Build Plan" };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

			   
		super.onCreate(savedInstanceState);
 		setContentView(R.layout.activity_main);
 		Intent intent = getIntent();
 		/**START DATA**/
		/* Data and Back-end Processes */
 		

 		/* Data and Back-end Processes */
 			user = intent.getParcelableExtra("User");
 			buildingList = intent.getParcelableArrayListExtra("BuildingList");
 		
 		
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
		Toast.makeText(this, "Welcome "+user.GetUsername(), Toast.LENGTH_LONG).show();
	}
		 	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		//Handle item selection
		switch(item.getItemId()){
			case R.id.action_log_out:
			{
				DataHandler.saveData(this, buildingList, user);
				if(ParseFacebookUtils.getSession()!=null)
					ParseFacebookUtils.getSession().closeAndClearTokenInformation();
				ParseUser.logOut();
				startActivity(new Intent(this, LoginActivity.class));
				finish();
			}DataHandler.saveData(this, buildingList, user);
			case R.id.bug_report:
			{
				FragmentManager fm=  getSupportFragmentManager();
				
				BugReportDialogFragment brdf = new BugReportDialogFragment();
				brdf.show(fm, "bug_report_dialog");
				
			}
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());
		//ViewPager.SimpleOnPageChangeListener
		
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
	public void onStop()
	{
		super.onStop();
		DataHandler.saveData(this, buildingList, user);
		
	}
}