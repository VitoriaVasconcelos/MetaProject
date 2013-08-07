package com.project.meta.model;

import android.provider.BaseColumns;

public class MetaBaseHelper implements BaseColumns{
	public static final String NAME    = "name";
	public static final String DESCRIPTION   = "description";
	public static final String ORIGIN = "origin";
	public static final String DESTINATION = "destination";
	
	private static String[] columns = new String[] {MetaBaseHelper._ID, MetaBaseHelper.NAME, MetaBaseHelper.DESCRIPTION, MetaBaseHelper.ORIGIN, MetaBaseHelper.DESTINATION};
	
	public static String[] getColumnsMetas(){
		return columns;
	}
	
	public static String SCRIPT_DATABASE_CREATE = "CREATE TABLE metas ( _id integer primary key autoincrement, name text not null, description text);";  
	
//	public static  String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS metas";
	public static  String SCRIPT_DATABASE_UPDATE = "ALTER TABLE metas ADD COLUMN origin text, destination text;";
	
}
