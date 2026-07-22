package com.namay.loan.application.processor;

import com.namay.loan.domain.model.constant.PaymentProcess;
import com.namay.loan.application.processor.impl.InterestOnlyProcess;
import com.namay.loan.application.processor.impl.RegularProcess;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProcessFactory {

    private final RegularProcess regularProcess;
    private final InterestOnlyProcess interestOnlyProcess;

    public ProcessFactory(RegularProcess regularProcess,
                          InterestOnlyProcess interestOnlyProcess) {
        this.regularProcess = regularProcess;
        this.interestOnlyProcess = interestOnlyProcess;
    }

    public Process get(PaymentProcess processType) {
        if (processType == PaymentProcess.INTEREST_ONLY) {
            return interestOnlyProcess;
        }
        return regularProcess;
    }
}
