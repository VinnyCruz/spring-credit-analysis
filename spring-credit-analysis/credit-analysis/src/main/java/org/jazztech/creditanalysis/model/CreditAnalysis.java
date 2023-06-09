package org.jazztech.creditanalysis.model;

import lombok.Builder;
import org.jazztech.creditanalysis.apiclient.dto.Client;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreditAnalysis (
        UUID clientId,
        Boolean approved,
        BigDecimal approvedLimit,
        BigDecimal withdraw,
        BigDecimal annualInterest,
        BigDecimal requestedAmount,
        LocalDateTime date
) {
    @Builder(toBuilder = true)
    public CreditAnalysis(UUID clientId, Boolean approved, BigDecimal approvedLimit,
                          BigDecimal withdraw, BigDecimal annualInterest,
                          BigDecimal requestedAmount, LocalDateTime date) {
        this.clientId = clientId;
        this.approved = approved;
        this.approvedLimit = approvedLimit;
        this.withdraw = withdraw;
        this.annualInterest = annualInterest;
        this.requestedAmount = requestedAmount;
        this.date = LocalDateTime.now();
    }

    public CreditAnalysis returnAnalysisWithClientId (Client client) {
        return this.toBuilder()
                .clientId(client.id())
                .build();
    }

    public CreditAnalysis returnAnalysisApprovedTrue
            (Boolean approved, BigDecimal approvedlimit,
            BigDecimal withdraw, BigDecimal annualInterest) {
        return this.toBuilder()
                .approved(approved)
                .approvedLimit(approvedlimit)
                .withdraw(withdraw)
                .annualInterest(annualInterest)
                .date(LocalDateTime.now())
                .build();
    }

    @Override
    public String toString() {
        return "CreditAnalysis{" +
                "clientId=" + clientId +
                ", approved=" + approved +
                ", approvedlimit=" + approvedLimit +
                ", withdraw=" + withdraw +
                ", annualInterest=" + annualInterest +
                ", requestedAmount=" + requestedAmount +
                ", date=" + LocalDateTime.now() +
                '}';
    }
}
