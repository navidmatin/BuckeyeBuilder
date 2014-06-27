package com.infinityloop.buckeyebuilderUtilities.databasehelper;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.infinityloop.buckeyebuilder.Building;
import com.infinityloop.buckeyebuilder.IUser;
import com.infinityloop.buckeyebuilder.R;
import com.infinityloop.buckeyebuilder.User;
import com.parse.ParseException;
import com.parse.ParseUser;
/**
 * A class that saves and reads the data from the sharedPreferences file
 * The data get saved as a JSON string, and this class read it back with help of
 * GSON library.
 */
public class DataHandler {
	public static void saveData(Context context, ArrayList<Building> buildingList, IUser user)
	{
		Gson gson= new Gson();
		ParseUser currentUser = ParseUser.getCurrentUser();
		//Saving Building data to local file
		SharedPreferences buildingSharedPref = context.getSharedPreferences(context.getString(R.string.buildings_file_name)+user.GetUsername(), Context.MODE_PRIVATE);
		SharedPreferences.Editor bspEditor = buildingSharedPref.edit();
		String buildingjson=gson.toJson(buildingList);
		bspEditor.putString("buildingList", buildingjson);
		bspEditor.commit();
		//Saving User data to local file
		SharedPreferences userSharedPref = context.getSharedPreferences(context.getString(R.string.user_file_name)+user.GetUsername(), Context.MODE_PRIVATE);
		SharedPreferences.Editor uEditor = userSharedPref.edit();
		String userjson=gson.toJson(user);
		uEditor.putString("user", userjson);
		uEditor.commit();
		if(currentUser!=null)
		{
			currentUser.put("buildingJSON", buildingjson);
			currentUser.put("userJSON", userjson);
			currentUser.saveInBackground();

		}
		
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
		else{
			ParseUser currentUser = ParseUser.getCurrentUser();
			if(currentUser!=null)
			{
				json=currentUser.getString("buildingJSON");
				if(json!=null)
				{
					ArrayList<Building> buildingList=gson.fromJson(json, new TypeToken<ArrayList<Building>>(){}.getType());
					return buildingList;
				}
			}
		}
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
		else {
			ParseUser currentUser = ParseUser.getCurrentUser();
			if(currentUser!=null)
			{
				json=currentUser.getString("userJSON");
				if(json!=null)
				{
					IUser user=gson.fromJson(json, new TypeToken<User>(){}.getType());
					return user;
				}
			}
			return null;
		}
	}
}
