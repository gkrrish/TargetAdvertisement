package com.advt.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TARGETED_ADVERTISEMENTS")
@Data
public class TargetedAdvertisements {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TARGETED_ADVTS_SEQ")
    @SequenceGenerator(name = "TARGETED_ADVTS_SEQ", sequenceName = "TARGETED_ADVTS_SEQ", allocationSize = 1)
    @Column(name = "TARGETADVTID")
    private Integer targetAdvtId;
    
    @Column(name = "advertisement_id", nullable = false)
    private Integer advertisementId;
    
    @Column(name = "advertiser_id", nullable = false)
    private Integer advertiserId;

    @Column(name = "FILELOCATION", nullable = false)
    private String fileLocation;

    @Column(name = "CREATEDDATE", nullable = false)
    private Timestamp createdDate;

    @Column(name = "paid", nullable = true)
    private String paid;

    @Column(name = "paid_amount", nullable = true)
    private BigDecimal paidAmount;

    @Column(name = "VERIFIEDBYADMIN", nullable = true)
    private String verifiedByAdmin;

    @Column(name = "ADMINNAME", nullable = true)
    private String adminName;

    @Column(name = "VERIFIEDTIMESTAMP", nullable = true)
    private Timestamp verifiedTimestamp;

    @Column(name = "VERIFICATIONREMARKS", nullable = true)
    private String verificationRemarks;

    @Column(name = "verified_allow_to_publish", nullable = true)
    private String verifiedAllowToPublish;
    
    @ManyToOne
    @JoinColumn(name = "advertisement_id", insertable = false, updatable = false)
    private TargetedAdvertisementPlan targetedAdvertisementPlan;

    @ManyToOne
    @JoinColumn(name = "advertiser_id", insertable = false, updatable = false)
    private AdvertiserDetails advertiserDetails;
}
