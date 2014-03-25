package com.infintyloop.buckeyebuilder;
//import com.infintyloop.buckeyebuilder.IBuilding;

public interface IUser {
	public void User(String userName,int[] amounts);
	public void BuyMetal(int metalRequest);
	public void BuyStone(int stoneRequest);
	public void BuyWood(int woodRequest);
	public void UpgradeBuilding(IBuilding building);
	public void MakeMoney(int amount);
	public void Walk();
}
