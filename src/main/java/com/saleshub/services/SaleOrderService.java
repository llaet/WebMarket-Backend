package com.saleshub.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saleshub.domain.BankSlipPayment;
import com.saleshub.domain.OrderedItem;
import com.saleshub.domain.SaleOrder;
import com.saleshub.domain.enums.PaymentStatus;
import com.saleshub.repositories.SaleOrderRepository;
import com.saleshub.services.exceptions.ObjectNotFoundException;

@Service
public class SaleOrderService {

	@Autowired
	private SaleOrderRepository repository;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderedItemService orderedItemService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private EmailService emailService;
	
	public SaleOrder findById(Integer id) {
		return this.repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado. Id: " + id));
	}

	public SaleOrder create(SaleOrder saleOrder) {
		
		saleOrder.setId(null);
		saleOrder.setOrderedAt(new Date());
		saleOrder.setCustomer(this.customerService.findById(saleOrder.getCustomer().getId()));
		saleOrder.getPayment().setPaymentStatus(PaymentStatus.PENDENTE);
		saleOrder.getPayment().setSaleOrder(saleOrder);
		
		if(saleOrder.getPayment() instanceof BankSlipPayment) {
			
			BankSlipPayment payment = (BankSlipPayment) saleOrder.getPayment();
			
			this.paymentService.fillOutBankSlipPayment(payment, saleOrder.getOrderedAt());
		}
		
		this.repository.saveAndFlush(saleOrder);
		
		this.paymentService.create(saleOrder.getPayment());
		
		for(OrderedItem order : saleOrder.getItems()) {
			order.setDiscount(0.0);
			order.setProduct(this.productService.findById(order.getProduct().getId()));
			order.setPrice(order.getProduct().getPrice());
			order.setSaleOrder(saleOrder);
		}
		this.orderedItemService.saveAll(saleOrder.getItems());

		this.emailService.sendOrderConfirmationEmail(saleOrder);

		return saleOrder;		
	}
}
