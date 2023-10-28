package com.yorku.BidSphere.Catalog;

import java.util.List;

//MODEL
public class CatalogItem {

	int itemID;
	String itemName;
	String description;
	List<String> keywords;
	String auctionType;
	int shippingTime;
	double initialPrice;
	
	//TO-DO - end date for forward bidding

	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	public String getAuctionType() {
		return auctionType;
	}
	public void setAuctionType(String auctionType) {
		this.auctionType = auctionType;
	}
	public int getShippingTime() {
		return shippingTime;
	}
	public void setShippingTime(int shippingTime) {
		this.shippingTime = shippingTime;
	}
	public double getInitialPrice() {
		return initialPrice;
	}
	public void setInitialPrice(double initialPrice) {
		this.initialPrice = initialPrice;
	}

	
}
