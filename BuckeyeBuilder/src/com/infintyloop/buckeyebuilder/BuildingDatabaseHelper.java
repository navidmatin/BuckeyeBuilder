package com.infintyloop.buckeyebuilder;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.database.Cursor;

//import com.infintyloop.buckeyebuilder.DatabaseHelper.TicTacToeOpenHelper;


public abstract class BuildingDatabaseHelper extends SQLiteOpenHelper    {
	
	private static final String DATABASE_NAME = "BuildingData.db";
	   private static final int DATABASE_VERSION = 1;
	   private static final String TABLE_NAME = "UserData";
	   //private Context context;
	   private SQLiteDatabase db;
	   //private SQLiteStatement insertStmt;
	   public static final String KEY_ID = "_id";
	  // private static final String INSERT = "insert into " + TABLE_NAME + "(name, password) values (?, ?)" ;
	   
	   public BuildingDatabaseHelper(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }
 
	   public SQLiteDatabase openDB() {
	        db = this.getWritableDatabase();
	        return db;
	    }
	   
	   
	   public void onCreate(SQLiteDatabase db) {
		   
		   
		   //Creates the Record
		   String sql = "CREATE TABLE IF NOT EXISTS UserData (" +
                   "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				   "buildingname TEXT," +
                   "cost INTEGER, " +
                   "genRates INTEGER, " +
                   "latitudes REAL, " +
                   "longitudes REAL, " +
                   "radiusValues REAL, " +
                   "description TEXT, ";
		   	db.execSQL(sql);
 
		   	
		   	
		    ContentValues values = new ContentValues();
		    //adds hardcoded values to the database
		    values.put("buildingname", "Hitchcock Hall");
		    values.put("cost", "100");
            values.put("genRates", "20");
            values.put("latitudes", "3.0");
            values.put("longitudes", "3.0");
            values.put("radiusValues", "3.0");
            values.put("description", "building 1");
            //Inserts data into id=0
            db.insert("UserData", null, values);
            
            values.put("buildingname", "Bolz Hall");
		    values.put("cost", "200");
            values.put("genRates", "25");
            values.put("latitudes", "2.0");
            values.put("longitudes", "2.0");
            values.put("radiusValues", "2.0");
            values.put("description", "building 2");
            //Inserts data into id=1
            db.insert("UserData", null, values);
            
            
            values.put("buildingname", "Dreese Laboratories");
		    values.put("cost", "300");
            values.put("genRates", "30");
            values.put("latitudes", "1.0");
            values.put("longitudes", "1.0");
            values.put("radiusValues", "1.0");
            values.put("description", "building 3");
            //Inserts data into id=2
            db.insert("UserData", null, values);
            
            values.put("buildingname", "Scott Laboratories");
		    values.put("cost", "200");
            values.put("genRates", "25");
            values.put("latitudes", "2.0");
            values.put("longitudes", "2.0");
            values.put("radiusValues", "2.0");
            values.put("description", "building 4");
            //Inserts data into id=3
            db.insert("UserData", null, values);


            values.put("buildingname", "Knowlton Hall");
		    values.put("cost", "300");
            values.put("genRates", "10");
            values.put("latitudes", "2.0");
            values.put("longitudes", "2.0");
            values.put("radiusValues", "2.0");
            values.put("description", "building 5");
            //Inserts data into id=4
            db.insert("UserData", null, values);
            
            
            values.put("buildingname", "Koffolt Laboratories");
		    values.put("cost", "400");
            values.put("genRates", "10");
            values.put("latitudes", "2.0");
            values.put("longitudes", "2.0");
            values.put("radiusValues", "2.0");
            values.put("description", "building 6");
            //Inserts data into id=5
            db.insert("UserData", null, values);
            
            values.put("buildingname", "Lincoln Tower");
		    values.put("cost", "500");
            values.put("genRates", "70");
            values.put("latitudes", "2.0");
            values.put("longitudes", "2.0");
            values.put("radiusValues", "2.0");
            values.put("description", "building 7");
            //Inserts data into id=6
            db.insert("UserData", null, values);
            
            values.put("buildingname", "Jennings Hall");
		    values.put("cost", "100");
            values.put("genRates", "80");
            values.put("latitudes", "2.0");
            values.put("longitudes", "2.0");
            values.put("radiusValues", "2.0");
            values.put("description", "building 8");
            //Inserts data into id=6
            db.insert("UserData", null, values);
		    
		    
	   }
	   
	   
	   public Cursor getAllRecords()
       {
           return db.query("UserData", new String[] {
                   KEY_ID,
                   "buildingname",
                   "cost", 
                   "genRates",
                   "latitudes",
                   "longitudes", 
                   "radiusValues",
                   "description"
           	},
           			null, 
           			null, 
           			null, 
           			null,
           			null);
       	}
	   
	  
	   @Override
	   //If database verison is changed then deletes all tables and recreates them
	      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	         Log.w("Example", "Upgrading database; this will drop and recreate the tables.");
	         db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	         onCreate(db);
	      }
	   
	   
}