package com.kerjahubs.userservice.model.request;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestLowongan {
    public String id = DefaultValues.emptyString;
    public String title = DefaultValues.emptyString;
    public String description = DefaultValues.emptyString;
    public BigDecimal salaryStart = BigDecimal.ZERO;
    public BigDecimal salaryEnd = BigDecimal.ZERO;
    public String workingType = DefaultValues.emptyString;
    public String postingDate = DefaultValues.emptyString;
    public String expiredDate = DefaultValues.emptyString;
    public String partnerId = DefaultValues.emptyString;
    public String companyIcon = DefaultValues.emptyString;
    public String companyImage = DefaultValues.emptyString;
}
