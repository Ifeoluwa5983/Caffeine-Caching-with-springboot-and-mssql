package com.caffeine.Caffeine.Caching.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
public class HighRiskFlag {

    @Id
    private Long id;

    private String highRiskRule;

    private String highRiskFlagMode;

    private String highRiskFlagReason;

    private String highRiskFlagComments;

    private LocalDate highRiskFlagDate;

    private String highRiskFlaggedBy;

    private boolean highRiskConfirmed;

    private LocalDate highRiskConfirmationDate;

    private String highRiskConfirmedBy;

    private Long  customerId;

    public HighRiskFlag(){}

}
