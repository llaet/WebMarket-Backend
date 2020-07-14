package com.saleshub.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.saleshub.domain.enums.PaymentStatus;

@Entity(name = "bank_slip_payment")
public class BankSlipPayment extends Payment {

	private static final long serialVersionUID = 1L;

	private Date dueDate;
	private Date payDay;
	
	public BankSlipPayment() {}

	public BankSlipPayment(Integer id, PaymentStatus paymentStatus, SaleOrder request,
			Date dueDate, Date payDay) {
		super(id, paymentStatus, request);
		this.dueDate = dueDate;
		this.payDay = payDay;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPayDay() {
		return payDay;
	}

	public void setPayDay(Date payDay) {
		this.payDay = payDay;
	}
	
}
