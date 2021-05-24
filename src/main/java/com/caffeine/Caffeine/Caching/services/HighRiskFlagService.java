package com.caffeine.Caffeine.Caching.services;

import com.caffeine.Caffeine.Caching.exception.NEOSException;
import com.caffeine.Caffeine.Caching.models.HighRiskFlag;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface HighRiskFlagService {

    CompletableFuture<String> updateHighRiskFlag(HighRiskFlag highRiskFlag) throws NEOSException;

    void createHighRiskFlag(HighRiskFlag highRiskFlag) throws NEOSException;

    HighRiskFlag getHighRiskFlagById(Long id);

    List<HighRiskFlag> getAllHighRiskFlags();
}
