package com.kerjahubs.userservice.model.response;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.userservice.model.PartnerData;
import com.kerjahubs.userservice.model.RetailData;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseLogin {
    private String cid = DefaultValues.emptyString;
    private String email = DefaultValues.emptyString;
    private String phoneNumber = DefaultValues.emptyString;
    private String type = DefaultValues.emptyString;
    private Boolean isVerified = Boolean.FALSE;
    private String lastLogin = DefaultValues.emptyString;
    private RetailData retailData = new RetailData();
    private List<PartnerData> partnerData = new ArrayList<>();
    private String sessionId = DefaultValues.emptyString;
}
