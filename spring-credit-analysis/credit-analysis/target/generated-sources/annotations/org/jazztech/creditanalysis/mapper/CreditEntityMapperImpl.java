package org.jazztech.creditanalysis.mapper;

import javax.annotation.processing.Generated;
import org.jazztech.creditanalysis.model.CreditAnalysis;
import org.jazztech.creditanalysis.repository.entity.CreditAnalysisEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-19T15:49:02-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class CreditEntityMapperImpl implements CreditEntityMapper {

    @Override
    public CreditAnalysisEntity from(CreditAnalysis creditAnalysis) {
        if ( creditAnalysis == null ) {
            return null;
        }

        CreditAnalysisEntity.CreditAnalysisEntityBuilder creditAnalysisEntity = CreditAnalysisEntity.builder();

        creditAnalysisEntity.clientId( creditAnalysis.clientId() );
        creditAnalysisEntity.approved( creditAnalysis.approved() );
        creditAnalysisEntity.approvedLimit( creditAnalysis.approvedLimit() );
        creditAnalysisEntity.withdraw( creditAnalysis.withdraw() );
        creditAnalysisEntity.annualInterest( creditAnalysis.annualInterest() );
        creditAnalysisEntity.requestedAmount( creditAnalysis.requestedAmount() );
        creditAnalysisEntity.date( creditAnalysis.date() );

        return creditAnalysisEntity.build();
    }
}
