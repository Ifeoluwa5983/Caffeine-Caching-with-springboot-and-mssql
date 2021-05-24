package com.caffeine.Caffeine.Caching.models;

import com.caffeine.Caffeine.Caching.commands.UpdateDirectorsCommand;
import com.caffeine.Caffeine.Caching.commands.UpdateHighRiskFlagCommand;
import com.caffeine.Caffeine.Caching.commands.UpdateKYCDetailsCommand;
import com.caffeine.Caffeine.Caching.commands.UpdateSignatoriesCommand;
import com.caffeine.Caffeine.Caching.events.UpdateDirectorsEvent;
import com.caffeine.Caffeine.Caching.events.UpdateHighRiskFlagEvent;
import com.caffeine.Caffeine.Caching.events.UpdateKYCDetailEvent;
import com.caffeine.Caffeine.Caching.events.UpdateSignatoriesEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Aggregate
@Table(name = "Customers")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @AggregateIdentifier
    @Column(name = "customer_id")
    private Long id;

    private String customerNo;

    private String salutation;

    private String name;

    private String firstName;

    private String lastName;

    private String otherName;

    private String shortName;

    private String gender;

    private Date dob;

    private String nationality;

    private String maritalStatus;

    private String bvn;

    private String address1Line1;

    private String address1Line2;

    private String address1Line3;

    private String address1Line4;

    private String address;

    private String postCode;

    private String city;

    private String lgaOfResidence;

    private String state;

    private String countryOfResidence;

    private String countryOfRisk;

    private String townOfOrigin;

    private String lgaOfOrigin;

    private String stateOfOrigin;

    private boolean resident;

    private String address2Line1;

    private String address2Line2;

    private String address2Line3;

    private String address2Line4;

    private String address2;

    private String language;

    private String religion;

    private String residentType;

    private Date dateStartStayinAddress;

    private Integer lengthofStayinAddress;

    private String nokTitle;

    private String nokFirstName;

    private String nokLastName;

    private String nokOtherName;

    private String nokRelationship;

    private String nokPhoneNo;

    private String nokEmailAddress;

    private String nokAddress;

    private String nokZipCode;

    private String nokCity;

    private String nokState;

    private String employmentStatus;

    private String natureofEmployment;

    private String employerName;

    private String highestLevelOfEducation;

    private String occupationCode;

    private String occupationDesc;

    private String employerAddress;

    private String employerZipCode;

    private String employerCity;

    private String employerState;

    private String officeEmail;

    private String officePhoneNo;

    private Date employmentStartDate;

    private Frequency freqIncome;

    private Double grossAnnualIncome;

    private String customerType;

    private boolean isStaff;

    private String staffId;

    private boolean isMember;

    private String memberShipNo;

    private boolean isMinor;

    private String guardian;

    private boolean guardianNo;

    private String guardianRelationship;

    private String defaultAccountNo;

    private String RCNo;

    private String phoneRef;

    private String email;

    private String education;

    private String profession;

    private String industry;

    private String industrySeg;

    private String region;

    private String mothersMaidenName;

    private String relationShipDept;

    private String relationshipMan;

    private String altRelationshipMan;

    private String status;

    private String notes;

    private String natureOfBuss;

    private String typeOfBuss;

    private BigDecimal paidUpShareCap;

    private String shareHoldstruc;

    private int staffStrength;

    private String bankers;

    private float marketShare;

    private String fiscalPeriod;

    private int noOfBranch;

    private int industryRank;

    private String TIN;

    private String IncomeTaxNo;

    private String VATRegNo;

    private boolean isAgent;

    private String agentType;

    private Boolean isGroupLeader;

    private Boolean isGroupMember;

    private String groupLeaderNo;

    private String infoShareConsentLevel;

    private boolean isRestricted;

    private String accessLevel;

    private String blockStatus;

    private boolean isBlocked4Transactions;

    private String blockReason;

    private boolean isHighRisk;

    private boolean isPEP;

    private String PEPRemarks;

    private String accountPurpose;

    private String verificationStatus;

    private String verificationRemarks;

    private String verificationDate;

    private boolean isVerified;

    private String branch;

    @OneToMany
    private List<KYCDetail> kycdocs;

    @OneToMany
    private List<CustomerSignatory> signatorys;

    @OneToMany
    private List<CompanyDirector> directors;

    @OneToMany
    private List<HighRiskFlag> highRiskFlags;

    public void setKycdocs(KYCDetail kycDetail){
            if(kycdocs == null){
                kycdocs = new ArrayList<>();
            }
            kycdocs.add(kycDetail);
    }

    public void setSignatorys(CustomerSignatory signatory){
        if(signatorys == null){
            signatorys= new ArrayList<>();
        }
        signatorys.add(signatory);
    }

    public void setDirectors(CompanyDirector director){
        if(directors == null){
            directors= new ArrayList<>();
        }
        directors.add(director);
    }

    public void setHighRiskFlags(HighRiskFlag highRiskFlag){
        if(highRiskFlags == null){
            highRiskFlags= new ArrayList<>();
        }
        highRiskFlags.add(highRiskFlag);
    }

    @CommandHandler
    public Customers(KYCDetail updateKYCDetailsCommand){
        AggregateLifecycle.apply(new UpdateKYCDetailEvent(updateKYCDetailsCommand.getId(),updateKYCDetailsCommand.getDocumentType(), updateKYCDetailsCommand.getDocumentReference(),
                updateKYCDetailsCommand.getDocumentComments(), updateKYCDetailsCommand.getDocumentIssueDate(), updateKYCDetailsCommand.getDocumentExpiryDate(), updateKYCDetailsCommand.isDocumentValid(),
                updateKYCDetailsCommand.isDocumentConfirmed(), updateKYCDetailsCommand.getDocumentConfirmedBy(), updateKYCDetailsCommand.getDocumentConfirmedDate(), updateKYCDetailsCommand.getDocumentImage(),
                updateKYCDetailsCommand.getCustomerId()));
    }

    @EventSourcingHandler
    public void on(UpdateKYCDetailEvent updateKYCDetailEvent) {
        KYCDetail kycDetail = new KYCDetail();
        kycDetail.setId(updateKYCDetailEvent.getId());
        kycDetail.setDocumentType(updateKYCDetailEvent.getDocumentType());
        kycDetail.setDocumentReference(updateKYCDetailEvent.getDocumentReference());
        kycDetail.setDocumentComments(updateKYCDetailEvent.getDocumentComments());
        kycDetail.setDocumentIssueDate(updateKYCDetailEvent.getDocumentIssueDate());
        kycDetail.setDocumentValid(updateKYCDetailEvent.isDocumentValid());
        kycDetail.setDocumentExpiryDate(updateKYCDetailEvent.getDocumentExpiryDate());
        kycDetail.setDocumentConfirmed(updateKYCDetailEvent.isDocumentConfirmed());
        kycDetail.setDocumentConfirmedBy(updateKYCDetailEvent.getDocumentConfirmedBy());
        kycDetail.setDocumentConfirmedDate(updateKYCDetailEvent.getDocumentConfirmedDate());
        kycDetail.setDocumentImage(updateKYCDetailEvent.getDocumentImage());
        kycDetail.setCustomerId(updateKYCDetailEvent.getCustomerId());

        setKycdocs(kycDetail);
        this.id = updateKYCDetailEvent.getCustomerId();
}

    @CommandHandler
    public void handle(CompanyDirector updateDirectorsCommand){
        AggregateLifecycle.apply(new UpdateDirectorsEvent(updateDirectorsCommand.getId(),updateDirectorsCommand.getDirectorName(), updateDirectorsCommand.getMothersMaidenName(),
                updateDirectorsCommand.getDateOfBirth(), updateDirectorsCommand.getAddress(), updateDirectorsCommand.getPostCode(), updateDirectorsCommand.getCity(),
                updateDirectorsCommand.getState(), updateDirectorsCommand.getCountry(), updateDirectorsCommand.getPhoneNo(), updateDirectorsCommand.getEmail(),
                updateDirectorsCommand.getBvn(), updateDirectorsCommand.getPhoto(), updateDirectorsCommand.getCustomerId()));
    }

    @EventSourcingHandler
    public void on(UpdateDirectorsEvent updateDirectorsEvent) {
        CompanyDirector companyDirector = new CompanyDirector();
        companyDirector.setId(updateDirectorsEvent.getId());
        companyDirector.setDirectorName(updateDirectorsEvent.getDirectorName());
        companyDirector.setMothersMaidenName(updateDirectorsEvent.getMothersMaidenName());
        companyDirector.setCountry(updateDirectorsEvent.getCountry());
        companyDirector.setDateOfBirth(updateDirectorsEvent.getDateOfBirth());
        companyDirector.setAddress(updateDirectorsEvent.getAddress());
        companyDirector.setPostCode(updateDirectorsEvent.getPostCode());
        companyDirector.setCity(updateDirectorsEvent.getCity());
        companyDirector.setBvn(updateDirectorsEvent.getBvn());
        companyDirector.setState(updateDirectorsEvent.getState());
        companyDirector.setPhoneNo(updateDirectorsEvent.getPhoneNo());
        companyDirector.setEmail(updateDirectorsEvent.getEmail());
        companyDirector.setPhoto(updateDirectorsEvent.getPhoto());
        companyDirector.setCustomerId(updateDirectorsEvent.getCustomerId());

        setDirectors(companyDirector);
        this.id = updateDirectorsEvent.getCustomerId();
    }

    @CommandHandler
    public void  handle(CustomerSignatory updateSignatoriesCommand){
        AggregateLifecycle.apply(new UpdateSignatoriesEvent(updateSignatoriesCommand.getId(),updateSignatoriesCommand.getSignatoryName(), updateSignatoriesCommand.getMothersMaidenName(),
                updateSignatoriesCommand.getDateOfBirth(), updateSignatoriesCommand.getAddress(), updateSignatoriesCommand.getPostCode(), updateSignatoriesCommand.getCity(),
                updateSignatoriesCommand.getState(), updateSignatoriesCommand.getCountry(), updateSignatoriesCommand.getPhoneNo(), updateSignatoriesCommand.getEmail(),
                updateSignatoriesCommand.getBvn(), updateSignatoriesCommand.getPhoto(), updateSignatoriesCommand.getSignature(), updateSignatoriesCommand.getCustomerId()));
    }

    @EventSourcingHandler
    public void on(UpdateSignatoriesEvent updateSignatoriesEvent) {
        CustomerSignatory customerSignatory = new CustomerSignatory();
        customerSignatory.setId(updateSignatoriesEvent.getId());
        customerSignatory.setSignatoryName(updateSignatoriesEvent.getSignatoryName());
        customerSignatory.setDateOfBirth(updateSignatoriesEvent.getDateOfBirth());
        customerSignatory.setMothersMaidenName(updateSignatoriesEvent.getMothersMaidenName());
        customerSignatory.setPostCode(updateSignatoriesEvent.getPostCode());
        customerSignatory.setAddress(updateSignatoriesEvent.getAddress());
        customerSignatory.setState(updateSignatoriesEvent.getState());
        customerSignatory.setCity(updateSignatoriesEvent.getCity());
        customerSignatory.setCountry(updateSignatoriesEvent.getCountry());
        customerSignatory.setPhoneNo(updateSignatoriesEvent.getPhoneNo());
        customerSignatory.setEmail(updateSignatoriesEvent.getEmail());
        customerSignatory.setBvn(updateSignatoriesEvent.getBvn());
        customerSignatory.setPhoto(updateSignatoriesEvent.getPhoto());
        customerSignatory.setSignature(updateSignatoriesEvent.getSignature());
        customerSignatory.setCustomerId(updateSignatoriesEvent.getCustomerId());

        setSignatorys(customerSignatory);
        this.id = updateSignatoriesEvent.getCustomerId();
    }

    @CommandHandler
    public void handle(HighRiskFlag updateHighRiskFlagCommand){
        AggregateLifecycle.apply(new UpdateHighRiskFlagEvent(updateHighRiskFlagCommand.getId(),updateHighRiskFlagCommand.getHighRiskRule(), updateHighRiskFlagCommand.getHighRiskFlagMode(),
                updateHighRiskFlagCommand.getHighRiskFlagReason(), updateHighRiskFlagCommand.getHighRiskFlagComments(), updateHighRiskFlagCommand.getHighRiskFlagDate(),
                updateHighRiskFlagCommand.getHighRiskFlaggedBy(), updateHighRiskFlagCommand.isHighRiskConfirmed(), updateHighRiskFlagCommand.getHighRiskConfirmationDate(),
                updateHighRiskFlagCommand.getHighRiskConfirmedBy(), updateHighRiskFlagCommand.getCustomerId()));
    }

    @EventSourcingHandler
    public void on(UpdateHighRiskFlagEvent updateHighRiskFlagEvent) {
        HighRiskFlag highRiskFlag = new HighRiskFlag();
        highRiskFlag.setId(updateHighRiskFlagEvent.getId());
        highRiskFlag.setHighRiskRule(updateHighRiskFlagEvent.getHighRiskRule());
        highRiskFlag.setHighRiskFlagMode(updateHighRiskFlagEvent.getHighRiskFlagMode());
        highRiskFlag.setHighRiskFlagReason(updateHighRiskFlagEvent.getHighRiskFlagReason());
        highRiskFlag.setHighRiskFlaggedBy(updateHighRiskFlagEvent.getHighRiskFlaggedBy());
        highRiskFlag.setHighRiskFlagDate(updateHighRiskFlagEvent.getHighRiskFlagDate());
        highRiskFlag.setHighRiskFlagComments(updateHighRiskFlagEvent.getHighRiskFlagComments());
        highRiskFlag.setHighRiskConfirmedBy(updateHighRiskFlagEvent.getHighRiskConfirmedBy());
        highRiskFlag.setHighRiskConfirmed(updateHighRiskFlagEvent.isHighRiskConfirmed());
        highRiskFlag.setHighRiskConfirmationDate(updateHighRiskFlagEvent.getHighRiskConfirmationDate());
        highRiskFlag.setCustomerId(updateHighRiskFlagEvent.getCustomerId());

        setHighRiskFlags(highRiskFlag);
        this.id = updateHighRiskFlagEvent.getCustomerId();

    }
}
