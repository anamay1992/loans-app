package com.namay.loan.domain.model.constant;

public enum RepaymentStrategy implements ValuedEnum {
    REDUCE_TERM(0, "Reducir tiempo"),
    REDUCE_QUOTA(1, "Reducir cuotas");

    private final int status;
    private final String description;

    RepaymentStrategy(int status, String description) {
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
