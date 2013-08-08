package com.project.meta.repository;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.project.meta.model.Meta;
import com.project.meta.model.MetaBaseHelper;

public class MetaRepository {
	
	private static MetaRepository instance;
	private static Context mContext;
	private static final String DATABASE_NAME = "metas_database";
	public static final String TABLE_NAME = "metas";
	private static final int DATABASE_VERSION = 6;
	protected SQLiteDatabase db;
	private DatabaseHandler dbHelper;
	
	
	private MetaRepository(Context context){
		mContext = context;
		openDatabase();
	}
	
	private void openDatabase() {
		try {
			closeDatabase();
				
			dbHelper = new DatabaseHandler(mContext, DATABASE_NAME, DATABASE_VERSION,
					MetaBaseHelper.SCRIPT_DATABASE_CREATE, MetaBaseHelper.SCRIPT_DATABASE_DELETE);
			db = dbHelper.getWritableDatabase();
			
			//TODO fazer uma classe de exceção para a gente
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	public void closeDatabase() {
		if (db != null) {
			try {
				db.close();
			} catch (SQLException sqe) {
				sqe.printStackTrace();
			}
		}
	}
	
	public static MetaRepository getInstance(Context context) {
		if (instance == null) {
			instance = new MetaRepository(context);
		}
		
		return instance;
	}
	
	public void addMeta(Meta meta) {
		ContentValues values = new ContentValues();
		values.put(MetaBaseHelper.NAME, meta.getName());
		values.put(MetaBaseHelper.DESCRIPTION, meta.getDescription()); 
		values.put(MetaBaseHelper.ORIGIN, meta.getOrigin());
		values.put(MetaBaseHelper.DESTINATION, meta.getDestination());

		try {
			db.insert(TABLE_NAME, null, values);
		} catch(SQLiteException se) {
			se.printStackTrace();
			//TODO Colocar no log uma classe de exceção nossa
		}
	}

	public Meta getMeta(int id) {
		Meta meta = null;
		try {
			Cursor cursor = db.query(TABLE_NAME, MetaBaseHelper.getColumnsMetas(), MetaBaseHelper._ID + "= ?",
					new String[] { String.valueOf(id) }, null, null, null, null);
			
			if (cursor != null && cursor.moveToFirst()) {
				int idIndex = cursor.getColumnIndex(MetaBaseHelper._ID);
				int nameIndex = cursor.getColumnIndex(MetaBaseHelper.NAME);
				int descriptionIndex = cursor.getColumnIndex(MetaBaseHelper.DESCRIPTION);
				int originIndex = cursor.getColumnIndex(MetaBaseHelper.ORIGIN);
				int destinationIndex = cursor.getColumnIndex(MetaBaseHelper.DESTINATION);
				meta = new Meta(cursor.getInt(idIndex), cursor.getString(nameIndex), cursor.getString(descriptionIndex));
				meta.setOrigin(cursor.getString(originIndex));
				meta.setDestination(cursor.getString(destinationIndex));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		return meta;
	}
	
	public List<Meta> getAllMetas() {
		List<Meta> metaList = new ArrayList<Meta>();

		try {
			Cursor cursor = db.query(TABLE_NAME, MetaBaseHelper.getColumnsMetas(), null, null, null, null, null);
	
			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				int idIndex = cursor.getColumnIndex(MetaBaseHelper._ID);
				int nameIndex = cursor.getColumnIndex(MetaBaseHelper.NAME);
				int descriptionIndex = cursor.getColumnIndex(MetaBaseHelper.DESCRIPTION);
				int originIndex = cursor.getColumnIndex(MetaBaseHelper.ORIGIN);
				int destinationIndex = cursor.getColumnIndex(MetaBaseHelper.DESTINATION);
				
				do {
					Meta meta = new Meta();
					meta.setID(cursor.getInt(idIndex));
					meta.setName(cursor.getString(nameIndex));
					meta.setDescription(cursor.getString(descriptionIndex));
					meta.setOrigin(cursor.getString(originIndex));
					meta.setDestination(cursor.getString(destinationIndex));

					metaList.add(meta);
				} while (cursor.moveToNext());
			}
		} catch (SQLException es) {
			// TODO: handle exception
		}

		return metaList;
	}

	public int updateMeta(Meta meta) {

		ContentValues values = new ContentValues();
		values.put(MetaBaseHelper.NAME, meta.getName());
		values.put(MetaBaseHelper.DESCRIPTION, meta.getDescription());
		values.put(MetaBaseHelper.ORIGIN, meta.getOrigin());
		values.put(MetaBaseHelper.DESTINATION, meta.getDestination());
		int rowsAffected = 0;

		try {
			rowsAffected = db.update(TABLE_NAME, values, MetaBaseHelper._ID + " = ?", new String[] { String.valueOf(meta.getID()) });
		} catch(SQLException se) {
			
		}
		
		return rowsAffected;
	}

	public void deleteMeta(Meta meta) {
		db.delete(TABLE_NAME, MetaBaseHelper._ID + " = ?", new String[] { String.valueOf(meta.getID()) });
	}
	
}
