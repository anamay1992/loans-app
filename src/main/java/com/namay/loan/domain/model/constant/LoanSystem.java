package com.namay.loan.domain.model.constant;

public enum LoanSystem implements ValuedEnum {
    FRENCH(0, "Metodo frances"),
    AMERICAN(1, "Metodo americano");

    private final int status;
    private final String description;

    LoanSystem(int status, String description) {
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
