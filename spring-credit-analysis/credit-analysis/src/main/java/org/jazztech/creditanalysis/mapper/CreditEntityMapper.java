package org.jazztech.creditanalysis.mapper;

import org.jazztech.creditanalysis.model.CreditAnalysis;
import org.jazztech.creditanalysis.repository.entity.CreditAnalysisEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditEntityMapper {
    CreditAnalysisEntity from(CreditAnalysis creditAnalysis);
}
