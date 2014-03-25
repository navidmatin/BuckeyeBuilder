package com.infintyloop.buckeyebuilder;

public class Building implements IBuilding {
	private String name;
	private int woodRequired;
	private int metalRequired;
	private int stoneRequired;
	private int level;
	private int levelCosts[] = new int[3];
	private int genCosts[] = new int[2];
	//private Location location;
	private double radius;
	private int currentCost;
	private String description;
	
	@Override
	public void Building(String theName, int[] theCost, int[] theGenRates, String theDescription) {
		// somehow need to set a value to all variables...
		name = theName;
		levelCosts[0] = theCost[0];
		levelCosts[1] = theCost[1];
		levelCosts[2] = theCost[2];
		genCosts[0] = theGenRates[0];
		genCosts[1] = theGenRates[1];
		description = theDescription;
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
	public int GetCurrentCost() {
		if(level == 0){
			currentCost = levelCosts[0];
		}
		else if(level == 1){
			currentCost = levelCosts[1];
		}
		else{
			currentCost = levelCosts[2];
		}
		return currentCost;
	}

	@Override
	public String GetDescription(){
		return description;
	}
	
	@Override
	public int[] GetCosts() {
		return levelCosts;
	}
	
	@Override
	public int[] GetGenRates() {
		return genCosts;
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
	
	
	@Override
	public int Update(){
	// once everywhatever
		if (level == 1){
			return 1;
		}
		else if (level == 2){
			return 2;
		}
		else {
			return 0;
		}
	}

}
