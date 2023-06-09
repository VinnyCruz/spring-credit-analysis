package org.jazztech.creditanalysis.service.search;

import lombok.RequiredArgsConstructor;
import org.jazztech.creditanalysis.controller.response.CreditAnalysisResponse;
import org.jazztech.creditanalysis.mapper.CreditResponseMapper;
import org.jazztech.creditanalysis.repository.CreditAnalysisRepository;
import org.jazztech.creditanalysis.repository.entity.CreditAnalysisEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class SearchAnalysisService {
    private final CreditAnalysisRepository creditAnalysisRepository;
    private final CreditResponseMapper creditResponseMapper;
    public CreditAnalysisResponse getAnalysisById(UUID id) {
        final CreditAnalysisEntity creditAnalysisEntity = creditAnalysisRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Análise de Crédito não encontrada"));
        return creditResponseMapper.from(creditAnalysisEntity);
    }
}
