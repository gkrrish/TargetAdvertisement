package com.advt.request;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateAdvertisementRequest {

	@NotNull
	private Integer advertiserId;

	@NotNull
	private Integer locationId;

	@Pattern(regexp = "image")
	private String mediaType;

	@NotNull
	private Integer fileSize;

	@NotNull
	private Integer userCount;

	@NotNull
	private BigDecimal price;

	@NotNull
	private Timestamp startTime;

	@NotNull
	private Timestamp endTime;

	private String advertisementType;
	private BigDecimal amount;
	private String tariffOffer;
}
