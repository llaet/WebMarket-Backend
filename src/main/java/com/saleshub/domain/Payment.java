package com.saleshub.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saleshub.domain.enums.PaymentStatus;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private Integer paymentStatus;
	
	@MapsId
	@OneToOne
	@JsonIgnore
	@JoinColumn(name="order_id")
	private SaleOrder saleOrder;
	
	public Payment() {}
	
	public Payment(Integer id, PaymentStatus paymentStatus, SaleOrder saleOrder) {
		super();
		this.id = id;
		this.paymentStatus = paymentStatus.getPaymentCode();
		this.saleOrder = saleOrder;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PaymentStatus getPaymentStatus() {
		return PaymentStatus.toEnum(paymentStatus);
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus.getPaymentCode();
	}

	public SaleOrder getSaleOrder() {
		return saleOrder;
	}

	public void setSaleOrder(SaleOrder saleOrder) {
		this.saleOrder = saleOrder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
