package com.yorku.BidSphere.Catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.catalog.Catalog;
import java.util.ArrayList;

@RestController
public class CatalogController {
	
	@Autowired
	CatalogService service;

	@GetMapping("/Catalog/getAll")
	public ResponseEntity<ArrayList<CatalogItem>> getAllItems()
	{
		ArrayList<CatalogItem> list = service.getAllItems();

		return ((list != null) ? new ResponseEntity<ArrayList<CatalogItem>>(list, HttpStatus.OK) :
				new ResponseEntity<>(null, HttpStatus.OK));
	}

	@GetMapping("/Catalog/get")
	public ResponseEntity<CatalogItem> getItem(@RequestParam(name="id") int itemID)
	{
		CatalogItem item = service.getItem(itemID);

		return ((item!=null) ? new ResponseEntity<CatalogItem>(item, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
	}

	@PostMapping("/Catalog/add")
	public ResponseEntity<CatalogItem> addItem(@RequestBody CatalogItem item)
	{
		try
		{
			if(item.getCurrentPrice() == 0 || item.getExpeditedShippingCost() == 0 || item.getEndTime().isEmpty() ||
					item.getDescription().isEmpty() || item.getExpeditedShippingCost() == 0)
			{
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			CatalogItem addedItem = service.addItem(item);
			return new ResponseEntity<CatalogItem>(addedItem, HttpStatus.OK);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
