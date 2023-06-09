package org.jazztech.creditanalysis.service.search;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.jazztech.creditanalysis.apiclient.ClientApi;
import org.jazztech.creditanalysis.apiclient.dto.Client;
import org.jazztech.creditanalysis.controller.response.CreditAnalysisResponse;
import org.jazztech.creditanalysis.exception.ClientNotFoundException;
import org.jazztech.creditanalysis.mapper.CreditResponseMapper;
import org.jazztech.creditanalysis.repository.CreditAnalysisRepository;
import org.jazztech.creditanalysis.repository.entity.CreditAnalysisEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class SearchAnalysisService {
    private final CreditAnalysisRepository creditAnalysisRepository;
    private final CreditResponseMapper creditResponseMapper;
    private final ClientApi clientApi;

    public CreditAnalysisResponse getAnalysisById(UUID id) {
        final CreditAnalysisEntity creditAnalysisEntity = creditAnalysisRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Análise de Crédito não encontrada"));
        return creditResponseMapper.from(creditAnalysisEntity);
    }

    public List<CreditAnalysisResponse> getAllCreditAnalyses() {
        final List<CreditAnalysisEntity> creditAnalyses;
        creditAnalyses = creditAnalysisRepository.findAll();
        return creditAnalyses
                .stream()
                .map(creditResponseMapper::from)
                .collect(Collectors.toList());
    }

    public List<CreditAnalysisResponse> getAllAnalsysisByClientId(UUID clientId) {
        final List<CreditAnalysisEntity> entityAnalysisList;
        entityAnalysisList = creditAnalysisRepository.findByClientId(clientId);
        final List<CreditAnalysisResponse> responseAnalysisList = creditResponseMapper
                .mapToResponseList(entityAnalysisList);
        return responseAnalysisList;
    }

    public List<CreditAnalysisResponse> getAllAnalsysisByClientCpf(String clientCpf) {
        final List<Client> client = clientApi.getClientByCpf(clientCpf);
        if (client.isEmpty()) {
            throw new ClientNotFoundException("Could not found any Client with the CPF: %s".formatted(clientCpf));
        }
        return getAllAnalsysisByClientId(client.get(0).id());
    }
}
