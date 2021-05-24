package com.caffeine.Caffeine.Caching.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateHighRiskFlagCommand {

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
}
