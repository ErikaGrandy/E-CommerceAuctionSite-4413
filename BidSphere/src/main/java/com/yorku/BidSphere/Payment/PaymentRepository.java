package com.yorku.BidSphere.Payment;

import org.springframework.data.repository.CrudRepository;

//AUTO-IMPLEMENTED
//WILL INCLUDE BASIC CRUD
public interface PaymentRepository extends CrudRepository<Payment, Integer> {
	//Leave empty, unless adding in custom SQL queries.
}