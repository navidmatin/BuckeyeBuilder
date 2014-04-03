package com.infintyloop.buckeyebuilder;


import android.os.Parcel;
import android.os.Parcelable;

public interface IBuilding extends Parcelable {
	public void GiveValuesToBuilding(String theName, int cost, int theGenRates, String theDescription);
	public void SetLevel(int incomingLevel);
	public String GetName();
	public int GetLevel();
	public int GetCurrentCost();
	public String GetDescription();
	public int GetCurrentGenRate();
	public void Upgrade(IUser user);
	public void writeToParcel(Parcel dest, int flags);
	public int describeContents();
}
