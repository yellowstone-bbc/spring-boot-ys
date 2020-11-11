package com.bobbychakra.restservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bobbychakra.restservice.entities.User;
import com.bobbychakra.restservice.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	// Get all user method being called here from REST 
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	// Create New User - Post method - with a Request body coming
	@PostMapping("/createuser")
	public User createUser(@RequestBody User user) {
		return userService.createNewUser(user);
	}
	
	// getUserbyId method - 
	@GetMapping("/users/{id}")
	public Optional<User> getUserbyId(@PathVariable("id") Long id){
		return userService.getUserbyId(id);
	}
	
	@PutMapping("/users/{id}")
	public User updateUserbyId(@PathVariable("id") Long id, @RequestBody User user) {
		return userService.updateUserbyId(id, user);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteById(id);
	}
	
	
	// getUserbyUserName method - 
	@GetMapping("/users/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username){
		return userService.getUserByUsername(username);
	}
	
	
	
	
	
}
