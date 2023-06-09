package org.jazztech.creditanalysis.repository.entity;

import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CREDITANALYSIS")
@Immutable
public class CreditAnalysisEntity {
    @Id
    UUID id;
    @Column(name = "client_id")
    UUID clientId;
    Boolean approved;
    @Column(name = "approved_limit")
    BigDecimal approvedLimit;
    BigDecimal withdraw;
    @Column(name = "annual_interest")
    BigDecimal annualInterest;
    @Column(name = "requested_amount")
    BigDecimal requestedAmount;
    LocalDateTime date;

    private CreditAnalysisEntity() {
        this.id = UUID.randomUUID();
    }

    @Builder
    public CreditAnalysisEntity
            (UUID clientId, Boolean approved, BigDecimal approvedLimit,
             BigDecimal withdraw, BigDecimal annualInterest, BigDecimal requestedAmount,
             LocalDateTime date) {
        this.id = UUID.randomUUID();
        this.clientId = clientId;
        this.approved = approved;
        this.approvedLimit = approvedLimit;
        this.withdraw = withdraw;
        this.annualInterest = annualInterest;
        this.requestedAmount = requestedAmount;
        this.date = date;
    }

    public UUID getId() {return id;}

    public UUID getClientId() {return clientId;}

    public Boolean getApproved() {return approved;}

    public BigDecimal getApprovedLimit() {return approvedLimit;}

    public BigDecimal getWithdraw() {return withdraw;}

    public BigDecimal getAnnualInterest() {return annualInterest;}

    public BigDecimal getRequestedAmount() {return requestedAmount;}

    public LocalDateTime getDate() {return date;}

    @Override
    public String toString() {
        return "CreditAnalysisEntity{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", approved=" + approved +
                ", approvedLimit=" + approvedLimit +
                ", withdraw=" + withdraw +
                ", annualInterest=" + annualInterest +
                ", requestedAmount=" + requestedAmount +
                ", date=" + date +
                '}';
    }
}
