package com.advt.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AdvertiserDetailsRequest {

    @NotNull
    private String advertiserName;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^\\+91[1-9]\\d{9}$", message = "Invalid Indian mobile number")
    private String advertiserMobileNumber;

    private String contactDetails;

    @NotNull
    private String status;
}
