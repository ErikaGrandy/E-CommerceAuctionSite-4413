package com.bidSphere.BidSphereForwardBidding.Bid;

import com.yorku.BidSphere.User.User;

public class BidRequest {

    private User user;
    private int amount;

    public BidRequest(User user, int amount) {
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
