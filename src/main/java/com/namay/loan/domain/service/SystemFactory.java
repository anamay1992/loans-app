package com.namay.loan.domain.service;

import com.namay.loan.domain.model.constant.LoanSystem;
import com.namay.loan.domain.service.impl.AmericanSystem;
import com.namay.loan.domain.service.impl.FrenchSystem;

public class SystemFactory {

    public System get(LoanSystem systemType) {
        return (systemType == LoanSystem.FRENCH)
                ? new FrenchSystem()
                : new AmericanSystem();
    }
}
