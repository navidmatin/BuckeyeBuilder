package com.infinityloop.buckeyebuilder.databasehelper;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.infintyloop.buckeyebuilder.Building;
import com.infintyloop.buckeyebuilder.IUser;
import com.infintyloop.buckeyebuilder.R;
import com.infintyloop.buckeyebuilder.User;
/**
 * A class that saves and reads the data from the sharedPreferences file
 * The data get saved as a JSON string, and this class read it back with help of
 * GSON library.
 */
public class DataHandler {
	public static void saveData(Context context, ArrayList<Building> buildingList, IUser user)
	{
		Gson gson= new Gson();
		//Saving Building data
		SharedPreferences buildingSharedPref = context.getSharedPreferences(context.getString(R.string.buildings_file_name)+user.GetUsername(), Context.MODE_PRIVATE);
		SharedPreferences.Editor bspEditor = buildingSharedPref.edit();
		String json=gson.toJson(buildingList);
		bspEditor.putString("buildingList", json);
		bspEditor.commit();
		//Saving User data
		SharedPreferences userSharedPref = context.getSharedPreferences(context.getString(R.string.user_file_name)+user.GetUsername(), Context.MODE_PRIVATE);
		SharedPreferences.Editor uEditor = userSharedPref.edit();
		json=gson.toJson(user);
		uEditor.putString("user", json);
		uEditor.commit();
		
	}
	public static ArrayList<Building> getBuildingListData(Context context, String username)
	{
		Gson gson= new Gson();
		SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.buildings_file_name)+username, Context.MODE_PRIVATE);
		String json=sharedPref.getString("buildingList",null);
		if(json!=null){
			ArrayList<Building> buildingList=gson.fromJson(json, new TypeToken<ArrayList<Building>>(){}.getType());
			return buildingList;
		}
		else
			return null;
	}
	public static IUser getUserData(Context context, String username)
	{
		Gson gson= new Gson();
		SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.user_file_name)+username, Context.MODE_PRIVATE);
		String json=sharedPref.getString("user",null);
		if(json!=null){
			IUser user=gson.fromJson(json, new TypeToken<User>(){}.getType());
			return user;
		}
		else
			return null;
	}
}
