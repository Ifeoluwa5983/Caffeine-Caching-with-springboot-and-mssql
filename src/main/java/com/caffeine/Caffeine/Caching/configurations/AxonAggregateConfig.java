package com.caffeine.Caffeine.Caching.configurations;

import com.mongodb.MongoClient;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.mongo.DefaultMongoTemplate;
import org.axonframework.mongo.MongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration
public class AxonAggregateConfig {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public EventStorageEngine eventStore(MongoClient client) {
        return new MongoEventStorageEngine(new DefaultMongoTemplate(client));
    }
}
