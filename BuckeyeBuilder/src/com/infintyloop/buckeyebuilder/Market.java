package com.infintyloop.buckeyebuilder;

public class Market {
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
 
 
 
	
	
	
	
}
