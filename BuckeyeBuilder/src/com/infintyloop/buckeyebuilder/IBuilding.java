package com.infintyloop.buckeyebuilder;

/**
 * Building interface, Not being Used for the current version! Just here in case of expanding the app
 */
import android.os.Parcel;
import android.os.Parcelable;

public interface IBuilding extends Parcelable {
	public void GiveValuesToBuilding(String theName, int cost, int theGenRates, String theDescription, double longitude, double latitude, double rad);
	public void SetLevel(int incomingLevel);
	public String GetName();
	public int GetLevel();
	public int GetCurrentCost();
	public String GetDescription();
	public int GetCurrentGenRate();
	public void Upgrade(IUser user);
	public void writeToParcel(Parcel dest, int flags);
	public int describeContents();
	public double GetRadius();
	public double GetLatitude();
	public double GetLongitude();
}
