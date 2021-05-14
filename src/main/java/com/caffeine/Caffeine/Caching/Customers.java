package com.caffeine.Caffeine.Caching;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Customers")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private String id;

    private String Name;

    private String ShortName;

//    private String Address1Line1;
//
//    private String Address1Line2;
//
//    private String Address1Line3;
//
//    private String Address1Line4;
//
//    private String PostalCode;
//
//    @Column(name = "Resident?")
//    private int resident;
//
//    private String Address2Line1;
//
//    private String Address2Line2;
//
//    private String Address2Line3;
//
//    private String Address2Line4;
//
//    private String City;
//
//    private String State;
//
//    private String Country;
//
//    private String Language;

    private LocalDate DateOfBirth;

    private String Sex;

//    private String PrimaryAccountNo;
//
//    private String RCNo;

    private String PhoneRef;

    private String EMailAddress;

//    private String Industry;
//
//    private String IndustrySegment;
//
//    private String Region;
//
//    private String RelationshipDepartment;
//
//    private String RelationshipManager;
//
//    private String AltRelationshipManager;
//
//    private String Status;
//
//    private String Notes;
//
//    private String NatureOfBusiness;
//
//    private String TypeOfBusiness;
//
//    private Double PaidUpShareCapital;
//
//    private String ShareholdingStructure;
//
//    private int StaffStrength;
//
//    private String Bankers;
//
//    private float MarketShare;
//
//    private String FiscalPeriod;
//
//    private int NoofBranches;
//
//    private int IndustryRank;
//
//    private String Directors;
//
//    private String Branch;

}
