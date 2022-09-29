package com.kerjahubs.userservice.model;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

@Data
public class Module {
    private String id = DefaultValues.emptyString;
    private String modulType = DefaultValues.emptyString;
    private String modulName = DefaultValues.emptyString;
    private String modulDesc = DefaultValues.emptyString;
    private String file = DefaultValues.emptyString;
    private Integer sequence = DefaultValues.emptyInteger;
    private String status = DefaultValues.emptyString;
}
