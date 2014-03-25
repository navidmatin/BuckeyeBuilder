package com.infintyloop.buckeyebuilder;

public interface IBuilding {
	public void Building(String theName, int[] theCost, int[] theGenRates, String theDescription);
	public void SetLevel(int incomingLevel);
	public String GetName();
	public int GetLevel();
	public int GetCurrentCost();
	public String GetDescription();
	public int[] GetCosts();
	public int[] GetGenRates();
	public boolean Upgrade();
	public int Update();
}
