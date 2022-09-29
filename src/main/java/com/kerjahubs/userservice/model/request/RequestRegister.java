package com.kerjahubs.userservice.model.request;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.userservice.model.OtherAccount;
import lombok.Data;

@Data
public class RequestRegister {
    private String fullName = DefaultValues.emptyString;
    private String email = DefaultValues.emptyString;
    private String phoneNumber = DefaultValues.emptyString;
    private String password = DefaultValues.emptyString;
    private Boolean isUsingOtherAccount = Boolean.FALSE;
    private OtherAccount otherAccount = new OtherAccount();
    private String type = DefaultValues.emptyString;
    private String registrationDate = DefaultValues.emptyString;
    private String registrationPlatform = DefaultValues.emptyString;
}
