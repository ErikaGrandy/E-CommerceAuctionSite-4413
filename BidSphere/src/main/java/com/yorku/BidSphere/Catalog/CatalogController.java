package com.yorku.BidSphere.Catalog;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogController {
	
	CatalogService catalogService = new CatalogService();

	@GetMapping("/Catalog/getAll")
	public ResponseEntity<String> getAllItems()
	{
		String str = "CatalogController is live.";
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}

	@GetMapping("/Catalog/get")
	public ResponseEntity<String> getItem()
	{
		String str = "CatalogController is live.";
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}

	@PostMapping("/Catalog/add")
	public ResponseEntity<String> addItem()
	{
		String str = "CatalogController is live.";
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}

}
