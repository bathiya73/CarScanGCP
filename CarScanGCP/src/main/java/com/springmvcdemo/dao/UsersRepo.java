package com.springmvcdemo.dao;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

public class UsersRepo {

	public static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	ObjectMapper mapperObj = new ObjectMapper();
	Query query = new Query("Users");

	Map<String, String> resultMap = new HashMap<String, String>();

	public Key addEditUser(Entity e){
		return datastore.put(e);
	}
	
	public Entity userGetByKey(Key key) throws EntityNotFoundException{
		return datastore.get(key);
	}
	
	public Iterable<Entity> allUsers(){
		return datastore.prepare(query).asIterable(FetchOptions.Builder.withLimit(100000));
	}
	
	public String deleteUser(long id){
		Key Key = KeyFactory.createKey("Users", id);
		datastore.delete(Key);
		return "Deleted";
	}
}