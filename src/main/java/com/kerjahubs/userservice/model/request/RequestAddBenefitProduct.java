package com.kerjahubs.userservice.model.request;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.userservice.model.Benefit;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RequestAddBenefitProduct {
    private String id = DefaultValues.emptyString;
    private String productId = DefaultValues.emptyString;
    private String benefitName = DefaultValues.emptyString;
    private Integer sequence = DefaultValues.emptyInteger;
    private Boolean status = Boolean.TRUE;
}
