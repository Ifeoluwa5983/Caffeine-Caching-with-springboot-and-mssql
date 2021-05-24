package com.caffeine.Caffeine.Caching.services;

import com.caffeine.Caffeine.Caching.exception.NEOSException;
import com.caffeine.Caffeine.Caching.models.KYCDetail;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface KYCDetailService {

    CompletableFuture<String> updateKYCDetails(KYCDetail kycDetail) throws NEOSException;

    void createKYCDetail(KYCDetail kycDetail) throws NEOSException;

    KYCDetail getKYCDetailById(Long id);

    List<KYCDetail> getAllKYCDetails();
}
