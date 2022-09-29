package com.kerjahubs.userservice.model.request;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestAddProduct {
    private String id = DefaultValues.emptyString;
    private String partnerId = DefaultValues.emptyString;
    private String sectorId = DefaultValues.emptyString;
    private String sectorSubId = DefaultValues.emptyString;
    private String productType = DefaultValues.emptyString;
    private String productName = DefaultValues.emptyString;
    private String productDesc = DefaultValues.emptyString;
    private BigDecimal price = BigDecimal.ZERO;
    private Integer seat = DefaultValues.emptyInteger;
    private String image = DefaultValues.emptyString;
    private String language = DefaultValues.emptyString;
    private String eventDate = DefaultValues.emptyString;
    private String expiredDate = DefaultValues.emptyString;
    private String status = DefaultValues.emptyString;
}
