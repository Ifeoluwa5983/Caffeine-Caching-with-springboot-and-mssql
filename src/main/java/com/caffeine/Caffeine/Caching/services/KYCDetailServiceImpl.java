package com.caffeine.Caffeine.Caching.services;

import com.caffeine.Caffeine.Caching.commands.UpdateKYCDetailsCommand;
import com.caffeine.Caffeine.Caching.configurations.CaffeineCacheConfig;
import com.caffeine.Caffeine.Caching.exception.NEOSException;
import com.caffeine.Caffeine.Caching.models.Customers;
import com.caffeine.Caffeine.Caching.models.HighRiskFlag;
import com.caffeine.Caffeine.Caching.models.KYCDetail;
import com.caffeine.Caffeine.Caching.repositories.CustomerRepository;
import com.caffeine.Caffeine.Caching.repositories.KYCDetailRepository;
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
@CacheConfig(cacheNames = {"kycdetails"})
public class KYCDetailServiceImpl implements KYCDetailService{

    private final CommandGateway commandGateway;

    private final KYCDetailRepository kycDetailRepository;

    @Autowired
    CaffeineCacheConfig cache;

    @Autowired
    CustomerRepository customerRepository;

    public KYCDetailServiceImpl(CommandGateway commandGateway, KYCDetailRepository kycDetailRepository) {
        this.commandGateway = commandGateway;
        this.kycDetailRepository = kycDetailRepository;
    }

    @Override
    public CompletableFuture<String> updateKYCDetails(KYCDetail kycDetail) throws NEOSException {
        if (kycDetail.getId() == null) {
            throw new NEOSException("KYCDetail does not exist");
        } else {
            kycDetailRepository.save(kycDetail);
            return commandGateway.send(new KYCDetail(kycDetail.getId(), kycDetail.getDocumentType(), kycDetail.getDocumentReference(),
                    kycDetail.getDocumentComments(), kycDetail.getDocumentIssueDate(), kycDetail.getDocumentExpiryDate(), kycDetail.isDocumentValid(),
                    kycDetail.isDocumentConfirmed(), kycDetail.getDocumentConfirmedBy(), kycDetail.getDocumentConfirmedDate(), kycDetail.getDocumentImage(), kycDetail.getCustomerId()));
        }
    }

    @Override
    public void createKYCDetail(KYCDetail kycDetail) throws NEOSException {
        if (kycDetail.getCustomerId() == null){
            throw new NEOSException("Customer id is missing");
        }
        Customers customer = customerRepository.findById(kycDetail.getCustomerId()).orElse(null);
        if (customer == null){
            throw new NEOSException("Customer does not exist");
        }
        else {
            customer.setKycdocs(kycDetail);
            kycDetailRepository.save(kycDetail);
            customerRepository.save(customer);
        }

    }

    @Transactional
    public void deleteKYCDetailById(Long id) throws NEOSException {
        KYCDetail kycDetail = kycDetailRepository.findById(id).orElse(null);
        if (kycDetail == null){
            throw new NEOSException("The kycdetail with the id does not exist");
        }
        Optional<Customers> customer = customerRepository.findById(kycDetail.getCustomerId()) ;
        List<KYCDetail> kycDetails = customer.get().getKycdocs();
        for (int i = 0 ; i < kycDetails.size(); i ++){
            if (kycDetails.get(i).getId().equals(id)){
                kycDetails.remove(kycDetails.get(i));
            }
        }
        kycDetailRepository.deleteById(id);
    }

    @Override
    @Cacheable
    public KYCDetail getKYCDetailById(Long id) {
        Optional<KYCDetail> kycDetail = kycDetailRepository.findById(id);
        cache.cache().put(id, kycDetail);
        return kycDetail.get();
    }

    @Override
    @Cacheable
    public List<KYCDetail> getAllKYCDetails() {
        List<KYCDetail> kycDetails = (List<KYCDetail>) cache.cache().get("Kycdetails", k -> kycDetailRepository.findAll());
        assert kycDetails != null;
        return  kycDetails;

    }
}
