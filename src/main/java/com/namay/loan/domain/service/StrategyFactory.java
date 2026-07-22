package com.namay.loan.domain.service;

import com.namay.loan.domain.model.constant.RepaymentStrategy;
import com.namay.loan.domain.service.impl.QuotaStrategy;
import com.namay.loan.domain.service.impl.TermStrategy;

public class StrategyFactory {

    private final SystemFactory systemFactory;

    public StrategyFactory(SystemFactory systemFactory) {
        this.systemFactory = systemFactory;
    }

    public Strategy get(RepaymentStrategy strategyType) {
        if (strategyType == RepaymentStrategy.REDUCE_QUOTA) {
            return new QuotaStrategy(systemFactory);
        } else {
            return new TermStrategy();
        }
    }
}
