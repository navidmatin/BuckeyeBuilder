package com.infintyloop.buckeyebuilder;
/**
 * Handles the location of the buildings and finding the current building
 */
import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

public class LocationHandler{// implements Runnable { //extends AsyncTask 
	private double userLongitude, userLatitude;
	private ArrayList<Double> lats = new ArrayList<Double>();
	private ArrayList<Double> rads = new ArrayList<Double>();
	private ArrayList<Double> lons = new ArrayList<Double>();
	private ArrayList<String> buildingNames = new ArrayList<String>();
	private int radius;
	private String currentLocal = "null";
	
    //public static void main(ArrayList<Building> allBuildings) {
     //   (new Thread(new LocationHandler())).start();
   // }
	 
	public void Initialize(ArrayList<Building> allBuildings){
		for(int i = 0; i < allBuildings.size(); i++){
			Building temp = allBuildings.get(i);
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
		userLongitude = lon;
		userLatitude = lat;

	}
	public String CheckLocationForBuilding(){
		// foreach loop
	//	for(String xa : buildingNames)
		for (int i = 0; i < buildingNames.size(); i++){
			double tempLat = lats.get(i);
			double tempLon = lons.get(i);
			double tempRad = rads.get(i);
			if((Math.abs(userLatitude - tempLat) <= tempRad) && (Math.abs(userLongitude - tempLon) <= tempRad)){
				currentLocal = buildingNames.get(i);
				return currentLocal;
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
//		int n = 4;
//		n = 5*5;
	//	Context context = null;
	//	Toast toast = Toast.makeText(context, "yada", 10);
	//	toast.show();
		//this.CheckLocationForBuilding();
//	}
}