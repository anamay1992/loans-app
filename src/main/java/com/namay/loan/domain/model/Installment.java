package com.namay.loan.domain.model;

import com.namay.loan.domain.model.constant.InstallmentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Installment {
    private Long id;
    private Long loanId;
    private Integer sequence;
    private BigDecimal amount;
    private BigDecimal principal;
    private BigDecimal interest;
    private BigDecimal balance;
    private LocalDate dueDate;
    private InstallmentStatus status;

    public Installment() {
    }

    public Installment(Long id, Integer sequence, BigDecimal amount, BigDecimal principal, BigDecimal interest, BigDecimal balance, LocalDate dueDate, InstallmentStatus status) {
        this.id = id;
        this.sequence = sequence;
        this.amount = amount;
        this.principal = principal;
        this.interest = interest;
        this.balance = balance;
        this.dueDate = dueDate;
        this.status = status;
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

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public InstallmentStatus getStatus() {
        return status;
    }

    public void setStatus(InstallmentStatus status) {
        this.status = status;
    }
}
