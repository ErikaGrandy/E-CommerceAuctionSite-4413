package com.yorku.BidSphere.DutchBid;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yorku.BidSphere.Bid.Bid;
import com.yorku.BidSphere.Bid.BidService;
import com.yorku.BidSphere.Bid.ForwardBidResponse;
import com.yorku.BidSphere.Catalog.CatalogController;
import com.yorku.BidSphere.Catalog.CatalogItem;
import com.yorku.BidSphere.DutchCatalog.DutchCatalogController;
import com.yorku.BidSphere.DutchCatalog.DutchCatalogItem;

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
		if (bid == null || bid.getAmount() == 0 || bid.getCatalogItemID() == 0 || bid.getUserID() == 0) {
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
		if (auctionItem.isAvailible()==false) {
			System.out.println("CatalogItem is unavailible. \n");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		// Check if Auction is open for bids
		ZonedDateTime time = ZonedDateTime.parse(auctionItem.getEndTime());
		if (ZonedDateTime.now().isAfter(time)) {
			System.out.println(bid.toString() + "Above bid because the auction has closed. \n");
			
			// expire the item and set it to unavailable
			auctionItem.setAvailible(false);
			// updating the auctionItem with unavailable status
			dutchCatalogController.addItem(auctionItem);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
		
		// Check if bid amount is equal to asking price
		if(auctionItem.getPrice() > bid.getAmount()) {
			System.out.println(bid.toString() + "Bid is too low. \n");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// this is the winning bid. Store it in the db
		// DutchBid db only contains winning bids
		service.addItem(bid);
	
		// setting buyerID in the auctionItem 
		auctionItem.setBuyerID(bid.getUserID());
		System.out.println(":" + bid.getUserID());
		System.out.println(auctionItem.getBuyerID());

		auctionItem.setAvailible(false);
		// updating the auctionItem with the buyerID
		dutchCatalogController.addItem(auctionItem);
		
		return new ResponseEntity<>(bid, HttpStatus.OK);

	}
	

}
