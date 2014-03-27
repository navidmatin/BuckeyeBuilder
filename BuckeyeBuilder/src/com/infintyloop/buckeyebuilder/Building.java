package com.infintyloop.buckeyebuilder;

import android.os.Parcel;

public class Building implements IBuilding {
	
	private String name;
	public int level;
	private int levelCosts[][] = new int[3][3];
	private int genCosts[] = new int[2];
	//private Location location;
	//private double radius;
	private int[] currentCost = new int[3];
	private String description;
	
	public Building(){;};
	
	public Building(Parcel in){
		readFromParcel(in);
	}
	
	public void GiveValuesToBuilding(String theName, int[][] theCost, int[] theGenRates, String theDescription) {
		// somehow need to set a value to all variables...
		name = theName;
		levelCosts[0] = theCost[0];
		levelCosts[1] = theCost[1];
		levelCosts[2] = theCost[2];
		genCosts[0] = theGenRates[0];
		genCosts[1] = theGenRates[1];
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

	
	public int[] GetCurrentCost() {
		if(level == 0){
			currentCost = levelCosts[0];
		}
		else if(level == 1){
			currentCost = levelCosts[1];
		}
		else{
			currentCost = levelCosts[2];
		}
		return currentCost;
	}

	
	public String GetDescription(){
		return description;
	}
	
	
	public int[][] GetCosts() {
		return levelCosts;
	}
	
	
	public int[] GetGenRates() {
		return genCosts;
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
		if (level == 1){
			return genCosts[0];
		}
		else if (level == 2){
			return genCosts[1];
		}
		else {
			return 0;
		}
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(name);
		dest.writeInt(level);
		dest.writeString(description);
		dest.writeIntArray(currentCost);
		dest.writeIntArray(genCosts);
		dest.writeArray(levelCosts);
	}
	
	public void readFromParcel(Parcel in){
		name = in.readString();
		level = in.readInt();
		description = in.readString();
		currentCost = in.createIntArray();
		genCosts = in.createIntArray();
	}
}
