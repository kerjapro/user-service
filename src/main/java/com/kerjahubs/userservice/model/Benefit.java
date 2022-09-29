package com.kerjahubs.userservice.model;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

@Data
public class Benefit {
    private String id = DefaultValues.emptyString;
    private String benefitName = DefaultValues.emptyString;
    private Integer sequence = DefaultValues.emptyInteger;
    private String status = DefaultValues.emptyString;
}
