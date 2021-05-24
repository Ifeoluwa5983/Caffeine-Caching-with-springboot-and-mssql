package com.caffeine.Caffeine.Caching.services;

import com.caffeine.Caffeine.Caching.commands.UpdateSignatoriesCommand;
import com.caffeine.Caffeine.Caching.configurations.CaffeineCacheConfig;
import com.caffeine.Caffeine.Caching.exception.NEOSException;
import com.caffeine.Caffeine.Caching.models.CustomerSignatory;
import com.caffeine.Caffeine.Caching.models.Customers;
import com.caffeine.Caffeine.Caching.models.KYCDetail;
import com.caffeine.Caffeine.Caching.repositories.CustomerRepository;
import com.caffeine.Caffeine.Caching.repositories.CustomerSignatoryRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@CacheConfig(cacheNames = {"signatory"})
public class CustomerSignatoryServiceImpl implements CustomerSignatoryService{

    private final CommandGateway commandGateway;

    private final CustomerSignatoryRepository customerSignatoryRepository;

    @Autowired
    CaffeineCacheConfig cache;

    @Autowired
    CustomerRepository customerRepository;

    public CustomerSignatoryServiceImpl(CommandGateway commandGateway, CustomerSignatoryRepository customerSignatoryRepository) {
        this.commandGateway = commandGateway;
        this.customerSignatoryRepository = customerSignatoryRepository;
    }

    @Override
    public CompletableFuture<String> updateCustomerSignatory(CustomerSignatory signatory) throws NEOSException {
        if (signatory.getId() == null) {
            throw new NEOSException("Signatory does not exist");
        } else {
            customerSignatoryRepository.save(signatory);
            return commandGateway.send(new CustomerSignatory(signatory.getId(), signatory.getSignatoryName(), signatory.getMothersMaidenName(),
                    signatory.getDateOfBirth(), signatory.getAddress(), signatory.getPostCode(), signatory.getCity(),
                    signatory.getState(), signatory.getCountry(), signatory.getPhoneNo(), signatory.getEmail(),
                    signatory.getBvn(), signatory.getPhoto(), signatory.getSignature(), signatory.getCustomerId()));
        }
    }

    @Transactional
    public void deleteCustomerSignatoryById(Long id) throws NEOSException {
        CustomerSignatory customerSignatory = customerSignatoryRepository.findById(id).orElse(null);
        if (customerSignatory == null){
            throw new NEOSException("The Customer Signatory with the id does not exist");
        }
        Optional<Customers> customer = customerRepository.findById(customerSignatory.getCustomerId()) ;
        List<CustomerSignatory> customerSignatories = customer.get().getSignatorys();
        for (int i = 0 ; i < customerSignatories.size(); i ++){
            if (customerSignatories.get(i).getId().equals(id)){
                customerSignatories.remove(customerSignatories.get(i));
            }
        }
        customerSignatoryRepository.deleteById(id);
    }

    @Override
    public void createSignatory(CustomerSignatory signatory) throws NEOSException {

        if (signatory.getCustomerId() == null){
            throw new NEOSException("Customer id is missing");
        }
        Customers customer = customerRepository.findById(signatory.getCustomerId()).orElse(null);
        if (customer == null){
            throw new NEOSException("Customer does not exist");
        }
        else {
            customerSignatoryRepository.save(signatory);
            customer.setSignatorys(signatory);
            customerRepository.save(customer);
        }
    }

    @Override
    @Cacheable
    public CustomerSignatory getSignatory(Long id) {
        Optional<CustomerSignatory> signatory = customerSignatoryRepository.findById(id);
        cache.cache().put(id, signatory);
        return signatory.get();
    }

    @Override
    @Cacheable
    public List<CustomerSignatory> getAllSignatories() {
        List<CustomerSignatory> customerSignatories = (List<CustomerSignatory>) cache.cache().get("CustomerSignatories", k -> customerSignatoryRepository.findAll());
        assert customerSignatories != null;
        return  customerSignatories;
    }
}
