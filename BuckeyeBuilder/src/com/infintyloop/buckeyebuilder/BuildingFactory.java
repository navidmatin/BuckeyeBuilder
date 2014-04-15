package com.infintyloop.buckeyebuilder;
import java.util.ArrayList;

import com.infinityloop.buckeyebuilder.databasehelper.BuildingDatabaseHelper;
import com.infintyloop.buckeyebuilder.Building;
import com.infintyloop.buckeyebuilder.IUser;

public class BuildingFactory{
	private String[] BuildingNames = {"Hitchcock Hall","Bolz Hall","Dreese Laboratories","Scott Laboratories","Caldwell Laboratories", "Koffolt Laboratories","Lincoln Tower","Jennings Hall"};
	private int[] Cost = {100,200,300,200,300,400,500,100};
	private int[] genRates = {20,25,30,25,10,10,70,80};
	private double[] latitudes = {40.003671,40.002989,40.002319,40.002295,40.002402,2.0,2.0,39.998670};
	private double[] longitudes = {-83.015363,-83.014996,-83.015826,-83.014377,-83.015043,2.0,2.0,-83.039015};
	private double[] radiusValues = {0.000087,0.000049,0.000146,0.000378,0.000246,0.0,0.0,0.0005};
	private String[] description = {"building 1", "building 2", "building 3", "building 4","building 5","building 6", "building 7", "building 8"};
	private BuildingDatabaseHelper buildingdatabasehelper;
	
	
	
	//Use the database for the above info^
	public ArrayList<Building> listOfBuildings = new ArrayList<Building>();
	
	private int paycheck;
	private IUser user;
	
	public void MakeBuildings(){
		for (int i=0;i < BuildingNames.length; i++){

		//	buildingdatabasehelper.addBuilding(BuildingNames[i],Cost[i], genRates[i], description[i], longitudes[i], latitudes[i], radiusValues[i]);
			Building temp= new Building();
			temp.GiveValuesToBuilding(BuildingNames[i],Cost[i], genRates[i], description[i], longitudes[i], latitudes[i], radiusValues[i]);
			listOfBuildings.add(temp);
		}
	}
	
	public void AssignLevels(int[] levels, IUser theUser){
		user = theUser;
		for (int i=0; i < levels.length; i++){
			Building x = listOfBuildings.get(i);
			int temp = levels[i];
			x.SetLevel(temp);
			listOfBuildings.set(i, x);
		}		
	}
	
	public ArrayList<Building> ReturnBuildingList(){
		return listOfBuildings;
	}
	
	public void PayUser(){
		paycheck = 0;
		for (int i = 0; i < BuildingNames.length; i++){
			Building temp = listOfBuildings.get(i); 
			if(temp.GetLevel() > 0){
				paycheck = paycheck + temp.GetCurrentGenRate();
			}
		}
		//user.MakeMoney();
	}
	
	public static Building FindBuilding(String buildingName, ArrayList<Building> buildingList)
	{
		
		for(Building building: buildingList)
		{
			if(building.GetName()==buildingName)
				return building;
		}
		return null;
		
	}
	public static ArrayList<Building> addBuildingtoTheList(Building building, ArrayList<Building> buildingList)
	{
		String name=building.GetName();
		for (Building _building: buildingList)
		{
			if(_building.GetName()==name)
			{
				_building=building;
				return buildingList;
			}
		}
		return null;
	}
}
