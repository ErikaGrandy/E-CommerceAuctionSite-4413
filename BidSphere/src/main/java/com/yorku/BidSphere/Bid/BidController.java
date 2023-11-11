package com.yorku.BidSphere.Bid;

import com.yorku.BidSphere.Catalog.CatalogController;
import com.yorku.BidSphere.Catalog.CatalogItem;
import com.yorku.BidSphere.Catalog.CatalogService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullFields;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.catalog.Catalog;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@Validated
@RestController
public class BidController {

	@Autowired
	BidService service;

	@Autowired
	CatalogController catalogController;

	@GetMapping("/Bid/getAll")
	public ResponseEntity<ArrayList<Bid>> getAllItems()
	{
		ArrayList<Bid> list = service.getAllItems();

		return ((list != null) ? new ResponseEntity<ArrayList<Bid>>(list, HttpStatus.OK) :
				new ResponseEntity<>(null, HttpStatus.OK));
	}

	@GetMapping("/Bid/get")
	public ResponseEntity<Bid> getItem(@RequestParam(name="id") int itemID)
	{
		Bid item = service.getItem(itemID);

		return ((item!=null) ? new ResponseEntity<Bid>(item, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
	}

	@PostMapping("/Bid/add")
	public ResponseEntity<Bid> addItem(@RequestBody Bid item)
	{
		try
		{
			Bid addedItem = service.addItem(item);
			return new ResponseEntity<Bid>(addedItem, HttpStatus.OK);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/Bid/getByCatalogue")
	public ResponseEntity<ArrayList<Bid>> getBidsByCatalogue(@RequestParam(name="id") int id)
	{
		if (id == 0)
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		ArrayList<Bid> list = service.getBidsbyCatID(id);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	//Accepts a bid for a forward Auction
	//To do
	// -> Add null checks to RequestBody
	@PostMapping("/forwardAuction/send")
	public ResponseEntity<ForwardBidResponse> sendForwardBid(@RequestBody @Validated Bid bid)
	{
		if (bid == null || bid.getAmount()==0 || bid.getCatalogItemID()==0 || bid.getUserID()==0)
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
//		System.out.println(bid.toString());

		//Get CatalogItem/Auction details
		ResponseEntity<CatalogItem> responseEntity = catalogController.getItem(bid.getCatalogItemID());
		CatalogItem auctionItem = responseEntity.getBody();
		if (auctionItem == null)
		{
			System.out.println("CatalogItem does not exist");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}


		//Check if Auction is open for bids
		ZonedDateTime time = ZonedDateTime.parse(auctionItem.getEndTime());
		if (ZonedDateTime.now().isAfter(time))
		{
			System.out.println(bid.toString() + "Above bid because the auction has closed. \n");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}


		//Store bid in db
		service.addItem(bid);

		//Compare bid to highest
		if (auctionItem.getCurrentPrice() < bid.getAmount())
		{
			auctionItem.setCurrentPrice(bid.getAmount());
			auctionItem.setHighestBidderID(bid.getUserID());
			//If itemID is set in the object, .save will update instead of create
			catalogController.addItem(auctionItem);
		}


		ForwardBidResponse bidResponse = new ForwardBidResponse(auctionItem.getCurrentPrice(), auctionItem.getHighestBidderID());
		return new ResponseEntity<ForwardBidResponse>(bidResponse, HttpStatus.OK);
	}


	//Returns the highest bidder amount and bidder id for an auction
	@GetMapping("/forwardAuction/getStatus")
	public ResponseEntity<ForwardBidResponse> getAuctionStatus(@RequestParam(name="id") int id)
	{
		ResponseEntity<CatalogItem> responseEntity = catalogController.getItem(id);
		CatalogItem auctionItem = responseEntity.getBody();
		if (auctionItem == null)
		{
			System.out.println("CatalogItem does not exist");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		ForwardBidResponse res = new ForwardBidResponse(auctionItem.getCurrentPrice(), auctionItem.getHighestBidderID());
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("/testing")
	public void test()
	{
		ZonedDateTime time = ZonedDateTime.now().plusMinutes(10);
		System.out.println(time.toString());
	}

}
