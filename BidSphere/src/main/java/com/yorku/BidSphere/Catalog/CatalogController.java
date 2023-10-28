package com.yorku.BidSphere.Catalog;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogController {
	
	CatalogService catalogService = new CatalogService();

	@GetMapping("/Catalog")
	public ResponseEntity<String> response()
	{
		String str = "CatalogController is live.";
		return new ResponseEntity<String>(str, HttpStatus.OK);

	}
}
