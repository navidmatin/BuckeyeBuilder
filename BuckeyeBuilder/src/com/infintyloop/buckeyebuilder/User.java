package com.infintyloop.buckeyebuilder;
import android.os.Parcel;
import android.os.Parcelable;

import com.infintyloop.buckeyebuilder.Market;

public class User implements IUser {

	private String name;
	private int materialAmounts[] = new int[3];
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
	
	public void GiveValuesToUser(String userName,int[] amounts, int cap, int cash){
		name = userName;
		materialAmounts[0] = amounts[0];
		materialAmounts[1] = amounts[1];
		materialAmounts[2] = amounts[2];
		moneyCap = cap;
		money = cash;
		// somehow set value to all variables
	}
	
	public String GetUsername(){
		return name;
	}
	
	  
	public int[] GetAmounts(){
		return materialAmounts;
	}
	
	  
	public int GetMoney(){
		return money;
	}
	
	  
	public int GetCap(){
		return moneyCap;
	}
	
	 
	public void BuyMetal(int metalRequest) {
		if(money > myMarket.Buy_Metal(metalRequest)){
			money = money - myMarket.Buy_Metal(metalRequest);
			materialAmounts[0] = materialAmounts[0] + metalRequest;
		}
	}
	
	 
	public void BuyStone(int stoneRequest) {
		if(money > myMarket.Buy_Stone(stoneRequest)){
			money = money - myMarket.Buy_Stone(stoneRequest);
			materialAmounts[1] = materialAmounts[1] + stoneRequest;
		}
	}
	
	 
	public void BuyWood(int woodRequest) {
		if(money > myMarket.Buy_Wood(woodRequest)){
			money = money - myMarket.Buy_Wood(woodRequest);
			materialAmounts[2] = materialAmounts[2] + woodRequest;
		}
	}
	
	 
	public void UpgradeBuilding(IBuilding buildingName) {
		//NEED TO RE-check the logic, I changed it based on the changes I made to the Building
		int requirements = buildingName.GetCurrentCost();
		if(materialAmounts[0] > requirements && materialAmounts[1] > requirements && materialAmounts[2] > requirements){
			if(buildingName.Upgrade()){
				for(int i = 0; i < 3; i++){
					materialAmounts[i] = materialAmounts[i] - requirements;
				}
			}
		}
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
	
	@Override 
	public void writeToParcel(Parcel dest, int flags) {		
		dest.writeString(name);
		dest.writeIntArray(materialAmounts);
		dest.writeInt(moneyCap);
		dest.writeInt(money);
		dest.writeParcelable(myMarket, flags);
		
	}
	private void readFrmParce(Parcel in) {
		myMarket = in.readParcelable(Market.class.getClassLoader());
		name=in.readString();
		materialAmounts=in.createIntArray();
		moneyCap=in.readInt();
		money=in.readInt();		
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