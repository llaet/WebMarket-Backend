package com.saleshub.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "ordered_item")
public class OrderedItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private OrderedItemPK id = new OrderedItemPK();
	
	private Double discount;
	private Integer quantity;
	private Double price;
	
	public OrderedItem() {}
	
	public OrderedItem(SaleOrder order, Product product, 
			Double discount, Integer quantity, Double price) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.discount = discount;
		this.quantity = quantity;
		this.price = price;
	}
	
	public Double getSubTotal() {
		return (price - discount) * quantity;
	}

	public OrderedItemPK getId() {
		return id;
	}

	public void setId(OrderedItemPK id) {
		this.id = id;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@JsonIgnore
	public SaleOrder getSaleOrder() {
		return id.getSaleOrder();
	}
	
	public void setSaleOrder(SaleOrder order) {
		id.setOrder(order);
	}

	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
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
		OrderedItem other = (OrderedItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat
                .getCurrencyInstance(new Locale("pt","BR"));
        final StringBuffer sb = new StringBuffer();
        sb.append("\nproduto: ").append(getProduct().getName());
        sb.append(", quantidade: ").append(quantity);
        sb.append(", preço unitário: ").append(numberFormat.format(price));
        sb.append(", subtotal: ").append(numberFormat.format(getSubTotal()));
        return sb.toString();
    }
}
