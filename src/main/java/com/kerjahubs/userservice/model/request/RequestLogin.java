package com.kerjahubs.userservice.model.request;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.userservice.model.OtherAccount;
import lombok.Data;

@Data
public class RequestLogin {
    private String username = DefaultValues.emptyString;
    private String password = DefaultValues.emptyString;
    private Boolean isUsingOtherAccount = Boolean.FALSE;
    private OtherAccount otherAccount = new OtherAccount();
}
