package com.namay.loan.domain.model;

import com.namay.loan.domain.model.constant.RepaymentStrategy;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Repayment {
    private Long id;
    private Long loanId;
    private Long installmentId;
    private BigDecimal amountPaid;
    private BigDecimal extraAmountPaid;
    private RepaymentStrategy strategyType;
    private LocalDateTime paymentDate;

    public Repayment() {
    }

    public Repayment(Long id, Long loanId, Long installmentId, BigDecimal amountPaid, BigDecimal extraAmountPaid, RepaymentStrategy strategyType, LocalDateTime paymentDate) {
        this.id = id;
        this.loanId = loanId;
        this.installmentId = installmentId;
        this.amountPaid = amountPaid;
        this.extraAmountPaid = extraAmountPaid;
        this.strategyType = strategyType;
        this.paymentDate = paymentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Long getInstallmentId() {
        return installmentId;
    }

    public void setInstallmentId(Long installmentId) {
        this.installmentId = installmentId;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public BigDecimal getExtraAmountPaid() {
        return extraAmountPaid;
    }

    public void setExtraAmountPaid(BigDecimal extraAmountPaid) {
        this.extraAmountPaid = extraAmountPaid;
    }

    public RepaymentStrategy getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(RepaymentStrategy strategyType) {
        this.strategyType = strategyType;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
