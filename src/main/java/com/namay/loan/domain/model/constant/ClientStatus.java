package com.namay.loan.domain.model.constant;

public enum ClientStatus implements ValuedEnum {
    ACTIVE(0, "Activo"),
    INACTIVE(1, "Inactivo"),
    BANNED(2, "Baneado o bloqueado");

    private final int status;
    private final String description;

    ClientStatus(int status, String description) {
        this.status = status;
        this.description = description;
    }

    @Override
    public int getStatus() {
        return this.status;
    }

    public String getDescription() {
        return this.description;
    }
}
