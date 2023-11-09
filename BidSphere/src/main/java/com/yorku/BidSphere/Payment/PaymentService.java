package com.yorku.BidSphere.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

	@Autowired private PaymentRepository paymentRepository;

	protected String createPayment(Payment payment)
	{
		int paymentID = paymentRepository.save(payment).getPaymentID();
		return "Payment has been saved with an ID of " + paymentID;
	}
}
