package com.advt.main.entity;

import java.util.List;

import com.advt.entity.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ADVERTISER_DETAILS")
@Data
public class AdvertiserDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "advertiser_details_seq")
    @SequenceGenerator(name = "advertiser_details_seq", sequenceName = "advertiser_details_seq", allocationSize = 1)
    @Column(name = "advertiser_id")
    private Integer advertiserId;

    @Column(name = "UserID", nullable = true)
    private Integer userId;

    @Column(name = "ISUSER", nullable = false)
    private String isUser = "N";

    @Column(name = "advertiser_name", nullable = false)
    private String advertiserName;

    @Column(name = "advertiser_mobile_number", nullable = true)
    private String advertiserMobileNumber;

    @Column(name = "contact_details", nullable = true)
    private String contactDetails;

    @Column(name = "status", nullable = false)
    private String status;
    
    @OneToMany(mappedBy = "advertiserDetails")
    private List<TargetedAdvertisements> targetedAdvertisements;

    @ManyToOne
    @JoinColumn(name = "UserID", insertable = false, updatable = false)
    private UserDetails userDetails;
}
