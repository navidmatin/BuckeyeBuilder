package com.infintyloop.buckeyebuilder;
import java.util.ArrayList;

import com.infinityloop.buckeyebuilder.databasehelper.BuildingDatabaseHelper;
import com.infintyloop.buckeyebuilder.Building;
import com.infintyloop.buckeyebuilder.IUser;

public class BuildingFactory{
	private String[] BuildingNames = {"Hitchcock Hall","Bolz Hall","Dreese Laboratories","Scott Laboratories","Caldwell Laboratories", 
	 "Koffolt Laboratories","Knowlton Hall","Jennings Hall","Baker Systems"};
	
	private int[] Cost = {100,200,300,200,300,400,500,100};
	private int[] genRates = {20,25,30,25,10,10,70,80};
	private double[] latitudes = {40.003671,40.002989,40.002319,40.002295,40.002402,40.003613,40.003688,39.998670,40.001563};
	private double[] longitudes = {-83.015363,-83.014996,-83.015826,-83.014377,-83.015043, -83.012501,-83.016863,-83.039015,-83.015875};
	private double[] radiusValues = {0.000087,0.000049,0.000146,0.000378,0.000246,0.000246,0.000250,0.0005,0.0004};
	private String[] description = {
			"Department of Civil and Environmental Engineering, Geodetic Science, Administration, Computer Services and Experiment Station Engineering Building",
			"College Engineering, College Science, and College Academic Building",
			"Computer Science and Engineering, Electrical and Computer Engineering Building", 
			"Department of Mechanical and Aerospace Engineering",
			"Computer Science and Engineering, Electrical and Computer Engineering Buildin",
			"Chemical and Biomolecular Engineering", 
			"Architecture Library, Austin E Knowlton School of Architecture, ksa café", 
			"Introductory Biology Program",
			"Integrated Systems Engineering, Office of the Chief Information Officer"
			};
	//private BuildingDatabaseHelper buildingdatabasehelper;
	
	
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
