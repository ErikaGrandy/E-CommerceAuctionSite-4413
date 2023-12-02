package com.bidSphere.BidSphereForwardBidding.Catalog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class CatalogItem {

	private @Id
	@GeneratedValue
	int itemID;

	private String name;

	private int currentPrice;

	private String auctionType;

	private int highestBidderID;

	private String endTime;

	private int shippingTime;

	private String description;

	private int expeditedShippingCost;


	public String toString() {
		return "Catalog Item \n" + "itemID: " + this.itemID + "\n" + "name: " + this.name + "\n";
	}

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

	public int getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(int currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getAuctionType() {
		return auctionType;
	}

	public void setAuctionType(String auctionType) {
		this.auctionType = auctionType;
	}

	public int getHighestBidderID() {
		return highestBidderID;
	}

	public void setHighestBidderID(int highestBidderID) {
		this.highestBidderID = highestBidderID;
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
}
