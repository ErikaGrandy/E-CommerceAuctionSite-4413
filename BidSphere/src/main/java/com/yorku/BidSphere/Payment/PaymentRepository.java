package com.yorku.BidSphere.Payment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//AUTO-IMPLEMENTED
//WILL INCLUDE BASIC CRUD
@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {
	//Leave empty, unless adding in custom SQL queries.
}