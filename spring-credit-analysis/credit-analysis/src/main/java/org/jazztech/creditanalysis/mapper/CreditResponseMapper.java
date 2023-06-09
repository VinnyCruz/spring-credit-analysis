package org.jazztech.creditanalysis.mapper;

import org.jazztech.creditanalysis.controller.response.CreditAnalysisResponse;
import org.jazztech.creditanalysis.repository.entity.CreditAnalysisEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditResponseMapper {
    CreditAnalysisResponse from(CreditAnalysisEntity creditAnalysis);
}
