package com.caffeine.Caffeine.Caching;


import java.util.Optional;

public interface CustomerService {
    Optional<Customers> getCustomer(final String customerID);

    void createCustomer(CustomerDto customers);
}

