package com.namay.loan.domain.model.constant;

public enum InstallmentStatus implements ValuedEnum {
    PENDING(0, "Pendiente"),
    PAID(1, "Pagada");

    private final int status;
    private final String description;

    InstallmentStatus(int status, String description) {
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
