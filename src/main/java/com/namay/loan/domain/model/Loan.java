package com.namay.loan.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.namay.loan.domain.model.constant.LoanStatus;
import com.namay.loan.domain.model.constant.LoanSystem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Loan {

    private Long id;
    private Long clientId;
    private String clientName;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal capital;
    private LoanSystem systemType;
    private BigDecimal rate;
    private Integer originalMonths;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Installment> installments = new ArrayList<>();

    private BigDecimal totalEarned;
    private LoanStatus status;

    public Loan() {
    }

    public Loan(Long id, Long clientId, String clientName, LocalDate startDate, LocalDate endDate, BigDecimal capital, LoanSystem systemType, BigDecimal rate, Integer originalMonths, List<Installment> installments, BigDecimal totalEarned, LoanStatus status) {
        this.id = id;
        this.clientId = clientId;
        this.clientName = clientName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capital = capital;
        this.systemType = systemType;
        this.rate = rate;
        this.originalMonths = originalMonths;
        this.installments = installments;
        this.totalEarned = totalEarned;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public LoanSystem getSystemType() {
        return systemType;
    }

    public void setSystemType(LoanSystem systemType) {
        this.systemType = systemType;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getOriginalMonths() {
        return originalMonths;
    }

    public void setOriginalMonths(Integer originalMonths) {
        this.originalMonths = originalMonths;
    }

    public List<Installment> getInstallments() {
        return installments;
    }

    public void setInstallments(List<Installment> installments) {
        this.installments = installments;
    }

    public BigDecimal getTotalEarned() {
        return totalEarned;
    }

    public void setTotalEarned(BigDecimal totalEarned) {
        this.totalEarned = totalEarned;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }
}
