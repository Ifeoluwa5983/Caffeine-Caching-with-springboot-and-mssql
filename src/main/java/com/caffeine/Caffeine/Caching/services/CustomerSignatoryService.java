package com.caffeine.Caffeine.Caching.services;

import com.caffeine.Caffeine.Caching.exception.NEOSException;
import com.caffeine.Caffeine.Caching.models.CustomerSignatory;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CustomerSignatoryService {

    CompletableFuture<String> updateCustomerSignatory(CustomerSignatory signatory) throws NEOSException;

    void createSignatory(CustomerSignatory signatory) throws NEOSException;

    CustomerSignatory getSignatory(Long id);

    List<CustomerSignatory> getAllSignatories();
}
