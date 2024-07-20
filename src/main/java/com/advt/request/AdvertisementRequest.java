package com.advt.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@JsonDeserialize
public class AdvertisementRequest {

	/*@NotBlank(message = "File location is required")
	private String fileLocation;*/

	@DecimalMin(value = "0.0", message = "Paid amount must be non-negative")
	private BigDecimal paidAmount;

	@NotNull
	private Integer advertisementId;
	
	
	@NonNull
	private String batchTime;


}
