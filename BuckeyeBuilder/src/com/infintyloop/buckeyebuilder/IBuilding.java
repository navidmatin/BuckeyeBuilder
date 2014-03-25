package com.infintyloop.buckeyebuilder;

public interface IBuilding {
	public void Building(String theName, int theCost);
	public String GetName();
	public int GetLevel();
	public int GetCost();
	public boolean Upgrade();
	public int Update();
}
