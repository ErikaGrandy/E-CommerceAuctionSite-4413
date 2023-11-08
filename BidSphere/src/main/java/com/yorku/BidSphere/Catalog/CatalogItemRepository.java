package com.yorku.BidSphere.Catalog;

import com.yorku.BidSphere.Payment.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogItemRepository extends CrudRepository<CatalogItem, Integer> {

}

