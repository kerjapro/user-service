package com.kerjahubs.userservice.model.request.user;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

@Data
public class RequestEditProfileDataPartner {
    private String partnerId = DefaultValues.emptyString;
    private String partnerName = DefaultValues.emptyString;
    private String about = DefaultValues.emptyString;
    private String tagline = DefaultValues.emptyString;
    private String website = DefaultValues.emptyString;
    private String provinceId = DefaultValues.emptyString;
    private String provinceName = DefaultValues.emptyString;
    private String districtId = DefaultValues.emptyString;
    private String districtName = DefaultValues.emptyString;
    private String address = DefaultValues.emptyString;
    private String postalCode = DefaultValues.emptyString;
    private String maps = DefaultValues.emptyString;
}
