package com.infintyloop.buckeyebuilder;

import java.util.ArrayList;
import java.util.Calendar;

import com.infinityloop.buckeyebuilder.databasehelper.DatabaseHelper;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserInfoFragment extends Fragment {
	Button btn;
	double longi;
	double lat;
	DatabaseHelper dh;
	TextView moneyView;
	IUser user;
	String currentbuilding;
	boolean fragmentState=false;
	ArrayList<Building> buildingList;
	LocationHandler locationHandler;
	TextView genRateView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View viewRoot= inflater.inflate(R.layout.user_info_fragment, container,false);
		//TEST: Show current Long and Lat
		locationHandler = new LocationHandler();
		return viewRoot;
	}
	@Override
	public void onStart()
	{
		super.onStart();
		fragmentState=true;
		moneyGeneratorThread();
		user = ((MainActivity)getParentFragment().getActivity()).user;

		currentbuilding= ((MainActivity)getParentFragment().getActivity()).currentBuilding;
		//ArrayList<Building> gsonthing =((MainActivity)getParentFragment().getActivity()).gsontest;
		buildingList = ((MainActivity)getParentFragment().getActivity()).buildingList;
	}
	
	private void findBuildingsAround(){
		if(buildingList!=null){
			locationHandler.Initialize(buildingList);
			GPSManager myGPSManager=((MainActivity)getParentFragment().getActivity()).gps;
    		myGPSManager.getLocation();
 		    	 		if(myGPSManager.canGetLocation()){ 
 		    				double userLat = myGPSManager.getLatitude();
 		    				double userLon = myGPSManager.getLongitude();
 		    				locationHandler.RecieveLocation(userLat, userLon);
 		    			}
 		    	 		currentbuilding= locationHandler.CheckLocationForBuilding();
		}
	}
	@Override
	public void onResume(){

		super.onResume();
		if(moneyView==null)
			moneyView = (TextView) getView().findViewById(R.id.textMoneyAmount);
		moneyView.setText(null);
		genRateView = (TextView) getView().findViewById(R.id.moneyperHour);
		
		findBuildingsAround();
		btn=(Button) getView().findViewById(R.id.button1);
		btn.setText("Can Build " + currentbuilding);
		btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Building building=BuildingFactory.FindBuilding(currentbuilding, buildingList);
				if(building.level==0)
				{
					building.Upgrade(user);
					user.IncreaseNumberofBuildingsOwned(1);
					buildingList=BuildingFactory.addBuildingtoTheList(building, buildingList);
				}
				
			
			}
		});
	}
	//Constantly updating the money
	private void moneyGeneratorThread() {
		final Handler handler = new Handler();
		Thread runnable = new Thread(new Runnable() {
			private long startTime = System.currentTimeMillis();
			public void run(){
				while(fragmentState)
				{
					try {
						Thread.sleep(1000);
					}
					catch(InterruptedException e) {
						e.printStackTrace();
					}
					handler.post(new Runnable(){
						@Override
						public void run(){
							//Constantly updating the genRate and 
							user.MakeMoney();
							int money= user.GetMoney();
							int cap=user.GetCap();
							moneyView.setText(money+"$ /"+cap+"$");
							int genRate=user.CalculateCurrentGenRate(buildingList);
							
							genRateView.setText(genRate+"$"+ " per hour");
							
						}
					});
				}
			}
		});
			runnable.start();
	}
	@Override
	public void onPause(){
		super.onPause();
		fragmentState=false;
		((MainActivity)getParentFragment().getActivity()).user=user;
	}
}
