package com.saleshub.domain.enums;

public enum PaymentStatus {

	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");
	
	private Integer paymentCode;
	private String paymentDescription;
	
	private PaymentStatus(Integer paymentCode, String paymentDescription) {
		this.paymentCode = paymentCode;
		this.paymentDescription = paymentDescription;
	}

	public Integer getPaymentCode() {
		return paymentCode;
	}

	public String getPaymentDescription() {
		return paymentDescription;
	}
	
	public static PaymentStatus toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		
		for(PaymentStatus type : PaymentStatus.values()) {
			if(code.equals(type.getPaymentCode())) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Código não existente. Código: " + code);
	}
	
}
