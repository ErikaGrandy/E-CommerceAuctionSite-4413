package com.yorku.BidSphere.Bid;

import com.yorku.BidSphere.User.User;

public class BidRequest {

    //SQL row id
    private int id;

    //UserID
    private int userID;


    private int amount;

    //ID of the catalog item
    private int catalogItemID;

    public BidRequest(int id, int userID, int amount, int catalogItemID) {
        this.id = id;
        this.userID = userID;
        this.amount = amount;
        this.catalogItemID = catalogItemID;
    }


}
