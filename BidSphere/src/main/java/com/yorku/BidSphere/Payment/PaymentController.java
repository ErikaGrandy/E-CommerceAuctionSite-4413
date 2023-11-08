package com.yorku.BidSphere.Payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

	PaymentService paymentService = new PaymentService();
	
	@GetMapping("/Payment")
	public ResponseEntity<String> response()
	{
		String str = "PaymentController is live.";
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}
	
	@PostMapping("/Payment/Add")
	public ResponseEntity<String> addPayment(@RequestBody Payment payment)
	{
		String message = paymentService.createPayment(payment);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
