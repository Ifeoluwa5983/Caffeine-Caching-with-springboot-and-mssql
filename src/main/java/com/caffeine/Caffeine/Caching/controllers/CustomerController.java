package com.caffeine.Caffeine.Caching.controllers;

import com.caffeine.Caffeine.Caching.configurations.CaffeineCacheConfig;
import com.caffeine.Caffeine.Caching.models.Customers;
import com.caffeine.Caffeine.Caching.services.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
    @Autowired
    private CaffeineCacheConfig cacheConfig;

    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService){
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddress(@PathVariable("id") Long customerId) {
        return ResponseEntity.ok(customerService.getCustomer(customerId));
    }

    @GetMapping("/search-customers/{searchAlphabet}")
    public ResponseEntity<?> searchCustomers(@PathVariable("searchAlphabet") String searchAlphabet) {
        return ResponseEntity.ok(customerService.search(searchAlphabet));
    }

    @GetMapping("/all-customers")
        public ResponseEntity<?> getAllCustomers() {
            return ResponseEntity.ok(customerService.getCustomers());
        }

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody Customers customers){
        cacheConfig.cache().invalidateAll();
        customerService.createCustomer(customers);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}/events")
    public List<Object> listEventsForAccount(@PathVariable(value = "customerId") String customerId){
        return customerService.listEventsForAccount(customerId);
    }
}
