package com.yorku.BidSphere;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Plays role of Facade
//Responsible for logging

@RestController
public class FrontController {

	@GetMapping("/")
	public ResponseEntity<String> greeting() {
		String str = "Website is online.";
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}
	
}
