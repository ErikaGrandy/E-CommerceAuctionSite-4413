package com.bidSphere.BidSphereDutchBidding.DutchBid;



import com.bidSphere.BidSphereDutchBidding.DutchCatalog.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;

@Validated
@RestController
public class DutchBidController {
	@Autowired
	DutchBidService service;

	@Autowired
	DutchCatalogController dutchCatalogController;

	@GetMapping("/DutchBid/getAll")
	public ResponseEntity<ArrayList<DutchBid>> getAllItems() {
		ArrayList<DutchBid> list = service.getAllItems();

		return ((list != null) ? new ResponseEntity<ArrayList<DutchBid>>(list, HttpStatus.OK)
				: new ResponseEntity<>(null, HttpStatus.OK));
	}

	@GetMapping("/DutchBid/get")
	public ResponseEntity<DutchBid> getItem(@RequestParam(name = "id") int itemID) {
		DutchBid item = service.getItem(itemID);

		return ((item != null) ? new ResponseEntity<DutchBid>(item, HttpStatus.OK)
				: new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
	}
	
    // accepts a bid for dutchAuction. Only 1 bid will be accepted
	// checks to see if the status of the item is available
	@PostMapping("/DutchBid/buy")
	public ResponseEntity<DutchBid> addItem(@RequestBody DutchBid bid) {
		if (bid == null || bid.getCatalogItemID() == 0 || bid.getUserID() == 0) {
			System.out.println("Bid is either null or has null components");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// Get CatalogItem/Auction details
		ResponseEntity<DutchCatalogItem> responseEntity = dutchCatalogController.getItem(bid.getCatalogItemID());
		DutchCatalogItem auctionItem = responseEntity.getBody();
		if (auctionItem == null) {
			System.out.println("CatalogItem does not exist or is sold. \n");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// Check if Item has been sold
		if (auctionItem.isAvailable()==false) {
			System.out.println("CatalogItem is unavailable. \n");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		// Check if Auction is open for bids
		ZonedDateTime time = ZonedDateTime.parse(auctionItem.getEndTime());
		if (ZonedDateTime.now().isAfter(time)) {
			System.out.println(bid.toString() + "Above bid because the auction has closed. \n");
			
			// expire the item and set it to unavailable
			auctionItem.setAvailable(false);
			// updating the auctionItem with unavailable status
			dutchCatalogController.addItem(auctionItem);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
		
		// this is the winning bid. Store it in the db
		// DutchBid db only contains winning bids
		service.addItem(bid);
	
		// setting buyerID in the auctionItem 
		auctionItem.setBuyerID(bid.getUserID());
		System.out.println(":" + bid.getUserID());
		System.out.println(auctionItem.getBuyerID());

		auctionItem.setAvailable(false);
		// updating the auctionItem with the buyerID
		dutchCatalogController.addItem(auctionItem);
		
		return new ResponseEntity<>(bid, HttpStatus.OK);

	}
	

}
