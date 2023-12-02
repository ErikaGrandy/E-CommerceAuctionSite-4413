package com.bidSphere.BidSphereForwardBidding.Bid;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BidRepository extends CrudRepository<Bid, Integer> {

    ArrayList<Bid> findBidByCatalogItemID(int catalogItemID);
}
