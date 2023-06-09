package org.jazztech.creditanalysis.mapper;

import org.jazztech.creditanalysis.controller.request.CreditAnalysisRequest;
import org.jazztech.creditanalysis.model.CreditAnalysis;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditModelMapper {
    CreditAnalysis from(CreditAnalysisRequest creditAnalysisRequest);
}
