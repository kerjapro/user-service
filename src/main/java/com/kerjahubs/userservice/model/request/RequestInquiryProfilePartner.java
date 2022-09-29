package com.kerjahubs.userservice.model.request;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

@Data
public class RequestInquiryProfilePartner {
    private String cid = DefaultValues.emptyString;
}
