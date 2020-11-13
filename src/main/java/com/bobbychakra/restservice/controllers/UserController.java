package com.bobbychakra.restservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.bobbychakra.restservice.entities.User;
import com.bobbychakra.restservice.exceptions.UserExistsException;
import com.bobbychakra.restservice.exceptions.UserNotFoundException;
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
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder builder) {	
		try {
			userService.createNewUser(user);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(header, HttpStatus.CREATED);
		} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	// getUserbyId method - 
	@GetMapping("/users/{id}")
	public Optional<User> getUserbyId(@PathVariable("id") Long id){
		try {
			return userService.getUserbyId(id);
		} catch(UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
	
	@PutMapping("/users/{id}")
	public User updateUserbyId(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			return userService.updateUserbyId(id, user);
		} catch(UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
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
