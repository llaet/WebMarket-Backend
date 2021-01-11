package com.saleshub.domain.enums;

public enum RequestEnum {

    BAD_REQUEST("Integridade de Dados"),
    BAD_REQUEST_AMAZON("Erro de Serviços Amazon"),
    NOT_FOUND("Não Encontrado"),
    FORBIDDEN("Acesso Negado"),
    UNPROCESSABLE_ENTITY("Erro de Validação");

    private String errorDescription;

    RequestEnum(String errorDescription){
        this.errorDescription = errorDescription;
    }

    public String getErrorDescription(){
        return errorDescription;
    }
}
