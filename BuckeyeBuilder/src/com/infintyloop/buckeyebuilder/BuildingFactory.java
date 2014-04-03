package com.infintyloop.buckeyebuilder;
import java.util.ArrayList;

import com.infintyloop.buckeyebuilder.IBuilding;
import com.infintyloop.buckeyebuilder.IUser;

public class BuildingFactory{
	private String[] BuildingNames = {"Hitchcock Hall","Bolz Hall","Dreese Laboratories","Scott Laboratories","Knowlton Hall", "Koffolt Laboratories","Lincoln Tower","Jennings Hall"};
	private int[] Cost = {100,200,300,200,300,400,500,100};
	private int[] genRates = {20,25,30,25,10,10,70,80};
	private String[] description = {"building 1", "building 2", "building 3", "building 4","building 5","building 6", "building 7", "building 8"};
	public ArrayList<IBuilding> listOfBuildings = new ArrayList<IBuilding>();
	private int paycheck;
	private IUser user;
	
	public void MakeBuildings(){
		for (int i=0;i < BuildingNames.length; i++){
			IBuilding temp= new Building();
			temp.GiveValuesToBuilding(BuildingNames[i],Cost[i], genRates[i], description[i]);
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
		user.MakeMoney(paycheck);
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
