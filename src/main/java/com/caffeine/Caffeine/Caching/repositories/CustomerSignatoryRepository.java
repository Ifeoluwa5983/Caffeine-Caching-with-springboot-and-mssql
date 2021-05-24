package com.caffeine.Caffeine.Caching.repositories;

import com.caffeine.Caffeine.Caching.models.CustomerSignatory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSignatoryRepository extends JpaRepository<CustomerSignatory, Long> {
}
