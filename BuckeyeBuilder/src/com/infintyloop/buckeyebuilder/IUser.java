package com.infintyloop.buckeyebuilder;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

//import com.infintyloop.buckeyebuilder.Building;

public interface IUser extends Parcelable {
	public void GiveValuesToUser(String userName,int cap, int cash);
	public String GetUsername();
	public int GetMoney();
	public void Pay(int amount);
	public int GetCap();
	public void MakeMoney();
	public int CalculateCurrentGenRate(ArrayList<Building> buildingList);
	public void Walk();
	public void writeToParcel(Parcel dest, int flags);
	public int describeContents();
	public int NumberofBuildingsOwned();
	public void IncreaseNumberofBuildingsOwned(int i);
}

