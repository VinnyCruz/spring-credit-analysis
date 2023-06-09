package org.jazztech.creditanalysis.mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.jazztech.creditanalysis.controller.response.CreditAnalysisResponse;
import org.jazztech.creditanalysis.repository.entity.CreditAnalysisEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-19T15:49:01-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class CreditResponseMapperImpl implements CreditResponseMapper {

    @Override
    public CreditAnalysisResponse from(CreditAnalysisEntity creditAnalysis) {
        if ( creditAnalysis == null ) {
            return null;
        }

        UUID id = null;
        UUID clientId = null;
        Boolean approved = null;
        BigDecimal requestedAmount = null;
        BigDecimal approvedLimit = null;
        BigDecimal withdraw = null;
        Double annualInterest = null;
        LocalDateTime date = null;

        id = creditAnalysis.getId();
        clientId = creditAnalysis.getClientId();
        approved = creditAnalysis.getApproved();
        requestedAmount = creditAnalysis.getRequestedAmount();
        approvedLimit = creditAnalysis.getApprovedLimit();
        withdraw = creditAnalysis.getWithdraw();
        if ( creditAnalysis.getAnnualInterest() != null ) {
            annualInterest = creditAnalysis.getAnnualInterest().doubleValue();
        }
        date = creditAnalysis.getDate();

        CreditAnalysisResponse creditAnalysisResponse = new CreditAnalysisResponse( id, clientId, approved, requestedAmount, approvedLimit, withdraw, annualInterest, date );

        return creditAnalysisResponse;
    }
}
