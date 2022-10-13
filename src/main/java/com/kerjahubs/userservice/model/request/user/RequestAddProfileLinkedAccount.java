package com.kerjahubs.userservice.model.request.user;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

@Data
public class RequestAddProfileLinkedAccount {
    public String cid = DefaultValues.emptyString;
    public String groupType = DefaultValues.emptyString;
    public String apps = DefaultValues.emptyString;
    public String appsId = DefaultValues.emptyString;
    public String appsCustomerName = DefaultValues.emptyString;
    public String appsCustomerImage = DefaultValues.emptyString;
    public String appsCustomerEmail = DefaultValues.emptyString;
    public String appsToken = DefaultValues.emptyString;
}
