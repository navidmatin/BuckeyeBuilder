package com.infintyloop.buckeyebuilder;
import com.infintyloop.buckeyebuilder.IBuilding;
import com.infintyloop.buckeyebuilder.IUser;

public class BuildingFactory{
	private String[] BuildingNames = {"Hitchcock Hall","Bolz Hall","Dreese Laboratories","Scott Laboratories"};
	private int[][][] Cost = {{{100,100,100},{200,200,300},{300,400,400}},{{200,200,400},{300,300,400},{400,400,400}},{{50,100,200},{100,150,200},{150,175,200}}};
	private int[][] genRates = {{0,2},{3,4},{3,4}};
	private String[] description = {"building 1", "building 2", "building 3", "building 4"};
	public IBuilding[] listOfBuildings = new IBuilding[BuildingNames.length];
	private int paycheck;
	private IUser user;
	public void MakeBuildings(){
		for (int i=0;i < BuildingNames.length; i++){
			listOfBuildings[i] = new Building();
			listOfBuildings[i].GiveValuesToBuilding(BuildingNames[0],Cost[0], genRates[0], description[0]);
		}
	}
	public void AssignLevels(int[] levels, IUser theUser){
		user = theUser;
		for (int i=0; i < levels.length; i++){
			listOfBuildings[i].SetLevel(levels[i]);
		}
	}
	public IBuilding[] ReturnBuildingList(){
		return listOfBuildings;
	}
	public void PayUser(){
		paycheck = 0;
		for (int i = 0; i < BuildingNames.length; i++){
			if(listOfBuildings[i].GetLevel() > 0){
				paycheck = paycheck + listOfBuildings[i].GenerateMoney();
			}
		}
		user.MakeMoney(paycheck);
	}
}
