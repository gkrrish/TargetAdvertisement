package com.advt.main.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.advt.entity.BatchJob;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
    
    @Column(name = "BATCHID", nullable = true)
    private Integer batchId; //this is used when we want to save the single value, instead of object
    
    @ManyToOne
    @JoinColumn(name = "BATCHID", insertable = false, updatable = false)
    private BatchJob batchJob;
}
