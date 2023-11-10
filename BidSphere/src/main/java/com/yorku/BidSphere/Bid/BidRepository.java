package com.yorku.BidSphere.Bid;

import com.yorku.BidSphere.Catalog.CatalogItem;
import com.yorku.BidSphere.Payment.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends CrudRepository<Bid, Integer> {

}
