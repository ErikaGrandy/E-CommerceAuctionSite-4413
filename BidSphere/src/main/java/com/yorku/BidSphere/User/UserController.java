package com.yorku.BidSphere.User;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private UserService userService = new UserService();

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/Users")
	public ResponseEntity<ArrayList<User>> getAllUsers() {
		ArrayList<User> users = userService.readAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/Users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		
		if (userService.read(id) != null) {
			return new ResponseEntity<>(userService.read(id), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/Users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			String password = user.getPassword();
			if (!userService.isPasswordValid(password)) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@PutMapping("/Users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
		try {
			String password = user.getPassword();
			if (!userService.isPasswordValid(password)) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			userService.update(id, user);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/Users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		try {
			userService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
