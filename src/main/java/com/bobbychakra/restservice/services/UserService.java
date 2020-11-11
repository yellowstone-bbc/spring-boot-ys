package com.bobbychakra.restservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bobbychakra.restservice.entities.User;
import com.bobbychakra.restservice.repositories.UserRepository;

@Service // This is service class 
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	// Implement a Method - Get all Users
	public List<User> getAllUsers(){
		return userRepository.findAll(); //Biz logic - would be complex in real time :)
	}
	
	// Create a New User method
	public User createNewUser(User user) {
		return userRepository.save(user);
	}
	
	// to get a user by ID alone 
	public Optional<User> getUserbyId(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}
	
	// Update user by ID
	public User updateUserbyId(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteById(Long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findByUserName(username);
	}
}
