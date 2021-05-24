package com.caffeine.Caffeine.Caching.repositories;

import com.caffeine.Caffeine.Caching.models.HighRiskFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HighRiskFlagRepository extends JpaRepository<HighRiskFlag, Long> {
}
