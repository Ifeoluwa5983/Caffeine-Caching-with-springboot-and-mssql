package com.caffeine.Caffeine.Caching.controllers;

import com.caffeine.Caffeine.Caching.exception.NEOSException;
import com.caffeine.Caffeine.Caching.models.CustomerSignatory;
import com.caffeine.Caffeine.Caching.services.CustomerSignatoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/customer")
public class CustomerSignatoryController {

    private final CustomerSignatoryServiceImpl customerSignatoryService;

    public CustomerSignatoryController(CustomerSignatoryServiceImpl customerSignatoryService) {
        this.customerSignatoryService = customerSignatoryService;
    }

    @PostMapping("/create-customer-signatory")
    public ResponseEntity<?> createCustomerSignatory(@RequestBody CustomerSignatory customerSignatory) throws NEOSException {
        customerSignatoryService.createSignatory(customerSignatory);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/customer-signatory/{id}")
    public ResponseEntity<?> getCustomerSignatoryById(@PathVariable("id") Long customerId) {
        return ResponseEntity.ok(customerSignatoryService.getSignatory(customerId));
    }

    @GetMapping("/all-signatories")
    public ResponseEntity<?> getAllSignatories() {
        return ResponseEntity.ok(customerSignatoryService.getAllSignatories());
    }

    @PutMapping(value = "/updateCustomerSignatory")
    public CompletableFuture<String> updateCustomerSignatory(@RequestBody CustomerSignatory customerSignatory) throws NEOSException {
        return customerSignatoryService.updateCustomerSignatory(customerSignatory);
    }

    @DeleteMapping("/signatories/delete/{id}")
    public ResponseEntity<?> deleteSignatoriesById(@PathVariable Long id){
        try{
            customerSignatoryService.deleteCustomerSignatoryById(id);
        }catch (NullPointerException | NEOSException exe){
            return ResponseEntity.badRequest().body(exe.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
