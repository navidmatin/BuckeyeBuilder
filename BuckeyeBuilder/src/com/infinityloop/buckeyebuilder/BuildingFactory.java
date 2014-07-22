package com.infinityloop.buckeyebuilder;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.infinityloop.buckeyebuilder.Building;
import com.infinityloop.buckeyebuilder.IUser;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
/**
 * A class that is called if the user new, in order to generate all of the buildings
 */
public class BuildingFactory{
	private ArrayList<String> buildingNames;
	private ArrayList<Integer> costs;
	private ArrayList<Integer> genRates;
	private ArrayList<Double> latitudes;
	private ArrayList<Double> longitudes;
	private ArrayList<Double> radius;
	private ArrayList<String> descriptions;
	private String version;
	
	public ArrayList<Building> listOfBuildings = new ArrayList<Building>();

	public BuildingFactory(){
		buildingNames= new ArrayList<String>();
		costs= new ArrayList<Integer>();
		genRates= new ArrayList<Integer>();
		latitudes= new ArrayList<Double>();
		longitudes= new ArrayList<Double>();
		radius= new ArrayList<Double>();
		descriptions= new ArrayList<String>();
	}
	
	private void getBuildingsFromDatabase(String version){
		if(version==null)
		{
			ParseQuery<ParseObject> query = ParseQuery.getQuery("Building").orderByAscending("Name");
			query.setLimit(1000);
			List<ParseObject> objects;
			try {
				objects = query.find();
				for(ParseObject object : objects)
				{
					buildingNames.add(object.getString("Name"));
					latitudes.add(object.getDouble("lat"));
					longitudes.add(object.getDouble("longi"));
					descriptions.add(object.getString("description"));
					radius.add(object.getDouble("radius"));
					genRates.add(object.getInt("genRate"));
					costs.add(object.getInt("cost"));
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*
			query.find(new FindCallback<ParseObject>() {
				public void done(List<ParseObject> objects, ParseException e) {
					if(e == null) {
						Log.d("score", "Retrieved" + objects.size() + "buildings");
						for(ParseObject object : objects)
						{
							buildingNames.add(object.getString("Name"));
							descriptions.add(object.getString("description"));
							latitudes.add(object.getDouble("lat"));
							longitudes.add(object.getDouble("longi"));
							descriptions.add(object.getString("description"));
							radius.add(object.getDouble("radius"));
							genRates.add(object.getInt("genRate"));
						}
					} else {
						Log.d("score", "Error:" + e.getMessage());
					}
				}
			});*/
		}
		else
		{
			ParseQuery<ParseObject> query = ParseQuery.getQuery("Building");
			query.setLimit(1000);
			//Checking for the version of the building
			query.whereContains("version", version);
			query.findInBackground(new FindCallback<ParseObject>() {
				public void done(List<ParseObject> objects, ParseException e) {
					if(e == null) {
						Log.d("score", "Retrieved" + objects.size() + "buildings");
						for(ParseObject object : objects)
						{
							buildingNames.add(object.getString("Name"));
							descriptions.add(object.getString("description"));
							latitudes.add(object.getDouble("lat"));
							longitudes.add(object.getDouble("longi"));
							descriptions.add(object.getString("description"));
							radius.add(object.getDouble("radius"));
							genRates.add(object.getInt("genRate"));
						}
					} else {
						Log.d("score", "Error:" + e.getMessage());
					}
				}
			});
		}
	}
	public void MakeBuildings(){
		getBuildingsFromDatabase(null);
		for (int i=0;i < buildingNames.size(); i++){
			Building temp= new Building();
			temp.GiveValuesToBuilding(buildingNames.get(i), costs.get(i).intValue(), genRates.get(i).intValue(), descriptions.get(i), longitudes.get(i), latitudes.get(i), radius.get(i));
			temp.version=version;
			listOfBuildings.add(temp);
		}
	}
	public ArrayList<Building> updateBuildings(String version, ArrayList<Building> buildingList){
		getBuildingsFromDatabase(version);
		listOfBuildings = buildingList;
		for (int i=0;i < buildingNames.size(); i++){
			Building temp= new Building();
			temp.GiveValuesToBuilding(buildingNames.get(i),costs.get(i), genRates.get(i), descriptions.get(i), longitudes.get(i), latitudes.get(i), radius.get(i));
			temp.version=version;
			listOfBuildings.add(temp);
		}
		return listOfBuildings;
	}
	
	public void AssignLevels(int[] levels, IUser theUser){
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
	public static Building FindBuilding(String buildingName, ArrayList<Building> buildingList)
	{
		
		for(Building building: buildingList)
		{
			if(building.GetName().equals(buildingName))
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
