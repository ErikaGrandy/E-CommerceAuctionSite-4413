package com.bidSphere.BidSphereDutchBidding.DutchCatalog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class DutchCatalogItem {
	
	private @Id
	@GeneratedValue
	int itemID;

	private String name;

	private int price;

	private int buyerID;
	
	private int decrementStep;
	
	private int lowestPrice;

	private String auctionType;

	private String endTime;

	private int shippingTime;

	private String description;

	private int expeditedShippingCost;
	
	private boolean available;
	
	private int sellerID;
	
	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getBuyerID() {
		return buyerID;
	}

	public void setBuyerID(int buyerID) {
		this.buyerID = buyerID;
	}

	public int getDecrementStep() {
		return decrementStep;
	}

	public void setDecrementStep(int decrementStep) {
		this.decrementStep = decrementStep;
	}

	public int getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(int lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getShippingTime() {
		return shippingTime;
	}

	public void setShippingTime(int shippingTime) {
		this.shippingTime = shippingTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getExpeditedShippingCost() {
		return expeditedShippingCost;
	}

	public void setExpeditedShippingCost(int expeditedShippingCost) {
		this.expeditedShippingCost = expeditedShippingCost;
	}
	
	public void lowerPrice() {
		if(this.price - this.decrementStep >= this.lowestPrice) {
			this.price = price - this.decrementStep;
		}
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getSellerID() {
		return sellerID;
	}

	public void setSellerID(int sellerID) {
		this.sellerID = sellerID;
	}

	public String getAuctionType() {
		return auctionType;
	}

	public void setAuctionType(String auctionType) {
		this.auctionType = auctionType;
	}


}
