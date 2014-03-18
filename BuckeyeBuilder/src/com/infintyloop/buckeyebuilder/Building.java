package com.infintyloop.buckeyebuilder;

public class Building implements IBuilding {
	private String name;
	private int woodRequired;
	private int metalRequired;
	private int stoneRequired;
	private int level;
	private int level1Cost;
	private int level2Cost;
	private int level3Cost;
	//private Location location;
	private double radius;
	private int currentCost;
	
	@Override
	public void Building() {
		// somehow need to set a value to all variables...
		if(level == 0){
			currentCost = level1Cost;
		}
		else if(level == 1){
			currentCost = level2Cost;
		}
		else{
			currentCost = level3Cost;
		}
	}

	@Override
	public String GetName() {
		return name;
	}

	@Override
	public int GetLevel() {
		return level;
	}

	@Override
	public int GetCost() {
		return currentCost;
	}

	@Override
	public boolean Upgrade() {
		if(level < 2){
			level = level + 1;
			return true;
		}
		else {
		// error message: No more available upgrades!
			return false;
		}
	}

}
