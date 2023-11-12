package com.yorku.BidSphere.DutchCatalog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.yorku.BidSphere.User.User;

import javax.xml.catalog.Catalog;
import java.util.ArrayList;

@RestController
public class DutchCatalogController {


	@Autowired
	DutchCatalogService service;

	@GetMapping("/DutchCatalog/getAll")
	public ResponseEntity<ArrayList<DutchCatalogItem>> getAllItems()
	{
		ArrayList<DutchCatalogItem> list = service.getAllItems();

		return ((list != null) ? new ResponseEntity<ArrayList<DutchCatalogItem>>(list, HttpStatus.OK) :
				new ResponseEntity<>(null, HttpStatus.OK));
	}
	@GetMapping("/DutchCatalog/get")
	public ResponseEntity<DutchCatalogItem> getItem(@RequestParam(name="ItemID") int itemID)
	{
		DutchCatalogItem item = service.getItem(itemID);

		return ((item!=null) ? new ResponseEntity<DutchCatalogItem>(item, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
	}
	
	
	@PostMapping("/DutchCatalog/add")
	public ResponseEntity<DutchCatalogItem> addItem(@RequestBody DutchCatalogItem item)
	{
		try
		{
			System.out.println(item.getName());
			DutchCatalogItem addedItem = service.addItem(item);
			return new ResponseEntity<DutchCatalogItem>(addedItem, HttpStatus.OK);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
//	@PutMapping("/DutchCatalog/updatePrice/{itemID}/{userID}")
//	public ResponseEntity<DutchCatalogItem> updatePrice(@PathVariable int itemID,@PathVariable int userID)
//	{
//		try
//		{
//			boolean verified = service.verifySeller(itemID, userID);
//			if(verified) {
//				DutchCatalogItem item = service.decrementPrice(itemID);
//				return new ResponseEntity<DutchCatalogItem>(item, HttpStatus.OK);
//			}else {
//				System.out.print("Only Seller can decrement price \n");
//				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//
//			}
//			
//		}
//		catch (Exception e)
//		{
//			System.out.println(e.getMessage());
//			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//		}
//	}
	@PutMapping("/DutchCatalog/updatePrice")
	public ResponseEntity<DutchCatalogItem> updatePrice1(@RequestParam int itemID, @RequestParam int userID)
	{
		try
		{
			boolean verified = service.verifySeller(itemID, userID);
			if(verified) {
				DutchCatalogItem item = service.decrementPrice(itemID);
				return new ResponseEntity<DutchCatalogItem>(item, HttpStatus.OK);
			}else {
				System.out.print("Only Seller can decrement price \n");
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

			}
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	

}
