package com.bidSphere.BidSphereForwardBidding.Catalog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogItemRepository extends CrudRepository<CatalogItem, Integer> {

}

