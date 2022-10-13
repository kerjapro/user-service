package com.kerjahubs.userservice.model.request.user;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.userservice.model.RetailDataEdit;
import lombok.Data;

@Data
public class RequestEditProfileDataRetail {
    private String cid = DefaultValues.emptyString;
    private String email = DefaultValues.emptyString;
    private String phoneNumber = DefaultValues.emptyString;
    private RetailDataEdit retailData = new RetailDataEdit();
}
