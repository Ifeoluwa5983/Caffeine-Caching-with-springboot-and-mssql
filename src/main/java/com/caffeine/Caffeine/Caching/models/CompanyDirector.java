package com.caffeine.Caffeine.Caching.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
public class CompanyDirector {

    @Id
    private Long id;

    private String directorName;

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

    private Long  customerId;

    public CompanyDirector(){}

}
