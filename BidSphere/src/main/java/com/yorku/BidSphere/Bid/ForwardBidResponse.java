package com.yorku.BidSphere.Bid;

public class ForwardBidResponse {
    private int highestBid;
    private int highestBidderID;

    public ForwardBidResponse(int highestBid, int highestBidderID) {
        this.highestBid = highestBid;
        this.highestBidderID = highestBidderID;
    }

    public int getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(int highestBid) {
        this.highestBid = highestBid;
    }

    public int getHighestBidderID() {
        return highestBidderID;
    }

    public void setHighestBidderID(int highestBidderID) {
        this.highestBidderID = highestBidderID;
    }
}
