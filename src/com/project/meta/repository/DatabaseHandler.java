package com.project.meta.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{

	private String scriptSQLCreate;
	private String scriptSQLDelete;
	
	public DatabaseHandler(Context context, String nomeBanco, int versaoBanco, String scriptSQLCreate, String scriptSQLDelete) {
		super(context, nomeBanco, null, versaoBanco);
		this.scriptSQLCreate = scriptSQLCreate;
		this.scriptSQLDelete = scriptSQLDelete;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(scriptSQLCreate);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(scriptSQLDelete);
		onCreate(db);
		
	}

}
