package com.bidSphere.BidSpherePayment.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	// Retrieve payment info in via ID
	@GetMapping("/Payment/getByID")
	public ResponseEntity<Payment> getPaymentByID(@RequestParam int paymentID) {

		if (paymentID == 0)
			return new ResponseEntity<Payment>((Payment) null, HttpStatus.BAD_REQUEST);

		Payment payment = paymentService.getPaymentByID(paymentID);

		if (payment == null)
			return new ResponseEntity<Payment>(payment, HttpStatus.NOT_FOUND);

		return new ResponseEntity<Payment>(payment, HttpStatus.OK);

	}

	// Retrieve payment info in via UserID and ItemID/Type
	@GetMapping("/Payment/getbyUserIDItemID")
	public ResponseEntity<Payment> getPaymentByID(@RequestParam int userID, @RequestParam int itemID, @RequestParam String itemType) {

		if (userID == 0 | itemID == 0 | itemType == null)
			return new ResponseEntity<Payment>((Payment) null, HttpStatus.BAD_REQUEST);

		Payment payment = paymentService.getPaymentByUserAndItem(userID, itemID, itemType);

		if (payment == null)
			return new ResponseEntity<Payment>(payment, HttpStatus.NOT_FOUND);

		return new ResponseEntity<Payment>(payment, HttpStatus.OK);

	}

	// Add a payment to database
	@PostMapping("/Payment/Add")
	public ResponseEntity<Payment> addPayment(@RequestBody Payment payment) {

		// Verify input
		if (!paymentService.checkPaymentObjectValid(payment))
			return new ResponseEntity<Payment>(payment, HttpStatus.BAD_REQUEST);

		// Add payment to DB
		Payment createdPayment = paymentService.createPayment(payment);

		// Check if adding to DB failed
		if (createdPayment == null)
			return new ResponseEntity<Payment>(createdPayment, HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<Payment>(createdPayment, HttpStatus.OK);
	}

}
