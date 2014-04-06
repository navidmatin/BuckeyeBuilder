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


//import com.infintyloop.buckeyebuilder.DatabaseHelper.TicTacToeOpenHelper;


public abstract class BuildingDatabaseHelper extends SQLiteOpenHelper    {
	
	private static final String DATABASE_NAME = "BuildingData.db";
	   private static final int DATABASE_VERSION = 1;
	   private static final String TABLE_NAME = "UserData";
	   private Context context;
	   private SQLiteDatabase db;
	   private SQLiteStatement insertStmt;
	  // private static final String INSERT = "insert into " + TABLE_NAME + "(name, password) values (?, ?)" ;
	   
	   public BuildingDatabaseHelper(Context context) {
		   //this.context = context;
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }
 
	   public SQLiteDatabase openDB() {
	        db = this.getWritableDatabase();
	        return db;
	    }
	   
	   
	   public void onCreate(SQLiteDatabase db) {
		   
		   String sql = "CREATE TABLE IF NOT EXISTS userdata (" +
                   "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   "cost INTEGER, " +
                   "genRates INTEGER, " +
                   "latitudes REAL, " +
                   "longitudes REAL, " +
                   "radiusValues REAL, " +
                   "description TEXT, ";
		   	db.execSQL(sql);
 
		   	
		   	
		    ContentValues values = new ContentValues();
		    //adds hardcoded values to the database
		    values.put("cost", "Hitchcock Hall");
            values.put("genRates", "100");
            values.put("latitudes", "3.0");
            values.put("longitudes", "3.0");
            values.put("radiusValues", "3.0");
            values.put("description", "building 1");
            //need to figure out how
            //db.insert("employee", sql, values);

		    
		    
	   }
	   
	   
	   
	   

	   
	   
}