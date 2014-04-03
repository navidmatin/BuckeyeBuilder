package com.infintyloop.buckeyebuilder;

import java.util.ArrayList;
import android.os.Parcel;
import android.os.Parcelable;

public class LocationHandler  implements Parcelable{
	private double userLongitude, userLatitude;
	private ArrayList<Double> lats = new ArrayList<Double>();
	private ArrayList<Double> rads = new ArrayList<Double>();
	private ArrayList<Double> lons = new ArrayList<Double>();
	private ArrayList<String> buildingNames = new ArrayList<String>();
	private int radius;
	 
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
		userLongitude = lat; // temporary values until we can get the real thing
		userLatitude = lon;
	}
	public String CheckLocationForBuilding(){
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
	
	public LocationHandler() {
	};
	public LocationHandler(Parcel in){
		readFromParcel(in);
	}
	
	
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	private void readFromParcel(Parcel in) {
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
	}
	
	public static final Parcelable.Creator<LocationHandler> CREATOR = 
			new Parcelable.Creator<LocationHandler>() {
		public LocationHandler createFromParcel (Parcel in) {
			return new LocationHandler(in);
		}
		public LocationHandler[] newArray (int size) {
			return new LocationHandler[size];
		}
	};
 
 
	
	
	
}