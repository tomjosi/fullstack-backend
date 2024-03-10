package com.springboot.fullstackBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fullstackBackend.exceptions.UserNotFoundException;
import com.springboot.fullstackBackend.model.User;
import com.springboot.fullstackBackend.repository.UserRepository;



@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@PostMapping("/user")
	User newUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
		
	}
	
	@GetMapping("/user")
	List<User> allUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/user/{id}")
	User getUser(@PathVariable("id") Long id) {
		return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
	}

	@PutMapping("/user/{id}")
	User updateuser(@RequestBody User newUser,@PathVariable Long id) {
		return userRepository.findById(id)
				.map(user -> {
					user.setName(newUser.getName());
					user.setUserName(newUser.getUserName());
					user.setEmail(newUser.getEmail());
					return userRepository.save(user);
				}).orElseThrow(()-> new UserNotFoundException(id));
		
	}
	
	@DeleteMapping("/user/{id}")
	String deleteUser(@PathVariable Long id) {
		if(!userRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
			userRepository.deleteById(id);
			return "User with id "+id+" has been deleted successfully";
			
		
	}
}
