package com.caffeine.Caffeine.Caching.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;


@Data
@Entity
@AllArgsConstructor
public class KYCDetail {

    @Id
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

    private Long customerId;

    public KYCDetail() {
    }
}


