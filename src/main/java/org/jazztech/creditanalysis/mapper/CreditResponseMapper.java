package org.jazztech.creditanalysis.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.jazztech.creditanalysis.controller.response.CreditAnalysisResponse;
import org.jazztech.creditanalysis.repository.entity.CreditAnalysisEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CreditResponseMapper {
    CreditAnalysisResponse from(CreditAnalysisEntity creditAnalysis);

    CreditAnalysisResponse mapToResponse(CreditAnalysisEntity entity);

    default List<CreditAnalysisResponse> mapToResponseList(List<CreditAnalysisEntity> entities) {
        return entities.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
