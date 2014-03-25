package com.infintyloop.buckeyebuilder;
import com.infintyloop.buckeyebuilder.IBuilding;

public class BuildingFactory{
	String[] BuildingNames = {"Hitchcock Hall","Bolz Hall","Dreese Laboratories","Scott Laboratories"};
	int[][] Cost = {{100,200,300},{400,400,400},{200,200,200}};
	int[][] genRates = {{0,2},{3,4}};
	String[] description = {"building 1", "building 2", "building 3", "building 4"};
	IBuilding[] listOfBuildings = new IBuilding[BuildingNames.length];
	public void MakeBuildings(){
		for (int i=0;i < BuildingNames.length; i++){
			listOfBuildings[i] = new Building();
			listOfBuildings[i].Building(BuildingNames[0],Cost[0], genRates[0], description[0]);
		}
	}
	public IBuilding ReturnBuildingReference(String name){
		for (int i = 0; i <BuildingNames.length; i++){
			if(listOfBuildings[i].GetName() == name){
				return listOfBuildings[0];
			}
		}
		return null;
	}
}
