package com.yorku.BidSphere.User;

import java.util.ArrayList;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/Users/verifyUser")
	public ResponseEntity<User> verifyUser(@RequestParam(name="username") String username, @RequestParam(name="password") String password)
	{
		User user = userService.verify(username, password);

		if (user == null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("Users/forgotPassword")
	public ResponseEntity<User> updatePassword(@RequestParam(name="username") String username,
											   @RequestParam(name="password") String newPassword,
											   @RequestParam(name="streetNumber") int streetNumber)
	{
		User user = userService.updatePassword(username, streetNumber, newPassword);

		if (user == null)
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}


	@GetMapping("/Users/getByID")
	public ResponseEntity<User> getUserById(@RequestParam(name="id") int id) {
		
		if (userService.read(id) != null) {
			return new ResponseEntity<>(userService.read(id), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/Users/createUser")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			String password = user.getPassword();
			if (userService.checkExisting(user.getUserName()))
			{
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
//			if (!userService.isPasswordValid(password)) {
//				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//			}
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
