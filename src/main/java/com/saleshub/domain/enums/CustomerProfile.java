package com.saleshub.domain.enums;

public enum CustomerProfile {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2,"ROLE_CLIENTE");

    private Integer roleCode;
    private String role;

    private CustomerProfile(Integer roleCode, String role) {
        this.role = role;
        this.roleCode = roleCode;
    }

    public Integer getRoleCode() {
        return roleCode;
    }

    public String getRole() {
        return role;
    }

    public static CustomerProfile toEnum(Integer roleCode) {
        if(roleCode == null) {
            return null;
        }

        for(CustomerProfile type : CustomerProfile.values()) {
            if(roleCode.equals(type.getRoleCode())) {
                return type;
            }
        }

        throw new IllegalArgumentException("Código de perfil do cliente não existe. Código: " + roleCode);
    }
}
