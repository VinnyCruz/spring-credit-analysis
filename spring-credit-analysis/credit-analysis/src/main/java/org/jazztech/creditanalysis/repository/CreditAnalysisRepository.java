package org.jazztech.creditanalysis.repository;

import org.jazztech.creditanalysis.repository.entity.CreditAnalysisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditAnalysisRepository extends JpaRepository<CreditAnalysisEntity, UUID> {
}
