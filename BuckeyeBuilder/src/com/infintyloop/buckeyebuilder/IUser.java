package com.infintyloop.buckeyebuilder;

import android.os.Parcel;
import android.os.Parcelable;

//import com.infintyloop.buckeyebuilder.IBuilding;

public interface IUser extends Parcelable {
	public void GiveValuesToUser(String userName,int[] amounts, int cap, int cash);
	public String GetUsername();
	public int[] GetAmounts();
	public int GetMoney();
	public int GetCap();
	public void BuyMetal(int metalRequest);
	public void BuyStone(int stoneRequest);
	public void BuyWood(int woodRequest);
	public void UpgradeBuilding(IBuilding building);
	public void MakeMoney(int amount);
	public int CalculateCurrentGenRate(IBuilding[] buildingList);
	public void Walk();
	public void writeToParcel(Parcel dest, int flags);
	public int describeContents();
}

