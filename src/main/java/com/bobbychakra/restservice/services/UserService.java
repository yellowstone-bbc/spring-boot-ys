package com.bobbychakra.restservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bobbychakra.restservice.entities.User;
import com.bobbychakra.restservice.exceptions.UserExistsException;
import com.bobbychakra.restservice.exceptions.UserNotFoundException;
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
	public User createNewUser(User user) throws UserExistsException {
		User existingUser = userRepository.findByUserName(user.getUserName());
		
		if(existingUser !=null)
			throw new UserExistsException("User already present");
		
		return userRepository.save(user);
	}
	
	// to get a user by ID alone 
	public Optional<User> getUserbyId(Long id) throws UserNotFoundException{
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("User not found in User Directory");
		
		return user;
	}
	
	// Update user by ID
	public User updateUserbyId(Long id, User user) throws UserNotFoundException {
		Optional<User> optUser = userRepository.findById(id);
		if(!optUser.isPresent())
			throw new UserNotFoundException("User not found in User Directory. Kindly provide correct ID");
		
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteById(Long id) {
		
		Optional<User> optUser = userRepository.findById(id);
		if(!optUser.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Not Found. Please check");

		userRepository.deleteById(id);
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findByUserName(username);
	}
}
