package com.yorku.BidSphere.Bid;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Bid {

    //SQL row id
    private @Id @GeneratedValue int id;
    private int userID;
    private int amount;
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

    public String toString()
    {
        return "Bid \n" +
                "userID: " + this.userID + "\n" +
                "amount: " + this.amount + "\n" +
                "catalogitemID: " + this.catalogItemID + "\n";
    }

}
