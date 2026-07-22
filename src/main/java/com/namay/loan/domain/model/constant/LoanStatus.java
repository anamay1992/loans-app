package com.namay.loan.domain.model.constant;

public enum LoanStatus implements ValuedEnum {
    ACTIVE(0, "Activo"),
    SETTLED(1, "Cancelado o Pagado");

    private final int status;
    private final String description;

    LoanStatus(int status, String description) {
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
