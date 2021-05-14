package com.caffeine.Caffeine.Caching;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@CacheConfig(cacheNames = {"customers"})
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CaffeineCacheConfig cache;

    @Autowired
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    CustomerRepository customerRepository;

    private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Cacheable
    @Override
    public Optional<Customers> getCustomer(String customerID) {
        LOG.info("Trying to get customer information for id {} ", customerID);
        Optional<Customers> customer = customerRepository.findById(customerID);
        cache.cache().put(customerID, customer);
        LOG.info("Customer -> {}", customer);
        return customer;
    }

    @Cacheable
    public List<CustomerDto> getCustomers(){
        LOG.info("About to");
        String sql = "SELECT * FROM Customers";
        LOG.info("Starting");
        List<Customers> customers = (List<Customers>) cache.cache().get("Customers", k -> jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Customers.class)));
        LOG.info("Started");
        List<CustomerDto> customerDtos = new ArrayList<>();
        assert customers != null;
        for (Customers customers1 : customers){
            customerDtos.add(modelMapper.map(customers1, CustomerDto.class));
        }
        return  customerDtos;
    }


    @Cacheable
    public List<Customers> search(String searchAlphabet){
        String sql = "SELECT ShortName,Name,EMailAddress,PhoneRef,DateofBirth,Sex,customer_id FROM Customers";

        List<Customers> customers = (List<Customers>) cache.cache().get("Customers", k -> jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Customers.class)));
        LOG.info("Started");
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
    public void createCustomer(CustomerDto customers) {
        LOG.info("Saving the Customer Data {} ", customers);
        Customers customers1 = modelMapper.map(customers, Customers.class);
        customerRepository.save(customers1);
    }

}
