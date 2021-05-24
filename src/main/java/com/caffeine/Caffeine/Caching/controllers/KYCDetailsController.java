package com.caffeine.Caffeine.Caching.controllers;

import com.caffeine.Caffeine.Caching.exception.NEOSException;
import com.caffeine.Caffeine.Caching.models.KYCDetail;
import com.caffeine.Caffeine.Caching.services.KYCDetailServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/customer")
public class KYCDetailsController {

    private final KYCDetailServiceImpl kycDetailService;

    public KYCDetailsController(KYCDetailServiceImpl kycDetailService) {
        this.kycDetailService = kycDetailService;
    }

    @PostMapping("/create-KYCDetail")
    public ResponseEntity<?> createKYCDetail(@RequestBody KYCDetail kycDetail) throws NEOSException {
        kycDetailService.createKYCDetail(kycDetail);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getKYCDetailById/{id}")
    public ResponseEntity<?> getKYCDetailById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(kycDetailService.getKYCDetailById(id));
    }

    @GetMapping("/all-KYCDetails")
    public ResponseEntity<?> getAllKYCDetails() {
        return ResponseEntity.ok(kycDetailService.getAllKYCDetails());
    }

    @PutMapping(value = "/updateKYCDetails")
    public CompletableFuture<String> updateKYCDetails(@RequestBody KYCDetail kycDetail) throws NEOSException {
        return kycDetailService.updateKYCDetails(kycDetail);
    }

    @DeleteMapping("/KYCDetails/delete/{id}")
    public ResponseEntity<?> deleteKYCDetailById(@PathVariable Long id){
        try{
            kycDetailService.deleteKYCDetailById(id);
        }catch (NullPointerException | NEOSException exe){
            return ResponseEntity.badRequest().body(exe.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
