package com.infintyloop.buckeyebuilder;

import android.os.Parcel;
import android.os.Parcelable;

public class Market implements Parcelable{
int woodCost=3,metalCost=5,stoneCost=4;
int totalWood,totalMetal,totalStone;
//boolean buyingWood,buyingMetal,buyingStone;
public int money,amountMaterial;

	public static void PayUser(IBuilding[] buildings, IUser user){
		int paycheck = 0;
		for (int i = 0; i < buildings.length; i++){
			if(buildings[i].GetLevel() > 0){
				paycheck = paycheck + buildings[i].GenerateMoney();
			}
		}
		user.MakeMoney(paycheck);
	}
	
	public Market() {
	};
	public Market(Parcel in){
		readFromParcel(in);
	}
	
	public int Buy_Wood(int woodAmount)
	{
			//totalWood= totalWood+amountMaterial;	
			woodCost=woodCost*woodAmount;
			return woodCost;		
	}
	
	public int Buy_Metal(int metalAmount)
	{
			//totalMetal= totalMetal+amountMaterial;
		metalCost=metalCost*metalAmount;
		return metalCost;
		
	}
	
	public int Buy_Stone(int stoneAmount)
	{
			//totalStone= totalStone+amountMaterial;	
		stoneCost=stoneCost*stoneAmount;
		return stoneCost;
			
	}
	
		
	
	public void ShowCost(int resources)
	{
	
		
		
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
		dest.writeInt(woodCost);
		dest.writeInt(metalCost);
		dest.writeInt(stoneCost);
		dest.writeInt(totalWood);
		dest.writeInt(totalMetal);
		dest.writeInt(totalStone);
		dest.writeInt(money);
		dest.writeInt(amountMaterial);
	}
	
	public void readFromParcel(Parcel in) {
		woodCost = in.readInt();
		metalCost = in.readInt();
		stoneCost = in.readInt();
		totalWood = in.readInt();
		totalMetal = in.readInt();
		totalStone = in.readInt();
		money = in.readInt();
		amountMaterial = in.readInt();
	}
	public static final Parcelable.Creator<Market> CREATOR = 
			new Parcelable.Creator<Market>() {
		public Market createFromParcel (Parcel in) {
			return new Market(in);
		}
		public Market[] newArray (int size) {
			return new Market[size];
		}
	};
 
 
	
	
	
	
}
