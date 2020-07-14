package com.saleshub.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class OrderedItemPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private SaleOrder saleOrder;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	public SaleOrder getSaleOrder() {
		return saleOrder;
	}
	
	public void setOrder(SaleOrder order) {
		this.saleOrder = order;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((saleOrder == null) ? 0 : saleOrder.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		OrderedItemPK other = (OrderedItemPK) obj;
		if (saleOrder == null) {
			if (other.saleOrder != null)
				return false;
		} else if (!saleOrder.equals(other.saleOrder))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	
}
