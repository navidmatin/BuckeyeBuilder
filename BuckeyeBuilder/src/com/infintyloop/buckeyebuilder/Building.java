package com.infintyloop.buckeyebuilder;

import android.os.Parcel;
import android.os.Parcelable;

public class Building implements IBuilding {
	
	private String name;
	public int level;
	private int cost; // Cost of level 0 to 1 upgrade
	private int genRate;
	//private Location location;
	//private double radius;
	private int currentCost;
	private String description;
	
	public Building(){};
	
	public Building(Parcel in){
		readFromParcel(in);
	}
	
	public void GiveValuesToBuilding(String theName, int _cost, int theGenRate, String theDescription) {
		// somehow need to set a value to all variables...
		name = theName;
		cost=_cost; //Cost of the leve 0 to 1 upgrade
		genRate=theGenRate; //The initial gen rate
		description = theDescription;
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
	
	
	public boolean Upgrade() {
		if(level < 2){
			level = level + 1;
			return true;
		}
		else {
		// error message: No more available upgrades!
			return false;
		}
	}
	
	
	
	public int GenerateMoney(){
		return GetCurrentGenRate();
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
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(name);
		dest.writeInt(level);
		dest.writeString(description);
		dest.writeInt(currentCost);
		dest.writeInt(genRate);
		dest.writeInt(cost);
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
