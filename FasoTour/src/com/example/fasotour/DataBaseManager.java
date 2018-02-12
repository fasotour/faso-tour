package com.example.fasotour;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DataBaseManager extends SQLiteOpenHelper{
	
	private static String DB_PATH = "data/data/com.example.fasotour/databases";
	private static String DB_NAME = "mabase";
	private static int DB_VERSION = 1;
	private static String DATABASE_TABLE = "mybase";
	private SQLiteDatabase myDataBase;
	private final Context myContext;
	
	String sqltable="create table Agence (id_agence INTEGER NOT NULL PRIMARY KEY, nom_agence TEXT NOT NULL)";
	public DataBaseManager(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.myContext = context;
		// TODO Auto-generated constructor stub
	}
	
	//===================================================================================================================
	public void createDataBase() throws IOException{
		
		boolean dbExist= checkDataBase();
		 
		if(dbExist){
			Log.i("tag", "dbexist "+dbExist);
		}else{
			Log.i("tag", "dbnotexist "+dbExist);
			this.getReadableDatabase();
			copyDataBase();
		}
		
	}
	
	private void copyDataBase() throws IOException{
		
		InputStream myInput= myContext.getAssets().open(DB_NAME );
		String outFileName = DB_PATH + DB_NAME;
		OutputStream myOutput= new FileOutputStream(outFileName);
		byte[] buffer=new byte[1024];
		int lenght;
		while((lenght=myInput.read(buffer))>0){
			myOutput.write(buffer,0,lenght);
		}
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}
	
	private boolean checkDataBase(){
		SQLiteDatabase checkDB=null;
		try{
			String mypath=DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READONLY);
			
		}catch(SQLException e){
			
		}
		if(checkDB !=null){
			checkDB.close();
		}
		return checkDB !=null ? true : false;
	}
	
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//db.execSQL(sqltable);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
