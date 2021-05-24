package com.caffeine.Caffeine.Caching.services;

import com.caffeine.Caffeine.Caching.commands.UpdateHighRiskFlagCommand;
import com.caffeine.Caffeine.Caching.configurations.CaffeineCacheConfig;
import com.caffeine.Caffeine.Caching.exception.NEOSException;
import com.caffeine.Caffeine.Caching.models.Customers;
import com.caffeine.Caffeine.Caching.models.HighRiskFlag;
import com.caffeine.Caffeine.Caching.repositories.CustomerRepository;
import com.caffeine.Caffeine.Caching.repositories.HighRiskFlagRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@CacheConfig(cacheNames = {"highRiskFlags"})
public class HighRiskFlagServiceImpl implements HighRiskFlagService{

    private final CommandGateway commandGateway;

    private final HighRiskFlagRepository highRiskFlagRepository;

    @Autowired
    CaffeineCacheConfig cache;

    @Autowired
    CustomerRepository customerRepository;

    public HighRiskFlagServiceImpl(CommandGateway commandGateway, HighRiskFlagRepository highRiskFlagRepository) {
        this.commandGateway = commandGateway;
        this.highRiskFlagRepository = highRiskFlagRepository;
    }

    @Override
    public CompletableFuture<String> updateHighRiskFlag(HighRiskFlag highRiskFlag) throws NEOSException {
        if (highRiskFlag.getId() == null){
            throw new NEOSException("High risk flag does not exist");
        }else {
            highRiskFlagRepository.save(highRiskFlag);
            return commandGateway.send(new HighRiskFlag(highRiskFlag.getId(),highRiskFlag.getHighRiskRule(), highRiskFlag.getHighRiskFlagMode(),
                    highRiskFlag.getHighRiskFlagReason(), highRiskFlag.getHighRiskFlagComments(), highRiskFlag.getHighRiskFlagDate(),
                    highRiskFlag.getHighRiskFlaggedBy(), highRiskFlag.isHighRiskConfirmed(), highRiskFlag.getHighRiskConfirmationDate(),
                    highRiskFlag.getHighRiskConfirmedBy(), highRiskFlag.getCustomerId()));
        }
    }

    @Override
    public void createHighRiskFlag(HighRiskFlag highRiskFlag) throws NEOSException {
        if (highRiskFlag.getCustomerId() == null){
            throw new NEOSException("Customer id is missing");
        }
        Customers customer = customerRepository.findById(highRiskFlag.getCustomerId()).orElse(null);
        if (customer == null){
            throw new NEOSException("Customer does not exist");
        }
        else {
            highRiskFlagRepository.save(highRiskFlag);
            customer.setHighRiskFlags(highRiskFlag);
            customerRepository.save(customer);
        }
    }

    @Transactional
    public void deleteHighRiskFlagById(Long id) throws NEOSException {
        HighRiskFlag highRiskFlag = highRiskFlagRepository.findById(id).orElse(null);
        if (highRiskFlag == null){
            throw new NEOSException("The risk with the id does not exist");
        }
        Optional<Customers> customer = customerRepository.findById(highRiskFlag.getCustomerId()) ;
        List<HighRiskFlag> highRiskFlags = customer.get().getHighRiskFlags();
        for (int i = 0 ; i < highRiskFlags.size(); i ++){
            if (highRiskFlags.get(i).getId().equals(id)){
                highRiskFlags.remove(highRiskFlags.get(i));
            }
        }
        highRiskFlagRepository.deleteById(id);
    }

    @Override
    public HighRiskFlag getHighRiskFlagById(Long id) {
        Optional<HighRiskFlag> highRiskFlag = highRiskFlagRepository.findById(id);
        cache.cache().put(id, highRiskFlag);
        return highRiskFlag.get();
    }

    @Override
    public List<HighRiskFlag> getAllHighRiskFlags() {
        List<HighRiskFlag> highRiskFlags = (List<HighRiskFlag>) cache.cache().get("HighRiskFlags", k -> highRiskFlagRepository.findAll());
        assert highRiskFlags != null;
        return  highRiskFlags;
    }
}
