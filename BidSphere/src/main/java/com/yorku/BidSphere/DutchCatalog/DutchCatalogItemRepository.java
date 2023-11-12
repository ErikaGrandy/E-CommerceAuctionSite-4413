package com.yorku.BidSphere.DutchCatalog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.yorku.BidSphere.Payment.Payment;
import org.springframework.stereotype.Component;
// Will only contain winning dutchBids
@Repository
public interface DutchCatalogItemRepository extends CrudRepository<DutchCatalogItem, Integer> {

}
