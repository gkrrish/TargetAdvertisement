package com.advt.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

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
@Table(name = "TARGETED_ADVERTISEMENT_PLAN")
@Data
public class TargetedAdvertisementPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "advt_plan_seq")
	@SequenceGenerator(name = "advt_plan_seq", sequenceName = "advt_plan_seq", allocationSize = 1)
	@Column(name = "advertisement_id")
	private Integer advertisementId;

	@Column(name = "location_id", nullable = false)
	private Integer locationId;

	@Column(name = "media_type", nullable = false)
	private String mediaType;

	@Column(name = "file_length", nullable = true)
	private Integer fileLength;

	@Column(name = "file_size", nullable = true)
	private Integer fileSize;

	@Column(name = "user_count", nullable = false)
	private Integer userCount;

	@Column(name = "price", nullable = false)
	private BigDecimal price;

	@Column(name = "start_time", nullable = false)
	private Timestamp startTime;

	@Column(name = "end_time", nullable = false)
	private Timestamp endTime;

	@Column(name = "advertisement_type", nullable = true)
	private String advertisementType;

	@Column(name = "amount", nullable = true)
	private BigDecimal amount;

	@Column(name = "tariff_offer", nullable = true)
	private String tariffOffer;

	@ManyToOne
    @JoinColumn(name = "location_id", insertable = false, updatable = false)
    private StatewiseLocation masterStatewiseLocation;
}
