package com.kerjahubs.userservice.model.request.kelas;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

@Data
public class RequestAddBenefitProduct {
    private String id = DefaultValues.emptyString;
    private String productId = DefaultValues.emptyString;
    private String benefitName = DefaultValues.emptyString;
    private Integer sequence = DefaultValues.emptyInteger;
    private Boolean status = Boolean.TRUE;
}
