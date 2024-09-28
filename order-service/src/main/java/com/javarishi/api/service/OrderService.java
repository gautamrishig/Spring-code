package com.javarishi.api.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarishi.api.common.Payment;
import com.javarishi.api.common.TransactionRequest;
import com.javarishi.api.common.TransactionResponse;
import com.javarishi.api.entity.Order;
import com.javarishi.api.repository.OrderRepository;

@Service
@RefreshScope
public class OrderService implements  OrderServiceInterface{
	Logger logger = org.slf4j.LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private OrderRepository repository;
	@Autowired
	@Lazy
	private RestTemplate template;
	
    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;
    
    @Value("${microservice.order-service.endpoints.endpoint.uri}")
    private String ORDER_ENDPOINT_URL;


	public TransactionResponse saveOrder(TransactionRequest request) throws JsonProcessingException {
		logger.info(ENDPOINT_URL+" saveOrder "+ORDER_ENDPOINT_URL);
		String response = "";
		Order order = request.getOrder();
		Payment payment = request.getPayment();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());
		// rest call
		logger.info("Order-Service Request : " + new ObjectMapper().writeValueAsString(request));
		Payment paymentResponse = template.postForObject("http://MICRO-PAYMENT-SERVICE/payment/doPayment", payment,Payment.class);

		response = paymentResponse.getPaymentStatus().equals("success") ? "payment processing successful and order placed": "there is a failure in payment api , order added to cart";
		logger.info("Order Service getting Response from Payment-Service : "+ new ObjectMapper().writeValueAsString(response));
		order.setPaymentId(paymentResponse.getPaymentId());
		order.setTransactionId(paymentResponse.getTransactionId());
		order.setPaymentStatus(paymentResponse.getPaymentStatus());
		repository.save(order);
		return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(),response);
	}

	public List<TransactionResponse> getAll() {
		logger.error(ENDPOINT_URL+" saveOrder "+ORDER_ENDPOINT_URL);
		List<Order> orders = repository.findAll();
		HttpEntity<List<Order>> requestEntity = new HttpEntity<>(orders);

		// Make a POST request to the payment service
		ResponseEntity<List<Payment>> responseEntity = template.exchange(
				"http://MICRO-PAYMENT-SERVICE/payment/getAllPayments", // Assuming POST endpoint
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Payment>>() {
				});

		List<Payment> payments = responseEntity.getBody();
		 List<TransactionResponse>  listTransactionResponse= new ArrayList<>();
		for (Payment payment2 : payments) {

			for (Order order : orders) {
				if (order.getPaymentId() == payment2.getPaymentId()) {
					TransactionResponse response = TransactionResponse.TransactionResponseBuilder.newInstance()
							.setOrder(order)
							.setPayment(payment2).build();
					listTransactionResponse.add(response);
				}
			}
		}

		return listTransactionResponse;
	}
}
