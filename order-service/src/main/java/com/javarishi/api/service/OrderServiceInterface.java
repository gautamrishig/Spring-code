package com.javarishi.api.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javarishi.api.common.TransactionRequest;
import com.javarishi.api.common.TransactionResponse;

public interface OrderServiceInterface {
	public TransactionResponse saveOrder(TransactionRequest request) throws JsonProcessingException ;
	public List<TransactionResponse> getAll();
}
