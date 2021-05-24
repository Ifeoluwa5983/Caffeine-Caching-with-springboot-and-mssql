package com.caffeine.Caffeine.Caching.configurations;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;


@Configuration
public class CaffeineCacheConfig {

    @Bean
    public Caffeine<Object, Object> caffeineConfig() {

        return Caffeine.newBuilder()
               .maximumSize(500)
               .initialCapacity(100)
               .expireAfterWrite(1, TimeUnit.DAYS)
               .recordStats();

    }

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("customers", "kycdetails", "signatory", "directors", "highRiskFlags");
        cacheManager.setCaffeine(caffeineConfig());
        return cacheManager;
    }

    public Cache<Object, Object> cache(){
          return Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(500)
                .build();
    }
}
