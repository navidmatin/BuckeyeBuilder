package com.infinityloop.buckeyebuilderUtilities.databasehelper;

/**
 * a Database helper for buildings, Not being Used!
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.infinityloop.buckeyebuilder.BuildingFactory;

import android.content.ContentValues;
import android.database.Cursor;

//import com.infinityloop.buckeyebuilder.DatabaseHelper.TicTacToeOpenHelper;


public abstract class BuildingDatabaseHelper extends SQLiteOpenHelper    {
	
	   
	   private static final String DATABASE_NAME = "BuildingData.db";
	   private static final int DATABASE_VERSION = 1;
	   private static final String TABLE_NAME = "UserData";
	   private SQLiteDatabase db;
	   private SQLiteStatement insertStmt;
	   public static final String KEY_ID = "_id";
	   public String sql;
	   public BuildingFactory buildingfactory;
	   public int i=0;
	 
	   
	   public BuildingDatabaseHelper(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }
 
	   public SQLiteDatabase openDB() {
	        db = this.getWritableDatabase();
	        return db;
	    }
	   
	   public void onCreate(SQLiteDatabase db) {
		   
		   //Creates the Record
		     sql = "CREATE TABLE IF NOT EXISTS UserData (" +
                   "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				   "buildingname TEXT," +
                   "cost INTEGER, " +
                   "genRates INTEGER, " +
                   "description TEXT, "+
                   "longitudes REAL, " +
                   "latitudes REAL, " +
                   "radiusValues REAL, " ;
                   
		   	db.execSQL(sql);
  
	   }
	   
	   
	   public long insert(String buildingname, int cost,int genRates,String description,double longitudes,double latitudes,double radiusValues) {
		      this.insertStmt.bindString(1, buildingname);
		      this.insertStmt.bindLong(2, cost);
		      this.insertStmt.bindLong(3, genRates);
		      this.insertStmt.bindString(4, description);
		      this.insertStmt.bindDouble(5, longitudes);
		      this.insertStmt.bindDouble(6,latitudes);
		      this.insertStmt.bindDouble(7, radiusValues);
		      
		      return this.insertStmt.executeInsert();
		   }
	   
	   public void deleteAll() {

		      this.db.delete(TABLE_NAME, null, null);
		   }
	   
	   
	   public List<String> selectAll(String buildingname, int cost,int genRates,String description,double longitudes,double latitudes,double radiusValues) {
		      List<String> list = new ArrayList<String>();
		      Cursor cursor = this.db.query(TABLE_NAME, new String[]{"buildingname","cost","genRates","description,","longitudes","latitudes","radiusValues"},"name = '"+ buildingname + cost+genRates+description+longitudes+latitudes+radiusValues, null, null, null, "name desc");
		      if (cursor.moveToFirst()) {
		        do {
		        	 list.add(cursor.getString(0));
		        	 list.add(cursor.getString(1));
		        	 list.add(cursor.getString(2));
		        	 list.add(cursor.getString(3));
		        	 list.add(cursor.getString(4));
		        	 list.add(cursor.getString(5));
		        	 list.add(cursor.getString(6));
		         } while (cursor.moveToNext()); 
		      }
		      if (cursor != null && !cursor.isClosed()) {
		         cursor.close();
		      }
		      return list;
		   }
	   

	  
	   @Override
	   //If database verison is changed then deletes all tables and recreates them
	      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	         Log.w("Example", "Upgrading database; this will drop and recreate the tables.");
	         db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	         onCreate(db);
	      }
	   
	   
	
	     
	   
}