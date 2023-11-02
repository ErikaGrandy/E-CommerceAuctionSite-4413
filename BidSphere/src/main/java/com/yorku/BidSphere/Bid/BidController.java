package com.yorku.BidSphere.Bid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BidController {

	BidService bidService = new BidService();

	//End points:
	//sendBid(): User sends bid to an auction event
	//getAuctionStatus: User requests up to date info on the auction


	//sendBid():
	// Params: UserInfo, bid
	//Process:


	//To Do: Validate user input
	@PostMapping("/Bids/sendBid")
	public ResponseEntity<String> sendBid(@RequestBody BidRequest bidRequest)
	{
		String str = "BidController is live.";
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}
}
