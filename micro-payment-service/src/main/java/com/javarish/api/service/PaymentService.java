package com.javarish.api.service;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarish.api.entity.Order;
import com.javarish.api.entity.Payment;
import com.javarish.api.repositroy.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository repository;

	Logger logger = LoggerFactory.getLogger(PaymentService.class);

	public Payment doPayment(Payment payment) throws JsonProcessingException {
		logger.info("Inside doPayment:");
		payment.setPaymentStatus(paymentProcessing());
		payment.setTransactionId(UUID.randomUUID().toString());
		logger.info("Payment-Service Request : {}", new ObjectMapper().writeValueAsString(payment));

		return repository.save(payment);
	}

	public String paymentProcessing() {
		logger.info("Inside paymentProcessing:");
		// api should be 3rd party payment gateway (paypal,paytm...)
		return new Random().nextBoolean() ? "success" : "false";
	}

	public Payment findPaymentHistoryByOrderId(int orderId) throws JsonProcessingException {
		logger.info("Inside findPaymentHistoryByOrderId:");
		Payment payment = repository.findByOrderId(orderId);
		logger.info("paymentService findPaymentHistoryByOrderId : {}", new ObjectMapper().writeValueAsString(payment));
		return payment;
	}

	public List<Payment> findAllPaymentByAllOrderID(List<Order> orList) {
		logger.info("Inside findAllPayment:");
		// TODO Auto-generated method stub
		List<Long> orderId = orList.stream().map(t -> t.getPaymentId()).collect(Collectors.toList());
		logger.info("Order id " + orderId);
		return repository.findByPaymentIdIn(orderId);
	}
}
