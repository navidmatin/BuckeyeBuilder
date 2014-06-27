package com.infinityloop.buckeyebuilder;

import java.util.ArrayList;
import java.util.Calendar;

import com.infinityloop.buckeyebuilderUtilities.databasehelper.DatabaseHelper;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
/**
 * A nested fragment that is placed inside the BuildFragment. It shows the user current money 
 * and the generation rate
 */
public class UserInfoFragment extends Fragment {
	private static final int PROGRESS = 0x1;
	private int mProgressStatus = 0;
	private ProgressBar mProgress;
	Button btn=null;
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
	Building building=null;
	
	
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
		buildingList = ((MainActivity)getParentFragment().getActivity()).buildingList;
	}
	private void findBuildingsAround(){
		if(buildingList!=null){
			locationHandler.Initialize(buildingList);
			if(((MainActivity)getParentFragment().getActivity())!=null)
			{
			GPSManager myGPSManager=((MainActivity)getParentFragment().getActivity()).gps;
    		myGPSManager.getLocation();
 		    	 		if(myGPSManager.canGetLocation()){ 
 		    				//double userLat = myGPSManager.getLatitude();
 		    				//double userLon = myGPSManager.getLongitude();
 		    				locationHandler.RecieveLocation(myGPSManager.getLocation());
 		    			}
 		    	 		currentbuilding= locationHandler.CheckLocationForBuilding();
		}
		if(currentbuilding != null) {
			building=BuildingFactory.FindBuilding(currentbuilding, buildingList);
			Button btn = (Button) getView().findViewById(R.id.build_button);
			if(building.GetLevel()==0)
			{				
				btn.setText("Build " + currentbuilding);
				btn.setEnabled(true);

				btn.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {		
						if(building.GetLevel()==0)
						{
							if(user.GetMoney()<building.GetCurrentCost())
							{
								Alert.notEnoughMoneyAlert(getActivity());
							}
							else{
								building.Upgrade(user);
								if(building.GetLevel()==0)
									Alert.upgradeFailed(getActivity());
								user.IncreaseNumberofBuildingsOwned(1);
								buildingList=BuildingFactory.addBuildingtoTheList(building, buildingList);
								((MainActivity)getParentFragment().getActivity()).buildingList=buildingList;
								((TextView)v).setText(currentbuilding+" Is already built");
								v.setEnabled(false);
								((MainActivity)getParentFragment().getActivity()).updateMap++;
							}	
						}
					}
				});
			}
			else
			{
				btn.setText(currentbuilding+"Is already built");
			}
		}
		}
	}
	@Override
	public void onResume(){

		super.onResume();
		if(moneyView==null)
			moneyView = (TextView) getView().findViewById(R.id.textMoneyAmount);
		moneyView.setText(null);
		genRateView = (TextView) getView().findViewById(R.id.moneyperHour);
		if(mProgress==null)
			mProgress = (ProgressBar) getView().findViewById(R.id.progressBar1);
		
		findBuildingsAround();
		locationCheckerThread();
		

	}
	//Constantly updating buildings around
		private void locationCheckerThread() {
			final Handler handler = new Handler();
			Thread runnable = new Thread(new Runnable() {
				public void run(){
					while(fragmentState)
					{
						try {
							Thread.sleep(30000);
						}
						catch(InterruptedException e) {
							e.printStackTrace();
						}
						handler.post(new Runnable(){
							@Override
							public void run(){
								//Constantly updating the build button 
								findBuildingsAround();
								
							}
						});
					}
				}
			});
				runnable.start();
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
							double percentage = (double) money/cap;
							mProgress.setProgress((int) (percentage*100));
							
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
