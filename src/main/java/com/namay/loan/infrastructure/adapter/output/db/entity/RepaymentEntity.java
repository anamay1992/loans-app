package com.namay.loan.infrastructure.adapter.output.db.entity;

import com.namay.loan.domain.model.constant.RepaymentStrategy;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "repayments")
public class RepaymentEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loan_id", nullable = false)
    private Long loanId;

    @Column(name = "installment_id", nullable = false)
    private Long installmentId;

    @Column(name = "amount_paid", nullable = false)
    private BigDecimal amountPaid;

    @Column(name = "extra_amount_paid", nullable = false)
    private BigDecimal extraAmountPaid;

    @Column(name = "strategy")
    private RepaymentStrategy strategyType;

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

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
