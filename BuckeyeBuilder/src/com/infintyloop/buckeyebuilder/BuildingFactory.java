package com.infintyloop.buckeyebuilder;
import java.util.ArrayList;

import com.infinityloop.buckeyebuilder.databasehelper.BuildingDatabaseHelper;
import com.infintyloop.buckeyebuilder.Building;
import com.infintyloop.buckeyebuilder.IUser;
/**
 * A class that is called if the user new, in order to generate all of the buildings
 */
public class BuildingFactory{
	private String[] BuildingNames = {"Hitchcock Hall","Bolz Hall","Dreese Laboratories","Scott Laboratories","Caldwell Laboratories", 
	 "Koffolt Laboratories","Knowlton Hall","Jennings Hall","Baker Systems","Cockins Hall","Smith Laboratory","Dulles Hall",
	  "McPherson Chemical Lab"};
	
	private int[] Cost = {100,200,300,200,300,400,500,100,200,325,300,350,100};
	private int[] genRates = {20,25,30,25,400,10,70,80,10,100,20,60,250};
	private double[] latitudes = {40.003671,40.002989,40.002319,40.002295,40.002402,40.003613,40.003688,39.998670,40.001563,40.001196,40.002437,40.001115,40.002461};
	private double[] longitudes = {-83.015363,-83.014996,-83.015826,-83.014377,-83.015043, -83.012501,-83.016863,-83.039015,-83.015875, -83.014979, -83.013310, -83.014391, -83.012200};
	private double[] radiusValues = {0.000087,0.000049,0.000146,0.000078,0.000546,0.000246,0.000250,0.0005,0.0004,0.0004,0.0004,0.0005,0.0004};
	private String[] description = {
			"Building 1: Department of Civil and Environmental Engineering, Geodetic Science, Administration, Computer Services and Experiment Station Engineering Building",
			"Buidling 2: College Engineering, College Science, and College Academic Building",
			"Buidling 3: Computer Science and Engineering, Electrical and Computer Engineering Building", 
			"Buidling 4: Department of Mechanical and Aerospace Engineering",
			"Buidling 5: Computer Science and Engineering, Electrical and Computer Engineering Buildin",
			"Building 6: Chemical and Biomolecular Engineering", 
			"Buidling 7: Architecture Library, Austin E Knowlton School of Architecture, ksa caf�", 
			"Buidling 8: Introductory Biology Program",
			"Buidling 9: Integrated Systems Engineering, Office of the Chief Information Officer",
			"Building 10: Mathematics and Statistics Building",
			"Building 11: Anthropology Arts and Sciences Honors Advising, Astronomy, Engineering Experiment Station,Film Studies, Office of Energy and Environment, Physics,Public Health",
			"Building 12: History, Humanities Administration, Melton Center For Jewish Studies, The Center for Folklore Studies, Women's Studies",
			"Building 13: Astronomy and Chemistry Building"

			
			};
	
	
//	private String[] description = {
//			"Building 1","Buidling 2:","Buidling 3","Buidling 4","Buidling 5","Building 6","Buidling 7","Buidling 8","Buidling 9","Building 10","Building 11"};
//	
	//private BuildingDatabaseHelper buildingdatabasehelper;
	
	
	public ArrayList<Building> listOfBuildings = new ArrayList<Building>();
	
	private int paycheck;
	private IUser user;
	
	public void MakeBuildings(){
		for (int i=0;i < BuildingNames.length; i++){

		//	buildingdatabasehelper.addBuilding(BuildingNames[i],Cost[i], genRates[i], description[i], longitudes[i], latitudes[i], radiusValues[i]);
			Building temp= new Building();
			temp.GiveValuesToBuilding(BuildingNames[i],getCost()[i], genRates[i], description[i], longitudes[i], latitudes[i], radiusValues[i]);
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

	public String[] getBuildingNames() {
		return BuildingNames;
	}

	public void setBuildingNames(String[] buildingNames) {
		BuildingNames = buildingNames;
	}

	public int[] getCost() {
		return Cost;
	}

	public void setCost(int[] cost) {
		Cost = cost;
	}
}
