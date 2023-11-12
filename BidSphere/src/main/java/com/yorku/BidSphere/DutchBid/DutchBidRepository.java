package com.yorku.BidSphere.DutchBid;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yorku.BidSphere.Bid.Bid;

@Repository
public interface DutchBidRepository extends  CrudRepository<DutchBid, Integer> {

}
