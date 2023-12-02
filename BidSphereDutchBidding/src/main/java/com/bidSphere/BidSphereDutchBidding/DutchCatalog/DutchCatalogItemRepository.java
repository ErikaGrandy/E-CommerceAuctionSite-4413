package com.bidSphere.BidSphereDutchBidding.DutchCatalog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Will only contain winning dutchBids
@Repository
public interface DutchCatalogItemRepository extends CrudRepository<DutchCatalogItem, Integer> {

}
