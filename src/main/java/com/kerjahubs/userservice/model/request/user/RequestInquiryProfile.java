package com.kerjahubs.userservice.model.request.user;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

@Data
public class RequestInquiryProfile {
    private String cid = DefaultValues.emptyString;
}
