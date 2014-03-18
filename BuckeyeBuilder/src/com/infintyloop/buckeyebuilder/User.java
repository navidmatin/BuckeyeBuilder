package com.infintyloop.buckeyebuilder;

public class User implements IUser {

	private String name;
	private int woodAmount;
	private int metalAmount;
	private int stoneAmount;
	private int moneyCap;
	// private Location location;
	private int money;
	
	@Override 
	public void User(){
		// somehow set value to all variables
	}

	@Override
	public void BuyMetal(int metalRequest) {
//		if(money > material.GetCost(metalRequest)){
//			money = money - material.GetCost(metalRequest);
			metalAmount = metalAmount + metalRequest;
	//	}
	}
	
	@Override
	public void BuyStone(int stoneRequest) {
//		if(money > material.GetCost(stoneRequest)){
	//		money = money - material.GetCost(stoneRequest);
			stoneAmount = stoneAmount + stoneRequest;
		//}
	}
	
	@Override
	public void BuyWood(int woodRequest) {
//		if(money > material.GetCost(woodRequest)){
	//		money = money - material.GetCost(woodRequest);
			woodAmount = woodAmount + woodRequest;
		//}
	}

	@Override
	public void UpgradeBuilding(String buildingName) {
//		if(money > buildingName.GetCost()){
	//		if(buildingName.Upgrade()){
			//	money = money - buildingName.GetCost();
		//	}
		//}
	}

	@Override
	public void MakeMoney(int amount) {
		// if money cap is not hit.. then call generate money
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Walk() {
		// TODO Auto-generated method stub
		
	}

}
