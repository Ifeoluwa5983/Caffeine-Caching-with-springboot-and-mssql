package com.caffeine.Caffeine.Caching.repositories;

import com.caffeine.Caffeine.Caching.models.KYCDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KYCDetailRepository extends JpaRepository<KYCDetail, Long> {
}
