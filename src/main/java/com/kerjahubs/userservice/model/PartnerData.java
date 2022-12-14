package com.kerjahubs.userservice.model;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.userservice.model.dto.user.DocumentDto;
import com.kerjahubs.userservice.model.dto.user.LinkedAccountsDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PartnerData {
    private String partnerId = DefaultValues.emptyString;
    private String partnerName = DefaultValues.emptyString;
    private String partnerType = DefaultValues.emptyString;
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
    private List<DocumentDto> documents = new ArrayList<>();
    private List<LinkedAccountsDto> linkedAccounts = new ArrayList<>();
}
