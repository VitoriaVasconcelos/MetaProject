package com.project.meta.model;

import java.io.Serializable;

public class Meta implements Serializable{
	
	private static final long serialVersionUID = 7094015584559223020L;
	
	private int _id;
	private String _name;
	private String _description;
	
	public Meta(){
		
	}
	
	public Meta(int id, String name, String description){
		this._id = id;
		this._name = name;
		this._description = description;
	}
	
	public Meta(String name, String description){
		this._name = name;
		this._description = description;
	}
	
	public int getID(){
		return this._id;
	}
	
	public void setID(int id){
		this._id = id;
	}
	
	public String getName(){
		return this._name;
	}
	
	public void setName(String name){
		this._name = name;
	}
	
	public String getDescription(){
		return this._description;
	}
	
	public void setDescription(String description){
		this._description = description;
	}
}
