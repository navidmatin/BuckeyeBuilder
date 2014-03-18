package com.infintyloop.buckeyebuilder;

public interface IUser {
	public void User();
	public void BuyWood(int woodRequest);
	public void BuyMetal(int metalRequest);
	public void BuyStone(int stoneRequest);
	public void UpgradeBuilding(String building);
	public void MakeMoney(int amount);
	public void Walk();
}
