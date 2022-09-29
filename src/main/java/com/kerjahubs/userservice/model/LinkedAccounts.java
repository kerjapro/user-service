package com.kerjahubs.userservice.model;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

@Data
public class LinkedAccounts {
    private String apps = DefaultValues.emptyString;
    private String appsId = DefaultValues.emptyString;
    private String appsCustomerName = DefaultValues.emptyString;
    private String appsCustomerImage = DefaultValues.emptyString;
    private String appsCustomerEmail = DefaultValues.emptyString;
}
