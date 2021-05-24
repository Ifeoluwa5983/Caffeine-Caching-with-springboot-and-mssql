package com.caffeine.Caffeine.Caching.controllers;

import com.caffeine.Caffeine.Caching.exception.NEOSException;
import com.caffeine.Caffeine.Caching.models.CompanyDirector;
import com.caffeine.Caffeine.Caching.services.CompanyDirectorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/customer")
public class CompanyDirectorController {

    private final CompanyDirectorServiceImpl companyDirectorService;

    public CompanyDirectorController(CompanyDirectorServiceImpl companyDirectorService) {
        this.companyDirectorService = companyDirectorService;
    }

    @PostMapping("/create-customer-director")
    public ResponseEntity<?> createCompanyDirector(@RequestBody CompanyDirector director) throws NEOSException {
        companyDirectorService.createCompanyDirector(director);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/customer-director/{id}")
    public ResponseEntity<?> getCompanyDirectorById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(companyDirectorService.getCompanyDirectorById(id));
    }

    @GetMapping("/all-directors")
    public ResponseEntity<?> getAllCompanyDirectors() {
        return ResponseEntity.ok(companyDirectorService.getAllCompanyDirectors());
    }

    @PutMapping(value = "/updateCustomerDirector")
    public CompletableFuture<String> updateCompanyDirector(@RequestBody CompanyDirector companyDirector) throws NEOSException {
        return companyDirectorService.updateCompanyDirector(companyDirector);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCompanyDirectorById(@PathVariable Long id){
        try{
            companyDirectorService.deleteCompanyDirectorById(id);
        }catch (NullPointerException | NEOSException exe){
            return ResponseEntity.badRequest().body(exe.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
