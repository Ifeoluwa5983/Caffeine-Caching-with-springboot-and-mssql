package com.caffeine.Caffeine.Caching.commands;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateKYCDetailsCommand {
    private Long id;
    private String documentType;
    private String documentReference;
    private String documentComments;
    private LocalDate documentIssueDate;
    private LocalDate documentExpiryDate;
    private boolean isDocumentValid;
    private boolean isDocumentConfirmed;
    private String documentConfirmedBy;
    private LocalDate documentConfirmedDate;
    private byte documentImage;
    private Long  customerId;
}
