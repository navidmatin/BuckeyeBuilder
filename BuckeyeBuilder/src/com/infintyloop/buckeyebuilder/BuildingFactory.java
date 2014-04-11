package com.infintyloop.buckeyebuilder;
import java.util.ArrayList;

import com.infintyloop.buckeyebuilder.IBuilding;
import com.infintyloop.buckeyebuilder.IUser;

public class BuildingFactory{
	//made all public just a note in case in breaks anything
	public String[] BuildingNames = {"Hitchcock Hall","Bolz Hall","Dreese Laboratories","Scott Laboratories","Knowlton Hall", "Koffolt Laboratories","Lincoln Tower","Jennings Hall"};
	public int[] Cost = {100,200,300,200,300,400,500,100};
	public int[] genRates = {20,25,30,25,10,10,70,80};
	public double[] latitudes = {3.0,2.0,-83.015043,2.0,2.0,2.0,2.0,2.0};
	public double[] longitudes = {3.0,2.0,40.002402,2.0,2.0,2.0,2.0,2.0};
	public double[] radiusValues = {0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0};
	public String[] description = {"building 1", "building 2", "building 3", "building 4","building 5","building 6", "building 7", "building 8"};
	public BuildingDatabaseHelper buildingdatabasehelper;
	
	
	
	//Use the database for the above info^
	public ArrayList<IBuilding> listOfBuildings = new ArrayList<IBuilding>();
	
	private int paycheck;
	private IUser user;
	
	public void MakeBuildings(){
		for (int i=0;i < BuildingNames.length; i++){
			buildingdatabasehelper.addBuilding(BuildingNames[i],Cost[i], genRates[i], description[i], longitudes[i], latitudes[i], radiusValues[i]);
			IBuilding temp= new Building();
			temp.GiveValuesToBuilding(BuildingNames[i],Cost[i], genRates[i], description[i], longitudes[i], latitudes[i], radiusValues[i]);
			listOfBuildings.add(temp);
		}
	}
	
	public void AssignLevels(int[] levels, IUser theUser){
		user = theUser;
		for (int i=0; i < levels.length; i++){
			IBuilding x = listOfBuildings.get(i);
			int temp = levels[i];
			x.SetLevel(temp);
			listOfBuildings.set(i, x);
		}		
	}
	
	public ArrayList<IBuilding> ReturnBuildingList(){
		return listOfBuildings;
	}
	
	public void PayUser(){
		paycheck = 0;
		for (int i = 0; i < BuildingNames.length; i++){
			IBuilding temp = listOfBuildings.get(i); 
			if(temp.GetLevel() > 0){
				paycheck = paycheck + temp.GetCurrentGenRate();
			}
		}
		//user.MakeMoney();
	}
	
	public static IBuilding FindBuilding(String buildingName, ArrayList<IBuilding> buildingList)
	{
		
		for(IBuilding building: buildingList)
		{
			if(building.GetName()==buildingName)
				return building;
		}
		return null;
		
	}
}
