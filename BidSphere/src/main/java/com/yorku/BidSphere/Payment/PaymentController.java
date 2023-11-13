package com.yorku.BidSphere.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	@GetMapping("/Payment/getAllPayments")
	public ResponseEntity<ArrayList<Payment>> getAllPayments() {
		ArrayList<Payment> payments = paymentService.getAllPayments();

		return (payments != null) ? new ResponseEntity<>(payments, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.OK);
	}

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

	// Retrieve payment info in via UserID and ItemID
	@GetMapping("/Payment/getbyUserIDItemID")
	public ResponseEntity<Payment> getPaymentByID(@RequestParam int userID, @RequestParam int itemID) {

		if (userID == 0 | itemID == 0)
			return new ResponseEntity<Payment>((Payment) null, HttpStatus.BAD_REQUEST);

		Payment payment = paymentService.getPaymentByUserAndItem(userID, itemID);

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

	// Update payment within database.
	// Ensure uses supplied payment ID
	@PutMapping("/Payment/Update")
	public ResponseEntity<Payment> addPayment(@RequestParam int paymentID, @RequestBody Payment payment) {

		if (paymentID == 0 | payment == null | !paymentService.checkPaymentObjectValid(payment))
			return new ResponseEntity<Payment>(payment, HttpStatus.BAD_REQUEST);

		Payment updatedPayment = paymentService.updatePayment(paymentID, payment);
		return new ResponseEntity<Payment>(updatedPayment, HttpStatus.OK);
	}

	// Delete payment by ID
	@DeleteMapping("/Payment/Delete")
	public ResponseEntity<Payment> deletePayment(@RequestParam int paymentID) {

		if (paymentID == 0)
			return new ResponseEntity<Payment>((Payment) null, HttpStatus.BAD_REQUEST);

		Payment deletedPayment = paymentService.deletePaymentByID(paymentID);
		return new ResponseEntity<Payment>(deletedPayment, HttpStatus.OK);

	}

}
