package com.cus.shopping.model;

public class PaymentRequest {

	private Double amountPaid;
	
	private String type; 
	
	private Integer idOrder;

	public PaymentRequest() {
		super();
	}

	public PaymentRequest(Double amountPaid, String type, Integer idOrder) {
		super();
		this.amountPaid = amountPaid;
		this.type = type;
		this.idOrder = idOrder;
	}

	public Double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Integer idOrder) {
		this.idOrder = idOrder;
	}
	
	
}
