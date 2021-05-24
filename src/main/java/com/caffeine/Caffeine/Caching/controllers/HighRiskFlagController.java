package com.caffeine.Caffeine.Caching.controllers;

import com.caffeine.Caffeine.Caching.exception.NEOSException;
import com.caffeine.Caffeine.Caching.models.HighRiskFlag;
import com.caffeine.Caffeine.Caching.services.HighRiskFlagServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/customer")
public class HighRiskFlagController {

    private final HighRiskFlagServiceImpl highRiskFlagService;

    public HighRiskFlagController(HighRiskFlagServiceImpl highRiskFlagService) {
        this.highRiskFlagService = highRiskFlagService;
    }

    @PostMapping("/create-high-risk-flag")
    public ResponseEntity<?> createHighRiskFlag(@RequestBody HighRiskFlag highRiskFlag) throws NEOSException {
        highRiskFlagService.createHighRiskFlag(highRiskFlag);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/high-risk-flag/{id}")
    public ResponseEntity<?> getHighRiskFlagById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(highRiskFlagService.getHighRiskFlagById(id));
    }

    @GetMapping("/getAllHighRiskFlags")
    public ResponseEntity<?> getAllHighRiskFlags() {
        return ResponseEntity.ok(highRiskFlagService.getAllHighRiskFlags());
    }

    @PutMapping(value = "/updateHighRiskFlag")
    public CompletableFuture<String> updateHighRiskFlag(@RequestBody HighRiskFlag highRiskFlag) throws NEOSException {
        return highRiskFlagService.updateHighRiskFlag(highRiskFlag);
    }

    @DeleteMapping("/highFlagRisk/delete/{id}")
    public ResponseEntity<?> deleteCompanyDirectorById(@PathVariable Long id){
        try{
            highRiskFlagService.deleteHighRiskFlagById(id);
        }catch (NullPointerException | NEOSException exe){
            return ResponseEntity.badRequest().body(exe.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
