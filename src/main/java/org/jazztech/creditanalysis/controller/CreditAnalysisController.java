package org.jazztech.creditanalysis.controller;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.jazztech.creditanalysis.controller.request.CreditAnalysisRequest;
import org.jazztech.creditanalysis.controller.response.CreditAnalysisResponse;
import org.jazztech.creditanalysis.service.create.CreateAnalysisService;
import org.jazztech.creditanalysis.service.search.SearchAnalysisService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1.0/creditanalysis")
@RequiredArgsConstructor
public class CreditAnalysisController {
    private final CreateAnalysisService createAnalysisService;
    private final SearchAnalysisService searchAnalysisService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreditAnalysisResponse requestAnalysis(@RequestBody CreditAnalysisRequest creditAnalysisRequest) {
        return createAnalysisService.makeAnalysisRequest(creditAnalysisRequest);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreditAnalysisResponse getAnalysis(@PathVariable(value = "id") UUID id) {
        return searchAnalysisService.getAnalysisById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CreditAnalysisResponse> getAnalysisBy(@RequestParam(value = "id", required = false) UUID clientId,
                                                      @RequestParam(value = "cpf", required = false) String clientCpf) {
        if (clientId == null && clientCpf == null) {
            return searchAnalysisService.getAllCreditAnalyses();
        } else if (clientId == null) {
            return searchAnalysisService.getAllAnalsysisByClientCpf(clientCpf);
        } else {
            return searchAnalysisService.getAllAnalsysisByClientId(clientId);
        }
    }
}
