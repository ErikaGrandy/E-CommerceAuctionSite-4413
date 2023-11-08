package com.yorku.BidSphere.Bid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BidController {

	BidService bidService = new BidService();

	@GetMapping("/Bids")
	public ResponseEntity<String> response()
	{
		String str = "BidController is live.";
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}
}
