package com.yorku.BidSphere.Payment;

import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	protected Payment createPayment(Payment payment) {

		try {
			Payment createdPayment = paymentRepository.save(payment);
			return createdPayment;
		} catch (Exception e) {
			return null;
		}
	}

	protected Payment getPaymentByID(int paymentID) {
		Optional<Payment> payment = paymentRepository.findById(paymentID);
		if (payment.isPresent())
			return payment.get();
		return null;
	}

	protected Payment getPaymentByUserAndItem(int UserID, int CatalogItemID) {
		Payment payment = paymentRepository.findDistinctByUserIDAndCatalogItemID(UserID, CatalogItemID);
		return payment;
	}

	protected Payment updatePayment(int paymentID, Payment newPaymentDetails) {
		if (newPaymentDetails.getUserID() == paymentID) // Same ID. Update details
		{
			paymentRepository.save(newPaymentDetails);
		} else { // Diff ID. Replace given paymentID with new details
			newPaymentDetails.setPaymentID(paymentID);
			paymentRepository.save(newPaymentDetails);
		}
		return newPaymentDetails;
	}

	protected Payment deletePaymentByID(int paymentID) {
		Optional<Payment> payment = paymentRepository.findById(paymentID);
		if (payment.isEmpty())
			return null;
		paymentRepository.deleteById(paymentID);
		return payment.get();
	}

	protected boolean checkPaymentObjectValid(Payment payment) {
		if (payment == null | payment.getUserID() == 0 | payment.getCatalogItemID() == 0)
			return false; // Note paymentID will be null until added to DB
		if (payment.getCardholderName() == null | payment.getAmount() == 0 | payment.getCvv() == 0
			| payment.getCardNum() == 0 | payment.getExpiryDate() == null)
			return false;

		return true;
	}

	protected boolean checkCardNumValid(Long cardNum) {
		int count = 0;
		while (cardNum > 0) {
			count += 1;
			cardNum = cardNum / 10;
		}

		if (count != 16)
			return false;
		return LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(cardNum.toString());
	}

	protected ArrayList<Payment> getAllPayments() {
		ArrayList<Payment> list = new ArrayList<>();
		Iterable<Payment> itList = paymentRepository.findAll();
		if (itList != null) {
			Iterator<Payment> iterator = itList.iterator();
			while (iterator.hasNext()) {
				list.add(iterator.next());
			}

			return list;
		} else {
			return null;
		}
	}
}