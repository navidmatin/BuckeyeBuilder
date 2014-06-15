package com.infintyloop.buckeyebuilder;

import android.os.Parcel;
import android.os.Parcelable;
/**
 * A class representing each Building
 */
public class Building implements IBuilding {
	
	private String name;
	private int level;
	private int cost; // Cost of level 0 to 1 upgrade
	private int genRate;
	private double lat;
	private double longi;
	private double radius;
	//private double radius;
	private int currentCost;
	private String description;
	public String version;
	
	public Building(){};
	
	public Building(Parcel in){
		readFromParcel(in);
	}
	public double GetLatitude()
	{
		return lat;
	}
	public double GetLongitude()
	{
		return longi;
	}
	public double GetRadius()
	{
		return radius;
	}
	public void GiveValuesToBuilding(String theName, int _cost, int theGenRate, String theDescription, double longitude, double latitude, double rad) {
		// somehow need to set a value to all variables...
		name = theName;
		cost=_cost; //Cost of the level 0 to 1 upgrade
		genRate=theGenRate; //The initial gen rate
		description = theDescription;
		lat = latitude;
		longi = longitude;
		radius = rad;
	}

	public void SetLevel(int incomingLevel){
		level = incomingLevel;
	}
	
	
	public String GetName() {
		return name;
	}

	
	public int GetLevel() {
		return level;
	}

	
	public int GetCurrentCost() {
		if(level == 0){
			currentCost=cost;
		}
		else if(level == 1){
			currentCost = cost * 2;
		}
		else{
			currentCost = cost * 3;
		}
		return currentCost;
	}

	public String GetDescription(){
		return description;
	}
	
	public int GetCurrentGenRate() {
		
		return (int) (genRate*level*1.5); //Outputting the current generation rate based on the level of the buildings
	}
	public int getPossibleGenRatePerLevel(){
		return (int) (genRate*1.5);
	}
	public void Upgrade(IUser user) {
		if(level<3){
			int _cost=this.GetCurrentCost();
			if(user.GetMoney()>=_cost)
			{
				user.Pay(_cost);
				level++;
			}
		}
		else{
			//Find a way to show an alert
		}
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	private void readFromParcel(Parcel in){
		name = in.readString();
		level = in.readInt();
		description = in.readString();
		currentCost = in.readInt();
		genRate = in.readInt();
		cost = in.readInt();
		longi= in.readDouble();
		lat = in.readDouble();
		radius=in.readDouble();
		version = in.readString();
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeInt(level);
		dest.writeString(description);
		dest.writeInt(currentCost);
		dest.writeInt(genRate);
		dest.writeInt(cost);
		dest.writeDouble(longi);
		dest.writeDouble(lat);
		dest.writeDouble(radius);
		dest.writeString(version);
	}
	
	public static final Parcelable.Creator<Building> CREATOR = 
			new Parcelable.Creator<Building>() {
		public Building createFromParcel (Parcel in) {
			return new Building(in);
		}
		public Building[] newArray(int size) {
			return new Building[size];
		}
	};
}
