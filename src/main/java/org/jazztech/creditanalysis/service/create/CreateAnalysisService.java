package org.jazztech.creditanalysis.service.create;

import feign.FeignException;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.jazztech.creditanalysis.apiclient.ClientApi;
import org.jazztech.creditanalysis.apiclient.dto.Client;
import org.jazztech.creditanalysis.controller.request.CreditAnalysisRequest;
import org.jazztech.creditanalysis.controller.response.CreditAnalysisResponse;
import org.jazztech.creditanalysis.exception.ClientNotFoundException;
import org.jazztech.creditanalysis.mapper.CreditEntityMapper;
import org.jazztech.creditanalysis.mapper.CreditModelMapper;
import org.jazztech.creditanalysis.mapper.CreditResponseMapper;
import org.jazztech.creditanalysis.model.CreditAnalysis;
import org.jazztech.creditanalysis.repository.CreditAnalysisRepository;
import org.jazztech.creditanalysis.repository.entity.CreditAnalysisEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAnalysisService {
    private static final BigDecimal LIMIT_PERCENTAGE_IF_REQUEST_IS_GREATER_THAN_FIFTH_PERCENT = BigDecimal.valueOf(0.15);
    private static final BigDecimal LIMIT_PERCENTAGE_IF_REQUEST_IS_LESS_THAN_FIFTH_PERCENT = BigDecimal.valueOf(0.30);
    private static final BigDecimal ANNUAL_INTEREST = BigDecimal.valueOf(0.15);
    private static final int LIMIT_INCOME = 50000;

    private final CreditAnalysisRepository creditAnalysisRepository;
    private final CreditResponseMapper creditResponseMapper;
    private final CreditModelMapper creditModelMapper;
    private final CreditEntityMapper creditEntityMapper;
    private final ClientApi clientApi;

    public CreditAnalysis checkIfClientExists(CreditAnalysisRequest creditAnalysisRequest) {
        final CreditAnalysis creditAnalysis = creditModelMapper.from(creditAnalysisRequest);
        final UUID clientId = creditAnalysis.clientId();
        final Client client = clientFoundInClientApi(clientId);
        final CreditAnalysis analysisWithClientIdUpdated = creditAnalysis
                .updateClientId(client);
        return analysisWithClientIdUpdated;
    }

    public CreditAnalysis checkIfIsApproved(CreditAnalysisRequest creditAnalysisRequest) {
        final CreditAnalysis pendentAnalysis = checkIfClientExists(creditAnalysisRequest);
        final BigDecimal monthlyIncome = creditAnalysisRequest.monthlyIncome();
        final BigDecimal requestedAmount = creditAnalysisRequest.requestedAmount();
        final BigDecimal consideredIncome = monthlyIncome.compareTo(BigDecimal.valueOf(LIMIT_INCOME)) > 0
        ? BigDecimal.valueOf(LIMIT_INCOME)
        : monthlyIncome;

        final CreditAnalysis creditAnalysed;
        final boolean approved;
        final BigDecimal approvedlimit;
        if (requestedAmount.compareTo(consideredIncome) > 0) {
            approved = false;
            creditAnalysed = pendentAnalysis
            .returnAnalysisApprovedFalse(approved);
        } else {
            approved = true;
            // aprova 15% da renda, se não, aprova 30% dela.
            if (requestedAmount.compareTo(consideredIncome.multiply(BigDecimal.valueOf(0.5))) > 0) {
                approvedlimit = consideredIncome.multiply(LIMIT_PERCENTAGE_IF_REQUEST_IS_GREATER_THAN_FIFTH_PERCENT);
            } else {
                approvedlimit = consideredIncome.multiply(LIMIT_PERCENTAGE_IF_REQUEST_IS_LESS_THAN_FIFTH_PERCENT);
            }
            final BigDecimal withdraw = approvedlimit.multiply(BigDecimal.valueOf(0.1));
            creditAnalysed = pendentAnalysis
            .returnAnalysisApprovedTrue(approved, approvedlimit, withdraw, ANNUAL_INTEREST);
        }
        return creditAnalysed;
    }

    public CreditAnalysisResponse makeAnalysisRequest(CreditAnalysisRequest creditAnalysisRequest) {
        final CreditAnalysis analysisWithClientIdUpdated = checkIfIsApproved(creditAnalysisRequest);
        final CreditAnalysisEntity creditAnalysisEntity = creditEntityMapper.from(analysisWithClientIdUpdated);
        final CreditAnalysisEntity analysisSavedInDb = saveAnalysisRequest(creditAnalysisEntity);
        final CreditAnalysisResponse entityToResponse = creditResponseMapper.from(analysisSavedInDb);
        return entityToResponse;
    }

    private CreditAnalysisEntity saveAnalysisRequest(CreditAnalysisEntity creditAnalysisEntity) {
        final CreditAnalysisEntity analysisSavedInDb;
        analysisSavedInDb = creditAnalysisRepository.save(creditAnalysisEntity);
        return analysisSavedInDb;
    }

    public Client clientFoundInClientApi(UUID id) {
        final Client client = clientApi.getClientById(id);
        if (client == null) {
            throw new ClientNotFoundException("Could not find Client by Id %s".formatted(id));
        }
        return client;
    }
}
