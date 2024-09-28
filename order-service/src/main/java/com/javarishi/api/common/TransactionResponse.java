package com.javarishi.api.common;

import com.javarishi.api.entity.Order;

public class TransactionResponse {
	private Order order;
	private Double amount;
	private String transactionId;
	private String message;
	private Payment payment;

//	

	public Payment getPayment() {
		return payment;
	}

	// Private constructor to enforce the use of the builder
	private TransactionResponse(TransactionResponseBuilder builder) {
	     this.order = builder.order;
	        this.amount = builder.amount > 0 ? builder.amount : null; // Include if greater than 0
	        this.transactionId = builder.transactionId != null && !builder.transactionId.isEmpty() ? builder.transactionId : null; // Include if not null/empty
	        this.message = builder.message != null ? builder.message : null; // Include if not null
	        this.payment = builder.payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public TransactionResponse(Order order, Double amount, String transactionId, String message) {
		super();
		this.order = order;
		if(amount>0)
		this.amount = amount;
		if(transactionId!=null && transactionId.length()>0)
		this.transactionId = transactionId;
		this.message = message;
	}

	public static class TransactionResponseBuilder {

		private Order order;
		private Double amount=(double) -1;
		private String transactionId;
		private String message;
		private Payment payment;

		// Static method to create a new builder instance
		public static TransactionResponseBuilder newInstance() {
			return new TransactionResponseBuilder();
		}

		// Private constructor to prevent direct instantiation
		private TransactionResponseBuilder() {
		}

		public TransactionResponseBuilder setOrder(Order order) {
			this.order = order;
			return this;
		}

		public TransactionResponseBuilder setAmount(Double amount) {
			this.amount = amount;
			return this;
		}

		public TransactionResponseBuilder setTransactionId(String transactionId) {
			this.transactionId = transactionId;
			return this;
		}

		public TransactionResponseBuilder setMessage(String message) {
			this.message = message;
			return this;
		}

		public TransactionResponseBuilder setPayment(Payment payment) {
			this.payment = payment;
			return this;
		}

		public TransactionResponse build() {

			return new TransactionResponse(this);

		}

		@Override
		public String toString() {
			return "TransactionResponseBuilder [order=" + order + ", amount=" + amount + ", transactionId="
					+ transactionId + ", message=" + message + ", payment=" + payment + "]";
		}

	}

}
