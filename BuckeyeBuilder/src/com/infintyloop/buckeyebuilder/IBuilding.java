package com.infintyloop.buckeyebuilder;


import android.os.Parcel;
import android.os.Parcelable;

public interface IBuilding extends Parcelable {
	public void GiveValuesToBuilding(String theName, int[][] theCost, int[] theGenRates, String theDescription);
	public void SetLevel(int incomingLevel);
	public String GetName();
	public int GetLevel();
	public int[] GetCurrentCost();
	public String GetDescription();
	public int[][] GetCosts();
	public int[] GetGenRates();
	public boolean Upgrade();
	public int GenerateMoney();
}
