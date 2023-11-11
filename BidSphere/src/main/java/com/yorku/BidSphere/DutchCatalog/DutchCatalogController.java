package com.yorku.BidSphere.DutchCatalog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
	
	@PutMapping("/DutchCatalog/updatePrice/{itemID}")
	public ResponseEntity<DutchCatalogItem> updatePrice(@PathVariable int itemID)
	{
		try
		{
			DutchCatalogItem item = service.decrementPrice(itemID);
			
			return new ResponseEntity<DutchCatalogItem>(item, HttpStatus.OK);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
