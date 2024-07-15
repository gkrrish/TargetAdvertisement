package com.advt.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class AdvertisementPlanRequest {

	@NotNull
	private Integer locationId;

	@Pattern(regexp = "^(audio|video|image|pdf)$", message = "Media type must be 'audio', 'video', 'image', or 'pdf'")
	private String mediaType;

	@NotNull
	private Timestamp startTime;

	@NotNull
	private Timestamp endTime;

	@Min(value = 0, message = "File length must be positive")
	private Integer fileLength;

	@Min(value = 0, message = "File size must be positive")
	private Integer fileSize;

	@NotNull
	@Min(value = 0, message = "User count must be positive")
	private Integer userCount;

	@NotNull
	@DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
	private BigDecimal price;

	private String advertisementType;

	@DecimalMin(value = "0.0", message = "Amount must be non-negative")
	private BigDecimal amount;

	private String tariffOffer;
}
