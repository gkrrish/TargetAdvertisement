package com.advt.request;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AdvertisementRequest {
    private Integer advertisementId;

    @NotNull
    private Integer advertiserId;

    @NotNull
    private Integer locationId;

    @NotNull
    @Pattern(regexp = "audio|video|image|pdf", message = "Invalid media type. Must be one of: audio, video, image, pdf")
    private String mediaType;

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

    @NotNull
    private Timestamp startTime;

    @NotNull
    private Timestamp endTime;

    private String advertisementType;

    @DecimalMin(value = "0.0", message = "Amount must be non-negative")
    private BigDecimal amount;

    private String tariffOffer;

    @NotBlank(message = "File location is required")
    private String fileLocation;

    @Pattern(regexp = "Y|N", message = "Paid must be 'Y' or 'N'")
    private String paid;

    @DecimalMin(value = "0.0", message = "Paid amount must be non-negative")
    private BigDecimal paidAmount;

    @Pattern(regexp = "Y|N", message = "Verified by admin must be 'Y' or 'N'")
    private String verifiedByAdmin;

    private String adminName;

    private Timestamp verifiedTimestamp;

    private String verificationRemarks;

    @Pattern(regexp = "Y|N", message = "Verified allow to publish must be 'Y' or 'N'")
    private String verifiedAllowToPublish;
}
