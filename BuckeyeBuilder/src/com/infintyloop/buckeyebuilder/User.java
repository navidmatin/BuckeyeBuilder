package com.infintyloop.buckeyebuilder;
import com.infintyloop.buckeyebuilder.Market;

public class User implements IUser {

	private String name;
	private int woodAmount;
	private int metalAmount;
	private int stoneAmount;
	private int moneyCap;
	// private Location location;
	private int money;
	private Market myMarket = new Market();

	
	@Override 
	public void User(String userName,int[] amounts){
		name = userName;
		woodAmount = amounts[0];
		metalAmount = amounts[1];
		stoneAmount = amounts[2];
		moneyCap = amounts[3];
		money = amounts[4];
		// somehow set value to all variables
	}

	@Override
	public void BuyMetal(int metalRequest) {
		if(money > myMarket.Buy_Metal(metalRequest)){
			money = money - myMarket.Buy_Metal(metalRequest);
			metalAmount = metalAmount + metalRequest;
		}
	}
	
	@Override
	public void BuyStone(int stoneRequest) {
		if(money > myMarket.Buy_Stone(stoneRequest)){
			money = money - myMarket.Buy_Stone(stoneRequest);
			stoneAmount = stoneAmount + stoneRequest;
		}
	}
	
	@Override
	public void BuyWood(int woodRequest) {
		if(money > myMarket.Buy_Wood(woodRequest)){
			money = money - myMarket.Buy_Wood(woodRequest);
			woodAmount = woodAmount + woodRequest;
		}
	}

	@Override
	public void UpgradeBuilding(IBuilding buildingName) {
		if(money > buildingName.GetCost()){
			if(buildingName.Upgrade()){
				money = money - buildingName.GetCost();
			}
		}
	}

	@Override
	public void MakeMoney(int amount) {
		if(money < moneyCap){
			money = money + amount;
		}
		// if money cap is not hit.. then call generate money
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Walk() {
		// TODO Auto-generated method stub
		
	}

}
