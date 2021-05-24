package com.caffeine.Caffeine.Caching.services;

import com.caffeine.Caffeine.Caching.exception.NEOSException;
import com.caffeine.Caffeine.Caching.models.CompanyDirector;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CompanyDirectorService {

    CompletableFuture<String> updateCompanyDirector(CompanyDirector director) throws NEOSException;

    void createCompanyDirector(CompanyDirector director) throws NEOSException;

    CompanyDirector getCompanyDirectorById(Long id);

    List<CompanyDirector> getAllCompanyDirectors();
}
