package com.meta.project;

public class Meta {
	
	//private variables
	int _id;
	String _name;
	String _description;
	
	// Empty constructor
	public Meta(){
		
	}
	
	// constructor
	public Meta(int id, String name, String _phone_number){
		this._id = id;
		this._name = name;
		this._description = _phone_number;
	}
	
	// constructor
	public Meta(String name, String _phone_number){
		this._name = name;
		this._description = _phone_number;
	}
	// getting ID
	public int getID(){
		return this._id;
	}
	
	// setting id
	public void setID(int id){
		this._id = id;
	}
	
	// getting name
	public String getName(){
		return this._name;
	}
	
	// setting name
	public void setName(String name){
		this._name = name;
	}
	
	// getting description
	public String getDescription(){
		return this._description;
	}
	
	// setting description
	public void setDescription(String description){
		this._description = description;
	}
}
