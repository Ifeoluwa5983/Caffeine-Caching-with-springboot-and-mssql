package com.caffeine.Caffeine.Caching;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CustomerController
{
    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService){
        this.customerService = customerService;
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getAddress(@PathVariable("id") String customerId) {
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

    @PostMapping("/register-customer")
    public ResponseEntity<?> registerCustomer(@RequestBody CustomerDto customers){
        customerService.createCustomer(customers);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
