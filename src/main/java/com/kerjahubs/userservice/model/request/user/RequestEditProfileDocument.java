package com.kerjahubs.userservice.model.request.user;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.userservice.model.PartnerData;
import com.kerjahubs.userservice.model.RetailData;
import lombok.Data;

@Data
public class RequestEditProfileDocument {
    private String cid = DefaultValues.emptyString;
    private RetailData retailData = new RetailData();
    private PartnerData partnerData = new PartnerData();
}
