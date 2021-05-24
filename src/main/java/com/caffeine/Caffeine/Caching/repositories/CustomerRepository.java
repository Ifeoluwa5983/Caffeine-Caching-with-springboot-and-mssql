package com.caffeine.Caffeine.Caching.repositories;

import com.caffeine.Caffeine.Caching.models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long > {


}
