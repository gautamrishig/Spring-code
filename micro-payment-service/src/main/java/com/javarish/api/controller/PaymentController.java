package com.javarish.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javarish.api.entity.Order;
import com.javarish.api.entity.Payment;
import com.javarish.api.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	Logger logger = org.slf4j.LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private PaymentService service;

	@PostMapping("/doPayment")
	public Payment doPayment(@RequestBody Payment payment) throws JsonProcessingException {
		logger.info("Inside doPayment:");
		return service.doPayment(payment);
	}

	@GetMapping("/{orderId}")
	public Payment findPaymentHistoryByOrderId(@PathVariable int orderId) throws JsonProcessingException {
		logger.info("Inside findPaymentHistoryByOrderId:");
		return service.findPaymentHistoryByOrderId(orderId);
	}

	@PostMapping("/getAllPayments")
	public ResponseEntity<List<Payment>> findAllPaymentHistoryByOrderId(@RequestBody List<Order> orList) {
		logger.info("Inside findAllPaymentHistoryByOrderId:");

		// Call the service to get payments
		List<Payment> payments = service.findAllPaymentByAllOrderID(orList);

		// Return the payments wrapped in a ResponseEntity
		return new ResponseEntity<>(payments, HttpStatus.OK);
	}

}
