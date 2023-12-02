package com.bidSphere.BidSphereDutchBidding.DutchBid;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DutchBidRepository extends  CrudRepository<DutchBid, Integer> {

}
