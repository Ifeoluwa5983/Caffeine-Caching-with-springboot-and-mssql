package com.caffeine.Caffeine.Caching.services;

import com.caffeine.Caffeine.Caching.commands.UpdateDirectorsCommand;
import com.caffeine.Caffeine.Caching.configurations.CaffeineCacheConfig;
import com.caffeine.Caffeine.Caching.exception.NEOSException;
import com.caffeine.Caffeine.Caching.models.CompanyDirector;
import com.caffeine.Caffeine.Caching.models.Customers;
import com.caffeine.Caffeine.Caching.models.KYCDetail;
import com.caffeine.Caffeine.Caching.repositories.CompanyDirectorRepository;
import com.caffeine.Caffeine.Caching.repositories.CustomerRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@CacheConfig(cacheNames = {"directors"})
public class CompanyDirectorServiceImpl implements CompanyDirectorService{

    private final CommandGateway commandGateway;

    private final CompanyDirectorRepository companyDirectorRepository;

    @Autowired
    CaffeineCacheConfig cache;

    @Autowired
    CustomerRepository customerRepository;

    public CompanyDirectorServiceImpl(CommandGateway commandGateway, CompanyDirectorRepository companyDirectorRepository) {
        this.commandGateway = commandGateway;
        this.companyDirectorRepository = companyDirectorRepository;
    }

    @Override
    public CompletableFuture<String> updateCompanyDirector(CompanyDirector director) throws NEOSException {
        if (director.getId() == null){
            throw new NEOSException("Director does not exist");
        }else {
            companyDirectorRepository.save(director);
            return commandGateway.send(new CompanyDirector(director.getId(),director.getDirectorName(), director.getMothersMaidenName(),
                    director.getDateOfBirth(), director.getAddress(), director.getPostCode(), director.getCity(),
                    director.getState(), director.getCountry(), director.getPhoneNo(), director.getEmail(),
                    director.getBvn(), director.getPhoto(), director.getCustomerId()));
        }
    }

    public void deleteCompanyDirectorById(Long id) throws NEOSException {
        CompanyDirector companyDirector = companyDirectorRepository.findById(id).orElse(null);
        if (companyDirector == null){
            throw new NEOSException("The director with the id does not exist");
        }
        Optional<Customers> customer = customerRepository.findById(companyDirector.getCustomerId()) ;
        List<CompanyDirector> companyDirectors = customer.get().getDirectors();
        for (int i = 0 ; i < companyDirectors.size(); i ++){
            if (companyDirectors.get(i).getId().equals(id)){
                companyDirectors.remove(companyDirectors.get(i));
            }
        }
        companyDirectorRepository.deleteById(id);
    }

    @Override
    public void createCompanyDirector(CompanyDirector director) throws NEOSException {

        if (director.getCustomerId() == null){
            throw new NEOSException("Customer id is missing");
        }
        Customers customer = customerRepository.findById(director.getCustomerId()).orElse(null);
        if (customer == null){
            throw new NEOSException("Customer does not exist");
        }
        else {
            companyDirectorRepository.save(director);
            customer.setDirectors(director);
            customerRepository.save(customer);
        }
    }

    @Override
    @Cacheable
    public CompanyDirector getCompanyDirectorById(Long id) {
        Optional<CompanyDirector> companyDirector = companyDirectorRepository.findById(id);
        cache.cache().put(id, companyDirector);
        return companyDirector.get();
    }

    @Override
    @Cacheable
    public List<CompanyDirector> getAllCompanyDirectors() {
        List<CompanyDirector> companyDirectors = (List<CompanyDirector>) cache.cache().get("CompanyDirectors", k -> companyDirectorRepository.findAll());
        assert companyDirectors != null;
        return  companyDirectors;
    }
}
