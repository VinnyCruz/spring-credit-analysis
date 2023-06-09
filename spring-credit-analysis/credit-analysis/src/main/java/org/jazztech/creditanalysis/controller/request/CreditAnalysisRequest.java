package org.jazztech.creditanalysis.controller.request;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

public record CreditAnalysisRequest (
    UUID clientId,
    BigDecimal monthlyIncome,
    BigDecimal requestedAmount
) {
    @Builder
    public CreditAnalysisRequest {}
}
