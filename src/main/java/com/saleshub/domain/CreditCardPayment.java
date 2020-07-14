package com.saleshub.domain;

import javax.persistence.Entity;

import com.saleshub.domain.enums.PaymentStatus;

@Entity(name = "credit_card_payment")
public class CreditCardPayment extends Payment {

	private static final long serialVersionUID = 1L;

	private Integer installmentsQuantity;
	
	public CreditCardPayment() {}

	public CreditCardPayment(Integer id, PaymentStatus paymentStatus, SaleOrder request,
			Integer installmentsQuantity) {
		super(id, paymentStatus, request);
		this.installmentsQuantity = installmentsQuantity;
	}

	public Integer getInstallmentsQuantity() {
		return installmentsQuantity;
	}

	public void setInstallmentsQuantity(Integer installmentsQuantity) {
		this.installmentsQuantity = installmentsQuantity;
	}
	
}
