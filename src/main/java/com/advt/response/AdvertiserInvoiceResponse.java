package com.advt.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class AdvertiserInvoiceResponse {
    private Integer advertiserId;
    private String advertiserName;
    private String advertiserMobileNumber;
    private Integer targetAdvtId;
    private String fileLocation;
    private Timestamp createdDate;
    private String paid;
    private BigDecimal paidAmount;
    private String verifiedByAdmin;
    private String adminName;
    private Timestamp verifiedTimestamp;
    private String verificationRemarks;
    private String verifiedAllowToPublish;
    private Integer advertisementId;
    private Integer locationId;
    private String mediaType;
    private Integer fileLength;
    private Integer fileSize;
    private Integer userCount;
    private BigDecimal price;
    private Timestamp startTime;
    private Timestamp endTime;
    private String advertisementType;
    private BigDecimal amount;
    private String tariffOffer;
    private String locationName;
    private Integer batchId;

    public AdvertiserInvoiceResponse(Integer advertiserId, String advertiserName, String advertiserMobileNumber, Integer targetAdvtId, 
                       String fileLocation, Timestamp createdDate, String paid, BigDecimal paidAmount, String verifiedByAdmin, 
                       String adminName, Timestamp verifiedTimestamp, String verificationRemarks, String verifiedAllowToPublish, 
                       Integer advertisementId, Integer locationId, String mediaType, Integer fileLength, Integer fileSize, 
                       Integer userCount, BigDecimal price, Timestamp startTime, Timestamp endTime, String advertisementType, 
                       BigDecimal amount, String tariffOffer, String locationName, Integer batchId) {
        this.advertiserId = advertiserId;
        this.advertiserName = advertiserName;
        this.advertiserMobileNumber = advertiserMobileNumber;
        this.targetAdvtId = targetAdvtId;
        this.fileLocation = fileLocation;
        this.createdDate = createdDate;
        this.paid = paid;
        this.paidAmount = paidAmount;
        this.verifiedByAdmin = verifiedByAdmin;
        this.adminName = adminName;
        this.verifiedTimestamp = verifiedTimestamp;
        this.verificationRemarks = verificationRemarks;
        this.verifiedAllowToPublish = verifiedAllowToPublish;
        this.advertisementId = advertisementId;
        this.locationId = locationId;
        this.mediaType = mediaType;
        this.fileLength = fileLength;
        this.fileSize = fileSize;
        this.userCount = userCount;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.advertisementType = advertisementType;
        this.amount = amount;
        this.tariffOffer = tariffOffer;
        this.locationName = locationName;
        this.batchId=batchId;
    }
}
