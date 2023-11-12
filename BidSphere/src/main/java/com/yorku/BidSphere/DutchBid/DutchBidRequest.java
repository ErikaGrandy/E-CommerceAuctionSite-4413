package com.yorku.BidSphere.DutchBid;

import com.yorku.BidSphere.User.User;

public class DutchBidRequest {
	
	
    private User user;
    private int amount;

    public DutchBidRequest(User user, int amount) {
        this.user = user;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public int getAmount() {
        return amount;
    }
}
