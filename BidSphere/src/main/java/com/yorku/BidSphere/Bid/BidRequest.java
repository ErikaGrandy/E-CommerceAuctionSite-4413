package com.yorku.BidSphere.Bid;

import com.yorku.BidSphere.User.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class BidRequest {

    //SQL row id
    private @Id @GeneratedValue int id;
    private int userID;
    private int amount;
    private int catalogItemID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCatalogItemID() {
        return catalogItemID;
    }

    public void setCatalogItemID(int catalogItemID) {
        this.catalogItemID = catalogItemID;
    }
}
