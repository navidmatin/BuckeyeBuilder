package com.infintyloop.buckeyebuilder;
import com.infintyloop.buckeyebuilder.IBuilding;

public class BuildingFactory{
	String[] BuildingNames = {"Hitchcock Hall","Bolz Hall","Dreese Laboratories","Scott Laboratories"};
	int[] Cost = {100,200,300,400};
	public void MakeBuildings(){
		for (int i=0;i < BuildingNames.length; i++){
			IBuilding j = new Building();
			j.Building(BuildingNames[i],Cost[i]);
		}
	}
}
