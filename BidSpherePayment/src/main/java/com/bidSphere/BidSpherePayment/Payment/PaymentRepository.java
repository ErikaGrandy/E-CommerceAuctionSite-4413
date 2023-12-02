package com.bidSphere.BidSpherePayment.Payment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {

	Payment findDistinctByUserIDAndCatalogItemID(int userID, int catalogItemID);

}