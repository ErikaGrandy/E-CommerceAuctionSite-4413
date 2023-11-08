package com.yorku.BidSphere.Bid;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Bid {

	@Id int bidID;
	int itemID; // Maps to itemID in CatalogItem
	int highestBidderID; // Maps to UserID in User
	double highestPrice;
	LocalDateTime endTime;
	public int getBidID() {
		return bidID;
	}
	public void setBidID(int bidID) {
		this.bidID = bidID;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public int getHighestBidderID() {
		return highestBidderID;
	}
	public void setHighestBidderID(int highestBidderID) {
		this.highestBidderID = highestBidderID;
	}
	public double getHighestPrice() {
		return highestPrice;
	}
	public void setHighestPrice(double highestPrice) {
		this.highestPrice = highestPrice;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	
}
