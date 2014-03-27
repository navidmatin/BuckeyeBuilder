package com.infintyloop.buckeyebuilder;
import android.os.Parcel;

import com.infintyloop.buckeyebuilder.Market;

public class User implements IUser {

//serialVersionUID
private String name;
private int materialAmounts[] = new int[3];
private int moneyCap;
// private Location location;
private int money;
private Market myMarket = new Market();

@Override 
public void GiveValuesToUser(String userName,int[] amounts, int cap, int cash){
	name = userName;
	materialAmounts[0] = amounts[0];
	materialAmounts[1] = amounts[1];
	materialAmounts[2] = amounts[2];
	moneyCap = cap;
	money = cash;
	// somehow set value to all variables
}

@Override 
public String GetUsername(){
	return name;
}

@Override 
public int[] GetAmounts(){
	return materialAmounts;
}

@Override 
public int GetMoney(){
	return money;
}

@Override 
public int GetCap(){
	return moneyCap;
}

@Override
public void BuyMetal(int metalRequest) {
	if(money > myMarket.Buy_Metal(metalRequest)){
		money = money - myMarket.Buy_Metal(metalRequest);
		materialAmounts[0] = materialAmounts[0] + metalRequest;
	}
}

@Override
public void BuyStone(int stoneRequest) {
	if(money > myMarket.Buy_Stone(stoneRequest)){
		money = money - myMarket.Buy_Stone(stoneRequest);
		materialAmounts[1] = materialAmounts[1] + stoneRequest;
	}
}

@Override
public void BuyWood(int woodRequest) {
	if(money > myMarket.Buy_Wood(woodRequest)){
		money = money - myMarket.Buy_Wood(woodRequest);
		materialAmounts[2] = materialAmounts[2] + woodRequest;
	}
}

@Override
public void UpgradeBuilding(IBuilding buildingName) {
	int[] requirements = buildingName.GetCurrentCost();
	if(materialAmounts[0] > requirements[0] && materialAmounts[1] > requirements[1] && materialAmounts[2] > requirements[2]){
		if(buildingName.Upgrade()){
			for(int i = 0; i < 3; i++){
				materialAmounts[i] = materialAmounts[i] - requirements[i];
			}
		}
	}
}

@Override
public void MakeMoney(int amount) {
	
	if(money < moneyCap){
		money = money + amount;
	}
// if money cap is not hit.. then call generate money
// ODO Auto-generated method stub
}

@Override
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
	// TODO Auto-generated method stub
	
}
}