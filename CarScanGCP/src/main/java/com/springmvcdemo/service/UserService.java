package com.springmvcdemo.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.springmvcdemo.dao.UsersRepo;
import com.springmvcdemo.model.Users;

public class UserService {
	ObjectMapper mapperObj = new ObjectMapper();
	Users user = new Users();
	UsersRepo uRepo = new UsersRepo();
	
	
	public String addEditUser(String user) throws EntityNotFoundException {
		String jsonStr = user;
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			resultMap = mapperObj.readValue(jsonStr, new TypeReference<HashMap<String, String>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (resultMap.containsKey("Key")) {

			long l = Long.parseLong(resultMap.get("Key"));
			Key Key = KeyFactory.createKey("Users", l);
			Entity e = uRepo.userGetByKey(Key);
			e.setProperty("ID", resultMap.get("ID"));
			e.setProperty("firstName", resultMap.get("firstName"));
			e.setProperty("lastName", resultMap.get("lastName"));
			e.setProperty("dob", resultMap.get("dob"));
			e.setProperty("city", resultMap.get("city"));
			e.setProperty("mobileNumber", resultMap.get("mobileNumber"));
			uRepo.addEditUser(e);

		} else {
			Entity entity = new Entity(this.user.getClass().getSimpleName());
			Field fld[] = Users.class.getDeclaredFields();
			for (int i = 0; i < fld.length; i++) {
				entity.setProperty(fld[i].getName(), resultMap.get(fld[i].getName()));
			}
			uRepo.addEditUser(entity);
		}

		return jsonStr;
	}

	public String allUsers() throws EntityNotFoundException, JsonProcessingException {
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr = mapperObj.writeValueAsString(uRepo.allUsers());
		return jsonStr;
	}
	
	public String deleteUser(long id) {
		return uRepo.deleteUser(id);
	}

}
