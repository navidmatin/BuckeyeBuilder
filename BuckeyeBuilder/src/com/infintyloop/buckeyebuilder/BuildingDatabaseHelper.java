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
	
	BuildingFactory buildingfactory;
	private static final String DATABASE_NAME = "BuildingData.db";
	   private static final int DATABASE_VERSION = 1;
	   private static final String TABLE_NAME = "UserData";
	   public String sql;
	   //private Context context;
	   private SQLiteDatabase db;
	   private SQLiteStatement insertStmt;
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
		     sql = "CREATE TABLE IF NOT EXISTS UserData (" +
                   "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				   "buildingname TEXT," +
                   "cost INTEGER, " +
                   "genRates INTEGER, " +
                   "latitudes REAL, " +
                   "longitudes REAL, " +
                   "radiusValues REAL, " +
                   "description TEXT, ";
		   	db.execSQL(sql);
  
	   }
	   
	   
	   public long insert(String buildingname, int cost,int genRates,double latitudes,double longitudes,double radiusValues,String description) {
		      this.insertStmt.bindString(1, buildingname);
		      this.insertStmt.bindLong(2, cost);
		      this.insertStmt.bindLong(3, genRates);
		      this.insertStmt.bindDouble(4,latitudes);
		      this.insertStmt.bindDouble(5, longitudes);
		      this.insertStmt.bindDouble(6, radiusValues);
		      this.insertStmt.bindString(7, description);
		   
		      return this.insertStmt.executeInsert();
		   }
	   
	   public void deleteAll() {

		      this.db.delete(TABLE_NAME, null, null);
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