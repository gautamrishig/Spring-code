package com.javarishi.api.controller;

import java.util.List;

import com.javarishi.api.service.OrderService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javarishi.api.common.TransactionRequest;
import com.javarishi.api.common.TransactionResponse;
import com.javarishi.api.service.OrderServiceInterface;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceInterface service;
    Logger logger = org.slf4j.LoggerFactory.getLogger(OrderController.class);
    
//    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
//    private String ENDPOINT_URL;
    
    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request) throws JsonProcessingException {
        logger.info("TransactionResponse  bookOrder ");
        return service.saveOrder(request);
    }
    
    
    @GetMapping("/allOrder")
    public List<TransactionResponse> bookOrder() throws JsonProcessingException {
        logger.info("TransactionResponse  bookOrder ");
        return service.getAll();
    }
}
