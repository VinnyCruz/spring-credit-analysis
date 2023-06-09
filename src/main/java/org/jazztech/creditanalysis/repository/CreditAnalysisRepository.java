package org.jazztech.creditanalysis.repository;

import java.util.List;
import java.util.UUID;
import org.jazztech.creditanalysis.repository.entity.CreditAnalysisEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CreditAnalysisRepository extends JpaRepository<CreditAnalysisEntity, UUID> {
    List<CreditAnalysisEntity> findByClientId(UUID clientId);
}
