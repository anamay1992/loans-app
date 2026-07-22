package com.namay.loan.infrastructure.adapter.output.db.entity;

import com.namay.loan.domain.model.constant.LoanStatus;
import com.namay.loan.domain.model.constant.LoanSystem;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "loans")
public class LoanEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "capital", nullable = false)
    private BigDecimal capital;

    @Column(name = "system")
    private LoanSystem systemType;

    @Column(name = "rate", nullable = false)
    private BigDecimal rate;

    @Column(name = "original_months", nullable = false)
    private Integer originalMonths;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "loan_id")
    private List<InstallmentEntity> installments;

    @Column(name = "total_earned")
    private BigDecimal totalEarned;

    @Column(name = "status", nullable = false)
    private LoanStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
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

    public List<InstallmentEntity> getInstallments() {
        return installments;
    }

    public void setInstallments(List<InstallmentEntity> installments) {
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
