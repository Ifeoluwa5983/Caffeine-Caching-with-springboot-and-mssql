package com.caffeine.Caffeine.Caching.services;

import com.caffeine.Caffeine.Caching.configurations.CaffeineCacheConfig;
import com.caffeine.Caffeine.Caching.models.Customers;
import com.caffeine.Caffeine.Caching.repositories.CustomerRepository;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = {"customers"})
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CaffeineCacheConfig cache;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    CustomerRepository customerRepository;

    private final EventStore eventStore;

    public CustomerServiceImpl(EventStore eventStore) {

        this.eventStore = eventStore;
    }

    private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Cacheable
    @Override
    public Optional<Customers> getCustomer(Long customerID) {
        LOG.info("Trying to get customer information for id {} ", customerID);
        Optional<Customers> customer = customerRepository.findById(customerID);
        cache.cache().put(customerID, customer);
        LOG.info("Customer -> {}", customer);
        return customer;
    }

    @Cacheable
    public List<Customers> getCustomers(){
        List<Customers> customers = (List<Customers>) cache.cache().get("Customers", k -> customerRepository.findAll());
        assert customers != null;
        return  customers;
    }


    @Cacheable
    public List<Customers> search(String searchAlphabet){
        List<Customers> customers = (List<Customers>) cache.cache().get("Customers", k -> customerRepository.findAll());
        List<Customers> matchedNames = new ArrayList<>();
        assert customers != null;
        for (Customers customer : customers) {
            if (customer.getName().toUpperCase().contains(searchAlphabet.toUpperCase())) {
                matchedNames.add(customer);
            }
        }
        return matchedNames;
    }

    @Override
    public void createCustomer(Customers customers) {
        LOG.info("Saving the Customer Data {} ", customers);
        customerRepository.save(customers);
    }

    @Override
    public List<Object> listEventsForAccount(String customerId) {
        DomainEventStream eventStream = eventStore.readEvents(customerId);

        if (eventStream.getLastSequenceNumber() != null) {
            return eventStore.readEvents(customerId).asStream().map( s -> s.getPayload()).collect(Collectors.toList());
        }
        return null;
    }

}
