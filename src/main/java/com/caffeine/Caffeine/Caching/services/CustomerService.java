package com.caffeine.Caffeine.Caching.services;

import com.caffeine.Caffeine.Caching.models.Customers;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Optional<Customers> getCustomer(final Long customerID);

    void createCustomer(Customers customers);

    public List<Object> listEventsForAccount(String customerId);
}

