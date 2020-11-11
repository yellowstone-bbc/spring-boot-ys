package com.bobbychakra.restservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bobbychakra.restservice.entities.User;

// Repository
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUserName(String userName);

}
