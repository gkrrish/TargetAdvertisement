package com.advt.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class AdvertisementPlanRequest {

    @NotNull
    private Integer advertiserId;

    @NotNull
    private Integer locationId;

    @Pattern(regexp = "^(audio|video|image|pdf)$", message = "Media type must be 'audio', 'video', 'image', or 'pdf'")
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
