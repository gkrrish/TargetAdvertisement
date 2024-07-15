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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "targeted_advts_seq")
    @SequenceGenerator(name = "targeted_advts_seq", sequenceName = "targeted_advts_seq", allocationSize = 1)
    @Column(name = "TargetAdvtId")
    private Integer targetAdvtId;

    @Column(name = "FileLocation", nullable = false)
    private String fileLocation;

    @Column(name = "createdDate", nullable = false)
    private Timestamp createdDate;

    @Column(name = "paid", nullable = true)
    private String paid;

    @Column(name = "paid_amount", nullable = true)
    private BigDecimal paidAmount;

    @Column(name = "VerifiedByAdmin", nullable = true)
    private String verifiedByAdmin;

    @Column(name = "AdminName", nullable = true)
    private String adminName;

    @Column(name = "VerifiedTimestamp", nullable = true)
    private Timestamp verifiedTimestamp;

    @Column(name = "VerificationREMARKS", nullable = true)
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
