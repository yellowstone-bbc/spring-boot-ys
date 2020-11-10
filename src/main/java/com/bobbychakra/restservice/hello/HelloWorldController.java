package com.bobbychakra.restservice.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// This will be my controller
@RestController
public class HelloWorldController {
	// Write a simple method
	
	// Method & URI to be defined
	//@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
	@GetMapping( path = "/helloworldget")
	public String returnString() {
		return "Hello User. This is implemented on SpringBoot";
	}
	@GetMapping(path = "/helloworld-user")
	public UserDetails helloWorldBean() {
		return new UserDetails("Bobby", "Chakra", "Tiruppur");
	}
}
