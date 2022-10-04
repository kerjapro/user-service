package com.kerjahubs.userservice.model.request;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.userservice.model.Documents;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RequestRegisterPartner {
    private String cid = DefaultValues.emptyString;
    private String partnerType = DefaultValues.emptyString;
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
    private String nik = DefaultValues.emptyString;
    private String npwpNumber = DefaultValues.emptyString;
    private List<Documents> documents = new ArrayList<>();
}
