package com.project.meta.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{

	private String scriptSQLCreate;
	private String scriptSQLUpdate;
	
	public DatabaseHandler(Context context, String nomeBanco, int versaoBanco, String scriptSQLCreate, String scriptSQLUpdate) {
		super(context, nomeBanco, null, versaoBanco);
		this.scriptSQLCreate = scriptSQLCreate;
		this.scriptSQLUpdate = scriptSQLUpdate;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(scriptSQLCreate);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion < 2) {
	        db.execSQL(scriptSQLUpdate);
	    }
//		db.execsql(scriptsqldelete);
//		oncreate(db);
		
	}

}
