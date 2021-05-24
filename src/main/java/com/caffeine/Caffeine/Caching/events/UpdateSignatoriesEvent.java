package com.caffeine.Caffeine.Caching.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSignatoriesEvent {

    private Long id;

    private String signatoryName;

    private String mothersMaidenName;

    private LocalDate dateOfBirth;

    private String address;

    private String postCode;

    private String city;

    private String state;

    private String country;

    private String phoneNo;

    private String email;

    private String bvn;

    private byte[] photo;

    private byte[] signature;

    private Long  customerId;
}
