package com.bidSphere.BidSphereDutchBidding.DutchBid;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class DutchBid {
	
    //SQL row id
    private @Id @GeneratedValue int id;
    private int userID;
    private int catalogItemID;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	};
	public int getCatalogItemID() {
		return catalogItemID;
	}
	public void setCatalogItemID(int catalogItemID) {
		this.catalogItemID = catalogItemID;
	}
    
}
