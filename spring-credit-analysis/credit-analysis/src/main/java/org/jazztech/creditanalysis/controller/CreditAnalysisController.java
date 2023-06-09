package org.jazztech.creditanalysis.controller;

import lombok.RequiredArgsConstructor;
import org.jazztech.creditanalysis.controller.request.CreditAnalysisRequest;
import org.jazztech.creditanalysis.controller.response.CreditAnalysisResponse;
import org.jazztech.creditanalysis.repository.CreditAnalysisRepository;
import org.jazztech.creditanalysis.service.create.CreateAnalysisService;
import org.jazztech.creditanalysis.service.search.SearchAnalysisService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "v1.0/creditanalysis")
@RequiredArgsConstructor
public class CreditAnalysisController {
    private final CreateAnalysisService createAnalysisService;
    private final SearchAnalysisService searchAnalysisService;
    private final CreditAnalysisRepository creditAnalysisRepository;

    @PostMapping
    public CreditAnalysisResponse requestAnalysis(@RequestBody CreditAnalysisRequest creditAnalysisRequest) {
        return createAnalysisService.makeAnalysisRequest(creditAnalysisRequest);
    }

    @GetMapping(path = "/{id}")
    public CreditAnalysisResponse getAnalysis(@PathVariable(value = "id") UUID id) {
        return searchAnalysisService.getAnalysisById(id);
    }
}
