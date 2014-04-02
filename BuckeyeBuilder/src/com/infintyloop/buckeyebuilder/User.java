package com.infintyloop.buckeyebuilder;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

import com.infintyloop.buckeyebuilder.Market;

public class User implements IUser {

	private String name;
	private int moneyCap;
	// private Location location;
	private int money;
	private Market myMarket;
	
	
	//Constructors
	public User() {};
	public Market myMarket() {
		return myMarket;
	}
	public User(Parcel in){
		readFrmParce(in);
	}
	
	public void GiveValuesToUser(String userName, int cap, int cash){
		name = userName;
		moneyCap = cap;
		money = cash;
		// somehow set value to all variables
	}
	
	public String GetUsername(){
		return name;
	}
	
	  
	public int GetMoney(){
		return money;
	}
	
	  
	public int GetCap(){
		return moneyCap;
	}
	
	public void UpgradeBuilding(IBuilding buildingName) {
		//NEED TO RE-check the logic, I changed it based on the changes I made to the Building
		int cost = buildingName.GetCurrentCost();
		if(money > cost){
			if(buildingName.Upgrade()){
				money = money - cost;
			}
		}
	}
	
	public int CalculateCurrentGenRate(ArrayList<IBuilding> buildingList)
	{
		int genRate=0;
		for(IBuilding building : buildingList)
		{
			genRate+=building.GetCurrentGenRate();
		}
		return genRate;
	}
	public void MakeMoney(int amount) {
		
		if(money < moneyCap){
			money = money + amount;
		}
	// if money cap is not hit.. then call generate money
	// ODO Auto-generated method stub
	}
	
	 
	public void Walk() {
	// TODO Auto-generated method stub
	}
	
	 @Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	private void readFrmParce(Parcel in) {		
			name=in.readString();
			moneyCap=in.readInt();
			money=in.readInt();	
			myMarket = in.readParcelable(Market.class.getClassLoader());
	}
	@Override 
	public void writeToParcel(Parcel dest, int flags) {		
		dest.writeString(name);
		dest.writeInt(moneyCap);
		dest.writeInt(money);
		dest.writeParcelable(myMarket, flags);
		
	}
	
	public static final Parcelable.Creator<User> CREATOR = 
			new Parcelable.Creator<User>() {
		public User createFromParcel (Parcel in) {
			return new User(in);
		}
		public User[] newArray(int size) {
			return new User[size];
		}
	};
}