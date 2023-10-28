package com.yorku.BidSphere.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	UserService userService = new UserService();

	@GetMapping("/Users")
	public ResponseEntity<String> response()
	{
		String str = "UserController is live.";
		return new ResponseEntity<String>(str, HttpStatus.OK);

	}
}
