package com.infintyloop.buckeyebuilder;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;

public class LocationHandler {//implements Runnable { //extends AsyncTask 
	private double userLongitude, userLatitude;
	private ArrayList<Double> lats = new ArrayList<Double>();
	private ArrayList<Double> rads = new ArrayList<Double>();
	private ArrayList<Double> lons = new ArrayList<Double>();
	private ArrayList<String> buildingNames = new ArrayList<String>();
	private int radius;
	
    //public static void main(ArrayList<IBuilding> allBuildings) {
     //   (new Thread(new LocationHandler())).start();
   // }
	 
	public void Initialize(ArrayList<IBuilding> allBuildings){
		for(int i = 0; i < allBuildings.size(); i++){
			IBuilding temp = allBuildings.get(i);
			double tempLat = temp.GetLatitude();
			double tempLon = temp.GetLongitude();
			double tempRadius = temp.GetRadius();
			String tempName = temp.GetName();
			lats.add(tempLat);
			lons.add(tempLon);
			rads.add(tempRadius);
			buildingNames.add(tempName);
		}

	}
	
	// will be grabbed from user class or location void API
	public void RecieveLocation(double lat, double lon){
		userLongitude = lat;
		userLatitude = lon;
	}
	public String CheckLocationForBuilding(){
		// foreach loop
	//	for(String a : buildingNames)
		for (int i = 0; i < buildingNames.size(); i++){
			double tempLat = lats.get(i);
			double tempLon = lons.get(i);
			double tempRad = rads.get(i);
			if((userLongitude > (tempLon - tempRad)) && (userLongitude < (tempLon + tempRad)) && (userLatitude < (tempLat + tempRad)) && (userLatitude > (tempLat - tempRad))){
				return buildingNames.get(i);
			}
		}
		return null;
	}
	
//	@Override
//	protected String doInBackground(Object... arg0) {
//		String buildng = CheckLocationForBuilding();
//		return buildng;
//	}

//	@Override
//	public void run() {
	// add print statement
	//	this.CheckLocationForBuilding();
//	}
}