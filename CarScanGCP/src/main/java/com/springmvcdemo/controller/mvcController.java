package com.springmvcdemo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.springmvcdemo.service.UserService;

@RestController
public class mvcController {


	UserService us = new UserService();

	@PutMapping("addEditUser")
	public String addEditUser(@RequestBody String user) throws EntityNotFoundException, JsonMappingException, JsonProcessingException {
		return us.addEditUser(user);
	}

	@GetMapping("getUsers")
	public String getUser() throws EntityNotFoundException, JsonProcessingException {
		return us.allUsers();
	}

	@DeleteMapping("deleteUser/{key}")
	public String deleteUser(@PathVariable long key) throws EntityNotFoundException {
		return us.deleteUser(key);
	}

}
