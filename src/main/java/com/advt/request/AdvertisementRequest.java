package com.advt.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdvertisementRequest {

	/*@NotBlank(message = "File location is required")
	private String fileLocation;*/

	@DecimalMin(value = "0.0", message = "Paid amount must be non-negative")
	private BigDecimal paidAmount;

	@NotNull
	private Integer advertisementId;

}
