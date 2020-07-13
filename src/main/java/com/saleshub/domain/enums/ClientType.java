package com.saleshub.domain.enums;

public enum ClientType {

	PESSOA_FISICA(1, "Pessoa Física"), PESSOA_JURIDICA(2, "Pessoa Jurídica");
	
	private Integer clientNumber;
	private String clientDescription;
	
	private ClientType(Integer clientNumber, String clientDescription) {
		this.clientNumber = clientNumber;
		this.clientDescription = clientDescription;
	}

	public Integer getClientNumber() {
		return clientNumber;
	}

	public String getClientDescription() {
		return clientDescription;
	}
	
	public static ClientType toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		
		for(ClientType type : ClientType.values()) {
			if(code.equals(type.getClientNumber())) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Código não existente. Código: " + code);
	}
}
