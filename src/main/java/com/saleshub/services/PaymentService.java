package com.saleshub.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saleshub.domain.BankSlipPayment;
import com.saleshub.domain.Payment;
import com.saleshub.repositories.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository repository;
	
	public Payment create(Payment payment) {
		return this.repository.saveAndFlush(payment);
	}
	
	
	public void fillOutBankSlipPayment(BankSlipPayment payment, Date orderedAt) {
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(orderedAt);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		
		payment.setDueDate(calendar.getTime());
	}

}
