package com.infintyloop.buckeyebuilder;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

//import com.infintyloop.buckeyebuilder.DatabaseHelper.TicTacToeOpenHelper;


public class BuildingDatabaseHelper  {
	
	private static final String DATABASE_NAME = "BuildingData.db";
	   private static final int DATABASE_VERSION = 1;
	   private static final String TABLE_NAME = "UserData";
	   private Context context;
	   private SQLiteDatabase db;
	   private SQLiteStatement insertStmt;
	   private static final String INSERT = "insert into " + TABLE_NAME + "(name, password) values (?, ?)" ;
 
	   
	   public BuildingDatabaseHelper(Context context) {
		      this.context = context;
		      BuildingDatabaseHelper openHelper = new BuildingDatabaseHelper(this.context);
		      this.db = openHelper.getWritableDatabase();
		      this.insertStmt = this.db.compileStatement(INSERT);
		   }

	   private SQLiteDatabase getWritableDatabase() 
	   {
		   return null;
	   }
	   
	   
	   public void deleteAll() 
	   {
		    // this.db.delete(TABLE_NAME, null, null);
	   }
	   
	   
	   
	   
}