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
	String currentbuilding = "Nothing";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		this.dh=new DatabaseHelper(getActivity());
		View viewRoot= inflater.inflate(R.layout.user_info_fragment, container,false);
		
		//TEST: Show current Long and Lat
		
		return viewRoot;
	}
	@Override
	public void onStart()
	{
		super.onStart();
		
		user = ((MainActivity)getParentFragment().getActivity()).user;

		currentbuilding= ((MainActivity)getParentFragment().getActivity()).currentBuilding;
		//ArrayList<Building> gsonthing =((MainActivity)getParentFragment().getActivity()).gsontest;
		ArrayList<Building> buildingList = ((MainActivity)getParentFragment().getActivity()).buildingList;
		int genRate=user.CalculateCurrentGenRate(buildingList);
		TextView genRateView = (TextView) getView().findViewById(R.id.moneyperHour);
		genRateView.setText(genRate+"$"+ " per hour");
		
	//	btn=(Button) getView().findViewById(R.id.button1);
		//btn.setText("Build " + currentbuilding);
		
	  // 	GPSManager _gps=((MainActivity)getParentFragment().getActivity()).gps;
	   //	Location location=_gps.getLocation();
	//	if(_gps.canGetLocation()){
		//	longi=_gps.getLatitude();
			//lat=_gps.getLongitude();
	//		Toast.makeText(getActivity().getApplicationContext(),"Your location is -\n Lat:"+ lat + "\nLong: "+longi, Toast.LENGTH_LONG).show();
		//	Toast.makeText(getActivity(), currentbuilding, Toast.LENGTH_LONG).show();
//		}
	
	//	btn.setOnClickListener(new OnClickListener(){
		//	@Override
//			public void onClick(View v) {
	//	for(int i = 0; i < buildingList.size(); i++){
	//		IBuilding temp = buildingList.get(i);
	//		String tempName = temp.GetName();
	//		if (tempName == currentbuilding){
	//			temp.Upgrade(user);
	//		}
			
	//	}
	//		}
			
	//	});
		
		
	}
	@Override
	public void onResume(){
		super.onResume();
		int money= user.GetMoney();
		int cap=user.GetCap();
		if(moneyView==null)
			moneyView = (TextView) getView().findViewById(R.id.textMoneyAmount);
		moneyView.setText(null);
		moneyGeneratorThread();
		//moneyView.setText(money+"$ /"+cap+"$");
		//user.MakeMoney();
		//this.dh.updateTime(user.GetUsername());
		//Toast.makeText(getActivity(), user.GetMoney(), Toast.LENGTH_LONG).show();
	}
	//Constantly updating the money
	private void moneyGeneratorThread() {
		final Handler handler = new Handler();
		Thread runnable = new Thread(new Runnable() {
			private long startTime = System.currentTimeMillis();
			public void run(){
				while(true)
				{
					try {
						Thread.sleep(100);
					}
					catch(InterruptedException e) {
						e.printStackTrace();
					}
					handler.post(new Runnable(){
						@Override
						public void run(){
							user.MakeMoney();
							int money= user.GetMoney();
							int cap=user.GetCap();
							moneyView.setText(money+"$ /"+cap+"$");
							
						}
					});
				}
			}
		});
		if(!runnable.isAlive())
			runnable.start();
	}
	@Override
	public void onPause(){
		super.onPause();
		((MainActivity)getParentFragment().getActivity()).user=user;
	}
}
